package com.devproject.miguelfagundez.authfirebaseapp.model

import com.devproject.miguelfagundez.authfirebaseapp.Interface
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth

/********************************************
 * Presenter
 * This class interact with FirebaseConnection
 * @author: Miguel Fagundez
 * @date: April 14th, 2020
 * @version: 1.0
 * *******************************************/
class Model(presenter : Interface.presenter) : Interface.model {

    private val presenter:Interface.presenter = presenter

    //************************************************************
    // Checking if user is null (session open)
    //************************************************************
    override fun getCurrentUserNull(): Boolean {
        if (FirebaseAuth.getInstance().currentUser != null)
            return true

        return false
    }

    override fun getEmailVerification(): Boolean {
        if (FirebaseAuth.getInstance().currentUser?.isEmailVerified == true)
            return true

        return false
    }

    override fun closingSession() {
        FirebaseAuth.getInstance().signOut()
    }

    //************************************************************
    // Create a new user in Firebase
    //************************************************************
    override fun createNewUser(email: String, password: String) {
        FirebaseAuth.getInstance()
            .createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                checkingTask(task, true)
            }
    }

    //************************************************************
    // Connecting with Firebase to check user data
    //************************************************************
    override fun loginFirebaseAcc(email: String, password: String) {
        FirebaseAuth.getInstance().signInWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                checkingTask(task, false)
            }
    }

    //*********************************************************
    // Checking Firebase results and connecting with Presenter
    //*********************************************************
    private fun checkingTask(task: Task<AuthResult>, isNewUser : Boolean)  {
        if (task.isSuccessful) {
            if(isNewUser == true){
                presenter.firebaseResponse(false, "User registered Successfully", isNewUser)
            }else {
                if (FirebaseAuth.getInstance().currentUser?.isEmailVerified == true) {
                    // if credentials are accepted and email was verified
                    // open a new activity
                    presenter.firebaseResponse(true, "Login Successfully", isNewUser)
                } else {
                    FirebaseAuth.getInstance().currentUser?.sendEmailVerification()
                    presenter.firebaseResponse(
                        false,
                        "Please check email for verification",
                        isNewUser
                    )
                }
            }
        } else {
            presenter.firebaseResponse(false,"Error: ${task.exception?.message}", isNewUser)
        }
    }
}