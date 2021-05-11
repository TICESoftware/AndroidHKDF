# AndroidHKDF

HMAC-based Extract-and-Expand Key Derivation Function (HKDF) as defined in <a href="https://tools.ietf.org/html/rfc5869">RFC 5869</a>.
The HMAC is provided by libsodium which uses the HMAC-SHA-512/256 algorithm. Libsodium is integrated via <a href="https://github.com/terl/lazysodium-android.git">Lazysodium</a>.

## Installation

### Jitpack
To integrate the library via jitpack add the jitpack repository to your root `build.gradle` file:

```
allprojects {
  repositories {
    ...
    maven { url 'https://jitpack.io' }
  }
}
```

You can then add the dependency to your app's `build.gradle` file where `$VERSION` specifies the specific version of the library:

```
dependencies {
  implementation 'com.github.TICESoftware:AndroidHKDF:$VERSION'
}
 ```

## Usage
For deriving a new key of length 32 bytes from some input keying material `ikm`:

```kotlin
import com.ticeapp.androidhkdf.*
import com.goterl.lazysodium.LazySodiumAndroid
import com.goterl.lazysodium.SodiumAndroid

val ikm = "Input key".encodeToByteArray()
val hkdfKey = deriveHKDFKey(ikm, L = 32)
```

A `salt` and some application specific info string (which is hashed into the HMAC) can additionally be provided:

`deriveHKDFKey(ikm, salt = salt, info = "Info", L = 32)`
