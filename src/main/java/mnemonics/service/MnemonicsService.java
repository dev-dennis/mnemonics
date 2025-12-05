package mnemonics.service;

import java.util.*;

import org.springframework.stereotype.Service;

import mnemonics.model.*;

@Service
public class MnemonicsService {

	private MnemonicsService() {

	}

	public static List<Solution> findSolutions(String wordString, String forbiddenString) {

		List<Word> words = createWords(wordString, toCharacterList(forbiddenString));
		return findSolutions(words);
	}

	public static List<Solution> findSolutions(String wordString, String forbiddenString, int reduceListBy) {

		List<Word> words = createWords(wordString, toCharacterList(forbiddenString));
		return findReducedWordsSolutions(words, reduceListBy);
	}

	private static List<Character> toCharacterList(String forbidden) {

		List<Character> forbiddenCharacters = new ArrayList<>();
		for (int i = 0; i < forbidden.length(); i++) {
			forbiddenCharacters.add(forbidden.charAt(i));
		}
		return forbiddenCharacters;
	}

	private static List<Word> createWords(String wordsString, List<Character> forbiddenCharacters) {

		String[] wordStrings = wordsString.split("\\,");
		List<Word> words = new ArrayList<>();
		for (String word : wordStrings) {
			words.add(new Word(word, forbiddenCharacters));
		}
		return words;
	}

	private static List<Solution> findSolutions(List<Word> words) {

		int reduceListBy = 0;
		List<Solution> solutions = new ArrayList<>();
		while (solutions.isEmpty() && reduceListBy < words.size()) {
			solutions.addAll(findReducedWordsSolutions(words, reduceListBy));
			reduceListBy++;
		}
		return solutions;
	}

	private static List<Solution> findReducedWordsSolutions(List<Word> words, int reduceListBy) {

		if (reduceListBy == 0) {
			return createAllValidSolutions(words);
		} else {
			List<List<Word>> reducedLists = createReducedListPermutations(words);
			List<Solution> solutions = new ArrayList<>();
			for (List<Word> reducedList : reducedLists) {
				solutions.addAll(findReducedWordsSolutions(reducedList, reduceListBy - 1));
			}
			return solutions;
		}

	}

	static ArrayList<List<Word>> createReducedListPermutations(List<Word> words) {

		ArrayList<List<Word>> reducedLists = new ArrayList<>();
		for (Word word : words) {
			List<Word> copy = new ArrayList<>(words);
			copy.remove(word);
			reducedLists.add(copy);
		}
		return reducedLists;
	}

	private static List<Solution> createAllValidSolutions(List<Word> words) {

		if (words.isEmpty()) {
			return List.of();
		} else if (words.size() == 1) {
			return Solution.createAll(words.get(0));
		} else {
			List<Solution> solutions = new ArrayList<>();
			List<Solution> solutions1 = createAllValidSolutions(words.subList(0, words.size() / 2));
			List<Solution> solutions2 = createAllValidSolutions(words.subList(words.size() / 2, words.size()));
			for (Solution solution1 : solutions1) {
				Set<Character> letterSet1 = solution1.getLetterSet();
				for (Solution solution2 : solutions2) {
					if (Collections.disjoint(letterSet1, solution2.getLetterSet())) {
						solutions.add(new Solution(solution1, solution2));
					}
				}
			}
			return solutions;
		}
	}

}
