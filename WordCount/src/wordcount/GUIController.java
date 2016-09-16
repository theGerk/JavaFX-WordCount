/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wordcount;

import java.io.File;
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
 * Controller class for the GUI
 *
 * @author Benji
 */
public class GUIController implements Initializable {

	//the current list of word and appearences of them being displayed.
	private List<Pair<String, Integer>> wordCountPairs;

	//reference to the ListView that appears in the GUI
	@FXML
	private ListView myList;

	/**
	 * Opens the file chooser, sets the data variable to the generated list of
	 * word, occurrence pairs. Then uses setList to make it appear on the GUI.
	 *
	 * @param event the event that is being handled. (unused)
	 */
	@FXML
	private void handelParseButton(ActionEvent event) {
		try {

			File fileChoosen = new FileChooser().showOpenDialog(myList.getScene().getWindow());

			//don't do anything unless they selected a file.
			if (fileChoosen != null) {
				List<String> lines = Files.readAllLines(fileChoosen.toPath());

				//set data, make it appear on screen
				wordCountPairs = Word.toWordCount(lines);
				setGUIList();

				lastSort = null;	//reset lastSort to null after we parse a new file.
			}
		} catch (IOException ex) {
			//netbeans generated code...
			//may want to change this line up later
			Logger.getLogger(GUIController.class.getName()).log(Level.SEVERE, null, ex);
		}
	}

	/**
	 * Puts the current values in data into the GUI element, in the order they
	 * appear in data.
	 */
	private void setGUIList() {
		ObservableList<String> fxList = FXCollections.observableArrayList();
		for (Pair<String, Integer> wordCountPair : wordCountPairs) {
			fxList.add(String.format("%d %s", wordCountPair.getValue(), wordCountPair.getKey()));
		}
		myList.setItems(fxList);

	}

	/**
	 * an enum for different ways the data can be sorted.
	 */
	private enum SortType {
		Alphabetical, //standard alphabetical ordering
		WordLength, //sort by lenght of the word
		Occurences	//sort by number of occurrences of the word
	};

	/**
	 * Keeps track of the last sort button that was clicked, this is only for
	 * the reverse sorting.
	 *
	 * This will be set to null if the same button is clicked twice.
	 *
	 * This will also be reset to null if a new file is parsed.
	 */
	private SortType lastSort = null;

	/**
	 * Sorts alphabetically, or reverse alphabetically if it was just used.
	 *
	 * @param event the event that is being handled. (unused)
	 */
	@FXML
	private void sortAlphabetically(ActionEvent event) {
		if (lastSort == SortType.Alphabetical) {
			wordCountPairs.sort((Pair<String, Integer> a, Pair<String, Integer> b) -> {
				return b.getKey().compareTo(a.getKey());
			});
			lastSort = null;
		} else {
			wordCountPairs.sort((Pair<String, Integer> a, Pair<String, Integer> b) -> {
				return a.getKey().compareTo(b.getKey());
			});
			lastSort = SortType.Alphabetical;
		}
		setGUIList();
	}

	/**
	 * Sorts by word length, or reverse sorts if it was just used.
	 *
	 * @param event the event that is being handled. (unused)
	 */
	@FXML
	private void sortByWordLength(ActionEvent event) {
		if (lastSort == SortType.WordLength) {
			wordCountPairs.sort((Pair<String, Integer> a, Pair<String, Integer> b) -> {
				return a.getKey().length() - b.getKey().length();
			});
			lastSort = null;
		} else {
			wordCountPairs.sort((Pair<String, Integer> a, Pair<String, Integer> b) -> {
				return b.getKey().length() - a.getKey().length();
			});
			lastSort = SortType.WordLength;
		}
		setGUIList();
	}

	/**
	 * Sorts by number of occurrences, or reverse sorts if it was just used.
	 *
	 * @param event
	 */
	@FXML
	private void sortByOccurences(ActionEvent event) {
		if (lastSort == SortType.Occurences) {
			wordCountPairs.sort((Pair<String, Integer> a, Pair<String, Integer> b) -> {
				return a.getValue().compareTo(b.getValue());
			});
			lastSort = null;
		} else {
			wordCountPairs.sort((Pair<String, Integer> a, Pair<String, Integer> b) -> {
				return b.getValue().compareTo(a.getValue());
			});
			lastSort = SortType.Occurences;

		}
		setGUIList();
	}

	/**
	 * This class does not use it's initialize function
	 *
	 * @param url
	 * @param rb
	 */
	@Override
	public void initialize(URL url, ResourceBundle rb) {
		// TODO
	}

}
