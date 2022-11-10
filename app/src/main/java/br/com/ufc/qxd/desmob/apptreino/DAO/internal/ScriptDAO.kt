package br.com.ufc.qxd.desmob.apptreino.DAO.internal

import br.com.ufc.qxd.desmob.apptreino.DAO.ScriptsDAOInterface
import br.com.ufc.qxd.desmob.apptreino.treino.Historico
import br.com.ufc.qxd.desmob.apptreino.treino.Script
import br.com.ufc.qxd.desmob.apptreino.treino.Treino

class ScriptDAO : ScriptsDAOInterface {
    companion object{
        private var counter:Int=0;
        private var scriptArray = ArrayList<Script>();
    }
    override fun init() {
        TODO("Not yet implemented")
    }

    override fun addScript(nome: String,treinos:ArrayList<Treino>) {
        counter++;
        val script = Script(nome, counter);
        script.exercicios = ArrayList(treinos);
        scriptArray.add(script);
    }

    override fun getScriptArray():ArrayList<Script> {
        return scriptArray;
    }

    override fun deleteScript(Id: Int) {
        for (i in scriptArray)
        {
            if (i.Id == Id){
                scriptArray.remove(i);
                break;
            }
        }
    }
}