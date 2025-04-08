package mnemonics.model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import lombok.Getter;
import lombok.Setter;

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

		Set<Word> words = new HashSet<>();
		Set<Character> letters = new HashSet<>();
		for (Mnemonic mnemonic : getMnemonics()) {
			words.add(mnemonic.getWord());
			letters.add(mnemonic.getLetter());
		}
		return words.size() == letters.size();
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
