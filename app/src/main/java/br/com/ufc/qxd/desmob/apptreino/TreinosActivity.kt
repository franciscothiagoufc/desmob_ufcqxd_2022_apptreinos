package br.com.ufc.qxd.desmob.apptreino

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class TreinosActivity : AppCompatActivity() {
    private lateinit var tabLayout:TabLayout;
    private lateinit var viewpager:ViewPager2;
    private lateinit var tabAdapter:TreinoTabAdapter;
    private lateinit var tabLayoutMediator: TabLayoutMediator;
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_treinos)
        /*Iniciando Views*/
        tabLayout = findViewById(R.id.treinos_tab);
        viewpager = findViewById(R.id.treinos_viewpage);
        /*Configurando Tabs e ViewPager*/
        tabAdapter = TreinoTabAdapter(this);
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
}