package com.example.girlyeshop

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.WindowManager
import android.view.animation.AnimationUtils
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_register.*

class RegisterActivity : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth
    private lateinit var preferencesProvider: UserClass

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )

        preferencesProvider = UserClass(applicationContext)

        //Using Firebase to create a user with mail and password
        auth = FirebaseAuth.getInstance()

        btn_register.setOnClickListener {
            createNewUserUsingFireBase()
            //createNewUserUsingLocalMemory()

        }

        btn_load.setOnClickListener {
//            et_name.setText(preferencesProvider.getString(Constants.KEY_FIRST_NAME))
//            et_lastName.setText(preferencesProvider.getString(Constants.KEY_LAST_NAME))
//            et_userName.setText(preferencesProvider.getString(Constants.KEY_USER_NAME))
//            et_email.setText(preferencesProvider.getString(Constants.KEY_EMAIL))
        }

        tv_login_user.setOnClickListener {
            val myIntent = Intent(this,MainActivity::class.java)
            startActivity(myIntent)
            finish()
        }
    }

    private fun createNewUserUsingFireBase(){

        var password = et_password.text.toString()
        var email = et_email.text.toString()

        //Checking if the email or the password is null
        if (et_email.text.isEmpty() || et_password.text.isEmpty()) return

        //Create user with email and password
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    Log.d("register", "createUserWithEmail:success")
                    val user = auth.currentUser
                    Toast.makeText(this,"User has successfully registered!",Toast.LENGTH_SHORT).show()
                } else {
                    // If sign in fails, display a message to the user.
                    Log.w("register", "createUserWithEmail:failure", task.exception)
                    Toast.makeText(
                        baseContext, "Authentication failed.",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
            .addOnFailureListener {
                Log.d("Register","Fail to create a user!")
                Toast.makeText(
                    this, "Failed to create new user!",
                    Toast.LENGTH_SHORT
                ).show()
            }
    }

    private fun createNewUserUsingLocalMemory(){
        preferencesProvider.putString(Constants.KEY_FIRST_NAME, et_name.text.toString().trim())
        preferencesProvider.putString(Constants.KEY_LAST_NAME, et_lastName.text.toString().trim())
        preferencesProvider.putString(Constants.KEY_USER_NAME, et_userName.text.toString().trim())
        preferencesProvider.putString(Constants.KEY_EMAIL, et_email.text.toString().trim())
        preferencesProvider.putString(Constants.KEY_PASSWORD, et_password.text.toString().trim())
        preferencesProvider.putString(Constants.KEY_CONFIRMATION_PASSWORD, et_confirmation_password.text.toString().trim())
        Toast.makeText(applicationContext,"Data saved using Local Memory!",Toast.LENGTH_SHORT).show()
    }
}