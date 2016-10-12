package UI
import Clases.Estado
import scala.swing._
import javax.swing._
import javax.swing.UIManager.setLookAndFeel;
import java.awt.event.ActionEvent
import java.awt.event.ActionListener
import org.netbeans.lib.awtextra._
import java.awt.Color
import java.awt.Font

class Tablero (_Estado: Estado, _Panel: JPanel){
  
  val Estado = _Estado
  val Panel = _Panel
  val Dimension = Estado.Dimension
  val TFicha = Panel.getSize().width/Dimension;
  
  Panel.removeAll()
  
  for(i <- 0 to Estado.Dimension - 1)
    for(j <- 0 to Estado.Dimension - 1){
      var Ficha = new Ficha(Estado,(i,j))
      Panel.add(Ficha.Comp)
      Ficha.Comp.setBounds((TFicha*j),(TFicha*i), TFicha, TFicha)
    } 
  
  Panel.repaint()
  
}

class Ficha( _Estado: Estado,_Posicion:(Int,Int)){
  
  val Fila = _Posicion._1
  val Columna = _Posicion._2
  val Estado = _Estado
  val Numero = Estado.GetElemento(Fila, Columna)
  var Comp : JComponent = null
  
  if(Numero != 0) Comp = new JButton(Numero.toString())
  else Comp = new JLabel()
  
  if(Comp.isInstanceOf[JButton]){
    var C = Comp.asInstanceOf[JButton]
    C.setHorizontalAlignment(SwingConstants.CENTER)
    C.setFont(new Font("Jokerman", Font.PLAIN, 25))
    C.setText(Estado.GetElemento(Fila, Columna).toString())
  }
  
}
