package UI
import Clases.Estado
import javax.swing._
import java.awt.event.ActionEvent
import java.awt.event.ActionListener
import java.util.ArrayList;
import java.awt.Color
import java.awt.Font
import sun.awt.DefaultMouseInfoPeer
import Clases.Application
import javax.swing.event.ListSelectionListener
import javax.swing.event.ListSelectionEvent


object PanelP extends JPanel {
  
  setLayout(null)
  setOpaque(true)
  setBackground(new Color(0,0,0,144))
  
  /**
   * Para poder seleccionar la heuristica
   */
  val CBHeuristica:JComboBox[String] = new JComboBox[String]
  CBHeuristica.setModel(new DefaultComboBoxModel[String])
  (CBHeuristica.getModel()).asInstanceOf[DefaultComboBoxModel[String]].addElement("Manhattan");
  (CBHeuristica.getModel()).asInstanceOf[DefaultComboBoxModel[String]].addElement("TilesOutOf");
  (CBHeuristica.getModel()).asInstanceOf[DefaultComboBoxModel[String]].addElement("MisplacedTiles");
  
  /**
   * Para obtener el estado inicial mediante un TextBox
   * Primero se debe vaciar la lista de metas y pasos al momento
   * de colocar un nuevo estado a solucionar
   */
  val LEInicial = new JLabel("Estado inicial: "){setForeground(Color.WHITE)}
  val TBEInicial = new JTextField("1 0 2 3 4 5 6 7 8")
  val BTNInicial = new JButton("Ingresar")
  BTNInicial.addActionListener(new ActionListener(){
    def actionPerformed(e: ActionEvent){
      ListEstMeta.setModel(new DefaultListModel[Estado])
      ListSolucion.setModel(new DefaultListModel[Estado])
      ListEstMeta.repaint()
      ListSolucion.repaint()
      Application.SetEstadoSolucionar(TBEInicial.getText())
    }})
  
  
  /**
   * Abre un jdialog para poder añadir estados meta
   * dentro de una lista en la clase App
   * Esta lista esta dentro del controlador.
   */
  val LabEstMeta = new JLabel("Estados meta:"){setForeground(Color.WHITE)}
  val ListEstMeta:JList[Estado] = new JList[Estado]
  val SMPanel = new JScrollPane()
  SMPanel.setViewportView(ListEstMeta)
  ListEstMeta.setModel(new DefaultListModel[Estado])
  val BTNAddMeta = new JButton("Añadir estado meta")
  BTNAddMeta.addActionListener(new ActionListener(){
  def actionPerformed(e: ActionEvent){PanelM.Show}})
  
  /**
   * El boton toma el string del Combobox de heuristicas para 
   * asi llamar al object app y resolver el puzzle con dicha heuristica
   */
  val LabSolucion = new JLabel("Lista de pasos:"){setForeground(Color.WHITE)}
  var ListSolucion:JList[Estado] = new JList[Estado]
  val SPanel = new JScrollPane()
  SPanel.setViewportView(ListSolucion)
  ListSolucion.setModel(new DefaultListModel[Estado])
  val BTNResolver = new JButton("Solucionar")
  BTNResolver.addActionListener(new ActionListener(){
  def actionPerformed(e: ActionEvent){Application.Solucionar(CBHeuristica.getSelectedItem().toString())}})
  
  /**
   * Agrega un evento al Jlist de pasos (o solucion) para que al momento
   * de tocar un elemento lo reproduzca en la matriz de la UI
   */
  ListSolucion.addListSelectionListener(new ListSelectionListener(){
    def valueChanged(e: ListSelectionEvent){
      if (!e.getValueIsAdjusting()) PanelE.ColocarEstado(ListSolucion.getSelectedValue())}})

  /**
   * Agrega un evento al Jlist de estados meta para que al momento
   * de tocar un elemento lo reproduzca en la matriz de la UI
   */
  ListEstMeta.addListSelectionListener(new ListSelectionListener(){
    def valueChanged(e: ListSelectionEvent){
      if (!e.getValueIsAdjusting()) PanelE.ColocarEstado(ListEstMeta.getSelectedValue())}
  })
  
  /**
   * Funcion llamada por el objecto App para colocar un Estado en el JList
   */
  def AddEstadoMeta(EstadoM:Estado) = {
    (ListEstMeta.getModel()).asInstanceOf[DefaultListModel[Estado]].addElement(EstadoM);
  }
  
