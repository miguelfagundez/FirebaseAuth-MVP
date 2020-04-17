package com.devproject.miguelfagundez.authfirebaseapp.presenter

import com.devproject.miguelfagundez.authfirebaseapp.Interface
import com.devproject.miguelfagundez.authfirebaseapp.model.Model

/********************************************
 * Presenter
 * This class interact with view and model
 * @author: Miguel Fagundez
 * @date: April 14th, 2020
 * @version: 1.0
 * *******************************************/
class Presenter(view : Interface.view) : Interface.presenter {

    private val view = view
    private val model : Interface.model

    init {
        model = Model(this)
    }

    override fun isCurrentUserNull(): Boolean {
        return model.getCurrentUserNull()
    }

    override fun isEmailVerified(): Boolean {
        return model.getEmailVerification()
    }

    override fun closeSession() {
        model.closingSession()
    }

    override fun createUser(email: String, password: String)  {
        model.createNewUser(email, password)
    }

    override fun loginAcc(email: String, password: String)  {
        model.loginFirebaseAcc(email, password)
    }

    override fun firebaseResponse(value: Boolean, message : String, isNewUser : Boolean) {
        view.credentialsResponse(value, message, isNewUser)
    }

}