package mnemonics.model;

import java.util.*;
import java.util.stream.Collectors;

import lombok.*;

@Getter
@Setter
public class Solution {

	private List<Mnemonic> mnemonics = new ArrayList<>();

	public Solution(Mnemonic mnemonic) {

		getMnemonics().add(mnemonic);
	}

	public Solution(Solution solution1, Solution solution2) {

		getMnemonics().addAll(solution1.getMnemonics());
		getMnemonics().addAll(solution2.getMnemonics());
	}

	public boolean isValid() {

		return !hasDuplicates() && hasEveryWordALetter();
	}

	private boolean hasDuplicates() {

		return getWordList().size() != getWordSet().size();
	}

	private boolean hasEveryWordALetter() {

		return getWordSet().size() == getLetterSet().size();
	}

	private Set<Character> getLetterSet() {

		return getMnemonics().stream().map(Mnemonic::getLetter).collect(Collectors.toSet());
	}

	private List<Word> getWordList() {

		return getMnemonics().stream().map(Mnemonic::getWord).toList();
	}

	private Set<Word> getWordSet() {

		return getMnemonics().stream().map(Mnemonic::getWord).collect(Collectors.toSet());
	}

	@Override
	public String toString() {

		StringBuilder sb = new StringBuilder();
		for (Mnemonic mnemonic : getMnemonics()) {
			sb.append(mnemonic);
			sb.append(";");
		}
		return sb.toString();
	}
}
