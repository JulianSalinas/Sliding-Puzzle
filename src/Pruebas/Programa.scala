package Pruebas
import Clases.Matriz
import Clases.Estado
import Clases.Heuristica

object Programa {
  
  def main(args: Array[String]): Unit = {
    
    //Probando los metodos de la matriz
    val matriz_1 = new Matriz(List(1,2,3,4,5,6,7,8,0))
    println("Matriz original: \n" + matriz_1)
    println("GetPosicion(6): " + matriz_1.GetPosicion(6))
    println("GetElemento(1,1): " + matriz_1.GetElemento(1, 1))
    println("SetElemento(0,0,50): \n" + matriz_1.SetElemento(0, 0, 50))
    println("CambiarPosiciones(0,6): \n" + matriz_1.CambiarPosiciones(0, 6))
    println("CambiarPosiciones((0,0),(0,1)): \n" + matriz_1.CambiarPosiciones((0,0),(0, 1)))
    
    //Pruebas de los estados
    
    //Estado raiz
    val estado_1 = new Estado(null,List(1,2,3,4,0,6,7,8,5))
    println("Estado raiz: \n" + estado_1)
    
    //Subestados de la raiz
    val subEstados = estado_1.SubEstados
    for(i <- 0 to subEstados.length - 1)
      println("SubEstado "+ i + " de la raiz: \n"+ subEstados(i))
    
    //El estado anterior del primer subestado debe ser la raiz
    val estado_anterior = subEstados(0).GetEstadoAnterior()
    println("Estado anterior al subEstado 0 de la raiz: \n" + estado_anterior)
    
    //SubEstados del subEstado 0 de la raiz
    val subSubEstados = subEstados(0).SubEstados
    for(i <- 0 to subSubEstados.length - 1)
      println("SubEstado "+ i + " del subEstado 0: \n"+ subSubEstados(i))
    
   
    val matriz1 = new Matriz(List(2,8,7,4,5,6,3,0,1))
    val matrizMeta = new Matriz(List(1,2,3,4,5,6,7,8,0))
    
    println("\nMAT1")
    println(matriz1)
    println("\nMatMeta")
    println(matrizMeta)
    
    val heuristicas = new Heuristica()
    
    println("\n" + heuristicas.MisplacedTiles(matriz1, matrizMeta))
    
    val matrizComparar1 = new Matriz(List(1,2,3,4,5,6,7,8,9))
    val matrizComparar2 = new Matriz(List(1,2,3,4,5,6,7,8,9))
    println(
        "\nmatrizComparar1.EqualsTo(matrizComparar2)\n"+ 
        matrizComparar1.EqualsTo(matrizComparar2)
    )
    
  }
  
}