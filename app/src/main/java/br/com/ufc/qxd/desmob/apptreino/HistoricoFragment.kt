package br.com.ufc.qxd.desmob.apptreino

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import br.com.ufc.qxd.desmob.apptreino.DAO.internal.HistoricoDAO
import br.com.ufc.qxd.desmob.apptreino.treino.Historico
import br.com.ufc.qxd.desmob.apptreino.treino.Treino

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [HistoricoFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class HistoricoFragment(historicoDAO: HistoricoDAO) : Fragment() {
    private lateinit var historicoRecycle:RecyclerView;
    private lateinit var historicoRecycleAdapter: TreinosHistoricoAdapter;
    private lateinit var linearManger: LinearLayoutManager;
    private lateinit var historicoDAO: HistoricoDAO;
    private lateinit var Treinos: ArrayList<Historico>;
    init {
        this.historicoDAO=historicoDAO;
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val View = inflater.inflate(R.layout.fragment_historico, container, false)
        /*Carregando Dados*/
        /*Iniciando Views*/
        linearManger = LinearLayoutManager(this.context);
        historicoRecycleAdapter = TreinosHistoricoAdapter(historicoDAO);
        historicoRecycle = View.findViewById(R.id.treinos_historico_recycle);
        /*Configurando Recycle View*/
        historicoRecycle.adapter = historicoRecycleAdapter;
        historicoRecycle.layoutManager = linearManger;
        historicoRecycleAdapter.notifyDataSetChanged();
        return View;
    }
}