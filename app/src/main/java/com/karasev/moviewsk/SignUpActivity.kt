package com.karasev.moviewsk

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
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
        val tvResetPassword = findViewById<TextView>(R.id.tvResetPass)
        val signUpButton = findViewById<Button>(R.id.buttonSignUp)
        val loginButton = findViewById<Button>(R.id.buttonLogin)

        signInButton(loginButton, email, pass)
        signUpButton(signUpButton, email, pass)
        resetPasswordFunc(tvResetPassword, email)


    }

    private fun resetPasswordFunc(
        tvResetPassword: TextView,
        email: EditText
    ) {
        tvResetPassword.setOnClickListener {
            auth.setLanguageCode("ru")
            auth.sendPasswordResetEmail(email.text.toString().trim())
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        Log.d("RESETPASSWORD", "Email sent.")
                    }else{
                        Log.d("RESETPASSWORD", task.exception.toString())
                    }
                }
        }
    }

    private fun signInButton(
        loginButton: Button,
        email: EditText,
        pass: EditText
    ) {
        loginButton.setOnClickListener {
            auth.signInWithEmailAndPassword(
                email.text.toString().trim(),
                pass.text.toString().trim()
            )
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        // Sign in success, update UI with the signed-in user's information
                        Log.d("SIGNIN", "signInWithEmail:success")
                        val user = auth.currentUser
                    } else {
                        // If sign in fails, display a message to the user.
                        Log.w("SIGNIN", "signInWithEmail:failure", task.exception)
                        Toast.makeText(
                            baseContext, "Authentication failed.",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
        }
    }

    private fun signUpButton(
        signUpButton: Button,
        email: EditText,
        pass: EditText
    ) {
        signUpButton.setOnClickListener {

            auth.createUserWithEmailAndPassword(
                email.text.toString().trim(),
                pass.text.toString().trim()
            )
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        Log.d("MovieDB", "Success")
                        val user = auth.currentUser
                    } else {
                        Toast.makeText(
                            baseContext, "${task.exception} \n$pass $email",
                            Toast.LENGTH_SHORT
                        ).show()

                    }
                }
        }
    }
}