package com.aakash.chatbot

import android.content.DialogInterface
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.aakash.chatbot.entities.AIResponse
import com.aakash.chatbot.entities.Chat
import com.aakash.chatbot.network.BrainShoApi
import com.aakash.chatbot.recyclerview.ChatAdapter
import com.google.android.material.floatingactionbutton.FloatingActionButton
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    companion object{
        const val BOT_KEY = "bot"
        const val USER_KEY = "user"
    }

    lateinit var chatAdapter: ChatAdapter
    private lateinit var recyclerView: RecyclerView
    private lateinit var messageContent: EditText
    private var chatsList = mutableListOf<Chat>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.main_recycler)
        messageContent = findViewById(R.id.main_userMessage)
        val senderButton = findViewById<FloatingActionButton>(R.id.main_sendMessage)

        chatsList.add(Chat("Hey! What's on your mind?", BOT_KEY))
        chatAdapter = ChatAdapter(chatsList)
        recyclerView.adapter = chatAdapter
        senderButton.setOnClickListener(this::onSenderButtonClicked)

        recyclerView.addOnLayoutChangeListener { view, left, top, right, bottom, oldLeft, oldTop, oldRight, oldBottom ->
            if(bottom < oldBottom){
                recyclerView.postDelayed(Runnable{
                    recyclerView.smoothScrollToPosition(chatAdapter.itemCount)
                },0)
            }
        }

    }

    private fun onSenderButtonClicked(view: View){
        if(messageContent.text.toString() == "")
            Toast.makeText(this,"Please enter a message",Toast.LENGTH_SHORT).show()
        else
            getResponse()

        messageContent.setText("")
    }

    private fun checkOptionsAdded(userRequest : String) : Boolean{
        if(userRequest.indexOf("open camera")>-1 && userRequest.indexOf("don't")==-1 && userRequest.indexOf("not")==-1){
            Handler(Looper.getMainLooper()).postDelayed(Runnable {
                chatAdapter.addData(Chat("Opening Camera...", BOT_KEY))
                recyclerView.smoothScrollToPosition(chatAdapter.itemCount)
            }, 500)
            Handler(Looper.getMainLooper()).postDelayed(Runnable {
                dispatchTakePictureIntent()
            }, 1000)
            return false
        }

        if(userRequest.indexOf("email") > -1){
            Handler(Looper.getMainLooper()).postDelayed(Runnable {
                openEmailDialog()
            }, 1000)
            return false
        }
        return true
    }

    private fun getResponse(){
        chatAdapter.addData(Chat(messageContent.text.toString(), USER_KEY))
        recyclerView.smoothScrollToPosition(chatAdapter.itemCount)


        if(checkOptionsAdded(messageContent.text.toString())) {
            val url =
                "http://api.brainshop.ai/get?bid=163746&key=qvm2SAD7av4gVkMx&uid=[uid]&msg=" + messageContent.text.toString()
            val getResponse = BrainShoApi.retrofitService.getAiResponse(url)
            getResponse.enqueue(object : Callback<AIResponse> {
                override fun onResponse(call: Call<AIResponse>, response: Response<AIResponse>) {
                    val dataReceived = response.body() as AIResponse

                    Handler(Looper.getMainLooper()).postDelayed(Runnable { // Do something after 5s = 5000ms
                        chatAdapter.addData(Chat(dataReceived.message, BOT_KEY))
                        recyclerView.smoothScrollToPosition(chatAdapter.itemCount)
                    }, 500)

                }

                override fun onFailure(call: Call<AIResponse>, t: Throwable) {
                    chatAdapter.addData(Chat("I didn't quite understand", BOT_KEY))
                }
            })
        }
    }

    private fun dispatchTakePictureIntent() {
        val intent = Intent("android.media.action.IMAGE_CAPTURE")
        startActivity(
            packageManager.getLaunchIntentForPackage(
                intent.resolveActivity(packageManager).packageName
            )
        )
    }

    private fun openEmailDialog(){
        val builder = AlertDialog.Builder(this)
        val dialogView : View = layoutInflater.inflate(R.layout.activity_send_email_dialog, null)
        builder.setView(dialogView)
        /*builder.setPositiveButton("Send Email") { dialog, _ ->
            dialog.dismiss()
        }
        builder.setNeutralButton("Cancel"){ dialog,_ ->
            dialog.dismiss()
        }*/
        val alertDialog: AlertDialog = builder.create()
        alertDialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        alertDialog.show()
    }


}