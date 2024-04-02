package main;

public class Membre {

	private BddAccess refBdd; 
	public Membre(BddAccess e) {
		refBdd = e;
	}
	public String getConseils(String table) {
		
		String requete = "Select titre,contenu,date from conseil where type = '" + table + "'";
		refBdd.creerStatement();
		refBdd.envoiRequeteSelect(requete);
		String response = refBdd.recupereReponseSelect();
		return response ; 
	}
}