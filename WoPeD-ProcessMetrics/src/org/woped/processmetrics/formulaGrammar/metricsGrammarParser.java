

  package org.woped.processmetrics.formulaGrammar;


import org.antlr.runtime.BitSet;
import org.antlr.runtime.NoViableAltException;
import org.antlr.runtime.Parser;
import org.antlr.runtime.RecognitionException;
import org.antlr.runtime.RecognizerSharedState;
import org.antlr.runtime.Token;
import org.antlr.runtime.TokenStream;

public class metricsGrammarParser extends Parser {
    public static final String[] tokenNames = new String[] {
        "<invalid>", "<EOR>", "<DOWN>", "<UP>", "IDENT", "DOUBLE", "MULTILINE_COMMENT", "STRING_LITERAL", "CHAR_LITERAL", "LETTER", "DIGIT", "WS", "COMMENT", "'('", "')'", "'log2('", "'pow('", "','", "'sqrt('", "'+'", "'-'", "'*'", "'/'", "'mod'"
    };
    public static final int LETTER=9;
    public static final int T__23=23;
    public static final int T__22=22;
    public static final int T__21=21;
    public static final int T__20=20;
    public static final int EOF=-1;
    public static final int T__19=19;
    public static final int MULTILINE_COMMENT=6;
    public static final int WS=11;
    public static final int T__16=16;
    public static final int STRING_LITERAL=7;
    public static final int T__15=15;
    public static final int T__18=18;
    public static final int T__17=17;
    public static final int CHAR_LITERAL=8;
    public static final int T__14=14;
    public static final int T__13=13;
    public static final int DOUBLE=5;
    public static final int IDENT=4;
    public static final int DIGIT=10;
    public static final int COMMENT=12;

    // delegates
    // delegators


        public metricsGrammarParser(TokenStream input) {
            this(input, new RecognizerSharedState());
        }
        public metricsGrammarParser(TokenStream input, RecognizerSharedState state) {
            super(input, state);
             
        }
        

    public String[] getTokenNames() { return metricsGrammarParser.tokenNames; }
    


