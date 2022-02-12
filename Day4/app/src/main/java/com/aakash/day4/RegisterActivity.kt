package com.aakash.day4

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.LoginFilter
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class RegisterActivity : AppCompatActivity() {
    companion object{
        const val EXTRA_USERNAME = "username"
        const val EXTRA_PASSWORD = "password"
        const val EXTRA_FIRSTNAME_REGISTERED = "firstNameRegistered"
        const val EXTRA_LASTNAME_REGISTERED = "lastNameRegister"
    }

    private lateinit var userNameField : EditText
    private lateinit var passwordField : EditText
    private lateinit var passwordConfirmField : EditText
    private lateinit var firstName : EditText
    private lateinit var lastName : EditText
    val intentToPassBackwards : Intent = Intent()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
        userNameField = findViewById(R.id.register_username)
        passwordField = findViewById(R.id.register_password)
        passwordConfirmField = findViewById(R.id.register_repeatPassword)
        firstName = findViewById(R.id.register_firstName)
        lastName = findViewById(R.id.register_lastName)
        val submitButton = findViewById<Button>(R.id.register_submit)
        submitButton.setOnClickListener(this::onSubmitButtonClicked)
    }

    private fun onSubmitButtonClicked(view: View){
        if(userNameField.text.toString() == ""
            || passwordField.text.toString() == ""
            || passwordConfirmField.text.toString() == ""
            || firstName.text.toString() == ""
            || lastName.text.toString() == ""){
            Toast.makeText(this,"Please fill all fields",Toast.LENGTH_SHORT).show()
        }
        else{
            if(passwordField.text.toString() == passwordConfirmField.text.toString()){
                intentToPassBackwards.putExtra(EXTRA_FIRSTNAME_REGISTERED,firstName.text.toString())
                intentToPassBackwards.putExtra(EXTRA_LASTNAME_REGISTERED,lastName.text.toString())
                intentToPassBackwards.putExtra(EXTRA_USERNAME,userNameField.text.toString())
                intentToPassBackwards.putExtra(EXTRA_PASSWORD,passwordField.text.toString())
                setResult(RESULT_OK,intentToPassBackwards)
                finish()
            }else{
                Toast.makeText(this,"Password Fields Do Not Match",Toast.LENGTH_SHORT).show()
            }
        }
    }
}