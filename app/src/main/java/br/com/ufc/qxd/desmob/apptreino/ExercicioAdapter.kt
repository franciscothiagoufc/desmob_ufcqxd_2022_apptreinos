package br.com.ufc.qxd.desmob.apptreino
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.TextView
import androidx.core.widget.doAfterTextChanged
import androidx.recyclerview.widget.RecyclerView
import br.com.ufc.qxd.desmob.apptreino.DAO.internal.HistoricoDAO
import br.com.ufc.qxd.desmob.apptreino.treino.Historico
import br.com.ufc.qxd.desmob.apptreino.treino.Treino
import java.lang.Exception

public class ExercicioAdapter(exercicios: ArrayList<Treino>): RecyclerView.Adapter<ExercicioAdapter.TreinoViewHolder>() {
    public var exercicios:ArrayList<Treino>;
    init {
        this.exercicios=exercicios;
    }
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ExercicioAdapter.TreinoViewHolder {
        var itemView = LayoutInflater.from(parent.context).inflate(R.layout.exercicio_item,parent,false);
        return TreinoViewHolder(itemView);
    }
    override fun onBindViewHolder(holder: ExercicioAdapter.TreinoViewHolder, position: Int) {
        val exercicio = exercicios.get(holder.adapterPosition);
        holder.putInfos(exercicio);
        holder.treinoNome.doAfterTextChanged {
            try {
                exercicios.get(holder.adapterPosition).peso = holder.pesoEdit.text.toString().toDouble();
                exercicios.get(holder.adapterPosition).reps = holder.repsEdit.text.toString().toInt();
            }catch (e:Exception){

            }
        }
    }
    override fun getItemCount(): Int {
        return exercicios.size;
    }
    class TreinoViewHolder(itemView: View) :RecyclerView.ViewHolder(itemView){
        public lateinit var treinoNome: TextView;
        public lateinit var seriesView: TextView;
        public lateinit var pesoEdit: EditText;
        public lateinit var repsEdit: EditText;
        init {
            treinoNome = itemView.findViewById(R.id.exercicio_item_nome);
            seriesView = itemView.findViewById(R.id.exercicio_item_serie);
            pesoEdit = itemView.findViewById(R.id.exercicio_item_peso);
            repsEdit = itemView.findViewById(R.id.exercicio_item_reps);
        }
        public fun putInfos(exercicio: Treino){
            treinoNome.text = exercicio.nome;
            seriesView.text = exercicio.series.toString();
        }
    }
}