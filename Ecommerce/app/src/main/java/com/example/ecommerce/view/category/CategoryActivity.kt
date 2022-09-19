package com.example.ecommerce.view.category

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.ecommerce.R
import com.example.ecommerce.databinding.ActivityCategoryBinding
import com.example.ecommerce.model.ProductItem
import com.example.ecommerce.model.ResponseModelItem
import com.example.ecommerce.utils.NetworkResult
import com.example.ecommerce.view.cart.CartActivity
import com.example.ecommerce.view.product.ProductActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CategoryActivity : AppCompatActivity(), OnItemClick {
    private lateinit var categoryBinding: ActivityCategoryBinding
    private val viewModel by viewModels<CategoryViewModel>()
    private lateinit var categoryAdapter: CategoryAdapter
    val tempList = mutableListOf<ResponseModelItem>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        categoryBinding = ActivityCategoryBinding.inflate(layoutInflater)
        setContentView(categoryBinding.root)
        categoryBinding.toolbar.txtCenter.text = getString(R.string.semessta)
        categoryBinding.toolbar.btnDone.visibility = View.VISIBLE
        categoryBinding.toolbar.btnDone.setOnClickListener {
            val intent = Intent(this, CartActivity::class.java)
            startActivity(intent)
        }

        viewModel.getListOfCategory()
        setUpRecyclerView()
        subscribeToObservables()

    }

    /**
     * Calling api
     */

    private fun subscribeToObservables() {
        viewModel.responseLiveData.observe(this) { response ->
            when (response) {
                is NetworkResult.Success -> {
                    categoryBinding.progressCircular.visibility = View.GONE
                    response.data?.let {
                        tempList.addAll(it)
                        categoryAdapter.differ.submitList(tempList)
                        categoryAdapter.notifyDataSetChanged()
                    }
                }
                is NetworkResult.Error -> {
                    categoryBinding.progressCircular.visibility = View.GONE
                    Toast.makeText(this@CategoryActivity, response.message, Toast.LENGTH_SHORT)
                        .show()
                }
                is NetworkResult.Loading -> {
                    categoryBinding.progressCircular.visibility = View.VISIBLE
                }
            }
        }
    }

    /**
     * Setting up the recyclerview
     */

    private fun setUpRecyclerView() {
        categoryAdapter = CategoryAdapter(this)
        categoryBinding.rvCategory.apply {
            adapter = categoryAdapter
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        }
    }

    /**
     * Opening details page on click of item
     */

    override fun onClickOfItem(responseModelItem: ResponseModelItem, position: Int) {
        val productImage = responseModelItem.baseImage
        val productTitle = responseModelItem.pname
        val productPrice = responseModelItem.pprice
        val productSpecialPrice = responseModelItem.specialPrice
        val productDesc = responseModelItem.description
        val shortDesc = responseModelItem.shortDescription

        val intent = Intent(this, ProductActivity::class.java)
        intent.putExtra(
            "extra_item",
            ProductItem(
                productImage,
                productTitle,
                productPrice,
                productSpecialPrice,
                productDesc,
                shortDesc
            )
        )
        startActivity(intent)
    }
}