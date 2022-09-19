package com.example.ecommerce.view.cart


import android.graphics.Paint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.ecommerce.databinding.CategoryItemLayoutBinding
import com.example.ecommerce.local.ProductEntity


class CartAdapter(private val newList: List<ProductEntity>, val listner: OnClickDelete) :
    RecyclerView.Adapter<CartAdapter.CategoryViewHolder>() {
    private lateinit var binding: CategoryItemLayoutBinding

    inner class CategoryViewHolder(
        private val categoryItemLayoutBinding: CategoryItemLayoutBinding,
    ) :
        RecyclerView.ViewHolder(categoryItemLayoutBinding.root) {

        /**
         * Binding up the data to recyclerview
         */

        fun bind(data: ProductEntity) {
            categoryItemLayoutBinding.apply {
                categoryImage.load(
                    data.image
                )
                categorySpecialPrice.text = "₹ ${
                    String.format(
                        "%.2f", data.specialPrice?.toDouble()
                    )
                }"
                categoryPrice.text = "₹ ${
                    String.format(
                        "%.2f", data.price?.toDouble()
                    )
                }"
                categoryPrice.paintFlags = Paint.STRIKE_THRU_TEXT_FLAG
                categoryTitle.text = data.title

                btnDelete.visibility = View.VISIBLE
                btnDelete.setOnClickListener {
                    listner.onDeleteListener(newList[position])
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        binding = CategoryItemLayoutBinding.inflate(layoutInflater, parent, false)
        return CategoryViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        val newList: ProductEntity = newList[position]
        holder.bind(newList)
    }

    override fun getItemCount(): Int {
        return newList.size
    }
}