  /**
   * Funcion llamada por el objecto App para colocar los estados que 
   * conforman la solucion en un JList
   */
  def IngresarSolucion(Estados:List[Estado]) = {
    ListSolucion.setModel(new DefaultListModel[Estado])
    ListSolucion.removeAll()
    Estados.foreach { _Estado => 
    (ListSolucion.getModel()).asInstanceOf[DefaultListModel[Estado]].addElement(_Estado)}
  }
  
  /**
   * Colocacion de cada unos de los componentes declarados
   */
  
  LEInicial.setBounds(27,22,90,10)
  TBEInicial.setBounds(25,37,495,25)
  BTNInicial.setBounds(525,37,75,25)
  LabEstMeta.setBounds(27,78,110,10)
  SMPanel.setBounds(28,96,265,160)
  BTNAddMeta.setBounds(25,256,270,25)
  LabSolucion.setBounds(330,78,110,10)
  SPanel.setBounds(331,96,265,160)
  BTNResolver.setBounds(328,256,135,25)
  CBHeuristica.setBounds(464,256,135,25)
  
  add(LEInicial)
  add(TBEInicial)
  add(BTNInicial)
  add(LabEstMeta)
  add(SMPanel)
  add(BTNAddMeta)
  add(LabSolucion)
  add(SPanel)
  add(BTNResolver)
  add(CBHeuristica)
  
}


/**
 * Panel donde se visualizan los estados seleccionados
 * en la interfaz grafica 
 */
object PanelE extends JPanel{
  setOpaque(true)
  setBackground(new Color(0,0,0))
  def ColocarEstado(_Estado: Estado) = {
    if(_Estado.!=(null)) new Tablero(_Estado,this)
  }
}


/**
 * Panel donde se muestran los detalles de la solucion este panel 
 * es pasado como parametro a una clase Tablero presente en el 
 * archivo Componente para que pinte la matriz correspondiente
 */
object PanelA extends JPanel{
  setLayout(null)
  setOpaque(true)
  setBackground(new Color(0,0,0,144))
  val label1 = new JLabel("TECNOLÓGICO DE COSTA RICA"){
    setForeground(Color.WHITE)
    setFont(new Font("Jokerman", Font.PLAIN, 14))
  }
  val label2 = new JLabel("HECHO POR: "){
    setForeground(Color.WHITE)
    setFont(new Font("Jokerman", Font.PLAIN, 12))
  }
  val label3 = new JLabel("JULIAN SALINAS, BRANDON DINARTE, OLMAN CASTILLO"){
    setForeground(Color.WHITE)
    setFont(new Font("Jokerman", Font.PLAIN, 16))
  }
  label1.setBounds(15,15,300,25)
  label2.setBounds(15,35,300,25)
  label3.setBounds(15,55,500,25)
  add(label1)
  add(label2)
  add(label3)
}

/**
 * JDialog que pregunta al usuario la secuencia que desea
 * añadir como estado meta
 */

object PanelM {

  private val jlabel = new JLabel("Utilice cualquier separador para expresar la secuencia"){setForeground(Color.WHITE)}
  private val jbutton = new JButton("Agregar")
  private val tbSecuencia = new JTextField("1,2,3,4,5,6,7,8,0")
  
  private val jdialog = new JDialog(Window)
  jdialog.setLayout(null)
  jdialog.setTitle("Ingrese un estado meta")
  jdialog.setSize(625,100)
  jdialog.setResizable(false);
  jdialog.setLocationRelativeTo(null)
  
  private val jpanel = new JPanel()
  jpanel.setLayout(null)
  jpanel.setOpaque(true)
  jpanel.setBackground(new Color(0,0,0,144))
  jlabel.setBounds(27,15,300,10)
  tbSecuencia.setBounds(25,30,495,25)
  jbutton.setBounds(525,30,75,25)
  jpanel.setBounds(0,0,625,100)
  jpanel.add(jlabel)
  jpanel.add(tbSecuencia)
  jpanel.add(jbutton)
  jdialog.add(jpanel)
  
  def Show = { jdialog.setVisible(true) }
  
  jbutton.addActionListener(new ActionListener(){
  def actionPerformed(e: ActionEvent){
    jdialog.setVisible(false)
    Application.IngresarEstadoMeta(tbSecuencia.getText())
  }})
        
}