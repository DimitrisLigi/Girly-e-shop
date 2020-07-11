package com.example.girlyeshop

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowManager
import android.view.animation.AnimationUtils
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main.iv_e_shop_logo
import kotlinx.android.synthetic.main.activity_main.iv_girly_logo
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


class MainActivity : AppCompatActivity() {

    private lateinit var myAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //Setting the views and the animations
        setAllTheViews()
        buttonsManager()
    }

    private fun logIn(){

        val email = et_email_log_in.text.toString().trim()
        val pass = et_password_log_in.text.toString().trim()

        //Checking for errors
        if (et_email_log_in.text.isEmpty() || et_password_log_in.text.isEmpty()){
            Toast.makeText(this,"Please give your email and password",Toast.LENGTH_SHORT).show()
            return
        }

        //Getting Firebase instance
        myAuth = FirebaseAuth.getInstance()

        //Signing in
        myAuth.signInWithEmailAndPassword(email,pass)
            .addOnCompleteListener {task ->
                if (task.isSuccessful){
                    Toast.makeText(this,"Successful log in", Toast.LENGTH_SHORT).show()
                }else{
                    Toast.makeText(this,"Successful log in alla kapou gamithike", Toast.LENGTH_SHORT).show()
                }

            }.addOnFailureListener {
                Toast.makeText(this,"Gamithike mitso!!!",Toast.LENGTH_SHORT).show()
            }

    }

    private fun setAllTheViews(){
        setContentView(R.layout.activity_main)
        //Make the view fullScreen
        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )

        setTheFirstAnimations()
        GlobalScope.launch {
            delay(1400)
            setTheSecondAnimations()
        }
    }

    private fun buttonsManager(){
        //Register Button

        tv_register.setOnClickListener {
            val myIntent = Intent(this,RegisterActivity::class.java)
            startActivity(myIntent)
            finish()
        }
        //Login Button
        btn_login.setOnClickListener {
            logIn()
        }
    }

    private fun setTheFirstAnimations(){
        val myAnim = AnimationUtils.loadAnimation(this, R.anim.my_splash_screen_anim)
        iv_e_shop_logo.alpha = 0f
        et_email_log_in.alpha = 0f
        et_password_log_in.alpha = 0f
        btn_login.alpha = 0f
        tv_register.alpha = 0f
        iv_girly_logo.startAnimation(myAnim)
        GlobalScope.launch {
            delay(1800)
            iv_e_shop_logo.animate().apply {
                duration = 500
                alpha(1f)
            }
        }
    }

    private fun setTheSecondAnimations(){

        GlobalScope.launch {
            delay(1000)
            et_email_log_in.animate().apply {
                duration = 500
                alpha(1f)
            }
            et_password_log_in.animate().apply {
                duration = 500
                alpha(1f)
            }
            btn_login.animate().apply {
                duration = 500
                alpha(1f)
            }

            tv_register.animate().apply {
                duration = 500
                alpha(1f)
            }
        }
    }
}

