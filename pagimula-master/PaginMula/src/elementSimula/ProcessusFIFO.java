package elementSimula;

public class ProcessusFIFO extends Processus {

	public ProcessusFIFO(ProcessusNormale p) {
		super(p.getNom(), p.getPid(), p.getTempsarrive(), p.getTempsexe());

		// TODO Auto-generated constructor stub
	}
	
	public ProcessusFIFO(ProcessusFIFO p) {
		super(p.getNom(), p.getPid(), p.getTempsarrive(), p.getTempsexe());
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
	



	public String toString() {
		return "| "+nom +"\t|"+ pid + "\t|" + tempsarrive + "\t|" + tempsexe + "\t|";
				
	}

	@Override
	public int compareTo(Processus processusp) {
		Integer a =super.tempsarrive;
		
		return a.compareTo((Integer)((ProcessusFIFO) processusp).getTempsarrive());

	}

}
