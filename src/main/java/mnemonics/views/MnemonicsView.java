package mnemonics.views;

import java.util.*;
import java.util.stream.IntStream;

import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.select.Select;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.Route;

import mnemonics.model.Solution;
import mnemonics.service.MnemonicsService;

@Route("")
public class MnemonicsView extends VerticalLayout {

	private static final long serialVersionUID = -239201362029002492L;

	private final TextField words = new TextField();
	private final TextField forbiddenCharacters = new TextField();
	private final Select<Integer> numberOfWords = new Select<>();
	private final Grid<Solution> solutions = new Grid<>();

	public MnemonicsView() {

		setHeightFull();
		configureComponents();
		addComponents();
		initialize();
	}

	private void configureComponents() {

		configureWordsField();
		configureForbiddenCharactersField();
		configureWordCountDropdown();
		configureGrid();
	}

	private void configureWordsField() {

		words.setWidthFull();
		words.setLabel("words");
		words.setValue("Neu,Löschen,Prüfen");
		words.setValueChangeMode(ValueChangeMode.LAZY);
		words.addValueChangeListener(e -> {
			updateNumberOfWords();
			findSolutions();
		});
	}

	private void configureForbiddenCharactersField() {

		forbiddenCharacters.setWidthFull();
		forbiddenCharacters.setLabel("forbidden characters");
		forbiddenCharacters.setValue("euöscherüfe");
		forbiddenCharacters.setValueChangeMode(ValueChangeMode.LAZY);
		forbiddenCharacters.addValueChangeListener(e -> findSolutions());
	}

	private void configureWordCountDropdown() {

		numberOfWords.setWidthFull();
		numberOfWords.setLabel("number of words to use");
		numberOfWords.setItemLabelGenerator(String::valueOf);
		numberOfWords.addValueChangeListener(e -> findSolutions());
	}

	private void configureGrid() {

		solutions.setWidthFull();
		solutions.setHeightFull();
		solutions.addColumn(Solution::getMnemonics).setHeader("Solutions");
		solutions.getColumns().forEach(col -> col.setAutoWidth(true));
	}

	private void addComponents() {

		add(words, forbiddenCharacters, numberOfWords, solutions);
	}

	private void initialize() {

		updateNumberOfWords();
		findSolutions();
	}

	private void findSolutions() {

		if (numberOfWords.getValue() == null) {
			solutions.setItems(Collections.emptySet());
			return;
		}

		List<Solution> solutionList = MnemonicsService.findSolutions(words.getValue(), forbiddenCharacters.getValue(), numberOfWords.getValue());
		solutions.setItems(solutionList);
	}

	private void updateNumberOfWords() {

		String wordsValue = words.getValue().trim();
		if (wordsValue.isEmpty()) {
			disableNumberOfWords();
			return;
		}
		enableNumberOfWords(wordsValue);
	}

	private void disableNumberOfWords() {

		numberOfWords.setItems();
		numberOfWords.setEnabled(false);
	}

	private void enableNumberOfWords(String wordsValue) {

		List<Integer> options = generateNumberOfWordsOptions(wordsValue);
		numberOfWords.setItems(options);
		numberOfWords.setValue(options.get(0));
		numberOfWords.setEnabled(true);
	}

	private List<Integer> generateNumberOfWordsOptions(String wordsValue) {

		int wordsCount = countWords(wordsValue);
		return IntStream.rangeClosed(1, wordsCount).boxed().sorted(Comparator.reverseOrder()).toList();
	}

	private int countWords(String wordsValue) {

		return (int) Arrays.stream(wordsValue.split(",")).map(String::trim).filter(w -> !w.isEmpty()).count();
	}
}