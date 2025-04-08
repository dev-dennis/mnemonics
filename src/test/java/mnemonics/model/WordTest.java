package mnemonics.model;

import java.util.List;
import java.util.Set;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class WordTest {

	private static final Character A = Character.valueOf('A');
	private static final Character LOWERB = Character.valueOf('b');
	private static final Character LOWERC = Character.valueOf('c');

	private static final List<Character> forbiddenCharacters = List.of(A, LOWERC);
	private static final Set<Character> expectedAllowedCharacters = Set.of(LOWERB);

	@Test
	void shouldRemoveForbiddenCharactersInAllCases() {

		Word word = new Word("ABC", forbiddenCharacters);

		Assertions.assertEquals(expectedAllowedCharacters, word.getAllowedCharacters());
	}

}
