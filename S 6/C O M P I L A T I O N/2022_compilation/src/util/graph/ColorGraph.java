package util.graph;

import util.graph.*;
import util.intset.*;
import java.util.*;
import java.io.*;

public class ColorGraph {
    public  Graph          graph;
    public  int            vertexNb;
    private Stack<Integer> stack;
    public  IntSet         removed;
    public  IntSet         spill;
    public  int[]          color;
    public  int            colorNb;
    public  Node[]         int2Node;
    static  int            NOCOLOR = -1;

    public ColorGraph(Graph graph, int colorNb, int[] preColoredVertices){
	this.graph   = graph;
	this.colorNb = colorNb;
	stack        = new Stack<Integer>(); 
	vertexNb     = graph.nodeCount();
	color        = new int[vertexNb];
	removed      = new IntSet(vertexNb);
	spill        = new IntSet(vertexNb);
	int2Node     = graph.nodeArray();
	for(int v=0; v < vertexNb; v++){
	    int preColor = preColoredVertices[v];
	    if(preColor >= 0 && preColor < colorNb)
		color[v] = preColoredVertices[v];
	    else
		color[v] = NOCOLOR;
	}
    }

    /*-------------------------------------------------------------------------------------------------------------*/
    /* associe une couleur à tous les sommets se trouvant dans la pile */
    /*-------------------------------------------------------------------------------------------------------------*/
    
    public void select()
    {
    }
    
    /*-------------------------------------------------------------------------------------------------------------*/
    /* récupère les couleurs des voisins de t */
    /*-------------------------------------------------------------------------------------------------------------*/
    
    public IntSet neighborsColor(int t)
    {
	return null;
    }
    
    /*-------------------------------------------------------------------------------------------------------------*/
    /* recherche une couleur absente de colorSet */
    /*-------------------------------------------------------------------------------------------------------------*/
    
    public int chooseAvailableColor(IntSet colorSet)
    {
	return 0;
    }
    
    /*-------------------------------------------------------------------------------------------------------------*/
    /* calcule le nombre de voisins du sommet t */
    /*-------------------------------------------------------------------------------------------------------------*/
    
    public int neighborsNb(int t)
    {
	return 0;
    }

    /*-------------------------------------------------------------------------------------------------------------*/
    /* simplifie le graphe d'interférence g                                                                        */
    /* la simplification consiste à enlever du graphe les temporaires qui ont moins de k voisins                   */
    /* et à les mettre dans une pile                                                                               */
    /* à la fin du processus, le graphe peut ne pas être vide, il s'agit des temporaires qui ont au moins k voisin */
    /*-------------------------------------------------------------------------------------------------------------*/

    public int simplify()
    {
	return 0;
    }
    
    /*-------------------------------------------------------------------------------------------------------------*/
    /*-------------------------------------------------------------------------------------------------------------*/
    
    public void spill()
    {
    }


    /*-------------------------------------------------------------------------------------------------------------*/
    /*-------------------------------------------------------------------------------------------------------------*/

    public void color()
    {
	this.simplify();
	this.spill();
	this.select();
    }

    public void affiche()
    {
	System.out.println("vertex\tcolor");
	for(int i = 0; i < vertexNb; i++){
	    System.out.println(i + "\t" + color[i]);
	}
    }
    
    

}
