import ts.Ts;
import ts.TsParser;
import nasm.NasmParser;
import nasm.NasmEval;
import nasm.Nasm;
import java.io.IOException;


public class NasmVM {
    private int verboseLevel;
    private int stackSize;
    private Nasm code;
    private String nasmFileName;

    public NasmVM(String nasmFileName, int stackSize, int verboseLevel){
	this.nasmFileName = nasmFileName;
	this.stackSize = stackSize;
	this.verboseLevel = verboseLevel;
    }
    
    public void run() throws IOException {
        var nasmParser = new NasmParser();
        code = nasmParser.parse(nasmFileName);

	if(verboseLevel > 0)
       		code.afficheNasm(null);
	
        NasmEval eval = new NasmEval(code, stackSize, verboseLevel);
	eval.displayOutput();
    }

    public static void main(String[] args){
	int verboseLevel = 0;
	int stackSize = 10000;
	String nasmFileName = null;

        try {
	    for (int i = 0; i < args.length; i++) {
		if(args[i].equals("-v"))
		    verboseLevel = Integer.parseInt(args[++i]);
		else if(args[i].equals("-s"))
		    stackSize = Integer.parseInt(args[++i]);
		else if(args[i].equals("-nasm"))
		    nasmFileName = args[++i];
	    }
	    if(nasmFileName == null){
		System.out.println("java NasmVM -nasm nasmFile -s stackSize -v verboseLevel");
		System.exit(1);
	    }
	    var vm = new NasmVM(nasmFileName, stackSize, verboseLevel);
	    vm.run();
	}catch(IOException e){
            e.printStackTrace();
        }
    }
}

/*
        else{
            var pathTab = preNasmFilePath.split("/");
            var fileNamePreNasm = pathTab[pathTab.length - 1];
            var fileName =  outputPath + fileNamePreNasm.substring(0, fileNamePreNasm.length()-3)+ ".out";
            vm.displayOutput(fileName);
        }
    }
*/
