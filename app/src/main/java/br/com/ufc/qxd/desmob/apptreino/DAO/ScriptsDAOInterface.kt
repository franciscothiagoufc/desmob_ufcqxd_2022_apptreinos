package br.com.ufc.qxd.desmob.apptreino.DAO

import br.com.ufc.qxd.desmob.apptreino.DAO.codesDAO
import br.com.ufc.qxd.desmob.apptreino.treino.Script
import br.com.ufc.qxd.desmob.apptreino.treino.Treino
import java.io.Serializable

interface ScriptsDAOInterface{
    public fun init();
    public fun addScript(nome: String,treinos:ArrayList<Treino>,sucess: () -> Unit = {},error:(code: codesDAO) -> Unit = {});
    public fun getScriptArray(Id:String,sucess: (result:ArrayList<Script>) -> Unit = {},error:(code:codesDAO) -> Unit = {});
    public fun getScript(Id:String,sucess: (result:Script) -> Unit = {},error:(code:codesDAO) -> Unit = {});
    public fun deleteScript(Id:String,sucess: () -> Unit = {},error:(code: codesDAO) -> Unit = {});
}