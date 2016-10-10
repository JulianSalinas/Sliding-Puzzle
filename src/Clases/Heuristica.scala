package Clases

class Heuristica {
  
  /** 
   *  Retorna el estimado de Manhattan entre 2 matrices
   *  @param estadoPosible matriz para el que calculamos la heuristica
   *  @param estadoMeta matriz con el estado al que se quiere llegar
   */
  def Manhattan( estadoPosible : Estado , estadoMeta : Estado ) : Int = {
    
    var tamano: Int = estadoMeta.Secuencia.length
    var acumulado: Int = 0
    
    for ( count <- 0 to tamano-1) {
      val posible = estadoPosible.GetPosicion(count)
      val meta = estadoMeta.GetPosicion(count)
      // Peculiaridad de la heuristica
      acumulado += Math.abs(posible._1 - meta._1) + Math.abs(posible._2 - meta._2)
    }
      
    return acumulado
  }
  
  /** 
   *  Retorna el entero estimado por heuristica. https://heuristicswiki.wikispaces.com/Tiles+out+of+row+and+column
   *  entre 2 matrices
   *  @param estadoPosible matriz para el que calculamos la heuristica
   *  @param estadoMeta matriz con el estado al que se quiere llegar
   */
  def TilesOutOf( estadoPosible : Estado , estadoMeta : Estado ) : Int = {
    
    var tamano: Int = estadoMeta.Secuencia.length
    var acumulado: Int = 0
    
    for ( count <- 0 to tamano-1) {
      val posible = estadoPosible.GetPosicion(count)
      val meta = estadoMeta.GetPosicion(count)     
      // Peculiaridad de la heuristica
      if (posible._1 != meta._1) acumulado += 1
      if (posible._2 != meta._2) acumulado += 1
    }
    
    return acumulado
  }
  
  /** 
   *  Retorna el entero estimado por heuristica Misplaced Tiles
   *  entre 2 matrices
   *  @param estadoPosible matriz para el que calculamos la heuristica
   *  @param estadoMeta matriz con el estado al que se quiere llegar
   */
  def MisplacedTiles( estadoPosible : Estado , estadoMeta : Estado ) : Int = {
    
    var tamano: Int = estadoMeta.Secuencia.length
    var acumulado: Int = 0
    
    // Peculiaridad de la heuristica -> el for empieza en 1
    for ( count <- 0 to tamano-1) {
      val posible = estadoPosible.GetPosicion(count)
      val meta = estadoMeta.GetPosicion(count)      
      // Peculiaridad de la heuristica
      if (posible != meta) acumulado += 1
    }
    
    return acumulado
  }
  
  /** 
   *  Retorna true o false si es posible o no llegar desde el primer estado recibido hasta el 2do
   *  @param estadoPosible estado inicial
   *  @param estadoMeta estado al que se quiere llegar
   */
  def tieneSolucion( estadoPosible : Estado, estadoMeta : Estado ) : Boolean = {
    
    // algoritmo de esta heuristica
    // https://www.cs.bham.ac.uk/~mdr/teaching/modules04/java2/TilesSolvability.html

    var secuenciaMeta = estadoMeta.Secuencia
    var secuenciaPosible = estadoPosible.Secuencia
    var inversiones = 0
    
    var tuplasMeta : List[(Int,Int)] = List() 
    
    for (count <- 0 to secuenciaMeta.length-1) 
      tuplasMeta = tuplasMeta:::List( (count, secuenciaMeta(count)) )
    
    // calcular numero de inversiones
    for (elementCount <- 0 to secuenciaPosible.length-2) {
      if (secuenciaPosible(elementCount) != 0)
      for (indexCount <- elementCount+1 to secuenciaPosible.length-1){        
        // valor relativo del elemento a verificar
        if (secuenciaPosible(indexCount) != 0) {
          val relativeVal = tuplasMeta.find(_._2 == secuenciaPosible(elementCount)).get._1
          val relativePlus1 = tuplasMeta.find(_._2 == secuenciaPosible(indexCount)).get._1
          
          if (relativeVal > relativePlus1) inversiones += 1
        }
      }
    }
    
    println("inversiones: " + inversiones)
    var ancho = estadoMeta.Dimension
    var anchoPar = ancho%2 == 0
    var inversionesPar = inversiones%2 == 0
    var blankEnPar =  (ancho - estadoPosible.GetPosicion(0)._1)%2 == 0
    
    if (!anchoPar && inversionesPar) {return true}
    if (anchoPar && blankEnPar && !inversionesPar) {return true}
    if (anchoPar && !blankEnPar && inversionesPar) {return true}
    false
    
  }
  
  
  
  
  
  
  
  
  
  
}