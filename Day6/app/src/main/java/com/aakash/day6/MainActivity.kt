package com.aakash.day6

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.recyclerview.widget.RecyclerView
import com.aakash.day6.entities.Nature
import com.aakash.day6.recyclerview.NatureAdapter
import java.lang.annotation.Native

class MainActivity : AppCompatActivity() {

    val launcher = registerForActivityResult(ActivityResultContracts.StartActivityForResult(),this::onActivityResult)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val data  = listOf<Nature>(
            Nature(R.drawable.nature3,R.string.nature3),
            Nature(R.drawable.nature1,R.string.nature1),
            Nature(R.drawable.nature2,R.string.nature2)
        )

        val createButton = findViewById<Button>(R.id.main_create)
        createButton.setOnClickListener{
            val intent = Intent(this, CreateActivity::class.java)

        }

        val recyclerView = findViewById<RecyclerView>(R.id.main_recyclerView)
        recyclerView.adapter = NatureAdapter(data)
    }

    private fun onActivityResult(result: ActivityResult){
        if(result.resultCode == RESULT_OK){
            val intent = result.data
            if(intent?.hasExtra(CreateActivity.EXTRA_TEXT_KEY) == true){
                val title = intent.getStringExtra(CreateActivity.EXTRA_TEXT_KEY)
                val image = intent.getIntExtra(CreateActivity.EXTRA_IMAGE_KEY, 0)
            }
        }
    }
}