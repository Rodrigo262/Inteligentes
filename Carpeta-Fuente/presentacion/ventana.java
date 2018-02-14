package presentacion;


import modelo.EspacioDeEstados;
import modelo.Estado;
import modelo.Nodo;
import modelo.Problema;
import modelo.Puzzle;
import java.awt.EventQueue;
import javax.imageio.ImageIO;
import javax.swing.JFrame;
import java.awt.GridLayout;

import javax.swing.Icon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.BorderLayout;
import javax.swing.JPanel;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JFormattedTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Stack;
import java.awt.Toolkit;
import java.awt.Color;
import javax.swing.border.TitledBorder;
import javax.swing.text.MaskFormatter;

//import com.sun.org.apache.bcel.internal.util.ClassPath;

//import sun.util.locale.provider.AuxLocaleProviderAdapter;

import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;
import java.awt.event.HierarchyListener;
import java.awt.event.HierarchyEvent;
import java.awt.image.BufferedImage;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeEvent;
import java.awt.event.InputMethodListener;
import java.awt.event.InputMethodEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;


public class ventana {

	private JFrame frmSistemasInteligentes;
	private JPanel panel;
	private JButton btnAbrirOriginal;
	private JButton btnAbrirModificado;
	private JLabel lblEstrategia;
	private JComboBox cbEstrategia;
	private JTextField txtPuzzle;
	private JTextField txtModificado;
	private JPanel panel_1;
	private JButton btnAnterior;
	private JButton btnSiguiente;
	private JLabel lblSolucion;
	private JLabel lblNmeroDeColumnas;
	private JComboBox cbCols;
	private JLabel lblNmeroDeFilas;
	private JComboBox cbRows;
	private JButton btnSExplorar;
	private String prof_max="99999";
	private String inc_cota="99999";
	private static String cols = "1";
	private static String rows = "1";
	private String path_original = "";
	private String path_modificado = "";
	private String estrategia = "";
	private Problema problema=null;
	private JLabel lblProfunidad;
	private JLabel lblCota;
	private JTextField txtfCota;
	private JTextField txtfProfunidad;
	private JLabel lblMensajes;
	private ArrayList<Estado> solution;
	private ArrayList<Estado> solution2;
	private int count=0;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ventana window = new ventana();
					window.frmSistemasInteligentes.setVisible(true);
					//crearPuzzle();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public ventana() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmSistemasInteligentes = new JFrame();
		frmSistemasInteligentes.setResizable(false);
		frmSistemasInteligentes.setIconImage(Toolkit.getDefaultToolkit().getImage(ventana.class.getResource("/presentacion/window_icon.png")));
		frmSistemasInteligentes.setTitle("Sistemas Inteligentes");
		frmSistemasInteligentes.setBounds(100, 100, 881, 684);
		frmSistemasInteligentes.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmSistemasInteligentes.getContentPane().setLayout(new BorderLayout(0, 0));
		{
			panel = new JPanel();
			frmSistemasInteligentes.getContentPane().add(panel, BorderLayout.CENTER);
			GridBagLayout gbl_panel = new GridBagLayout();
			gbl_panel.columnWidths = new int[]{20, 0, 30, 0, 65, 15, 0, 40, 15, 0, 40, 0, 0, 20, 0};
			gbl_panel.rowHeights = new int[]{20, 0, 0, 0, 0, 30, 30, 0, 10, 0, 0, 0, 0};
			gbl_panel.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0, Double.MIN_VALUE};
			gbl_panel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0, 0.0, Double.MIN_VALUE};
			panel.setLayout(gbl_panel);
			{
				btnAbrirOriginal = new JButton("Abrir puzzle");
				btnAbrirOriginal.addActionListener(new BtnAbrirImagen());
				btnAbrirOriginal.setFont(new Font("Dialog", Font.PLAIN, 11));
				btnAbrirOriginal.setHorizontalAlignment(SwingConstants.LEFT);
				GridBagConstraints gbc_btnAbrirOriginal = new GridBagConstraints();
				gbc_btnAbrirOriginal.fill = GridBagConstraints.HORIZONTAL;
				gbc_btnAbrirOriginal.insets = new Insets(0, 0, 5, 5);
				gbc_btnAbrirOriginal.gridx = 1;
				gbc_btnAbrirOriginal.gridy = 1;
				panel.add(btnAbrirOriginal, gbc_btnAbrirOriginal);
			}
			{
				txtPuzzle = new JTextField();
				txtPuzzle.setBackground(Color.WHITE);
				txtPuzzle.setEditable(false);
				GridBagConstraints gbc_txtPuzzle = new GridBagConstraints();
				gbc_txtPuzzle.gridwidth = 11;
				gbc_txtPuzzle.insets = new Insets(0, 0, 5, 5);
				gbc_txtPuzzle.fill = GridBagConstraints.BOTH;
				gbc_txtPuzzle.gridx = 2;
				gbc_txtPuzzle.gridy = 1;
				panel.add(txtPuzzle, gbc_txtPuzzle);
				txtPuzzle.setColumns(10);
			}
			{
				btnAbrirModificado = new JButton("Abrir modificado");
				btnAbrirModificado.setEnabled(false);
				btnAbrirModificado.addActionListener(new BtnAbrirImagen());
				btnAbrirModificado.setHorizontalAlignment(SwingConstants.LEFT);
				btnAbrirModificado.setFont(new Font("Dialog", Font.PLAIN, 11));
				GridBagConstraints gbc_btnAbrirModificado = new GridBagConstraints();
				gbc_btnAbrirModificado.fill = GridBagConstraints.HORIZONTAL;
				gbc_btnAbrirModificado.insets = new Insets(0, 0, 5, 5);
				gbc_btnAbrirModificado.gridx = 1;
				gbc_btnAbrirModificado.gridy = 2;
				panel.add(btnAbrirModificado, gbc_btnAbrirModificado);
			}
			{
				txtModificado = new JTextField();
				txtModificado.setBackground(Color.WHITE);
				txtModificado.setEditable(false);
				GridBagConstraints gbc_txtModificado = new GridBagConstraints();
				gbc_txtModificado.gridwidth = 11;
				gbc_txtModificado.insets = new Insets(0, 0, 5, 5);
				gbc_txtModificado.fill = GridBagConstraints.BOTH;
				gbc_txtModificado.gridx = 2;
				gbc_txtModificado.gridy = 2;
				panel.add(txtModificado, gbc_txtModificado);
				txtModificado.setColumns(10);
			}
			{
				lblNmeroDeColumnas = new JLabel("Número de columnas:");
				lblNmeroDeColumnas.setEnabled(false);
				lblNmeroDeColumnas.setFont(new Font("Dialog", Font.PLAIN, 11));
				GridBagConstraints gbc_lblNmeroDeColumnas = new GridBagConstraints();
				gbc_lblNmeroDeColumnas.anchor = GridBagConstraints.EAST;
				gbc_lblNmeroDeColumnas.insets = new Insets(0, 0, 5, 5);
				gbc_lblNmeroDeColumnas.gridx = 1;
				gbc_lblNmeroDeColumnas.gridy = 3;
				panel.add(lblNmeroDeColumnas, gbc_lblNmeroDeColumnas);
			}
			{
				cbCols = new JComboBox();
				cbCols.setEnabled(false);
				cbCols.addItemListener(new CbColsItemListener());
				cbCols.setMaximumRowCount(10);
				cbCols.setModel(new DefaultComboBoxModel(new String[] {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10"}));
				cbCols.setFont(new Font("Dialog", Font.PLAIN, 11));
				cbCols.setBackground(Color.WHITE);
				GridBagConstraints gbc_cbCols = new GridBagConstraints();
				gbc_cbCols.insets = new Insets(0, 0, 5, 5);
				gbc_cbCols.fill = GridBagConstraints.HORIZONTAL;
				gbc_cbCols.gridx = 2;
				gbc_cbCols.gridy = 3;
				panel.add(cbCols, gbc_cbCols);
			}
			{
				lblNmeroDeFilas = new JLabel("Número de filas:");
				lblNmeroDeFilas.setEnabled(false);
				lblNmeroDeFilas.setFont(new Font("Dialog", Font.PLAIN, 11));
				GridBagConstraints gbc_lblNmeroDeFilas = new GridBagConstraints();
				gbc_lblNmeroDeFilas.anchor = GridBagConstraints.EAST;
				gbc_lblNmeroDeFilas.insets = new Insets(0, 0, 5, 5);
				gbc_lblNmeroDeFilas.gridx = 3;
				gbc_lblNmeroDeFilas.gridy = 3;
				panel.add(lblNmeroDeFilas, gbc_lblNmeroDeFilas);
			}
			{
				cbRows = new JComboBox();
				cbRows.setEnabled(false);
				cbRows.addItemListener(new CbRowsItemListener());
				cbRows.setModel(new DefaultComboBoxModel(new String[] {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10"}));
				cbRows.setMaximumRowCount(10);
				cbRows.setFont(new Font("Dialog", Font.PLAIN, 11));
				cbRows.setBackground(Color.WHITE);
				GridBagConstraints gbc_cbRows = new GridBagConstraints();
				gbc_cbRows.insets = new Insets(0, 0, 5, 5);
				gbc_cbRows.fill = GridBagConstraints.HORIZONTAL;
				gbc_cbRows.gridx = 4;
				gbc_cbRows.gridy = 3;
				panel.add(cbRows, gbc_cbRows);
			}
			{
				lblEstrategia = new JLabel("Estrategia");
				lblEstrategia.setEnabled(false);
				lblEstrategia.setFont(new Font("Dialog", Font.PLAIN, 11));
				GridBagConstraints gbc_lblEstrategia = new GridBagConstraints();
				gbc_lblEstrategia.anchor = GridBagConstraints.EAST;
				gbc_lblEstrategia.insets = new Insets(0, 0, 5, 5);
				gbc_lblEstrategia.gridx = 1;
				gbc_lblEstrategia.gridy = 4;
				panel.add(lblEstrategia, gbc_lblEstrategia);
			}
			{
				cbEstrategia = new JComboBox();
				cbEstrategia.setEnabled(false);
				cbEstrategia.addItemListener(new CbEstrategiaItemListener());
				cbEstrategia.setBackground(Color.WHITE);
				cbEstrategia.setFont(new Font("Dialog", Font.PLAIN, 11));
				cbEstrategia.setModel(new DefaultComboBoxModel(new String[] {"Seleccione una estrategia", "Anchura", "Costo Uniforme", "Profundidad", "Profundidad Acotada", "Profundidad Iterativa", "A estrella"}));
				GridBagConstraints gbc_cbEstrategia = new GridBagConstraints();
				gbc_cbEstrategia.gridwidth = 3;
				gbc_cbEstrategia.insets = new Insets(0, 0, 5, 5);
				gbc_cbEstrategia.fill = GridBagConstraints.HORIZONTAL;
				gbc_cbEstrategia.gridx = 2;
				gbc_cbEstrategia.gridy = 4;
				panel.add(cbEstrategia, gbc_cbEstrategia);
			}
			{
			}
			{
				{
					lblProfunidad = new JLabel("Profundida Max.");
					lblProfunidad.setFont(new Font("Dialog", Font.PLAIN, 11));
					lblProfunidad.setVisible(false);
					GridBagConstraints gbc_lblProfunidad = new GridBagConstraints();
					gbc_lblProfunidad.fill = GridBagConstraints.VERTICAL;
					gbc_lblProfunidad.anchor = GridBagConstraints.EAST;
					gbc_lblProfunidad.insets = new Insets(0, 0, 5, 5);
					gbc_lblProfunidad.gridx = 1;
					gbc_lblProfunidad.gridy = 5;
					panel.add(lblProfunidad, gbc_lblProfunidad);
				}
				{
					txtfCota = new JTextField();
					txtfCota.addKeyListener(new TxtfCotaKeyListener());
					txtfCota.setEditable(true);
					txtfCota.setVisible(false);
					{
						lblCota = new JLabel("Inc. Cota");
						lblCota.setFont(new Font("Dialog", Font.PLAIN, 11));
						lblCota.setVisible(false);
						{
							txtfProfunidad = new JTextField();
							txtfProfunidad.addKeyListener(new TxtfProfunidadKeyListener());
							txtfProfunidad.setVisible(false);
							GridBagConstraints gbc_txtfProfunidad = new GridBagConstraints();
							gbc_txtfProfunidad.insets = new Insets(0, 0, 5, 5);
							gbc_txtfProfunidad.fill = GridBagConstraints.BOTH;
							gbc_txtfProfunidad.gridx = 2;
							gbc_txtfProfunidad.gridy = 5;
							panel.add(txtfProfunidad, gbc_txtfProfunidad);
							txtfProfunidad.setColumns(5);
						}
						GridBagConstraints gbc_lblCota = new GridBagConstraints();
						gbc_lblCota.fill = GridBagConstraints.VERTICAL;
						gbc_lblCota.anchor = GridBagConstraints.EAST;
						gbc_lblCota.insets = new Insets(0, 0, 5, 5);
						gbc_lblCota.gridx = 3;
						gbc_lblCota.gridy = 5;
						panel.add(lblCota, gbc_lblCota);
					}
					txtfCota.setColumns(5);
					txtfCota.setBackground(Color.WHITE);
					GridBagConstraints gbc_txtfCota = new GridBagConstraints();
					gbc_txtfCota.insets = new Insets(0, 0, 5, 5);
					gbc_txtfCota.fill = GridBagConstraints.BOTH;
					gbc_txtfCota.gridx = 4;
					gbc_txtfCota.gridy = 5;
					panel.add(txtfCota, gbc_txtfCota);
				}
			}
			btnSExplorar = new JButton("Explorar");
			btnSExplorar.setEnabled(false);
			btnSExplorar.addActionListener(new BtnSExplorarActionListener());
			GridBagConstraints gbc_btnSExplorar = new GridBagConstraints();
			gbc_btnSExplorar.fill = GridBagConstraints.HORIZONTAL;
			gbc_btnSExplorar.insets = new Insets(0, 0, 5, 5);
			gbc_btnSExplorar.gridx = 1;
			gbc_btnSExplorar.gridy = 7;
			panel.add(btnSExplorar, gbc_btnSExplorar);
			{
				lblMensajes = new JLabel("");
				GridBagConstraints gbc_lblMensajes = new GridBagConstraints();
				gbc_lblMensajes.anchor = GridBagConstraints.WEST;
				gbc_lblMensajes.gridwidth = 11;
				gbc_lblMensajes.insets = new Insets(0, 0, 5, 5);
				gbc_lblMensajes.gridx = 2;
				gbc_lblMensajes.gridy = 7;
				panel.add(lblMensajes, gbc_lblMensajes);
			}
			{
				panel_1 = new JPanel();
				panel_1.setBorder(new TitledBorder(null, "Soluci\u00F3n", TitledBorder.LEADING, TitledBorder.TOP, null, null));
				GridBagConstraints gbc_panel_1 = new GridBagConstraints();
				gbc_panel_1.gridheight = 2;
				gbc_panel_1.gridwidth = 12;
				gbc_panel_1.insets = new Insets(0, 0, 5, 5);
				gbc_panel_1.fill = GridBagConstraints.BOTH;
				gbc_panel_1.gridx = 1;
				gbc_panel_1.gridy = 9;
				panel.add(panel_1, gbc_panel_1);
				GridBagLayout gbl_panel_1 = new GridBagLayout();
				gbl_panel_1.columnWidths = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0};
				gbl_panel_1.rowHeights = new int[]{0, 0, 0, 0, 0};
				gbl_panel_1.columnWeights = new double[]{0.0, 1.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0, Double.MIN_VALUE};
				gbl_panel_1.rowWeights = new double[]{0.0, 1.0, 0.0, 0.0, Double.MIN_VALUE};
				panel_1.setLayout(gbl_panel_1);
				{
					lblSolucion = new JLabel("");
					lblSolucion.setIcon(new ImageIcon(ventana.class.getResource("/presentacion/window_icon.png")));
					GridBagConstraints gbc_lblSolucion = new GridBagConstraints();
					gbc_lblSolucion.gridwidth = 6;
					gbc_lblSolucion.insets = new Insets(0, 0, 5, 5);
					gbc_lblSolucion.gridx = 1;
					gbc_lblSolucion.gridy = 1;
					panel_1.add(lblSolucion, gbc_lblSolucion);
				}
				{
					btnAnterior = new JButton("");
					btnAnterior.setEnabled(false);
					btnAnterior.addActionListener(new BtnAnteriorActionListener());
					btnAnterior.setIcon(new ImageIcon(ventana.class.getResource("/presentacion/ButtonPreviousPage.png")));
					GridBagConstraints gbc_btnAnterior = new GridBagConstraints();
					gbc_btnAnterior.insets = new Insets(0, 0, 0, 5);
					gbc_btnAnterior.gridx = 3;
					gbc_btnAnterior.gridy = 3;
					panel_1.add(btnAnterior, gbc_btnAnterior);
				}
				{
					btnSiguiente = new JButton("");
					btnSiguiente.addActionListener(new BtnSiguienteActionListener());
					btnSiguiente.setIcon(new ImageIcon(ventana.class.getResource("/presentacion/ButtonNextPage.png")));
					GridBagConstraints gbc_btnSiguiente = new GridBagConstraints();
					gbc_btnSiguiente.insets = new Insets(0, 0, 0, 5);
					gbc_btnSiguiente.gridx = 4;
					gbc_btnSiguiente.gridy = 3;
					panel_1.add(btnSiguiente, gbc_btnSiguiente);
				}
			}
		}
	}

	
	
	private class BtnAbrirImagen implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			lblMensajes.setText("");
			Puzzle p_original=null;
			Puzzle p_inicial = null;
			JFileChooser fcAbrir = new JFileChooser();
			fcAbrir.setFileFilter(new ImageFilter());
			String comentarios = "";
			int valorDevuelto = fcAbrir.showOpenDialog(frmSistemasInteligentes);
//			 Recoger el nombre del fichero seleccionado por el usuario
			if (valorDevuelto == JFileChooser.APPROVE_OPTION) {
				File file = fcAbrir.getSelectedFile();
				if(e.getActionCommand()=="Abrir puzzle"){
					txtPuzzle.setText(file.getAbsolutePath());
					path_original=file.getAbsolutePath(); 
					if(txtPuzzle.getText()!="")
						btnAbrirModificado.setEnabled(true);
				
				}else if(e.getActionCommand()=="Abrir modificado"){
					txtModificado.setText(file.getAbsolutePath());
					int imgWidth = 640;
			        int imgHeight = 400;
			        path_modificado = file.getAbsolutePath();
			        if(txtModificado.getText()!=""){
			        	cbRows.setEnabled(true);
			        	lblNmeroDeColumnas.setEnabled(true);
			        	cbCols.setEnabled(true);
			        	lblNmeroDeFilas.setEnabled(true);
			        	cbEstrategia.setEnabled(true);
			        	lblEstrategia.setEnabled(true);
			        }
				}				
			}
		}
	}

	private class BtnSExplorarActionListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			try {
				
				btnAnterior.setEnabled(false);
				btnSiguiente.setEnabled(true);
				lblSolucion.setIcon(null);
				problema = new Problema(new EspacioDeEstados(path_original,Integer.parseInt(rows), Integer.parseInt(cols)), new Estado(Integer.parseInt(rows),  Integer.parseInt(cols), path_modificado, EspacioDeEstados.eobjetivo.getPiezas()));
				if(problema.getEde().esValido(problema.getEinicial())){					
					Nodo n = problema.busqueda(estrategia, Integer.parseInt(prof_max), Integer.parseInt(inc_cota));
					if(n!=null){
						
						lblMensajes.setText("¡Solución encontrada! Ver fichero solucion_"+estrategia+".txt");
						problema.escribirSolucion(n, estrategia, path_original, path_modificado, Integer.parseInt(rows), Integer.parseInt(cols));
						try {
							solution=new ArrayList<Estado>();
							
							while(n.getParent()!=null){
								solution.add(n.getEstado());
								n=n.getParent();
							}
							solution.add(problema.getEinicial());
							count=(solution.size()-1);
							solution.get(count).generarImagen("solucion_parcial");
							ImageIcon image = new ImageIcon( new ImageIcon("solucion_parcial.png").getImage().getScaledInstance(550, 350,  java.awt.Image.SCALE_SMOOTH));
							lblSolucion.setIcon(image);
							
							
							
						} catch (Exception e2) {
							// TODO: handle exception
						}
					}else{
						lblMensajes.setText("No se ha encontrado solución");
					}
					System.out.println("***** Fin del programa *****");
					
				}else{
					lblMensajes.setText("No existe solución: Los puzzles no coinciden");
				}
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}
	private class CbColsItemListener implements ItemListener {
		public void itemStateChanged(ItemEvent e) {
			cols = cbCols.getItemAt(cbCols.getSelectedIndex()).toString();
		}
	}
	private class CbRowsItemListener implements ItemListener {
		public void itemStateChanged(ItemEvent e) {
			rows = cbRows.getItemAt(cbRows.getSelectedIndex()).toString();
		}
	}
	private class CbEstrategiaItemListener implements ItemListener {
		public void itemStateChanged(ItemEvent e) {
			estrategia = cbEstrategia.getItemAt(cbEstrategia.getSelectedIndex()).toString();
			System.out.println(estrategia);
			if(estrategia == "Profundidad Acotada"){
				lblProfunidad.setVisible(true);
				txtfProfunidad.setVisible(true);
				lblCota.setVisible(false);
				txtfCota.setVisible(false);
				if(txtfProfunidad.getText().length()>0)
					btnSExplorar.setEnabled(true);
				else
					btnSExplorar.setEnabled(false);
				
			}
			else if(estrategia == "Profundidad Iterativa"){
				lblProfunidad.setVisible(true);
				txtfProfunidad.setVisible(true);
				lblCota.setVisible(true);
				txtfCota.setVisible(true);

				if(txtfProfunidad.getText().length()>0 && txtfCota.getText().length()>0)
					btnSExplorar.setEnabled(true);
				else
					btnSExplorar.setEnabled(false);
			}
			else if(estrategia == "Seleccione una estrategia"){
				lblProfunidad.setVisible(false);
				txtfProfunidad.setVisible(false);
				lblCota.setVisible(false);
				txtfCota.setVisible(false);
				btnSExplorar.setEnabled(false);
			}
			else{
				lblProfunidad.setVisible(false);
				txtfProfunidad.setVisible(false);
				lblCota.setVisible(false);
				txtfCota.setVisible(false);
				btnSExplorar.setEnabled(true);
				prof_max="99999";
				inc_cota="99999";
			}
				
			
		}
	}
	private class TxtfProfunidadKeyListener extends KeyAdapter {
		@Override
		public void keyReleased(KeyEvent e) {
			if(txtfProfunidad.getText().equals("")){
				btnSExplorar.setEnabled(false);
			}
			else
				btnSExplorar.setEnabled(true);
			    prof_max=txtfProfunidad.getText().toString();
			    inc_cota=prof_max;
		}
	}
	private class TxtfCotaKeyListener extends KeyAdapter {
		@Override
		public void keyReleased(KeyEvent e) {
			if(txtfProfunidad.getText().equals("") && txtfCota.getText().equals("")){
				btnSExplorar.setEnabled(false);
			}
			else
				btnSExplorar.setEnabled(true);
			    prof_max=txtfProfunidad.getText().toString();
			    inc_cota=txtfCota.getText().toString();
		}
	}
	private class BtnSiguienteActionListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			try {
				if(count>0){
					count--;
					btnAnterior.setEnabled(true);
				}
				if(count==0) btnSiguiente.setEnabled(false);
				
				System.out.println(solution.get(count));
				solution.get(count).generarImagen("solucion_parcial");
				System.out.println(count);
				ImageIcon image = new ImageIcon( new ImageIcon("solucion_parcial.png").getImage().getScaledInstance(550, 350,  java.awt.Image.SCALE_SMOOTH));
				lblSolucion.setIcon(image);
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}
	private class BtnAnteriorActionListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			try {
				if(count<solution.size()-1){
					count++;
					btnSiguiente.setEnabled(true);
				}
				if(count==solution.size()-1) btnAnterior.setEnabled(false);
				System.out.println(solution.get(count));
				solution.get(count).generarImagen("solucion_parcial");
				ImageIcon image = new ImageIcon( new ImageIcon("solucion_parcial.png").getImage().getScaledInstance(550, 350,  java.awt.Image.SCALE_SMOOTH));
				lblSolucion.setIcon(image);
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}
	public static void crearPuzzle() throws IOException{
		int cols = 3;
		int rows = 3;
		String path_original = "./images/drone5x4.png";
		Estado estado_objetivo = new Estado(rows, cols, path_original, null);
		estado_objetivo.mover('R');
		estado_objetivo.mover('R');
		estado_objetivo.mover('D');
		estado_objetivo.mover('D');
		estado_objetivo.mover('L');
		estado_objetivo.mover('L');
		estado_objetivo.mover('U');
		estado_objetivo.mover('U');
		estado_objetivo.mover('R');

		estado_objetivo.generarImagen("./images/drone9");
}
}
