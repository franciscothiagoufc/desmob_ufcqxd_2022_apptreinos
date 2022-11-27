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

public class ExercicioAdapter(exercicios: ArrayList<Treino>): RecyclerView.Adapter<ExercicioAdapter.TreinoViewHolder>() {
    private var exercicios:ArrayList<Treino>;
    init {
        this.exercicios=exercicios;
    }
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ExercicioAdapter.TreinoViewHolder {
        var itemView = LayoutInflater.from(parent.context).inflate(R.layout.treinos_cadastro_item,parent,false);
        return TreinoViewHolder(itemView);
    }

    override fun onBindViewHolder(holder: ExercicioAdapter.TreinoViewHolder, position: Int) {
        val exercicio = exercicios.get(holder.adapterPosition);
        holder.putInfos(exercicio);
        holder.deleteButton.setOnClickListener {
            exercicios.removeAt(holder.adapterPosition);
            notifyDataSetChanged();
        }
    }

    override fun getItemCount(): Int {
        return exercicios.size;
    }
    class TreinoViewHolder(itemView: View) :RecyclerView.ViewHolder(itemView){
        public lateinit var deleteButton: ImageButton;
        public lateinit var treinoNome: TextView;
        public lateinit var seriesView: TextView;
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