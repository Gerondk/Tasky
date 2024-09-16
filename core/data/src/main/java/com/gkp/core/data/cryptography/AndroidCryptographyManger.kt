package com.gkp.core.data.cryptography

import android.security.keystore.KeyGenParameterSpec
import android.security.keystore.KeyProperties
import java.io.InputStream
import java.io.OutputStream
import java.security.KeyStore
import java.security.KeyStore.SecretKeyEntry
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

    fun encryptData(dataToEncrypt: ByteArray, encryptedOutput: OutputStream) {
        val encryptedBytes = encryptCypher.doFinal(dataToEncrypt)
        encryptedOutput.use { stream ->
            with(stream) {
                write(encryptCypher.iv.size)
                write(encryptCypher.iv)
                write(encryptedBytes.size)
                write(encryptedBytes)
            }
        }
    }

    fun decryptData(encryptedInput: InputStream): ByteArray {
        return encryptedInput.use { stream ->
            with(stream) {
                val ivSize = read()
                val iv = ByteArray(ivSize)
                read(iv)

                val encryptedByteSize = read()
                val encryptedBytes = ByteArray(encryptedByteSize)
                read(encryptedBytes)

                getDecryptCipher(iv).doFinal(encryptedBytes)
            }
        }
    }

    private val keyStore = KeyStore.getInstance("AndroidKeyStore").apply {
        load(null)
    }

    private val encryptCypher = Cipher.getInstance(TRANSFORMATION).apply {
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
}