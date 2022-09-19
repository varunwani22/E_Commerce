package com.example.ecommerce.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize


/**
 * Parcelize to send the data
 */

@Parcelize
data class ProductItem(
    var prodImage: String?,
    var prodTitle: String?,
    var prodPrice: String?,
    var prodSpecialPrice: String?,
    var prodDesc: String?,
    var prodShortDesc: String?
) : Parcelable