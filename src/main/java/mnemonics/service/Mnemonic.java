package mnemonics.service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class Mnemonic {

	private Wort mWort;

	private Character mBuchstabe;

	public Character getBuchstabe() {

		return mBuchstabe;
	}

	private void setBuchstabe(Character mBuchstabe) {

		this.mBuchstabe = mBuchstabe;
	}

	public Wort getWort() {

		return mWort;
	}

	private void setWort(Wort pWort) {

		this.mWort = pWort;
	}

	public Mnemonic(Wort pWort, Character pBuchstabe) {

		setWort(pWort);
		setBuchstabe(pBuchstabe);
	}

	@Override
	public String toString() {

		return getWort().getWort() + "," + getBuchstabe();
	}

	public static List<Mnemonic> getAll(Wort pWort) {

		Set<Character> chars = pWort.getErlaubteBUchstaben().chars().mapToObj(c -> (char) c).collect(Collectors.toSet());
		return chars.stream().map(c -> new Mnemonic(pWort, c)).toList();
	}
}
