package com.example.hacktober.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.hacktober.R
import java.security.SecureRandom
import javax.crypto.Cipher
import javax.crypto.KeyGenerator
import javax.crypto.SecretKey

class PictureActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_picture)
    }

    @Throws(Exception::class)
    fun encrypt(toEncrypt: String, key: String): ByteArray? {
        // create a binary key from the argument key (seed)
        val sr = SecureRandom(key.toByteArray())
        val kg: KeyGenerator = KeyGenerator.getInstance("twofish")
        kg.init(sr)
        val sk: SecretKey = kg.generateKey()

        // create an instance of cipher
        val cipher: Cipher = Cipher.getInstance("twofish")

        // initialize the cipher with the key
        cipher.init(Cipher.ENCRYPT_MODE, sk)

        // enctypt!
        return cipher.doFinal(toEncrypt.toByteArray())
    }

    @Throws(Exception::class)
    fun decrypt(toDecrypt: ByteArray?, key: String): String? {
        // create a binary key from the argument key (seed)
        val sr = SecureRandom(key.toByteArray())
        val kg: KeyGenerator = KeyGenerator.getInstance("twofish")
        kg.init(sr)
        val sk: SecretKey = kg.generateKey()

        // do the decryption with that key
        val cipher: Cipher = Cipher.getInstance("twofish")
        cipher.init(Cipher.DECRYPT_MODE, sk)
        val decrypted: ByteArray = cipher.doFinal(toDecrypt)
        return String(decrypted)
    }
}