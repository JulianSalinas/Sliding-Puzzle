package UI
import Clases.Estado
import scala.swing._
import javax.swing._
import javax.swing.UIManager.setLookAndFeel;
import java.awt.event.ActionEvent
import java.awt.event.ActionListener
import org.netbeans.lib.awtextra._
import java.awt.Color

object PanelP extends JPanel {
  
  setLayout(new AbsoluteLayout())
  setOpaque(true)
  setBackground(new Color(0,128,255,144))
  
  /**
   * Para obtener el estado inicial mediante un TextBox
   */
  val LEInicial = new JLabel("Estado inicial: "){setForeground(Color.WHITE)}
  val TBEInicial = new JTextField("1,2,3,4,5,6,7,8,0")
  
  val BTNInicial = new JButton("Ingresar")
  BTNInicial.addActionListener(new ActionListener(){
  def actionPerformed(e: ActionEvent){/**/}})
  
  add(LEInicial, new AbsoluteConstraints(27,15,90,10))
  add(TBEInicial, new AbsoluteConstraints(25,30,495,25))
  add(BTNInicial, new AbsoluteConstraints(525,30,75,25))
  
  /**
   * Para poder añadir los estados meta
   */
  val LabEstMeta = new JLabel("Estados meta:"){setForeground(Color.WHITE)}
  val ListEstMeta = new JTable()//probar con JList
  
  val BTNAddMeta = new JButton("Añadir estado meta")
  BTNInicial.addActionListener(new ActionListener(){
  def actionPerformed(e: ActionEvent){/*Funcion del boton aqui*/}})
  
  add(LabEstMeta, new AbsoluteConstraints(27,65,110,10))
  add(ListEstMeta, new AbsoluteConstraints(28,80,265,160))
  add(BTNAddMeta, new AbsoluteConstraints(25,240,270,25))
  
  /**
   * Para poder visualizar la solucion
   */
  val LabSolucion = new JLabel("Lista de pasos:"){setForeground(Color.WHITE)}
  val ListSolucion = new JTable()//probar con JList
  
  val BTNResolver = new JButton("Solucionar")
  BTNInicial.addActionListener(new ActionListener(){
  def actionPerformed(e: ActionEvent){/*Funcion del boton aqui*/}})
  
  add(LabSolucion, new AbsoluteConstraints(330,65,110,10))
  add(ListSolucion, new AbsoluteConstraints(331,80,265,160))
  add(BTNResolver, new AbsoluteConstraints(328,240,270,25))
  
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
  
}