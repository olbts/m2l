package main;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
public class LoginPage implements ActionListener {
	private BddAccess bdd;
	private Membre membreRef;
	private Responsable responsableRef;
	String utilisateurSelected = new String("membre");
	JFrame frame = new JFrame();
	JButton loginButton = new JButton("Login");
	JButton resetButton = new JButton("Reset");
	JTextField loginField = new JTextField(25);
	JTextField userField = new JTextField(25);
	JPasswordField passwordField = new JPasswordField(25);
	JLabel loginLabel = new JLabel("Login : ");
	JLabel userLabel = new JLabel("Utilisateur : "); 
	JLabel passwordLabel = new JLabel("Password : ");
	JLabel pageLabel = new JLabel("Connexion");
	JRadioButton membre = new JRadioButton("membre");
	JRadioButton responsable = new JRadioButton("responsable");
	ButtonGroup utilisateur = new ButtonGroup();
	JLabel messageLabel = new JLabel();
	private HashMap<String,String> loginInfos = new HashMap<String,String>();
	public LoginPage() {
		utilisateurSelected = "membre";
		membre.setBounds(5,50,80,25);
		responsable.setBounds(85,50,100,25);
		membre.setSelected(true);
        utilisateur.add(membre);
    	utilisateur.add(responsable);
    	membre.addActionListener(this);
    	responsable.addActionListener(this);
    	pageLabel.setBounds(180,20,75,25);
		loginLabel.setBounds(50,100,75,25);
		userLabel.setBounds(50,150,75,25);
		passwordLabel.setBounds(50,200,75,25);
		messageLabel.setBounds(180,300,250,35);
		loginField.setBounds(120 , 100,200,25);
		userField.setBounds(120 , 150,200,25);
		passwordField.setBounds(120 , 200,200,25);
		loginButton.setBounds(150, 250, 75, 25);
		resetButton.setBounds(230, 250, 75, 25);
		loginButton.addActionListener(this);
		resetButton.addActionListener(this);
		loginButton.setFocusable(false);
		resetButton.setFocusable(false);
		frame.add(pageLabel);
		frame.add(membre);
    	frame.add(responsable);
		frame.add(loginLabel);
		frame.add(userLabel);
		frame.add(passwordLabel);
		frame.add(messageLabel);
		frame.add(loginField);
		frame.add(userField);
		frame.add(passwordField);
		frame.add(loginButton);
		frame.add(resetButton);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(420,420);
		frame.setLayout(null);
		frame.setVisible(true);
	}
	
	public void setBDD(BddAccess e) {
		this.bdd = e;
	}
	
	public void setMembre(Membre e) {
		this.membreRef = e;
	}
	public void setResponsable(Responsable e) {
		this.responsableRef = e;
	}
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()== loginButton) {
			String login = loginField.getText();
		    String nom = userField.getText();
		    String mdp = passwordField.getText();
		    String response;
		    String requete = "Select * from "+ utilisateurSelected +" where login = '" + login + "' AND nom = '" + nom + "' AND mdp = '" + mdp + "'"; 
			bdd.creerStatement();
			bdd.envoiRequeteSelect(requete);
			response =  bdd.recupereReponseSelect();
			if(response == "") {
				messageLabel.setText("Informations non valides");
			}
			else {
				
				if(utilisateurSelected == "membre") {
					
			    	
			    	MembrePage membrePage = new MembrePage();
			    	membrePage.setBDD(bdd);
			    	membrePage.setMembre(membreRef);
			    	frame.dispose();
			    }
			    else {
			    	
			    	ResponsablePage responsablePage = new ResponsablePage();
			    	responsablePage.setBDD(bdd);
			    	responsablePage.setResponsable(responsableRef);
			    	frame.dispose();
			    }
				}
		}
		else if ((e.getSource()== resetButton)) {
			userField.setText("");
			loginField.setText("");
			passwordField.setText("");
		}
		else if (e.getSource() == responsable) {
			utilisateurSelected = "responsable";
			
		}
		else if (e.getSource() == membre) {
			utilisateurSelected = "membre";
			
		}
	}
}
