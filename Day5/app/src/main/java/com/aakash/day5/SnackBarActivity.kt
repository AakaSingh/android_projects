package com.aakash.day5

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.android.material.snackbar.Snackbar

class SnackBarActivity : AppCompatActivity() {

    companion object{
        const val EXTRA_SNACK_BAR_COUNT = "toastCount"
    }

    private var longOrShort = "Short"
    private var snackBarCount = 0
    private lateinit var snackBarText : EditText
    private var snackBarTextValue = "this message"
    private val intentToPassBackwards : Intent = Intent()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_snack_bar)

        val longButton = findViewById<Button>(R.id.snack_bar_long)
        val shortButton = findViewById<Button>(R.id.snack_bar_short)
        val makeText = findViewById<Button>(R.id.snack_bar_makeText)
        snackBarText = findViewById(R.id.snack_bar_text)

        longButton.setOnClickListener(this::onLongOrShortClicked)
        shortButton.setOnClickListener(this::onLongOrShortClicked)
        makeText.setOnClickListener(this::onMakeTextClicked)
    }

    private fun onLongOrShortClicked(view : View){
        val e = view as Button
        longOrShort = e.text.toString()
    }

    private fun onMakeTextClicked(view: View){
        snackBarCount++
        if(snackBarText.text.toString() != "")
            snackBarTextValue = snackBarText.text.toString()


        if(longOrShort == "Long"){
            val mySnackBar = Snackbar.make(findViewById(R.id.snackBarParent),"SnackBar", Snackbar.LENGTH_LONG)
            mySnackBar.setAction("Undo") {
                snackBarCount--
                intentToPassBackwards.putExtra(EXTRA_SNACK_BAR_COUNT,snackBarCount)
                setResult(RESULT_CANCELED,intentToPassBackwards)
            }
            mySnackBar.show()
        }
        else {
            val mySnackBar = Snackbar.make(findViewById(R.id.snackBarParent),"SnackBar", Snackbar.LENGTH_SHORT)
            mySnackBar.setAction("Undo") {
                snackBarCount--
                intentToPassBackwards.putExtra(EXTRA_SNACK_BAR_COUNT,snackBarCount)
                setResult(RESULT_CANCELED,intentToPassBackwards)
            }
            mySnackBar.show()
        }
        intentToPassBackwards.putExtra(EXTRA_SNACK_BAR_COUNT,snackBarCount)
        setResult(RESULT_CANCELED,intentToPassBackwards)
    }
}