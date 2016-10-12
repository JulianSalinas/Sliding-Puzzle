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
  PanelP.setBackground(new Color(0,128,255,144))
  PanelE.setBackground(new Color(0,0,0,210))
  getContentPane().add(PanelE, new AbsoluteConstraints(25,150,300,300))
  getContentPane().add(PanelP, new AbsoluteConstraints(350,150,625,300))
  
  /**
   * Boton cuya funcion es inicializar el tablero
   * Esta arriba del PanelE
   */
  val BotonT = new JButton("Tablero por Default")
  BotonT.addActionListener(new ActionListener(){
  def actionPerformed(e: ActionEvent){NuevoTableroDefault()}})
  getContentPane().add(BotonT, new AbsoluteConstraints(25,125,200,25))
  
  /**
   * Text box donde se escoge el tamaño de la matriz 
   * Esta a la par del BotonT
   */
  val TBDimension = new JSpinner(){this.setValue(9)}
  getContentPane().add(TBDimension, new AbsoluteConstraints(225,125,100,25))
  
  /**
   * Para obtener el estado inicial mediante un TextBox
   */
  val LEInicial = new JLabel("Estado inicial: "){setForeground(Color.WHITE)}
  val TBEInicial = new JTextField("1,2,3,4,5,6,7,8,0")
  
  val BTNInicial = new JButton("Ingresar")
  BTNInicial.addActionListener(new ActionListener(){
  def actionPerformed(e: ActionEvent){NuevoTableroPersonalizado()}})
  
  PanelP.add(LEInicial, new AbsoluteConstraints(27,15,90,10))
  PanelP.add(TBEInicial, new AbsoluteConstraints(25,30,495,25))
  PanelP.add(BTNInicial, new AbsoluteConstraints(525,30,75,25))

  /**
   * Para poder añadir los estados meta
   */
  val LabEstMeta = new JLabel("Estados meta:"){setForeground(Color.WHITE)}
  val ListEstMeta = new JTable()//probar con JList
  
  val BTNAddMeta = new JButton("Añadir estado meta")
  BTNInicial.addActionListener(new ActionListener(){
  def actionPerformed(e: ActionEvent){/*Funcion del boton aqui*/}})
  
  PanelP.add(LabEstMeta, new AbsoluteConstraints(27,65,110,10))
  PanelP.add(ListEstMeta, new AbsoluteConstraints(28,80,265,160))
  PanelP.add(BTNAddMeta, new AbsoluteConstraints(25,240,270,25))
  
  /**
   * Para poder visualizar la solucion
   */
  val LabSolucion = new JLabel("Lista de pasos:"){setForeground(Color.WHITE)}
  val ListSolucion = new JTable()//probar con JList
  
  val BTNResolver = new JButton("Solucionar")
  BTNInicial.addActionListener(new ActionListener(){
  def actionPerformed(e: ActionEvent){/*Funcion del boton aqui*/}})
  
  PanelP.add(LabSolucion, new AbsoluteConstraints(330,65,110,10))
  PanelP.add(ListSolucion, new AbsoluteConstraints(331,80,265,160))
  PanelP.add(BTNResolver, new AbsoluteConstraints(328,240,270,25))
  
  /**
   * Por razones de la vida el fondo se coloca al
   * final para que no tape lo demas
   */
  getContentPane().add(Fondo, new AbsoluteConstraints(0, 0, -1, -1))
  
  
  /***************
   * Miscelaneos *
   ***************/
  
  /**
   * Crear un tablero con un estado por default segun la dimension
   * indicada en TBDimension
   */
  def NuevoTableroDefault(){
    val Dimension = TBDimension.getValue().asInstanceOf[Int]
    val SecuenciaDefault = IniciarSecuenciaDefault(1, Dimension - 1 )
    NuevoTablero(SecuenciaDefault)
  }
  
  /**
   * Crear un tablero con un estado segun el textbox TBEInicial
   */
  def NuevoTableroPersonalizado(){
    NuevoTablero(String2List(TBEInicial.getText()))
  }
  
  /**
   * Crear un tablero y lo coloca en el panel PanelE
   */
  def NuevoTablero(Sec:List[Int]){
    if(IsSecuenciaValida(Sec)){
      PanelE.removeAll()
      PanelE.repaint()
      val Estado = new Estado(Sec)
      new Tablero(Estado,PanelE)
    }
    else MostrarMensaje("Asegurese que los datos son correctos")
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
  private def String2List(_String:String):List[Int]={
    val Regex = """([0-9]+)""".r
    val Matchs = Regex.findAllIn(_String).toList
    return Matchs.map(x => x.toInt) 
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
   * Sirve para que el controlador pueda enviar menseajes al usuario
   * @param Mensaje Mensaje ue se quiere enviar al usuario
   */
  def MostrarMensaje( Mensaje:String ) = {
    JOptionPane.showMessageDialog(
        null, Mensaje, "Mensaje", 
        JOptionPane.INFORMATION_MESSAGE)
  }

}