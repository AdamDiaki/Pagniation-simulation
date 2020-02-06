package elementSimula;

public class ProcessusPriorite extends Processus {

	public ProcessusPriorite(ProcessusNormale a, int priorite) {
		super(a.getNom(),a.getPid(), a.getTempsarrive(), a.getTempsexe());
		super.priorite=priorite;
	}
	
	public ProcessusPriorite(String name) {
		super(name); 
	}
	
	public ProcessusPriorite(Processus p ) {
		super(p.nom, p.pid, p.tempsarrive, p.tempsexe);
		super.priorite = p.priorite;
	}
	
	public void setPriorite(int priorite) {
		super.setPriorite(priorite);
	}
	
	public void setTempsarrive(int tempsarr) {
		super.setTempsarrive(tempsarr);
	}
	
	public void setTempsexe(int tempsexe) {
		super.setTempsexe(tempsexe); 
	}
	
	public int getPriorite() {
		return super.priorite;
	}
	@Override
	public int compareTo(Processus o) {
		// TODO Auto-generated method stub
		return 0;
	}

	
}
