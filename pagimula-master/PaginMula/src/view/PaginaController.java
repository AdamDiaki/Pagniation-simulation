package view;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.text.Text;
import metier.Fifo;
import metier.Generate;
import metier.Lfu;
import metier.Lru;
import metier.Processus;
import metier.ProcessusLfu;

public class PaginaController implements Initializable {
	@FXML CheckBox fifoCb = new CheckBox(); 
	@FXML CheckBox lruCb = new CheckBox(); 
	@FXML CheckBox lfuCb = new CheckBox();
	@FXML CheckBox aleaCb = new CheckBox();
	@FXML CheckBox manuelCb = new CheckBox(); 

	@FXML ChoiceBox<Integer> nbrProcessChoiceB; 
	@FXML ChoiceBox<Integer> refMaxCb; 
	@FXML ChoiceBox<Integer> nbrChaineRefCB; 
	@FXML ChoiceBox<Integer> caseMemoireCB; 
	
	@FXML Button genBtn; 
	@FXML Button resetBtn;
	@FXML Button addProcessusBtn; 

	@FXML Text processusText;

	@FXML GridPane resultPane = new GridPane(); 
	@FXML GridPane processusGP;
	@FXML GridPane formGP; 
	
	@FXML Label formLB = new Label(); 
	

	private boolean genere  = false;
	private List<Integer> list;

	private ArrayList<ArrayList<Processus>> listEtape; 
	private ArrayList<ArrayList<ProcessusLfu>> listEtapeLfu; 
	private ArrayList<ArrayList<Integer>> listEtapeFifo; 


