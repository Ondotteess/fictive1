import syspro.tm.Tasks;
import syspro.tm.lexer.*;
import TokenGenerators.IndentationGenerator;
import TokenGenerators.TokenGenerator;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class Main {
    private static class MyLexer implements Lexer {

        @Override
        public List<Token> lex(String s) {

            // System.out.println(s);
            Head head = new Head(s);
            Context context = new Context();
            Tokenizer scanner = new Tokenizer(context);
            ArrayList<TokenGenerator> generators = new ArrayList<>();
            ArrayList<Token> tokens = new ArrayList<>();

            SequenceInfo sequenceInfo = head.readSequence();

            while (sequenceInfo != null) {
                generators.addAll(scanner.scan(sequenceInfo));
                sequenceInfo = head.readSequence();
            }

            for (int i = 0; i < context.currentIndentLevel; i++) {
                IndentationGenerator tg = new IndentationGenerator("", new StringBuilder(), context.start, context.leadingTriviaLength , context.trailingTriviaLength, 0, 0, true);
                tg.setDifference(-1);
                generators.add(tg);
            }

            for (TokenGenerator tg : generators) {
                Token d = tg.getToken();
                tokens.add(d);
                //System.out.println(d.getClass().toString() + "  " +
                //                     d + "\t\t" +
                //       d.start + "\t\t" +
                //       d.end + "\t\t" +
                //       d.leadingTriviaLength + "\t\t" +
                //       d.trailingTriviaLength);
            }

            return tokens;
        }
    }


    public static void main(String[] args) {
        Lexer MyLexer = new MyLexer();

        Tasks.Lexer.registerSolution(MyLexer, new TestMode().forceLineTerminators(TestLineTerminators.Native));
        System.out.println();
        Tasks.Lexer.registerSolution(MyLexer, new TestMode().forceLineTerminators(TestLineTerminators.CarriageReturnLineFeed));
        System.out.println();
        Tasks.Lexer.registerSolution(MyLexer, new TestMode().forceLineTerminators(TestLineTerminators.Mixed));
        System.out.println();
        Tasks.Lexer.registerSolution(MyLexer, new TestMode().forceLineTerminators(TestLineTerminators.LineFeed));
        System.out.println();
        Tasks.Lexer.registerSolution(MyLexer, new TestMode().parallel(true));
        System.out.println();
        Tasks.Lexer.registerSolution(MyLexer, new TestMode().shuffled(true));
        System.out.println();
        Tasks.Lexer.registerSolution(MyLexer, new TestMode().repeated(true));

    }

}
