package mnemonics.service;

import static mnemonics.constants.TestConstants.*;

import java.util.*;

import org.junit.jupiter.api.*;

import mnemonics.model.Word;

class MnemonicsServiceTest {

	private static final List<Word> words = List.of(WORD_A, WORD_B, WORD_C, WORD_D);
	private static final List<List<Word>> expectedWordsList = List.of(WORDS_ABC, WORDS_ABD, WORDS_BCD, WORDS_ACD);

	@Test
	final void getReducedListPermutations() {

		ArrayList<List<Word>> wordsList = MnemonicsService.createReducedListPermutations(words);
		Set<List<Word>> wordsListSet = new HashSet<>(wordsList);

		HashSet<List<Word>> expectedWordsListSet = new HashSet<>(expectedWordsList);

		Assertions.assertEquals(expectedWordsListSet, wordsListSet);
	}

}
