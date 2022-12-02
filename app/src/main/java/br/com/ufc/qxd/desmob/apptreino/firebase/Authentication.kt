package br.com.ufc.qxd.desmob.apptreino.firebase

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthException
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class Authentication {
    public fun checkAuthentication():Boolean{
        return Firebase.auth != null;
    }
    public fun login(){
        if (!checkAuthentication()){

        }
    }
}