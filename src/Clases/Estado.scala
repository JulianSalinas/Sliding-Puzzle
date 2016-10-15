package Clases

/**
 */
class Estado(_Secuencia : List[Int] , _Estado_Anterior: Estado = null ){
  
  /** 
   *  Atributo: Estado del cual se derivo este estado (Estado padre)
   */
  val Estado_Anterior = _Estado_Anterior
  
  /** 
   *  Retorna la secuencia con la que se creo la matriz
   */
  val Secuencia = _Secuencia
  
  /** 
   *  Atributo que representa la cantidad de filas y columnas que tiene la matriz
   *  Se infiere obteniendo la raiz cuadrada del largo de la lista (debe tener raiz exacta)
   */
  val Dimension = Math.pow(Secuencia.length, 0.5).toInt
  
  /** 
   *  Atributo que usa la matriz de forma interna, se convierte la secuencia
   *  en una lista de listas.
   *  Ej: List(1,2,3,4,5,6,7,8,9) = List(List(1,2,3),List(4,5,6),List(7,8,9))
   */
  private val Matriz = Secuencia.grouped(Dimension).toList
  
  /** 
   *  Retorna una tupla que representa las coordenadas del valor consultado.
   *  Si dicho valor no existe en la matriz entonces se regresa (-1,-1)
   *  @param elemento del cual se quiere obtener la posicion
   *  @param numero de columna
   */
  def GetPosicion(Elemento : Int): (Int,Int) = {
    for(i <- 0 to Dimension - 1; j <- 0 to Dimension - 1 )
        if(Matriz(i)(j) == Elemento) return (i,j)
    return (-1,-1)
  }
  
  /** 
   *  Retorna el valor contenido en la matriz segun las coordenadas dadas
   *  @param numero de fila
   *  @param numero de columna
   */
  def GetElemento(Fila: Int, Columna: Int): Int = {
    val Clausula_1 = Dimension > Fila && Dimension > Columna
    val Clausula_2 = Fila >= 0 && Columna >= 0
    if(Clausula_1 && Clausula_2) return Matriz(Fila)(Columna)
    else return -1
  }
  
  /** 
   *  Retorna una NUEVO estado con el nuevo valor seteado
   *  @param numero de fila
   *  @param numero de columna
   *  @param elemento a setear
   */
  def SetElemento(Fila: Int, Columna: Int, Elemento: Int): Estado = {
    val Matriz_N = Matriz
    val Fila_N = Matriz_N(Fila).updated(Columna, Elemento)
    val Matriz_Aux = Matriz_N.updated(Fila, Fila_N)
    val Sec = Matriz_Aux.flatten
    return new Estado(Sec,Estado_Anterior)
  }
  
  /** 
   *  Retorna una NUEVO estado intercambiando dos elemetos segun sus posiciones (X,Y)
   *  @param coordenadas del elemento a intercambiar E1 
   *  @param coordenadas del elemento a intercambiar E2 donde E2 != E1
   */
  def CambiarPosiciones(Posicion_1:(Int,Int), Posicion_2:(Int,Int)): Estado = {
    val Elemento_1 = Matriz(Posicion_1._1)(Posicion_1._2)
    val Elemento_2 = Matriz(Posicion_2._1)(Posicion_2._2)   
    val Matriz_Aux = 
       SetElemento(Posicion_1._1,Posicion_1._2,Elemento_2)
      .SetElemento(Posicion_2._1,Posicion_2._2, Elemento_1)
    val Sec = Matriz_Aux.Matriz.flatten
    return new Estado(Sec,Estado_Anterior)
  }
  
  /** 
   *  Retorna una NUEVO estado con los elementos intercambiados
   *  @param elemento a intercambiar X 
   *  @param elemento a intercambiar Y donde X != Y
   */
  def CambiarPosiciones(Elemento_1 : Int, Elemento_2 : Int): Estado = {
    val Sec = Matriz.flatten
    val Aux = Sec
      .updated(Sec.indexOf(Elemento_1),Elemento_2)
      .updated(Sec.indexOf(Elemento_2),Elemento_1)
    return new Estado(Aux,Estado_Anterior)
  }
  
  /** 
   *  Obtiene todos los posibles segun los movimientos validos
   *  @param Existentes Lista con los estados vistos actualmente
   *  @param estadoMeta Estado objetivo de solucion
   */
  def GetSubEstados(Existentes : List[Estado]) : List[Estado] = {
    val Neutro = GetPosicion(0)
    val Est_Der = GetSubEstado(Neutro._1,Neutro._2+1)
    val Est_Izq = GetSubEstado(Neutro._1,Neutro._2-1)
    val Est_Arr = GetSubEstado(Neutro._1+1,Neutro._2)
    val Est_Aba = GetSubEstado(Neutro._1-1,Neutro._2)
    val Estados = List(Est_Der,Est_Izq,Est_Arr,Est_Aba)
    val Estados_No_Nulos = EliminarNulos(Estados)
    val Estados_No_Existentes = EliminarEstadosInvalidos(Estados_No_Nulos, Existentes)
    return Estados_No_Existentes
  }
  
  /** 
   *  Obtiene un posible estado, para ello los parametros se debe basar
   *  en las coordenadas del elemento neutro.
   *  Si la posicion del elemento no existe devuelve un estado nulo
   *  @param Posicion X del elemento neutro
   *  @param Posicion Y del elemento neutro
   */
  private def GetSubEstado( PosX : Int, PosY : Int ) : Estado = {
    val Elemento = GetElemento(PosX ,PosY)
    if(Elemento.!= (-1))
      return new Estado(CambiarPosiciones(0, Elemento).Secuencia, this)
    return null
  }
  
  /** 
   *  Retorna una lista sin Matriz nulos resultante de otra lista que si los posee
   *  @param Lista de Matriz la cual contiene nulos
   */
  private def EliminarNulos( Lista : List[Estado] ): List[Estado] = {
    if(Lista == Nil) Lista
    else if (Lista.head.!=(null)) List(Lista.head) ::: EliminarNulos(Lista.tail)
    else EliminarNulos(Lista.tail)
  }
  
  /**
   * Retorna una lista la cual no posee como estados posibles, estados ya existentes en el arbol
   * @param Lista Posee los estados posibles
   * @param Existentes Lista con los estados existentes en arbol
   */
  private def EliminarEstadosInvalidos(Lista : List[Estado], Existentes : List[Estado]): List[Estado] = {
    if(Lista == Nil) Lista
    else if( !Existentes.exists { x => Lista.head.EqualsTo(x) }) List(Lista.head) ::: EliminarEstadosInvalidos(Lista.tail, Existentes)
    else EliminarEstadosInvalidos(Lista.tail, Existentes)
  }

  /** 
   *  Retorna true si las matrices son iguales
   *  @param matriz con la cual comparar
   */
  def EqualsTo(_Estado : Estado): Boolean = {
    return _Estado.Matriz == this.Matriz
  }
  
  /**
   *  Imprime la matriz de forma cuadrada
   */
  override def toString() : String = {
    /*val str = for (l <- Matriz) yield l.mkString("{", ",", "}")
    return str.mkString("\n")*/
    return Secuencia.mkString("{", ",", "}")
  }
  
}