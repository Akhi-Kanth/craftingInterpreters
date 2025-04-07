package lox;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

/**
 * lox
 */
public class Lox {
    public static boolean hadError = false;

    // main method
    public static void main(String[] args) throws IOException {
        int argsLength = args.length;

        // inncorect usage of the program, user inputed two arguments
        if (argsLength > 1) {
            System.out.println("Usage: jlox [script]");
            System.exit(64);
        }
        // called with a file argument
        else if (argsLength == 1) {
            runFile(args[0]);
        }
        // called without a file argument
        else {
            runPrompt();
        }
    }

    private static void runFile(String path) throws IOException {
        // read all the bytes from the file
        byte[] bytes = Files.readAllBytes(Paths.get(path));

        // run the bytes as a string
        run(new String(bytes, Charset.defaultCharset()));

        // exit the system if there is an error
        if (hadError) System.exit(65);
    }

    private static void runPrompt() throws IOException {
        // create a reader to read from the console
        InputStreamReader input = new InputStreamReader(System.in);
        BufferedReader reader = new BufferedReader(input);

        // infinite loop to read from the console
        // until the user enters in a null line
        for (;;){
            System.out.print(">>> ");
            String line = reader.readLine();
            if (promptExitChecker(line)) break;
            run(line);
            hadError = false;
        }
    }

    private static boolean promptExitChecker(String input) {
        if (input == null) System.err.println("input error on promptExitChecker()");

        return (input.equals("exit") || input.equals("quit") || input.equals("exit()") || input.equals("quit()") || input == null);
    }

    private static void run(String source) {
        // creates personal scanner object
        Scanner scanner = new Scanner(source);
        List<Token> tokens = scanner.scanTokens();

        // for now, we will just print out the tokens
        for (Token token : tokens) {
            // System.out.println("This is running lol");
            System.out.println(token);
        }
    }

    static void error(int line, String message){
        // report the error
        report(line, "", message);
    }

    private static void report(int line, String where, String message){
        // print out the error message
        System.err.println("[line " + line + "] Error " + where + ": " + message);
        // set the hadError flag to true, we check this flag before running a line
        hadError = true;
    }


}
