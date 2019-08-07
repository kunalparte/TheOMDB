package com.example.kunalparte.sundaymobtask.utils

import android.content.Context
import android.graphics.Bitmap
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestBuilder
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.BaseRequestOptions
import com.example.kunalparte.theomdb.R

class GlideLoader {

    companion object {
        fun url(context: Context) : RequestBuilder<Bitmap> {
             return Glide.with(context)
                 .asBitmap()
                 .fitCenter()
                 .placeholder(R.drawable.ic_image_black_24dp)
                 .diskCacheStrategy(DiskCacheStrategy.ALL)
        }
    }
}