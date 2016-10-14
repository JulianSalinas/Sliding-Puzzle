package Clases
import UI.Window
import UI.PanelP
import UI.PanelE


object Application extends App{
  
  
  /**
   * MAIN
   */
   Window.setVisible(true)

   
   /**
    * La UI llama a este metodo al querer setear el estado a solucionar
    */
   def IngresarEstadoSolucionar(StrSecuencia:String): Unit = {
     try{
       val Sec = String2List(StrSecuencia)
       if(IsSecuenciaValida(Sec)){
         val Est = new Estado(Sec)
         PanelE.ColocarEstado(Est)
       }
       else throw new Exception("Secuencia inválida")
     }
     catch{case e: Exception => { Window.MostrarMensaje(e.getMessage) }}
   }
    
   /**
    * Se agregar un estado meta usando la UI
    */
   def IngresarEstadoMeta(StrSecuencia:String): Unit = {
     try{
       val Sec = String2List(StrSecuencia)
       if(IsSecuenciaValida(Sec)){
         //Falta validar que es el mismo tamaño que la que se va a solucionar
         val Est = new Estado(Sec)
         PanelP.AddEstadoMeta(Est)
       }
       else throw new Exception("Secuencia inválida")
     }
     catch{case e: Exception => { Window.MostrarMensaje(e.getMessage) }}
     
   }
   
   /**
   * Crea una lista de numeros consecutivos pero dejando de ultimo el 0
   * @param Inicio Numero con el que se inicia la secuancia
   * @param Fin Ultimo numero antes del 0
   */
  private def IniciarSecuenciaDefault(Inicio: Int, Fin:Int) : List[Int] = {
    if(Inicio > Fin) List(0) else{
      var Inicio_2 = Inicio + 1
      List(Inicio):::IniciarSecuenciaDefault(Inicio_2,Fin)
    }
  } 
   
   /**
   * Verifica que se pueda generar la matrix NxN
   * y a la vez que posea todos los numeros en dicho rango
   * @param Lista 
   */
  private def IsSecuenciaValida(Sec:List[Int]):Boolean={
    val expresion_1 =(Math.sqrt(Sec.length) % 1 == 0)
    val expresion_2 = HasAll(Sec)
    val result = expresion_1 && expresion_2
    return result
  }
   
   /**
   * Toma un string y lo convierte a una lista de enteros
   * No importa si posee puntos, comas o cualquier simbolo como separados
   * @param String Lo que se quiere convertir a una lista de enteros
   */
  private def String2List(_String:String):List[Int]={
    val Regex = """([0-9]+)""".r
    val Matchs = Regex.findAllIn(_String).toList
    return Matchs.map(x => x.toInt) 
  }
  
  
  /**
   * Verifica que el estado tenga todos los numeros que deberia de tener
   * @param Lista 
   */
  private def HasAll(Sec:List[Int]): Boolean = {
    for(i <- 0 to Sec.length-1)
      if( !Sec.contains(i)) {return false}
    return true
  }
  
}