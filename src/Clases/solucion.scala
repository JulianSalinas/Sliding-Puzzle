package Clases

class Solucion {
  
  private def IsPar( valor : Int ): Boolean = {
    return valor%2 == 0
  }
  
  /**
   * Retorna la suma de los inversores
   * Los inversores son la cantidad de numeros mas pequeños que el elemento
   * despues del elemento
   */
  def inversiones( estado : Estado ):Int={
    
    var lista_valores:List[Int] = estado.Secuencia
    var lista_inversiones:List[Int]=List()
    
    for ( i <- 0 to lista_valores.length-1 ){
      lista_inversiones=lista_inversiones:::List(0)
      for(j<- i+1 to lista_valores.length-1){
        if(lista_valores(i)>lista_valores(j) && lista_valores(j) != 0)
          lista_inversiones=lista_inversiones.updated(i, lista_inversiones(i)+1)
         }
      }
    
    return (lista_inversiones.sum)
  }
  
  /**
   * Retorna true
   * si el espacion en blanco esta debajo de la mitad y esta en una fila par
   */
   def espacio_blanco(estado : Estado): Boolean = {
     return estado.GetPosicion(0)._1 < estado.Dimension/2 && IsPar(estado.GetPosicion(0)._1)
   }
   
   /*
    * Retorna true si el estado tiene solucion
    */
    def HasSolucion(estado : Estado): Boolean ={ 
      var cantidad_inversiones = inversiones(estado)
      val Clausula_1 = !IsPar(estado.Dimension) && IsPar(cantidad_inversiones)
      val Clausula_2 = IsPar(cantidad_inversiones) == espacio_blanco(estado)
      val Clausula_3 = IsPar(estado.Dimension) && (Clausula_2)
      return Clausula_1 || Clausula_2
    }
}