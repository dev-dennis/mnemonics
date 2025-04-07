package mnemonics.service;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Word {

	static Logger logger = Logger.getLogger("MyLog");

	private String mWort;
	private ArrayList<Character> mVergebeneBuchstaben;

	public Word(String pWort, List<Character> pVergebeneBuchstaben) {

		setWort(pWort);
		setVergebeneBuchstaben(pVergebeneBuchstaben);
		logger.log(Level.INFO, "Wort: {0}, Buchstaben: {1}", new String[] { getWort(), getErlaubteBuchstaben() });
	}

	public String getWort() {

		return mWort;
	}

	private void setWort(String pWort) {

		mWort = pWort;
	}

	private String getBuchstaben() {

		return entferneDoppelte(getWort().toLowerCase());
	}

	private static String entferneDoppelte(String pWort) {

		char[] lChars = pWort.toCharArray();
		Set<Character> charSet = new LinkedHashSet<>();
		for (char c : lChars) {
			charSet.add(c);
		}

		StringBuilder sb = new StringBuilder();
		for (Character character : charSet) {
			sb.append(character);
		}
		return sb.toString();
	}

	public String getErlaubteBuchstaben() {

		char[] lChars = getBuchstaben().toCharArray();
		Set<Character> charSet = new LinkedHashSet<>();
		for (char c : lChars) {
			if (!getVergebeneBuchstaben().contains(c)) {
				charSet.add(c);
			}
		}

		StringBuilder sb = new StringBuilder();
		for (Character character : charSet) {
			sb.append(character);
		}
		return sb.toString();
	}

	private List<Character> getVergebeneBuchstaben() {

		return mVergebeneBuchstaben;
	}

	private void setVergebeneBuchstaben(List<Character> pVergebeneBuchstaben) {

		mVergebeneBuchstaben = (ArrayList<Character>) pVergebeneBuchstaben;
	}

	@Override
	public String toString() {

		return getWort() + "," + getErlaubteBuchstaben();
	}
}
