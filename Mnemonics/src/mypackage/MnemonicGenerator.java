package mypackage;

import java.util.*;

public class MnemonicGenerator {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Geben Sie eine Liste von Wörtern ein (getrennt durch Leerzeichen): ");
        String[] words = scanner.nextLine().toLowerCase().split(" ");

        System.out.print("Geben Sie eine Liste von verbotenen Buchstaben ein: ");
        Set<Character> bannedChars = new HashSet<>();
        String banned = scanner.nextLine().toLowerCase();
        for (char c : banned.toCharArray()) {
            bannedChars.add(c);
        }

        List<Map<Character, String>> mnemonicsList = generateAllMnemonics(words, bannedChars);

        for (Map<Character, String> mnemonics : mnemonicsList) {
            StringBuilder sb = new StringBuilder();
            for (String word : words) {
                for (char c : word.toCharArray()) {
                    if (!bannedChars.contains(c)) {
                        sb.append(mnemonics.get(c));
                    }
                }
                sb.append(",").append(word).append(" ");
            }
            System.out.println(sb.toString().trim());
        }
    }

    public static List<Map<Character, String>> generateAllMnemonics(String[] words, Set<Character> bannedChars) {
        Set<Character> allChars = new HashSet<>();
        for (String word : words) {
            for (char c : word.toCharArray()) {
                allChars.add(c);
            }
        }
        allChars.removeAll(bannedChars);

        List<Map<Character, String>> result = new ArrayList<>();
        generateMnemonics(new HashMap<>(), allChars, result);
        return result;
    }

    private static void generateMnemonics(Map<Character, String> mnemonic, Set<Character> availableChars, List<Map<Character, String>> result) {
        if (availableChars.isEmpty()) {
            result.add(new HashMap<>(mnemonic));
            return;
        }
        for (char c : availableChars) {
            String existingMnemonic = mnemonic.get(c);
            if (existingMnemonic == null) {
                for (char key : mnemonic.keySet()) {
                    if (mnemonic.get(key).equals(String.valueOf(c))) {
                        existingMnemonic = mnemonic.remove(key);
                        break;
                    }
                }
            }
            for (char key : mnemonic.keySet()) {
                if (mnemonic.get(key).equals(String.valueOf(c))) {
                    existingMnemonic = null;
                    break;
                }
            }
            if (existingMnemonic == null) {
                for (int i = 0; i < 10; i++) {
                    String newMnemonic = String.valueOf(i);
                    boolean valid = true;
                    for (char key : mnemonic.keySet()) {
                        if (mnemonic.get(key).equals(newMnemonic)) {
                            valid = false;
                            break;
                        }
                    }
                    if (valid) {
                        mnemonic.put(c, newMnemonic);
                        generateMnemonics(mnemonic, availableChars, result);
                        mnemonic.remove(c);
                    }
                }
            } else {
                mnemonic.put(c, existingMnemonic);
                generateMnemonics(mnemonic, availableChars, result);
                mnemonic.remove(c);
            }
        }
    }
}
