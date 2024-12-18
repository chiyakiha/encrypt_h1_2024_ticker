package src;

import java.util.Base64;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * <h4>Combined Decoder Implementation</h4>
 * Reverses the three-stage encoding process:<br>
 * 1. Kanji to alphanumeric conversion<br>
 * 2. Base64 decoding<br>
 * 3. Character conversion reversal<br>
 * WARNING: Requires properly formatted input to decode successfully.
 */
public class combi_dc extends jax3 {
    private final Map<String, Character> kanjiToAlpha = new HashMap<>();

    public combi_dc() {
        initDcMp();
    }

    public static void main(String[] args) {
        combi_dc decoder = new combi_dc();
        Scanner scanner = new Scanner(System.in);

        System.out.println("エンコードされたテキストを入力してください:");
        String input = scanner.nextLine();

        try {
            // Step 1: Kanji to Alphanumeric
            String kanjiDecoded = decoder.dcKanji(input);
            System.out.println("1 - 漢字デコード: " + kanjiDecoded);

            // Step 2: Base64 Decode
            String base64Decoded = decoder.decodeFromBase64(kanjiDecoded);
            System.out.println("2 - Base64デコード: " + base64Decoded);

            // Step 3: Character Conversion Decode
            String finalDecoded = decoder.dc(base64Decoded);
            System.out.println("3 - 最終結果: " + finalDecoded);
        } catch (Exception e) {
            System.err.println("デコード中にエラーが発生しました: " + e.getMessage());
        }

        scanner.close();
    }

    /// Initialises the kanji-to-alphanumeric mapping using predefined arrays.
    /// Required to initialise any decode operation.
    private void initDcMp() {
        AryLtrMp(up_a, 'A');
        AryLtrMp(up_b, 'B');
        AryLtrMp(up_c, 'C');
        AryLtrMp(up_d, 'D');
        AryLtrMp(up_e, 'E');
        AryLtrMp(up_f, 'F');
        AryLtrMp(up_g, 'G');
        AryLtrMp(up_h, 'H');
        AryLtrMp(up_i, 'I');
        AryLtrMp(up_j, 'J');
        AryLtrMp(up_k, 'K');
        AryLtrMp(up_l, 'L');
        AryLtrMp(up_m, 'M');
        AryLtrMp(up_n, 'N');
        AryLtrMp(up_o, 'O');
        AryLtrMp(up_p, 'P');
        AryLtrMp(up_q, 'Q');
        AryLtrMp(up_r, 'R');
        AryLtrMp(up_s, 'S');
        AryLtrMp(up_t, 'T');
        AryLtrMp(up_u, 'U');
        AryLtrMp(up_v, 'V');
        AryLtrMp(up_w, 'W');
        AryLtrMp(up_x, 'X');
        AryLtrMp(up_y, 'Y');
        AryLtrMp(up_z, 'Z');

        AryLtrMp(down_a, 'a');
        AryLtrMp(down_b, 'b');
        AryLtrMp(down_c, 'c');
        AryLtrMp(down_d, 'd');
        AryLtrMp(down_e, 'e');
        AryLtrMp(down_f, 'f');
        AryLtrMp(down_g, 'g');
        AryLtrMp(down_h, 'h');
        AryLtrMp(down_i, 'i');
        AryLtrMp(down_j, 'j');
        AryLtrMp(down_k, 'k');
        AryLtrMp(down_l, 'l');
        AryLtrMp(down_m, 'm');
        AryLtrMp(down_n, 'n');
        AryLtrMp(down_o, 'o');
        AryLtrMp(down_p, 'p');
        AryLtrMp(down_q, 'q');
        AryLtrMp(down_r, 'r');
        AryLtrMp(down_s, 's');
        AryLtrMp(down_t, 't');
        AryLtrMp(down_u, 'u');
        AryLtrMp(down_v, 'v');
        AryLtrMp(down_w, 'w');
        AryLtrMp(down_x, 'x');
        AryLtrMp(down_y, 'y');
        AryLtrMp(down_z, 'z');
    }

    /// Array match from Original -> Convert
    private void AryLtrMp(String[] array, char letter) {
        if (array != null) {
            for (String kanji : array) {
                kanjiToAlpha.put(kanji, letter);
            }
        }
    }

    /// Processes input string character by character to convert kanji
    /// back to their original alphanumerics as viable.
    public String dcKanji(String input) {
        if (input == null || input.isEmpty()) {
            return "";
        }

        StringBuilder result = new StringBuilder();
        for (int i = 0; i < input.length(); i++) {
            char currentChar = input.charAt(i);
            if (Character.isIdeographic(currentChar)) {
                String kanji = String.valueOf(currentChar);
                result.append(kanjiToAlpha.getOrDefault(kanji, currentChar));
            } else {
                result.append(currentChar);
            }
        }
        return result.toString();
    }

    public String decodeFromBase64(String encodedText) {
        if (encodedText == null || encodedText.isEmpty()) {
            return "";
        }
        try {
            byte[] decodedBytes = Base64.getDecoder().decode(encodedText);
            return new String(decodedBytes);
        } catch (IllegalArgumentException e) {
            throw new RuntimeException("前のステップからの base64 出力が壊れています。", e);
        }
    }

    /// Base letter spaced decoding, DC from type FullWidth
    /// to standard HalfWidth
    public String dc(String input) {
        if (input == null || input.isEmpty()) {
            return "";
        }

        StringBuilder result = new StringBuilder();
        boolean isInWord = false;

        for (int i = 0; i < input.length(); i++) {
            char c = input.charAt(i);

            // Skip consecutive spaces
            if (Character.isWhitespace(c)) {
                if (isInWord) {
                    isInWord = false;
                    result.append(' ');
                }
                continue;
            }
            isInWord = true;

            if (c >= 0xFF01 && c <= 0xFF5E) {
                c = (char) (c - 0xFF01 + 0x21);
            }

            if (Character.isLetter(c)) {
                char base = Character.isUpperCase(c) ? 'A' : 'a';
                c = (char) (((c - base + 23) % 26) + base);
            } else if (Character.isDigit(c)) {
                c = (char) (((c - '0' + 7) % 10) + '0');
            }

            result.append(c);
        }

        return result.toString().trim();
    }
}