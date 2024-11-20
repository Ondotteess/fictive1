package syntax;

import syspro.tm.lexer.Token;

import java.util.List;

public class Context {
    private final List<Token> tokens;
    private int position;

    public Context(List<Token> tokens) {
        this.tokens = tokens;
        this.position = 0;
    }

    public Token getToken() {
        return (position < tokens.size()) ? tokens.get(position++) : null;
    }

    public Token lookAhead(int distance) {
        return (position + distance < tokens.size())
                ? tokens.get(position + distance) : null;
    }

    public Token lookAhead() {
        return (position + 1 < tokens.size())
                ? tokens.get(position + 1) : null;
    }

    int getPosition(){
        return position;
    }

    void setPosition(int position) {
        this.position = position;
    }

}
