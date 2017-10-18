package model;

public class Salon {
	int id;
	String name;
	String mdp;
	int createurId;
	boolean connected;
	
	public Salon(){
		
	}
	
	public Salon(String name, String mdp, int createurId){
		this.name = name;
		this.mdp = mdp;
		this.createurId = createurId;
		
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getMdp() {
		return mdp;
	}

	public void setMdp(String mdp) {
		this.mdp = mdp;
	}

	public int getCreateurId() {
		return createurId;
	}

	public void setCreateurId(int createurId) {
		this.createurId = createurId;
	}
	
	public void seDeconnecter(){
		this.connected = false;
	}
	
	
}
