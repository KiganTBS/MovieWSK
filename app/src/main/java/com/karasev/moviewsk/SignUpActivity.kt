package com.karasev.moviewsk

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class SignUpActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        auth = Firebase.auth

        val email = findViewById<EditText>(R.id.editTextEmail)
        val pass = findViewById<EditText>(R.id.editTextPassword)
        val signUpButton = findViewById<Button>(R.id.buttonSignUp)

        signUpButton.setOnClickListener {

            auth.createUserWithEmailAndPassword(email.text.toString(), pass.text.toString())
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        Log.d("MovieDB", "Success")
                        val user = auth.currentUser

                    } else {
                        Toast.makeText(
                            baseContext, "Authentication failed.$pass $email",
                            Toast.LENGTH_SHORT
                        ).show()

                    }
                }
        }


    }
}