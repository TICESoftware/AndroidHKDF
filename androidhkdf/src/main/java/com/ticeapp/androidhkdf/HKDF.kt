package com.ticeapp.androidhkdf

import com.goterl.lazycode.lazysodium.LazySodiumAndroid
import com.goterl.lazycode.lazysodium.SodiumAndroid
import com.goterl.lazycode.lazysodium.exceptions.SodiumException
import com.goterl.lazycode.lazysodium.interfaces.Auth

fun deriveHKDFKey(ikm: ByteArray, salt: ByteArray? = null, info: String = "", L: Int): ByteArray {
    val sodium = LazySodiumAndroid(SodiumAndroid())

    val hashOutputLength = Auth.BYTES

    val saltBytes = salt ?: ByteArray(hashOutputLength)

    if (L > 255 * hashOutputLength)
        throw IllegalArgumentException("Invalid parameter 'L'")

    if (saltBytes.size != Auth.KEYBYTES)
        throw IllegalArgumentException("Invalid parameter 'salt'")

    // Step 1: Extract
    val prk = ByteArray(hashOutputLength)
    if (!sodium.cryptoAuth(prk, ikm, ikm.size.toLong(), saltBytes)) {
        throw SodiumException("Extraction step failed.")
    }

    // Step 2: Expand
    val N = (L + hashOutputLength - 1) / hashOutputLength // equivalent to rounding up L/hashOutputLength
    var T = ByteArray(0)

    var lastTi = ByteArray(0)
    for (i in 0 until N) {
        var message = lastTi.clone()
        message += sodium.bytes(info)
        message += i.toByte()

        val currentTi = ByteArray(hashOutputLength)
        if (!sodium.cryptoAuth(currentTi, message, message.size.toLong(), prk)) {
            throw SodiumException("Expanding step failed.")
        }

        T += currentTi
        lastTi = currentTi
    }

    return T.slice(0..L-1).toByteArray()
}