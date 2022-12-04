package br.com.ufc.qxd.desmob.apptreino.DAO

import br.com.ufc.qxd.desmob.apptreino.DAO.codesDAO
import br.com.ufc.qxd.desmob.apptreino.treino.Script
import br.com.ufc.qxd.desmob.apptreino.treino.Treino
import java.io.Serializable

interface ScriptsDAOInterface{
    public fun init();
    public fun addScript(userId:String,nome: String,treinos:ArrayList<Treino>,sucess: () -> Unit = {},error:(code: codesDAO) -> Unit = {});
    public fun getScriptArray(userId:String,sucess: (result:ArrayList<Script>) -> Unit = {},error:(code:codesDAO) -> Unit = {});
    public fun getScript(userId:String,Id:String,sucess: (result:ArrayList<Treino>) -> Unit = {},error:(code:codesDAO) -> Unit = {});
    public fun deleteScript(userId:String,Id:String,sucess: () -> Unit = {},error:(code: codesDAO) -> Unit = {});
}