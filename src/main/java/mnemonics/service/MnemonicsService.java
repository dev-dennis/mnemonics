package mnemonics.service;

import java.util.*;

import org.springframework.stereotype.Service;

import mnemonics.model.*;

@Service
public class MnemonicsService {

	private MnemonicsService() {

	}

	public static List<Solution> findSolutions(String wordString, String forbiddenString, int wordsToUse) {

		List<Word> words = createWords(wordString, toCharacterList(forbiddenString));
		List<List<Word>> reducedWordLists = generateReducedWordLists(words, wordsToUse);
		List<Solution> result = new ArrayList<>();
		for (List<Word> list : reducedWordLists) {
			List<Solution> allValidSolutions = createAllValidSolutions(list);
			result.addAll(allValidSolutions);
		}
		return result;
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

	static List<List<Word>> generateReducedWordLists(List<Word> words, int numberOfWords) {

		List<List<Word>> result = new ArrayList<>();

		if (words.size() < numberOfWords) {
			return result;
		}

		if (numberOfWords == 0) {
			result.add(new ArrayList<>());
			return result;
		}

		List<List<Word>> reducedWordLists = generateReducedWordLists(words.subList(1, words.size()), numberOfWords - 1);
		for (List<Word> list : reducedWordLists) {
			List<Word> newList = new ArrayList<>();
			newList.add(words.get(0));
			newList.addAll(list);
			result.add(newList);
		}
		if (words.size() > numberOfWords) {
			result.addAll(generateReducedWordLists(words.subList(1, words.size()), numberOfWords));
		}
		return result;
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
