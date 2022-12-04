package br.com.ufc.qxd.desmob.apptreino.DAO.internal

import br.com.ufc.qxd.desmob.apptreino.DAO.HistoricoDAOInterface
import br.com.ufc.qxd.desmob.apptreino.DAO.codesDAO
import br.com.ufc.qxd.desmob.apptreino.treino.Historico
import br.com.ufc.qxd.desmob.apptreino.treino.Treino

class HistoricoDAO : HistoricoDAOInterface {
    companion object{
        private var counter:Int=0;
        private var historicoArray = ArrayList<Historico>();
    }
    override fun init() {
        TODO("Not yet implemented")
    }

    override fun addHistorico(treino: Treino,sucess: () -> Unit,error:(code:codesDAO) -> Unit) {
        counter++;
        val historico = Historico(treino, counter.toString());
        historicoArray.add(historico);
        sucess();
    }

    override fun getHistoricoArray(Id:String,sucess: (result:ArrayList<Historico>) -> Unit,error:(code:codesDAO) -> Unit) {
        sucess(ArrayList<Historico>(historicoArray));
    }

    override fun deleteHistorico(Id:String,sucess:() -> Unit,error:(code:codesDAO) -> Unit) {
        for (i in historicoArray)
        {
            if (i.Id == Id){
                historicoArray.remove(i);
                sucess()
                break
            }
        }
        error(codesDAO.NOT_FOUND);
    }
}