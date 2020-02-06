package elementSimula;

public class ProcessusNormale extends Processus {

	public ProcessusNormale(String nom2,int pid2, int tempsarrive2, int tempsexe2) {
		super(nom2,pid2, tempsarrive2, tempsexe2);
	}
	
	public ProcessusNormale(String name) {
		super(name);
	}
	
	public ProcessusNormale(String name, boolean test) {
		// le boolean sert juste à aller dans ce constructeur pour aller dabs le constructeur super aleatoire
		super(name,test);
	}
	public ProcessusNormale(Processus p) {
		super(p.nom, p.pid, p.tempsarrive, p.tempsexe); 
	}
	
	public ProcessusNormale(ProcessusNormale p) {
		super(p.nom, p.pid, p.tempsarrive, p.tempsexe); 
	}
	
	public ProcessusNormale(ProcessusPrioriteSP p) {
		super(p.nom, p.pid, p.tempsarrive, p.tempsexe); 
		super.priorite = p.getPriorite();
	}
	
	public ProcessusNormale(ProcessusFIFO p) {
		super(p.nom, p.pid, p.tempsarrive, p.tempsexe); 
	}
	
	public ProcessusNormale(ProcessusPrioriteAP p) {
		super(p.nom, p.pid, p.tempsarrive, p.tempsexe); 
		super.priorite = p.getPriorite();
	}
	
	public ProcessusNormale(ProcessusSrft p) {
		super(p.getNom(), p.getPid(), p.getTempsarrive(), p.getTempsexe());
	}
	
	
	public void setPid(int pid) {
		this.pid = pid;
	}

	public void setTempsarrive(int tempsarrive) {
		this.tempsarrive = tempsarrive;
	}
	public void setTempsexe(int tempsexe) {
		this.tempsexe = tempsexe;
	}


	public int getPid() {
		return pid;
	}

	public int getTempsarrive() {
		return tempsarrive;
	}
   
	@Override
	public String getNom() {
		// TODO Auto-generated method stub
		return super.getNom();
	}
	
	
	public String toString() {
		return "| "+nom +"\t|"+ pid + "\t|" + tempsarrive + "\t|" + tempsexe + "\t|";
				
	}

	
	
	
	
	@Override
	public int compareTo(Processus processusp) {
		Integer a =tempsarrive;
		Integer b= tempsexe;
		Integer c= priorite;
		Integer d=  processusp.getPriorite();
		
		
		int comparerProcesus = a.compareTo((Integer)((ProcessusNormale) processusp).getTempsarrive());
		
		 if ( comparerProcesus ==0) {
			 
			 comparerProcesus = b.compareTo((Integer)((ProcessusNormale) processusp).getTempsexe());
			 
			 if ( comparerProcesus ==0) {
				 return c.compareTo(d);
			 }
			 
			
			 
		 }
		 
		return comparerProcesus;
	}

	public int getTempsexe() {
		// TODO Auto-generated method stub
		return tempsexe;
	}

}
