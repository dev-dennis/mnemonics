package mnemonics.model;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.jupiter.api.Test;

class MnemonicTest {

	private static final Character A = Character.valueOf('A');
	private static final Character B = Character.valueOf('B');
	private static final Character C = Character.valueOf('C');

	private static final Word WORD_ABC = new Word("ABC", List.of());

	private static final Mnemonic MNEMONIC_A = new Mnemonic(WORD_ABC, A);
	private static final Mnemonic MNEMONIC_B = new Mnemonic(WORD_ABC, B);
	private static final Mnemonic MNEMONIC_C = new Mnemonic(WORD_ABC, C);

	private static final Set<Mnemonic> EXPECTED_SET = Set.of(MNEMONIC_A, MNEMONIC_B, MNEMONIC_C);

	@Test
	void shouldContainAllExpectedMnemonics() {

		List<Mnemonic> all = Mnemonic.getAll(WORD_ABC);
		Set<Mnemonic> allSet = new HashSet<Mnemonic>(all);

		assertTrue(EXPECTED_SET.containsAll(allSet));
	}

}
