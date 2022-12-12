package br.com.ufc.qxd.desmob.apptreino
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.ImageButton
import android.widget.TextView
import androidx.activity.result.ActivityResult
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.recyclerview.widget.RecyclerView
import br.com.ufc.qxd.desmob.apptreino.DAO.firebase.ScriptDAO
import br.com.ufc.qxd.desmob.apptreino.firebase.Authentication
import br.com.ufc.qxd.desmob.apptreino.treino.Script

class InicialScriptsAdapter(Scripts:ArrayList<Script>,Activity: InicialActivity):RecyclerView.Adapter<InicialScriptsAdapter.ScriptViewHolder>() {
    public lateinit var Scripts: ArrayList<Script>;
    private lateinit var Activity: InicialActivity;
    private lateinit var authentication:Authentication;
    private lateinit var scriptDAO: ScriptDAO;
    private lateinit var startForResult: ActivityResultLauncher<Intent>;
    init {
        /*Sentando parametros*/
        this.Scripts=Scripts;
        this.Activity = Activity;
        /*Obtendo autenticação do usuário*/
        this.authentication=Authentication(Activity)
        /*Criando chamada para a activity iniciar treino*/
        startForResult = Activity.registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
                result: ActivityResult ->
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
        /*Iniciando ViewHolders*/
        val script = Scripts.get(holder.adapterPosition);
        holder.putInfos(script);
        /*Implementando função de deletar script*/
        scriptDAO=ScriptDAO();
        holder.deleteButton.setOnClickListener {
            scriptDAO.deleteScript( authentication.getId(),script.Id,{
                Scripts.removeAt(holder.adapterPosition);
                notifyDataSetChanged();
            }){

            }
        }
        /*Implementando função de iniciar script*/
        holder.startButton.setOnClickListener {
            var intent = Intent(Activity,IniciarTreinoActivity::class.java);
            intent.putExtra("scriptID",Scripts.get(holder.adapterPosition).Id)
            startForResult.launch(intent);
        }
        /*Animações ocorre caso haja uma alteração na posição*/
        if(holder.adapterPosition >  holder.lastposition){
            holder.itemView.startAnimation(AnimationUtils.loadAnimation(this.Activity.baseContext,android.R.anim.fade_in))
            holder.lastposition=holder.adapterPosition
        }
    }
    override fun getItemCount(): Int {
        return Scripts.size;
    }
    class ScriptViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        public lateinit var startButton: ImageButton;
        public lateinit var deleteButton: ImageButton;
        public lateinit var nome: TextView;
        public var lastposition = -1
        init {
            /*View do item script*/
            deleteButton = itemView.findViewById(R.id.iniciar_script_item_remover);
            startButton = itemView.findViewById(R.id.iniciar_script_item_iniciar);
            nome = itemView.findViewById(R.id.iniciar_script_item_nome);
        }
        fun putInfos(script: Script){
            nome.text = script.nome;
        }
    }
}