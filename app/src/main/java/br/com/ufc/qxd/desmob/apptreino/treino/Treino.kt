package br.com.ufc.qxd.desmob.apptreino.treino

class Treino(nome:String,series:Int,reps:Int) {
    var nome:String
    var series:Int
    var reps:Int
    var peso:Double = 0.0;
    init {
        this.nome=nome;
        this.series=series;
        this.reps=reps;
    }
}