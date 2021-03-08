package dossier;

public class Etudiant {
	
	private String nom, prenom;
	private int age;
	private static int inscription;
	
	public Etudiant(String nom, String prenom, int age) {
		this.nom= nom;
		this.prenom=prenom;
		this.age=age;
		inscription++;
	}
	
	
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public String getPrenom() {
		return prenom;
	}
	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}












	public int getInscription() {
		return inscription;
	}












	public void setInscription(int inscription) {
		Etudiant.inscription = inscription;
	}
	
		
}
