package elementSimula;

import java.util.List;

public class ProcessusPrioriteAP extends Processus {

	public ProcessusPrioriteAP(ProcessusPriorite p) {
		super(p.getNom(),p.getPid(), p.getTempsarrive(), p.getTempsexe());
		super.priorite = p.getPriorite();
	}

	public ProcessusPrioriteAP(ProcessusPrioriteAP p) {
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
		Integer b= super.priorite;
		return b.compareTo((Integer)((ProcessusPrioriteAP) processusp).getPriorite());

	}
	
	
	public int minTempsarr( 	List<ProcessusPrioriteAP> ArrProcessusp ) {
		int mintA = Integer.MAX_VALUE;



		for(int i = 0; i < ArrProcessusp.size(); i++){

			if(ArrProcessusp.get(i).getTempsarrive() < mintA) {
				mintA = ArrProcessusp.get(i).getTempsarrive();

			}
		}

		return mintA;
	}

	public int tempsExecutiontotale(List<ProcessusPrioriteAP> arrProcessus2) {
		int tet=0;

		for (int k=0; k < arrProcessus2.size(); k++ ) {
			tet += arrProcessus2.get(k).getTempsexe(); 

		}
		return tet;
	}

}
