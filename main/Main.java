package main;


public class Main {

	

	public static void main(String[] args) {
		
		BddAccess bdd =  new BddAccess();
		
		
		bdd.rechercherDriver("com.mysql.jdbc.Driver");
		bdd.connecterBdd("jdbc:mysql://localhost:3306/m2l?useSSL=false","root","");
		LoginPage loginIhm = new LoginPage();
		Membre membre = new Membre(bdd);
		Responsable responsable = new Responsable(bdd);
		loginIhm.setBDD(bdd);
		loginIhm.setMembre(membre);
		loginIhm.setResponsable(responsable);

	}

}