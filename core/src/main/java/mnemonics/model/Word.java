package mnemonics.model;

import java.util.*;
import java.util.logging.*;
import java.util.stream.Collectors;

import lombok.*;

@Getter
@Setter
@EqualsAndHashCode
public class Word {

	static Logger logger = Logger.getLogger("MyLog");

	private String text;

	private Set<Character> allowedCharacters;

	public Word(String text, List<Character> forbiddenCharacters) {

		setText(text);

		Set<Character> allChars = getAllChars(text);
		allChars.removeAll(forbiddenCharacters.stream().map(Character::toLowerCase).toList());
		setAllowedCharacters(allChars);

		logger.log(Level.FINE, "new word: text:{0},allowedCharacters:{1}", new Object[] { getText(), getAllowedCharacters() });
	}

	private Set<Character> getAllChars(String text) {

		return text.toLowerCase().chars().mapToObj(c -> (char) c).collect(Collectors.toSet());
	}

	@Override
	public String toString() {

		return getText() + "," + getAllowedCharacters();
	}

}
