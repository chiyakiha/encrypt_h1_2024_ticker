package src;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * <h4>Initialiser</h4>
 * Type in an item key, launch the specific tool.<br>
 * WARNING: On-demand exit is only available with High 3.
 */

public class Main {
    private final Map<String, Class<?>> funcClass;
    private final Scanner scanner;
    private final String[] programArgs;

    public Main(String[] args) {
        this.funcClass = new HashMap<>();
        this.scanner = new Scanner(System.in);
        this.programArgs = args;
        regFunc();
    }

    public static void main(String[] args) {
        new Main(args).run();
    }

    /// Individual classes are callable from this thread and are assigned
    /// three-character IDs that the user can enter to switch to the assigned
    /// tool. Tool switching without terminating the existing thread is not
    /// possible.
    private void regFunc() {
        try {
            funcClass.put("con", Class.forName("conv_mst"));
            funcClass.put("b64", Class.forName("b64_mst"));
            funcClass.put("jax", Class.forName("jax_mst"));
            funcClass.put("cnv", Class.forName("conv_dc"));
            funcClass.put("64d", Class.forName("b64_dc"));
            funcClass.put("jxd", Class.forName("jax_dc"));
        } catch (ClassNotFoundException e) {
            System.err.println("Cannot load func: " + e.getMessage());
        }
    }

    /// This is the interactable thread. The list of callable items is
    /// purposely hard-coded as the self-assignable version of this list would require
    /// the user to enter the entire description of the requested tool to launch it.
    public void run() {
        while (true) {
            System.out.println("Available functions:");
            System.out.println("""
                    == Encoders ==
                    con: basic conversion
                    b64: base64 conversion
                    jax: alpha->kanji conversion
                    == Decoders ==
                    cnv: basic decoder
                    64d: base64 decoder
                    jxd: kanji->alpha decoder""");
            System.out.println("\nType an option below.");

            System.out.println("type --exit-- to exit\n");
            System.out.print("Select option: ");

            String command = scanner.nextLine().toLowerCase();
            if (command.equals("--exit--")) {
                System.out.println("Exiting");
                break;
            }

            executeFunction(command);
        }
        scanner.close();
    }

    /// This object verifies that the user typed in an existing object key,
    /// and will invoke the referenced class if valid. A non-existant warning will
    /// be presented should this check fail.
    private void executeFunction(String command) {
        Class<?> functionClass = funcClass.get(command);
        if (functionClass == null) {
            System.err.println("ALERT: This command doesn't exist.");
            return;
        }

        try {
            System.out.println("Loading " + command);
            Method mainMethod = functionClass.getMethod("main", String[].class);
            System.out.println("Done loading...");
            mainMethod.invoke(null, (Object) programArgs);
        } catch (Exception e) {
            System.err.println("Cannot execute: " + e.getMessage());
        }
    }
}