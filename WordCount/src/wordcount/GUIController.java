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
import javafx.scene.control.ListView;
import javafx.stage.FileChooser;
import javafx.util.Pair;

/**
 *
 * @author Benji
 */
public class GUIController implements Initializable {

	private List<Pair<String, Integer>> data;

	@FXML
	private ListView myList;

	@FXML
	private void handelParseButton(ActionEvent event) {
		try {
			List<String> text = Files.readAllLines(new FileChooser().showOpenDialog(myList.getScene().getWindow()).toPath());
			data = Word.toWordCount(text);
			setList();
		} catch (IOException ex) {
			//netbeans generated code...
			//may want to change this line up later
			Logger.getLogger(GUIController.class.getName()).log(Level.SEVERE, null, ex);
		}
	}

	private void setList() {
		ObservableList<String> fxList = FXCollections.observableArrayList();
		for (Pair<String, Integer> wordCountPair : data) {
			fxList.add(String.format("%d %s", wordCountPair.getValue(), wordCountPair.getKey()));
		}
		myList.setItems(fxList);

	}

	@FXML
	private void sortAlphebetically(ActionEvent event) {
		data.sort((Pair<String, Integer> a, Pair<String, Integer> b) -> {
			return a.getKey().compareTo(b.getKey());
		});
		setList();
	}

	@FXML
	private void sortByLength(ActionEvent event) {
		data.sort((Pair<String, Integer> a, Pair<String, Integer> b) -> {
			return b.getKey().length() - a.getKey().length();
		});
		setList();
	}

	@FXML
	private void sortByOccurences(ActionEvent event) {
		data.sort((Pair<String, Integer> a, Pair<String, Integer> b) -> {
			return b.getValue().compareTo(a.getValue());
		});
		setList();
	}

	@Override
	public void initialize(URL url, ResourceBundle rb) {
		// TODO
	}

}
