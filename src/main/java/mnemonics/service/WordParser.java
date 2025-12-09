package mnemonics.service;

import java.util.*;

import mnemonics.model.Word;

public class WordParser {

	private WordParser() {

	}

	static List<Word> createWords(String wordsString, String forbiddenString) {

		List<Character> forbiddenCharacters = toCharacterList(forbiddenString);
		return Arrays.stream(wordsString.split("\\,")).map(String::trim).filter(w -> !w.isEmpty()).map(w -> new Word(w, forbiddenCharacters)).toList();
	}

	private static List<Character> toCharacterList(String forbidden) {

		return forbidden.chars().mapToObj(c -> (char) c).toList();
	}

}
