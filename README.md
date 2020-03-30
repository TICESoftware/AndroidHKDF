# AndroidHKDF

HMAC-based Extract-and-Expand Key Derivation Function (HKDF) as defined in <a href="https://tools.ietf.org/html/rfc5869">RFC 5869</a>.
The HMAC is provided by libsodium which uses the HMAC-SHA-512/256 algorithm.

## Usage
For deriving a new key of length 32 bytes from some input keying material `ikm`:

```kotlin
import com.ticeapp.androidhkdf.*
import com.goterl.lazycode.lazysodium.LazySodiumAndroid
import com.goterl.lazycode.lazysodium.SodiumAndroid

val ikm = "Input key".encodeToByteArray()
val hkdfKey = deriveHKDFKey(ikm, L = 32)
```

A `salt` and some application specific info string (which is hashed into the HMAC) can additionally be provided:

`deriveHKDFKey(ikm, salt = salt, info = "Info", L = 32)`
