package com.aakash.day5

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts

class MainActivity : AppCompatActivity() {

    private var toastCount = 0
    private var snackBarCount = 0
    private lateinit var toastCountField : TextView
    private lateinit var snackBarCountField : TextView


    private val resultLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
            result: ActivityResult ->
        run {
            if (result.resultCode == Activity.RESULT_OK) {
                val intent = result.data
                if (intent != null) {
                    if(intent.hasExtra(ToastActivity.EXTRA_TOAST_COUNT)){
                        toastCount += intent.getIntExtra(ToastActivity.EXTRA_TOAST_COUNT, 0)
                        toastCountField.text = toastCount.toString()
                    }
                }
            }
            else{
                val intent = result.data
                if(intent != null) {
                    if (intent.hasExtra(SnackBarActivity.EXTRA_SNACK_BAR_COUNT)) {
                        snackBarCount += intent.getIntExtra(
                            SnackBarActivity.EXTRA_SNACK_BAR_COUNT,
                            0
                        )
                        snackBarCountField.text = snackBarCount.toString()
                    }
                }
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val toastButton = findViewById<Button>(R.id.main_createToast)
        val snackBarButton = findViewById<Button>(R.id.main_createSnackBar)
        toastCountField = findViewById(R.id.main_toastCount)
        snackBarCountField = findViewById(R.id.main_snackBarCount)

        toastButton.setOnClickListener(this::onToastButtonClicked)
        snackBarButton.setOnClickListener(this::onSnackBarButtonClicked)

    }

    private fun onToastButtonClicked(view: View){
        val intent = Intent(this,ToastActivity::class.java)
        resultLauncher.launch(intent)
    }
    private fun onSnackBarButtonClicked(view: View){
        val intent = Intent(this,SnackBarActivity::class.java)
        resultLauncher.launch(intent)
    }

}