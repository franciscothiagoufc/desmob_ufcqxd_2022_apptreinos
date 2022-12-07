package br.com.ufc.qxd.desmob.apptreino.DAO.firebase

import android.util.Log
import br.com.ufc.qxd.desmob.apptreino.DAO.HistoricoDAOInterface
import br.com.ufc.qxd.desmob.apptreino.DAO.codesDAO
import br.com.ufc.qxd.desmob.apptreino.treino.Historico
import br.com.ufc.qxd.desmob.apptreino.treino.Script
import br.com.ufc.qxd.desmob.apptreino.treino.Treino
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class HistoricoDAO : HistoricoDAOInterface {
    init {

    }
    override fun init() {
        TODO("Not yet implemented")
    }
    override fun addHistorico(
        userId: String,
        treino: Treino,
        sucess: () -> Unit,
        error: (code: codesDAO) -> Unit
    ) {
        var data = hashMapOf<String,Any>();
        data["userId"] = userId;
        data["peso"] = treino.peso
        data["series"] = treino.series
        data["nome"] = treino.nome
        data["reps"] = treino.reps
        Firebase.firestore.collection("Historico").add(data).addOnSuccessListener{
            sucess()
        }.addOnCanceledListener(){
            error(codesDAO.CON_ERRO)
        }
    }

    override fun getHistoricoArray(
        userId: String,
        sucess: (result: ArrayList<Historico>) -> Unit,
        error: (code: codesDAO) -> Unit
    ) {
        Firebase.firestore.collection("Historico").whereEqualTo("userId", userId).get().addOnSuccessListener(){
                documents -> run{
            var result = ArrayList<Historico>();
            for(i in documents){
                result.add(Historico(Treino(i["nome"] as String,Integer.parseInt(i["series"].toString()) as Int,Integer.parseInt(i["reps"].toString())),i.id))
            }
            sucess(result)
        }
        }.addOnCanceledListener {
            error(codesDAO.CON_ERRO);
        }
    }

    override fun deleteHistorico(
        userId: String,
        Id: String,
        sucess: () -> Unit,
        error: (code: codesDAO) -> Unit
    ) {
        Log.w("teste",Id);
        Firebase.firestore.collection("Historico").document(Id).delete().addOnSuccessListener({sucess()}).addOnCanceledListener{error(codesDAO.CON_ERRO)}
    }

}