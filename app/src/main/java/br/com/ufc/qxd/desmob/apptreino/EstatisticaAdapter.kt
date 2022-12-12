package br.com.ufc.qxd.desmob.apptreino

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.TextView
import androidx.core.widget.doAfterTextChanged
import androidx.recyclerview.widget.RecyclerView
import br.com.ufc.qxd.desmob.apptreino.treino.Estatistica
import br.com.ufc.qxd.desmob.apptreino.treino.Treino

public class EstatisticaAdapter(estatisticas: HashMap<String,Estatistica>): RecyclerView.Adapter<EstatisticaAdapter.EstatisticaViewHolder>() {
    public var estatisticas: Map<String,Estatistica>;
    init {
        this.estatisticas=estatisticas;
    }
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): EstatisticaAdapter.EstatisticaViewHolder {
        var itemView = LayoutInflater.from(parent.context).inflate(R.layout.treinos_estatistica_item,parent,false);
        return EstatisticaViewHolder(itemView);
    }
    override fun onBindViewHolder(holder: EstatisticaAdapter.EstatisticaViewHolder, position: Int) {
        val nome = estatisticas.keys.toList()[holder.adapterPosition]
        val estatistica = estatisticas[nome] ?: Estatistica(0,0.0,0.0);
        holder.putInfos(estatistica,nome)
    }
    override fun getItemCount(): Int {
        return estatisticas.size;
    }
    class EstatisticaViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        public lateinit var series: TextView;
        public lateinit var nome: TextView;
        public lateinit var peso: TextView;
        public lateinit var reps: TextView;
        init {
            series = itemView.findViewById(R.id.treinos_estatistica_item_series);
            nome = itemView.findViewById(R.id.treinos_estatistica_item_nome);
            peso = itemView.findViewById(R.id.treinos_estatistica_item_peso);
            reps = itemView.findViewById(R.id.treinos_estatistica_item_reps);
        }
        public fun putInfos(estatistica: Estatistica,nome:String){
            this.nome.text = nome;
            this.peso.setText(String.format("%.2f",estatistica.pesoAvg).toString())
            this.reps.setText(String.format("%.2f",estatistica.repsAvg).toString())
            this.series.setText(estatistica.serieTotal.toString())
        }
    }
}