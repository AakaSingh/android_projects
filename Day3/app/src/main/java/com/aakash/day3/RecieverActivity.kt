package com.aakash.day3

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import androidx.core.widget.addTextChangedListener

class RecieverActivity : AppCompatActivity() {

    companion object{
            const val EXTRA_NAME_KEY = "receiverInfo"
    }
    val intentToPassBackwards = Intent()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reciever)

        val textModifier = findViewById<EditText>(R.id.receiver_text)

        val myIntent = intent
        if(myIntent.hasExtra(MainActivity.EXTRA_HELLO_KEY)){
            textModifier.setText(myIntent.getStringExtra(MainActivity.EXTRA_HELLO_KEY))
        }

        textModifier.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                TODO("Not yet implemented")
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                TODO("Not yet implemented")
            }

            override fun afterTextChanged(p0: Editable?) {
                intentToPassBackwards.putExtra(EXTRA_NAME_KEY, p0.toString())
                setResult(RESULT_OK, intentToPassBackwards)
            }
        })
    }
}