package com.example.chatapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.example.chatapp.databinding.ActivitySignupBinding
import com.google.firebase.auth.FirebaseAuth

class SignupActivity : AppCompatActivity() {
    private lateinit var email: String
    private lateinit var password: String
    private lateinit var mAuthSignUp:FirebaseAuth
    private lateinit var bindingSignup:ActivitySignupBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindingSignup=DataBindingUtil.setContentView(this,R.layout.activity_signup)
        mAuthSignUp=FirebaseAuth.getInstance()
        bindingSignup.tvSignupPage.setOnClickListener {
            val intent = Intent(this@SignupActivity,LoginActivity::class.java)
            startActivity(intent)
        }
        bindingSignup.btnSignup.setOnClickListener{
             email=bindingSignup.etUsername.text.toString()
             password=bindingSignup.etPassword.text.toString()
            if (!email.isNullOrEmpty()&&!password.isNullOrEmpty()) {
                signUp(email, password)
            }
        }
    }

    private fun signUp(email: String, password: String) {
        mAuthSignUp.createUserWithEmailAndPassword(email,password).addOnCompleteListener(this){
            if (it.isSuccessful)
            {
                val intent= Intent(this@SignupActivity,MainActivity::class.java)
                startActivity(intent)

            }
            else{
                Toast.makeText(this@SignupActivity,"Some error occurred",Toast.LENGTH_SHORT).show()
            }
        }
    }
}