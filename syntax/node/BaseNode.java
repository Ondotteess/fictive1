package syntax.node;

import java.util.ArrayList;
import java.util.List;
import java.util.List;
import syspro.tm.lexer.Token;
import syspro.tm.parser.AnySyntaxKind;
import syspro.tm.parser.SyntaxKind;
import syspro.tm.parser.SyntaxNode;
import syspro.tm.parser.TextSpan;



public class BaseNode implements SyntaxNode {
    private final AnySyntaxKind kind;
    private final Token token; // Используется для терминальных узлов
    private final List<SyntaxNode> children;

    public BaseNode(SyntaxKind kind) {
        this(kind, null);
    }

    public BaseNode(AnySyntaxKind kind, Token token) {
        this.kind = kind;
        this.token = token;
        this.children = new ArrayList<>();
    }

    public void addChild(SyntaxNode child) {
        if (child != null) {
            children.add(child);
        }
    }

    @Override
    public AnySyntaxKind kind() {
        return kind;
    }

    @Override
    public int slotCount() {
        return children.size();
    }

    @Override
    public SyntaxNode slot(int index) {
        if (index < 0 || index >= children.size()) {
            return null;
        }
        return children.get(index);
    }

    @Override
    public Token token() {
        return token;
    }

    @Override
    public boolean isTerminal() {
        return token != null;
    }

    @Override
    public SyntaxNode firstTerminal() {
        if (isTerminal()) {
            return this;
        }
        for (SyntaxNode child : children) {
            SyntaxNode terminal = child.firstTerminal();
            if (terminal != null) {
                return terminal;
            }
        }
        return null;
    }

    @Override
    public SyntaxNode lastTerminal() {
        if (isTerminal()) {
            return this;
        }
        for (int i = children.size() - 1; i >= 0; i--) {
            SyntaxNode terminal = children.get(i).lastTerminal();
            if (terminal != null) {
                return terminal;
            }
        }
        return null;
    }

    @Override
    public List<SyntaxNode> descendants(boolean includeSelf) {
        List<SyntaxNode> result = new ArrayList<>();
        if (includeSelf) {
            result.add(this);
        }
        for (SyntaxNode child : children) {
            result.addAll(child.descendants(true));
        }
        return result;
    }

    @Override
    public int position() {
        SyntaxNode first = firstTerminal();
        return first != null ? first.token().start : -1;
    }

    @Override
    public int fullLength() {
        SyntaxNode first = firstTerminal();
        SyntaxNode last = lastTerminal();
        return first != null && last != null ? last.token().end - first.token().start + 1 : 0;
    }

    @Override
    public int leadingTriviaLength() {
        SyntaxNode first = firstTerminal();
        return first != null ? first.token().leadingTriviaLength : 0;
    }

    @Override
    public int trailingTriviaLength() {
        SyntaxNode last = lastTerminal();
        return last != null ? last.token().trailingTriviaLength : 0;
    }

    @Override
    public TextSpan span() {
        int start = position() + leadingTriviaLength();
        int length = fullLength() - leadingTriviaLength() - trailingTriviaLength();
        return new TextSpan(start, Math.max(length, 0));
    }

    @Override
    public TextSpan fullSpan() {
        int start = position();
        int length = fullLength();
        return new TextSpan(start, length);
    }

    @Override
    public String toString() {
        return "SyntaxNode{" +
                "kind=" + kind +
                ", token=" + token +
                ", children=" + children +
                '}';
    }
}