package enjospaa.nos

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import enjospaa.nos.databinding.ActivityMainBinding
import enjospaa.nos.fragment.BlankFragment
import enjospaa.nos.fragment.IOnBackPressed
import enjospaa.nos.fragment.NoConnectionScreenFragment
import enjospaa.nos.fragment.WebViewFragment

const val URL_CONFIG_KEY = "url"

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        activity = this
        if (state == null) {
            checking()
        }
        else if (state == ResultOfChecking.WAIT_RESULT)
            return
        else {
            if (state == ResultOfChecking.CONNECTION_COMPLETE)
                webViewFragmentCall(url!!)
            if (state == ResultOfChecking.NO_CONNECTION)
                noConnectionFragmentCall()
            if (state == ResultOfChecking.LINK_MISSING || state == ResultOfChecking.EMULATOR)
                bringOutTheStub()
        }
    }

    fun checking() {
        binding.progressBar.visibility = View.VISIBLE

        state = ResultOfChecking.WAIT_RESULT

        Thread {
            val linkRequest = LinkRequest()
            val result = linkRequest.linkRequest()
            state = result

            if (result == ResultOfChecking.CONNECTION_COMPLETE) {
                url = linkRequest.url
                Handler(Looper.getMainLooper()).post { webViewFragmentCall(url!!) }
            }
            if (result == ResultOfChecking.NO_CONNECTION)
                Handler(Looper.getMainLooper()).post { noConnectionFragmentCall() }
            if (result == ResultOfChecking.LINK_MISSING || result == ResultOfChecking.EMULATOR)
                Handler(Looper.getMainLooper()).post { bringOutTheStub() }
        }.start()
    }

    companion object {
        var state: ResultOfChecking? = null
        var url: String? = null
        var activity: MainActivity? = null
    }

    private fun noConnectionFragmentCall() {
        binding.progressBar.visibility = View.GONE
        val currentFragment = activity!!.supportFragmentManager.findFragmentById(binding.fragmentContainer.id)
        if (currentFragment == null) {
            val fragment = NoConnectionScreenFragment.newInstance()
            activity!!.supportFragmentManager.beginTransaction()
                .add(binding.fragmentContainer.id, fragment)
                .commit()
        }
        activity = null
    }

    private fun bringOutTheStub() {
        binding.progressBar.visibility = View.GONE

        val currentFragment = activity!!.supportFragmentManager.findFragmentById(binding.fragmentContainer.id)
        if (currentFragment == null) {
            val fragment = BlankFragment.newInstance()
            activity!!.supportFragmentManager.beginTransaction()
                .add(binding.fragmentContainer.id, fragment)
                .commit()
        }
        activity = null
    }

    private fun webViewFragmentCall(url: String) {
        binding.progressBar.visibility = View.GONE
        val currentFragment = activity!!.supportFragmentManager.findFragmentById(binding.fragmentContainer.id)
        if (currentFragment == null) {
            val fragment = WebViewFragment.newInstance(url)
            activity!!.supportFragmentManager.beginTransaction()
                .add(binding.fragmentContainer.id, fragment)
                .commit()
        }
        activity = null
    }

    @Deprecated("Deprecated in Java")
    override fun onBackPressed() {
        val fragment = supportFragmentManager.findFragmentById(binding.fragmentContainer.id)
        if (fragment !is IOnBackPressed || !(fragment as IOnBackPressed?)!!.onBackPressed()) {
            super.onBackPressed()
        }
    }
}