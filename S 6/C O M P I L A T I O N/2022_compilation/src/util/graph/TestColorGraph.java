package util.graph;
import util.graph.*;
import util.intset.*;
import java.util.*;
import java.io.*;

public class TestColorGraph
{
    public static void main(String[] args)
    {
	Graph g = new Graph();
	/*
	Node n0 = g.newNode();
	Node n1 = g.newNode();
	Node n2 = g.newNode();
	Node n3 = g.newNode();
	Node n4 = g.newNode();
	
		g.addNOEdge(n0, n1);
	g.addNOEdge(n0, n2);
	g.addNOEdge(n0, n3);
       	g.addNOEdge(n0, n4);

	g.addNOEdge(n1, n2);
       	g.addNOEdge(n1, n3);
	g.addNOEdge(n1, n4);

	g.addNOEdge(n2, n3);
	g.addNOEdge(n2, n4);

	g.addNOEdge(n3, n4);
	*/


	Node n1 = g.newNode();
	Node n2 = g.newNode();
	Node n3 = g.newNode();
	Node n4 = g.newNode();
	Node n5 = g.newNode();
	Node n6 = g.newNode();
	Node n7 = g.newNode();
	Node n8 = g.newNode();
	Node n9 = g.newNode();
	Node n10 = g.newNode();

	g.addNOEdge(n1, n2);
	g.addNOEdge(n1, n4);
	g.addNOEdge(n1, n9);

	g.addNOEdge(n2, n10);

	g.addNOEdge(n3, n8);
	g.addNOEdge(n3, n9);
	g.addNOEdge(n3, n10);

	g.addNOEdge(n4, n5);
	g.addNOEdge(n4, n8);
	g.addNOEdge(n4, n10);

	g.addNOEdge(n5, n8);
	g.addNOEdge(n5, n10);

	g.addNOEdge(n6, n7);
	g.addNOEdge(n6, n8);
	g.addNOEdge(n6, n9);

	g.addNOEdge(n7, n8);

	g.addNOEdge(n8, n9);

	g.show(System.out);
	int[] colors = new int[]{ -3,-3,-3,0,-3,2,1,-3,-3,-3};
	ColorGraph cg = new ColorGraph(g, 3, colors);
	cg.color();
	cg.affiche();
    }
}
