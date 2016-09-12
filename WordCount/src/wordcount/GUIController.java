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

	private enum SortType {
		Alphebetical,
		WordLength,
		Occurences
	};

	private SortType lastSort = null;

	@FXML
	private void sortAlphebetically(ActionEvent event) {
		if (lastSort == SortType.Alphebetical) {
			data.sort((Pair<String, Integer> a, Pair<String, Integer> b) -> {
				return b.getKey().compareTo(a.getKey());
			});
			lastSort = null;
		} else {
			data.sort((Pair<String, Integer> a, Pair<String, Integer> b) -> {
				return a.getKey().compareTo(b.getKey());
			});
			lastSort = SortType.Alphebetical;
		}
		setList();
	}

	@FXML
	private void sortByWordLength(ActionEvent event) {
		if (lastSort == SortType.WordLength) {
			data.sort((Pair<String, Integer> a, Pair<String, Integer> b) -> {
				return a.getKey().length() - b.getKey().length();
			});
			lastSort = null;
		} else {
			data.sort((Pair<String, Integer> a, Pair<String, Integer> b) -> {
				return b.getKey().length() - a.getKey().length();
			});
			lastSort = SortType.WordLength;
		}
		setList();
	}

	@FXML

	private void sortByOccurences(ActionEvent event) {
		if (lastSort == SortType.Occurences) {
			data.sort((Pair<String, Integer> a, Pair<String, Integer> b) -> {
				return a.getValue().compareTo(b.getValue());
			});
			lastSort = null;
		} else {
			data.sort((Pair<String, Integer> a, Pair<String, Integer> b) -> {
				return b.getValue().compareTo(a.getValue());
			});
			lastSort = SortType.Occurences;

		}
		setList();
	}

	@Override
	public void initialize(URL url, ResourceBundle rb) {
		// TODO
	}

}
