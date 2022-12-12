package br.com.ufc.qxd.desmob.apptreino

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import br.com.ufc.qxd.desmob.apptreino.DAO.firebase.HistoricoDAO
import br.com.ufc.qxd.desmob.apptreino.treino.Estatistica
import br.com.ufc.qxd.desmob.apptreino.treino.Historico

class EstatisticaFragment() : Fragment() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var estatisticaAdapter: EstatisticaAdapter;
    private lateinit var estatisticas: HashMap<String,Estatistica>
    private lateinit var layout:LinearLayoutManager;
    public lateinit var historicoDAO: HistoricoDAO;
    public lateinit var userId:String
    init {
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        if(savedInstanceState != null)
            userId=savedInstanceState.get("userId") as String
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_estatistica, container, false)
        recyclerView = view.findViewById(R.id.treinos_estatistica_recycle);
        estatisticas = HashMap<String,Estatistica>()
        //estatisticas["supino"] = Estatistica(10,10.0,10.0)
        //estatisticas["flexao"] = Estatistica(10,10.0,10.0)
        estatisticaAdapter = EstatisticaAdapter(estatisticas)
        recyclerView.adapter = estatisticaAdapter
        layout=LinearLayoutManager(context)
        recyclerView.layoutManager=layout
        estatisticaAdapter.notifyDataSetChanged()
        /*Recuperando dados*/
        historicoDAO = HistoricoDAO();
        historicoDAO.getHistoricoArray(userId,{
                result->calcularEstatistica(result)
        }){

        }
        return view;
    }
    override fun onSaveInstanceState(outState: Bundle) {
        outState.putString("userId",userId)
        super.onSaveInstanceState(outState)
    }
    private fun calcularEstatistica(historico:ArrayList<Historico>){
            var seriesTotal = HashMap<String,Int>()
            var repsTotal = HashMap<String,Double>()
            var pesoTotal = HashMap<String,Double>()
            for(i in historico){
                val nome = i.treino.nome
                val serie = seriesTotal[nome] ?: 0
                val reps = repsTotal[nome] ?: 0
                val peso = pesoTotal[nome] ?: 0
                seriesTotal[nome] = serie + i.treino.series
                repsTotal[nome] = serie + i.treino.reps.toDouble()
                pesoTotal[nome] = serie + i.treino.series.toDouble()*i.treino.reps.toDouble()
            }
            for(nome in seriesTotal.keys){
                val serie = seriesTotal[nome] ?: 0
                val reps = repsTotal[nome] ?: 1.0
                val peso = pesoTotal[nome] ?: 0.0
                estatisticas[nome] = Estatistica(serie,reps/serie.toDouble(), peso/reps)
            }
        estatisticaAdapter.notifyDataSetChanged()
    }
}