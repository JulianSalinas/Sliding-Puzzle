package Clases

class Matriz( Secuencia: List[Int] ){
  
  //Atributo : Cantidad de filas = columnas
  val Dimension = Math.pow(Secuencia.length, 0.5).toInt
  
  //Atributo : Lista plana => Lista de listas
  //Ej: List(1,2,3,4,5,6,7,8,9) = List(List(1,2,3),List(4,5,6),List(7,8,9))
  private val Elementos = Secuencia.grouped(Dimension).toList
  
  //Metodo : Obtener la secuencia con la que se creo la matriz
  def GetSecuencia() : List[Int] = {
    return Secuencia
  }
  
  //Metodo : Obtener la posicion de un elemento de la matriz de un elemento
  //Si el elemento no existe entonces se regresa (-1,-1)
  def GetPosicion(Elemento : Int): (Int,Int) = {
    for(i <- 0 to Dimension - 1; j <- 0 to Dimension - 1 )
        if(Elementos(i)(j) == Elemento) return (i,j)
    return (-1,-1)
  }
  
  //Metodo : Obtener el elemento que hay en una posicion
  //Si no existe regresa - 1
  def GetElemento(Fila: Int, Columna: Int): Int = {
    if(Dimension > Fila && Dimension > Columna){
      return Elementos(Fila)(Columna)
    }
    else{
      return -1
    }
  }
  
  //Metodo : Devuelve una NUEVA matriz con el nuevo valor seteado
  def SetElemento(Fila: Int, Columna: Int, Elemento: Int): Matriz = {
    val Elementos_N = Elementos
    val Fila_N = Elementos_N(Fila).updated(Columna, Elemento)
    val Elementos_Aux = Elementos_N.updated(Fila, Fila_N)
    val Sec = Elementos_Aux.flatten
    return new Matriz(Sec)
  }
  
  //Metodo : Regresa una NUEVA matriz intercambiando dos elemetos segun sus posiciones (X,Y)
  def CambiarPosiciones(Posicion_1:(Int,Int), Posicion_2:(Int,Int)): Matriz = {
    val Elemento_1 = Elementos(Posicion_1._1)(Posicion_1._2)
    val Elemento_2 = Elementos(Posicion_2._1)(Posicion_2._2)   
    val Matriz_Aux = 
       SetElemento(Posicion_1._1,Posicion_1._2,Elemento_2)
      .SetElemento(Posicion_2._1,Posicion_2._2, Elemento_1)
    val Sec = Matriz_Aux.Elementos.flatten
    return new Matriz(Sec)
  }
  
  //Metodo : Regresa una NUEVA matriz intercambiando dos elementos segun su valor
  def CambiarPosiciones(Elemento_1 : Int, Elemento_2 : Int): Matriz = {
    val Sec = Elementos.flatten
    val Aux = Sec
      .updated(Sec.indexOf(Elemento_1),Elemento_2)
      .updated(Sec.indexOf(Elemento_2),Elemento_1)
    return new Matriz(Aux)
  }
  
  //Metodo: Compara esta matriz con una pasada por parametro 
  def EqualsTo(Matriz : Matriz): Boolean = {
    return Matriz.Elementos == this.Elementos
  }
  
  //Metodo : Para poder imprimir la matriz bonita
  override def toString() : String = {
    val str = for (l <- Elementos) yield l.mkString("{", ",", "}")
    return str.mkString("\n")
  }
  
}