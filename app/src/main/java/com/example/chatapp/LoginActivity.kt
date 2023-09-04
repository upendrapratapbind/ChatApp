package com.example.chatapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.example.chatapp.databinding.ActivityLoginBinding
import com.google.firebase.auth.FirebaseAuth

class LoginActivity : AppCompatActivity() {

    private lateinit var mAuthSignIn:FirebaseAuth
    private lateinit var bindingSignIn: ActivityLoginBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindingSignIn=DataBindingUtil.setContentView(this,R.layout.activity_login)
        mAuthSignIn=FirebaseAuth.getInstance()
        bindingSignIn.tvSignupPage.setOnClickListener{
            val intent=Intent(this,SignupActivity::class.java)
            startActivity(intent)

        }
        bindingSignIn.btnLogin.setOnClickListener{
            val email=bindingSignIn.etUsername.text.toString()
            val password=bindingSignIn.etPassword.text.toString()
            login(email,password)
        }
    }

    private fun login(email: String, password: String) {
     mAuthSignIn.signInWithEmailAndPassword(email,password).addOnCompleteListener(this){
         if (it.isSuccessful){
           val intent=Intent(this@LoginActivity,MainActivity::class.java)
             startActivity(intent)
         }
         else{
             Toast.makeText(this@LoginActivity,"User does not exist", Toast.LENGTH_SHORT).show()

         }
     }
    }
}