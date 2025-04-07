package mnemonics.model;

import java.util.List;
import java.util.Set;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class WordTest {

	private static final Character A = Character.valueOf('a');
	private static final Character B = Character.valueOf('b');
	private static final Character C = Character.valueOf('c');

	private static final List<Character> forbiddenCharacters = List.of(A, C);
	private static final Set<Character> expectedAllowedCharacters = Set.of(B);

	@Test
	void getAllowedCharacters() {

		Word word = new Word("ABC", forbiddenCharacters);

		Assertions.assertEquals(expectedAllowedCharacters, word.getAllowedCharacters());
	}

	@Test
	void todo() {

		// TODO check for upper/lowercase
	}

}
