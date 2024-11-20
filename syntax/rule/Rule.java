package syntax.rule;

import syntax.Context;
import syspro.tm.parser.SyntaxNode;

public interface Rule {
    static SyntaxNode parse(Context context) {
        return null;
    }
}
