package br.com.ufc.qxd.desmob.apptreino.utils

import br.com.ufc.qxd.desmob.apptreino.DAO.internal.HistoricoDAO
import br.com.ufc.qxd.desmob.apptreino.DAO.internal.ScriptDAO
import br.com.ufc.qxd.desmob.apptreino.treino.Script
import br.com.ufc.qxd.desmob.apptreino.treino.Treino

class Utils {
    companion object{
        public fun dummyScripts(scriptDAO: ScriptDAO){
            val treino1 = Treino("Treino1",1,1);
            val treino2 = Treino("Treino2",2,2);
            val treino3 = Treino("Treino3",3,3);
            val treino4 = Treino("Treino4",4,4);
            val treinoArray = ArrayList<Treino>();
            treinoArray.add(treino1);
            treinoArray.add(treino2);
            treinoArray.add(treino3);
            treinoArray.add(treino4);
            scriptDAO.addScript("Treino1",treinoArray)
            scriptDAO.addScript("Treino2",treinoArray)
            scriptDAO.addScript("Treino3",treinoArray)
            scriptDAO.addScript("Treino4",treinoArray)
        }
        public fun dummyHistorico(historicoDAO: HistoricoDAO){
            val treino1 = Treino("Treino1",1,1);
            val treino2 = Treino("Treino2",2,2);
            val treino3 = Treino("Treino3",3,3);
            val treino4 = Treino("Treino4",4,4);
            historicoDAO.addHistorico(treino1);
            historicoDAO.addHistorico(treino2);
            historicoDAO.addHistorico(treino3);
            historicoDAO.addHistorico(treino4);
        }
    }
}