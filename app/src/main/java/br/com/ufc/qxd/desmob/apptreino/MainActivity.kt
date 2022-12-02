package br.com.ufc.qxd.desmob.apptreino

import android.app.PendingIntent
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import br.com.ufc.qxd.desmob.apptreino.DAO.internal.HistoricoDAO
import br.com.ufc.qxd.desmob.apptreino.DAO.internal.ScriptDAO
import br.com.ufc.qxd.desmob.apptreino.firebase.Authentication
import br.com.ufc.qxd.desmob.apptreino.utils.Utils

class MainActivity : AppCompatActivity() {
    private lateinit var treinosActivity: InicialActivity;
    private lateinit var treinosActivityIntent: Intent;
    private lateinit var historicoDAO: HistoricoDAO;
    private lateinit var scriptDAO: ScriptDAO;
    private lateinit var Args:Bundle;
    private var authentication = Authentication(this);
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        scriptDAO = ScriptDAO();
        historicoDAO = HistoricoDAO();
        /*Adicionando dados fictícios*/
        Utils.dummyScripts(scriptDAO);
        Utils.dummyHistorico(historicoDAO);
        /*--------------------------*/
        treinosActivityIntent = Intent(this,InicialActivity::class.java);
        /*Args = Bundle();
        Args.putSerializable("historicoDAO",historicoDAO);
        Args.putSerializable("scriptDAO",scriptDAO);
        treinosActivityIntent.putExtra("Args",Args);*/
        treinosActivity = InicialActivity();
        authentication.login();
        //startActivity(treinosActivityIntent);
    }
}