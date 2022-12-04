package br.com.ufc.qxd.desmob.apptreino

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import android.widget.Toast
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import br.com.ufc.qxd.desmob.apptreino.DAO.internal.HistoricoDAO
import br.com.ufc.qxd.desmob.apptreino.DAO.firebase.ScriptDAO
import br.com.ufc.qxd.desmob.apptreino.firebase.Authentication
import br.com.ufc.qxd.desmob.apptreino.treino.Script

class InicialActivity : AppCompatActivity() {
    private lateinit var edtScripts: ImageButton;
    private lateinit var scriptRecycle: RecyclerView;
    private lateinit var layoutManger: LinearLayoutManager;
    private lateinit var btnLogout: Button;
    private lateinit var scriptAdapter: InicialScriptsAdapter;
    private lateinit var scriptDAO: ScriptDAO;
    private lateinit var historicoDAO: HistoricoDAO;
    private lateinit var authentication: Authentication;
    private val editActivityResult = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
            result: ActivityResult -> update();
    }
    init {

    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_inicial)
        authentication = Authentication(this);
        /*Iniciando Views*/
        scriptRecycle = findViewById(R.id.inicial_script_recycle);
        edtScripts = findViewById(R.id.inicial_script_editscript);
        btnLogout = findViewById(R.id.inicial_script_btn_logout);
        /*Configurando Recycle View*/
        scriptAdapter = InicialScriptsAdapter(ArrayList<Script>(),this);
        layoutManger = LinearLayoutManager(this);
        scriptRecycle.layoutManager = layoutManger;
        scriptRecycle.adapter = scriptAdapter;
        /*Configurando Botões*/
        edtScripts.setOnClickListener{
            val treinosActivity = InicialActivity();
            val treinosActivityIntent = Intent(this,TreinosActivity::class.java);
            editActivityResult.launch(treinosActivityIntent);
        }
        btnLogout.setOnClickListener{
            authentication.logoutCallBack = {
                finish();
            }
            authentication.logout()
        }
        /*Iniciando Dados*/
        scriptDAO = ScriptDAO();
        historicoDAO = HistoricoDAO();
        /*Carregando dados*/
        update();
    }
    private fun update(){
        /*Carregando dados*/
        scriptDAO.getScriptArray(authentication.getId(),{
                result -> scriptAdapter.Scripts = result;scriptAdapter.notifyDataSetChanged();
        }){
                code -> Toast.makeText(applicationContext, "Falha ao carregar Script", Toast.LENGTH_SHORT)
        }
    }
}