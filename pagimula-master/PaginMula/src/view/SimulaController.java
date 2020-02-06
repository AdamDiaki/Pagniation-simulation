package view;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import elementSimula.Processus;
import elementSimula.ProcessusFIFO;
import elementSimula.ProcessusNormale;
import elementSimula.ProcessusPriorite;
import elementSimula.ProcessusPrioriteAP;
import elementSimula.ProcessusPrioriteSP;
import elementSimula.ProcessusSrft;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.ComboBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.RowConstraints;
import metierSimula.Ordonnancement;

public class SimulaController implements Initializable {

	@FXML CheckBox sjfCB = new CheckBox(); 
	@FXML CheckBox srftCB = new CheckBox(); 
	@FXML CheckBox rrCB = new CheckBox();
	@FXML CheckBox pnpCB = new CheckBox(); 
	@FXML CheckBox ppCB = new CheckBox(); 
	@FXML CheckBox lifoCB = new CheckBox();
	@FXML CheckBox fcfsCB = new CheckBox();
	@FXML CheckBox aleaCB = new CheckBox();
	@FXML CheckBox manuelCB = new CheckBox(); 

	@FXML ChoiceBox<Integer> nbrProcessChoiceB; 

	@FXML Button genBtn; 
	@FXML Button resetBtn;
	@FXML Button addProcessusBtn; 

	@FXML GridPane formGP; 

	@FXML TableView<Processus> genProcessTV = new TableView<Processus>(); 
	@FXML TableColumn<Processus, String> labelCol = new TableColumn<Processus,String>("Processus"); 
	@FXML TableColumn<Processus, Integer> tempsExeCol = new TableColumn<Processus, Integer>("Temps éxécution"); 
	@FXML TableColumn<Processus, Integer> tempsArriCol = new TableColumn<Processus, Integer>("Temps Arrivé"); 
	@FXML TableColumn<Processus, Integer> prioriteCol = new TableColumn<Processus, Integer>("Temps Arrivé"); 

	@FXML TableView<Processus> resultatTV = new TableView<Processus>(); 
	@FXML TableColumn<Processus, Integer> pidCol; 
	@FXML TableColumn<Processus, String> nomCol; 
	@FXML TableColumn<Processus, Integer> tempsArrCol; 
	@FXML TableColumn<Processus, Integer> tempsExecCol; 
	@FXML TableColumn<Processus, Integer> prioCol; 

	@FXML Label quantumLabel; 
	@FXML ChoiceBox<Integer> quantumCB;

	private boolean genere = false; 

