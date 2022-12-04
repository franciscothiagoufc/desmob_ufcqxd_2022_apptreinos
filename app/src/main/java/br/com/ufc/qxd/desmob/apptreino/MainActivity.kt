package br.com.ufc.qxd.desmob.apptreino

import android.app.PendingIntent
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import br.com.ufc.qxd.desmob.apptreino.DAO.internal.HistoricoDAO
import br.com.ufc.qxd.desmob.apptreino.DAO.firebase.ScriptDAO
import br.com.ufc.qxd.desmob.apptreino.firebase.Authentication
import br.com.ufc.qxd.desmob.apptreino.utils.Utils

class MainActivity : AppCompatActivity() {
    private lateinit var loginEmailPass:Button;
    private lateinit var treinosActivity: InicialActivity;
    private lateinit var treinosActivityIntent: Intent;
    private lateinit var historicoDAO: HistoricoDAO;
    private lateinit var scriptDAO: ScriptDAO;
    private lateinit var Args:Bundle;
    private lateinit var authentication: Authentication;
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        /*Iniciando Views*/
        loginEmailPass = findViewById(R.id.main_btn_login_emailpass);
        /*Iniciando DAO*/
        scriptDAO = ScriptDAO();
        historicoDAO = HistoricoDAO();
        /*Iniciando Autenticação*/
        authentication = Authentication(this)
        authentication.loginCallBack = {
            startActivity(treinosActivityIntent);
        }
        /*Adicionando dados fictícios*/
        //Utils.dummyScripts(scriptDAO,authentication);
        //Utils.dummyHistorico(historicoDAO);
        /*Iniciando activitys*/
        treinosActivityIntent = Intent(this,InicialActivity::class.java);
        treinosActivity = InicialActivity();
        if(authentication.checkAuthentication()){
            //startActivity(treinosActivityIntent);
        }
        /*Adicionando Interações*/
        loginEmailPass.setOnClickListener(){
            authentication.login()
        }
    }

}