package com.aakash.day5

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class ToastActivity : AppCompatActivity() {

    companion object{
        const val EXTRA_TOAST_COUNT = "toastCount"
    }

    private var longOrShort = "Short"
    private var toastCount = 0
    private lateinit var toastText : EditText
    private var toastTextValue = "this message"
    private val intentToPassBackwards : Intent = Intent()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_toast)

        val longButton = findViewById<Button>(R.id.toast_long)
        val shortButton = findViewById<Button>(R.id.toast_short)
        val makeText = findViewById<Button>(R.id.toast_makeText)
        toastText = findViewById(R.id.toast_toastText)

        longButton.setOnClickListener(this::onLongOrShortClicked)
        shortButton.setOnClickListener(this::onLongOrShortClicked)
        makeText.setOnClickListener(this::onMakeTextClicked)
    }

    private fun onLongOrShortClicked(view : View){
        val e = view as Button
        longOrShort = e.text.toString()
    }

    private fun onMakeTextClicked(view: View){
        toastCount++
        if(toastText.text.toString() != "")
            toastTextValue = toastText.text.toString()


        if(longOrShort == "Long")
            Toast.makeText(this,toastTextValue,Toast.LENGTH_LONG).show()
        else
            Toast.makeText(this,toastTextValue,Toast.LENGTH_SHORT).show()

        intentToPassBackwards.putExtra(EXTRA_TOAST_COUNT,toastCount)
        setResult(RESULT_OK,intentToPassBackwards)
    }
}