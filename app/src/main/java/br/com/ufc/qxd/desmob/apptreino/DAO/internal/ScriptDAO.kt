package br.com.ufc.qxd.desmob.apptreino.DAO.internal

import br.com.ufc.qxd.desmob.apptreino.DAO.ScriptsDAOInterface
import br.com.ufc.qxd.desmob.apptreino.DAO.codesDAO
import br.com.ufc.qxd.desmob.apptreino.treino.Historico
import br.com.ufc.qxd.desmob.apptreino.treino.Script
import br.com.ufc.qxd.desmob.apptreino.treino.Treino

/*class ScriptDAO : ScriptsDAOInterface {
    companion object{
        private var counter:Int=0;
        private var scriptArray = ArrayList<Script>();
    }
    override fun init() {
        TODO("Not yet implemented")
    }

    override public fun addScript(nome: String,treinos:ArrayList<Treino>,sucess: () -> Unit ,error:(code: codesDAO) -> Unit) {
        counter++;
        val script = Script(nome, counter.toString());
        script.exercicios = ArrayList(treinos);
        scriptArray.add(script);
        sucess();
    }

    override fun getScriptArray(Id:String,sucess:(result:ArrayList<Script>) -> Unit,error:(code:codesDAO) -> Unit) {
        sucess(ArrayList<Script>(scriptArray))
    }
    override public fun getScript(Id:String,sucess: (result:Script) -> Unit,error:(code:codesDAO) -> Unit){
        for(i in scriptArray){
            if(i.Id == Id){
                sucess(i)
                break
            }
        }
        error(codesDAO.NOT_FOUND)
    }

    override fun deleteScript(Id:String,sucess: () -> Unit ,error:(code:codesDAO) -> Unit) {
        for (i in scriptArray)
        {
            if (i.Id == Id){
                scriptArray.remove(i);
                sucess();
                break;
            }
        }
        error(codesDAO.NOT_FOUND);
    }
}

 */