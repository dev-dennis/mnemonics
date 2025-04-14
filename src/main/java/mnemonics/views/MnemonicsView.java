package mnemonics.views;

import java.util.*;

import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;

import mnemonics.model.Solution;
import mnemonics.service.MnemonicsService;

@Route("")
public class MnemonicsView extends VerticalLayout {

	private static final long serialVersionUID = -239201362029002492L;

	TextField words = new TextField("words");
	TextField forbiddenCharacters = new TextField("forbidden characters");

	TextField solutions = new TextField("solutions");

	Button submit = new Button("submit");

	private MnemonicsService service;

	public MnemonicsView(MnemonicsService service) {

		this.service = service;

		words.setValue("Apfel,Birne,Clementine");
		forbiddenCharacters.setValue("A,a,p,f,l,B,b,i,r,n,C,c,l,m,n,t,i,n");

		solutions.setWidthFull();
		solutions.setReadOnly(true);

		add(words);
		add(forbiddenCharacters);
		add(submit);
		add(solutions);

		submit.addClickShortcut(Key.ENTER);
		submit.addClickListener(e -> findsolutions());
	}

	private void findsolutions() {

		List<Solution> solveit = service.solveit(words.getValue(), forbiddenCharacters.getValue());

		HashSet<Solution> solutionSet = new HashSet<>(solveit);
		if (solutionSet.isEmpty()) {
			solutions.setValue("no solutions");
		} else {
			solutions.setValue(solutionSet.toString());
		}
	}
}
