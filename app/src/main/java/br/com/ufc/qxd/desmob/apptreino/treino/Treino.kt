package br.com.ufc.qxd.desmob.apptreino.treino

class Treino(nome:String,series:Int=0,reps:Int=0,peso:Double=0.0) {
    var nome:String
    var series:Int
    var reps:Int
    var peso:Double
    init {
        this.nome=nome;
        this.series=series;
        this.reps=reps;
        this.peso=peso
    }
}