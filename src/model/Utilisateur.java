package model;

public class Utilisateur {
	int id;
	String pseudo;
	String motDePasse;
	String avatar;
	boolean connected;
	
	public Utilisateur(){
		
	}
	
	public Utilisateur(String pseudo, String motDePasse, String avatar){
	this.pseudo = pseudo;
	this.motDePasse = motDePasse;
	this.avatar = avatar;
	
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getPseudo() {
		return pseudo;
	}

	public void setPseudo(String pseudo) {
		this.pseudo = pseudo;
	}

	public String getMotDePasse() {
		return motDePasse;
	}

	public void setMotDePasse(String motDePasse) {
		this.motDePasse = motDePasse;
	}

	public String getAvatar() {
		return avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}

	public boolean isConnected() {
		return connected;
	}

	public void setConnected(boolean connected) {
		this.connected = connected;
	}
	

}
