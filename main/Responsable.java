package main;

public class Responsable {
	private BddAccess refBdd; 
	public Responsable(BddAccess e) {
		refBdd = e;
	}
	public void insertConseil(String titre, String contenu ,String date, String type) {
		
		
		String requete = "INSERT INTO  conseil(`titre`, `contenu`, `date`, `type`) VALUES ("+'"'+titre +'"'+ " ," +'"'+ contenu +'"'+ " , " +'"'+ date +'"'+ " , " +'"'+  type +'"'+ ")"; 
	
		refBdd.creerStatement();
		refBdd.envoiRequeteUpdate(requete);
		
	}
}
