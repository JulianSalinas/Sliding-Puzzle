package Clases
import UI.Window
import UI.PanelP
import UI.PanelE


object Application extends App{
  
  
  /**
   * MAIN
   */
   Window.setVisible(true)
   var EstadosM:List[Estado] = List()
   var EstadoS:Estado = new Estado(List(2,1,3,4,5,6,7,8,0))
   
   
   /**
    * La UI llama a este metodo indicando por medio de un string la
    * heuristica que se desea usar para solucionar el EstadoS que contiene App
    */
   def Solucionar(StrHeuristica:String): Unit = {
     try{
       Window.MostrarMensaje("Resolviendo con " + StrHeuristica)
       val Heuristica = new Heuristica()
       val Pasos = SolucionarAux(StrHeuristica)
       PanelP.IngresarSolucion(Pasos)
       Window.MostrarMensaje(
           "Solución encontrada en "+Pasos.length.toString()+
           " pasos!\nHas click sobre cada paso para visualizarlo")
     } catch{case e: Exception => { Window.MostrarMensaje("Ha ocurrido un error") }}
   }
   
   /**
    * Obtiene los pasos de la solucion dado el nombre de una heuristica,
    * la heuristica escogida es pasada por parametro al solucionador.
    * Usada unicamente por la funcion Solucionar
    */
   private def SolucionarAux(StrHeu:String): List[Estado] = {
     val Sol = new Solucionador()
     val H = new Heuristica()
     if(StrHeu.==("Manhattan")) Sol.ObtenerSolucion(EstadoS, EstadosM, H.MHT)
     else if (StrHeu.==("TilesOutOf")) Sol.ObtenerSolucion(EstadoS, EstadosM, H.MT)
     else if (StrHeu.==("MisplacedTiles")) Sol.ObtenerSolucion(EstadoS, EstadosM, H.MT)
     else throw new Exception("Heurística todavía sin implementar")
   }
   
   /**
    * La UI hace la solicitud de agregar un estado meta si la secuencia de dicho
    * estado es valida y del mismo tamaño que el estado a solucionar
    */
   def IngresarEstadoMeta(StrSecuencia:String): Unit = {
     try{
       val Sec = String2List(StrSecuencia)
       val Est = new Estado(Sec)
       //Si alguna de estas 3 levanta un error entonces no se coloca el estado meta
         VerificarLargosIguales(Sec)
         VerificarValidezSecuencia(Sec)
         VerificarExistenciaSolucion(Sec)
       EstadosM = EstadosM:::List(Est)
       PanelP.AddEstadoMeta(Est)
       Window.MostrarMensaje("Estado meta cargado correctamente")
     } catch{case e: Exception => { Window.MostrarMensaje(e.getMessage) }} 
   }
   
   //Valida que la secuancia cumpla con todos los requisitos respecto al tamaño y elementos posibles
   private def VerificarValidezSecuencia(Sec:List[Int]) = {
     if(!IsSecuenciaValida(Sec))
     throw new Exception("Secuencia inválida")
   }
   
   //Se verifica que el estado meta tenga inversa respecto al estado a solucionar
   private def VerificarExistenciaSolucion(Sec:List[Int]) = {
     val Est = new Estado(Sec)
     val Heu = new Heuristica()
     if(!Heu.tieneSolucion(EstadoS,Est))
     throw new Exception("Un estado meta tiene que ser válido\nIntente intercambiar un par de valores de la secuencia")
   }
   
   //Respecto a la secuencia del estado a solucionar
   private def VerificarLargosIguales(Sec:List[Int]) = {
     if(EstadoS.Secuencia.length != Sec.length)
     throw new Exception("Un estado meta debe ser del mismo tamaño que el estado a resolver")
   }
   
   /**
    * Es llamado por la interfaz para poder cambiar el estado
    * que se desea solucionar
    * @param String obtenido del TextBox junto al boton Agregar
    */
   def SetEstadoSolucionar(StrSecuencia:String) = {
     val Sec = String2List(StrSecuencia)
     if(IsSecuenciaValida(Sec)){
       EstadosM = List()
       EstadoS = new Estado(Sec)
       PanelE.ColocarEstado(EstadoS)
       Window.MostrarMensaje("Estado a solucionar cambiado a:\n " + EstadoS)
     } else throw new Exception("Secuencia inválida")
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