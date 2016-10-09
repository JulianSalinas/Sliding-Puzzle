package UI
import scala.swing._
import javax.swing._
import javax.swing.UIManager.setLookAndFeel;
import javax.swing.SpringLayout.Constraints
import java.awt.event.ActionEvent
import java.awt.event.ActionListener
import com.sun.org.apache.xalan.internal.xsltc.compiler.AbsolutePathPattern

object MainWindow extends MainFrame{
  
  val Ancho = 1000
  val Alto = 600
  
  AjustarVentana()
  ColocarTema()
  ColocarPaneles()
  ColocarComponentes()

  def AjustarVentana() = {
    this.title = "Sliding-Puzzle"
    this.size = new Dimension(Ancho,Alto)
    this.preferredSize = new Dimension(Ancho,Alto)
  }
  
  def ColocarTema() = {
    JFrame.setDefaultLookAndFeelDecorated(true)
    try{setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel")}
    catch{case e: Exception => {MostrarMensaje(e.getMessage)}}
  }

  def ColocarPaneles() = {
    
  }
  
  def ColocarComponentes() = {
    
    val panel = new BoxPanel(Orientation.Horizontal){
      background = new Color(154,120,121)
      preferredSize = new Dimension(Alto,Ancho)
    }
    
  }
  
  def MostrarMensaje( Mensaje:String ) = {
    JOptionPane.showMessageDialog(
        null, Mensaje, "Mensaje", 
        JOptionPane.INFORMATION_MESSAGE)
  }

}