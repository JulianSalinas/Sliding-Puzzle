package Clases

class Matriz( Secuencia: List[Int] ){
  
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
  private val Elementos = Secuencia.grouped(Dimension).toList
  
  /** 
   *  Retorna la secuencia con la que se creo la matriz
   */
  def GetSecuencia() : List[Int] = {
    return Secuencia
  }
  
  /** 
   *  Retorna una tupla que representa las coordenadas del valor consultado.
   *  Si dicho valor no existe en la matriz entonces se regresa (-1,-1)
   *  @param elemento del cual se quiere obtener la posicion
   *  @param numero de columna
   */
  def GetPosicion(Elemento : Int): (Int,Int) = {
    for(i <- 0 to Dimension - 1; j <- 0 to Dimension - 1 )
        if(Elementos(i)(j) == Elemento) return (i,j)
    return (-1,-1)
  }
  
  /** 
   *  Retorna el valor contenido en la matriz segun las coordenadas dadas
   *  @param numero de fila
   *  @param numero de columna
   */
  def GetElemento(Fila: Int, Columna: Int): Int = {
    if(Dimension > Fila && Dimension > Columna){
      return Elementos(Fila)(Columna)
    }
    else{
      return -1
    }
  }
  
  /** 
   *  Retorna una NUEVA matriz con el nuevo valor seteado
   *  @param numero de fila
   *  @param numero de columna
   *  @param elemento a setear
   */
  def SetElemento(Fila: Int, Columna: Int, Elemento: Int): Matriz = {
    val Elementos_N = Elementos
    val Fila_N = Elementos_N(Fila).updated(Columna, Elemento)
    val Elementos_Aux = Elementos_N.updated(Fila, Fila_N)
    val Sec = Elementos_Aux.flatten
    return new Matriz(Sec)
  }
  
  /** 
   *  Retorna una NUEVA matriz intercambiando dos elemetos segun sus posiciones (X,Y)
   *  @param coordenadas del elemento a intercambiar E1 
   *  @param coordenadas del elemento a intercambiar E2 donde E2 != E1
   */
  def CambiarPosiciones(Posicion_1:(Int,Int), Posicion_2:(Int,Int)): Matriz = {
    val Elemento_1 = Elementos(Posicion_1._1)(Posicion_1._2)
    val Elemento_2 = Elementos(Posicion_2._1)(Posicion_2._2)   
    val Matriz_Aux = 
       SetElemento(Posicion_1._1,Posicion_1._2,Elemento_2)
      .SetElemento(Posicion_2._1,Posicion_2._2, Elemento_1)
    val Sec = Matriz_Aux.Elementos.flatten
    return new Matriz(Sec)
  }
  
  /** 
   *  Retorna una NUEVA matriz con los elementos intercambiados
   *  @param elemento a intercambiar X 
   *  @param elemento a intercambiar Y donde X != Y
   */
  def CambiarPosiciones(Elemento_1 : Int, Elemento_2 : Int): Matriz = {
    val Sec = Elementos.flatten
    val Aux = Sec
      .updated(Sec.indexOf(Elemento_1),Elemento_2)
      .updated(Sec.indexOf(Elemento_2),Elemento_1)
    return new Matriz(Aux)
  }
  
  /** 
   *  Retorna true si las matrices son iguales
   *  @param matriz con la cual comparar
   */
  def EqualsTo(Matriz : Matriz): Boolean = {
    return Matriz.Elementos == this.Elementos
  }
  
  /** 
   *  Imprime la matriz de forma cuadrada
   */
  override def toString() : String = {
    val str = for (l <- Elementos) yield l.mkString("{", ",", "}")
    return str.mkString("\n")
  }
  
}