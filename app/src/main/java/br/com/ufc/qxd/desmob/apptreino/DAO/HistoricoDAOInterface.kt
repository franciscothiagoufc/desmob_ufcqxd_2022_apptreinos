package br.com.ufc.qxd.desmob.apptreino.DAO

import br.com.ufc.qxd.desmob.apptreino.treino.Historico
import br.com.ufc.qxd.desmob.apptreino.treino.Treino
import java.io.Serializable

interface HistoricoDAOInterface : Serializable {
    public fun init();
    public fun addHistorico(treino: Treino);
    public fun getHistoricoArray(): ArrayList<Historico>;
    public fun deleteHistorico(Id:Int);
}