package mnemonics.views;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;

@Route("")
public class MnemonicsView extends VerticalLayout {

	private static final long serialVersionUID = -239201362029002492L;

	public MnemonicsView() {

		add(new Button("Click me", e -> Notification.show("Hello, Spring+Vaadin user!")));
	}
}