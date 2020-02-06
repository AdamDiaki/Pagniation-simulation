package view;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;

import javafx.stage.Stage;

public class Launcher extends Application {

	public static void main(String[] args) {
		launch(args); 
	}
	
	@Override
	public void start(Stage s) throws Exception {
		TabPane layout = new TabPane(); 
		Tab tab1 = new Tab("Pagina"); 	
		layout.getTabs().add(tab1);
		Tab tab2 = new Tab("Simula"); 
		layout.getTabs().add(tab2);
		tab1.setContent((Node) FXMLLoader.load(this.getClass().getResource("PaginaView.fxml")));
		tab2.setContent((Node) FXMLLoader.load(this.getClass().getResource("SimulaView.fxml")));

		tab1.setClosable(false);
		tab2.setClosable(false);
		
		
		
		Scene scene = new Scene(layout); 
		s.setHeight(720);
		s.setWidth(1080);
		s.setScene(scene); 
		s.show();
	}
	

}
