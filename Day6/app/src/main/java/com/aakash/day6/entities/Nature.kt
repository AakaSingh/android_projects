package com.aakash.day6.entities

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class Nature(
    @DrawableRes val image : Int,
    @StringRes val title : Int
){}
