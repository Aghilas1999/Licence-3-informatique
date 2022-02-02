/* This file was generated by SableCC (http://www.sablecc.org/). */

package sc.parser;

import sc.lexer.*;
import sc.node.*;
import sc.analysis.*;
import java.util.*;

import java.io.DataInputStream;
import java.io.BufferedInputStream;
import java.io.IOException;

@SuppressWarnings("nls")
public class Parser
{
    public final Analysis ignoredTokens = new AnalysisAdapter();

    protected ArrayList<Object> nodeList;

    private final Lexer lexer;
    private final ListIterator<Object> stack = new LinkedList<Object>().listIterator();
    private int last_pos;
    private int last_line;
    private Token last_token;
    private final TokenIndex converter = new TokenIndex();
    private final int[] action = new int[2];

    private final static int SHIFT = 0;
    private final static int REDUCE = 1;
    private final static int ACCEPT = 2;
    private final static int ERROR = 3;

    public Parser(@SuppressWarnings("hiding") Lexer lexer)
    {
        this.lexer = lexer;
    }

    protected void filter() throws ParserException, LexerException, IOException
    {
        // Empty body
    }

    private void push(int numstate, ArrayList<Object> listNode, boolean hidden) throws ParserException, LexerException, IOException
    {
        this.nodeList = listNode;

        if(!hidden)
        {
            filter();
        }

        if(!this.stack.hasNext())
        {
            this.stack.add(new State(numstate, this.nodeList));
            return;
        }

        State s = (State) this.stack.next();
        s.state = numstate;
        s.nodes = this.nodeList;
    }

    private int goTo(int index)
    {
        int state = state();
        int low = 1;
        int high = gotoTable[index].length - 1;
        int value = gotoTable[index][0][1];

        while(low <= high)
        {
            // int middle = (low + high) / 2;
            int middle = (low + high) >>> 1;

            if(state < gotoTable[index][middle][0])
            {
                high = middle - 1;
            }
            else if(state > gotoTable[index][middle][0])
            {
                low = middle + 1;
            }
            else
            {
                value = gotoTable[index][middle][1];
                break;
            }
        }

        return value;
    }

    private int state()
    {
        State s = (State) this.stack.previous();
        this.stack.next();
        return s.state;
    }

    private ArrayList<Object> pop()
    {
        return ((State) this.stack.previous()).nodes;
    }

    private int index(Switchable token)
    {
        this.converter.index = -1;
        token.apply(this.converter);
        return this.converter.index;
    }

    @SuppressWarnings("unchecked")
    public Start parse() throws ParserException, LexerException, IOException
    {
        push(0, null, true);
        List<Node> ign = null;
        while(true)
        {
            while(index(this.lexer.peek()) == -1)
            {
                if(ign == null)
                {
                    ign = new LinkedList<Node>();
                }

                ign.add(this.lexer.next());
            }

            if(ign != null)
            {
                this.ignoredTokens.setIn(this.lexer.peek(), ign);
                ign = null;
            }

            this.last_pos = this.lexer.peek().getPos();
            this.last_line = this.lexer.peek().getLine();
            this.last_token = this.lexer.peek();

            int index = index(this.lexer.peek());
            this.action[0] = Parser.actionTable[state()][0][1];
            this.action[1] = Parser.actionTable[state()][0][2];

            int low = 1;
            int high = Parser.actionTable[state()].length - 1;

            while(low <= high)
            {
                int middle = (low + high) / 2;

                if(index < Parser.actionTable[state()][middle][0])
                {
                    high = middle - 1;
                }
                else if(index > Parser.actionTable[state()][middle][0])
                {
                    low = middle + 1;
                }
                else
                {
                    this.action[0] = Parser.actionTable[state()][middle][1];
                    this.action[1] = Parser.actionTable[state()][middle][2];
                    break;
                }
            }

            switch(this.action[0])
            {
                case SHIFT:
		    {
		        ArrayList<Object> list = new ArrayList<Object>();
		        list.add(this.lexer.next());
                        push(this.action[1], list, false);
                    }
		    break;
                case REDUCE:
                    {
                        int reduction = this.action[1];
                        if(reduction < 500) reduce_0(reduction);
                    }
                    break;
                case ACCEPT:
                    {
                        EOF node2 = (EOF) this.lexer.next();
                        PProgramme node1 = (PProgramme) pop().get(0);
                        Start node = new Start(node1, node2);
                        return node;
                    }
                case ERROR:
                    throw new ParserException(this.last_token,
                        "[" + this.last_line + "," + this.last_pos + "] " +
                        Parser.errorMessages[Parser.errors[this.action[1]]]);
            }
        }
    }

