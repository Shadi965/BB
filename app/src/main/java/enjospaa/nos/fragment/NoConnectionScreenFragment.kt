package enjospaa.nos.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import enjospaa.nos.MainActivity
import enjospaa.nos.databinding.FragmentNoConnectionScreenBinding

class NoConnectionScreenFragment : Fragment() {

    private lateinit var binding: FragmentNoConnectionScreenBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentNoConnectionScreenBinding.inflate(inflater, container, false)

        binding.tryAgainBtn.setOnClickListener {
            val currentFragment = parentFragmentManager.findFragmentById(container!!.id)
            if (currentFragment != null)
                parentFragmentManager.beginTransaction()
                    .remove(currentFragment).commit()
            if (activity is MainActivity) {
                (activity as MainActivity).checking()
            }
        }

        return binding.root
    }

    companion object {
        @JvmStatic
        fun newInstance() = NoConnectionScreenFragment()
    }
}