package ts;

import ts.Ts;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;

public class TsParser {
    private enum Scope{LOCAL, GLOBAL}

    private class FuncInfo{
        public String identif = "";
        public int nbArgs = 0;
        public Ts ts = new Ts();
    }

    private Ts symbolsTab;
    private FuncInfo currentFuncInfo;
    private Scope currentScope;

    public TsParser(){

    }

    public Ts parse(String filePath){
        symbolsTab = new Ts();
        currentFuncInfo = null;
        try {
            Files.lines(Paths.get(filePath)).forEachOrdered(this::processLine);;
        } catch(IOException e){
            e.printStackTrace();
        }
        if(currentScope == Scope.LOCAL)
            symbolsTab.addFct(currentFuncInfo.identif, currentFuncInfo.nbArgs, currentFuncInfo.ts, null);
        return symbolsTab;
    }

    private void processLine(String line){
        var lineArgs = line.split("([ ]|[\t])+");
        if(line.contains("LOCAL")) {
            if(currentScope != Scope.GLOBAL)
                symbolsTab.addFct(currentFuncInfo.identif, currentFuncInfo.nbArgs, currentFuncInfo.ts, null);
            currentScope = Scope.LOCAL;
            currentFuncInfo = new FuncInfo();
            currentFuncInfo.identif = lineArgs[3];

        }
        else if(line.contains("GLOBALE"))
            currentScope = Scope.GLOBAL;
        else if(currentScope == Scope.GLOBAL && line.contains("VAR"))
            symbolsTab.addVar(lineArgs[0], Integer.parseInt(lineArgs[2].trim()));
        else if(currentScope == Scope.LOCAL){
            if(line.contains("PARAM")){
                currentFuncInfo.nbArgs++;
                currentFuncInfo.ts.addParam(lineArgs[0]);
            }
            else if(line.contains("VAR"))
                currentFuncInfo.ts.addVar(lineArgs[0], Integer.parseInt(lineArgs[2]));
        }
    }
    /*
    public static void main(String args[]){
        var parser = new SymbolsTableParser();
        var r = parser.parse("function.ts");
        System.out.println("---Table Globale---");
        r.affiche(System.out);
        System.out.println("---Tables Locales---");
        r.afficheTablesLocales(System.out);
	}*/
}
