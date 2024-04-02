package main;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;

public class MembrePage implements ActionListener {
	private BddAccess bdd;
	private Membre membre;

	JLabel pageLabel = new JLabel("Membre");
	String[] listeConseil = { "legislation", "remboursement","litige"};
	JLabel listeConseils = new JLabel("");//new JTextArea(20,50); 
	JComboBox <String> conseils = new JComboBox<String> (listeConseil);
	JButton validerButton = new JButton("Valider" );
	JFrame frame = new JFrame();
	public MembrePage() {
		pageLabel.setBounds(110,10,75,20);
		validerButton.setBounds(250,50,75,25);
		validerButton.addActionListener(this);
		conseils.setBounds(100,50,110,25);
		
		//listeConseils.setPreferredSize(new Dimension(500, 50));
		
		frame.add(conseils);
		frame.add(listeConseils);
		frame.add(pageLabel);
		frame.add(validerButton);

JScrollPane scrPane = new JScrollPane(listeConseils, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
scrPane.setBounds(0,80,410,300);
frame.add(scrPane);
//frame.add(scrPane);
		//JScrollPane scroller = new JScrollPane(listeConseils, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
       // scroller.setPreferredSize(null);;

       // frame.add(scroller);
		
		
		frame.setSize(420,	 420);
		frame.setLayout(null);
		frame.setVisible(true);
		
		
	}
	public void setBDD(BddAccess e) {
		this.bdd = e;
	}
	public void setMembre(Membre e) {
		this.membre = e;
	}
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == validerButton) {
			String table = (String) conseils.getSelectedItem();
			//String requete = "Select * from " + table ;
			//bdd.creerStatement();
			//bdd.envoiRequeteSelect(requete);
			//String response = bdd.recupereReponseSelect();
			String response = membre.getConseils(table);
			this.listeConseils.setText(response);
			
				}

}
	}
