package mnemonics.service;

import java.util.*;

import mnemonics.model.Word;

public class WordGenerator {

	private WordGenerator() {

	}

	static List<List<Word>> generateReducedWordLists(List<Word> words, int numberOfWords) {

		if (words.isEmpty() || words.size() < numberOfWords || numberOfWords == 0) {
			return new ArrayList<>();
		}

		if (numberOfWords == 1) {
			return oneWordLists(words);
		}

		List<List<Word>> result = new ArrayList<>();
		result.addAll(listsWithFirstWord(words, numberOfWords));
		result.addAll(listsWithoutFirstWord(words, numberOfWords));
		return result;
	}

	private static List<List<Word>> oneWordLists(List<Word> words) {

		return words.stream().map(Collections::singletonList).toList();
	}

	private static List<List<Word>> listsWithFirstWord(List<Word> words, int numberOfWords) {

		return concatWordWithEveryList(words.get(0), generateReducedWordLists(words.subList(1, words.size()), numberOfWords - 1));
	}

	private static List<List<Word>> concatWordWithEveryList(Word word, List<List<Word>> wordsLists) {

		List<List<Word>> result = new ArrayList<>();
		for (List<Word> words : wordsLists) {
			List<Word> newList = new ArrayList<>();
			newList.add(word);
			newList.addAll(words);
			result.add(newList);
		}
		return result;
	}

	private static List<List<Word>> listsWithoutFirstWord(List<Word> words, int numberOfWords) {

		return generateReducedWordLists(words.subList(1, words.size()), numberOfWords);
	}

}
