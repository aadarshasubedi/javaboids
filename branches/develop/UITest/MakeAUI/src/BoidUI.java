
import java.awt.event.KeyEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Event;
import java.awt.BorderLayout;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.KeyStroke;
import java.awt.Point;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JMenuItem;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JFrame;
import javax.swing.JDialog;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.GridBagLayout;
import javax.swing.JCheckBox;
import java.awt.GridBagConstraints;
import javax.swing.JTextField;
import javax.swing.JButton;

public class BoidUI {

	private JFrame jFrame = null;  //  @jve:decl-index=0:visual-constraint="10,10"
	private JPanel jContentPane = null;
	private JMenuBar jJMenuBar = null;
	private JMenu fileMenu = null;
	private JMenu editMenu = null;
	private JMenu helpMenu = null;
	private JMenuItem exitMenuItem = null;
	private JMenuItem aboutMenuItem = null;
	private JMenuItem cutMenuItem = null;
	private JMenuItem copyMenuItem = null;
	private JMenuItem pasteMenuItem = null;
	private JMenuItem saveMenuItem = null;
	private JDialog aboutDialog = null;  //  @jve:decl-index=0:visual-constraint="1028,605"
	private JPanel aboutContentPane = null;
	private JLabel aboutVersionLabel = null;
	private JPanel pnlBoidControl = null;
	private JCheckBox chkRuleAvoidCollision = null;
	private JCheckBox chkFlockToCenter = null;
	private JTextField edtAvoidCollision = null;
	private JCheckBox chkKeepDistance = null;
	private JCheckBox chkAllignVelocity = null;
	private JTextField edtFlockToCenter = null;
	private JTextField edtKeepMinimalDistance = null;
	private JTextField edtAllignVelocity = null;
	private JLabel jLabel = null;
	private JLabel jLabel1 = null;
	private JLabel jLabel2 = null;
	private JLabel jLabel21 = null;
	private JTextField edtMaxSpeed = null;
	private JTextField edtMacVeloc = null;
	private JTextField edtSightDistance = null;
	private JLabel jLabel211 = null;
	private JTextField edtNumberOfBoids = null;
	private JButton btnPopulate = null;
	private JButton btnAddObstacle = null;
	private JCheckBox chkRuleFlee = null;
	private JCheckBox chkRuleMoveToAtrractionPnt = null;
	private JTextField edtAttractionPoint = null;
	private JLabel jLabel3 = null;
	/**
	 * This method initializes jFrame
	 * 
	 * @return javax.swing.JFrame
	 */
	private JFrame getJFrame() {
		if (jFrame == null) {
			jFrame = new JFrame();
			jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			jFrame.setJMenuBar(getJJMenuBar());
			jFrame.setSize(989, 634);
			jFrame.setContentPane(getJContentPane());
			jFrame.setTitle("Application");
		}
		return jFrame;
	}

	/**
	 * This method initializes jContentPane
	 * 
	 * @return javax.swing.JPanel
	 */
	private JPanel getJContentPane() {
		if (jContentPane == null) {
			jContentPane = new JPanel();
			jContentPane.setLayout(null);
			jContentPane.add(getPnlBoidControl(), null);
		}
		return jContentPane;
	}

	/**
	 * This method initializes jJMenuBar	
	 * 	
	 * @return javax.swing.JMenuBar	
	 */
	private JMenuBar getJJMenuBar() {
		if (jJMenuBar == null) {
			jJMenuBar = new JMenuBar();
			jJMenuBar.add(getFileMenu());
			jJMenuBar.add(getEditMenu());
			jJMenuBar.add(getHelpMenu());
		}
		return jJMenuBar;
	}

	/**
	 * This method initializes jMenu	
	 * 	
	 * @return javax.swing.JMenu	
	 */
	private JMenu getFileMenu() {
		if (fileMenu == null) {
			fileMenu = new JMenu();
			fileMenu.setText("File");
			fileMenu.add(getSaveMenuItem());
			fileMenu.add(getExitMenuItem());
		}
		return fileMenu;
	}

	/**
	 * This method initializes jMenu	
	 * 	
	 * @return javax.swing.JMenu	
	 */
	private JMenu getEditMenu() {
		if (editMenu == null) {
			editMenu = new JMenu();
			editMenu.setText("Edit");
			editMenu.add(getCutMenuItem());
			editMenu.add(getCopyMenuItem());
			editMenu.add(getPasteMenuItem());
		}
		return editMenu;
	}

