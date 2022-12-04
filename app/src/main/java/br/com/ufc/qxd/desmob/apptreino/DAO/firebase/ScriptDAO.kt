package br.com.ufc.qxd.desmob.apptreino.DAO.firebase

import android.util.Log
import br.com.ufc.qxd.desmob.apptreino.DAO.ScriptsDAOInterface
import br.com.ufc.qxd.desmob.apptreino.DAO.codesDAO
import br.com.ufc.qxd.desmob.apptreino.firebase.Authentication
import br.com.ufc.qxd.desmob.apptreino.treino.Script
import br.com.ufc.qxd.desmob.apptreino.treino.Treino
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.QuerySnapshot
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class ScriptDAO : ScriptsDAOInterface {
    //private lateinit var authentication: Authentication;
    override fun init() {
        //authentication=Authentication();
    }
    override fun addScript(
        userId: String,
        nome: String,
        treinos: ArrayList<Treino>,
        sucess: () -> Unit,
        error: (code: codesDAO) -> Unit
    ) {
        var data = hashMapOf<String,Any>();
        data["userId"] = userId;
        data["nome"] = nome;
        data["exercicios"] = treinos
        Firebase.firestore.collection("Scripts").add(data).addOnSuccessListener{
            sucess()
        }.addOnCanceledListener(){
            error(codesDAO.CON_ERRO)
        }
    }
    override fun getScriptArray(
        userId: String,
        sucess: (result: ArrayList<Script>) -> Unit,
        error: (code: codesDAO) -> Unit
    ) {
        Firebase.firestore.collection("Scripts").whereEqualTo("userId", userId).get().addOnSuccessListener(){
            documents -> run{
                var result = ArrayList<Script>();
                for(i in documents){
                    result.add(Script(i.id,i["nome"] as String))
                }
                sucess(result)
        }
        }.addOnCanceledListener {
            error(codesDAO.CON_ERRO);
        }
    }

    override fun getScript(
        userId: String,
        Id: String,
        sucess: (result: ArrayList<Treino>) -> Unit,
        error: (code: codesDAO) -> Unit
    ) {
        Firebase.firestore.collection("Scripts").document(Id).get().addOnSuccessListener(){
                document -> run{
                    val itemType = object : TypeToken<ArrayList<Treino>>() {}.type
                    val exercicios=Gson().fromJson<ArrayList<Treino>>(document["exercicios"].toString(),itemType)
                    sucess(exercicios)
        }
        }.addOnCanceledListener {
            error(codesDAO.CON_ERRO);
        }
    }

    override fun deleteScript(userId: String,Id: String, sucess: () -> Unit, error: (code: codesDAO) -> Unit) {
        TODO("Not yet implemented")
    }
}