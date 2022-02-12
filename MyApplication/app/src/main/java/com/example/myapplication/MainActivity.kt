package com.example.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView

class MainActivity : AppCompatActivity(){

    private lateinit var mainImageView: ImageView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mainImageView = findViewById<ImageView>(R.id.main_mainImage)
        val firstImage = findViewById<ImageView>(R.id.main_image1)
        val secondImage = findViewById<ImageView>(R.id.main_image2)
        val thirdImage = findViewById<ImageView>(R.id.main_image3)

        firstImage.setOnClickListener(this::onImageClick)
        secondImage.setOnClickListener(this::onImageClick)
        thirdImage.setOnClickListener(this::onImageClick)
    }

    private fun onImageClick(view: View?){
        val clickedImage : ImageView = view as ImageView
        mainImageView.setImageDrawable(clickedImage.drawable)
    }
}