    private void reduce_0(int reduction) throws IOException, LexerException, ParserException
    {
        switch(reduction)
        {
            case 0: /* reduce AProg1Programme */
            {
                ArrayList<Object> list = new0();
                push(goTo(0), list, false);
            }
            break;
            case 1: /* reduce AProg2Programme */
            {
                ArrayList<Object> list = new1();
                push(goTo(0), list, false);
            }
            break;
            case 2: /* reduce AOuExp */
            {
                ArrayList<Object> list = new2();
                push(goTo(1), list, false);
            }
            break;
            case 3: /* reduce AExp1Exp */
            {
                ArrayList<Object> list = new3();
                push(goTo(1), list, false);
            }
            break;
            case 4: /* reduce AEtExp1 */
            {
                ArrayList<Object> list = new4();
                push(goTo(2), list, false);
            }
            break;
            case 5: /* reduce AExp2Exp1 */
            {
                ArrayList<Object> list = new5();
                push(goTo(2), list, false);
            }
            break;
            case 6: /* reduce AInfExp2 */
            {
                ArrayList<Object> list = new6();
                push(goTo(3), list, false);
            }
            break;
            case 7: /* reduce AEgalExp2 */
            {
                ArrayList<Object> list = new7();
                push(goTo(3), list, false);
            }
            break;
            case 8: /* reduce AExp3Exp2 */
            {
                ArrayList<Object> list = new8();
                push(goTo(3), list, false);
            }
            break;
            case 9: /* reduce APlusExp3 */
            {
                ArrayList<Object> list = new9();
                push(goTo(4), list, false);
            }
            break;
            case 10: /* reduce AMoinExp3 */
            {
                ArrayList<Object> list = new10();
                push(goTo(4), list, false);
            }
            break;
            case 11: /* reduce AExp4Exp3 */
            {
                ArrayList<Object> list = new11();
                push(goTo(4), list, false);
            }
            break;
            case 12: /* reduce AFoisExp4 */
            {
                ArrayList<Object> list = new12();
                push(goTo(5), list, false);
            }
            break;
            case 13: /* reduce ADiviseExp4 */
            {
                ArrayList<Object> list = new13();
                push(goTo(5), list, false);
            }
            break;
            case 14: /* reduce AExp5Exp4 */
            {
                ArrayList<Object> list = new14();
                push(goTo(5), list, false);
            }
            break;
            case 15: /* reduce ANonExp5 */
            {
                ArrayList<Object> list = new15();
                push(goTo(6), list, false);
            }
            break;
            case 16: /* reduce AExp6Exp5 */
            {
                ArrayList<Object> list = new16();
                push(goTo(6), list, false);
            }
            break;
            case 17: /* reduce ANombreExp6 */
            {
                ArrayList<Object> list = new17();
                push(goTo(7), list, false);
            }
            break;
            case 18: /* reduce AParentheseExp6 */
            {
                ArrayList<Object> list = new18();
                push(goTo(7), list, false);
            }
            break;
        }
    }



