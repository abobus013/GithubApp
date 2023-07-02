package com.example.githubapp.presentation.fragments

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
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
import androidx.navigation.fragment.findNavController
import com.example.githubapp.MainActivity
import com.example.githubapp.R
import com.example.githubapp.databinding.FragmentLoginBinding
import net.openid.appauth.AuthorizationService

class LoginFragment : Fragment(R.layout.fragment_login) {
    private lateinit var service: AuthorizationService
    private var _binding: FragmentLoginBinding? = null
    private lateinit var sharedPreferences: SharedPreferences
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        sharedPreferences = requireContext().getSharedPreferences("my_app_prefs", Context.MODE_PRIVATE)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        (requireActivity() as MainActivity).hideBottomNavigation()

        service = AuthorizationService(requireContext())

        binding.btnSingIn.setOnClickListener {
            val accessToken = sharedPreferences.getString("access_token", null)
            if (accessToken != null) {
                navigateToMainFragment()
            } else {
                val intent = Intent(
                    Intent.ACTION_VIEW, Uri.parse(
                        "https://github.com/login/oauth/authorize?client_id=8f3cf5f09bd0c93a0528&scope=repo"
                    )
                )
                startActivity(intent)
            }
        }

        binding.btnSingInWith.setOnClickListener {
            findNavController().navigate(LoginFragmentDirections.actionLoginFragmentToMainFragment())
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

    private fun navigateToMainFragment() {
        findNavController().navigate(R.id.action_loginFragment_to_mainFragment)
    }


    override fun onResume() {
        super.onResume()
        val uri: Uri? = activity?.intent?.data
        if (uri != null) {
            val code = uri.getQueryParameter("code")
            if (code != null) {
                Log.d("Code", code)
                val accessToken = "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"
                val editor = sharedPreferences.edit()
                editor.putString("access_token", accessToken)
                editor.apply()

                Toast.makeText(requireContext(), "Login success: $code", Toast.LENGTH_SHORT)
                    .show()
                navigateToMainFragment()
            } else if (uri.getQueryParameter("error") != null) {
                Toast.makeText(requireContext(), "Something went wrong!", Toast.LENGTH_SHORT)
                    .show()
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onDestroy() {
        super.onDestroy()
        service.dispose()
    }
}