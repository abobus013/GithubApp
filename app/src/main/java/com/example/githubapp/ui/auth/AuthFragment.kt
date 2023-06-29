package com.example.githubapp.ui.auth

import android.content.Intent
import android.graphics.Color
import android.net.Uri
import android.os.Bundle
import android.text.Spannable
import android.text.SpannableString
import android.text.TextPaint
import android.text.method.LinkMovementMethod
import android.text.style.ClickableSpan
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import com.example.githubapp.R
import com.example.githubapp.databinding.FragmentAuthBinding
import com.example.githubapp.fragments.LoginFragmentDirections
import com.example.githubapp.ui.MainActivity
import timber.log.Timber

class AuthFragment : Fragment() {

    private val viewModel: AuthViewModel by viewModels()
    private var _binding: FragmentAuthBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAuthBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        (requireActivity() as MainActivity).hideBottomNavigation()

        binding.btnSingIn.setOnClickListener {
            val intent = Intent(
                Intent.ACTION_VIEW, Uri.parse(
                    "https://github.com/login/oauth/authorize?client_id=8f3cf5f09bd0c93a0528&scope=repo"
                )
            )
            startActivity(intent)
        }

        binding.btnSingInWith.setOnClickListener {
            activity?.findNavController(R.id.fragment_container)?.navigate(
                LoginFragmentDirections.actionLoginFragmentToMainFragment()
            )
            Timber.tag("TAG").d("OK")
        }

        textLinkFirst()
        textLinkSecond()

    }


    private fun textLinkFirst() {
        val spannableString = SpannableString(getString(R.string.terms_of_use_txt))
        val clickableSpan = object : ClickableSpan() {

            override fun onClick(widget: View) {
                val intent = Intent(
                    Intent.ACTION_VIEW, Uri.parse(
                        "https://docs.github.com/en/site-policy/github-terms/github-terms-of-service"
                    )
                )
                startActivity(intent)
            }

            override fun updateDrawState(ds: TextPaint) {
                super.updateDrawState(ds)

                val customColor = Color.parseColor("#1976D2")
                ds.color = customColor
                ds.isUnderlineText = true
            }
        }
        spannableString.setSpan(clickableSpan, 0, 12, Spannable.SPAN_INCLUSIVE_EXCLUSIVE)

        binding.tvLinkFirs.text = spannableString
        binding.tvLinkFirs.movementMethod = LinkMovementMethod.getInstance()
    }

    private fun textLinkSecond() {
        val spannableString = SpannableString(getString(R.string.privacy_policy_txt))
        val clickableSpan = object : ClickableSpan() {

            override fun onClick(widget: View) {
                val intent = Intent(
                    Intent.ACTION_VIEW, Uri.parse(
                        "https://docs.github.com/en/site-policy/privacy-policies/github-privacy-statement"
                    )
                )
                startActivity(intent)
            }

            override fun updateDrawState(ds: TextPaint) {
                super.updateDrawState(ds)

                val customColor = Color.parseColor("#1976D2")
                ds.color = customColor
                ds.isUnderlineText = true
            }
        }
        spannableString.setSpan(clickableSpan, 0, 14, Spannable.SPAN_INCLUSIVE_EXCLUSIVE)

        binding.tvLinkSecond.text = spannableString
        binding.tvLinkSecond.movementMethod = LinkMovementMethod.getInstance()
    }


    override fun onResume() {
        super.onResume()
        val uri: Uri? = activity?.intent?.data
        if (uri != null) {
            val code = uri.getQueryParameter("code")
            if (code != null) {
                // Получите токен доступа
                Toast.makeText(requireContext(), "Login success: $code", Toast.LENGTH_SHORT).show()
            } else if (uri.getQueryParameter("error") != null) {
                Toast.makeText(requireContext(), "Something went wrong!", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}