    @SuppressWarnings({ "unchecked", "rawtypes" })
    ArrayList<Object> new0() /* reduce AProg1Programme */
    {
        @SuppressWarnings("hiding") ArrayList<Object> nodeList = new ArrayList<Object>();

        @SuppressWarnings("unused") ArrayList<Object> nodeArrayList5 = pop();
        @SuppressWarnings("unused") ArrayList<Object> nodeArrayList4 = pop();
        @SuppressWarnings("unused") ArrayList<Object> nodeArrayList3 = pop();
        @SuppressWarnings("unused") ArrayList<Object> nodeArrayList2 = pop();
        @SuppressWarnings("unused") ArrayList<Object> nodeArrayList1 = pop();
        PProgramme pprogrammeNode1;
        {
            // Block
        TParentheseOuvrante tparentheseouvranteNode2;
        TIdentif tidentifNode3;
        TVirgule tvirguleNode4;
        TNombre tnombreNode5;
        TParentheseFermante tparenthesefermanteNode6;
        tparentheseouvranteNode2 = (TParentheseOuvrante)nodeArrayList1.get(0);
        tidentifNode3 = (TIdentif)nodeArrayList2.get(0);
        tvirguleNode4 = (TVirgule)nodeArrayList3.get(0);
        tnombreNode5 = (TNombre)nodeArrayList4.get(0);
        tparenthesefermanteNode6 = (TParentheseFermante)nodeArrayList5.get(0);

        pprogrammeNode1 = new AProg1Programme(tparentheseouvranteNode2, tidentifNode3, tvirguleNode4, tnombreNode5, tparenthesefermanteNode6);
        }
	nodeList.add(pprogrammeNode1);
        return nodeList;
    }



    @SuppressWarnings({ "unchecked", "rawtypes" })
    ArrayList<Object> new1() /* reduce AProg2Programme */
    {
        @SuppressWarnings("hiding") ArrayList<Object> nodeList = new ArrayList<Object>();

        @SuppressWarnings("unused") ArrayList<Object> nodeArrayList3 = pop();
        @SuppressWarnings("unused") ArrayList<Object> nodeArrayList2 = pop();
        @SuppressWarnings("unused") ArrayList<Object> nodeArrayList1 = pop();
        PProgramme pprogrammeNode1;
        {
            // Block
        TParentheseOuvrante tparentheseouvranteNode2;
        TNombre tnombreNode3;
        TParentheseFermante tparenthesefermanteNode4;
        tparentheseouvranteNode2 = (TParentheseOuvrante)nodeArrayList1.get(0);
        tnombreNode3 = (TNombre)nodeArrayList2.get(0);
        tparenthesefermanteNode4 = (TParentheseFermante)nodeArrayList3.get(0);

        pprogrammeNode1 = new AProg2Programme(tparentheseouvranteNode2, tnombreNode3, tparenthesefermanteNode4);
        }
	nodeList.add(pprogrammeNode1);
        return nodeList;
    }



    @SuppressWarnings({ "unchecked", "rawtypes" })
    ArrayList<Object> new2() /* reduce AOuExp */
    {
        @SuppressWarnings("hiding") ArrayList<Object> nodeList = new ArrayList<Object>();

        @SuppressWarnings("unused") ArrayList<Object> nodeArrayList3 = pop();
        @SuppressWarnings("unused") ArrayList<Object> nodeArrayList2 = pop();
        @SuppressWarnings("unused") ArrayList<Object> nodeArrayList1 = pop();
        PExp pexpNode1;
        {
            // Block
        PExp pexpNode2;
        TOu touNode3;
        PExp1 pexp1Node4;
        pexpNode2 = (PExp)nodeArrayList1.get(0);
        touNode3 = (TOu)nodeArrayList2.get(0);
        pexp1Node4 = (PExp1)nodeArrayList3.get(0);

        pexpNode1 = new AOuExp(pexpNode2, touNode3, pexp1Node4);
        }
	nodeList.add(pexpNode1);
        return nodeList;
    }



    @SuppressWarnings({ "unchecked", "rawtypes" })
    ArrayList<Object> new3() /* reduce AExp1Exp */
    {
        @SuppressWarnings("hiding") ArrayList<Object> nodeList = new ArrayList<Object>();

        @SuppressWarnings("unused") ArrayList<Object> nodeArrayList1 = pop();
        PExp pexpNode1;
        {
            // Block
        PExp1 pexp1Node2;
        pexp1Node2 = (PExp1)nodeArrayList1.get(0);

        pexpNode1 = new AExp1Exp(pexp1Node2);
        }
	nodeList.add(pexpNode1);
        return nodeList;
    }



