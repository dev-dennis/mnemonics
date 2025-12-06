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

	private final TextField words = new TextField("Words");
	private final TextField forbiddenCharacters = new TextField("Forbidden Characters");
	private final Select<Integer> numberOfWords = new Select<>();
	private final Grid<Solution> grid = new Grid<>();

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

		words.setValue("Neu,Löschen,Prüfen");
		words.setValueChangeMode(ValueChangeMode.LAZY);
		words.addValueChangeListener(e -> {
			updateWordCountDropdown();
			findSolutions();
		});
		words.setWidthFull();
		words.setHelperText("Separate words with commas");
	}

	private void configureForbiddenCharactersField() {

		forbiddenCharacters.setValue("euöscherüfe");
		forbiddenCharacters.setValueChangeMode(ValueChangeMode.LAZY);
		forbiddenCharacters.addValueChangeListener(e -> findSolutions());
		forbiddenCharacters.setWidthFull();
		forbiddenCharacters.setHelperText("Characters to exclude from mnemonics");
	}

	private void configureWordCountDropdown() {

		numberOfWords.setWidthFull();
		numberOfWords.setItemLabelGenerator(String::valueOf);
		numberOfWords.addValueChangeListener(e -> findSolutions());
		numberOfWords.setLabel("Number of Words to Use");
		numberOfWords.setHelperText("Reduce if no solutions are found");
	}

	private void configureGrid() {

		grid.setWidthFull();
		grid.setHeightFull();
		grid.addColumn(Solution::getMnemonics).setHeader("Solutions");
		grid.getColumns().forEach(col -> col.setAutoWidth(true));
	}

	private void addComponents() {

		add(words, forbiddenCharacters, numberOfWords, grid);
	}

	private void initialize() {

		updateWordCountDropdown();
		findSolutions();
	}

	private void findSolutions() {

		Integer selectedWordCount = numberOfWords.getValue();

		if (selectedWordCount == null) {
			grid.setItems(Collections.emptySet());
			return;
		}

		List<Solution> solutions = MnemonicsService.findSolutions(words.getValue(), forbiddenCharacters.getValue(), selectedWordCount);

		grid.setItems(solutions);
	}

	private void updateWordCountDropdown() {

		String wordsValue = words.getValue().trim();
		if (wordsValue.isEmpty()) {
			disableWordCountDropdown();
			return;
		}
		enableWordCountDropdown(wordsValue);
	}

	private void disableWordCountDropdown() {

		numberOfWords.setItems();
		numberOfWords.setEnabled(false);
	}

	private void enableWordCountDropdown(String wordsValue) {

		List<Integer> options = generateWordCountOptions(wordsValue);
		numberOfWords.setItems(options);
		numberOfWords.setEnabled(true);
		numberOfWords.setValue(options.get(0));
	}

	private List<Integer> generateWordCountOptions(String wordsValue) {

		int wordsCount = countWords(wordsValue);
		return IntStream.rangeClosed(1, wordsCount).boxed().sorted(Comparator.reverseOrder()).toList();
	}

	private int countWords(String wordsValue) {

		return (int) Arrays.stream(wordsValue.split(",")).map(String::trim).filter(w -> !w.isEmpty()).count();
	}
}