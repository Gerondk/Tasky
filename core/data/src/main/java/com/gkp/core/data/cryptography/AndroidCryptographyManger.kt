package com.gkp.core.data.cryptography

import android.security.keystore.KeyGenParameterSpec
import android.security.keystore.KeyProperties
import java.io.InputStream
import java.io.OutputStream
import java.nio.ByteBuffer
import java.security.KeyStore
import java.security.KeyStore.SecretKeyEntry
import java.security.SecureRandom
import javax.crypto.Cipher
import javax.crypto.KeyGenerator
import javax.crypto.SecretKey
import javax.crypto.spec.IvParameterSpec

object AndroidCryptographyManger {
    private const val ALGORITHM = KeyProperties.KEY_ALGORITHM_AES
    private const val BLOCK_MODE = KeyProperties.BLOCK_MODE_CBC
    private const val PADDING = KeyProperties.ENCRYPTION_PADDING_PKCS7
    private const val KEY_ALIAS = "secret"
    private const val TRANSFORMATION = "$ALGORITHM/$BLOCK_MODE/$PADDING"
    private const val  INT_SIZE_IN_BYTES = 4

    fun encryptData(dataToEncrypt: ByteArray, encryptedOutput: OutputStream) {
        val encryptedBytes = encryptCypher.doFinal(dataToEncrypt)
        encryptedOutput.use { stream ->
            with(stream) {
                writeInteger(encryptCypher.iv.size)
                write(encryptCypher.iv)
                write(encryptedBytes)
            }
        }
    }

    fun decryptData(encryptedInput: InputStream): ByteArray {
        return encryptedInput.use { stream ->
            with(stream) {
                val ivSize = readInteger()
                val iv = ByteArray(ivSize)
                read(iv)

                val encryptedBytes = readBytes()
                getDecryptCipher(iv).doFinal(encryptedBytes)
            }
        }
    }

    private val keyStore = KeyStore.getInstance("AndroidKeyStore").apply {
        load(null)
    }

    // Put this get Avoid generating new cipher
    private val encryptCypher : Cipher
    get() = Cipher.getInstance(TRANSFORMATION).apply {
        init(Cipher.ENCRYPT_MODE, getKey())
    }

    private fun getDecryptCipher(iv: ByteArray) = Cipher.getInstance(TRANSFORMATION).apply {
        init(Cipher.DECRYPT_MODE, getKey(), IvParameterSpec(iv))
    }

    private fun getKey(): SecretKey {
        val existingKeyEntry = keyStore.getEntry(KEY_ALIAS, null) as? SecretKeyEntry
        return existingKeyEntry?.secretKey ?: createKey()
    }

    private fun createKey(): SecretKey {
        return  KeyGenerator.getInstance(ALGORITHM).apply {
            init(
                KeyGenParameterSpec.Builder(
                    KEY_ALIAS,
                    KeyProperties.PURPOSE_ENCRYPT or KeyProperties.PURPOSE_DECRYPT
                )
                    .setBlockModes(BLOCK_MODE)
                    .setEncryptionPaddings(PADDING)
                    .setUserAuthenticationRequired(false)
                    .setRandomizedEncryptionRequired(true)
                    .build()
            )
        }.generateKey()
    }

    private fun InputStream.readInteger() : Int {
        val buffer = ByteArray(INT_SIZE_IN_BYTES)
        read(buffer)
        return ByteBuffer.wrap(buffer).int

    }

    private fun OutputStream.writeInteger(integer: Int) {
        val buffer = ByteBuffer.allocate(INT_SIZE_IN_BYTES)
            .putInt(integer)
            .array()
        write(buffer)
    }
}

