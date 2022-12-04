package br.com.ufc.qxd.desmob.apptreino

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.LinearLayout
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import br.com.ufc.qxd.desmob.apptreino.DAO.internal.HistoricoDAO
import br.com.ufc.qxd.desmob.apptreino.DAO.firebase.ScriptDAO
import br.com.ufc.qxd.desmob.apptreino.firebase.Authentication
import br.com.ufc.qxd.desmob.apptreino.treino.Treino

class IniciarTreinoActivity : AppCompatActivity() {
    private lateinit var btnFinalizar : Button;
    private lateinit var exerciciosRecycle : RecyclerView;
    private lateinit var exerciciosAdapter: ExercicioAdapter;
    private lateinit var exercicioLayout: LinearLayoutManager;
    private lateinit var scriptDAO: ScriptDAO;
    private lateinit var historicoDAO: HistoricoDAO;
    private lateinit var authentication: Authentication;
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_iniciar_treino)
        /*Carregando Views*/
        btnFinalizar = findViewById(R.id.iniciar_treino_btn_finalizar);
        exerciciosRecycle = findViewById(R.id.iniciar_treino_recycle);
        /*Iniciando Recycle*/
        exerciciosAdapter = ExercicioAdapter(ArrayList<Treino>());
        exerciciosRecycle.adapter = exerciciosAdapter;
        exercicioLayout = LinearLayoutManager(this);
        exerciciosRecycle.layoutManager = exercicioLayout;
        /*Carregando dados*/
        authentication = Authentication(this);
        val id = intent.getStringExtra("scriptID") ?: " " ;
        scriptDAO = ScriptDAO();
        scriptDAO.getScript(authentication.getId(),id,{
                result -> exerciciosAdapter.exercicios = result;exerciciosAdapter.notifyDataSetChanged();
        }){
                code -> Toast.makeText(applicationContext, "Falha ao carregar Script", Toast.LENGTH_SHORT)
        }
        historicoDAO = HistoricoDAO();
        /*Setando Botões*/
        btnFinalizar.setOnLongClickListener(){
            finalizar();
        }
    }
    private fun finalizar(): Boolean {
        for(i in exerciciosAdapter.exercicios){
            historicoDAO.addHistorico(i);
        }
        finish();
        return true
    }
}