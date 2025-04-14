package mnemonics.views;

import java.util.*;

import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.Route;

import mnemonics.model.Solution;
import mnemonics.service.MnemonicsService;

@Route("")
public class MnemonicsView extends VerticalLayout {

	private static final long serialVersionUID = -239201362029002492L;

	TextField words = new TextField("words");
	TextField forbiddenCharacters = new TextField("forbidden characters");
	Grid<Solution> grid = new Grid<>();

	public MnemonicsView() {

		this.setHeightFull();

		words.setValue("Apfel,Birne,Clementine");
		words.setValueChangeMode(ValueChangeMode.LAZY);
		words.addValueChangeListener(e -> findsolutions());
		words.setWidthFull();

		forbiddenCharacters.setValue("ApflBirnClmntin");
		forbiddenCharacters.setValueChangeMode(ValueChangeMode.LAZY);
		forbiddenCharacters.addValueChangeListener(e -> findsolutions());
		forbiddenCharacters.setWidthFull();

		grid.setWidthFull();
		grid.setHeightFull();
		grid.addColumn(s -> s.getMnemonics()).setHeader("solutions");
		grid.getColumns().forEach(col -> col.setAutoWidth(true));

		add(words);
		add(forbiddenCharacters);
		add(grid);

		findsolutions();
	}

	private void findsolutions() {

		List<Solution> solveit = MnemonicsService.solveit(words.getValue(), forbiddenCharacters.getValue());
		HashSet<Solution> solutionSet = new HashSet<>(solveit);
		grid.setItems(solutionSet);

	}
}
