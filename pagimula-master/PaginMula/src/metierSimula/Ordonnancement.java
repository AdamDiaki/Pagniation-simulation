package metierSimula;

import java.util.ArrayList;

import java.util.Collections;
import java.util.List;

import elementSimula.Processus;
import elementSimula.ProcessusFIFO;
import elementSimula.ProcessusNormale;
import elementSimula.ProcessusPriorite;
import elementSimula.ProcessusPrioriteAP;
import elementSimula.ProcessusPrioriteSP;
import elementSimula.ProcessusSrft;


public class Ordonnancement{

	private List<ProcessusNormale> listeProcessusNormale = new ArrayList<ProcessusNormale>();

	private List<ProcessusPriorite> listeProcessusPriorite = new ArrayList<ProcessusPriorite>();
	
	public Ordonnancement() {
		
	}

	public void ajouterProcessus(Processus processusp) {
		if(processusp instanceof ProcessusNormale) {
			this.listeProcessusNormale.add((ProcessusNormale) processusp);
		}

		if(processusp instanceof ProcessusPriorite) {
			this.listeProcessusPriorite.add((ProcessusPriorite) processusp);
		}
	}
	public void clear() {
		listeProcessusNormale.clear();
		listeProcessusPriorite.clear();

	}


	//DEBUT SRFT
	public void ordonnancementSrft(){


		List<ProcessusSrft> ArrProcessus2 = new ArrayList<ProcessusSrft>(); 
		ProcessusNormale a = new ProcessusNormale("",0,0,0);

		for (ProcessusNormale e:  this.listeProcessusNormale) {
			ProcessusSrft p1 = new ProcessusSrft(e);
			ArrProcessus2.add(p1);

		}

		List<ProcessusSrft> subarrayinftc = new ArrayList<ProcessusSrft>();

		ProcessusSrft p = new ProcessusSrft(a);
		ProcessusSrft time = new ProcessusSrft(a);


		int t= time.tempsExecutiontotale ( ArrProcessus2);
		int tc= time.minTempsarr(ArrProcessus2);
		System.out.println("|nom\t|ID\t|TA\t|TR\t|");
		while(t>0) {

			for (int i =0; i< ArrProcessus2.size(); i++) {
				if(ArrProcessus2.get(i).getTempsarrive() <=tc && ArrProcessus2.get(i).getTempsexe() >0 ) {
					subarrayinftc.add(ArrProcessus2.get(i));
				}
			}

			if(!subarrayinftc.isEmpty()) {
				Collections.sort(subarrayinftc);
				p=  subarrayinftc.get(0);
				System.out.println(p);
			}

			else { 
				Collections.sort(ArrProcessus2);
				p=  ArrProcessus2.get(0);
				System.out.println(p);
			}

			if (p.getTempsexe() > 0 ) {
				int n = p.getTempsexe();
				p.setTempsexe(n-1);
			}

			int pid = p.getPid();

			if(p.getTempsexe()>0 ) {
				for (int j =0; j<ArrProcessus2.size(); j++) {
					if(ArrProcessus2.get(j).getPid() == pid  ) {
						//bon code si new add ne marche pas
						/*ArrProcessus2.remove(j);
						ArrProcessus2.add(p);*/ 
						ArrProcessus2.set(j, p);

					}
				}
			}
			else{
				for (int j =0; j<ArrProcessus2.size(); j++) {
					if(ArrProcessus2.get(j).getPid() == pid  ) {
						ArrProcessus2.remove(j);
					}
				}
			}


			subarrayinftc.clear();
			tc ++;
			t--;
		}
	}
	
