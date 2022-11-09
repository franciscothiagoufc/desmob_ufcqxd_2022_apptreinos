package br.com.ufc.qxd.desmob.apptreino

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import androidx.viewpager2.adapter.FragmentStateAdapter

class TreinoTabAdapter(fragment: FragmentActivity) : FragmentStateAdapter(fragment) {

    override fun getItemCount(): Int {
        return 3;
    }
    override fun createFragment(position: Int): Fragment {
        return when(position){
            0 -> CadastroFragment();
            1 -> HistoricoFragment();
            2 -> EstatisticaFragment();
            else -> CadastroFragment();
        }
    }
}