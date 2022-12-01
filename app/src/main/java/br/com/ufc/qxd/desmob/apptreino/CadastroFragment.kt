package br.com.ufc.qxd.desmob.apptreino

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.ImageButton
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import br.com.ufc.qxd.desmob.apptreino.DAO.internal.ScriptDAO
import br.com.ufc.qxd.desmob.apptreino.treino.Script
import br.com.ufc.qxd.desmob.apptreino.treino.Treino

class CadastroFragment : Fragment() {
    private lateinit var addExercicio: ImageButton;
    private lateinit var addTreino: ImageButton;
    private lateinit var nomeScript: EditText;
    private lateinit var nomeExercicio: EditText;
    private lateinit var series: EditText;
    private lateinit var exerciciosRecyclerView: RecyclerView;
    private lateinit var exerciciosAdapter: TreinosCadastroAdapter;
    private lateinit var exerciciosLinearLayout: LinearLayoutManager;
    private lateinit var exercicios: ArrayList<Treino>;
    private lateinit var scriptDAO: ScriptDAO;
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var view = inflater.inflate(R.layout.fragment_cadastro, container, false)
        /*Instanciondo Views*/
        addExercicio = view.findViewById(R.id.treinos_cadastro_add_exercicio);
        addTreino = view.findViewById(R.id.treinos_cadastro_confirma);
        nomeScript = view.findViewById(R.id.treinos_cadastro_script);
        nomeExercicio = view.findViewById(R.id.treinos_cadastro_exercicio);
        series = view.findViewById(R.id.treinos_cadastro_serie);
        exerciciosRecyclerView = view.findViewById(R.id.treinos_cadastro_exercicio_recycle);
        /*Iniciando DAO*/
        scriptDAO = ScriptDAO();
        /*Carregando dados*/
        exercicios = ArrayList<Treino>();
        /*Iniciando Recycle*/
        exerciciosAdapter = TreinosCadastroAdapter(exercicios)
        exerciciosLinearLayout = LinearLayoutManager(this.context);
        exerciciosRecyclerView.adapter = exerciciosAdapter;
        exerciciosRecyclerView.layoutManager = exerciciosLinearLayout;
        //exerciciosAdapter.notifyDataSetChanged();
        /*Funções dos Bostões*/
        addExercicio.setOnClickListener {
            try {
                val nome = nomeExercicio.text.toString();
                val serie = series.text.toString().toInt();
                exercicios.add(Treino(nome,serie,0));
                exerciciosAdapter.notifyDataSetChanged();
            }catch (e:Exception){

            }

        }
        addTreino.setOnClickListener {
            try {
                val nome = nomeScript.text.toString();
                if(scriptDAO.addScript(nome,exercicios)){
                    nomeScript.text.clear();
                    nomeExercicio.text.clear();
                    series.text.clear();
                    exercicios.clear();
                    exerciciosAdapter.notifyDataSetChanged();
                }
            }catch (e:Exception){

            }
        }
        return view;
    }
}