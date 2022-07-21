package com.example.mockupshop

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.mockupshop.data.DataDummyProduct
import com.example.mockupshop.data.listProductDataDummy
import com.example.mockupshop.databinding.ActivityMainBinding
import com.example.mockupshop.detileproduct.DetileProductActivity
import com.example.mockupshop.listproduct.OnItemClickListener
import com.example.mockupshop.listproduct.ProductGridAdapter
import com.example.mockupshop.listproduct.ProductListAdapter
import com.example.mockupshop.myprofile.MyProfileActivity

class MainActivity : AppCompatActivity() {

    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    private var gridView = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)


        setGridViewLayout()
        binding.ivListView.setOnClickListener {
            setListorGridView()
        }
        binding.ivProfile.setOnClickListener {
            startActivity(Intent(this, MyProfileActivity::class.java))
        }
    }

    private fun setListorGridView() {
        binding.ivListView.setImageResource(
            if (gridView) R.drawable.ic_visualization_grid_svgrepo_com
            else R.drawable.ic_visualization_list_svgrepo_com)

        if (!gridView) {
            setGridViewLayout()
            gridView = true
        } else {
            setListViewLayout()
            gridView = false
        }
    }

    private fun setGridViewLayout() {
        val adapter = ProductGridAdapter(this, listProductDataDummy)
        adapter.onClick(object : OnItemClickListener {
            override fun onClick(position: Int, data: Any?) {
                data as DataDummyProduct
                startActivity(
                    Intent(this@MainActivity, DetileProductActivity::class.java)
                        .putExtra(DetileProductActivity.EXTRA_DATA_PRODUCT, data)
                )
            }

        })
        binding.run {
            rvListProduct.layoutManager = StaggeredGridLayoutManager(2, LinearLayoutManager.VERTICAL)
            rvListProduct.adapter = adapter
            rvListProduct.setHasFixedSize(true)
            rvListProduct.scheduleLayoutAnimation()
        }
    }

    private fun setListViewLayout() {
        val adapter = ProductListAdapter(this, listProductDataDummy)
        adapter.clickItem(object : OnItemClickListener {
            override fun onClick(position: Int, data: Any?) {
                data as DataDummyProduct
                startActivity(
                    Intent(this@MainActivity, DetileProductActivity::class.java)
                        .putExtra(DetileProductActivity.EXTRA_DATA_PRODUCT, data)
                )
            }
        })
        binding.run {
            rvListProduct.layoutManager = LinearLayoutManager(this@MainActivity)
            rvListProduct.adapter = adapter
            rvListProduct.setHasFixedSize(true)
            rvListProduct.scheduleLayoutAnimation()
        }
    }
}