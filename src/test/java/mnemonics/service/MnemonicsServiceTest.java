package mnemonics.service;

import static mnemonics.constants.TestConstants.*;
import static org.junit.jupiter.api.Assertions.*;

import java.util.*;

import org.junit.jupiter.api.Test;

import mnemonics.model.*;

class MnemonicsServiceTest {

	private static final List<Word> words = List.of(WORD_A, WORD_B, WORD_C, WORD_D);
	private static final List<List<Word>> expectedWordsList = List.of(WORDS_ABC, WORDS_ABD, WORDS_BCD, WORDS_ACD);

	@Test
	final void shouldGenerateCorrectReducedWordLists() {

		Set<List<Word>> actual = new HashSet<>(WordGenerator.generateReducedWordLists(words, 3));

		Set<List<Word>> expected = new HashSet<>(expectedWordsList);

		assertEquals(expected, actual);
	}

	@Test
	void shouldNotCreateDuplicateSolutionsWhenBothWordsOnlyAllowSameLetter() {

		List<Solution> solutions = new MnemonicsService().findSolutions("cat,cat", "ca", 2);

		assertTrue(solutions.isEmpty());
	}

	@Test
	void shouldAllowSameWordWithDifferentLetters() {

		List<Solution> solutions = new MnemonicsService().findSolutions("cat,cat", "", 2);

		assertFalse(solutions.isEmpty());

		assertEquals(6, solutions.size());

		for (Solution solution : solutions) {
			List<Mnemonic> mnemonics = solution.getMnemonics();
			Set<Mnemonic> uniqueMnemonics = new HashSet<>(mnemonics);
			assertEquals(mnemonics.size(), uniqueMnemonics.size());
		}
	}

}
