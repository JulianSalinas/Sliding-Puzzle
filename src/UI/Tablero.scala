package UI
import Clases.Estado
import scala.swing._
import javax.swing._
import javax.swing.UIManager.setLookAndFeel;
import org.netbeans.lib.awtextra._

class Tablero (_Estado: Estado, _Panel: JPanel){
  
  val Estado = _Estado
  val Panel = _Panel
  val Dimension = Estado.Dimension
  val TFicha = Panel.getSize().width/Dimension;
  
  for(i <- 0 to Estado.Dimension - 1)
    for(j <- 0 to Estado.Dimension - 1){
      var Ficha = new Ficha(Estado,(i,j))
      Panel.add(Ficha)
      Ficha.setBounds((TFicha*j),(TFicha*i), TFicha, TFicha)
      Ficha.setVisible(true)
      Ficha.repaint()
    }
  
}