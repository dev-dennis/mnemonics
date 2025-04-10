package mnemonics.service;

import java.util.*;

import mnemonics.model.*;

public class MnemonicsService {

	private MnemonicsService() {

	}

	public static List<Solution> solve(String[] args, int reduceListBy) {

		List<Character> forbiddenCharacters = getForbiddenCharacters(args);
		List<Word> words = getWords(args, forbiddenCharacters);

		return solve(words, reduceListBy);
	}

	private static List<Solution> solve(List<Word> words, int reduceListBy) {

		return findReducedWordsSolutions(words, reduceListBy);
	}

	public static List<Solution> solve(String[] args) {

		List<Character> forbiddenCharacters = getForbiddenCharacters(args);
		List<Word> words = getWords(args, forbiddenCharacters);

		return solve(words);
	}

	private static List<Solution> solve(List<Word> words) {

		int reduceListBy = 0;
		List<Solution> solutions = new ArrayList<>();
		while (solutions.isEmpty() && reduceListBy < words.size()) {
			solutions.addAll(findReducedWordsSolutions(words, reduceListBy));
			reduceListBy++;
		}
		return solutions;
	}

	private static List<Word> getWords(String[] args, List<Character> forbiddenCharacters) {

		String[] wordStrings = args[1].split("\\,");
		List<Word> words = new ArrayList<>();
		for (String word : wordStrings) {
			words.add(new Word(word, forbiddenCharacters));
		}
		return words;
	}

	private static List<Character> getForbiddenCharacters(String[] args) {

		String[] forbiddenStrings = args[0].split("\\,");
		List<Character> forbiddenCharacters = new ArrayList<>();
		for (String forbiddenString : forbiddenStrings) {
			if (forbiddenString.length() == 1) {
				forbiddenCharacters.add(forbiddenString.charAt(0));
			}
		}
		return forbiddenCharacters;
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
				for (Solution solution2 : solutions2) {
					Solution solution = new Solution(solution1, solution2);
					if (solution.isValid()) {
						solutions.add(solution);
					}
				}
			}
			return solutions;
		}
	}

}
