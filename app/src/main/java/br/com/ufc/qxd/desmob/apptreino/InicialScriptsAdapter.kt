package br.com.ufc.qxd.desmob.apptreino

import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import br.com.ufc.qxd.desmob.apptreino.treino.Script
import br.com.ufc.qxd.desmob.apptreino.treino.Treino

class InicialScriptsAdapter(Scripts:ArrayList<Script>,Activity: InicialActivity):RecyclerView.Adapter<InicialScriptsAdapter.ScriptViewHolder>() {
    private lateinit var Scripts: ArrayList<Script>;
    private lateinit var Activity: InicialActivity;
    init {
        this.Scripts=Scripts;
        this.Activity = Activity;
    }
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): InicialScriptsAdapter.ScriptViewHolder {
        var itemView = LayoutInflater.from(parent.context).inflate(R.layout.iniciar_script_item,parent,false);
        return InicialScriptsAdapter.ScriptViewHolder(itemView);
    }
    override fun onBindViewHolder(holder: InicialScriptsAdapter.ScriptViewHolder, position: Int) {
        val script = Scripts.get(holder.adapterPosition);
        holder.putInfos(script);
        holder.deleteButton.setOnClickListener {
            Scripts.removeAt(holder.adapterPosition);
            notifyDataSetChanged();
        }
        holder.startButton.setOnClickListener {
            val iniciarTreinoIntent = Intent(Activity,IniciarTreinoActivity::class.java);
            Activity.startActivity(iniciarTreinoIntent);

        }
    }
    override fun getItemCount(): Int {
        return Scripts.size;
    }
    class ScriptViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        public lateinit var startButton: ImageButton;
        public lateinit var deleteButton: ImageButton;
        public lateinit var nome: TextView;
        init {
            deleteButton = itemView.findViewById(R.id.iniciar_script_item_remover);
            startButton = itemView.findViewById(R.id.iniciar_script_item_iniciar);
            nome = itemView.findViewById(R.id.iniciar_script_item_nome);
        }
        fun putInfos(script: Script){
            nome.text = script.nome;
        }
    }
}