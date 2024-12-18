package src;

import java.util.Base64;
import java.util.Scanner;

/**
 * <h4>DECODE: Base64 Encoding</h4>
 * <b><i>See <code>b64_mst</code> for information about the complementing encoding system.</i></b><br><br>
 * Note that this is the only method system that can return the exact original string (as b64 supplements
 * built-in systems for transcoding differently-cased characters).
 * <i>Note: This method only accepts input via <code>java.util.Scanner(System.in)</code>.</i>
 *
 * @see java.util.Scanner
 * @see java.util.Base64.Decoder
 */

public class b64_dc {
    public static void main(String[] args) {
        Scanner scn = new Scanner(System.in);

        System.out.println("Base64 でエンコードされたテキストを入力します (終了するにはreturnキーを 2 回押します):");
        StringBuilder in = new StringBuilder();
        String line;

        while (scn.hasNextLine()) {
            line = scn.nextLine();
            if (line.isEmpty()) break;
            in.append(line);
        }
        String dcStr = decodeFromBase64(in.toString());
        System.out.println("\nデコードされたテキスト:");
        System.out.println(dcStr);
        scn.close();
    }

    /// This uses the existing methods provided by <code>java.util.Base64</code>.
    ///
    /// @see java.util.Base64.Decoder
    public static String decodeFromBase64(String encTx) {
        if (encTx == null || encTx.isEmpty()) {
            return "";
        }
        try {
            byte[] decodedBytes = Base64.getDecoder().decode(encTx);
            return new String(decodedBytes);
        } catch (IllegalArgumentException e) {
            return "エラー: 無効な Base64 入力";
        }
    }
}