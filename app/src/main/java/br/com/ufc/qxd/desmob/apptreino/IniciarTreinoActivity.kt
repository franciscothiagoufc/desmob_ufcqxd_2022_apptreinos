package br.com.ufc.qxd.desmob.apptreino

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.LinearLayout
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import br.com.ufc.qxd.desmob.apptreino.DAO.internal.HistoricoDAO
import br.com.ufc.qxd.desmob.apptreino.DAO.internal.ScriptDAO
import br.com.ufc.qxd.desmob.apptreino.treino.Treino

class IniciarTreinoActivity : AppCompatActivity() {
    private lateinit var btnFinalizar : Button;
    private lateinit var exerciciosRecycle : RecyclerView;
    private lateinit var exerciciosAdapter: ExercicioAdapter;
    private lateinit var exercicioLayout: LinearLayoutManager;
    private lateinit var scriptDAO: ScriptDAO;
    private lateinit var historicoDAO: HistoricoDAO;
    private lateinit var exercicios: ArrayList<Treino>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_iniciar_treino)
        /*Carregando Views*/
        btnFinalizar = findViewById(R.id.iniciar_treino_btn_finalizar);
        exerciciosRecycle = findViewById(R.id.iniciar_treino_recycle);
        /*Carregando dados*/
        val id = intent.getIntExtra("scriptID",0);
        scriptDAO = ScriptDAO();
        exercicios = scriptDAO.getScript(id)?.exercicios ?: ArrayList<Treino>();
        historicoDAO = HistoricoDAO();
        /*Iniciando Recycle*/
        exerciciosAdapter = ExercicioAdapter(exercicios);
        exerciciosRecycle.adapter = exerciciosAdapter;
        exercicioLayout = LinearLayoutManager(this);
        exerciciosRecycle.layoutManager = exercicioLayout;
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