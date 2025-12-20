package mnemonics.view;

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

	private final transient MnemonicsService service;

	private final TextField words = new TextField();
	private final TextField forbiddenCharacters = new TextField();
	private final Select<Integer> wordsCount = new Select<>();
	private final Select<Integer> resultCount = new Select<>();
	private final Grid<Solution> solutions = new Grid<>();

	public MnemonicsView(MnemonicsService service) {

		this.service = service;

		setHeightFull();
		configureComponents();
		addComponents();
		initialize();
	}

	private void configureComponents() {

		configureWordsField();
		configureForbiddenCharactersField();
		configureWordsCountDropdown();
		configureResultCount();
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

	private void configureWordsCountDropdown() {

		wordsCount.setWidthFull();
		wordsCount.setLabel("number of words to use");
		wordsCount.setItemLabelGenerator(String::valueOf);
		wordsCount.addValueChangeListener(e -> findSolutions());
	}

	private void configureResultCount() {

		resultCount.setWidthFull();
		resultCount.setLabel("max number of results to return");
		List<Integer> options = List.of(10, 20, 50, 100);
		resultCount.setItems(options);
		resultCount.setValue(options.get(0));
		resultCount.setItemLabelGenerator(String::valueOf);
		resultCount.addValueChangeListener(e -> findSolutions());

	}

	private void configureGrid() {

		solutions.setWidthFull();
		solutions.setHeightFull();
		solutions.addColumn(Solution::getMnemonics).setHeader("Solutions");
		solutions.getColumns().forEach(col -> col.setAutoWidth(true));
	}

	private void addComponents() {

		add(words, forbiddenCharacters, wordsCount, resultCount, solutions);
	}

	private void initialize() {

		updateNumberOfWords();
		findSolutions();
	}

	private void findSolutions() {

		if (wordsCount.getValue() == null) {
			solutions.setItems(Collections.emptySet());
			return;
		}

		List<Solution> solutionList = service.findSolutions(words.getValue(), forbiddenCharacters.getValue(), wordsCount.getValue(), resultCount.getValue());
		solutions.setItems(solutionList);
	}

	private void updateNumberOfWords() {

		String wordsValue = words.getValue().trim();
		if (wordsValue.isEmpty()) {
			disableWordsCount();
			return;
		}
		enableWordsCount(wordsValue);
	}

	private void disableWordsCount() {

		wordsCount.setItems();
		wordsCount.setEnabled(false);
	}

	private void enableWordsCount(String wordsValue) {

		List<Integer> options = generateWordsCountOptions(wordsValue);
		wordsCount.setItems(options);
		wordsCount.setValue(options.get(0));
		wordsCount.setEnabled(true);
	}

	private List<Integer> generateWordsCountOptions(String wordsValue) {

		int count = countWords(wordsValue);
		return IntStream.rangeClosed(1, count).boxed().sorted(Comparator.reverseOrder()).toList();
	}

	private int countWords(String wordsValue) {

		return (int) Arrays.stream(wordsValue.split(",")).map(String::trim).filter(w -> !w.isEmpty()).count();
	}
}