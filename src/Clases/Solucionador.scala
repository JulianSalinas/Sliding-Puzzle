package Clases

class Solucionador {
  
  /**
   * Se obtiene una lista de estados que representan la solucion.
   * Se debe pasar la funcion heuristica como parametros
   * Se asume que el estado a solucionar tiene solucion con respecto
   * a todos sus metas
   * La UI avisa al usuario si trata de ingresar una secuencia no solucionable
   */
  def ObtenerSolucion ( initState : Estado, EstadosMeta : List[Estado] , heuristica: (Estado, Estado) => Int) : List[Estado] = {
    val MejorMeta = EstadosMeta.reduceLeft((E1,E2) => 
    if(heuristica(initState, E1) < heuristica(initState, E2)) E1 else E2)
    val EstadoFinal = BuscarUltimoEstado(initState, MejorMeta, heuristica)
    return RecrearSolucion(EstadoFinal)
  }
  
  /**
   * Retorna un estado al cual se le pueden obtener los padres para poder
   * recrear la secuencia de estados correspondientes a la solucion
   */
  def BuscarUltimoEstado(
      EstadoInicial : Estado, EstadoMeta : Estado , 
      Heuristica: (Estado, Estado) => Int) : Estado = {   
    
    var Visitados : List[Estado] = List()
    var EstadoActual = EstadoInicial
    
    while (!EstadoActual.EqualsTo(EstadoMeta)) {
      var PesoActual = Heuristica(EstadoActual,EstadoMeta)
      var Subestados = EstadoActual.GetSubEstados(Visitados)
      //.filter { X => Heuristica(X, EstadoMeta) < PesoActual }
      if (!Subestados.isEmpty) EstadoActual = Subestados.reduceLeft((E1,E2) => 
      if ( Heuristica(E1, EstadoMeta) < Heuristica(E2, EstadoMeta) ) E1 else E2)
      else EstadoActual = EstadoActual.Estado_Anterior
      Visitados = Visitados ::: List(EstadoActual)  
    }  
    return EstadoActual
  }

  /**
   * Funcion para evitar ir almacenando y borrando los estados que conforman 
   * la solucion. Con esta se puede obtener la secuencia solucion a partir 
   * de obtener los padres del estado.
   */
  private def RecrearSolucion( Estado: Estado ) : List[Estado] = {
    if(Estado.Estado_Anterior.==(null)) List(Estado)
    else RecrearSolucion(Estado.Estado_Anterior):::List(Estado)
  }
  
}