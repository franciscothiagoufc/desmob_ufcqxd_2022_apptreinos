package br.com.ufc.qxd.desmob.apptreino

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import br.com.ufc.qxd.desmob.apptreino.DAO.internal.HistoricoDAO
import br.com.ufc.qxd.desmob.apptreino.treino.Historico
import br.com.ufc.qxd.desmob.apptreino.treino.Treino

public class TreinosHistoricoAdapter(historicoDAO: HistoricoDAO): RecyclerView.Adapter<TreinosHistoricoAdapter.TreinoViewHolder>() {
    private var historicoDAO:HistoricoDAO;
    init {
        this.historicoDAO=historicoDAO;
    }
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): TreinosHistoricoAdapter.TreinoViewHolder {
        var itemView = LayoutInflater.from(parent.context).inflate(R.layout.treinos_historico_item,parent,false);
        return TreinoViewHolder(itemView);
    }

    override fun onBindViewHolder(holder: TreinosHistoricoAdapter.TreinoViewHolder, position: Int) {
        val historico = historicoDAO.getHistoricoArray().get(holder.adapterPosition);
        holder.putInfos(historico.treino);
        holder.deleteButton.setOnClickListener {
            if(historicoDAO.deleteHistorico(historico.Id)){
                notifyDataSetChanged();
            }
        }
    }

    override fun getItemCount(): Int {
        return this.historicoDAO.getHistoricoArray().size;
    }
    class TreinoViewHolder(itemView: View) :RecyclerView.ViewHolder(itemView){
        public lateinit var deleteButton: ImageButton;
        public lateinit var treinoNome: TextView;
        public lateinit var repeticoesView: TextView;
        public lateinit var seriesView: TextView;
        init {
            deleteButton = itemView.findViewById(R.id.treinos_historico_item_deleta);
            treinoNome = itemView.findViewById(R.id.treinos_historico_item_treino);
            repeticoesView = itemView.findViewById(R.id.treinos_historico_item_repeticoes);
            seriesView = itemView.findViewById(R.id.treinos_historico_item_series);
        }
        public fun putInfos(treino: Treino){
            treinoNome.text = treino.nome;
            repeticoesView.text = treino.reps.toString();
            seriesView.text = treino.series.toString();
        }
    }
}