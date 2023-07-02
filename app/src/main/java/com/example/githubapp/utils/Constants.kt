package com.example.githubapp.utils

import android.widget.Toast
import androidx.fragment.app.Fragment

const val GITHUB_CLIENT_ID = "8f3cf5f09bd0c93a0528"
const val GITHUB_CLIENT_SECRET ="5447af3efb5afba3751aa6a0025e97affcf1a538"

fun Fragment.toast(message: String, length: Int = Toast.LENGTH_SHORT) {
    Toast.makeText(requireContext(), message, length).show()
}



