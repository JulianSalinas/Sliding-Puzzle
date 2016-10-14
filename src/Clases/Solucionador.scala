package Clases

class Solucionador {
  
  val solutionClass = new Heuristica()
  var listaExistentes : List[Estado] = List()
  
  def ObtenerSolucion ( initState : Estado, EstadosMeta : List[Estado] , heuristica: (Estado, Estado) => Int) : List[Estado] = {
       
    val cantidadMetas = EstadosMeta.length
    
    // Verifica si el inicial es una solucion
    if ( EstadosMeta.exists { x => initState.EqualsTo(x) } ) return List(initState)
    
    // Ordenar la lista de estados meta segun su valor de heuristica
    val metasOrdenados : List[Estado] = EstadosMeta.sortWith{(E1,E2) => heuristica(initState, E1) < heuristica(initState, E2) }
    var solucion : List[Estado] = List()
    
    // Buscar camino a solucion para el mejor estado meta
    for (count <- 0 until cantidadMetas){
      if (solutionClass.tieneSolucion(initState, metasOrdenados(count))) {
        //return buscarCamino (listaExistentes, initState, metasOrdenados(count), heuristica, solucion)
        return buscarIterativo(initState, metasOrdenados(count), heuristica)
      }
    }
    return List(new Estado(List(1,1,1,1)))
  }
  
  def buscarCamino ( listaExistentes : List[Estado], estadoActual : Estado, estadoMeta : Estado , 
      heuristica: (Estado, Estado) => Int, solucion : List[Estado]) : List[Estado] = {
    var subEstados = estadoActual.GetSubEstados(listaExistentes)
    
    // Verificamos si llegamos a la solucion
    if (estadoActual.EqualsTo(estadoMeta)) return solucion ::: List(estadoActual)
    
    if (!subEstados.isEmpty){
      var mejorSubEstado = subEstados.reduceLeft(
          (E1,E2) => if ( heuristica(E1, estadoMeta) < heuristica(E2, estadoMeta) ) E1 else E2)
      return buscarCamino(listaExistentes ::: List(estadoActual), mejorSubEstado, estadoMeta, heuristica, solucion ::: List(estadoActual))
    }
    else {
      return buscarCamino(listaExistentes ::: List(estadoActual), estadoActual.Estado_Anterior, estadoMeta, heuristica, solucion.dropRight(1))
    }
    
    return List()
  }
  
  def buscarIterativo ( estadoInicial : Estado, estadoMeta : Estado , heuristica: (Estado, Estado) => Int) : List[Estado]= {
    
    var listaE : List[Estado] = List()
    var estadoActual = estadoInicial
    var solucion : List[Estado] = List()
    var cantidad = 0
    println("inicio")
    while (true) {
      var subEstados = estadoActual.GetSubEstados(listaE)
      if (estadoActual.EqualsTo(estadoMeta)) {
        //solucion = solucion ::: List(estadoActual)
        println(estadoActual)
        cantidad+=1
        println(cantidad)
        return solucion
      }
      
      if (!subEstados.isEmpty){
        var mejorSubEstado = subEstados.reduceLeft(
          (E1,E2) => if ( heuristica(E1, estadoMeta) < heuristica(E2, estadoMeta) ) E1 else E2)
        listaE = listaE ::: List(estadoActual)        
        //solucion = solucion ::: List(estadoActual)
        //println(estadoActual)
        cantidad+=1
        estadoActual = mejorSubEstado
      }
      else {
        listaE = listaE ::: List(estadoActual)
        estadoActual = estadoActual.Estado_Anterior
        //solucion = solucion.dropRight(1)
      }
    } 
    return List()
  }

  
}