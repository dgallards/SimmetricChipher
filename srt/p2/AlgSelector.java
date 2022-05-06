package p2;

import java.awt.BorderLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
/**
 * Este es el panel de selección de algortimo de cifrado
 * @author Diego Gallardo Zancada
 *
 */
public class AlgSelector extends JDialog {

	
	private static final long serialVersionUID = 1L;

	private final JPanel contentPanel = new JPanel();

	String selection;

	public String getSelection() {
		return selection;
	}

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			AlgSelector dialog = new AlgSelector();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public AlgSelector() {
		setResizable(false);
		setModal(true);
		setBounds(100, 100, 284, 260);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		JButton btnNewButton = new JButton(Messages.getString("AlgSelector.btnNewButton.text")); //$NON-NLS-1$
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				selection = "PBEWithMD5AndDES";
				setVisible(false);
			}
		});
		JButton btnNewButton_1 = new JButton(Messages.getString("AlgSelector.btnNewButton_1.text")); //$NON-NLS-1$
		btnNewButton_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				selection = "PBEWithMD5AndDES";
				setVisible(false);
			}
		});
		JButton btnNewButton_2 = new JButton(Messages.getString("AlgSelector.btnNewButton_2.text")); //$NON-NLS-1$
		btnNewButton_2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				selection = "PBEWithSHA1AndDESede";
				setVisible(false);
			}
		});
		JButton btnNewButton_3 = new JButton(Messages.getString("AlgSelector.btnNewButton_3.text")); //$NON-NLS-1$
		btnNewButton_3.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				selection = "PBEWithSHA1AndRC2_40";
				setVisible(false);
			}
		});

		JLabel lblSeleccioneElAlgoritmo = new JLabel(Messages.getString("AlgSelector.lblSeleccioneElAlgoritmo.text")); //$NON-NLS-1$
		GroupLayout gl_contentPanel = new GroupLayout(contentPanel);
		gl_contentPanel.setHorizontalGroup(
			gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, gl_contentPanel.createSequentialGroup()
					.addContainerGap(21, Short.MAX_VALUE)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
						.addComponent(lblSeleccioneElAlgoritmo)
						.addGroup(gl_contentPanel.createSequentialGroup()
							.addGap(12)
							.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING, false)
								.addComponent(btnNewButton_1, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(btnNewButton_2, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(btnNewButton_3, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(btnNewButton, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
					.addGap(11))
		);
		gl_contentPanel.setVerticalGroup(
			gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, gl_contentPanel.createSequentialGroup()
					.addContainerGap(25, Short.MAX_VALUE)
					.addComponent(lblSeleccioneElAlgoritmo)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(btnNewButton)
					.addGap(18)
					.addComponent(btnNewButton_1)
					.addGap(18)
					.addComponent(btnNewButton_2)
					.addGap(18)
					.addComponent(btnNewButton_3)
					.addGap(15))
		);
		contentPanel.setLayout(gl_contentPanel);
	}
}