	/**
	 * This method initializes jMenu	
	 * 	
	 * @return javax.swing.JMenu	
	 */
	private JMenu getHelpMenu() {
		if (helpMenu == null) {
			helpMenu = new JMenu();
			helpMenu.setText("Help");
			helpMenu.add(getAboutMenuItem());
		}
		return helpMenu;
	}

	/**
	 * This method initializes jMenuItem	
	 * 	
	 * @return javax.swing.JMenuItem	
	 */
	private JMenuItem getExitMenuItem() {
		if (exitMenuItem == null) {
			exitMenuItem = new JMenuItem();
			exitMenuItem.setText("Exit");
			exitMenuItem.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					System.exit(0);
				}
			});
		}
		return exitMenuItem;
	}

	/**
	 * This method initializes jMenuItem	
	 * 	
	 * @return javax.swing.JMenuItem	
	 */
	private JMenuItem getAboutMenuItem() {
		if (aboutMenuItem == null) {
			aboutMenuItem = new JMenuItem();
			aboutMenuItem.setText("About");
			aboutMenuItem.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					JDialog aboutDialog = getAboutDialog();
					aboutDialog.pack();
					Point loc = getJFrame().getLocation();
					loc.translate(20, 20);
					aboutDialog.setLocation(loc);
					aboutDialog.setVisible(true);
				}
			});
		}
		return aboutMenuItem;
	}

	/**
	 * This method initializes aboutDialog	
	 * 	
	 * @return javax.swing.JDialog
	 */
	private JDialog getAboutDialog() {
		if (aboutDialog == null) {
			aboutDialog = new JDialog(getJFrame(), true);
			aboutDialog.setTitle("About");
			aboutDialog.setContentPane(getAboutContentPane());
		}
		return aboutDialog;
	}

	/**
	 * This method initializes aboutContentPane
	 * 
	 * @return javax.swing.JPanel
	 */
	private JPanel getAboutContentPane() {
		if (aboutContentPane == null) {
			aboutContentPane = new JPanel();
			aboutContentPane.setLayout(new BorderLayout());
			aboutContentPane.add(getAboutVersionLabel(), BorderLayout.CENTER);
		}
		return aboutContentPane;
	}

	/**
	 * This method initializes aboutVersionLabel	
	 * 	
	 * @return javax.swing.JLabel	
	 */
	private JLabel getAboutVersionLabel() {
		if (aboutVersionLabel == null) {
			aboutVersionLabel = new JLabel();
			aboutVersionLabel.setText("Version 1.0");
			aboutVersionLabel.setHorizontalAlignment(SwingConstants.CENTER);
		}
		return aboutVersionLabel;
	}

	/**
	 * This method initializes jMenuItem	
	 * 	
	 * @return javax.swing.JMenuItem	
	 */
	private JMenuItem getCutMenuItem() {
		if (cutMenuItem == null) {
			cutMenuItem = new JMenuItem();
			cutMenuItem.setText("Cut");
			cutMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X,
					Event.CTRL_MASK, true));
		}
		return cutMenuItem;
	}

	/**
	 * This method initializes jMenuItem	
	 * 	
	 * @return javax.swing.JMenuItem	
	 */
	private JMenuItem getCopyMenuItem() {
		if (copyMenuItem == null) {
			copyMenuItem = new JMenuItem();
			copyMenuItem.setText("Copy");
			copyMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C,
					Event.CTRL_MASK, true));
		}
		return copyMenuItem;
	}

	/**
	 * This method initializes jMenuItem	
	 * 	
	 * @return javax.swing.JMenuItem	
	 */
	private JMenuItem getPasteMenuItem() {
		if (pasteMenuItem == null) {
			pasteMenuItem = new JMenuItem();
			pasteMenuItem.setText("Paste");
			pasteMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_V,
					Event.CTRL_MASK, true));
		}
		return pasteMenuItem;
	}

	/**
	 * This method initializes jMenuItem	
	 * 	
	 * @return javax.swing.JMenuItem	
	 */
	private JMenuItem getSaveMenuItem() {
		if (saveMenuItem == null) {
			saveMenuItem = new JMenuItem();
			saveMenuItem.setText("Save");
			saveMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S,
					Event.CTRL_MASK, true));
		}
		return saveMenuItem;
	}

	/**
	 * This method initializes pnlBoidControl	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getPnlBoidControl() {
		if (pnlBoidControl == null) {
			jLabel3 = new JLabel();
			jLabel3.setBounds(new Rectangle(60, 30, 106, 16));
			jLabel3.setText("BEHAVIOR RULES");
			jLabel211 = new JLabel();
			jLabel211.setBounds(new Rectangle(15, 405, 106, 16));
			jLabel211.setText("Number of Boids");
			jLabel21 = new JLabel();
			jLabel21.setBounds(new Rectangle(15, 375, 91, 16));
			jLabel21.setText("Sight distance");
			jLabel2 = new JLabel();
			jLabel2.setBounds(new Rectangle(15, 360, 91, 16));
			jLabel2.setText("Max Accel");
			jLabel1 = new JLabel();
			jLabel1.setBounds(new Rectangle(15, 345, 91, 16));
			jLabel1.setText("MAX Speed");
			jLabel = new JLabel();
			jLabel.setBounds(new Rectangle(15, 315, 111, 16));
			jLabel.setText("BOID properties");
			pnlBoidControl = new JPanel();
			pnlBoidControl.setLayout(null);
			pnlBoidControl.setBounds(new Rectangle(741, 6, 232, 560));
			pnlBoidControl.add(getChkRuleAvoidCollision(), null);
			pnlBoidControl.add(getChkFlockToCenter(), null);
			pnlBoidControl.add(getEdtAvoidCollision(), null);
			pnlBoidControl.add(getChkKeepDistance(), null);
			pnlBoidControl.add(getChkAllignVelocity(), null);
			pnlBoidControl.add(getEdtFlockToCenter(), null);
			pnlBoidControl.add(getEdtKeepMinimalDistance(), null);
			pnlBoidControl.add(getEdtAllignVelocity(), null);
			pnlBoidControl.add(jLabel, null);
			pnlBoidControl.add(jLabel1, null);
			pnlBoidControl.add(jLabel2, null);
			pnlBoidControl.add(jLabel21, null);
			pnlBoidControl.add(getEdtMaxSpeed(), null);
			pnlBoidControl.add(getEdtMacVeloc(), null);
			pnlBoidControl.add(getEdtSightDistance(), null);
			pnlBoidControl.add(jLabel211, null);
			pnlBoidControl.add(getEdtNumberOfBoids(), null);
			pnlBoidControl.add(getBtnPopulate(), null);
			pnlBoidControl.add(getBtnAddObstacle(), null);
			pnlBoidControl.add(getChkRuleFlee(), null);
			pnlBoidControl.add(getChkRuleMoveToAtrractionPnt(), null);
			pnlBoidControl.add(getEdtAttractionPoint(), null);
			pnlBoidControl.add(jLabel3, null);
		}
		return pnlBoidControl;
	}

	/**
	 * This method initializes chkRuleAvoidCollision	
	 * 	
	 * @return javax.swing.JCheckBox	
	 */
	private JCheckBox getChkRuleAvoidCollision() {
		if (chkRuleAvoidCollision == null) {
			chkRuleAvoidCollision = new JCheckBox();
			chkRuleAvoidCollision.setBounds(new Rectangle(15, 90, 115, 21));
			chkRuleAvoidCollision.setText("Avoid Collision");
		}
		return chkRuleAvoidCollision;
	}

	/**
	 * This method initializes chkFlockToCenter	
	 * 	
	 * @return javax.swing.JCheckBox	
	 */
	private JCheckBox getChkFlockToCenter() {
		if (chkFlockToCenter == null) {
			chkFlockToCenter = new JCheckBox();
			chkFlockToCenter.setBounds(new Rectangle(15, 120, 135, 21));
			chkFlockToCenter.setText("Flock to the center");
		}
		return chkFlockToCenter;
	}

	/**
	 * This method initializes edtAvoidCollision	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getEdtAvoidCollision() {
		if (edtAvoidCollision == null) {
			edtAvoidCollision = new JTextField();
			edtAvoidCollision.setBounds(new Rectangle(165, 90, 52, 20));
		}
		return edtAvoidCollision;
	}

	/**
	 * This method initializes chkKeepDistance	
	 * 	
	 * @return javax.swing.JCheckBox	
	 */
	private JCheckBox getChkKeepDistance() {
		if (chkKeepDistance == null) {
			chkKeepDistance = new JCheckBox();
			chkKeepDistance.setBounds(new Rectangle(15, 150, 154, 21));
			chkKeepDistance.setText("Keep minimal distance");
		}
		return chkKeepDistance;
	}

	/**
	 * This method initializes chkAllignVelocity	
	 * 	
	 * @return javax.swing.JCheckBox	
	 */
	private JCheckBox getChkAllignVelocity() {
		if (chkAllignVelocity == null) {
			chkAllignVelocity = new JCheckBox();
			chkAllignVelocity.setBounds(new Rectangle(15, 180, 106, 21));
			chkAllignVelocity.setText("Allign velocity");
		}
		return chkAllignVelocity;
	}

	/**
	 * This method initializes edtFlockToCenter	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getEdtFlockToCenter() {
		if (edtFlockToCenter == null) {
			edtFlockToCenter = new JTextField();
			edtFlockToCenter.setBounds(new Rectangle(165, 120, 50, 20));
		}
		return edtFlockToCenter;
	}

	/**
	 * This method initializes edtKeepMinimalDistance	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getEdtKeepMinimalDistance() {
		if (edtKeepMinimalDistance == null) {
			edtKeepMinimalDistance = new JTextField();
			edtKeepMinimalDistance.setBounds(new Rectangle(165, 150, 52, 20));
		}
		return edtKeepMinimalDistance;
	}

	/**
	 * This method initializes edtAllignVelocity	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getEdtAllignVelocity() {
		if (edtAllignVelocity == null) {
			edtAllignVelocity = new JTextField();
			edtAllignVelocity.setBounds(new Rectangle(165, 180, 52, 20));
		}
		return edtAllignVelocity;
	}

	/**
	 * This method initializes edtMaxSpeed	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getEdtMaxSpeed() {
		if (edtMaxSpeed == null) {
			edtMaxSpeed = new JTextField();
			edtMaxSpeed.setBounds(new Rectangle(135, 345, 46, 20));
		}
		return edtMaxSpeed;
	}

	/**
	 * This method initializes edtMacVeloc	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getEdtMacVeloc() {
		if (edtMacVeloc == null) {
			edtMacVeloc = new JTextField();
			edtMacVeloc.setBounds(new Rectangle(135, 360, 46, 20));
		}
		return edtMacVeloc;
	}

	/**
	 * This method initializes edtSightDistance	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getEdtSightDistance() {
		if (edtSightDistance == null) {
			edtSightDistance = new JTextField();
			edtSightDistance.setBounds(new Rectangle(135, 375, 46, 20));
		}
		return edtSightDistance;
	}

	/**
	 * This method initializes edtNumberOfBoids	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getEdtNumberOfBoids() {
		if (edtNumberOfBoids == null) {
			edtNumberOfBoids = new JTextField();
			edtNumberOfBoids.setBounds(new Rectangle(135, 405, 46, 20));
		}
		return edtNumberOfBoids;
	}

	/**
	 * This method initializes btnPopulate	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getBtnPopulate() {
		if (btnPopulate == null) {
			btnPopulate = new JButton();
			btnPopulate.setBounds(new Rectangle(45, 435, 121, 31));
			btnPopulate.setText("Repopulate");
		}
		return btnPopulate;
	}

	/**
	 * This method initializes btnAddObstacle	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getBtnAddObstacle() {
		if (btnAddObstacle == null) {
			btnAddObstacle = new JButton();
			btnAddObstacle.setBounds(new Rectangle(45, 480, 121, 31));
			btnAddObstacle.setText("Add Obstacle");
		}
		return btnAddObstacle;
	}

	/**
	 * This method initializes chkRuleFlee	
	 * 	
	 * @return javax.swing.JCheckBox	
	 */
	private JCheckBox getChkRuleFlee() {
		if (chkRuleFlee == null) {
			chkRuleFlee = new JCheckBox();
			chkRuleFlee.setBounds(new Rectangle(15, 60, 136, 24));
			chkRuleFlee.setText("Flee");
		}
		return chkRuleFlee;
	}

	/**
	 * This method initializes chkRuleMoveToAtrractionPnt	
	 * 	
	 * @return javax.swing.JCheckBox	
	 */
	private JCheckBox getChkRuleMoveToAtrractionPnt() {
		if (chkRuleMoveToAtrractionPnt == null) {
			chkRuleMoveToAtrractionPnt = new JCheckBox();
			chkRuleMoveToAtrractionPnt.setBounds(new Rectangle(15, 210, 121, 24));
			chkRuleMoveToAtrractionPnt.setText("Attraction Point");
		}
		return chkRuleMoveToAtrractionPnt;
	}

	/**
	 * This method initializes edtAttractionPoint	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getEdtAttractionPoint() {
		if (edtAttractionPoint == null) {
			edtAttractionPoint = new JTextField();
			edtAttractionPoint.setBounds(new Rectangle(165, 210, 46, 20));
		}
		return edtAttractionPoint;
	}

	/**
	 * Launches this application
	 */
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				BoidUI application = new BoidUI();
				application.getJFrame().setVisible(true);
			}
		});
	}

}
