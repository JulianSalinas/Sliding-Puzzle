package UI
import Clases.Estado
import scala.swing._
import javax.swing._
import javax.swing.UIManager.setLookAndFeel;
import java.awt.event.ActionEvent
import java.awt.event.ActionListener
import org.netbeans.lib.awtextra._
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
  getContentPane().setLayout(new AbsoluteLayout())
  
  try{setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel")}
  catch{case e: Exception => { MostrarMensaje(e.getMessage) }}
  
  /*****************************
   * Funciones de la  ventana *
   *****************************/
  
  
  /**
   * Sirve para que el controlador pueda enviar menseajes al usuario
   * @param Mensaje Mensaje ue se quiere enviar al usuario
   */
  def MostrarMensaje( Mensaje:String ) = {
    JOptionPane.showMessageDialog(
        null, Mensaje, "Mensaje", 
        JOptionPane.INFORMATION_MESSAGE)
  }

  /*********************************
   * Colocacion de los componentes *
   *********************************/
  
  getContentPane().add(PanelE, new AbsoluteConstraints(25,50,300,300))
  getContentPane().add(PanelP, new AbsoluteConstraints(350,50,625,300))
  getContentPane().add(PanelA, new AbsoluteConstraints(25,375,950,100))
  getContentPane().add(Fondo, new AbsoluteConstraints(0, 0, -1, -1))

}