package Clases

class ObtenerLista {
  var lista:List[Int]=List()
  
  def String_to_list(dato:String):List[Int]={
    val number="""([0-9]+)""".r
    for (i<-number.findAllIn(dato).toList){
      lista=lista:::List(i.toInt)
    }
    return lista
  }
  /*
   * 
   * Verifica que los numeros esten en en el rango
   * 
   */
  def EstaEnRango():Boolean={
    for(i <- 0 to lista.length-1){
      if( !lista.contains(i)){
        return false
      }
    }
    return true
  }
  /*
   * 
   * Verifica si se puede generar la matrix NxN
   * Verifica que los numeros esten en el rango
   * 
   * 
   */
  def verificar():Boolean={
    val exprecion1 =(Math.sqrt(lista.length) % 1 == 0)
    val exprecion2= EstaEnRango()
    return (exprecion1 && exprecion2)
  }
}