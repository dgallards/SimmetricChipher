package p2;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Frame;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JPasswordField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Arrays;
/**
 * Este es el panel para elegir la contraseña en la GUI.
 * @author Diego Gallardo Zancada y Daniel Merino Cortés
 *
 */
public class PasswordPanel extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = -240027579237197556L;
	private final JPanel contentPanel = new JPanel();
	private JPasswordField passwordField;
	private JPasswordField passwordField_1;

	String password,password1;

	boolean aceptar = false;

	public String getPassWord() {
		return password;
	}

	public boolean getAceptar() {
		return aceptar;
	}

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			PasswordPanel dialog = new PasswordPanel();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public PasswordPanel() {
		setResizable(false);
		setModal(true);
		setBounds(100, 100, 450, 204);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		JLabel lblIntroduzcaLaContrasea = new JLabel(Messages.getString("PasswordPanel.lblIntroduzcaLaContrasea.text")); //$NON-NLS-1$

		JLabel lblConfirmeLaContrasea = new JLabel(Messages.getString("PasswordPanel.lblConfirmeLaContrasea.text")); //$NON-NLS-1$

		JLabel lblLasContraseasNo = new JLabel(Messages.getString("PasswordPanel.lblLasContraseasNo.text")); //$NON-NLS-1$
		lblLasContraseasNo.setVisible(false);

		passwordField = new JPasswordField();
		
		passwordField.setColumns(20);

		passwordField_1 = new JPasswordField();
		passwordField_1.setColumns(20);
		

		lblLasContraseasNo.setEnabled(false);
		lblLasContraseasNo.setForeground(Color.RED);
		GroupLayout gl_contentPanel = new GroupLayout(contentPanel);
		gl_contentPanel
				.setHorizontalGroup(gl_contentPanel.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_contentPanel.createSequentialGroup().addContainerGap(17, Short.MAX_VALUE)
								.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
										.addComponent(lblIntroduzcaLaContrasea).addComponent(lblConfirmeLaContrasea))
								.addPreferredGap(ComponentPlacement.RELATED)
								.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
										.addComponent(lblLasContraseasNo)
										.addComponent(passwordField_1, GroupLayout.PREFERRED_SIZE,
												GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
										.addComponent(passwordField, GroupLayout.PREFERRED_SIZE,
												GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
								.addGap(7)));
		gl_contentPanel
				.setVerticalGroup(
						gl_contentPanel.createParallelGroup(Alignment.TRAILING).addGroup(Alignment.LEADING,
								gl_contentPanel.createSequentialGroup().addGap(42)
										.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
												.addComponent(lblIntroduzcaLaContrasea)
												.addComponent(passwordField, GroupLayout.PREFERRED_SIZE,
														GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
										.addGap(18)
										.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
												.addComponent(passwordField_1, GroupLayout.PREFERRED_SIZE,
														GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
												.addComponent(lblConfirmeLaContrasea))
										.addPreferredGap(ComponentPlacement.RELATED).addComponent(lblLasContraseasNo)
										.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));
		contentPanel.setLayout(gl_contentPanel);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton(Messages.getString("PasswordPanel.okButton.text")); //$NON-NLS-1$
				okButton.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {
						
						if (!Arrays.equals(passwordField.getPassword(), passwordField_1.getPassword())) {
							//System.out.println(new String(passwordField.getPassword()) + ", "+new String(passwordField.getPassword()));
							lblLasContraseasNo.setEnabled(true);
							lblLasContraseasNo.setVisible(true);
							passwordField.removeAll();
							passwordField_1.removeAll();
						} else {
							password = new String(passwordField.getPassword());
							password1 = new String(passwordField_1.getPassword());
							if(password.length()<6 || password1.length()<6) {
								Frame frame = new Frame();
								JOptionPane.showMessageDialog(frame, Messages.getString("PasswordPanel.okButton.pwdlength"));
							}else {
							aceptar = true;
							setVisible(false);
							}
						}

					}

				});
				okButton.setActionCommand(Messages.getString("PasswordPanel.okButton.actionCommand")); //$NON-NLS-1$
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton(Messages.getString("PasswordPanel.cancelButton.text")); //$NON-NLS-1$
				cancelButton.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {
						setVisible(false);
						aceptar = false;
					}
				});
				//cancelButton.setActionCommand("Calcel");
				buttonPane.add(cancelButton);
			}
		}
	}
}
