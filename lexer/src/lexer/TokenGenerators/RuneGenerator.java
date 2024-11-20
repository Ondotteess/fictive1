package lexer.TokenGenerators;

import syspro.tm.lexer.RuneLiteralToken;
import syspro.tm.lexer.Token;

public class RuneGenerator extends TokenGenerator{

    public RuneGenerator(String sequence,
                         StringBuilder buffer,
                         int start,
                         int leadingTrivialLen,
                         int trailingTrivialLen) {

        super(sequence, buffer, start, leadingTrivialLen, trailingTrivialLen);
        getBuffer();
    }

    @Override
    protected void getBuffer() {

    }

    @Override
    public Token getToken() {
        int end = start + tokenLen() - 1;
        return new RuneLiteralToken(
                start,
                end,
                leadingTrivialLen,
                trailingTrivialLen,
                Character.codePointAt(sequence,1));
    }

    @Override
    public int computeEnd(){
        return start + tokenLen() - 2 + leadingTrivialLen + trailingTrivialLen;
    }

}



