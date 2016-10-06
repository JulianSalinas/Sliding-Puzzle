package Clases
import Clases.Matriz

class Estado( Padre: Estado, Secuencia : List[Int] ){
  
  //Atributo : Matriz que representa un estado S
  private val Matriz = new Matriz(Secuencia)

  //Metodo: Devuelve el estado anterior
  def GetEstadoAnterior() : Estado = {
    return Padre
  }
  
  //Metodo: Obtiene todos los estados para los posibles movimientos
  def GetSubEstados() : List[Estado] = {
    val Neutro = Matriz.GetPosicion(0)
    val Est_Der = GetEstado(Neutro._1,Neutro._2+1)
    val Est_Izq = GetEstado(Neutro._1,Neutro._2-1)
    val Est_Arr = GetEstado(Neutro._1+1,Neutro._2)
    val Est_Aba = GetEstado(Neutro._1-1,Neutro._2)
    val Estados = List(Est_Der,Est_Izq,Est_Arr,Est_Aba)
    val Estados_No_Nulos = EliminarNulos(Estados)
    val Estados_No_Invalidos = EliminarEstadosInvalidos(Estados_No_Nulos)
    return Estados_No_Invalidos
  }
  
  //Metodo: Obtiene un estado segun la posicion donde deberia estar un elemento
  //Si la posicion del elemento no existe devuelve un estado nulo
  private def GetEstado( PosX : Int, PosY : Int ) : Estado = {
    val Elemento = Matriz.GetElemento(PosX ,PosY)
    if(Elemento.!= (-1)){
      return new Estado(this,Matriz.CambiarPosiciones(0, Elemento).GetSecuencia())
    }
    return null
  }
  
  //Metodo: Eliminas los estados nulos
  private def EliminarNulos( Lista : List[Estado] ): List[Estado] = {
    if(Lista == Nil) Lista
    else if (Lista.head.!=(null)) List(Lista.head) ::: EliminarNulos(Lista.tail)
    else EliminarNulos(Lista.tail)
  }
  
  //Metodo: Toma una lista de estados y elimina los que sean iguales al padre
  private def EliminarEstadosInvalidos(Lista : List[Estado] ): List[Estado] = {
    if(Lista == Nil) Lista
    else if(Lista.head.!=(Padre)) List(Lista.head) ::: EliminarEstadosInvalidos(Lista.tail)
    else EliminarEstadosInvalidos(Lista.tail)
  }
  
  override def toString(): String = {
    return Matriz.toString()
  }
  
}