	public List<ProcessusNormale> ordonnancementSrftwList(List<ProcessusNormale> list){
		List<ProcessusNormale> listRetour = new ArrayList<ProcessusNormale>();
		
		List<ProcessusSrft> ArrProcessus2 = new ArrayList<ProcessusSrft>(); 
		ProcessusNormale a = new ProcessusNormale("",0,0,0);

		for (ProcessusNormale e: list) {
			ProcessusSrft p1 = new ProcessusSrft(e);
			ArrProcessus2.add(p1);
		}

		List<ProcessusSrft> subarrayinftc = new ArrayList<ProcessusSrft>();

		ProcessusSrft p = new ProcessusSrft(a);
		ProcessusSrft time = new ProcessusSrft(a);


		int t= time.tempsExecutiontotale ( ArrProcessus2);
		int tc= time.minTempsarr(ArrProcessus2);
		System.out.println("|nom\t|ID\t|TA\t|TR\t|");
		while(t>0) {

			for (int i =0; i< ArrProcessus2.size(); i++) {
				if(ArrProcessus2.get(i).getTempsarrive() <=tc && ArrProcessus2.get(i).getTempsexe() >0 ) {
					subarrayinftc.add(ArrProcessus2.get(i));
				}
			}

			if(!subarrayinftc.isEmpty()) {
				Collections.sort(subarrayinftc);
				p=  subarrayinftc.get(0);
				listRetour.add(new ProcessusNormale(p));
				System.out.println(p);
			}

			else { 
				Collections.sort(ArrProcessus2);
				p=  ArrProcessus2.get(0);
				listRetour.add(new ProcessusNormale(p));
				System.out.println(p);
			}

			if (p.getTempsexe() > 0 ) {
				int n = p.getTempsexe();
				p.setTempsexe(n-1);
			}

			int pid = p.getPid();

			if(p.getTempsexe()>0 ) {
				for (int j =0; j<ArrProcessus2.size(); j++) {
					if(ArrProcessus2.get(j).getPid() == pid  ) {
						//bon code si new add ne marche pas
						/*ArrProcessus2.remove(j);
						ArrProcessus2.add(p);*/ 
						ArrProcessus2.set(j, p);

					}
				}
			}
			else{
				for (int j =0; j<ArrProcessus2.size(); j++) {
					if(ArrProcessus2.get(j).getPid() == pid  ) {
						ArrProcessus2.remove(j);
					}
				}
			}


			subarrayinftc.clear();
			tc ++;
			t--;
		}
		
		System.out.println("List retour");
		for(ProcessusNormale p2 : listRetour) {
			System.out.println(p2.toString());
		}
		return listRetour;
	}
	//FIN SRFT

	//DEBUT PP_AP
	public void ordonnancementPP_AP(){
		List<ProcessusPrioriteAP> listRetour = new ArrayList<ProcessusPrioriteAP>();
		
		List<ProcessusPrioriteAP> ArrProcessus2 = new ArrayList<ProcessusPrioriteAP>(); 

		ProcessusNormale a = new ProcessusNormale("",0, 0, 0);
		ProcessusPriorite b = new ProcessusPriorite(a, 0);

		for (ProcessusPriorite e:  this.listeProcessusPriorite) {
			ProcessusPrioriteAP p1 = new ProcessusPrioriteAP(e);
			ArrProcessus2.add(p1);

		}

		List<ProcessusPrioriteAP> subarrayinftc = new ArrayList<ProcessusPrioriteAP>();

		ProcessusPrioriteAP p = new ProcessusPrioriteAP(b);
		ProcessusPrioriteAP time = new ProcessusPrioriteAP(b);


		int t= time.tempsExecutiontotale ( ArrProcessus2);
		int tc= time.minTempsarr(ArrProcessus2);

		System.out.println("|nom\t|ID\t|TA\t|TR\t|PR\t|");

		while(t>0) {

			for (int i =0; i< ArrProcessus2.size(); i++) {
				if(ArrProcessus2.get(i).getTempsarrive() <=tc && ArrProcessus2.get(i).getTempsexe() >0 ) {
					subarrayinftc.add(ArrProcessus2.get(i));
				}
			}

			if(!subarrayinftc.isEmpty()) {
				Collections.sort(subarrayinftc);
				p=  subarrayinftc.get(0);
				listRetour.add(new ProcessusPrioriteAP(p));
				System.out.println(p);
			}

			else { 
				Collections.sort(ArrProcessus2);
				p=  ArrProcessus2.get(0);
				listRetour.add(new ProcessusPrioriteAP(p));
				System.out.println(p);

			}

			if (p.getTempsexe() > 0 ) {
				int n = p.getTempsexe();
				p.setTempsexe(n-1);
			}

			int pid = p.getPid();

			if(p.getTempsexe()>0 ) {
				for (int j =0; j<ArrProcessus2.size(); j++) {
					if(ArrProcessus2.get(j).getPid() == pid  ) {
						ArrProcessus2.set(j, p);
					}
				}
			}
			else{
				for (int j =0; j<ArrProcessus2.size(); j++) {
					if(ArrProcessus2.get(j).getPid() == pid  ) {
						ArrProcessus2.remove(j);
					}
				}
			}

			subarrayinftc.clear();
			tc ++;
			t--;
		}

	}
	
