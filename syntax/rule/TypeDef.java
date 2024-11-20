package syntax.rule;

import syntax.Context;
import syntax.node.BaseNode;
import syspro.tm.lexer.IdentifierToken;
import syspro.tm.lexer.Keyword;
import syspro.tm.lexer.KeywordToken;
import syspro.tm.lexer.Token;
import syspro.tm.parser.SyntaxKind;
import syspro.tm.parser.SyntaxNode;

import javax.crypto.KEM;

public class TypeDef implements Rule{
    public static SyntaxNode parse(Context context) {

        BaseNode typeDef = new BaseNode(SyntaxKind.TYPE_DEFINITION);

        Token keyword = context.getToken();
        Token identifier = context.getToken();

        if (isContextual(keyword)) {
            //System.out.println("lol");
        } else {
            // если нет класс/интерфейс/объект -> участок токена помечаем
            // как не валидный
        }

        if (isIdent(identifier)) {
            System.out.println(identifier.toString());
        } else {

        }

        return null;
    }

    private static boolean isContextual(Token keyword) {
        return keyword instanceof IdentifierToken &&
                (((IdentifierToken) keyword).contextualKeyword == Keyword.CLASS ||
                        ((IdentifierToken) keyword).contextualKeyword == Keyword.OBJECT ||
                        ((IdentifierToken) keyword).contextualKeyword == Keyword.INTERFACE);
    }

    private static boolean isIdent(Token ident) {
        return ident instanceof IdentifierToken &&
                (((IdentifierToken) ident).contextualKeyword == null);
    }

}
