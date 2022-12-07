package br.com.ufc.qxd.desmob.apptreino

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import br.com.ufc.qxd.desmob.apptreino.DAO.firebase.HistoricoDAO
import br.com.ufc.qxd.desmob.apptreino.firebase.Authentication
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
class HistoricoFragment() : Fragment() {
    private lateinit var historicoRecycle:RecyclerView;
    private lateinit var historicoRecycleAdapter: TreinosHistoricoAdapter;
    private lateinit var linearManger: LinearLayoutManager;
    public lateinit var historicoDAO: HistoricoDAO;
    public var userId:String="";
    private lateinit var Treinos: ArrayList<Historico>;
    init {
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        if(savedInstanceState != null)
            userId=savedInstanceState.get("userId") as String
        Log.w("TreinosActivity",userId)
        // Inflate the layout for this fragment
        val View = inflater.inflate(R.layout.fragment_historico, container, false)
        /*Iniciando Views*/
        linearManger = LinearLayoutManager(this.context);
        historicoRecycleAdapter = TreinosHistoricoAdapter(ArrayList<Historico>(),userId);
        historicoRecycle = View.findViewById(R.id.treinos_historico_recycle);
        /*Configurando Recycle View*/
        historicoRecycle.adapter = historicoRecycleAdapter;
        historicoRecycle.layoutManager = linearManger;
        historicoRecycleAdapter.notifyDataSetChanged();
        /*Recuperando dados*/
        historicoDAO = HistoricoDAO();
        historicoDAO.getHistoricoArray(userId,{
            result->historicoRecycleAdapter.historicoArray = result;historicoRecycleAdapter.notifyDataSetChanged();
        }){

        }
        return View;
    }
    override fun onSaveInstanceState(outState: Bundle) {
        outState.putString("userId",userId)
        super.onSaveInstanceState(outState)
    }
    override fun onResume() {
        super.onResume()
    }
}