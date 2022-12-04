package br.com.ufc.qxd.desmob.apptreino

import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.activity.result.ActivityResult
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.ActivityResultRegistry
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import br.com.ufc.qxd.desmob.apptreino.DAO.firebase.ScriptDAO
import br.com.ufc.qxd.desmob.apptreino.firebase.Authentication
import br.com.ufc.qxd.desmob.apptreino.treino.Script
import br.com.ufc.qxd.desmob.apptreino.treino.Treino
import br.com.ufc.qxd.desmob.apptreino.utils.Codes

class InicialScriptsAdapter(Scripts:ArrayList<Script>,Activity: InicialActivity):RecyclerView.Adapter<InicialScriptsAdapter.ScriptViewHolder>() {
    public lateinit var Scripts: ArrayList<Script>;
    private lateinit var Activity: InicialActivity;
    private lateinit var authentication:Authentication;
    private lateinit var scriptDAO: ScriptDAO;
    private lateinit var startForResult: ActivityResultLauncher<Intent>;
    init {
        this.Scripts=Scripts;
        this.Activity = Activity;
        startForResult = Activity.registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result: ActivityResult ->
        }
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
        scriptDAO=ScriptDAO();
        holder.deleteButton.setOnClickListener {
            scriptDAO.deleteScript( authentication.getId(),script.Id,{
                Scripts.removeAt(holder.adapterPosition);
                notifyDataSetChanged();
            }){

            }
        }
        holder.startButton.setOnClickListener {
            var intent = Intent(Activity,IniciarTreinoActivity::class.java);
            intent.putExtra("scriptID",Scripts.get(holder.adapterPosition).Id)
            startForResult.launch(intent);
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