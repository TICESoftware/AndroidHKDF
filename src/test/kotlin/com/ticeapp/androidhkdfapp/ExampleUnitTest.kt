package com.ticeapp.androidhkdfapp

import com.goterl.lazysodium.LazySodiumJava
import com.goterl.lazysodium.SodiumJava
import com.ticeapp.androidhkdf.deriveHKDFKey
import org.junit.Test

class ExampleUnitTest {
    @Test
    fun keyDerivation() {
        val sodium = LazySodiumJava(SodiumJava())
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
