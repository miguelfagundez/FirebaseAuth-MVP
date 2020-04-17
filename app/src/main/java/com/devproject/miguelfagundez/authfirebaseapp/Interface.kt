package com.devproject.miguelfagundez.authfirebaseapp


/********************************************
 * Interface - MVP
 * This is the interface that needs to be
 * implemented in order to have MVP architecture
 * @author: Miguel Fagundez
 * @date: April 14th, 2020
 * @version: 1.0
 * *******************************************/
interface Interface {

    interface view{
        fun showMessage(message:String)
        fun credentialsResponse(value : Boolean, message : String, isNewUser : Boolean)
    }
    interface presenter{
        fun isCurrentUserNull():Boolean
        fun isEmailVerified():Boolean
        fun closeSession()
        fun createUser(email: String, password: String)
        fun loginAcc(email: String, password: String)
        fun firebaseResponse(value: Boolean, message : String,isNewUser: Boolean)
    }
    interface model{
        fun getCurrentUserNull():Boolean
        fun getEmailVerification():Boolean
        fun closingSession()
        fun createNewUser(email: String, password: String)
        fun loginFirebaseAcc(email: String, password: String)
    }
}