	public List<ProcessusNormale> ordonnancementPP_APwList(List<ProcessusPriorite> list){
		
		List<ProcessusNormale> listRetour = new ArrayList<ProcessusNormale>();
		List<ProcessusPrioriteAP> ArrProcessus2 = new ArrayList<ProcessusPrioriteAP>(); 

		ProcessusNormale a = new ProcessusNormale("",0, 0, 0);
		ProcessusPriorite b = new ProcessusPriorite(a, 0);

		for (ProcessusPriorite e: list) {
			ProcessusPrioriteAP p1 = new ProcessusPrioriteAP(e);
			ArrProcessus2.add(p1);

		}

		List<ProcessusPrioriteAP> subarrayinftc = new ArrayList<ProcessusPrioriteAP>();

		ProcessusPrioriteAP p = new ProcessusPrioriteAP(b);
		ProcessusPrioriteAP time = new ProcessusPrioriteAP(b);


		int t= time.tempsExecutiontotale ( ArrProcessus2);
		int tc= time.minTempsarr(ArrProcessus2);

		System.out.println("|nom\t|ID\t|TA\t|TR\t|PR\t|");

		while(t>0) {

			for (int i =0; i< ArrProcessus2.size(); i++) {
				if(ArrProcessus2.get(i).getTempsarrive() <=tc && ArrProcessus2.get(i).getTempsexe() >0 ) {
					subarrayinftc.add(ArrProcessus2.get(i));
				}
			}

			if(!subarrayinftc.isEmpty()) {
				Collections.sort(subarrayinftc);
				p=  subarrayinftc.get(0);
				listRetour.add(new ProcessusNormale(p));
				System.out.println(p);
			}

			else { 
				Collections.sort(ArrProcessus2);
				p=  ArrProcessus2.get(0);
				listRetour.add(new ProcessusNormale(p));
				System.out.println(p);

			}

			if (p.getTempsexe() > 0 ) {
				int n = p.getTempsexe();
				p.setTempsexe(n-1);
			}

			int pid = p.getPid();

			if(p.getTempsexe()>0 ) {
				for (int j =0; j<ArrProcessus2.size(); j++) {
					if(ArrProcessus2.get(j).getPid() == pid  ) {
						ArrProcessus2.set(j, p);
					}
				}
			}
			else{
				for (int j =0; j<ArrProcessus2.size(); j++) {
					if(ArrProcessus2.get(j).getPid() == pid  ) {
						ArrProcessus2.remove(j);
					}
				}
			}

			subarrayinftc.clear();
			tc ++;
			t--;
		}
		
		System.out.println("List retour");
		for(ProcessusNormale p2 : listRetour) {
			System.out.println(p2.toString());
		}
		return listRetour;
	}


	//FIN PP_AP
	//fifo debut


	public  void ordonnancementFIFO(){
		List<ProcessusFIFO> ArrProcessus2 = new ArrayList<ProcessusFIFO>(); 
		ProcessusNormale a = new ProcessusNormale("",0,0,0);
		ProcessusFIFO p = new ProcessusFIFO(a);
		for (ProcessusNormale e:  this.listeProcessusNormale) {
			ProcessusFIFO p1 = new ProcessusFIFO(e);
			ArrProcessus2.add(p1);

		}

		Collections.sort(ArrProcessus2);
		System.out.println("|nom\t|ID\t|TA\t|TR\t|");
		for(int i=0; i < ArrProcessus2.size(); i++ ) {
			for(int j=0; j < ArrProcessus2.get(i).getTempsexe(); j++ ) {
				p=ArrProcessus2.get(i);
				System.out.println(p);
			}
		}

	}
	
