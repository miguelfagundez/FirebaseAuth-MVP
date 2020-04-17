package com.devproject.miguelfagundez.authfirebaseapp.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.devproject.miguelfagundez.authfirebaseapp.Interface
import com.devproject.miguelfagundez.authfirebaseapp.R
import com.devproject.miguelfagundez.authfirebaseapp.presenter.Presenter
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth

/********************************************
 * Activity - InitialActivity
 * This activity interacts with Presenter layer
 * @author: Miguel Fagundez
 * @date: April 14th, 2020
 * @version: 1.0
 * *******************************************/
class InitialActivity : AppCompatActivity(), Interface.view{

    private val loginFragment: LoginFragment =
        LoginFragment()

    private val presenter = Presenter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_initial)

        //************************************************************
        // Connecting with Presenter to check current user data
        //************************************************************
        if(presenter.isCurrentUserNull()) {
            if (presenter.isEmailVerified()) {
                openHomeActivity()
            } else {
                showMessage(getString(R.string.check_email_verification))
            }
        }
        openLoginFragment()
    }

    private fun openLoginFragment() {
        supportFragmentManager
            .beginTransaction()
            .setCustomAnimations(
                R.anim.slide_in,
                R.anim.slide_out,
                R.anim.slide_in,
                R.anim.slide_out
            )
            .add(R.id.initial_frame_layout, loginFragment)
            .commit()
    }

    //***************************************************
    // Open second activity (checking project)
    //***************************************************
    private fun openHomeActivity() {
        startActivity(Intent(this@InitialActivity, MainActivity::class.java))
        finish()
    }

    //************************************************************
    // Connecting with Presenter to register or checking user data
    //************************************************************
    fun registerNewUser(email:String, password:String) {
        showMessage(getString(R.string.register_user_data))
        presenter.createUser(email, password)
    }

    fun loginUser(email:String, password: String){
        showMessage(getString(R.string.check_user_data))
        presenter.loginAcc(email, password)
    }

    override fun credentialsResponse(value: Boolean, message: String, isNewUser : Boolean) {
        showMessage(message)
        if (value == true && isNewUser == false) {
            openHomeActivity()
        }
    }

    override fun showMessage(message: String) {
        Toast.makeText(this@InitialActivity, message, Toast.LENGTH_SHORT).show()
    }
}
