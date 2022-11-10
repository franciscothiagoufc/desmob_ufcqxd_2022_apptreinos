package br.com.ufc.qxd.desmob.apptreino

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {
    private lateinit var treinosActivity: InicialActivity;
    private lateinit var treinosActivityIntent: Intent;
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        treinosActivity = InicialActivity();
        treinosActivityIntent = Intent(this,InicialActivity::class.java);
        startActivity(treinosActivityIntent);
    }
}