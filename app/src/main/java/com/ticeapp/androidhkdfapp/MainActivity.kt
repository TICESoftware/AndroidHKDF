package com.ticeapp.androidhkdfapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

import com.ticeapp.androidhkdf.*

import com.goterl.lazysodium.LazySodiumAndroid
import com.goterl.lazysodium.SodiumAndroid

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        testKeyDerivation()
    }

    fun testKeyDerivation() {
        val sodium = LazySodiumAndroid(SodiumAndroid())
        val ikm = sodium.sodiumHex2Bin("AABBCCDDEEFF00112233445566778899")
        val salt = sodium.sodiumHex2Bin("00112233445566778899AABBCCDDEEFF00112233445566778899AABBCCDDEEFF")
        val info = "Info"
        val L = 29

        val key = deriveHKDFKey(ikm, salt, info, L)

        if (key.size != L) {
            throw Exception("Test failed")
        }
    }
}
