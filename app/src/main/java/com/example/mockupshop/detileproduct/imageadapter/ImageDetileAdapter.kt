package com.example.mockupshop.detileproduct.imageadapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.mockupshop.R
import com.example.mockupshop.databinding.ActivityImageDetileAdapterBinding
import com.facebook.shimmer.Shimmer
import com.facebook.shimmer.ShimmerDrawable

class ImageDetileAdapter(private val context: Context, private val items: List<String>) :
    RecyclerView.Adapter<ImageDetileAdapter.ImageDetileViewHolder>() {

    private lateinit var onItemClick: OnImageClickListener
    fun onClick(listener: OnImageClickListener) {
        onItemClick = listener
    }

    inner class ImageDetileViewHolder(val binding: ActivityImageDetileAdapterBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bindData(item: String) {
            binding.run {
                Glide.with(context).load(item)
                    .placeholder(R.drawable.app_icon)
                    .error(R.drawable.app_icon)
                    .into(ivProduct)

                root.setOnClickListener {
                    onItemClick.onClick(item)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup,
        viewType: Int): ImageDetileViewHolder {
        return ImageDetileViewHolder(
            ActivityImageDetileAdapterBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(
        holder: ImageDetileAdapter.ImageDetileViewHolder,
        position: Int) {
        holder.bindData(items[position])
    }

    override fun getItemCount(): Int = items.size
}