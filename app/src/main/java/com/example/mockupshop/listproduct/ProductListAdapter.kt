package com.example.mockupshop.listproduct

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.AdapterView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.mockupshop.R
import com.example.mockupshop.data.DataDummyProduct
import com.example.mockupshop.databinding.ActivityProductListAdapterBinding

class ProductListAdapter(private val context: Context, private val items: List<DataDummyProduct>) :
    RecyclerView.Adapter<ProductListAdapter.ProductListHolder>() {

    inner class ProductListHolder(private val binding: ActivityProductListAdapterBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bindData(item: DataDummyProduct, position: Int) {
            binding.run {
                Glide.with(context).load(item.image[0])
                    .placeholder(R.drawable.app_icon)
                    .error(R.drawable.app_icon)
                    .into(ivProduct)

                categroryTv.text = item.category
                productNameTV.text = item.name
                ratingTV.text = item.rating.toString()
                priceTV.text = item.price

                root.setOnClickListener {
                    onItemClick.onClick(position, item)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductListHolder {
        return ProductListHolder(
            ActivityProductListAdapterBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    private lateinit var onItemClick: OnItemClickListener
    fun clickItem(listener: OnItemClickListener) {
        onItemClick = listener
    }


    override fun onBindViewHolder(holder: ProductListHolder, position: Int) {
        holder.bindData(items[position], position)
    }

    override fun getItemCount(): Int = items.size

}