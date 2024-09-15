package com.gkp.auth.data.session

import androidx.datastore.core.Serializer
import com.gkp.auth.domain.AuthInfo
import com.gkp.core.data.cryptography.AndroidCryptographyManger
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import java.io.InputStream
import java.io.OutputStream

class DataStoreAuthInfoSerializer(
    override val defaultValue: AuthInfo,
) : Serializer<AuthInfo> {

    override suspend fun readFrom(input: InputStream): AuthInfo {
        val decryptedBytes = input.use {
            AndroidCryptographyManger.decryptData(it)
        }
        return Json.decodeFromString(decryptedBytes.decodeToString())
    }

    override suspend fun writeTo(t: AuthInfo, output: OutputStream) {
        output.use { stream ->
            AndroidCryptographyManger.encryptData(
                dataToEncrypt = Json.encodeToString(t).encodeToByteArray(),
                encryptedOutput = stream
            )
        }
    }
}