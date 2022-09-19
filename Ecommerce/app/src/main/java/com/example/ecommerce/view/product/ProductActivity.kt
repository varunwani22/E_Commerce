package com.example.ecommerce.view.product

import android.graphics.Paint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import coil.load
import com.example.ecommerce.R
import com.example.ecommerce.databinding.ActivityCategoryBinding
import com.example.ecommerce.databinding.ActivityProductBinding
import com.example.ecommerce.local.ProductEntity
import com.example.ecommerce.model.ProductItem
import com.example.ecommerce.view.category.CategoryViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProductActivity : AppCompatActivity() {
    private lateinit var productActivityBinding: ActivityProductBinding
    private val viewModel by viewModels<ProductViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        productActivityBinding = ActivityProductBinding.inflate(layoutInflater)
        setContentView(productActivityBinding.root)

        productActivityBinding.toolbar.txtCenter.text = getString(R.string.product_details)
        productActivityBinding.toolbar.imgBack.visibility = View.VISIBLE

        productActivityBinding.toolbar.imgBack.setOnClickListener {
            finish()
        }

        var item = intent.getParcelableExtra<ProductItem>("extra_item")

        productActivityBinding.apply {
            productImage.load("https://www.semessta.com/media/catalog/product" + item?.prodImage)
            productPrice.paintFlags = Paint.STRIKE_THRU_TEXT_FLAG
            productTitle.text = item?.prodTitle
            productDesc.text = item?.prodDesc
            productSpecialPrice.text =

                "₹ ${
                    String.format(
                        "%.2f", item?.prodSpecialPrice?.toDouble()
                    )
                }"
            productPrice.text =
                "₹ ${
                    String.format(
                        "%.2f", item?.prodPrice?.toDouble()
                    )
                }"
        }

        /**
         * Adding item to cart
         */

        productActivityBinding.btnAddToBag.setOnClickListener {
            val image = "https://www.semessta.com/media/catalog/product" + item?.prodImage
            val title = item?.prodTitle
            val price = item?.prodPrice
            val specialPrice = item?.prodSpecialPrice

            val newEntry = ProductEntity(image, title, price, specialPrice)
            viewModel.insertRecord(newEntry)
            Toast.makeText(this, "Added to cart", Toast.LENGTH_SHORT).show()
            finish()
        }

    }
}