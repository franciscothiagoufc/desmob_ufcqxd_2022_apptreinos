package br.com.ufc.qxd.desmob.apptreino

import android.content.Context
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

public class TreinosCadastroAdapter(exercicios: ArrayList<Treino>): RecyclerView.Adapter<TreinosCadastroAdapter.TreinoViewHolder>() {
    private var exercicios:ArrayList<Treino>;
    private lateinit var contex:Context
    init {
        this.exercicios=exercicios;
    }
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): TreinosCadastroAdapter.TreinoViewHolder {
        var itemView = LayoutInflater.from(parent.context).inflate(R.layout.treinos_cadastro_item,parent,false);
        contex=parent.context
        return TreinoViewHolder(itemView);
    }

    override fun onBindViewHolder(holder: TreinosCadastroAdapter.TreinoViewHolder, position: Int) {
        val exercicio = exercicios.get(holder.adapterPosition);
        holder.putInfos(exercicio);
        holder.deleteButton.setOnClickListener {
            exercicios.removeAt(holder.adapterPosition);
            notifyDataSetChanged();
        }
        /*Animações ocorre caso haja uma alteração na posição*/
        if(holder.adapterPosition >  holder.lastposition){
            holder.itemView.startAnimation(android.view.animation.AnimationUtils.loadAnimation(contex,android.R.anim.slide_in_left))
            holder.lastposition=holder.adapterPosition
        }
    }

    override fun getItemCount(): Int {
        return exercicios.size;
    }
    class TreinoViewHolder(itemView: View) :RecyclerView.ViewHolder(itemView){
        public lateinit var deleteButton: ImageButton;
        public lateinit var treinoNome: TextView;
        public lateinit var seriesView: TextView;
        public var lastposition=-1
        init {
            deleteButton = itemView.findViewById(R.id.treinos_cadastro_item_deleta);
            treinoNome = itemView.findViewById(R.id.treinos_cadastro_item_treino);
            seriesView = itemView.findViewById(R.id.treinos_cadastro_item_series);
        }
        public fun putInfos(exercicio: Treino){
            treinoNome.text = exercicio.nome;
            seriesView.text = exercicio.series.toString();
        }
    }
}