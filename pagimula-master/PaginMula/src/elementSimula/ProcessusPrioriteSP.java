package elementSimula;

public class ProcessusPrioriteSP extends Processus {

	public ProcessusPrioriteSP(ProcessusPriorite p) {
		super(p.getNom(),p.getPid(), p.getTempsarrive(), p.getTempsexe());
		super.priorite = p.getPriorite();
	}
	
	public ProcessusPrioriteSP(ProcessusPrioriteSP p) {
		super(p.getNom(),p.getPid(), p.getTempsarrive(), p.getTempsexe());
		super.priorite = p.getPriorite();
	}
	
	public void setPid(int pid) {
		super.pid = pid;
	}

	public void setTempsarrive(int tempsarrive) {
		super.tempsarrive = tempsarrive;
	}
	public void setTempsexe(int tempsexe) {
		super.tempsexe = tempsexe;
	}


	public int getPid() {
		return super.pid;
	}

	public int getTempsarrive() {
		return super.tempsarrive;
	}

	public int getTempsexe() {
		return super.tempsexe;
	}
	
   
    public int getPriorite() {
    	
    	return super.priorite;
    }


	public String toString() {
		return "| "+nom +"\t|"+ pid + "\t|" + tempsarrive + "\t|" + tempsexe + "\t|"+priorite+"\t|";
				
	}
	
	
	
	
	@Override
	public int compareTo(Processus processusp) {
		Integer a =tempsarrive;
		Integer b= priorite;
		Integer c= tempsexe;
		
		
		
		int comparerProcesus = a.compareTo((Integer)((ProcessusPrioriteSP) processusp).getTempsarrive());
		
		 if ( comparerProcesus ==0) {
			 
			 comparerProcesus = b.compareTo((Integer)((ProcessusPrioriteSP) processusp).getPriorite());
			 
			 if ( comparerProcesus ==0) {
				 return c.compareTo((Integer)((ProcessusPrioriteSP) processusp).getTempsexe());
			 }
			 
			
			 
		 }
		 
		return comparerProcesus;
	}

}