    @SuppressWarnings({ "unchecked", "rawtypes" })
    ArrayList<Object> new4() /* reduce AEtExp1 */
    {
        @SuppressWarnings("hiding") ArrayList<Object> nodeList = new ArrayList<Object>();

        @SuppressWarnings("unused") ArrayList<Object> nodeArrayList3 = pop();
        @SuppressWarnings("unused") ArrayList<Object> nodeArrayList2 = pop();
        @SuppressWarnings("unused") ArrayList<Object> nodeArrayList1 = pop();
        PExp1 pexp1Node1;
        {
            // Block
        PExp1 pexp1Node2;
        TEt tetNode3;
        PExp2 pexp2Node4;
        pexp1Node2 = (PExp1)nodeArrayList1.get(0);
        tetNode3 = (TEt)nodeArrayList2.get(0);
        pexp2Node4 = (PExp2)nodeArrayList3.get(0);

        pexp1Node1 = new AEtExp1(pexp1Node2, tetNode3, pexp2Node4);
        }
	nodeList.add(pexp1Node1);
        return nodeList;
    }



    @SuppressWarnings({ "unchecked", "rawtypes" })
    ArrayList<Object> new5() /* reduce AExp2Exp1 */
    {
        @SuppressWarnings("hiding") ArrayList<Object> nodeList = new ArrayList<Object>();

        @SuppressWarnings("unused") ArrayList<Object> nodeArrayList1 = pop();
        PExp1 pexp1Node1;
        {
            // Block
        PExp2 pexp2Node2;
        pexp2Node2 = (PExp2)nodeArrayList1.get(0);

        pexp1Node1 = new AExp2Exp1(pexp2Node2);
        }
	nodeList.add(pexp1Node1);
        return nodeList;
    }



    @SuppressWarnings({ "unchecked", "rawtypes" })
    ArrayList<Object> new6() /* reduce AInfExp2 */
    {
        @SuppressWarnings("hiding") ArrayList<Object> nodeList = new ArrayList<Object>();

        @SuppressWarnings("unused") ArrayList<Object> nodeArrayList3 = pop();
        @SuppressWarnings("unused") ArrayList<Object> nodeArrayList2 = pop();
        @SuppressWarnings("unused") ArrayList<Object> nodeArrayList1 = pop();
        PExp2 pexp2Node1;
        {
            // Block
        PExp2 pexp2Node2;
        TInferieur tinferieurNode3;
        PExp3 pexp3Node4;
        pexp2Node2 = (PExp2)nodeArrayList1.get(0);
        tinferieurNode3 = (TInferieur)nodeArrayList2.get(0);
        pexp3Node4 = (PExp3)nodeArrayList3.get(0);

        pexp2Node1 = new AInfExp2(pexp2Node2, tinferieurNode3, pexp3Node4);
        }
	nodeList.add(pexp2Node1);
        return nodeList;
    }



    @SuppressWarnings({ "unchecked", "rawtypes" })
    ArrayList<Object> new7() /* reduce AEgalExp2 */
    {
        @SuppressWarnings("hiding") ArrayList<Object> nodeList = new ArrayList<Object>();

        @SuppressWarnings("unused") ArrayList<Object> nodeArrayList3 = pop();
        @SuppressWarnings("unused") ArrayList<Object> nodeArrayList2 = pop();
        @SuppressWarnings("unused") ArrayList<Object> nodeArrayList1 = pop();
        PExp2 pexp2Node1;
        {
            // Block
        PExp2 pexp2Node2;
        TEgal tegalNode3;
        PExp3 pexp3Node4;
        pexp2Node2 = (PExp2)nodeArrayList1.get(0);
        tegalNode3 = (TEgal)nodeArrayList2.get(0);
        pexp3Node4 = (PExp3)nodeArrayList3.get(0);

        pexp2Node1 = new AEgalExp2(pexp2Node2, tegalNode3, pexp3Node4);
        }
	nodeList.add(pexp2Node1);
        return nodeList;
    }



    @SuppressWarnings({ "unchecked", "rawtypes" })
    ArrayList<Object> new8() /* reduce AExp3Exp2 */
    {
        @SuppressWarnings("hiding") ArrayList<Object> nodeList = new ArrayList<Object>();

        @SuppressWarnings("unused") ArrayList<Object> nodeArrayList1 = pop();
        PExp2 pexp2Node1;
        {
            // Block
        PExp3 pexp3Node2;
        pexp3Node2 = (PExp3)nodeArrayList1.get(0);

        pexp2Node1 = new AExp3Exp2(pexp3Node2);
        }
	nodeList.add(pexp2Node1);
        return nodeList;
    }



