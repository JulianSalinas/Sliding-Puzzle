package Pruebas
import Clases.Estado
import Clases.Heuristica
import Clases.Solucion


object Programa {
  
  def main(args: Array[String]): Unit = {
    
    //Probando los metodos de la matriz
    val matriz_1 = new Estado(List(1,2,3,4,5,6,7,8,0))
    println("Matriz original: \n" + matriz_1)
    println("GetPosicion(6): " + matriz_1.GetPosicion(6))
    println("GetElemento(1,1): " + matriz_1.GetElemento(1, 1))
    println("SetElemento(0,0,50): \n" + matriz_1.SetElemento(0, 0, 50))
    println("CambiarPosiciones(0,6): \n" + matriz_1.CambiarPosiciones(0, 6))
    println("CambiarPosiciones((0,0),(0,1)): \n" + matriz_1.CambiarPosiciones((0,0),(0, 1)))
    
    //Pruebas de los estados
    
    //Estado raiz
    val estado_1 = new Estado(List(1,2,3,4,0,6,7,8,5))
    println("Estado raiz: \n" + estado_1)
    
    //Subestados de la raiz
    val subEstados = estado_1.GetSubEstados()
    for(i <- 0 to subEstados.length - 1)
      println("SubEstado "+ i + " de la raiz: \n"+ subEstados(i))
    
    //El estado anterior del primer subestado debe ser la raiz
    val estado_anterior = subEstados(0).Estado_Anterior
    println("Estado anterior al subEstado 0 de la raiz: \n" + estado_anterior)
    
    //SubEstados del subEstado 0 de la raiz
    val subSubEstados = subEstados(0).GetSubEstados()
    for(i <- 0 to subSubEstados.length - 1)
      println("SubEstado "+ i + " del subEstado 0: \n"+ subSubEstados(i))
    
   
    val matriz1 = new Estado(List(2,12,14,13,7,3,9,5,8,6,11,10,0,4,15,1))
    val matrizMeta = new Estado(List(1,2,3,4,5,7,6,8,9,10,11,12,13,14,15,0))
    
    println("\nMAT1")
    println(matriz1)
    println("\nMatMeta")
    println(matrizMeta)
    
    val heuristicas = new Heuristica()
    
    println("SOLUBLE" + heuristicas.tieneSolucion(matriz1, matrizMeta))
    
    val matrizComparar1 = new Estado(List(1,2,3,4,5,6,7,8,9))
    val matrizComparar2 = new Estado(List(1,2,3,4,5,6,7,8,9))
    
    println(
        "\nmatrizComparar1.EqualsTo(matrizComparar2)\n"+ 
        matrizComparar1.EqualsTo(matrizComparar2)
    )
    /*
     *  probar si una matriz tiene solucion
     */
    
    //tiene solucion
    val m1 = new Estado(List(2,1,3,4,5,6,8,7,0))
    //no tiene solucion
    val m2 = new Estado(List(2,1,3,4,5,6,7,8,0))
    
    val s = new Solucion()
    
    println(s.HasSolucion(m1))
    
    println(m1+"\ntiene solucion: "+s.HasSolucion(m1))
    println(m2+"\ntiene solucion: "+s.HasSolucion(m2))
    
  }
  
}