	public List<ProcessusNormale> ordonnancementFIFOwList(List<ProcessusNormale> list){
		List<ProcessusNormale> listRetour = new ArrayList<ProcessusNormale>(); 
		List<ProcessusFIFO> ArrProcessus2 = new ArrayList<ProcessusFIFO>(); 
		ProcessusNormale a = new ProcessusNormale("",0,0,0);
		ProcessusFIFO p = new ProcessusFIFO(a);
		for (ProcessusNormale e:  list) {
			ProcessusFIFO p1 = new ProcessusFIFO(e);
			ArrProcessus2.add(p1);

		}

		Collections.sort(ArrProcessus2);
		System.out.println("|nom\t|ID\t|TA\t|TR\t|");
		for(int i=0; i < ArrProcessus2.size(); i++ ) {
			for(int j=0; j < ArrProcessus2.get(i).getTempsexe(); j++ ) {
				p=ArrProcessus2.get(i);
				listRetour.add(new ProcessusNormale(p));
				System.out.println(p);
			}
		}
		System.out.println("List retour");
		for(ProcessusNormale p2 : listRetour) {
			System.out.println(p2.toString());
		}
		return listRetour;
	}
	//fifo fin

	//DEBUT LIFO

	public void ordonnancementLifo(){
		List<ProcessusNormale> ArrProcessus2 = new ArrayList<ProcessusNormale>();
		ProcessusNormale p;
		for (Processus pros:  this.listeProcessusNormale) {
			ArrProcessus2.add( (ProcessusNormale) pros);
		}
		//List<ProcessusNormale> return_ordonnancement = new ArrayList<ProcessusNormale>();
		Collections.sort(ArrProcessus2);
		System.out.println("|nom\t|ID\t|TA\t|TR\t|");
		for(int i=ArrProcessus2.size()-1 ; i >= 0; i--) {
			for(int j=0; j < ArrProcessus2.get(i).getTempsexe(); j++ ) {
				//return_ordonnancement.add(ArrProcessus2.get(i));
				p=ArrProcessus2.get(i);
				System.out.println(p);
			}
		}
		//return return_ordonnancement;
	}
	
	public List<ProcessusNormale> ordonnancementLifowList(List<ProcessusNormale> list){
		List<ProcessusNormale> listRetour = new ArrayList<ProcessusNormale>();
		List<ProcessusNormale> ArrProcessus2 = new ArrayList<ProcessusNormale>();
		ProcessusNormale p;
		for (Processus pros: list) {
			ArrProcessus2.add( (ProcessusNormale) pros);
		}
		//List<ProcessusNormale> return_ordonnancement = new ArrayList<ProcessusNormale>();
		Collections.sort(ArrProcessus2);
		System.out.println("|nom\t|ID\t|TA\t|TR\t|");
		for(int i=ArrProcessus2.size()-1 ; i >= 0; i--) {
			for(int j=0; j < ArrProcessus2.get(i).getTempsexe(); j++ ) {
				//return_ordonnancement.add(ArrProcessus2.get(i));
				p=ArrProcessus2.get(i);
				listRetour.add(new ProcessusNormale(p));
				System.out.println(p);
			}
		}
		
		System.out.println("List retour");
		for(ProcessusNormale p2 : listRetour) {
			System.out.println(p2.toString());
		}
		
		return listRetour;
		//return return_ordonnancement;
	}


	//FIN LIFO

	//SJF

	public void ordonnancementSJF(){
		List<ProcessusNormale> ArrProcessus2 = new ArrayList<ProcessusNormale>();
		ProcessusNormale p =new ProcessusNormale("",0,0,0);
		for (Processus pros:  this.listeProcessusNormale) {
			ArrProcessus2.add( (ProcessusNormale) pros);
		}

		//List<ProcessusNormale> return_ordonnancement = new ArrayList<ProcessusNormale>();
		Collections.sort(ArrProcessus2);
		System.out.println("|nom\t|ID\t|TA\t|TR\t|");
		for(int i=0; i < ArrProcessus2.size(); i++ ) {
			for(int j=0; j < ArrProcessus2.get(i).getTempsexe(); j++ ) {
				//return_ordonnancement.add(ArrProcessus2.get(i));
				p=ArrProcessus2.get(i);
				System.out.println(p);
			}
		}
		//return return_ordonnancement;
	}
	
