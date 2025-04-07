package mnemonics.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import mnemonics.model.Word;

class MnemonicsServiceTest {

	private static final Character A = Character.valueOf('A');
	private static final Character B = Character.valueOf('B');
	private static final Character C = Character.valueOf('C');
	private static final Character D = Character.valueOf('D');

	private static final Word wordA = new Word("A", new ArrayList<Character>(A));
	private static final Word wordB = new Word("B", new ArrayList<Character>(B));
	private static final Word wordC = new Word("C", new ArrayList<Character>(C));
	private static final Word wordD = new Word("D", new ArrayList<Character>(D));

	private static final List<Word> words = List.of(wordA, wordB, wordC, wordD);

	private static final List<Word> wordsABC = List.of(wordA, wordB, wordC);
	private static final List<Word> wordsABD = List.of(wordA, wordB, wordD);
	private static final List<Word> wordsBCD = List.of(wordB, wordC, wordD);
	private static final List<Word> wordsACD = List.of(wordA, wordC, wordD);

	List<List<Word>> expectedWordsList = List.of(wordsABC, wordsABD, wordsBCD, wordsACD);

	@Test
	final void getReducedListPermutations() {

		ArrayList<List<Word>> wordsList = MnemonicsService.getReducedListPermutations(words);
		Set<List<Word>> wordsListSet = new HashSet<>(wordsList);

		HashSet<List<Word>> expectedWordsListSet = new HashSet<>(expectedWordsList);

		Assertions.assertEquals(expectedWordsListSet, wordsListSet);
	}

}
