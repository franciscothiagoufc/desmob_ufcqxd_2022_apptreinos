package br.com.ufc.qxd.desmob.apptreino.DAO.internal

import br.com.ufc.qxd.desmob.apptreino.DAO.HistoricoDAOInterface
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

    override fun addHistorico(treino: Treino) {
        counter++;
        val historico = Historico(treino, counter);
        historicoArray.add(historico);
    }

    override fun getHistoricoArray():ArrayList<Historico> {
        return historicoArray;
    }

    override fun deleteHistorico(Id: Int):Boolean {
        for (i in historicoArray)
        {
            if (i.Id == Id){
                historicoArray.remove(i);
                return true;
            }
        }
        return false;
    }
}