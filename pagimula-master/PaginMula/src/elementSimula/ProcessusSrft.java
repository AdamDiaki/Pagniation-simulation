package elementSimula;

import java.util.List;

import elementSimula.ProcessusSrft;

public class ProcessusSrft extends Processus {

	public ProcessusSrft(ProcessusNormale p) {
		super(p.getNom(), p.getPid(), p.getTempsarrive(), p.getTempsexe());

	}
	
	public ProcessusSrft(ProcessusSrft p) {
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
		
		
		Integer b= super.tempsexe;
		
		
		return b.compareTo((Integer)((ProcessusSrft) processusp).getTempsexe());
		
	
}

	



	public int minTempsarr( 	List<ProcessusSrft> ArrProcessusp ) {
		int mintA = Integer.MAX_VALUE;



		for(int i = 0; i < ArrProcessusp.size(); i++){

			if(ArrProcessusp.get(i).getTempsarrive() < mintA) {
				mintA = ArrProcessusp.get(i).getTempsarrive();

			}
		}

		return mintA;
	}

	public int tempsExecutiontotale(List<ProcessusSrft> arrProcessus2) {
		int tet=0;

		for (int k=0; k < arrProcessus2.size(); k++ ) {
			tet += arrProcessus2.get(k).getTempsexe(); 

		}
		return tet;
	}


}
