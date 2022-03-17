package nasm;

public class NasmAddress extends NasmOperand {

    public NasmOperand base;
    public NasmOperand offset;
    public char direction;

    public NasmAddress(NasmOperand base, char direction, NasmOperand offset){
	this.base = base;
	this.direction = direction;
	this.offset = offset;
    }

    public NasmAddress(NasmOperand base){
	this.base = base;
	this.direction = '+';
	this.offset = new NasmConstant(0);
    }

    public String toString(){
	//	return "address";
	if(this.offset != null)
	    // la multiplication par 4 pour passer en octets n'est effectuée que maintenant afin de
	    // traiter de la même façon les indices de tableaux et les adresses relatives
	    // c'est pas terrible !
	    //	    return "" + this.base + this.direction + "4*" + this.offset;
	    return "" + this.base + this.direction + this.offset;

	//	    return "dword [" + this.base + this.direction + "4*" + this.offset + "]";
	return "" + this.base;
	//	return "dword [" + this.base + "]";
	
    }

    public boolean isGeneralRegister(){
	return false;
    }
    
    /*


    printf("dword [");
    nasm_affiche_operande(code_nasm, operande->u.address.base);
    // la multiplication par 4 pour passer en octets n'est effectuée que maintenant afin de
    // traiter de la même façon les indices de tableaux et les adresses relatives
    // c'est pas terrible !
    if(operande->u.address.offset){
      printf(" %c 4 * ", operande->u.address.direction);
      nasm_affiche_operande(code_nasm, operande->u.address.offset);
    }
    printf("]");
    */

    
    public <T> T accept(NasmVisitor <T> visitor) {
        return visitor.visit(this);
    }


}
