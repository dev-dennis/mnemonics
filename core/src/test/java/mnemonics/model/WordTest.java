package mnemonics.model;

import static mnemonics.constants.TestConstants.*;

import java.util.*;

import org.junit.jupiter.api.*;

class WordTest {

	private static final List<Character> FORBIDDEN_CHARACTERS = List.of(A, LOWERC);
	private static final Set<Character> EXPECTED_ALLOWED_CHARACTERS = Set.of(LOWERB);

	@Test
	void shouldRemoveForbiddenCharactersInAllCases() {

		Word word = new Word("ABC", FORBIDDEN_CHARACTERS);

		Assertions.assertEquals(EXPECTED_ALLOWED_CHARACTERS, word.getAllowedCharacters());
	}

}
