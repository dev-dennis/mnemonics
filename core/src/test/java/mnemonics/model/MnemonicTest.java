package mnemonics.model;

import static mnemonics.constants.TestConstants.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.*;

import org.junit.jupiter.api.Test;

class MnemonicTest {

	private static final Set<Mnemonic> EXPECTED_SET = Set.of(MNEMONIC_ABC_A, MNEMONIC_ABC_B, MNEMONIC_ABC_C);

	@Test
	void shouldContainAllExpectedMnemonics() {

		Set<Mnemonic> allSet = new HashSet<Mnemonic>(Mnemonic.createAll(WORD_ABC));

		assertEquals(EXPECTED_SET, allSet);
	}

}
