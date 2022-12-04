package br.com.ufc.qxd.desmob.apptreino.treino

class Script(nome:String,Id:String="") {
    var Id:String="";
    var exercicios:ArrayList<Treino>
    var nome:String
    init {
        this.Id = Id;
        this.exercicios = ArrayList<Treino>();
        this.nome = nome;
    }
}