    // $ANTLR start "evaluator"
    // /home/tobias/workspace/Woped/src/antlrGrammar/metricsGrammar.g:13:1: evaluator returns [double result] : expression EOF ;
    public final double evaluator() throws RecognitionException {
        double result = 0.0;

        double expression1 = 0.0;


        try {
            // /home/tobias/workspace/Woped/src/antlrGrammar/metricsGrammar.g:14:2: ( expression EOF )
            // /home/tobias/workspace/Woped/src/antlrGrammar/metricsGrammar.g:14:4: expression EOF
            {
            pushFollow(FOLLOW_expression_in_evaluator42);
            expression1=expression();

            state._fsp--;

            match(input,EOF,FOLLOW_EOF_in_evaluator44); 
             result = expression1; 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return result;
    }
    // $ANTLR end "evaluator"


    // $ANTLR start "term"
    // /home/tobias/workspace/Woped/src/antlrGrammar/metricsGrammar.g:19:1: term returns [double result] : ( IDENT | '(' expression ')' | DOUBLE | sqrt | pow | log2 );
    public final double term() throws RecognitionException {
        double result = 0.0;

        Token DOUBLE3=null;
        double expression2 = 0.0;

        double sqrt4 = 0.0;

        double pow5 = 0.0;

        double log26 = 0.0;


        try {
            // /home/tobias/workspace/Woped/src/antlrGrammar/metricsGrammar.g:20:2: ( IDENT | '(' expression ')' | DOUBLE | sqrt | pow | log2 )
            int alt1=6;
            switch ( input.LA(1) ) {
            case IDENT:
                {
                alt1=1;
                }
                break;
            case 13:
                {
                alt1=2;
                }
                break;
            case DOUBLE:
                {
                alt1=3;
                }
                break;
            case 18:
                {
                alt1=4;
                }
                break;
            case 16:
                {
                alt1=5;
                }
                break;
            case 15:
                {
                alt1=6;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 1, 0, input);

                throw nvae;
            }

            switch (alt1) {
                case 1 :
                    // /home/tobias/workspace/Woped/src/antlrGrammar/metricsGrammar.g:20:4: IDENT
                    {
                    match(input,IDENT,FOLLOW_IDENT_in_term64); 
                    result = 0;

                    }
                    break;
                case 2 :
                    // /home/tobias/workspace/Woped/src/antlrGrammar/metricsGrammar.g:21:4: '(' expression ')'
                    {
                    match(input,13,FOLLOW_13_in_term71); 
                    pushFollow(FOLLOW_expression_in_term73);
                    expression2=expression();

                    state._fsp--;

                    match(input,14,FOLLOW_14_in_term75); 
                    result = expression2;

                    }
                    break;
                case 3 :
                    // /home/tobias/workspace/Woped/src/antlrGrammar/metricsGrammar.g:22:4: DOUBLE
                    {
                    DOUBLE3=(Token)match(input,DOUBLE,FOLLOW_DOUBLE_in_term82); 
                    result = Double.parseDouble((DOUBLE3!=null?DOUBLE3.getText():null));

                    }
                    break;
                case 4 :
                    // /home/tobias/workspace/Woped/src/antlrGrammar/metricsGrammar.g:23:4: sqrt
                    {
                    pushFollow(FOLLOW_sqrt_in_term89);
                    sqrt4=sqrt();

                    state._fsp--;

                    result = sqrt4;

                    }
                    break;
                case 5 :
                    // /home/tobias/workspace/Woped/src/antlrGrammar/metricsGrammar.g:24:4: pow
                    {
                    pushFollow(FOLLOW_pow_in_term96);
                    pow5=pow();

                    state._fsp--;

                    result = pow5;

                    }
                    break;
                case 6 :
                    // /home/tobias/workspace/Woped/src/antlrGrammar/metricsGrammar.g:25:4: log2
                    {
                    pushFollow(FOLLOW_log2_in_term104);
                    log26=log2();

                    state._fsp--;

                    result = log26;

                    }
                    break;

            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return result;
    }
    // $ANTLR end "term"


    // $ANTLR start "log2"
    // /home/tobias/workspace/Woped/src/antlrGrammar/metricsGrammar.g:28:1: log2 returns [double result] : 'log2(' expression ')' ;
    public final double log2() throws RecognitionException {
        double result = 0.0;

        double expression7 = 0.0;


        try {
            // /home/tobias/workspace/Woped/src/antlrGrammar/metricsGrammar.g:29:2: ( 'log2(' expression ')' )
            // /home/tobias/workspace/Woped/src/antlrGrammar/metricsGrammar.g:29:4: 'log2(' expression ')'
            {
            match(input,15,FOLLOW_15_in_log2121); 
            pushFollow(FOLLOW_expression_in_log2123);
            expression7=expression();

            state._fsp--;

            match(input,14,FOLLOW_14_in_log2125); 
            result = Math.log(expression7) / Math.log(2);

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return result;
    }
    // $ANTLR end "log2"


    // $ANTLR start "pow"
    // /home/tobias/workspace/Woped/src/antlrGrammar/metricsGrammar.g:31:1: pow returns [double result] : 'pow(' expression ',' DOUBLE ')' ;
    public final double pow() throws RecognitionException {
        double result = 0.0;

        Token DOUBLE9=null;
        double expression8 = 0.0;


        try {
            // /home/tobias/workspace/Woped/src/antlrGrammar/metricsGrammar.g:32:2: ( 'pow(' expression ',' DOUBLE ')' )
            // /home/tobias/workspace/Woped/src/antlrGrammar/metricsGrammar.g:32:4: 'pow(' expression ',' DOUBLE ')'
            {
            match(input,16,FOLLOW_16_in_pow140); 
            pushFollow(FOLLOW_expression_in_pow142);
            expression8=expression();

            state._fsp--;

            match(input,17,FOLLOW_17_in_pow144); 
            DOUBLE9=(Token)match(input,DOUBLE,FOLLOW_DOUBLE_in_pow146); 
            match(input,14,FOLLOW_14_in_pow149); 
            result = Math.pow(expression8,Double.parseDouble((DOUBLE9!=null?DOUBLE9.getText():null)));

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return result;
    }
    // $ANTLR end "pow"


    // $ANTLR start "sqrt"
    // /home/tobias/workspace/Woped/src/antlrGrammar/metricsGrammar.g:34:1: sqrt returns [double result] : 'sqrt(' expression ')' ;
    public final double sqrt() throws RecognitionException {
        double result = 0.0;

        double expression10 = 0.0;


        try {
            // /home/tobias/workspace/Woped/src/antlrGrammar/metricsGrammar.g:35:2: ( 'sqrt(' expression ')' )
            // /home/tobias/workspace/Woped/src/antlrGrammar/metricsGrammar.g:35:4: 'sqrt(' expression ')'
            {
            match(input,18,FOLLOW_18_in_sqrt165); 
            pushFollow(FOLLOW_expression_in_sqrt167);
            expression10=expression();

            state._fsp--;

            match(input,14,FOLLOW_14_in_sqrt169); 
            result = Math.sqrt(expression10);

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return result;
    }
    // $ANTLR end "sqrt"


    // $ANTLR start "unary"
    // /home/tobias/workspace/Woped/src/antlrGrammar/metricsGrammar.g:37:1: unary returns [double result] : ( '+' | '-' )* term ;
    public final double unary() throws RecognitionException {
        double result = 0.0;

        double term11 = 0.0;


        try {
            // /home/tobias/workspace/Woped/src/antlrGrammar/metricsGrammar.g:38:2: ( ( '+' | '-' )* term )
            // /home/tobias/workspace/Woped/src/antlrGrammar/metricsGrammar.g:38:4: ( '+' | '-' )* term
            {
             boolean positive = true; 
            // /home/tobias/workspace/Woped/src/antlrGrammar/metricsGrammar.g:39:3: ( '+' | '-' )*
            loop2:
            do {
                int alt2=3;
                int LA2_0 = input.LA(1);

                if ( (LA2_0==19) ) {
                    alt2=1;
                }
                else if ( (LA2_0==20) ) {
                    alt2=2;
                }


                switch (alt2) {
            	case 1 :
            	    // /home/tobias/workspace/Woped/src/antlrGrammar/metricsGrammar.g:39:4: '+'
            	    {
            	    match(input,19,FOLLOW_19_in_unary190); 

            	    }
            	    break;
            	case 2 :
            	    // /home/tobias/workspace/Woped/src/antlrGrammar/metricsGrammar.g:39:10: '-'
            	    {
            	    match(input,20,FOLLOW_20_in_unary194); 
            	     positive = !positive; 

            	    }
            	    break;

            	default :
            	    break loop2;
                }
            } while (true);

            pushFollow(FOLLOW_term_in_unary200);
            term11=term();

            state._fsp--;


            			result = term11;
            			if (!positive)
            				result = -result;
            		

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return result;
    }
    // $ANTLR end "unary"


    // $ANTLR start "mult"
    // /home/tobias/workspace/Woped/src/antlrGrammar/metricsGrammar.g:47:1: mult returns [double result] : op1= unary ( '*' op2= unary | '/' op2= unary | 'mod' op2= unary )* ;
    public final double mult() throws RecognitionException {
        double result = 0.0;

        double op1 = 0.0;

        double op2 = 0.0;


        try {
            // /home/tobias/workspace/Woped/src/antlrGrammar/metricsGrammar.g:48:2: (op1= unary ( '*' op2= unary | '/' op2= unary | 'mod' op2= unary )* )
            // /home/tobias/workspace/Woped/src/antlrGrammar/metricsGrammar.g:48:4: op1= unary ( '*' op2= unary | '/' op2= unary | 'mod' op2= unary )*
            {
            pushFollow(FOLLOW_unary_in_mult221);
            op1=unary();

            state._fsp--;

             result = op1; 
            // /home/tobias/workspace/Woped/src/antlrGrammar/metricsGrammar.g:49:3: ( '*' op2= unary | '/' op2= unary | 'mod' op2= unary )*
            loop3:
            do {
                int alt3=4;
                switch ( input.LA(1) ) {
                case 21:
                    {
                    alt3=1;
                    }
                    break;
                case 22:
                    {
                    alt3=2;
                    }
                    break;
                case 23:
                    {
                    alt3=3;
                    }
                    break;

                }

                switch (alt3) {
            	case 1 :
            	    // /home/tobias/workspace/Woped/src/antlrGrammar/metricsGrammar.g:49:5: '*' op2= unary
            	    {
            	    match(input,21,FOLLOW_21_in_mult229); 
            	    pushFollow(FOLLOW_unary_in_mult233);
            	    op2=unary();

            	    state._fsp--;

            	     result = result * op2; 

            	    }
            	    break;
            	case 2 :
            	    // /home/tobias/workspace/Woped/src/antlrGrammar/metricsGrammar.g:50:5: '/' op2= unary
            	    {
            	    match(input,22,FOLLOW_22_in_mult241); 
            	    pushFollow(FOLLOW_unary_in_mult245);
            	    op2=unary();

            	    state._fsp--;

            	     result = result / op2; 

            	    }
            	    break;
            	case 3 :
            	    // /home/tobias/workspace/Woped/src/antlrGrammar/metricsGrammar.g:51:5: 'mod' op2= unary
            	    {
            	    match(input,23,FOLLOW_23_in_mult253); 
            	    pushFollow(FOLLOW_unary_in_mult257);
            	    op2=unary();

            	    state._fsp--;

            	     result = result % op2; 

            	    }
            	    break;

            	default :
            	    break loop3;
                }
            } while (true);


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return result;
    }
    // $ANTLR end "mult"


    // $ANTLR start "expression"
    // /home/tobias/workspace/Woped/src/antlrGrammar/metricsGrammar.g:55:1: expression returns [double result] : op1= mult ( '+' op2= mult | '-' op2= mult )* ;
    public final double expression() throws RecognitionException {
        double result = 0.0;

        double op1 = 0.0;

        double op2 = 0.0;


        try {
            // /home/tobias/workspace/Woped/src/antlrGrammar/metricsGrammar.g:56:2: (op1= mult ( '+' op2= mult | '-' op2= mult )* )
            // /home/tobias/workspace/Woped/src/antlrGrammar/metricsGrammar.g:56:4: op1= mult ( '+' op2= mult | '-' op2= mult )*
            {
            pushFollow(FOLLOW_mult_in_expression282);
            op1=mult();

            state._fsp--;

             result = op1; 
            // /home/tobias/workspace/Woped/src/antlrGrammar/metricsGrammar.g:57:3: ( '+' op2= mult | '-' op2= mult )*
            loop4:
            do {
                int alt4=3;
                int LA4_0 = input.LA(1);

                if ( (LA4_0==19) ) {
                    alt4=1;
                }
                else if ( (LA4_0==20) ) {
                    alt4=2;
                }


                switch (alt4) {
            	case 1 :
            	    // /home/tobias/workspace/Woped/src/antlrGrammar/metricsGrammar.g:57:5: '+' op2= mult
            	    {
            	    match(input,19,FOLLOW_19_in_expression290); 
            	    pushFollow(FOLLOW_mult_in_expression294);
            	    op2=mult();

            	    state._fsp--;

            	     result = result + op2; 

            	    }
            	    break;
            	case 2 :
            	    // /home/tobias/workspace/Woped/src/antlrGrammar/metricsGrammar.g:58:5: '-' op2= mult
            	    {
            	    match(input,20,FOLLOW_20_in_expression302); 
            	    pushFollow(FOLLOW_mult_in_expression306);
            	    op2=mult();

            	    state._fsp--;

            	     result = result - op2; 

            	    }
            	    break;

            	default :
            	    break loop4;
                }
            } while (true);


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return result;
    }
    // $ANTLR end "expression"

    // Delegated rules


 

    public static final BitSet FOLLOW_expression_in_evaluator42 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_evaluator44 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_IDENT_in_term64 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_13_in_term71 = new BitSet(new long[]{0x00000000001DA030L});
    public static final BitSet FOLLOW_expression_in_term73 = new BitSet(new long[]{0x0000000000004000L});
    public static final BitSet FOLLOW_14_in_term75 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_DOUBLE_in_term82 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_sqrt_in_term89 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_pow_in_term96 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_log2_in_term104 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_15_in_log2121 = new BitSet(new long[]{0x00000000001DA030L});
    public static final BitSet FOLLOW_expression_in_log2123 = new BitSet(new long[]{0x0000000000004000L});
    public static final BitSet FOLLOW_14_in_log2125 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_16_in_pow140 = new BitSet(new long[]{0x00000000001DA030L});
    public static final BitSet FOLLOW_expression_in_pow142 = new BitSet(new long[]{0x0000000000020000L});
    public static final BitSet FOLLOW_17_in_pow144 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_DOUBLE_in_pow146 = new BitSet(new long[]{0x0000000000004000L});
    public static final BitSet FOLLOW_14_in_pow149 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_18_in_sqrt165 = new BitSet(new long[]{0x00000000001DA030L});
    public static final BitSet FOLLOW_expression_in_sqrt167 = new BitSet(new long[]{0x0000000000004000L});
    public static final BitSet FOLLOW_14_in_sqrt169 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_19_in_unary190 = new BitSet(new long[]{0x00000000001DA030L});
    public static final BitSet FOLLOW_20_in_unary194 = new BitSet(new long[]{0x00000000001DA030L});
    public static final BitSet FOLLOW_term_in_unary200 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_unary_in_mult221 = new BitSet(new long[]{0x0000000000E00002L});
    public static final BitSet FOLLOW_21_in_mult229 = new BitSet(new long[]{0x00000000001DA030L});
    public static final BitSet FOLLOW_unary_in_mult233 = new BitSet(new long[]{0x0000000000E00002L});
    public static final BitSet FOLLOW_22_in_mult241 = new BitSet(new long[]{0x00000000001DA030L});
    public static final BitSet FOLLOW_unary_in_mult245 = new BitSet(new long[]{0x0000000000E00002L});
    public static final BitSet FOLLOW_23_in_mult253 = new BitSet(new long[]{0x00000000001DA030L});
    public static final BitSet FOLLOW_unary_in_mult257 = new BitSet(new long[]{0x0000000000E00002L});
    public static final BitSet FOLLOW_mult_in_expression282 = new BitSet(new long[]{0x0000000000180002L});
    public static final BitSet FOLLOW_19_in_expression290 = new BitSet(new long[]{0x00000000001DA030L});
    public static final BitSet FOLLOW_mult_in_expression294 = new BitSet(new long[]{0x0000000000180002L});
    public static final BitSet FOLLOW_20_in_expression302 = new BitSet(new long[]{0x00000000001DA030L});
    public static final BitSet FOLLOW_mult_in_expression306 = new BitSet(new long[]{0x0000000000180002L});

}