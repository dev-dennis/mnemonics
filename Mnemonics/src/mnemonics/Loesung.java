package mnemonics;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Loesung {

    private List<Mnemonic> mMnemonics = new ArrayList<>();

    private List<Mnemonic> getMnemonics() {
        return mMnemonics;
    }

    private void setMnemonics(List<Mnemonic> mMnemonics) {
        this.mMnemonics = mMnemonics;
    }

    @Override
    public String toString() {

        StringBuilder lSB = new StringBuilder();
        for (Mnemonic lMnemonic : getMnemonics()) {
            lSB.append(lMnemonic);
            lSB.append(";");
        }
        return lSB.toString();
    }

    public Loesung(Mnemonic pMnemonic) {
        getMnemonics().add(pMnemonic);
    }

    public Loesung(Loesung loesung1, Loesung loesung2) {

        List<Mnemonic> mnemonics = new ArrayList<>();
        List<Mnemonic> mnemonics1 = loesung1.getMnemonics();
        List<Mnemonic> mnemonics2 = loesung2.getMnemonics();
        mnemonics.addAll(mnemonics1);
        mnemonics.addAll(mnemonics2);
        setMnemonics(mnemonics);
    }

    boolean isValid() {

        Set<Wort> lWoerter = new HashSet<>();
        Set<Character> lBuchstaben = new HashSet<>();
        for (Mnemonic lMnemonic : getMnemonics()) {
            lWoerter.add(lMnemonic.getWort());
            lBuchstaben.add(lMnemonic.getBuchstabe());
        }
        return lWoerter.size() == lBuchstaben.size();
    }
}
