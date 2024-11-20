package lexer.TokenGenerators;

import syspro.tm.lexer.BadToken;
import syspro.tm.lexer.StringLiteralToken;
import syspro.tm.lexer.Token;

public class StringGenerator extends TokenGenerator {

    private int currentPosition;
    private Token outToken;

    public StringGenerator(String sequence,
                           StringBuilder buffer,
                           int start,
                           int leadingTrivialLen,
                           int trailingTrivialLen) {
        super(sequence, buffer, start, leadingTrivialLen, trailingTrivialLen);
        this.currentPosition = 1;
        getBuffer();
    }

    @Override
    protected void getBuffer() {
        while (currentPosition < sequence.length()) {
            int currentChar = sequence.codePointAt(currentPosition);

            if (currentChar == '"') {
                buffer.appendCodePoint(currentChar);
                currentPosition += Character.charCount(currentChar);;
                this.outToken = new StringLiteralToken(
                                        start,
                                        computeEnd(),
                                        leadingTrivialLen,
                                        trailingTrivialLen,
                                        buffer.toString());
                return;
            }

            if (currentChar != '\r' && currentChar != '\n') {
                buffer.appendCodePoint(currentChar);
            } else {
                buffer.appendCodePoint(currentChar);
                this.outToken = new BadToken(start,
                                             computeEnd(),
                                             leadingTrivialLen,
                                             trailingTrivialLen);
                return;
            }
            currentPosition += Character.charCount(currentChar);;
        }
        this.outToken = new BadToken(start,
                                     computeEnd(),
                                     leadingTrivialLen,
                                     trailingTrivialLen);
    }

    @Override
    public Token getToken() {
        return outToken;
    }

}
