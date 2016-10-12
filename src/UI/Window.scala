package UI
import Clases.Estado
import scala.swing._
import javax.swing._
import javax.swing.UIManager.setLookAndFeel;
import java.awt.event.ActionEvent
import java.awt.event.ActionListener
import org.netbeans.lib.awtextra._
import java.awt.Color


object MainWindow extends JFrame{
  
  /*****************************
   * Propiedades de la ventana *
   *****************************/
  
  val Ancho = 1005
  val Alto = 600
  val Fondo = new JLabel()
  val imagen = new ImageIcon(getClass().getResource("/UI/Fondo.jpg"))
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
   * Text box donde se escoge el tamaño de la matriz 
   * Esta a la par del BotonT
   */
  val TBDimension = new JSpinner(){this.setValue(9)}
  
  /**
   * Boton cuya funcion es inicializar el tablero
   * Esta arriba del PanelE
   */
  val BotonT = new JButton("Tablero por Default")
  BotonT.addActionListener(new ActionListener(){
  def actionPerformed(e: ActionEvent){PanelE.NuevoTableroDefault(TBDimension.getValue().asInstanceOf[Int])}})
  
  
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
  
  getContentPane().add(PanelE, new AbsoluteConstraints(25,150,300,300))
  getContentPane().add(PanelP, new AbsoluteConstraints(350,150,625,300))
  getContentPane().add(BotonT, new AbsoluteConstraints(25,125,200,25))
  getContentPane().add(TBDimension, new AbsoluteConstraints(225,125,100,25))
  getContentPane().add(Fondo, new AbsoluteConstraints(0, 0, -1, -1))

}