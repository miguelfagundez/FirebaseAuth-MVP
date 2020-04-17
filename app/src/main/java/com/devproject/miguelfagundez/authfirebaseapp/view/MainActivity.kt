package com.devproject.miguelfagundez.authfirebaseapp.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.devproject.miguelfagundez.authfirebaseapp.Interface
import com.devproject.miguelfagundez.authfirebaseapp.R
import com.devproject.miguelfagundez.authfirebaseapp.presenter.Presenter
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_main.*

/********************************************
 * Activity - MainActivity
 * This activity is used to test if user data
 *   is validated by Firebase project
 * @author: Miguel Fagundez
 * @date: April 14th, 2020
 * @version: 1.0
 * *******************************************/
class MainActivity : AppCompatActivity(), Interface.view{

    private val presenter = Presenter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //************************************************************
        // Connecting with Firebase to logout user data
        //************************************************************
        btnLogout.setOnClickListener {
            presenter.closeSession()
            showMessage(getString(R.string.logout_message))
            openInitialActivity()
        }
    }

    //***************************************************
    // Open initial activity (logins/register available)
    //***************************************************
    private fun openInitialActivity() {
        startActivity(Intent(this@MainActivity, InitialActivity::class.java))
        finish()
    }

    override fun showMessage(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    override fun credentialsResponse(value: Boolean, message: String, isNewUser : Boolean) {

        //   This method is empty because this Activity do not
        // interact with presenter nor model (firebase connection)
    }


}
