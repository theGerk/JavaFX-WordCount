/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wordcount;

import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.stage.FileChooser;

/**
 *
 * @author Benji
 */
public class GUIController implements Initializable {

	@FXML
	private ListView myList;

	@FXML
	private void handleButtonAction(ActionEvent event) {
		try {
			List<String> text = Files.readAllLines(new FileChooser().showOpenDialog(myList.getScene().getWindow()).toPath());
			ObservableList<String> value = FXCollections.observableArrayList();
			for (String t : text) {
				List<String> words = Word.toWordList(t);
				for (String w : words) {
					value.add(w);
				}
			}
			myList.setItems(value);
		} catch (IOException ex) {
			//netbeans generated code...
			//may want to change this line up later
			Logger.getLogger(GUIController.class.getName()).log(Level.SEVERE, null, ex);
		}
	}

	@Override
	public void initialize(URL url, ResourceBundle rb) {
		// TODO
	}

}
