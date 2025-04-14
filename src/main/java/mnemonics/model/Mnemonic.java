package mnemonics.model;

import java.util.*;

import lombok.*;

@Getter
@Setter
@EqualsAndHashCode
public class Mnemonic {

	private Word word;

	private Character letter;

	public Mnemonic(Word word, Character letter) {

		setWord(word);
		setLetter(Character.toLowerCase(letter));
	}

	public static List<Mnemonic> createAll(Word word) {

		Set<Character> chars = word.getAllowedCharacters();
		return chars.stream().map(c -> new Mnemonic(word, c)).toList();
	}

	@Override
	public String toString() {

		return getWord().getText() + ":" + getLetter();
	}
}
