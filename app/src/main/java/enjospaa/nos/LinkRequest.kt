package enjospaa.nos

import android.os.Build
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.ktx.Firebase
import com.google.firebase.remoteconfig.FirebaseRemoteConfig
import com.google.firebase.remoteconfig.FirebaseRemoteConfigSettings
import com.google.firebase.remoteconfig.ktx.remoteConfig
import java.io.IOException
import java.net.InetAddress
import java.net.URI
import java.util.*


class LinkRequest {
    private val remoteConfig = Firebase.remoteConfig
    var url = ""

    init {
        val configSettings = FirebaseRemoteConfigSettings.Builder()
            .setMinimumFetchIntervalInSeconds(0)
            .build()
        remoteConfig.setConfigSettingsAsync(configSettings)
        remoteConfig.setDefaultsAsync(R.xml.remote_config_defaults)
    }

    fun linkRequest() : ResultOfChecking {
        url = remoteConfig.getString(URL_CONFIG_KEY)
        if (url != "") {
            try {
                if (InetAddress.getByName(URI.create(url).host ?: url).isReachable(3000))
                    return ResultOfChecking.CONNECTION_COMPLETE
                else
                    return ResultOfChecking.NO_CONNECTION
            } catch (ex: IOException) {
                return ResultOfChecking.NO_CONNECTION
            }
        }
        else {
            if (checkIsEmu())
                return ResultOfChecking.EMULATOR
            try {
                if (!InetAddress.getByName(URI.create("https://firebase.google.com/").host ?: "firebase.google.com").isReachable(3000))
                    return ResultOfChecking.NO_CONNECTION
            } catch (ex: IOException) {
                return ResultOfChecking.NO_CONNECTION
            }
            var result = ResultOfChecking.NO_CONNECTION
            val thread = Thread {
                var t = 0
                remoteConfig.fetchAndActivate().addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        url = remoteConfig.getString(URL_CONFIG_KEY)
                        if (url == ""){
                            result = ResultOfChecking.LINK_MISSING
                        }
                        else {
                            result = ResultOfChecking.CONNECTION_COMPLETE
                            t = 50
                        }
                    }
                }
                while (t < 50) {
                    url = remoteConfig.getString(URL_CONFIG_KEY)
                    Thread.sleep(100)
                    t += 1
                }
            }
            thread.start()
            thread.join()
            return result
        }
    }

    fun test() {
        val fire = FirebaseRemoteConfig.getInstance()
        val configSettings = FirebaseRemoteConfigSettings.Builder()
            .setMinimumFetchIntervalInSeconds(3600)
            .build()
        fire.setConfigSettingsAsync(configSettings)
        fire.fetchAndActivate().addOnCompleteListener(OnCompleteListener<Boolean?> { task ->
            if (task.isSuccessful) {
                //ссылка с фаера
                val ope: String = fire.getString("url")
                val brand = Build.BRAND
            }
        })
    }

    private fun checkIsEmu(): Boolean {
        if (BuildConfig.DEBUG) return false
        val phoneModel = Build.MODEL
        val buildProduct = Build.PRODUCT
        val buildHardware = Build.HARDWARE
        val brand = Build.BRAND;
        var result = (Build.FINGERPRINT.startsWith("generic")
                || phoneModel.contains("google_sdk")
                || phoneModel.lowercase(Locale.getDefault()).contains("droid4x")
                || phoneModel.contains("Emulator")
                || phoneModel.contains("Android SDK built for x86")
                || Build.MANUFACTURER.contains("Genymotion")
                || buildHardware == "goldfish"
                || Build.BRAND.contains("google")
                || buildHardware == "vbox86"
                || buildProduct == "sdk"
                || buildProduct == "google_sdk"
                || buildProduct == "sdk_x86"
                || buildProduct == "vbox86p"
                || Build.BOARD.lowercase(Locale.getDefault()).contains("nox")
                || Build.BOOTLOADER.lowercase(Locale.getDefault()).contains("nox")
                || buildHardware.lowercase(Locale.getDefault()).contains("nox")
                || buildProduct.lowercase(Locale.getDefault()).contains("nox"))
        if (result) return true
        result = result or (Build.BRAND.startsWith("generic") && Build.DEVICE.startsWith("generic"))
        if (result) return true
        result = result or ("google_sdk" == buildProduct)
        return result
    }
}