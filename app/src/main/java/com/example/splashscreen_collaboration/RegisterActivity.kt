package com.example.splashscreen_collaboration

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class RegisterActivity : AppCompatActivity() {
    lateinit var etEmail:  EditText
    lateinit var etPassword: EditText
    private lateinit var regBtn: Button

    // create Firebase authentication object
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        //bindings
        etEmail = findViewById(R.id.editTextText)
        etPassword = findViewById(R.id.editTextTextPassword)
        regBtn = findViewById(R.id.RegisterBtn)

        // Initialising auth object
        auth = Firebase.auth


        regBtn.setOnClickListener{
            registerUser()
        }

    }
    private fun registerUser()
    {
        val email = etEmail.text.toString()
        val pass = etPassword.text.toString()

        // check pass
        if (email.isBlank() || pass.isBlank()) {
            Toast.makeText(this, "Email and Password can't be blank", Toast.LENGTH_SHORT).show()
            return
        }
        // If all credential are correct
        // We call createUserWithEmailAndPassword
        // using auth object and pass the
        // email and pass in it.
        auth.createUserWithEmailAndPassword(email, pass).addOnCompleteListener(this) {
            if (it.isSuccessful) {
                Toast.makeText(this, "Successfully Singed Up", Toast.LENGTH_SHORT).show()
                finish()
            } else {
                Toast.makeText(this, "Singed Up Failed!", Toast.LENGTH_SHORT).show()
            }
        }
        regBtn.setOnClickListener{
         val intent = Intent(this, LoginActivity::class.java)
        startActivity(intent)

        }

    }
}