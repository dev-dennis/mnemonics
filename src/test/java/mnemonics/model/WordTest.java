package mnemonics.model;

import static mnemonics.constants.TestConstants.*;

import java.util.*;

import org.junit.jupiter.api.*;

class WordTest {

	private static final List<Character> forbiddenCharacters = List.of(A, LOWERC);
	private static final Set<Character> expectedAllowedCharacters = Set.of(LOWERB);

	@Test
	void shouldRemoveForbiddenCharactersInAllCases() {

		Word word = new Word("ABC", forbiddenCharacters);

		Assertions.assertEquals(expectedAllowedCharacters, word.getAllowedCharacters());
	}

}
