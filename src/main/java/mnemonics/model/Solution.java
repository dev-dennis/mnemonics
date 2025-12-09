package mnemonics.model;

import java.util.*;

import lombok.*;

@Getter
@Setter
@EqualsAndHashCode
public class Solution {

	private List<Mnemonic> mnemonics = new ArrayList<>();

	private Set<Character> letterSet = new HashSet<>();

	public Solution(Mnemonic mnemonic) {

		mnemonics.add(mnemonic);
		letterSet.add(mnemonic.getLetter());
	}

	public Solution(Solution solution1, Solution solution2) {

		mnemonics.addAll(solution1.getMnemonics());
		letterSet.addAll(solution1.getLetterSet());

		mnemonics.addAll(solution2.getMnemonics());
		letterSet.addAll(solution2.getLetterSet());
	}

	public void merge(Solution solution) {

		mnemonics.addAll(solution.getMnemonics());
		letterSet.addAll(solution.getLetterSet());
	}

	public static List<Solution> createAll(Word word) {

		List<Mnemonic> mnemonicList = Mnemonic.createAll(word);
		return mnemonicList.stream().map(Solution::new).toList();
	}

	@Override
	public String toString() {

		return getMnemonics().toString();
	}
}