	@Override
	public void initialize(URL location, ResourceBundle resources) {

		System.out.println("Launch");


		genBtn.setDisable(true);
		
		formGP.setVisible(false);
		formLB.setVisible(false);
		nbrChaineRefCB.setVisible(false);
		addProcessusBtn.setVisible(false);
		
		
		nbrProcessChoiceB.setItems(FXCollections.observableArrayList(
				2,3,4,5,6,7,8,9,10,15,20
				));
		refMaxCb.setItems(FXCollections.observableArrayList(
				9, 15, 20, 25
				));
		nbrChaineRefCB.setItems(FXCollections.observableArrayList(
				1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20
				));
		caseMemoireCB.setItems(FXCollections.observableArrayList(
				2,3,4,5
				));
		nbrProcessChoiceB.getSelectionModel().selectFirst();
		refMaxCb.getSelectionModel().selectFirst();
		nbrChaineRefCB.getSelectionModel().selectFirst();
		caseMemoireCB.getSelectionModel().selectFirst();
		
		EventHandler<ActionEvent> event = new EventHandler<ActionEvent>() { 

			public void handle(ActionEvent e) {
				CheckBox chk = (CheckBox) e.getSource();
				switch(chk.getText()) {
				case "FIFO": 
					if(chk.isSelected()) {
						lruCb.setSelected(false);
						lfuCb.setSelected(false);
					} 
					break;
				case "LRU": 
					if(chk.isSelected()) {
						fifoCb.setSelected(false);
						lfuCb.setSelected(false);
					}
					break;
				case "LFU": 
					if(chk.isSelected()) {
						fifoCb.setSelected(false);
						lruCb.setSelected(false);
					} 
				}
				if(chk.isSelected() && (aleaCb.isSelected() || manuelCb.isSelected())) {
					genBtn.setDisable(false);
				} else {
					genBtn.setDisable(true);
				}
			} 

		}; 

		fifoCb.setOnAction(event);
		lruCb.setOnAction(event);
		lfuCb.setOnAction(event);
		


		EventHandler<ActionEvent> eventGeneration = new EventHandler<ActionEvent>() { 

			public void handle(ActionEvent e) {
				CheckBox chk = (CheckBox) e.getSource();
				switch(chk.getText()) {
				case "Aléatoire": 
					if(chk.isSelected()) {
						manuelCb.setSelected(false);
					}
					break;
				case "Manuel": 
					if(chk.isSelected()) {
						aleaCb.setSelected(false);
					}
					break;
				} 
				if(chk.isSelected() && (lruCb.isSelected() || fifoCb.isSelected() || lfuCb.isSelected())) {
					genBtn.setDisable(false);
				} else {
					genBtn.setDisable(true);
				}
			}
		}; 

		aleaCb.setOnAction(eventGeneration);
		manuelCb.setOnAction(eventGeneration);

		nbrChaineRefCB.setOnAction(e -> {
			resetGridPane(formGP);
			
			Integer nbr = nbrChaineRefCB.getValue();
			formGP.setMaxWidth(nbr*50);
			addColsRows(formGP, nbr, 0);
			// Add grid
			ObservableList<Integer> listNbrPossible = FXCollections.observableArrayList();
			int refMax = (int)refMaxCb.getValue(); 
			for(int j= 0; j<refMax; j++) {
				listNbrPossible.add(j+1);
			}
			for(int i=0; i<nbr; i++) {
				Pane pane = new Pane(); 
				pane.setStyle("-fx-border-color: black; -fx-border-width: 1 1 1 1;");
				ChoiceBox<Integer> cb = new ChoiceBox<Integer>(); 
			
				cb.setItems(listNbrPossible);
				cb.getSelectionModel().selectFirst();
				pane.getChildren().add(cb);
				formGP.add(pane, i, 0);
				
			}
		});
		
		addProcessusBtn.setOnAction(e -> {
			if(!genere) {
				
				Integer nbr = nbrChaineRefCB.getValue();
				int nbrCase = (int)caseMemoireCB.getValue();
				this.list = new ArrayList<Integer>();
				
				// Remplissage liste avec les valeurs saisies
				for(int i=0; i<nbr.intValue(); i++) {
					Pane p = (Pane)formGP.getChildren().get(i); 
					ChoiceBox<Integer> choiceBox = (ChoiceBox<Integer>)p.getChildren().get(0);
					Integer valueChoice = (Integer)choiceBox.getValue(); 
					System.out.println("choicebox : "+ choiceBox.getValue());
					list.add(valueChoice);
				}
				
				if(fifoCb.isSelected()) {
					System.out.println("nbr case :" + nbrCase);
					Fifo l = new Fifo(list, nbrCase); 
					l.run();
					listEtapeFifo = l.getListEtape();
					afficherFifo();		
				} else if(lruCb.isSelected()) {
					Lru l = new Lru(list, nbrCase); 
					l.run();
					listEtape = l.getListEtape(); 
					afficherLru();
				} else if(lfuCb.isSelected()) {
					Lfu l = new Lfu(list,nbrCase); 
					l.run();
					listEtapeLfu = l.getListEtape(); 
					afficherLfu();
				}
			}
			
		});
		

		genBtn.setOnAction(e -> {
			if(!genere) {
				int nbrProcessus = (int)nbrProcessChoiceB.getValue();
				int refMax = (int)refMaxCb.getValue();
				int nbrCase = (int)caseMemoireCB.getValue();
				System.out.println("nbrP : " + nbrProcessus + " refMax : " +refMax + "Case mémoire: " + nbrCase);
				
				if(aleaCb.isSelected()) {
					Generate g = new Generate(nbrProcessus, refMax);
					g.genere();
					list = g.getList(); 
					System.out.println("Taille de la list : " + list.size());
					affichageList(list);
					// Constructeur 
					if(fifoCb.isSelected()) {
						Fifo l = new Fifo(list, nbrCase); 
						l.run();
						listEtapeFifo = l.getListEtape();
						for (int i = 0; i < listEtapeFifo.size(); i++) {
							ArrayList<Integer> list = listEtapeFifo.get(i);
							System.out.println("Liste numéro : " + (i+1));
							for(Integer nbrProces : list) {
								System.out.println(nbrProces);
							}
						}
						afficherFifo();
					} else if(lruCb.isSelected()) {
						Lru l = new Lru(list,nbrCase); 
						l.run();
						listEtape = l.getListEtape(); 
						afficherLru();
					} else if(lfuCb.isSelected()) {		
						Lfu l = new Lfu(list,nbrCase); 
						l.run();
						listEtapeLfu = l.getListEtape(); 
						afficherLru();
					}
				} else if(manuelCb.isSelected()){
					showManuelForm();
				}
				genBtn.setDisable(true);
			}
		}); 
		

		resetBtn.setOnAction(e -> {
			resetGridPane(resultPane); 
			resetGridPane(processusGP);
			resetGridPane(formGP);
			disableManuelForm();
			resultPane.setVisible(false);
			genBtn.setDisable(false);
			genere = false;
		});

	}

	public void showManuelForm() {
		formGP.setVisible(true);
		formLB.setVisible(true);
		nbrChaineRefCB.setVisible(true);
		addProcessusBtn.setVisible(true);
	}
	
	public void disableManuelForm() {
		formGP.setVisible(false);
		formLB.setVisible(false);
		nbrChaineRefCB.setVisible(false);
		addProcessusBtn.setVisible(false);
	}
	
	public void affichageList(List<Integer> list) {
		// TODO revoir l'affichage pour les mettres dans un gridPane avec une case chacun
		int nbrCols = list.size(); 
		processusGP.setMaxWidth(list.size()*50);
		
		addColsRows(processusGP, nbrCols, 0);
		// Add grid
		for(int i=0; i<nbrCols; i++) {
			Pane pane = new Pane(); 
			pane.setStyle("-fx-border-color: black; -fx-border-width: 1 1 1 1;");
			processusGP.add(pane, i, 0);
		}

		// Affichage du résultat
		for(int i=0; i<list.size();i++) {
			Integer p = list.get(i); 
			Label label = new Label("" + p);
			processusGP.add(label, i, 0);
			GridPane.setHalignment(label, HPos.CENTER);
		}

	}
	
