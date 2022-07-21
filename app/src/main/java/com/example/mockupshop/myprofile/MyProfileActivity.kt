package com.example.mockupshop.myprofile

import android.app.Dialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.content.res.ResourcesCompat
import com.example.mockupshop.R
import com.example.mockupshop.databinding.ActivityMyProfileBinding
import com.example.mockupshop.databinding.LayoutImageDialogBinding

class MyProfileActivity : AppCompatActivity() {

    private val binding by lazy {ActivityMyProfileBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.ivProfile.setOnClickListener {
            showDialogPicture()
        }
        binding.tbProfile.setNavigationOnClickListener {
            onBackPressed()
        }
    }

    private fun showDialogPicture() {
        val dialog = Dialog(this, com.airbnb.lottie.R.style.Base_Theme_AppCompat_Light_Dialog_MinWidth)
        val dialogBinding = LayoutImageDialogBinding.inflate(layoutInflater)
        dialog.window?.setBackgroundDrawable(
            ResourcesCompat.getDrawable(
                resources,
                com.google.android.material.R.color.mtrl_btn_transparent_bg_color,
                null
            )
        )
        dialogBinding.ivImageDialog.setImageResource(R.drawable.my_foto)
        dialog.setContentView(dialogBinding.root)
        dialog.show()
    }
}