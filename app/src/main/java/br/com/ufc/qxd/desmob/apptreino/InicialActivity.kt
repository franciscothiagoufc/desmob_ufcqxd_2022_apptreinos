package br.com.ufc.qxd.desmob.apptreino

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ImageButton
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import br.com.ufc.qxd.desmob.apptreino.DAO.IntentCodes
import br.com.ufc.qxd.desmob.apptreino.DAO.internal.HistoricoDAO
import br.com.ufc.qxd.desmob.apptreino.DAO.internal.ScriptDAO
import br.com.ufc.qxd.desmob.apptreino.treino.Historico
import br.com.ufc.qxd.desmob.apptreino.treino.Script
import br.com.ufc.qxd.desmob.apptreino.utils.Utils

class InicialActivity : AppCompatActivity() {
    private lateinit var edtScripts: ImageButton;
    private lateinit var scriptRecycle: RecyclerView;
    private lateinit var layoutManger: LinearLayoutManager;
    private lateinit var scriptAdapter: InicialScriptsAdapter;

    private lateinit var scriptDAO: ScriptDAO;
    private lateinit var historicoDAO: HistoricoDAO;

    init {

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_inicial)
        /*Iniciando Views*/
        scriptRecycle = findViewById(R.id.inicial_script_recycle);
        edtScripts = findViewById(R.id.inicial_script_editscript);
        /*Iniciando Dados*/
        scriptDAO = intent.getBundleExtra("Args")?.getSerializable("scriptDAO") as ScriptDAO ;
        historicoDAO = intent.getBundleExtra("Args")?.getSerializable("historicoDAO") as HistoricoDAO ;
        /*Configurando Recycle View*/
        scriptAdapter = InicialScriptsAdapter(scriptDAO.getScriptArray());
        layoutManger = LinearLayoutManager(this);
        scriptRecycle.layoutManager = layoutManger;
        scriptRecycle.adapter = scriptAdapter;
        /*Configurando Botões*/
        edtScripts.setOnClickListener{
            val treinosActivity = InicialActivity();
            val treinosActivityIntent = Intent(this,TreinosActivity::class.java);
            val treinosActivityIntentBundle = Bundle();
            treinosActivityIntentBundle.putSerializable("scriptDAO",scriptDAO);
            treinosActivityIntentBundle.putSerializable("historicoDAO",historicoDAO);
            treinosActivityIntent.putExtra("Args",treinosActivityIntentBundle);
            startActivity(treinosActivityIntent);
        }
        scriptAdapter.notifyDataSetChanged();
    }
}