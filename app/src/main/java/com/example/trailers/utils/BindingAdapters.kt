package com.example.movietrailers.utils

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.example.movietrailers.R


@BindingAdapter("imageUrl")
fun setImageUrl(imageView: ImageView, url: String?) {
    url?.let {
        Glide.with(imageView.context)

            .
        load(it).placeholder(R.drawable.photo)
            .error(R.drawable.photo)
            .transition(DrawableTransitionOptions.withCrossFade())
            .into(imageView)

    }
}