    @SuppressWarnings({ "unchecked", "rawtypes" })
    ArrayList<Object> new9() /* reduce APlusExp3 */
    {
        @SuppressWarnings("hiding") ArrayList<Object> nodeList = new ArrayList<Object>();

        @SuppressWarnings("unused") ArrayList<Object> nodeArrayList3 = pop();
        @SuppressWarnings("unused") ArrayList<Object> nodeArrayList2 = pop();
        @SuppressWarnings("unused") ArrayList<Object> nodeArrayList1 = pop();
        PExp3 pexp3Node1;
        {
            // Block
        PExp3 pexp3Node2;
        TPlus tplusNode3;
        PExp4 pexp4Node4;
        pexp3Node2 = (PExp3)nodeArrayList1.get(0);
        tplusNode3 = (TPlus)nodeArrayList2.get(0);
        pexp4Node4 = (PExp4)nodeArrayList3.get(0);

        pexp3Node1 = new APlusExp3(pexp3Node2, tplusNode3, pexp4Node4);
        }
	nodeList.add(pexp3Node1);
        return nodeList;
    }



    @SuppressWarnings({ "unchecked", "rawtypes" })
    ArrayList<Object> new10() /* reduce AMoinExp3 */
    {
        @SuppressWarnings("hiding") ArrayList<Object> nodeList = new ArrayList<Object>();

        @SuppressWarnings("unused") ArrayList<Object> nodeArrayList3 = pop();
        @SuppressWarnings("unused") ArrayList<Object> nodeArrayList2 = pop();
        @SuppressWarnings("unused") ArrayList<Object> nodeArrayList1 = pop();
        PExp3 pexp3Node1;
        {
            // Block
        PExp3 pexp3Node2;
        TMoin tmoinNode3;
        PExp4 pexp4Node4;
        pexp3Node2 = (PExp3)nodeArrayList1.get(0);
        tmoinNode3 = (TMoin)nodeArrayList2.get(0);
        pexp4Node4 = (PExp4)nodeArrayList3.get(0);

        pexp3Node1 = new AMoinExp3(pexp3Node2, tmoinNode3, pexp4Node4);
        }
	nodeList.add(pexp3Node1);
        return nodeList;
    }



    @SuppressWarnings({ "unchecked", "rawtypes" })
    ArrayList<Object> new11() /* reduce AExp4Exp3 */
    {
        @SuppressWarnings("hiding") ArrayList<Object> nodeList = new ArrayList<Object>();

        @SuppressWarnings("unused") ArrayList<Object> nodeArrayList1 = pop();
        PExp3 pexp3Node1;
        {
            // Block
        PExp4 pexp4Node2;
        pexp4Node2 = (PExp4)nodeArrayList1.get(0);

        pexp3Node1 = new AExp4Exp3(pexp4Node2);
        }
	nodeList.add(pexp3Node1);
        return nodeList;
    }



    @SuppressWarnings({ "unchecked", "rawtypes" })
    ArrayList<Object> new12() /* reduce AFoisExp4 */
    {
        @SuppressWarnings("hiding") ArrayList<Object> nodeList = new ArrayList<Object>();

        @SuppressWarnings("unused") ArrayList<Object> nodeArrayList3 = pop();
        @SuppressWarnings("unused") ArrayList<Object> nodeArrayList2 = pop();
        @SuppressWarnings("unused") ArrayList<Object> nodeArrayList1 = pop();
        PExp4 pexp4Node1;
        {
            // Block
        PExp4 pexp4Node2;
        TFois tfoisNode3;
        PExp5 pexp5Node4;
        pexp4Node2 = (PExp4)nodeArrayList1.get(0);
        tfoisNode3 = (TFois)nodeArrayList2.get(0);
        pexp5Node4 = (PExp5)nodeArrayList3.get(0);

        pexp4Node1 = new AFoisExp4(pexp4Node2, tfoisNode3, pexp5Node4);
        }
	nodeList.add(pexp4Node1);
        return nodeList;
    }



