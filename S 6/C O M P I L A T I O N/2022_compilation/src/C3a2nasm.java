/**
 * L’objectif de ce TP est de générer du code pré-assembleur à partir du code trois adresses. Pour
chaque instruction du code trois adresses, une ou plusieurs instructions assembleur sont produites.
L’implémentation du module de génération du pré-assembleur peut être réalisé à l’aide du visiteur
C3aVisitor et du package nasm.
*
 */

import c3a.*;
import nasm.*;
import ts.Ts;
import ts.TsItemFct;

public class C3a2nasm implements C3aVisitor<NasmOperand> {
    private C3a c3a;
    private Nasm nasm;
    private Ts tableGlobale;
    private TsItemFct currentFct;
    private NasmRegister esp;
    private NasmRegister ebp;

        public C3a2nasm(C3a c3a, Ts tableGlobale) {
            this.c3a = c3a;
            nasm = new Nasm(tableGlobale);
            nasm.setTempCounter(c3a.getTempCounter());
            System.out.println("temp counter nb " + nasm.getTempCounter());
            
            this.tableGlobale = tableGlobale;
            this.currentFct = null;
            esp = nasm.newRegister();
            esp.colorRegister(Nasm.REG_EBX);
        
            ebp = nasm.newRegister();
            ebp.colorRegister(Nasm.REG_EAX);
        
            NasmLabel label = new NasmLabel("main");
            nasm.ajouteInst(new NasmCall(null, label, ""));

            NasmOperand constante1 = new NasmConstant(0);
            nasm.ajouteInst(new NasmMov(null, esp, constante1, ""));

            NasmOperand constante2 = new NasmConstant(1);
            nasm.ajouteInst(new NasmMov(null, ebp, constante2, ""));

            nasm.ajouteInst(new NasmInt(null, ""));

            
            for(C3aInst c3aInst : c3a.listeInst){
                  	   // System.out.println("<" + c3aInst.getClass().getSimpleName() + ">");
                 c3aInst.accept(this);
            }
            System.out.println("temp counter nb " + nasm.getTempCounter());
            }
        
    
    //Getter
    public Nasm getNasm() {
        return nasm;
    }

       /*--------------------------------------------------------------------------------------------------------------
      transforme une opérande trois adresses en une opérande asm selon les règles suivantes :
      
      C3aConstant -> NasmConstant
      C3aTemp     -> NasmRegister
      C3aLabel    -> NasmLabel
      C3aFunction -> NasmLabel
      C3aVar      -> NasmAddress
      --------------------------------------------------------------------------------------------------------------*/
      public NasmOperand visit(C3aConstant oper) {
           return new NasmConstant(oper.val);
      }

      public NasmOperand visit(C3aLabel oper) {
          return new NasmLabel(oper.toString());
      }
  
      public NasmOperand visit(C3aTemp oper) {
          return new NasmRegister(oper.num);
      }
      
      public NasmOperand visit(C3aVar oper) {
          
          NasmAddress adresse;
          boolean estIndicee = (oper.index != null);
          boolean estParametre = oper.item.isParam;        
          boolean estLocale = currentFct.getTable().getVar(oper.item.getIdentif()) != null;
  
          NasmRegister reg_ebp = nasm.newRegister();        
          reg_ebp.colorRegister(Nasm.REG_EBP);
  
          if (!estIndicee) {
              NasmConstant indice;
              if (estParametre) {
                  indice = new NasmConstant((currentFct.getNbArgs() * 2) - oper.item.getAdresse());
                          adresse = new NasmAddress(reg_ebp, '+', indice);
                      } else
                  if (estLocale) {
                      if (oper.item.getAdresse() > 0)
                             indice = new NasmConstant(oper.item.getAdresse());
                      else        
                          indice = new NasmConstant(oper.item.getAdresse()+1);
                      adresse = new NasmAddress(reg_ebp, '-', indice);
                  } else
                      adresse = new NasmAddress(new NasmLabel(oper.item.getIdentif()));
          } else {        
              NasmLabel tableau = new NasmLabel(oper.item.getIdentif());
              NasmOperand indice = oper.index.accept(this);
         
              adresse = new NasmAddress(tableau, '+', indice);
          }
          return adresse;
      }
    
    public NasmOperand visit(C3aFunction oper) {
      
        return new NasmLabel(oper.val.identif);
    } 

    /*--------------------------------------------------------------------------------------------------------------*/

    private NasmOperand getLabel(C3aInst inst) {
   
        return (inst.label != null) ? inst.label.accept(this) : null;
    }
    
    public NasmOperand visit(C3aInstAdd inst){

	NasmOperand label = (inst.label != null) ? inst.label.accept(this) : null;
	nasm.ajouteInst(new NasmMov(label, inst.result.accept(this), inst.op1.accept(this), ""));
	nasm.ajouteInst(new NasmAdd(null , inst.result.accept(this), inst.op2.accept(this), ""));
    
    return null;
    
    }

