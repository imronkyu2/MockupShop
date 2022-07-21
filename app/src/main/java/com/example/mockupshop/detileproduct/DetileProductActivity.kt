package com.example.mockupshop.detileproduct

import android.os.Bundle
import android.view.animation.Animation
import android.view.animation.LinearInterpolator
import android.view.animation.RotateAnimation
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.res.ResourcesCompat
import com.bumptech.glide.Glide
import com.example.mockupshop.R
import com.example.mockupshop.data.DataDummyProduct
import com.example.mockupshop.data.TremsAndCondition
import com.example.mockupshop.data.listTremsAndCondition
import com.example.mockupshop.databinding.ActivityDetileProductBinding
import com.example.mockupshop.detileproduct.imageadapter.ImageDetileAdapter
import com.example.mockupshop.detileproduct.imageadapter.OnImageClickListener

class DetileProductActivity : AppCompatActivity() {

    private val binding by lazy { ActivityDetileProductBinding.inflate(layoutInflater) }
    private lateinit var data: DataDummyProduct

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        window.statusBarColor = ResourcesCompat.getColor(resources, R.color.blue_03b3ff,null)

        getData()
        setView()

    }

    private fun getData() {
        data = intent.getParcelableExtra(EXTRA_DATA_PRODUCT) ?: DataDummyProduct()
    }

    private fun setView() {
        binding.run {
            Glide.with(this@DetileProductActivity).load(data.image[0]).into(ivProduct)
            ratingTV.text = data.rating.toString()
            categroryTv.text = data.category
            productNameTV.text = data.name
            priceTV.text = data.price
            descriptionTV.text = data.desc
            sizeTV.text = data.size
            termAndConditionTv.text = listTremsAndCondition[0].toString()

            backButton.setOnClickListener{
                finish()
            }

            addBtn.setOnClickListener{
                Toast.makeText(this@DetileProductActivity, "Maaf, Product " + data.name + " belum bisa di tambahkan saat ini", Toast.LENGTH_SHORT).show()
            }

            layoutTermsAndConditions.setOnClickListener{
                if (expandableTermsAndConditions.isExpanded()) {
                    expandableTermsAndConditions.collapse()
                    val rotate = RotateAnimation(
                        -180f,
                        0F,
                        Animation.RELATIVE_TO_SELF,
                        0.5f,
                        Animation.RELATIVE_TO_SELF,
                        0.5f
                    )
                    rotate.duration = 700
                    rotate.fillAfter = true
                    rotate.interpolator = LinearInterpolator()
                    imageArrow.startAnimation(rotate)
                } else {
                    expandableTermsAndConditions.expand()
                    val rotate = RotateAnimation(
                        0f,
                        -180f,
                        Animation.RELATIVE_TO_SELF,
                        0.5f,
                        Animation.RELATIVE_TO_SELF,
                        0.5f
                    )
                    rotate.duration = 500
                    rotate.fillAfter = true
                    rotate.interpolator = LinearInterpolator()
                    imageArrow.startAnimation(rotate)
                }
            }
            setAdapterCompositioin()
        }
    }

    private fun setAdapterCompositioin() {

        val adapter = ImageDetileAdapter(this@DetileProductActivity, data.image)
        adapter.onClick(object : OnImageClickListener {
            override fun onClick(position: String) {
                binding.run {
                    Glide.with(this@DetileProductActivity).load(position).into(ivProduct)
                }

            }

        })


        binding.rvListComposition.adapter = adapter
        binding.rvListComposition.scheduleLayoutAnimation()
    }

    companion object {
        const val EXTRA_DATA_PRODUCT = "EXTRA_DATA_PRODUCT"
    }
}