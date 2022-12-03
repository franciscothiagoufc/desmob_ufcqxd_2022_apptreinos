package br.com.ufc.qxd.desmob.apptreino.firebase

import android.app.Activity.RESULT_OK
import android.content.Intent
import android.util.Log
import androidx.activity.result.ActivityResultLauncher
import androidx.appcompat.app.AppCompatActivity
import com.firebase.ui.auth.AuthUI
import com.firebase.ui.auth.FirebaseAuthUIActivityResultContract
import com.firebase.ui.auth.data.model.FirebaseAuthUIAuthenticationResult
import com.google.firebase.auth.FirebaseAuth


class Authentication(activity: AppCompatActivity) {
    private lateinit var activity:AppCompatActivity;
    private lateinit var signInLauncher : ActivityResultLauncher<Intent>;
    private lateinit var providers : ArrayList<AuthUI.IdpConfig>
    private lateinit var signInIntent : Intent;
    private lateinit var auth: FirebaseAuth;
    public  var loginCallBack: () -> Unit = {};
    public  var logoutCallBack: () -> Unit = {};
    init {
        this.activity = activity
        /*Configurando providers*/
        providers =  ArrayList<AuthUI.IdpConfig>();
        providers.add(AuthUI.IdpConfig.EmailBuilder().build())
        /*Configurando Tela de autenticação*/
        signInLauncher = this.activity.registerForActivityResult(FirebaseAuthUIActivityResultContract()){
                res -> this.onSignInResult(res,{});
        }
        /*Configruando Launcher*/
        signInIntent = AuthUI.getInstance().createSignInIntentBuilder().setAvailableProviders(providers).build();
    }
    public fun checkAuthentication():Boolean{
        return FirebaseAuth.getInstance().currentUser != null;
    }
    public fun login(){
        if (!checkAuthentication()){
            signInLauncher.launch(signInIntent);
        }
        else{
            loginCallBack()
        }
    }
    private fun onSignInResult(result: FirebaseAuthUIAuthenticationResult,u: () -> Unit){
        if (result.resultCode == RESULT_OK) {
            loginCallBack()
        } else {

        }
    }
    public fun logout(){
        AuthUI.getInstance().signOut(activity).addOnCompleteListener{
            logoutCallBack()
        }
    }
}