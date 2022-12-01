package br.com.ufc.qxd.desmob.apptreino.DAO

import br.com.ufc.qxd.desmob.apptreino.treino.Script
import br.com.ufc.qxd.desmob.apptreino.treino.Treino
import java.io.Serializable

interface ScriptsDAOInterface : Serializable {
    public fun init();
    public fun addScript(nome: String,treinos:ArrayList<Treino>):Boolean;
    public fun getScript(id:Int):Script?;
    public fun getScriptArray():ArrayList<Script>;
    public fun deleteScript(Id:Int):Boolean;
}