    @SuppressWarnings({ "unchecked", "rawtypes" })
    ArrayList<Object> new13() /* reduce ADiviseExp4 */
    {
        @SuppressWarnings("hiding") ArrayList<Object> nodeList = new ArrayList<Object>();

        @SuppressWarnings("unused") ArrayList<Object> nodeArrayList3 = pop();
        @SuppressWarnings("unused") ArrayList<Object> nodeArrayList2 = pop();
        @SuppressWarnings("unused") ArrayList<Object> nodeArrayList1 = pop();
        PExp4 pexp4Node1;
        {
            // Block
        PExp4 pexp4Node2;
        TDivise tdiviseNode3;
        PExp5 pexp5Node4;
        pexp4Node2 = (PExp4)nodeArrayList1.get(0);
        tdiviseNode3 = (TDivise)nodeArrayList2.get(0);
        pexp5Node4 = (PExp5)nodeArrayList3.get(0);

        pexp4Node1 = new ADiviseExp4(pexp4Node2, tdiviseNode3, pexp5Node4);
        }
	nodeList.add(pexp4Node1);
        return nodeList;
    }



    @SuppressWarnings({ "unchecked", "rawtypes" })
    ArrayList<Object> new14() /* reduce AExp5Exp4 */
    {
        @SuppressWarnings("hiding") ArrayList<Object> nodeList = new ArrayList<Object>();

        @SuppressWarnings("unused") ArrayList<Object> nodeArrayList1 = pop();
        PExp4 pexp4Node1;
        {
            // Block
        PExp5 pexp5Node2;
        pexp5Node2 = (PExp5)nodeArrayList1.get(0);

        pexp4Node1 = new AExp5Exp4(pexp5Node2);
        }
	nodeList.add(pexp4Node1);
        return nodeList;
    }



    @SuppressWarnings({ "unchecked", "rawtypes" })
    ArrayList<Object> new15() /* reduce ANonExp5 */
    {
        @SuppressWarnings("hiding") ArrayList<Object> nodeList = new ArrayList<Object>();

        @SuppressWarnings("unused") ArrayList<Object> nodeArrayList2 = pop();
        @SuppressWarnings("unused") ArrayList<Object> nodeArrayList1 = pop();
        PExp5 pexp5Node1;
        {
            // Block
        TNon tnonNode2;
        PExp5 pexp5Node3;
        tnonNode2 = (TNon)nodeArrayList1.get(0);
        pexp5Node3 = (PExp5)nodeArrayList2.get(0);

        pexp5Node1 = new ANonExp5(tnonNode2, pexp5Node3);
        }
	nodeList.add(pexp5Node1);
        return nodeList;
    }



    @SuppressWarnings({ "unchecked", "rawtypes" })
    ArrayList<Object> new16() /* reduce AExp6Exp5 */
    {
        @SuppressWarnings("hiding") ArrayList<Object> nodeList = new ArrayList<Object>();

        @SuppressWarnings("unused") ArrayList<Object> nodeArrayList1 = pop();
        PExp5 pexp5Node1;
        {
            // Block
        PExp6 pexp6Node2;
        pexp6Node2 = (PExp6)nodeArrayList1.get(0);

        pexp5Node1 = new AExp6Exp5(pexp6Node2);
        }
	nodeList.add(pexp5Node1);
        return nodeList;
    }



    @SuppressWarnings({ "unchecked", "rawtypes" })
    ArrayList<Object> new17() /* reduce ANombreExp6 */
    {
        @SuppressWarnings("hiding") ArrayList<Object> nodeList = new ArrayList<Object>();

        @SuppressWarnings("unused") ArrayList<Object> nodeArrayList1 = pop();
        PExp6 pexp6Node1;
        {
            // Block
        TNombre tnombreNode2;
        tnombreNode2 = (TNombre)nodeArrayList1.get(0);

        pexp6Node1 = new ANombreExp6(tnombreNode2);
        }
	nodeList.add(pexp6Node1);
        return nodeList;
    }



