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
  def BuscarUltimoEstado( estadoInicial : Estado, estadoMeta : Estado , heuristica: (Estado, Estado) => Int) : Estado = {   
    var listaE : List[Estado] = List()
    var estadoActual = estadoInicial
    
    while (!estadoActual.EqualsTo(estadoMeta)) {
      var subEstados = estadoActual.GetSubEstados(listaE)
 
      /*Se selecciona el mejor estado por medio de la heuristica*/     
      if (!subEstados.isEmpty){
        estadoActual = subEstados.reduceLeft(
          (E1,E2) => if ( heuristica(E1, estadoMeta) < heuristica(E2, estadoMeta) ) E1 else E2)
        listaE = listaE ::: List(estadoActual)  
      }
      
      /* Si el estado no tenia hijos validos entonces solo se agrega
       * el estado como visitado y se devuelve a buscar en otros hijos del padre*/   
      else {
        listaE = listaE ::: List(estadoActual)
        estadoActual = estadoActual.Estado_Anterior
      } 
      
    }  
    return estadoActual
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