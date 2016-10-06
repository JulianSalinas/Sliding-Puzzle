package Clases

class Heuristica {
  
  /** 
   *  Retorna el estimado de Manhattan entre 2 matrices
   *  @param estadoPosible matriz para el que calculamos la heuristica
   *  @param estadoMeta matriz con el estado al que se quiere llegar
   */
  def Manhattan( estadoPosible : Matriz , estadoMeta : Matriz ) : Int = {
    
    var tamano: Int = estadoMeta.Dimension
    var acumulado: Int = 0
    
    for ( count <- 0 to Math.pow(tamano, 2).toInt) {
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
  def TilesOutOf( estadoPosible : Matriz , estadoMeta : Matriz ) : Int = {
    
    var tamano: Int = estadoMeta.Dimension
    var acumulado: Int = 0
    
    for ( count <- 0 to Math.pow(tamano, 2).toInt) {
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
  def MisplacedTiles( estadoPosible : Matriz , estadoMeta : Matriz ) : Int = {
    
    var tamano: Int = estadoMeta.Dimension
    var acumulado: Int = 0
    
    // Peculiaridad de la heuristica -> el for empieza en 1
    for ( count <- 1 to Math.pow(tamano, 2).toInt) {
      val posible = estadoPosible.GetPosicion(count)
      val meta = estadoMeta.GetPosicion(count)      
      // Peculiaridad de la heuristica
      if (posible != meta) acumulado += 1
    }
    
    return acumulado
  }
   
}