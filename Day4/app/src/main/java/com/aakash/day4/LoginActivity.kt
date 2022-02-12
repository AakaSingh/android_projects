package com.aakash.day4

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import java.sql.BatchUpdateException

class LoginActivity : AppCompatActivity() {

    companion object{
        const val EXTRA_FIRST_NAME = "firstName"
        const val EXTRA_LAST_NAME = "lastName"
    }

    private var username : String = ""
    private var password : String = ""
    private var firstName : String = ""
    private var lastName : String = ""

    private lateinit var usernameField : EditText
    private lateinit var passwordField : EditText

    private val resultLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
            result: ActivityResult ->
        run {
            if (result.resultCode == Activity.RESULT_OK) {
                val intent = result.data
                if (intent != null) {
                    if (intent.hasExtra(RegisterActivity.EXTRA_USERNAME)) {
                        username = intent.getStringExtra(RegisterActivity.EXTRA_USERNAME).toString()
                        password = intent.getStringExtra(RegisterActivity.EXTRA_PASSWORD).toString()
                        firstName = intent.getStringExtra(RegisterActivity.EXTRA_FIRSTNAME_REGISTERED).toString()
                        lastName = intent.getStringExtra(RegisterActivity.EXTRA_LASTNAME_REGISTERED).toString()
                    }
                }
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        usernameField = findViewById(R.id.login_username)
        passwordField = findViewById(R.id.login_password)
        val loginButton = findViewById<Button>(R.id.login_login)
        val registerButton = findViewById<Button>(R.id.login_register)

        loginButton.setOnClickListener(this::onLoginClicked)
        registerButton.setOnClickListener(this::onRegisterClicked)
    }

    private fun onLoginClicked(view: View){
        if(usernameField.text.toString() == "" || passwordField.text.toString() == ""){
            Toast.makeText(this,"Fill Both Fields to Login",Toast.LENGTH_SHORT).show()
        }
        else if (usernameField.text.toString() == username){
            if(passwordField.text.toString() == password){
                val intent = Intent(this,LoggedInActivity::class.java).apply {
                    putExtra(EXTRA_FIRST_NAME,firstName)
                    putExtra(EXTRA_LAST_NAME,lastName)
                }
                startActivity(intent)
            }else{
                Toast.makeText(this,"Password does not Match, Try Again",Toast.LENGTH_SHORT).show()
            }
        }
        else{
            Toast.makeText(this,"Username does not match, try Registering",Toast.LENGTH_SHORT).show()
        }
    }
    private fun onRegisterClicked(view: View){
        val intent = Intent(this,RegisterActivity::class.java)
        resultLauncher.launch(intent)
    }
}