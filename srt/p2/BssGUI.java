package p2;

import java.awt.EventQueue;
import java.awt.Frame;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;
/**
 * Esta clase es la encargada de mostrar la interfaz gráfica y lanzar los algoritmos de cifrado
 * @author Diego Gallardo Zancada y Daniel Merino Cortés
 *
 */
public class BssGUI {

	private JFrame frame;

	Practica2 practica2 = new Practica2();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {

					BssGUI window = new BssGUI();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public BssGUI() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame("Trabajo BSS");

		frame.setResizable(false);
		frame.setBounds(100, 100, 343, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);

		JButton btnNewButton = new JButton(Messages.getString("BssGUI.btnNewButton.text")); //$NON-NLS-1$
		btnNewButton.addMouseListener(new MouseAdapter() { 									//ESTE ES EL BOTÓN DE ENCRIPTADO
			@Override
			public void mouseClicked(MouseEvent e) {
				
				JFileChooser fileChooser = new JFileChooser(); 								//Aquí creamos un popup para elegir el archivo
				fileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));//Aquí se le dice cual carpeta mostrar, normalmente el directorio home del usuario
				int result = fileChooser.showOpenDialog(fileChooser);						//Lanzamos el popup
				if (result == JFileChooser.APPROVE_OPTION) { 								//Si marcamos "OK"
					File selectedFile = fileChooser.getSelectedFile(); 						//Recuperamos el archivo elegido
					AlgSelector algSelector = new AlgSelector(); 							//Obligamos al usuario a elegir un algoritmo de cifrado
					algSelector.setVisible(true); 											//Mostramos el mensaje
					// System.out.println("Selected file: " + selectedFile.getAbsolutePath());
					String selection = algSelector.getSelection(); 							//Recuperamos la elección
					//System.out.println(selection);
					PasswordPanel pwdPanel = new PasswordPanel(); 							//Obligamos al usuario a introducir una contraseña
					pwdPanel.setVisible(true); 												//Mostramos el mensaje
					String pwd = pwdPanel.getPassWord(); 									//Recuperamos la contraseña
					//System.out.println(pwd);
					if (pwdPanel.getAceptar()) { 											//Si la contraseña introducida es válida
						practica2.doEncryptFile(selectedFile.getAbsolutePath(), selection, pwd); //Encriptamos el archivo
					} else {
						// System.out.print("se canceló la contra");
					}

				} else {
					// System.out.println("No se pulsó nada");
				}
			}
		});

		JButton btnNewButton_1 = new JButton(Messages.getString("BssGUI.btnNewButton_1.text")); //$NON-NLS-1$
		btnNewButton_1.addMouseListener(new MouseAdapter() {//ESTE ES EL BOTÓN DE DESCIFRADO
			@Override
			public void mouseClicked(MouseEvent e) {
				FileNameExtensionFilter filter = new FileNameExtensionFilter(
						Messages.getString("BssGUI.btnNewButton_1.filetypes"), "cif"); 		//Creamos un filtro para elegir lso archivos ".cif"
				JFileChooser fileChooser = new JFileChooser();							 	// Creamos un popup para elegir el archivo
				fileChooser.setFileFilter(filter);											//Le introducimos el filtro creado
				fileChooser.setCurrentDirectory(new File(System.getProperty("user.home"))); //Aquí se le dice cual carpeta mostrar, normalmente el directorio home del usuario
				int result = fileChooser.showOpenDialog(fileChooser);						//Mostramos el popup
				if (result == JFileChooser.APPROVE_OPTION) {								//Si pulsa "OK"
					File selectedFile = fileChooser.getSelectedFile(); 						//Recuperamos el archivo mostrado
					// System.out.println("Selected file: " + selectedFile.getAbsolutePath());
					PasswordPanel pwdPanel = new PasswordPanel(); 							//Obligamos al usuario a introducir una contraseña
					pwdPanel.setVisible(true); 												//Mostramos el mensaje
					String pwd = pwdPanel.getPassWord(); 									//Recuperamos la contraseña
					//System.out.println(pwd);
					if (pwdPanel.getAceptar()) { 											//Si la contraseña es válida
						if (selectedFile.getAbsolutePath().contains(".cif")) {
							 try {
							practica2.doDecryptFile(selectedFile.getAbsolutePath(), pwd); 	//Desencriptamos
							 } catch (Exception exc) { 										//Esta es la excepción si la contraseña es incorrecta
							 Frame frame = new Frame();
							 JOptionPane.showMessageDialog(frame,
							 Messages.getString("BssGUI.btnNewButton_1.password_incorrect"));//Mostramos un mensaje de contraseña inválida
							 

							 }
						} else {
							Frame frame = new Frame();
							JOptionPane.showMessageDialog(frame,
									Messages.getString("BssGUI.btnNewButton_1.filetype_incorrect"));
						}
					} else {
						// System.out.print("se canceló la contra");
					}

				} else {
					// System.out.println("No se pulsó nada");
				}
			}
		});

		JLabel lblNewLabel = new JLabel();
		lblNewLabel.setIcon(new ImageIcon(BssGUI.class.getResource("/Resources/love-locks-clipart-16.jpg")));

		JButton button = new JButton(); // $NON-NLS-1$
		button.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				LangSelector langSelector = new LangSelector();
				langSelector.setVisible(true);
				Messages.setIdioma(langSelector.getLang());
				frame.dispose();
				BssGUI window = new BssGUI();
				window.frame.setVisible(true);
			}
		});
		button.setSelectedIcon(new ImageIcon(BssGUI.class.getResource("/Resources/global.jpg")));
		button.setIcon(new ImageIcon(BssGUI.class.getResource("/Resources/global.jpg"),
				Messages.getString("BssGUI.Button.i18n")));
		GroupLayout groupLayout = new GroupLayout(frame.getContentPane());
		groupLayout.setHorizontalGroup(groupLayout.createParallelGroup(Alignment.LEADING).addGroup(groupLayout
				.createSequentialGroup().addGap(48)
				.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(lblNewLabel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addGroup(groupLayout.createSequentialGroup().addComponent(btnNewButton).addGap(18)
								.addComponent(btnNewButton_1)))
				.addGap(78))
				.addGroup(groupLayout.createSequentialGroup().addContainerGap()
						.addComponent(button, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE)
						.addContainerGap(279, Short.MAX_VALUE)));
		groupLayout.setVerticalGroup(groupLayout.createParallelGroup(Alignment.TRAILING).addGroup(groupLayout
				.createSequentialGroup().addContainerGap(14, Short.MAX_VALUE)
				.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 140, GroupLayout.PREFERRED_SIZE).addGap(18)
				.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE).addComponent(btnNewButton)
						.addComponent(btnNewButton_1))
				.addGap(18).addComponent(button, GroupLayout.PREFERRED_SIZE, 44, GroupLayout.PREFERRED_SIZE)
				.addContainerGap()));
		frame.getContentPane().setLayout(groupLayout);
	}
}