	public List<ProcessusNormale> ordonnancementSJFwList(List<ProcessusNormale> list){
		List<ProcessusNormale> listRetour = new ArrayList<ProcessusNormale>();
		List<ProcessusNormale> ArrProcessus2 = new ArrayList<ProcessusNormale>();
		ProcessusNormale p =new ProcessusNormale("",0,0,0);
		for (Processus pros:  list) {
			ArrProcessus2.add( (ProcessusNormale) pros);
		}

		//List<ProcessusNormale> return_ordonnancement = new ArrayList<ProcessusNormale>();
		Collections.sort(ArrProcessus2);
		System.out.println("|nom\t|ID\t|TA\t|TR\t|");
		for(int i=0; i < ArrProcessus2.size(); i++ ) {
			for(int j=0; j < ArrProcessus2.get(i).getTempsexe(); j++ ) {
				//return_ordonnancement.add(ArrProcessus2.get(i));
				p=ArrProcessus2.get(i);
				listRetour.add(new ProcessusNormale(p));
				System.out.println(p);
			}
		}
		System.out.println("List retour");
		for(ProcessusNormale pn : listRetour) {
			System.out.println(pn.toString());
		}
		return listRetour; 
		//return return_ordonnancement;
	}



	//FIFO

	//DEBUT NON PREMPTIF

	public void ordonnancementPP_SP(){

		List<ProcessusPrioriteSP> ArrProcessus2 = new ArrayList<ProcessusPrioriteSP>(); 
		ProcessusPrioriteSP p ;


		for (ProcessusPriorite e:  this.listeProcessusPriorite) {
			ProcessusPrioriteSP p1 = new ProcessusPrioriteSP(e);
			ArrProcessus2.add(p1);

		}

		//List<ProcessusPrioriteSP> return_ordonnancement = new ArrayList<ProcessusPrioriteSP>();

		Collections.sort(ArrProcessus2);
		System.out.println("|nom\t|ID\t|TA\t|TR\t|PR\t|");
		for(int i=0; i < ArrProcessus2.size(); i++ ) {
			for(int j=0; j < ArrProcessus2.get(i).getTempsexe(); j++ ) {
				//return_ordonnancement.add(ArrProcessus2.get(i));
				p= ArrProcessus2.get(i);
				System.out.println(p);
			}
		}
		//return return_ordonnancement;
	}
	
	public List<ProcessusNormale> ordonnancementPP_SPwList(List<ProcessusPriorite> list){
		List<ProcessusNormale> listRetour = new ArrayList<ProcessusNormale>(); 
		List<ProcessusPrioriteSP> ArrProcessus2 = new ArrayList<ProcessusPrioriteSP>(); 
		ProcessusPrioriteSP p ;


		for (ProcessusPriorite e:  list) {
			ProcessusPrioriteSP p1 = new ProcessusPrioriteSP(e);
			ArrProcessus2.add(p1);

		}

		//List<ProcessusPrioriteSP> return_ordonnancement = new ArrayList<ProcessusPrioriteSP>();

		Collections.sort(ArrProcessus2);
		System.out.println("|nom\t|ID\t|TA\t|TR\t|PR\t|");
		for(int i=0; i < ArrProcessus2.size(); i++ ) {
			for(int j=0; j < ArrProcessus2.get(i).getTempsexe(); j++ ) {
				//return_ordonnancement.add(ArrProcessus2.get(i));
				p= ArrProcessus2.get(i);
				listRetour.add(new ProcessusNormale(p));
				System.out.println(p);
			}
		}
		System.out.println("List retour");
		for(ProcessusNormale pn : listRetour) {
			System.out.println(pn.toString());
		}
		return listRetour;
		//return return_ordonnancement;
	}

	//FIN NON PREMPTIF


	//DEBUT ROUND ROBBING

