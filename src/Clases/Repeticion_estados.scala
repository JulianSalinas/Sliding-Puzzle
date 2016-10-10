package Clases

class Lista_Estados(){
    var Lista:List[Estado]=List()

}
class Filas_Estados(){
  var Lista:List[Lista_Estados]=List()

}

class Repeticion_estados {
  var Matriz_Estados:List[Filas_Estados]=List()
  
  def iniciar(estado_meta:Estado)={
   
      for(i <- 0 to estado_meta.Dimension-1){
        Matriz_Estados=Matriz_Estados:::List(new Filas_Estados())
        for(j <- 0 to estado_meta.Dimension-1){
          Matriz_Estados(i).Lista= Matriz_Estados(i).Lista:::List(new Lista_Estados)
        }
      }
  
  }
 
  
  def insertar(estado:Estado)={
    var x:Int=estado.GetPosicion(0)._1
    var y:Int=estado.GetPosicion(0)._2
    if(!EstaElEstado(estado)){
      Matriz_Estados(x).Lista(y).Lista=Matriz_Estados(x).Lista(y).Lista:::List(estado)
    }
  }
  def EstaElEstado(estado:Estado):Boolean={
    var x:Int=estado.GetPosicion(0)._1
    var y:Int=estado.GetPosicion(0)._2

    var lista:List[Estado]=Matriz_Estados(x).Lista(y).Lista
    
    for(i <- lista ){
      if(i.EqualsTo(estado)){
        return true
      }
    }
    return false
 
  }

  
  
}