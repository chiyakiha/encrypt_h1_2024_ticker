package src;

import java.util.Base64;
import java.util.Scanner;

/**
 * <h4>Base64 Encoding</h4>
 * An entered string is encoded to Base64 type.
 * The encoded string can be decoded based on any existing Base64 tool.
 * See below URI for more information.
 * <a href="https://www.zunouissiki.com/java-base64-encode-decode/">...</a>
 */

public class b64_mst {
    public static void main(String[] args) {
        Scanner scn = new Scanner(System.in);

        System.out.println("Base64に変換するテキストを入力してください（終了するにはreturnキーを2回押します）:");
        StringBuilder in = new StringBuilder();
        String line;

        while (scn.hasNextLine()) {
            line = scn.nextLine();
            if (line.isEmpty()) break;
            in.append(line).append("\n");
        }

        String encSt = convertToBase64(in.toString());
        System.out.println("\nBase64 でエンコードされたテキスト:");
        System.out.println(encSt);

        scn.close();
    }

    /// This uses the original method specified by <code>java.util.Base64.Encoder</code>.
    ///
    /// @see java.util.Base64.Encoder
    public static String convertToBase64(String text) {
        if (text == null || text.isEmpty()) {
            return "";
        }
        return Base64.getEncoder().encodeToString(text.getBytes());
    }
}