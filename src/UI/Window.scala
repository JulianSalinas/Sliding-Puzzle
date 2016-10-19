package UI
import Clases.Estado
import scala.swing._
import javax.swing._
import javax.swing.UIManager.setLookAndFeel;
import java.awt.event.ActionEvent
import java.awt.event.ActionListener
import java.awt.Color


object Window extends JFrame{
  
  /*****************************
   * Propiedades de la ventana *
   *****************************/
  
  val Ancho = 1005
  val Alto = 550
  val Fondo = new JLabel()
  val imagen = new ImageIcon(getClass().getResource("/UI/Wallpaper5.jpg"))
  Fondo.setIcon(imagen)
  Fondo.setSize(getSize())

  setTitle("Sliding-Puzzle")
  setSize(Ancho,Alto)
  setResizable(false);
  setLocationRelativeTo(null)
  getContentPane().setLayout(null)
  
  try{setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel")}
  catch{case e: Exception => { MostrarMensaje(e.getMessage) }}

  
  def MostrarMensaje( Mensaje:String ) = {
    JOptionPane.showMessageDialog(
        null, Mensaje, "Mensaje", 
        JOptionPane.INFORMATION_MESSAGE)
  }
  
  PanelE.setBounds(25,50,300,300)
  PanelP.setBounds(350,50,625,300)
  PanelA.setBounds(25,375,950,100)
  Fondo.setBounds(0, 0, Ancho, Alto)
  getContentPane().add(PanelE)
  getContentPane().add(PanelP)
  getContentPane().add(PanelA)
  getContentPane().add(Fondo)

}