package Clases

class Estado( Padre: Estado, Secuencia : List[Int] ){
  
  /** 
   *  Atributo interno para representa un estado S en forma de una matriz
   *  @param Secuacia que contiene los elementos ordenados para la matriz
   */
  private val Matriz = new Matriz(Secuencia)
  
  /** 
   *  Retorna el estado del cual se derivo este estado (Estaod padre o anterior)
   */
  def GetEstadoAnterior() : Estado = {
    return Padre
  }
  
  /** 
   *  Metodo para inicializar el atributo Subestados
   */
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
  
  /** 
   *  Obtiene un posible estado, para ello los parametros se debe basar
   *  en la coordenadas del elemento neutro.
   *  Si la posicion del elemento no existe devuelve un estado nulo
   *  @param Posicion X del elemento neutro
   *  @param Posicion Y del elemento neutro
   */
  private def GetEstado( PosX : Int, PosY : Int ) : Estado = {
    val Elemento = Matriz.GetElemento(PosX ,PosY)
    if(Elemento.!= (-1)){
      return new Estado(this,Matriz.CambiarPosiciones(0, Elemento).GetSecuencia())
    }
    return null
  }
  
  /** 
   *  Retorna una lista son elementos nulos resultante de otra lista que si los posee
   *  @param Lista de elementos la cual contiene nulos
   */
  private def EliminarNulos( Lista : List[Estado] ): List[Estado] = {
    if(Lista == Nil) Lista
    else if (Lista.head.!=(null)) List(Lista.head) ::: EliminarNulos(Lista.tail)
    else EliminarNulos(Lista.tail)
  }
  
  /** 
   *  Retorna un lista nueva la cual no posee como posible estado al padre
   *  puesto que dicho elemento causaria un bucle
   *  @param Lista que posee como uno de sus elementos al estado padre
   */
  private def EliminarEstadosInvalidos(Lista : List[Estado] ): List[Estado] = {
    if(Lista == Nil) Lista
    else if(Lista.head.!=(Padre)) List(Lista.head) ::: EliminarEstadosInvalidos(Lista.tail)
    else EliminarEstadosInvalidos(Lista.tail)
  }
  
  /** 
   *  Por el momento imprime la matriz contenida, pero deberia imprimir una secuencia
   */
  override def toString(): String = {
    return Matriz.toString()
  }
  
}