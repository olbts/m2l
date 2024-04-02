package main;

import java.sql.Connection;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.mysql.jdbc.ResultSetMetaData;

public class BddAccess {

	private Connection cnx;
	private Statement stmt;
	
	// Recuperation des resultats de la requete SELECT
	private ResultSet rs;
	
	// Donne les informations sur les tables, colonnes,..
	private ResultSetMetaData resMeta;
	
	// 1ere etape: Recherche et chargement du driver en memoire
	public void rechercherDriver(String driver) {
		try {
			Class.forName(driver);
			System.out.println("Driver trouve !!");
		} catch (ClassNotFoundException e) {
			System.out.println("Driver non trouve !!");
			e.printStackTrace();
		}
	}
	
	// 2eme etape : se connecter a la base de donnees
	public void connecterBdd(String bddName, String login, String mdp) {
		try {
			cnx = DriverManager.getConnection(bddName, login, mdp);
			//cnx = DriverManager.getConnection("jdbc:mysql://localhost:3306/ecole?useSSL=false", "root", "");
			System.out.println("Connexion BDD OK!!");
		} catch (SQLException e) {
			System.out.println("Probleme connexion BDD!!");
			e.printStackTrace();
		}
	}
	
	// 3eme etape: Creation du conteneur de requete
	public void creerStatement() {
		try {
			stmt = cnx.createStatement();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	// 4eme etape: Envoi d'une requete SELECT 
	public void envoiRequeteSelect(String req) {
		try {
			rs = stmt.executeQuery(req);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	// 4eme etape: Envoi d'une requete de type (UPDATE, INSERT, CREATE, DELETE,..)
	public void envoiRequeteUpdate(String req) {
		try {
			stmt.executeUpdate(req);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	// Recuperation de la reponse associee a une requete select
	public String recupereReponseSelect() {
		String reponse = "" ;
		//Recuper de rs les meta data
		//la metadata va chercher le type de structuration de la BDD
		try {
			resMeta=(ResultSetMetaData) rs.getMetaData();
			System.out.println(rs.getClass().getName());
			//recuperer le nombre de colonne de la reponse
			// int nbCols = resMeta.getColumnCount();
			// String nbRows = resMeta.getColumnLabel(2);
			
				
			//affichage des noms des colonnes
			//for(int i = 1; i<= nbCols; i++) {
				//System.out.print(resMeta.getColumnName(i) + " |"+" " );
			//}
			int numeroConseil = 1;
			reponse = "<html><div>";
			//traitement de la requete
			while(rs.next()){
					String stringContenu = "";
					String[]listContenu = rs.getObject(2).toString().split("\\s+");
					for (int i = 0; i < listContenu.length; i++) {
						stringContenu = stringContenu + listContenu[i] + " ";
						if(i%7 == 0 && i != 1 && i != 0) {
							stringContenu = stringContenu + "<br>";
						}
					}
					String titre ="<h4><font color =\"red\">" + numeroConseil + ". " +   rs.getObject(1).toString()+"</font></h4>";
					String date = "<font color =\"blue\">Date de parution :" + rs.getObject(3).toString()+"</font>";
					reponse = reponse  + "<p >"+ titre + "<br>"+ date + "<br>" + stringContenu +"<hr></p>";
					numeroConseil = numeroConseil + 1 ;
				}
			reponse = reponse + "</div></html>";
				
		} catch (SQLException e) {
				e.printStackTrace();
				
		}
		return reponse;
		}
}
