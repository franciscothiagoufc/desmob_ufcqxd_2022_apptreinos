package br.com.ufc.qxd.desmob.apptreino

import android.util.Log
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import androidx.viewpager2.adapter.FragmentStateAdapter
import br.com.ufc.qxd.desmob.apptreino.DAO.internal.HistoricoDAO
import br.com.ufc.qxd.desmob.apptreino.DAO.firebase.ScriptDAO

class TreinoTabAdapter(fragment: TreinosActivity,userId:String) : FragmentStateAdapter(fragment) {
    private lateinit var userId:String;
    init {
        this.userId=userId;
    }
    override fun getItemCount(): Int {
        return 3;
    }
    override fun createFragment(position: Int): Fragment {
        return when(position){
            0 -> CadastroFragment(userId);
            1 -> HistoricoFragment();
            2 -> EstatisticaFragment();
            else -> CadastroFragment(userId);
        }
    }
}