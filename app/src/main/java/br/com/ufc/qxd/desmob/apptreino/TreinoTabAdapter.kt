package br.com.ufc.qxd.desmob.apptreino

import android.util.Log
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.adapter.FragmentViewHolder
import br.com.ufc.qxd.desmob.apptreino.DAO.firebase.HistoricoDAO
import br.com.ufc.qxd.desmob.apptreino.DAO.firebase.ScriptDAO

class TreinoTabAdapter(fragment: TreinosActivity,userId:String) : FragmentStateAdapter(fragment) {
    private lateinit var userId:String;
    init {
        this.userId=userId;
    }
    override fun getItemCount(): Int {
        return 3;
    }

    override fun onAttachedToRecyclerView(recyclerView: RecyclerView) {
        super.onAttachedToRecyclerView(recyclerView)
    }
    override fun createFragment(position: Int): Fragment {
        return when(position){
            0 -> {
                val cadastro = CadastroFragment()
                cadastro.userId=userId;
                return cadastro;
            }
            1 -> {
                val historico = HistoricoFragment()
                historico.userId=userId;
                return historico;
            };
            2 -> {
                EstatisticaFragment()
            };
            else -> CadastroFragment();
        }
    }
}