	public void ordonnancementRR(int quantum){

		List<ProcessusNormale> arrProcessus2 = this.listeProcessusNormale;
		//List<ProcessusNormale> arrProcessus2 = list; 
		
		Collections.sort(arrProcessus2);
		ProcessusNormale p;
		int nbrQuantum = 1;
		while(!verifieTempsExecutionTotale(arrProcessus2)){
			System.out.println("Quantum numéro : " + nbrQuantum);
			if(arrProcessus2.size() > 0) {

				p = arrProcessus2.get(0);
				for(int i=0; i<quantum; i++) {
					if(p.getTempsexe() != 0) {
						System.out.println(p.toString());
						int n = p.getTempsexe();
						p.setTempsexe(n-1);
						if(p.getTempsexe() == 0) {
							if(arrProcessus2.size() > 0) {
								arrProcessus2.remove(0);
								if(arrProcessus2.size() > 0) {
									p = arrProcessus2.get(0);
								}
							}
						}
					} else {
						if(arrProcessus2.size() > 0) {
							arrProcessus2.remove(0);
							if(arrProcessus2.size() > 0) {
								p = arrProcessus2.get(0);
							}
							i--;
						}
					}
				}
				int idProcessusEnCours = p.getPid();
				int idProcessusPremierePosition = 0;
				if(arrProcessus2.size() > 0) {
					idProcessusPremierePosition = arrProcessus2.get(0).getPid();
				}

				if(idProcessusEnCours == idProcessusPremierePosition) {
					arrProcessus2.remove(0);
					if(p.getTempsexe() > 0) {
						arrProcessus2.add(p);
					}
				}
			}
			nbrQuantum++;
		}

	}
	
	public List<ProcessusNormale> ordonnancementRRwList(List<ProcessusNormale> list,int quantum){
		List<ProcessusNormale> listRetour = new ArrayList<ProcessusNormale>(); 
		//List<ProcessusNormale> arrProcessus2 = this.listeProcessusNormale;
		List<ProcessusNormale> arrProcessus2 = list; 
		
		Collections.sort(arrProcessus2);
		ProcessusNormale p;
		int nbrQuantum = 1;
		while(!verifieTempsExecutionTotale(arrProcessus2)){
			System.out.println("Quantum numéro : " + nbrQuantum);
			if(arrProcessus2.size() > 0) {

				p = arrProcessus2.get(0);
				for(int i=0; i<quantum; i++) {
					if(p.getTempsexe() != 0) {
						listRetour.add(new ProcessusNormale(p));
						System.out.println(p.toString());
						int n = p.getTempsexe();
						p.setTempsexe(n-1);
						if(p.getTempsexe() == 0) {
							if(arrProcessus2.size() > 0) {
								arrProcessus2.remove(0);
								if(arrProcessus2.size() > 0) {
									p = arrProcessus2.get(0);
								}
							}
						}
					} else {
						if(arrProcessus2.size() > 0) {
							arrProcessus2.remove(0);
							if(arrProcessus2.size() > 0) {
								p = arrProcessus2.get(0);
							}
							i--;
						}
					}
				}
				int idProcessusEnCours = p.getPid();
				int idProcessusPremierePosition = 0;
				if(arrProcessus2.size() > 0) {
					idProcessusPremierePosition = arrProcessus2.get(0).getPid();
				}

				if(idProcessusEnCours == idProcessusPremierePosition) {
					arrProcessus2.remove(0);
					if(p.getTempsexe() > 0) {
						arrProcessus2.add(p);
					}
				}
			}
			nbrQuantum++;
		}
		System.out.println("List retour");
		for(ProcessusNormale pn : listRetour) {
			System.out.println(pn.toString());
		}
		return listRetour;
	}

	private boolean verifieTempsExecutionTotale(List<ProcessusNormale> arrProcessus2) {
		// TODO Auto-generated method stub
		int tet=0;
		for (int k=0; k < arrProcessus2.size(); k++ ) {
			tet += arrProcessus2.get(k).getTempsexe(); 
		}

		if(tet == 0) {
			return true ;
		}
		else {
			return false;
		}

	}
	//FIN ROUND TOBING
}
//< >

