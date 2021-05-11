package com.ticeapp.androidhkdfapp

import com.goterl.lazysodium.LazySodiumAndroid
import com.goterl.lazysodium.SodiumAndroid
import com.ticeapp.androidhkdf.deriveHKDFKey
import org.junit.Test

import org.junit.Assert.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun keyDerivation() {
        val sodium = LazySodiumAndroid(SodiumAndroid())
        val ikm = sodium.sodiumHex2Bin("AABBCCDDEEFF00112233445566778899")
        val salt = sodium.sodiumHex2Bin("00112233445566778899AABBCCDDEEFF00112233445566778899AABBCCDDEEFF")
        val info = "Info"
        val L = 29

        val key = deriveHKDFKey(ikm, salt, info, L)
        val hexKey = sodium.sodiumBin2Hex(key)
        print(hexKey)
        print("KEY SIZE:  ${key.size}, L: $L")
    }
}
