package elementSimula;

import java.util.Random;

public abstract class Processus implements Comparable<Processus> {

	protected int pid;
	protected int tempsarrive;
	protected int tempsexe;
	protected int priorite;
	private static int id; 
	protected String nom;

	public Processus(String nom,int pid2, int tempsarrive2, int tempsexe2) {
		this.pid=pid2;
		this.tempsarrive=tempsarrive2;
		this.tempsexe=tempsexe2;
		this.nom=nom;
	}
	
	public Processus(String nom, boolean test) {
		this.pid= id;
		id++;
		Random random = new Random();
		int max = 10; 
		int min = 1;
		this.tempsarrive= random.nextInt(max + 1 - min) + min;
		this.tempsexe= random.nextInt(max + 1 - min) + min;
		this.priorite = random.nextInt(max + 1 - min) + min;
		this.nom=nom;
	}
	
	public Processus(String nom) {
		this.nom = nom;
		id++; 
		this.pid = id;
	}
	

	public int getPid() {
		return pid;
	}
	public int getPriorite() {
		return priorite;
	}
	public int getTempsarrive() {
		return tempsarrive;
	}
	public int getTempsexe() {
		return tempsexe;
	}
	public String getNom() {
		return nom;
	}

	/**
	 * @param pid the pid to set
	 */
	public void setPid(int pid) {
		this.pid = pid;
	}

	/**
	 * @param tempsarrive the tempsarrive to set
	 */
	public void setTempsarrive(int tempsarrive) {
		this.tempsarrive = tempsarrive;
	}

	/**
	 * @param tempsexe the tempsexe to set
	 */
	public void setTempsexe(int tempsexe) {
		this.tempsexe = tempsexe;
	}

	/**
	 * @param priorite the priorite to set
	 */
	public void setPriorite(int priorite) {
		this.priorite = priorite;
	}

	/**
	 * @param nom the nom to set
	 */
	public void setNom(String nom) {
		this.nom = nom;
	}
	
	

}
