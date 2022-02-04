package com.aakash.day3

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts

class MainActivity : AppCompatActivity() {

    companion object{
        public const val EXTRA_HELLO_KEY = "hello"
    }

    private lateinit var stringValue : String

    private val resultLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){
        result:ActivityResult ->
        run{
            val intent = result.data
            if(intent!!.hasExtra(RecieverActivity.EXTRA_NAME_KEY)){
                Log.e("recieved data", intent.getStringExtra(RecieverActivity.EXTRA_NAME_KEY).toString())
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val hello = findViewById<TextView>(R.id.main_helloWorld)
        val sendMore = findViewById<Button>(R.id.main_send)
        sendMore.setOnClickListener(this::sendMore)

        hello.setText((R.string.app_name))

        stringValue = resources.getString(R.string.app_name)
    }

    private fun sendMore(view: View){
        val intent = Intent(this, RecieverActivity::class.java).apply {
            putExtra(EXTRA_HELLO_KEY,stringValue)
        }
        resultLauncher.launch(intent)
    }


}