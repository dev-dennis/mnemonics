package mnemonics.model;

import java.util.*;
import java.util.stream.Collectors;

import lombok.*;

@Getter
@Setter
@EqualsAndHashCode
public class Solution {

	private List<Mnemonic> mnemonics = new ArrayList<>();

	public Solution(Mnemonic mnemonic) {

		getMnemonics().add(mnemonic);
	}

	public Solution(Solution solution1, Solution solution2) {

		getMnemonics().addAll(solution1.getMnemonics());
		getMnemonics().addAll(solution2.getMnemonics());
	}

	public void merge(Solution solution) {

		getMnemonics().addAll(solution.getMnemonics());
	}

	public static List<Solution> createAll(Word word) {

		List<Mnemonic> mnemonicList = Mnemonic.createAll(word);
		return mnemonicList.stream().map(Solution::new).toList();
	}

	public Set<Character> getLetterSet() {

		return getMnemonics().stream().map(Mnemonic::getLetter).collect(Collectors.toSet());
	}

	@Override
	public String toString() {

		return getMnemonics().toString();
	}
}
