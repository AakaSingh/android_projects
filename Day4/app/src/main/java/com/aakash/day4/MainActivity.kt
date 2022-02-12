package com.aakash.day4

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.*
import android.widget.Button
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val toastButton = findViewById<Button>(R.id.main_toast)
        val explicitIntentButton = findViewById<Button>(R.id.main_explicitIntent)

        toastButton.setOnClickListener{
            val myToast = Toast.makeText(this,"You Just Clicked The Button",Toast.LENGTH_SHORT)
            myToast.show()
        }

        explicitIntentButton.setOnClickListener{
            val sendIntent = Intent().apply {
                action = Intent.ACTION_SEND
                putExtra(Intent.EXTRA_TEXT,"Hello Everyone")
                type = "text/plain"
            }
            startActivity(sendIntent)
        }

        registerForContextMenu(toastButton)
    }

    override fun onCreateContextMenu(menu: ContextMenu?, v: View?, menuInfo: ContextMenu.ContextMenuInfo?) {
        super.onCreateContextMenu(menu, v, menuInfo)
        menuInflater.inflate(R.menu.nested_menu, menu)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when(item.itemId){
            R.id.menu_item_1 -> {
                Toast.makeText(this,item.title,Toast.LENGTH_SHORT).show()
                true
            }
            R.id.menu_item_2 -> {
                Toast.makeText(this,item.title,Toast.LENGTH_SHORT).show()
                true
            }
            R.id.menu_item_3 -> {
                Toast.makeText(this,item.title,Toast.LENGTH_SHORT).show()
                true
            }
            else -> {
                Toast.makeText(this,"Not Found",Toast.LENGTH_SHORT).show()
                true
            }
        }
    }
}