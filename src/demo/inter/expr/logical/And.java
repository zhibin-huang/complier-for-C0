package demo.inter.expr.logical;

import demo.inter.expr.Expr;
import demo.lexer.Token;

public class And extends Logical {
    public And(Token tok, Expr x1, Expr x2){
        super(tok, x1, x2);
    }

    /* //f == 0
              ifFalse expr1 goto label
              if expr2 goto t
        label:

        //f != 0
              ifFalse expr goto f
              if expr2 goto t
              goto f
     */
    public void jumping(int t, int f){
        int label = f != 0 ? f : newlabel();
        expr1.jumping(0, label);
        expr2.jumping(t, f);
        if( f == 0 ) emitlabel(label);
    }

    public String AST_str(int col){
        String AST_child = "\t".repeat(Math.max(0,col + 1)) +
                op.toString() +
                ",\n"+
                expr1.AST_str(col + 1) +
                ",\n"+
                expr2.AST_str(col + 1);

        return "\t".repeat(Math.max(0, col)) +
                "And(" +
                '\n' +
                AST_child +
                '\n' +
                "\t".repeat(Math.max(0, col)) +
                ')';
    }

}