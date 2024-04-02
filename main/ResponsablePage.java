package main;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
public class ResponsablePage implements ActionListener{
	private BddAccess bdd;
	private Responsable responsable ; 
	String[] listeConseil = { "legislation", "remboursement","litige"};
	JComboBox <String> conseils = new JComboBox<String> (listeConseil);
	JFrame frame = new JFrame(); 
	JButton validerButton = new JButton("Valider" );
	JLabel pageLabel = new JLabel("Responsable");
	JLabel conseilLabel = new JLabel(" conseil:");
	JLabel choixLabel = new JLabel("Type conseil:");
	JLabel titreLabel = new JLabel ("Titre:");
	JTextField titreField = new JTextField(100);
	JTextArea conseilField = new JTextArea(40,100);
	public ResponsablePage() {
		pageLabel.setBounds(110,10,75,20);
		conseils.setBounds(210,50,110,25);
		choixLabel.setBounds(0,50,150,25);
		titreLabel.setBounds(0,100,150,25);
		titreField.setBounds(170,100,200,25);
		conseilLabel.setBounds(0,150,150,25);
		conseilField.setBounds(170,150,200,50);
		validerButton.setBounds(210,250,75,25);
		
		
		validerButton.addActionListener(this);
		
		
		pageLabel.setBounds(110,10,110,20);
		frame.add(titreLabel);
		frame.add(titreField);
		frame.add(conseils);
		frame.add(conseilField);
		frame.add(conseilLabel);
		frame.add(choixLabel);
		frame.add(validerButton);
		frame.add(pageLabel);
		frame.setSize(420,420);
		frame.setLayout(null);
		frame.setVisible(true);
	}
	public void setBDD(BddAccess e) {
		this.bdd = e;
	}
	public void setResponsable(Responsable e) {
		this.responsable = e;
	}
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == validerButton) {
			String type = (String) conseils.getSelectedItem();
			String date = "2024/08/04";
			String titre = titreField.getText();
			String contenu = conseilField.getText();
			responsable.insertConseil(titre , contenu,date,type);
			//String requete = "INSERT INTO " + table + "(libelle) VALUES ('" + nouveauConseil + "')"; 
			//bdd.creerStatement();
			//bdd.envoiRequeteUpdate(requete);
			conseilField.setText("");
			
				}

}

}