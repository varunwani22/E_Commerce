package com.example.ecommerce.model


import com.google.gson.annotations.SerializedName

data class ResponseModelItem(
    @SerializedName("base_image")
    val baseImage: String?,
    @SerializedName("description")
    val description: String?,
    @SerializedName("filter_productcategory")
    val filterProductcategory: String?,
    @SerializedName("new_from_date")
    val newFromDate: String?,
    @SerializedName("new_to_date")
    val newToDate: String?,
    @SerializedName("pcat_id")
    val pcatId: Boolean?,
    @SerializedName("pid")
    val pid: String?,
    @SerializedName("pname")
    val pname: String?,
    @SerializedName("pprice")
    val pprice: String?,
    @SerializedName("product_url_path")
    val productUrlPath: String?,
    @SerializedName("short_description")
    val shortDescription: String?,
    @SerializedName("sku")
    val sku: String?,
    @SerializedName("small_image")
    val smallImage: String?,
    @SerializedName("special_from_date")
    val specialFromDate: String?,
    @SerializedName("special_price")
    val specialPrice: String?,
    @SerializedName("special_to_date")
    val specialToDate: Any?,
    @SerializedName("thumbnail_image")
    val thumbnailImage: String?,
    @SerializedName("url_path")
    val urlPath: String?
)