package com.gkp.auth.data.session

import androidx.datastore.core.Serializer
import com.gkp.core.data.cryptography.AndroidCryptographyManger
import com.gkp.core.network.model.LoginResponse
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import java.io.InputStream
import java.io.OutputStream

class DataStoreLoginResponseSerializer(
    override val defaultValue: LoginResponse,
) : Serializer<LoginResponse> {

    override suspend fun readFrom(input: InputStream): LoginResponse {
        val decryptedBytes = input.use {
            AndroidCryptographyManger.decryptData(it)
        }
        return Json.decodeFromString(decryptedBytes.decodeToString())
    }

    override suspend fun writeTo(t: LoginResponse, output: OutputStream) {
        output.use { stream ->
            AndroidCryptographyManger.encryptData(
                dataToEncrypt = Json.encodeToString(t).encodeToByteArray(),
                encryptedOutput = stream
            )
        }
    }
}