    public NasmOperand visit(C3aInstSub inst) {
        
        NasmOperand label = getLabel(inst);
        
        NasmOperand op1 = inst.op1.accept(this);
        NasmOperand op2 = inst.op2.accept(this);
        NasmOperand res = inst.result.accept(this);

        nasm.ajouteInst(new NasmMov(label, res, op1, ""));   
        nasm.ajouteInst(new NasmSub(null, res, op2, ""));
        return null;
    }
    
    public NasmOperand visit(C3aInstMult inst) {
       
        NasmOperand label = getLabel(inst);

        NasmOperand op1 = inst.op1.accept(this);
        NasmOperand op2 = inst.op2.accept(this);
        NasmOperand res = inst.result.accept(this);

        nasm.ajouteInst(new NasmMov(label, res, op1, ""));   
        nasm.ajouteInst(new NasmMul(null, res, op2, ""));
        return null;
    }

    public NasmOperand visit(C3aInstDiv inst) {

        NasmOperand label = getLabel(inst);

        NasmRegister reg_eax = nasm.newRegister();
        reg_eax.colorRegister(Nasm.REG_EAX);
        NasmRegister reg = nasm.newRegister();

        NasmOperand op1 = inst.op1.accept(this);
        NasmOperand op2 = inst.op2.accept(this);
        NasmOperand dest = inst.result.accept(this);

        nasm.ajouteInst(new NasmMov(label,reg_eax,op1,""));
        nasm.ajouteInst(new NasmMov(null,reg,op2,""));
        nasm.ajouteInst(new NasmDiv(null,reg,""));
        nasm.ajouteInst(new NasmMov(null,dest,reg_eax,""));
        
        return null;
    }

    public NasmOperand visit(C3aInstCall inst) {
   
        NasmOperand label = getLabel(inst);
        NasmRegister reg_esp = nasm.newRegister();
        reg_esp.colorRegister(Nasm.REG_ESP);

        NasmOperand resultat = inst.result.accept(this);
        nasm.ajouteInst(new NasmSub(label,reg_esp,new NasmConstant(4),""));
        nasm.ajouteInst(new NasmCall(null,new NasmLabel(inst.op1.val.identif),""));
        nasm.ajouteInst(new NasmPop(null, resultat,""));
   
        int nombre_arguments = inst.op1.val.getNbArgs();
        if (nombre_arguments > 0) {
   
            NasmConstant memoire_occupee = new NasmConstant(nombre_arguments * 4);
             nasm.ajouteInst(new NasmAdd(null, reg_esp,  memoire_occupee, ""));
        }
        return null;
    }
    
    public NasmOperand visit(C3aInstFBegin inst) {  

        NasmOperand label = (inst.val.identif != null) ? new NasmLabel(inst.val.identif) : null;
        NasmRegister reg_ebp = nasm.newRegister();

        reg_ebp.colorRegister(Nasm.REG_EBP);   
        nasm.ajouteInst(new NasmPush(label, reg_ebp, ""));

        NasmRegister reg_esp = nasm.newRegister();
        reg_esp.colorRegister(Nasm.REG_ESP);
        nasm.ajouteInst(new NasmMov(null, reg_ebp, reg_esp, ""));   
        currentFct = inst.val;

        int nombre_variables = currentFct.getTable().nbVar();
   
        NasmConstant constante = new NasmConstant(nombre_variables*4);   
        nasm.ajouteInst(new NasmSub(null,reg_esp,constante,""));

        return null;
    }

    
    public NasmOperand visit(C3aInst inst) {
        
        return null;
    }
    
    public NasmOperand visit(C3aInstJumpIfLess inst) {

        NasmOperand label = getLabel(inst);
        NasmOperand op1 = inst.op1.accept(this);
        NasmOperand op2 = inst.op2.accept(this);
        NasmOperand resultat = inst.result.accept(this);
        
        if (op1.isGeneralRegister())
            nasm.ajouteInst(new NasmCmp(label,op1,op2,""));
        else {
            NasmRegister registre = nasm.newRegister();
        
            nasm.ajouteInst(new NasmMov(label,registre,op1,""));
            nasm.ajouteInst(new NasmCmp(null,registre,op2,""));
        }
        nasm.ajouteInst(new NasmJl(null,resultat,""));

        return null;
    }