	@Override
	public void initialize(URL location, ResourceBundle resources) {

		genBtn.setDisable(true);
		genProcessTV.setVisible(false);
		resultatTV.setVisible(false);
		addProcessusBtn.setVisible(false);
		quantumLabel.setVisible(false);
		quantumCB.setVisible(false);

		nbrProcessChoiceB.setItems(FXCollections.observableArrayList(
				1,2,3,4,5,6,7,8,9,10
				));

		quantumCB.setItems(FXCollections.observableArrayList(
				1,2,3,4,5,6,7,8,9,10
				));

		EventHandler<ActionEvent> event = new EventHandler<ActionEvent>() { 

			public void handle(ActionEvent e) {
				CheckBox chk = (CheckBox) e.getSource();
				switch(chk.getText()) {
				case "SJF": 
					if(chk.isSelected()) {
						//sjfCB.setSelected(false);
						srftCB.setSelected(false);
						rrCB.setSelected(false);
						pnpCB.setSelected(false);
						ppCB.setSelected(false);
						lifoCB.setSelected(false);
						fcfsCB.setSelected(false);
						quantumLabel.setVisible(false);
						quantumCB.setVisible(false);
					} 
					break;
				case "SRFT": 
					if(chk.isSelected()) {
						sjfCB.setSelected(false);
						//srftCB.setSelected(false);
						rrCB.setSelected(false);
						pnpCB.setSelected(false);
						ppCB.setSelected(false);
						lifoCB.setSelected(false);
						fcfsCB.setSelected(false);
						quantumLabel.setVisible(false);
						quantumCB.setVisible(false);
					}
					break;
				case "RR": 
					if(chk.isSelected()) {
						sjfCB.setSelected(false);
						srftCB.setSelected(false);
						//rrCB.setSelected(false);
						pnpCB.setSelected(false);
						ppCB.setSelected(false);
						lifoCB.setSelected(false);
						fcfsCB.setSelected(false);

						quantumLabel.setVisible(true);
						quantumCB.setVisible(true);
					} 
					break;

				case "Priorité non préemptif": 
					if(chk.isSelected()) {
						sjfCB.setSelected(false);
						srftCB.setSelected(false);
						rrCB.setSelected(false);
						//pnpCB.setSelected(false);
						ppCB.setSelected(false);
						lifoCB.setSelected(false);
						fcfsCB.setSelected(false);
						quantumLabel.setVisible(false);
						quantumCB.setVisible(false);
					} 
					break;
				case "Priorité préemptif": 
					if(chk.isSelected()) {
						sjfCB.setSelected(false);
						srftCB.setSelected(false);
						rrCB.setSelected(false);
						pnpCB.setSelected(false);
						//ppCB.setSelected(false);
						lifoCB.setSelected(false);
						fcfsCB.setSelected(false);
						quantumLabel.setVisible(false);
						quantumCB.setVisible(false);
					}
					break;
				case "LIFO": 
					if(chk.isSelected()) {
						sjfCB.setSelected(false);
						srftCB.setSelected(false);
						rrCB.setSelected(false);
						pnpCB.setSelected(false);
						ppCB.setSelected(false);
						//lifoCB.setSelected(false);
						fcfsCB.setSelected(false);
						quantumLabel.setVisible(false);
						quantumCB.setVisible(false);
					} 
					break;
				case "FCFS": 
					if(chk.isSelected()) {
						sjfCB.setSelected(false);
						srftCB.setSelected(false);
						rrCB.setSelected(false);
						pnpCB.setSelected(false);
						ppCB.setSelected(false);
						lifoCB.setSelected(false);
						//fcfsCB.setSelected(false);
						quantumLabel.setVisible(false);
						quantumCB.setVisible(false);
					} 
					break;
				}

				if(chk.isSelected() && (aleaCB.isSelected() || manuelCB.isSelected())) {
					genBtn.setDisable(false);
				} else {
					genBtn.setDisable(true);
				}
			} 
		}; 

		sjfCB.setOnAction(event);
		srftCB.setOnAction(event);
		rrCB.setOnAction(event);
		pnpCB.setOnAction(event);
		ppCB.setOnAction(event);
		lifoCB.setOnAction(event);
		fcfsCB.setOnAction(event);

		EventHandler<ActionEvent> eventGeneration = new EventHandler<ActionEvent>() { 
			public void handle(ActionEvent e) {
				CheckBox chk = (CheckBox) e.getSource();
				switch(chk.getText()) {
				case "Aléatoire": 
					if(chk.isSelected()) {
						manuelCB.setSelected(false);
					}
					break;
				case "Manuel": 
					if(chk.isSelected()) {
						aleaCB.setSelected(false);
					}
					break;
				} 
				if(chk.isSelected() && (sjfCB.isSelected() 
						|| srftCB.isSelected() 
						|| rrCB.isSelected()
						|| fcfsCB.isSelected() 
						|| pnpCB.isSelected()
						|| ppCB.isSelected() 
						|| lifoCB.isSelected())
						) {
					genBtn.setDisable(false);
				} else {
					genBtn.setDisable(true);
				}
			}
		}; 

		aleaCB.setOnAction(eventGeneration);
		manuelCB.setOnAction(eventGeneration);


		genBtn.setOnAction(e -> {
			if(!genere) {
				int nbrProcessus = (int)nbrProcessChoiceB.getValue();
				ObservableList<Processus> listProcessus = FXCollections.observableArrayList(); 
				if(manuelCB.isSelected()) {

					ObservableList<Integer> listNbrPossible = FXCollections.observableArrayList();
					int refMax = 10;
					for(int j= 0; j<refMax; j++) {
						listNbrPossible.add(j+1);
					}


					for(int j= 0; j<nbrProcessus; j++) {
						if(pnpCB.isSelected() || ppCB.isSelected()) {
							listProcessus.add(new ProcessusPriorite("Processus "+(j+1)));
						} else {
							listProcessus.add(new ProcessusNormale("Processus "+(j+1)));
						}
					}

					labelCol.setCellValueFactory(
							new PropertyValueFactory<Processus, String>("nom"));
					tempsExeCol.setCellValueFactory(
							new PropertyValueFactory<Processus, Integer>("tempsexe"));
					tempsExeCol.setCellFactory(ComboBoxTableCell.<Processus, Integer>forTableColumn(listNbrPossible));
					tempsExeCol.setCellFactory(tc -> {
						ComboBox<Integer> combo = new ComboBox<>();
						combo.getItems().addAll(listNbrPossible);
						TableCell<Processus, Integer> cell = new TableCell<Processus, Integer>() {
							@Override
							protected void updateItem(Integer reason, boolean empty) {
								super.updateItem(reason, empty);
								if (empty) {
									setGraphic(null);
								} else {
									combo.setValue(reason);
									setGraphic(combo);
								}
							}
						};
						combo.setOnAction(e1 -> {
							genProcessTV.getItems().get(cell.getIndex()).setTempsexe(combo.getValue());;
						});
						return cell ;
					});

					tempsArriCol.setCellValueFactory(
							new PropertyValueFactory<Processus, Integer>("tempsarrive"));
					tempsArriCol.setCellFactory(ComboBoxTableCell.<Processus, Integer>forTableColumn(listNbrPossible));
					tempsArriCol.setCellFactory(tc -> {
						ComboBox<Integer> combo = new ComboBox<>();
						combo.getItems().addAll(listNbrPossible);
						TableCell<Processus, Integer> cell = new TableCell<Processus, Integer>() {
							@Override
							protected void updateItem(Integer reason, boolean empty) {
								super.updateItem(reason, empty);
								if (empty) {
									setGraphic(null);
								} else {
									combo.setValue(reason);
									setGraphic(combo);
								}
							}
						};
						combo.setOnAction(e1 -> {
							genProcessTV.getItems().get(cell.getIndex()).setTempsarrive(combo.getValue());;
						});

						return cell ;
					});
					
					
					prioriteCol.setVisible(false);
					if(pnpCB.isSelected() || ppCB.isSelected()) {
						prioriteCol.setVisible(true);
						genProcessTV.setMaxWidth(570);

						prioriteCol.setCellValueFactory(
								new PropertyValueFactory<Processus, Integer>("priorite"));
						prioriteCol.setCellFactory(ComboBoxTableCell.<Processus, Integer>forTableColumn(listNbrPossible));
						prioriteCol.setCellFactory(tc -> {
							ComboBox<Integer> combo = new ComboBox<>();
							combo.getItems().addAll(listNbrPossible);
							TableCell<Processus, Integer> cell = new TableCell<Processus, Integer>() {
								@Override
								protected void updateItem(Integer reason, boolean empty) {
									super.updateItem(reason, empty);
									if (empty) {
										setGraphic(null);
									} else {
										combo.setValue(reason);
										setGraphic(combo);
									}
								}
							};
							combo.setOnAction(e1 -> {
								genProcessTV.getItems().get(cell.getIndex()).setPriorite(combo.getValue());;
							});

							return cell ;
						});

					}


					
				}  else {
					labelCol.setCellValueFactory(
							new PropertyValueFactory<Processus, String>("nom"));
					tempsExeCol.setCellValueFactory(
							new PropertyValueFactory<Processus, Integer>("tempsexe"));
					tempsArriCol.setCellValueFactory(
							new PropertyValueFactory<Processus, Integer>("tempsarrive"));
					prioriteCol.setCellValueFactory(
							new PropertyValueFactory<Processus, Integer>("priorite"));
					if(pnpCB.isSelected() || ppCB.isSelected()) {
						prioriteCol.setVisible(true);
						genProcessTV.setMaxWidth(570);
					} else {
						prioriteCol.setVisible(false);
					}

					for(int j= 0; j<nbrProcessus; j++) {
						listProcessus.add(new ProcessusNormale("Processus "+(j+1), true));
					}
				}
				genProcessTV.setItems(listProcessus);
				addProcessusBtn.setVisible(true);
				genProcessTV.setVisible(true);
			}



		});

		addProcessusBtn.setOnAction(e -> {
			List<ProcessusNormale> listNormal = new ArrayList<ProcessusNormale>();
			List<ProcessusPriorite> listPriorite = new ArrayList<ProcessusPriorite>();

			for(int i=0; i<genProcessTV.getItems().size(); i++) {
				Processus p = genProcessTV.getItems().get(i); 
				if(pnpCB.isSelected() || ppCB.isSelected()) {
					ProcessusPriorite pp = new ProcessusPriorite(p);
					listPriorite.add(pp);
				} else {
					ProcessusNormale pn = new ProcessusNormale(p);
					listNormal.add(pn); 
				}
				System.out.println(p.toString());
			}
			System.out.println(listNormal.size());
			System.out.println(listPriorite.size());
			Ordonnancement o = new Ordonnancement();
			int quantum = (int)quantumCB.getValue(); 
			List<ProcessusNormale> listRetour = new ArrayList<ProcessusNormale>();
			if(sjfCB.isSelected()) {
				listRetour = o.ordonnancementSJFwList(listNormal);
			} else if(srftCB.isSelected()) {
				listRetour = o.ordonnancementSrftwList(listNormal);
			} else if(rrCB.isSelected()) {
				listRetour = o.ordonnancementRRwList(listNormal, quantum);
			} else if(pnpCB.isSelected()) {
				listRetour = o.ordonnancementPP_SPwList(listPriorite);
			} else if(ppCB.isSelected()) {
				listRetour = o.ordonnancementPP_APwList(listPriorite);
			} else if(lifoCB.isSelected()) {
				listRetour = o.ordonnancementLifowList(listNormal);
			} else if(fcfsCB.isSelected()) {
				listRetour = o.ordonnancementFIFOwList(listNormal);
			}

			pidCol.setCellValueFactory(
					new PropertyValueFactory<Processus, Integer>("pid"));
			nomCol.setCellValueFactory(
					new PropertyValueFactory<Processus, String>("nom"));
			tempsArrCol.setCellValueFactory(
					new PropertyValueFactory<Processus, Integer>("tempsarrive"));
			tempsExecCol.setCellValueFactory(
					new PropertyValueFactory<Processus, Integer>("tempsexe"));
			prioCol.setCellValueFactory(
					new PropertyValueFactory<Processus, Integer>("priorite"));
			if(pnpCB.isSelected() || ppCB.isSelected()) {
				prioCol.setVisible(true);
				resultatTV.setMaxWidth(490);
			} else {
				prioCol.setVisible(false);
				resultatTV.setMaxWidth(420);
			}

			System.out.println("List in controller");
			for(Processus p : listRetour) {
				System.out.println(p.toString());
			}

			resultatTV.setVisible(true);
			ObservableList<Processus> listProcessusRetour = FXCollections.observableArrayList(listRetour); 
			listProcessusRetour.toString();
			resultatTV.setItems(listProcessusRetour);
			addProcessusBtn.setDisable(true);

		});


		resetBtn.setOnAction(e -> {
			resultatTV.setVisible(false);
			genProcessTV.setVisible(false);
			addProcessusBtn.setVisible(false);
		});
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
}
