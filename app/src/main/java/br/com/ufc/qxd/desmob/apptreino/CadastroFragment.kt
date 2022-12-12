package br.com.ufc.qxd.desmob.apptreino

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import br.com.ufc.qxd.desmob.apptreino.DAO.firebase.ScriptDAO
import br.com.ufc.qxd.desmob.apptreino.firebase.Authentication
import br.com.ufc.qxd.desmob.apptreino.treino.Script
import br.com.ufc.qxd.desmob.apptreino.treino.Treino
import io.grpc.Context

class CadastroFragment() : Fragment() {
    private lateinit var addExercicio: ImageButton;
    private lateinit var addTreino: ImageButton;
    private lateinit var nomeScript: EditText;
    private lateinit var nomeExercicio: AutoCompleteTextView;
    private lateinit var autoCompleteAdapter: ArrayAdapter<String>;
    private lateinit var series: EditText;
    private lateinit var exerciciosRecyclerView: RecyclerView;
    private lateinit var exerciciosAdapter: TreinosCadastroAdapter;
    private lateinit var exerciciosLinearLayout: LinearLayoutManager;
    private lateinit var exercicios: ArrayList<Treino>;
    private lateinit var scriptDAO: ScriptDAO;
    public lateinit var userId: String;
    init {

    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        if(savedInstanceState?.containsKey("userId") == true)
            userId = savedInstanceState?.getString("userId") ?: ""
        // Inflate the layout for this fragment
        var view = inflater.inflate(R.layout.fragment_cadastro, container, false)
        /*Instanciondo Views*/
        addExercicio = view.findViewById(R.id.treinos_cadastro_add_exercicio);
        addTreino = view.findViewById(R.id.treinos_cadastro_confirma);
        nomeScript = view.findViewById(R.id.treinos_cadastro_script);
        nomeExercicio = view.findViewById(R.id.treinos_cadastro_exercicio);
        autoCompleteAdapter = ArrayAdapter<String>(inflater.context,android.R.layout.simple_list_item_1,resources.getStringArray(R.array.exercicios))
        nomeExercicio.setAdapter(autoCompleteAdapter)
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
                scriptDAO.addScript(userId,nome,exercicios,{
                    this.nomeScript.text.clear();
                    this.nomeExercicio.text.clear();
                    this.series.text.clear();
                    this.exercicios.clear();
                    exerciciosAdapter.notifyDataSetChanged();
                }){
                        code -> Toast.makeText(context, "Falha ao carregar Script", Toast.LENGTH_SHORT)
                }
            }catch (e:Exception){

            }
        }
        return view;
    }
    override fun onSaveInstanceState(outState: Bundle) {
        outState.putString("userId",userId)
        super.onSaveInstanceState(outState)
    }
}