	public void afficherLru() {

		// Creation des colonnes et lignes
		int nbrCols = listEtape.size();
		int nbrRows = 0;
		for(ArrayList<Processus> list: listEtape) {
			if(list.size() > nbrRows) nbrRows = list.size(); 
		}
		
		addColsRows(resultPane, nbrCols+1, nbrRows);
		addPanelForResult(resultPane);
	

		resultPane.setStyle("-fx-background-color: #23CFDC");

		// Affichage du résultat
		for(int i=0; i< listEtape.size();i++) {
			ArrayList<Processus> list = listEtape.get(i); 
			for(int j=0; j<list.size(); j++) {
				Processus p = list.get(j); 
				Label label = new Label("" + p.getValue());

				resultPane.add(label, i+1, j);
				GridPane.setHalignment(label, HPos.CENTER);

			}
		}
		resultPane.setVisible(true);
		genere = true;
	}
	
	public void afficherLfu() {

		// Creation des colonnes et lignes
		int nbrCols = listEtapeLfu.size();
		int nbrRows = 0;
		for(ArrayList<ProcessusLfu> list: listEtapeLfu) {
			if(list.size() > nbrRows) nbrRows = list.size(); 
		}
		addColsRows(resultPane, nbrCols+1, nbrRows);
		addPanelForResult(resultPane);
		
		resultPane.setStyle("-fx-background-color: #23CFDC");

		// Affichage du résultat
		for(int i=0; i< listEtapeLfu.size();i++) {
			ArrayList<ProcessusLfu> list = listEtapeLfu.get(i); 
			for(int j=0; j<list.size(); j++) {
				ProcessusLfu p = list.get(j); 
				Label label = new Label("" + p.getValue());

				resultPane.add(label, i+1, j);
				GridPane.setHalignment(label, HPos.CENTER);

			}
		}
		resultPane.setVisible(true);
		genere = true;
	}
	
	public void afficherFifo() {

		// Creation des colonnes et lignes
		int nbrCols = listEtapeFifo.size();
		int nbrRows = 0;
		for(ArrayList<Integer> list: listEtapeFifo) {
			if(list.size() > nbrRows) nbrRows = list.size(); 
		}
		
		addColsRows(resultPane, nbrCols+1, nbrRows);
		addPanelForResult(resultPane);
		
		resultPane.setStyle("-fx-background-color: #23CFDC");

		// Affichage du résultat
		for(int i=0; i< listEtapeFifo.size();i++) {
			ArrayList<Integer> list = listEtapeFifo.get(i); 
			for(int j=0; j<list.size(); j++) {
				//Processus p = list.get(j); 
				Label label = new Label("" + list.get(j));
				resultPane.add(label, i+1, j);
				GridPane.setHalignment(label, HPos.CENTER);
			}
		}
		resultPane.setVisible(true);
		genere = true;
	}
	
	public void resetGridPane(GridPane pane) {
		while(pane.getRowConstraints().size() > 0){
			pane.getRowConstraints().remove(0);
		}

		while(pane.getColumnConstraints().size() > 0){
			pane.getColumnConstraints().remove(0);
		}
		pane.getChildren().clear();
		
	}
	
	public void addColsRows(GridPane pane, int nbrCols, int nbrRows) {
		for (int i = 0; i < nbrCols; i++) {
			ColumnConstraints colConst = new ColumnConstraints();
			colConst.setPercentWidth(100.0 / nbrCols);
			pane.getColumnConstraints().add(colConst);
		}
		for (int i = 0; i < nbrRows; i++) {
			RowConstraints rowConst = new RowConstraints();
			rowConst.setPercentHeight(100.0 / nbrRows);
			pane.getRowConstraints().add(rowConst);    

		}
	}
	
	public void addPanelForResult(GridPane gridPane) {
		// Ajout d'un panel aux cellules de la premiere colonne
		int nbrCols = gridPane.getColumnCount(); 
		int nbrRows = gridPane.getRowCount();
		for(int i =0; i<nbrRows; i++) {
			Pane pane = new Pane(); 
			pane.setStyle("-fx-background-color: #FFFFFF;"); 

			Label label = new Label("C"+(i+1)); 
			gridPane.add(pane,0, i);
			gridPane.add(label, 0,i);
			GridPane.setHalignment(label, HPos.CENTER);

		}

		// Ajout d'un panel aux autres cellules 
		for(int i=1; i<=nbrCols; i++) {
			for(int j=0; j<=nbrRows; j++) {
				Pane pane = new Pane(); 
				pane.setStyle("-fx-border-color: black; -fx-border-width: 1 1 0 0;");

				gridPane.add(pane, i, j);
			}
		}
	}
}
