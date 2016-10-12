package UI
import Clases.Estado
import scala.swing._
import javax.swing._
import javax.swing.UIManager.setLookAndFeel;
import java.awt.event.ActionEvent
import java.awt.event.ActionListener
import org.netbeans.lib.awtextra._
import java.awt.Color


object PanelE extends JPanel{
  
  setOpaque(true)
  setBackground(new Color(0,0,0,210))
  
  /**
   * Crear un tablero con un estado por default segun la dimension
   * indicada en TBDimension
   */
  def NuevoTableroDefault(_Dimension:Int) = {
    val Dimension = _Dimension
    val SecuenciaDefault = IniciarSecuenciaDefault(1, Dimension - 1 )
    NuevoTablero(SecuenciaDefault)
  }
  
  /**
   * Crear un tablero y lo coloca en el panel PanelE
   */
  def NuevoTablero(Sec:List[Int]) = {
    if(IsSecuenciaValida(Sec)){
      val Estado = new Estado(Sec)
      new Tablero(Estado,this)
    }
    else MainWindow.MostrarMensaje("Asegurese que los datos son correctos")
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
   * Verifica que el estado tenga todos los numeros que deberia de tener
   * @param Lista 
   */
  private def HasAll(Sec:List[Int]): Boolean = {
    for(i <- 0 to Sec.length-1)
      if( !Sec.contains(i)) {return false}
    return true
  }
  
}