    public NasmOperand visit(C3aInstRead inst) {

        NasmOperand label = getLabel(inst);
        NasmOperand resultat = inst.result.accept(this);
        NasmRegister reg_eax = nasm.newRegister();
        
        reg_eax.colorRegister(Nasm.REG_EAX);

        NasmOperand sinput = new NasmLabel("sinput");
        NasmOperand realine = new NasmLabel("readline");
        NasmOperand atoi = new NasmLabel("atoi");

        nasm.ajouteInst(new NasmMov(label,reg_eax, sinput,""));
        nasm.ajouteInst(new NasmCall(null, realine,""));
        nasm.ajouteInst(new NasmCall(null, atoi,""));
        nasm.ajouteInst(new NasmMov(null, resultat, reg_eax,""));

        return null;
    }
    
    public NasmOperand visit(C3aInstAffect inst) {
        
        NasmOperand label = getLabel(inst);
        NasmOperand op1 = inst.op1.accept(this);
        NasmOperand resultat = inst.result.accept(this);

        nasm.ajouteInst(new NasmMov(label,resultat,op1,""));
        return null;
    }

    public NasmOperand visit(C3aInstFEnd inst) {
   
        NasmOperand label = getLabel(inst);
        NasmRegister reg_ebp = nasm.newRegister();
        reg_ebp.colorRegister(Nasm.REG_EBP);
        
        NasmRegister reg_esp = nasm.newRegister();
        
        reg_esp.colorRegister(Nasm.REG_ESP);
        int nbVariables = currentFct.getTable().nbVar();

        NasmConstant constante = new NasmConstant(nbVariables*4);

        nasm.ajouteInst(new NasmAdd(label,reg_esp,constante,""));   
        nasm.ajouteInst(new NasmPop(label, reg_ebp, ""));
        nasm.ajouteInst(new NasmRet(null, ""));

        return null;
    }

    public NasmOperand visit(C3aInstJumpIfEqual inst) {
        
        NasmOperand label = getLabel(inst);
        NasmOperand op1 = inst.op1.accept(this);
        NasmOperand op2 = inst.op2.accept(this);
        NasmOperand resultat = inst.result.accept(this);

        if (op1.isGeneralRegister())   
            nasm.ajouteInst(new NasmCmp(label,op1,op2,""));
        
            else {
        
                NasmRegister registre = nasm.newRegister();

            nasm.ajouteInst(new NasmMov(label,registre,op1,""));
            nasm.ajouteInst(new NasmCmp(null,registre,op2,""));
        
        }
        
        nasm.ajouteInst(new NasmJe(null,resultat,""));

        return null;
    }

    public NasmOperand visit(C3aInstJumpIfNotEqual inst) {
        
        NasmOperand label = getLabel(inst);
        NasmOperand op1 = inst.op1.accept(this);
        NasmOperand op2 = inst.op2.accept(this);
        NasmOperand resultat = inst.result.accept(this);
        
        if (op1.isGeneralRegister())
            nasm.ajouteInst(new NasmCmp(label,op1,op2,""));
        else {
        
            NasmRegister registre = nasm.newRegister();
        
            nasm.ajouteInst(new NasmMov(label,registre,op1,""));
            nasm.ajouteInst(new NasmCmp(null,registre,op2,""));
        }
        
        nasm.ajouteInst(new NasmJne(null,resultat,""));

        return null;
    }

    public NasmOperand visit(C3aInstJump inst) {
        
        NasmOperand label = getLabel(inst);
        NasmOperand resultat = inst.result.accept(this);
   
        nasm.ajouteInst(new NasmJmp(label, resultat,""));
        return null;
    }

    public NasmOperand visit(C3aInstParam inst) {

        NasmOperand label = getLabel(inst);
        NasmOperand op1 = inst.op1.accept(this);

        nasm.ajouteInst(new NasmPush(label,op1,""));
        
        return null;
    }
       
    public NasmOperand visit(C3aInstReturn inst) {

        NasmOperand label = getLabel(inst);
        NasmOperand op1 = inst.op1.accept(this);
        NasmRegister reg_ebp = nasm.newRegister();
        
        reg_ebp.colorRegister(Nasm.REG_EBP);

        NasmConstant valeur_retour = new NasmConstant(2);
        NasmAddress adresse = new NasmAddress(reg_ebp,'+', valeur_retour);
        
        nasm.ajouteInst(new NasmMov(label,adresse,op1,""));

        return null;
    }

    public NasmOperand visit(C3aInstWrite inst) {
        
        NasmOperand label = getLabel(inst);
        NasmRegister reg_eax = nasm.newRegister();
        
        reg_eax.colorRegister(Nasm.REG_EAX);

        NasmOperand op1 = inst.op1.accept(this);
        
        nasm.ajouteInst(new NasmMov(label,reg_eax,op1,""));
        nasm.ajouteInst(new NasmCall(null, new NasmLabel("iprintLF"),""));

        return null;
    }
    
    public NasmOperand visit(C3aInstStop inst) {
     
        return null;
    } 
}