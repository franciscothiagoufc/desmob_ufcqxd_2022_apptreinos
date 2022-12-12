package br.com.ufc.qxd.desmob.apptreino

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import br.com.ufc.qxd.desmob.apptreino.DAO.firebase.HistoricoDAO
import br.com.ufc.qxd.desmob.apptreino.treino.Historico
import br.com.ufc.qxd.desmob.apptreino.treino.Treino

public class TreinosHistoricoAdapter(historicoArray:ArrayList<Historico>,userId:String): RecyclerView.Adapter<TreinosHistoricoAdapter.TreinoViewHolder>() {
    private lateinit var historicoDAO:HistoricoDAO;
    public lateinit var historicoArray:ArrayList<Historico>;
    private lateinit var userId: String;
    init {
        this.historicoDAO=HistoricoDAO();
        this.historicoArray=ArrayList<Historico>()
        this.userId=userId;
    }
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): TreinosHistoricoAdapter.TreinoViewHolder {
        var itemView = LayoutInflater.from(parent.context).inflate(R.layout.treinos_historico_item,parent,false);
        return TreinoViewHolder(itemView);
    }

    override fun onBindViewHolder(holder: TreinosHistoricoAdapter.TreinoViewHolder, position: Int) {
        val historico = historicoArray.get(holder.adapterPosition);
        holder.putInfos(historico.treino);
        holder.deleteButton.setOnClickListener {
            historicoDAO.deleteHistorico(userId,historico.Id,{
                historicoArray.removeAt(holder.adapterPosition)
                notifyDataSetChanged();
            }){
            }
        }
    }

    override fun getItemCount(): Int {
        return this.historicoArray.size;
    }
    class TreinoViewHolder(itemView: View) :RecyclerView.ViewHolder(itemView){
        public lateinit var deleteButton: ImageButton;
        public lateinit var treinoNome: TextView;
        public lateinit var pesoView: TextView;
        public lateinit var repeticoesView: TextView;
        public lateinit var seriesView: TextView;
        init {
            deleteButton = itemView.findViewById(R.id.treinos_historico_item_deleta);
            treinoNome = itemView.findViewById(R.id.treinos_historico_item_treino);
            repeticoesView = itemView.findViewById(R.id.treinos_historico_item_repeticoes);
            pesoView = itemView.findViewById(R.id.treinos_historico_item_peso);
            seriesView = itemView.findViewById(R.id.treinos_historico_item_series);
        }
        public fun putInfos(treino: Treino){
            treinoNome.text = treino.nome;
            repeticoesView.text = treino.reps.toString();
            seriesView.text = treino.series.toString();
            pesoView.text = treino.peso.toString();
        }
    }
}