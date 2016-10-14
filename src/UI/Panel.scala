package UI
import Clases.Estado
import javax.swing._
import java.awt.event.ActionEvent
import java.awt.event.ActionListener
import org.netbeans.lib.awtextra._
import java.util.ArrayList;
import java.awt.Color
import sun.awt.DefaultMouseInfoPeer
import Clases.Application
import javax.swing.event.ListSelectionListener
import javax.swing.event.ListSelectionEvent


object PanelP extends JPanel {

  setLayout(new AbsoluteLayout())
  setOpaque(true)
  setBackground(new Color(0,0,0,144))
  
  /*****************************
   * Funciones de la  ventana *
   *****************************/
  
  /**
   * Para poder seleccionar la heuristica
   */
  val CBHeuristica:JComboBox[String] = new JComboBox[String]
  CBHeuristica.setModel(new DefaultComboBoxModel[String])
  (CBHeuristica.getModel()).asInstanceOf[DefaultComboBoxModel[String]].addElement("Manhattan");
  (CBHeuristica.getModel()).asInstanceOf[DefaultComboBoxModel[String]].addElement("TilesOutOf");
  (CBHeuristica.getModel()).asInstanceOf[DefaultComboBoxModel[String]].addElement("MisplacedTiles");
  (CBHeuristica.getModel()).asInstanceOf[DefaultComboBoxModel[String]].addElement("Falta");
  
  /**
   * Para obtener el estado inicial mediante un TextBox
   */
  val LEInicial = new JLabel("Estado inicial: "){setForeground(Color.WHITE)}
  val TBEInicial = new JTextField("1,2,3,4,5,6,7,8,0")
  val BTNInicial = new JButton("Ingresar")
  BTNInicial.addActionListener(new ActionListener(){
    def actionPerformed(e: ActionEvent){
      /*Funcion del boton aqui*/
      Application.IngresarEstadoSolucionar(TBEInicial.getText())
    }})
  
  
  /**
   * Para poder añadir los estados meta
   */
  val LabEstMeta = new JLabel("Estados meta:"){setForeground(Color.WHITE)}
  val ListEstMeta:JList[Estado] = new JList[Estado]
  val SMPanel = new JScrollPane()
  SMPanel.setViewportView(ListEstMeta)
  ListEstMeta.setModel(new DefaultListModel[Estado])
  val BTNAddMeta = new JButton("Añadir estado meta")
  BTNAddMeta.addActionListener(new ActionListener(){
    def actionPerformed(e: ActionEvent){
      /*Funcion del boton aqui*/
      PanelM.Show
    }}) 
  ListEstMeta.addListSelectionListener(new ListSelectionListener(){
    def valueChanged(e: ListSelectionEvent){
      /*Funcion al presionar un elementos de la lista de metas*/
      if (!e.getValueIsAdjusting())
        PanelE.ColocarEstado(ListEstMeta.getSelectedValue())
    }
  })
  
  
  /**
   * Para poder visualizar la solucion
   */
  val LabSolucion = new JLabel("Lista de pasos:"){setForeground(Color.WHITE)}
  var ListSolucion:JList[Estado] = new JList[Estado]
  val SPanel = new JScrollPane()
  SPanel.setViewportView(ListSolucion)
  ListSolucion.setModel(new DefaultListModel[Estado])
  val BTNResolver = new JButton("Solucionar")
  BTNResolver.addActionListener(new ActionListener(){
    def actionPerformed(e: ActionEvent){
      /*Funcion del boton aqui*/
    }})
  ListSolucion.addListSelectionListener(new ListSelectionListener(){
    def valueChanged(e: ListSelectionEvent){
      /*Funcion al presionar un elementos de la lista de soluciones*/
      if (!e.getValueIsAdjusting())
        PanelE.ColocarEstado(ListSolucion.getSelectedValue())
    }
  })
  
  def RemoveAll() = {  
    ListSolucion.removeAll()
    ListEstMeta.removeAll()
  }
  
  def AddEstadoMeta(EstadoM:Estado) = {
    (ListEstMeta.getModel()).asInstanceOf[DefaultListModel[Estado]].addElement(EstadoM);
  }
  
  def AddEstados(Estados:List[Estado]) = {
    ListSolucion.removeAll()
    Estados.foreach { _Estado => 
    (ListSolucion.getModel()).asInstanceOf[DefaultListModel[Estado]].addElement(_Estado)}
  }
  
  /*********************************
   * Colocacion de los componentes *
   *********************************/
  
  add(LEInicial, new AbsoluteConstraints(27,22,90,10))
  add(TBEInicial, new AbsoluteConstraints(25,37,495,25))
  add(BTNInicial, new AbsoluteConstraints(525,37,75,25))
  add(LabEstMeta, new AbsoluteConstraints(27,78,110,10))
  add(SMPanel, new AbsoluteConstraints(28,96,265,160))
  add(BTNAddMeta, new AbsoluteConstraints(25,256,270,25))
  add(LabSolucion, new AbsoluteConstraints(330,78,110,10))
  add(SPanel, new AbsoluteConstraints(331,96,265,160))
  add(BTNResolver, new AbsoluteConstraints(328,256,135,25))
  add(CBHeuristica, new AbsoluteConstraints(464,256,135,25))
  
}

object PanelE extends JPanel{
  setOpaque(true)
  setBackground(new Color(0,0,0))
  def ColocarEstado(_Estado:Estado) = {
    new Tablero(_Estado,this)
  }
}

object PanelA extends JPanel{
  setLayout(new AbsoluteLayout())
  setOpaque(true)
  setBackground(new Color(0,0,0,144))
  val label = new JLabel("Información de la solución:\n"){setForeground(Color.WHITE)}
  add(label, new AbsoluteConstraints(15,15,300,25))
}


object PanelM {

  private val jlabel = new JLabel("Utilice cualquier separador para expresar la secuencia"){setForeground(Color.WHITE)}
  private val jbutton = new JButton("Agregar")
  private val tbSecuencia = new JTextField("1,2,3,4,5,6,7,8,0")
  
  private val jdialog = new JDialog(Window)
  jdialog.setLayout(new AbsoluteLayout())
  jdialog.setTitle("Ingrese un estado meta")
  jdialog.setSize(625,100)
  jdialog.setResizable(false);
  jdialog.setLocationRelativeTo(null)
  
  private val jpanel = new JPanel()
  jpanel.setLayout(new AbsoluteLayout())
  jpanel.setOpaque(true)
  jpanel.setBackground(new Color(0,0,0,144))
  jpanel.add(jlabel, new AbsoluteConstraints(27,15,300,10))
  jpanel.add(tbSecuencia,new AbsoluteConstraints(25,30,495,25))
  jpanel.add(jbutton,new AbsoluteConstraints(525,30,75,25))
  jdialog.add(jpanel, new AbsoluteConstraints(0,0,625,100))
  
  def Show = { jdialog.setVisible(true) }
  
  jbutton.addActionListener(new ActionListener(){
  def actionPerformed(e: ActionEvent){
    jdialog.setVisible(false)
    Application.IngresarEstadoMeta(tbSecuencia.getText())
    ///Application.IngresarEstadoSolucionar(tbSecuencia.getText())
  }})
        
}