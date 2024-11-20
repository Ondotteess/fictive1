package syntax.rule;

import syntax.Context;
import syntax.node.BaseNode;
import syspro.tm.parser.SyntaxKind;
import syspro.tm.parser.SyntaxNode;

public class SourceText implements Rule{

    public static SyntaxNode parse(Context context) {
        BaseNode root = new BaseNode(SyntaxKind.SOURCE_TEXT);
        while (context.lookAhead(0) != null) {
            root.addChild(TypeDef.parse(context));
        }
        return root;
    }

}
