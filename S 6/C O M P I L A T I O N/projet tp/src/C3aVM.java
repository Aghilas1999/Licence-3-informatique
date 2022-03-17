import java.io.IOException;
import c3a.C3a;
import c3a.C3aParser;
import c3a.C3aEval;
import ts.Ts;
import ts.TsParser;

public class C3aVM {
    private int verboseLevel = 0;
    private TsParser stParser;
    private C3aParser c3aParser;
    private String symbolsTableFileName;
    private String C3AFileName;
    private int stackSize;
    
    public C3aVM(String symbolsTableFileName, String C3AFileName, int stackSize, int verboseLevel){
	this.stackSize = stackSize;
	this.verboseLevel = verboseLevel;
	this.symbolsTableFileName = symbolsTableFileName;
	this.C3AFileName = C3AFileName;
    }
    
    
    public void run() throws IOException {
	stParser = new TsParser();
        Ts symbolTable = stParser.parse(symbolsTableFileName);
	c3aParser = new C3aParser();
        C3a code = c3aParser.parse(C3AFileName, symbolTable);
	if(verboseLevel > 0)
	    code.affiche(null);
        C3aEval eval = new C3aEval(code, symbolTable, stackSize, verboseLevel);
        eval.affiche(null);
	//        eval.affiche(C3AFileName.substring(0, C3AFileName.length() - 4));

    }

    public static void main(String[] args){
	int verboseLevel = 0;
	String symbolsTableFileName = null;
	String C3AFileName = null;
	int stackSize = 10000;

        try {
	    for (int i = 0; i < args.length; i++) {
		if(args[i].equals("-v"))
		    verboseLevel = Integer.parseInt(args[++i]);
		else if(args[i].equals("-s"))
		    stackSize = Integer.parseInt(args[++i]);
		else if(args[i].equals("-c3a"))
		    C3AFileName = args[++i];
		else if(args[i].equals("-ts"))
		    symbolsTableFileName = args[++i];
	    }
	    if(C3AFileName == null || symbolsTableFileName == null){
		System.out.println("java C3aVM -c3a C3AFile -ts TSFile -s stackSize -v verboseLevel");
		System.exit(1);
	    }
	    C3aVM vm = new C3aVM(symbolsTableFileName, C3AFileName, stackSize, verboseLevel);
	    vm.run();
	}catch(IOException e){
            e.printStackTrace();
        }
    }
}
