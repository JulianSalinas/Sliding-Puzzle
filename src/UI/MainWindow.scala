package UI
import Clases.Estado
import scala.swing._
import javax.swing._
import javax.swing.UIManager.setLookAndFeel;
import java.awt.event.ActionEvent
import java.awt.event.ActionListener
import org.netbeans.lib.awtextra._


object MainWindow extends JFrame{
  
  /*****************************
   * Propiedades de la ventana *
   *****************************/
  
  val Ancho = 1005
  val Alto = 600
  val Fondo = new JLabel()
  val imagen = new ImageIcon(getClass().getResource("/UI/fondo.jpg"))
  Fondo.setIcon(imagen)
  Fondo.setSize(getSize())

  setTitle("Sliding-Puzzle")
  setSize(Ancho,Alto)
  setResizable(false);
  setLocationRelativeTo(null)
  getContentPane().setLayout(new AbsoluteLayout())
  
  try{setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel")}
  catch{case e: Exception => { MostrarMensaje(e.getMessage) }}
  
  
  /*********************************
   * Colocacion de los componentes *
   *********************************/
  
  /**
   * Paneles principales 
   * PanelE = Donde se ve el estado a solucionar
   * PanelP = Principal, donde se ven las opciones
   */
  var PanelE = new JPanel(){this.setBackground(new Color(0,128,255))}
  var PanelP = new JPanel(){this.setBackground(new Color(0,128,255))}
  PanelP.setLayout(new AbsoluteLayout())
  PanelP.setOpaque(true)
  PanelE.setOpaque(true)
  PanelP.setBackground(new Color(0,0,0,144))
  PanelE.setBackground(new Color(0,0,0,144))
  getContentPane().add(PanelE, new AbsoluteConstraints(25,150,300,300))
  getContentPane().add(PanelP, new AbsoluteConstraints(350,150,625,300))
  
  /**
   * Boton cuya funcion es inicializar el tablero
   * Esta arriba del PanelE
   */
  val BotonT = new JButton("Tablero por Default")
  BotonT.addActionListener(new ActionListener(){
  def actionPerformed(e: ActionEvent){NuevoTablero()}})
  getContentPane().add(BotonT, new AbsoluteConstraints(25,125,200,25))
  
  /**
   * Text box donde se escoge el tamaño de la matriz 
   * Esta a la par del BotonT
   */
  val TBDimension = new JSpinner(){this.setValue(9)}
  getContentPane().add(TBDimension, new AbsoluteConstraints(225,125,100,25))
  
  /**
   * Por razones de la vida el fondo se coloca al
   * final para que no tape lo demas
   */
  getContentPane().add(Fondo, new AbsoluteConstraints(0, 0, -1, -1))
  
  /**
   * Para obtener el estado inicial mediante un TextBox
   */
  val LEInicial = new JLabel("Estado inicial: "){setBackground(new Color(0,0,0))}
  val TBEInicial = new JTextField("1,2,3,4,5,6,7,8,0")
  PanelP.add(LEInicial, new AbsoluteConstraints(15,15,100,10))
  PanelP.add(TBEInicial, new AbsoluteConstraints(15,30,200,25))
  
  /***************
   * Miscelaneos *
   ***************/
  
  /**
   * Crear un tablero con un estado por default segun la dimension
   * indicada en TBDimension
   */
  def NuevoTablero(){
    val Dimension = TBDimension.getValue().asInstanceOf[Int]
    if(Math.sqrt(Dimension) * Math.sqrt(Dimension) == Dimension){
      PanelE.removeAll()
      PanelE.repaint()
      val SecuenciaDefault = IniciarSecuenciaDefault(1, Dimension - 1 )
      val EstadoDefault = new Estado(SecuenciaDefault)
      new Tablero(EstadoDefault,PanelE)
    }
    else MostrarMensaje("Asegurese que se puede crea una tablero con dicha dimensión")
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
   * Toma un string y lo convierte a una lista de enteros
   * No importa si posee puntos, comas o cualquier simbolo como separados
   * @param String Lo que se quiere convertir a una lista de enteros
   */
  def String2List(_String:String):List[Int]={
    val Regex = """([0-9]+)""".r
    val Matchs = Regex.findAllIn(_String).toList
    return Matchs.map(x => x.toInt) 
  }

  /**
   * Sirve para que el controlador pueda enviar menseajes al usuario
   * @param Mensaje Mensaje ue se quiere enviar al usuario
   */
  def MostrarMensaje( Mensaje:String ) = {
    JOptionPane.showMessageDialog(
        null, Mensaje, "Mensaje", 
        JOptionPane.INFORMATION_MESSAGE)
  }

}