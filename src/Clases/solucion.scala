package Clases

class solucion {
  def par( valor : Int ):Boolean={

    if(valor%2 == 0){
      return true
    }
    return false
  }
  /*
   * 
   * Retorna la suma de los inversores
   * Los inversores son la cantidad de numeros mas pequeños que el elemento
   * despues del elemento
   * 
   */
    def inversiones( estado : Matriz ):Int={
      var lista_valores:List[Int]=estado.GetSecuencia()
     
      var lista_inversiones:List[Int]=List()
      for ( i <- 0 to lista_valores.length-1 ){
        
         lista_inversiones=lista_inversiones:::List(0)
         
         for(j<- i+1 to lista_valores.length-1){
           
           if(lista_valores(i)>lista_valores(j) && lista_valores(j) != 0){
            lista_inversiones=lista_inversiones.updated(i, lista_inversiones(i)+1)
             
           }
         }
      }
    return (lista_inversiones.sum)
  }
    /*
     * Retorna:true
     * si el espacion en blanco esta debajo de la mitad
     * y esta en una fila par
     * 
     */
   def espacio_blanco(estado : Matriz):Boolean={
     
    if( estado.GetPosicion(0)._1 < (estado.Dimension/2) && par(estado.GetPosicion(0)._1)){
      return true
    }
    return false
   }
   /*
    * Retorna: true
    * si el estado tiene solucion
    * 
    */
  def tiene_solucion(estado : Matriz): Boolean ={ 
    var cantidad_inversiones=inversiones(estado)
    if( ((!par(estado.Dimension)) && (par(cantidad_inversiones)))|| (par(estado.Dimension) && (par(cantidad_inversiones)==espacio_blanco(estado))) ){
      return true
    }
    return false
    
  }
}