    @SuppressWarnings({ "unchecked", "rawtypes" })
    ArrayList<Object> new18() /* reduce AParentheseExp6 */
    {
        @SuppressWarnings("hiding") ArrayList<Object> nodeList = new ArrayList<Object>();

        @SuppressWarnings("unused") ArrayList<Object> nodeArrayList3 = pop();
        @SuppressWarnings("unused") ArrayList<Object> nodeArrayList2 = pop();
        @SuppressWarnings("unused") ArrayList<Object> nodeArrayList1 = pop();
        PExp6 pexp6Node1;
        {
            // Block
        TParentheseOuvrante tparentheseouvranteNode2;
        PExp pexpNode3;
        TParentheseFermante tparenthesefermanteNode4;
        tparentheseouvranteNode2 = (TParentheseOuvrante)nodeArrayList1.get(0);
        pexpNode3 = (PExp)nodeArrayList2.get(0);
        tparenthesefermanteNode4 = (TParentheseFermante)nodeArrayList3.get(0);

        pexp6Node1 = new AParentheseExp6(tparentheseouvranteNode2, pexpNode3, tparenthesefermanteNode4);
        }
	nodeList.add(pexp6Node1);
        return nodeList;
    }



    private static int[][][] actionTable;
/*      {
			{{-1, ERROR, 0}, {0, SHIFT, 1}, },
			{{-1, ERROR, 1}, {13, SHIFT, 3}, {14, SHIFT, 4}, },
			{{-1, ERROR, 2}, {15, ACCEPT, -1}, },
			{{-1, ERROR, 3}, {1, SHIFT, 5}, },
			{{-1, ERROR, 4}, {2, SHIFT, 6}, },
			{{-1, REDUCE, 1}, },
			{{-1, ERROR, 6}, {13, SHIFT, 7}, },
			{{-1, ERROR, 7}, {1, SHIFT, 8}, },
			{{-1, REDUCE, 0}, },
        };*/
    private static int[][][] gotoTable;
/*      {
			{{-1, 2}, },
			{{-1, -1}, },
			{{-1, -1}, },
			{{-1, -1}, },
			{{-1, -1}, },
			{{-1, -1}, },
			{{-1, -1}, },
			{{-1, -1}, },
        };*/
    private static String[] errorMessages;
/*      {
			"expecting: '('",
			"expecting: nombre, identif",
			"expecting: EOF",
			"expecting: ')'",
			"expecting: ','",
			"expecting: nombre",
        };*/
    private static int[] errors;
/*      {
			0, 1, 2, 3, 4, 2, 5, 3, 2, 
        };*/

    static 
    {
        try
        {
            DataInputStream s = new DataInputStream(
                new BufferedInputStream(
                Parser.class.getResourceAsStream("parser.dat")));

            // read actionTable
            int length = s.readInt();
            Parser.actionTable = new int[length][][];
            for(int i = 0; i < Parser.actionTable.length; i++)
            {
                length = s.readInt();
                Parser.actionTable[i] = new int[length][3];
                for(int j = 0; j < Parser.actionTable[i].length; j++)
                {
                for(int k = 0; k < 3; k++)
                {
                    Parser.actionTable[i][j][k] = s.readInt();
                }
                }
            }

            // read gotoTable
            length = s.readInt();
            gotoTable = new int[length][][];
            for(int i = 0; i < gotoTable.length; i++)
            {
                length = s.readInt();
                gotoTable[i] = new int[length][2];
                for(int j = 0; j < gotoTable[i].length; j++)
                {
                for(int k = 0; k < 2; k++)
                {
                    gotoTable[i][j][k] = s.readInt();
                }
                }
            }

            // read errorMessages
            length = s.readInt();
            errorMessages = new String[length];
            for(int i = 0; i < errorMessages.length; i++)
            {
                length = s.readInt();
                StringBuffer buffer = new StringBuffer();

                for(int j = 0; j < length; j++)
                {
                buffer.append(s.readChar());
                }
                errorMessages[i] = buffer.toString();
            }

            // read errors
            length = s.readInt();
            errors = new int[length];
            for(int i = 0; i < errors.length; i++)
            {
                errors[i] = s.readInt();
            }

            s.close();
        }
        catch(Exception e)
        {
            throw new RuntimeException("The file \"parser.dat\" is either missing or corrupted.");
        }
    }
}
