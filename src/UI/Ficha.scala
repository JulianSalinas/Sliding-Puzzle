package UI
import Clases.Estado
import scala.swing._
import javax.swing._
import javax.swing.UIManager.setLookAndFeel;
import org.netbeans.lib.awtextra._

class Ficha( _Estado: Estado,_Posicion:(Int,Int)) extends JTextField {
  val Fila = _Posicion._1
  val Columna = _Posicion._2
  val Estado = _Estado
  
  setText(Estado.GetElemento(Fila, Columna).toString());
  
}