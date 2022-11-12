package br.com.ufc.qxd.desmob.apptreino

import android.util.Log
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import androidx.viewpager2.adapter.FragmentStateAdapter
import br.com.ufc.qxd.desmob.apptreino.DAO.internal.HistoricoDAO
import br.com.ufc.qxd.desmob.apptreino.DAO.internal.ScriptDAO

class TreinoTabAdapter(fragment: TreinosActivity,scriptDAO: ScriptDAO,historicoDAO: HistoricoDAO) : FragmentStateAdapter(fragment) {
    private lateinit var historicoDAO:HistoricoDAO;
    private lateinit var scriptDAO: ScriptDAO;
    init {
        this.historicoDAO=historicoDAO;
        this.scriptDAO=scriptDAO;
    }
    override fun getItemCount(): Int {
        return 3;
    }
    override fun createFragment(position: Int): Fragment {
        return when(position){
            0 -> CadastroFragment();
            1 -> return HistoricoFragment(historicoDAO);
            2 -> EstatisticaFragment();
            else -> CadastroFragment();
        }
    }
}