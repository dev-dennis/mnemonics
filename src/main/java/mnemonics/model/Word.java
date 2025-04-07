package mnemonics.model;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Word {

	static Logger logger = Logger.getLogger("MyLog");

	private String text;

	private Set<Character> allowedCharacters;

	public Word(String text, List<Character> forbiddenCharacters) {

		setText(text);

		Set<Character> allChars = getAllChars(text);
		allChars.removeAll(forbiddenCharacters);
		setAllowedCharacters(allChars);

		logger.log(Level.INFO, "new word: text:{0},allowedCharacters:{1}", new Object[] { getText(), getAllowedCharacters() });
	}

	private Set<Character> getAllChars(String text) {

		Set<Character> chars = new HashSet<>();
		String lowerCase = text.toLowerCase();
		for (int i = 0; i < lowerCase.length(); i++) {
			chars.add(lowerCase.charAt(i));
		}
		return chars;
	}

	@Override
	public String toString() {

		return getText() + "," + getAllowedCharacters();
	}

}
