package com.example.ecommerce.view.category

import android.graphics.Paint
import android.text.Layout
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.ecommerce.databinding.CategoryItemLayoutBinding
import com.example.ecommerce.model.ResponseModelItem

class CategoryAdapter(var onItemClick: OnItemClick) :
    RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder>() {
    private lateinit var binding: CategoryItemLayoutBinding

    inner class CategoryViewHolder(
        private val categoryItemLayoutBinding: CategoryItemLayoutBinding,
        var onItemClick: OnItemClick
    ) :
        RecyclerView.ViewHolder(categoryItemLayoutBinding.root) {
        /**
         * Binding data to recycler view
         */

        fun bind(data: ResponseModelItem) {
            categoryItemLayoutBinding.apply {
                categoryImage.load(
                    "https://www.semessta.com/media/catalog/product" + data.smallImage
                )
                categorySpecialPrice.text = "₹ ${
                    String.format(
                        "%.2f", data.specialPrice?.toDouble()
                    )
                }"
                categoryPrice.text = "₹ ${
                    String.format(
                        "%.2f", data.pprice?.toDouble()
                    )
                }"
                categoryPrice.paintFlags = Paint.STRIKE_THRU_TEXT_FLAG
                categoryTitle.text = data.pname

                categoryCard.setOnClickListener {
                    onItemClick.onClickOfItem(data, adapterPosition)
                }
            }
        }
    }

    /**
     * DiffUtils implementation
     */

    private val differCallBack = object : DiffUtil.ItemCallback<ResponseModelItem>() {
        override fun areItemsTheSame(
            oldItem: ResponseModelItem,
            newItem: ResponseModelItem
        ): Boolean {
            return oldItem.pid == newItem.pid
        }

        override fun areContentsTheSame(
            oldItem: ResponseModelItem,
            newItem: ResponseModelItem
        ): Boolean {
            return oldItem == newItem
        }

    }
    val differ = AsyncListDiffer(this, differCallBack)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        binding = CategoryItemLayoutBinding.inflate(layoutInflater, parent, false)
        return CategoryViewHolder(binding, onItemClick)
    }

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        val data = differ.currentList[position]
        holder.bind(data)
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

}