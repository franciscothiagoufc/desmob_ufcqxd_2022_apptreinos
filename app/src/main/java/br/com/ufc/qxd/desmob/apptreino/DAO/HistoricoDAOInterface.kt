package br.com.ufc.qxd.desmob.apptreino.DAO

import br.com.ufc.qxd.desmob.apptreino.DAO.codesDAO
import br.com.ufc.qxd.desmob.apptreino.treino.Historico
import br.com.ufc.qxd.desmob.apptreino.treino.Treino
import java.io.Serializable

interface HistoricoDAOInterface {
    public fun init();
    public fun addHistorico(userId:String,treino: Treino,sucess: () -> Unit = {},error:(code:codesDAO) -> Unit = {});
    public fun getHistoricoArray(userId: String,sucess: (result:ArrayList<Historico>) -> Unit = {},error:(code:codesDAO) -> Unit = {});
    public fun deleteHistorico(userId: String,Id:String,sucess:() -> Unit = {},error:(code: codesDAO) -> Unit = {});
}