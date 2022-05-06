package p2;

import java.awt.BorderLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
/**
 * Este es el panel de selección de idioma.
 * @author Diego Gallardo Zancada
 *
 */
public class LangSelector extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1119054377228150040L;

	private final JPanel contentPanel = new JPanel();
	
	String lang="español";
	
	
	String getLang() {
		return lang;
	}

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			LangSelector dialog = new LangSelector();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public LangSelector() {
		setResizable(false);
		setAlwaysOnTop(true);
		setModal(true);
		setBounds(100, 100, 166, 208);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		
		JButton btnEspaol = new JButton("Español");
		btnEspaol.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				lang = "español";
				setVisible(false);
			}
		});
		
		JButton btnEnglish = new JButton("English");
		btnEnglish.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				lang="ingles";
				setVisible(false);
			}
		});
		
		JButton btnFranais = new JButton("Français");
		btnFranais.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				lang="frances";
				setVisible(false);
			}
		});
		GroupLayout gl_contentPanel = new GroupLayout(contentPanel);
		gl_contentPanel.setHorizontalGroup(
			gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING, false)
						.addComponent(btnEnglish, GroupLayout.DEFAULT_SIZE, 120, Short.MAX_VALUE)
						.addComponent(btnEspaol, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(btnFranais, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
					.addContainerGap(306, Short.MAX_VALUE))
		);
		gl_contentPanel.setVerticalGroup(
			gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addGap(28)
					.addComponent(btnEspaol)
					.addGap(18)
					.addComponent(btnEnglish)
					.addGap(18)
					.addComponent(btnFranais)
					.addContainerGap(122, Short.MAX_VALUE))
		);
		contentPanel.setLayout(gl_contentPanel);
	}
}
