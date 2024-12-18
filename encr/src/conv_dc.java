package src;

import java.util.Scanner;

/**
 * <h4>DECODE: Basic letter shifting + spacing</h4>
 * <b><i>See <code>conv_mst</code> for information about the complementing encoding system.</i></b><br><br>
 * Important note: THIS METHOD IS NOT PERFECT! -> Spaces cannot be replicated perfectly, as there is
 * no logic that can determine the original sentence formatting.
 * <i>Note: This method only accepts input via <code>java.util.Scanner(System.in)</code>.</i>
 *
 * @see java.util.Scanner
 */

public class conv_dc {
    public static String dc(String input) {
        StringBuilder rst = new StringBuilder();
        boolean lastWasSpace = false;

        for (char c : input.toCharArray()) {
            if (c == '　') {
                if (!lastWasSpace) {
                    rst.append(' ');
                    lastWasSpace = true;
                }
                continue;
            }
            lastWasSpace = false;

            char decodedChar = c;

            if (decodedChar >= 0xFF01 && decodedChar <= 0xFF5E) {
                decodedChar = (char) (decodedChar - 0xFF01 + 0x21);
            }

            if (Character.isLetter(decodedChar)) {
                char base = Character.isUpperCase(decodedChar) ? 'A' : 'a';
                decodedChar = (char) (((decodedChar - base + 23) % 26) + base);
            } else if (Character.isDigit(decodedChar)) {
                decodedChar = (char) (((decodedChar - '0' + 7) % 10) + '0');
            }

            rst.append(decodedChar);
        }

        return rst.toString().trim();
    }

    /// Only <code>java.util.Scanner(System.in)</code> method is usable here.
    public static void main(String[] args) {
        Scanner scn = new Scanner(System.in);

        System.out.println("デコードするエンコードされたテキストを入力してください:");
        String enc = scn.nextLine();

        System.out.println("デコードされたテキスト:");
        System.out.println(dc(enc));

        scn.close();
    }
}

