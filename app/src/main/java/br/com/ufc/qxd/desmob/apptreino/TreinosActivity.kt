package br.com.ufc.qxd.desmob.apptreino

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.viewpager2.widget.ViewPager2
import br.com.ufc.qxd.desmob.apptreino.DAO.internal.HistoricoDAO
import br.com.ufc.qxd.desmob.apptreino.DAO.firebase.ScriptDAO
import br.com.ufc.qxd.desmob.apptreino.firebase.Authentication
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class TreinosActivity : AppCompatActivity() {
    private lateinit var tabLayout:TabLayout;
    private lateinit var viewpager:ViewPager2;
    private lateinit var tabAdapter:TreinoTabAdapter;
    private lateinit var tabLayoutMediator: TabLayoutMediator;
    private lateinit var authentication: Authentication;
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_treinos)
        /*Autenticação para obter o Id do usuári*/
        authentication = Authentication(this);
        /*Iniciando Views*/
        tabLayout = findViewById(R.id.treinos_tab);
        viewpager = findViewById(R.id.treinos_viewpage);
        /*Configurando Tabs e ViewPager*/
        tabAdapter = TreinoTabAdapter(this,authentication.getId());
        viewpager.adapter = tabAdapter;
        tabLayoutMediator=TabLayoutMediator(tabLayout,viewpager){tab, position ->
            tab.text = when(position){
                0 -> "Cadatros";
                1 -> "Histórico";
                2 -> "Estatística";
                else -> "None";
            }
        }
        tabLayoutMediator.attach();
    }

    override fun onResume() {
        super.onResume()
        Log.w("TreinosActivity","TreinosActivity")
    }
}