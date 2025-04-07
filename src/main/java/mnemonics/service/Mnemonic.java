package mnemonics.service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import mnemonics.model.Word;

public class Mnemonic {

	private Word mWort;

	private Character mBuchstabe;

	public Character getBuchstabe() {

		return mBuchstabe;
	}

	private void setBuchstabe(Character mBuchstabe) {

		this.mBuchstabe = mBuchstabe;
	}

	public Word getWort() {

		return mWort;
	}

	private void setWort(Word pWort) {

		this.mWort = pWort;
	}

	public Mnemonic(Word pWort, Character pBuchstabe) {

		setWort(pWort);
		setBuchstabe(pBuchstabe);
	}

	@Override
	public String toString() {

		return getWort().getText() + "," + getBuchstabe();
	}

	public static List<Mnemonic> getAll(Word word) {

		Set<Character> chars = word.getAllowedCharacters();
		return chars.stream().map(c -> new Mnemonic(word, c)).toList();
	}
}
