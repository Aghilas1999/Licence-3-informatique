package nasm;

public interface NasmVisitor <T> {
    public T visit(NasmAdd inst);
    public T visit(NasmCall inst);
    public T visit(NasmDiv inst);
    public T visit(NasmJe inst);
    public T visit(NasmJle inst);
    public T visit(NasmJne inst);
    public T visit(NasmMul inst);
    public T visit(NasmOr inst);
    public T visit(NasmCmp inst);
    public T visit(NasmInst inst);
    public T visit(NasmJge inst);
    public T visit(NasmJl inst);
    public T visit(NasmNot inst);
    public T visit(NasmPop inst);
    public T visit(NasmRet inst);
    public T visit(NasmXor inst);
    public T visit(NasmAnd inst);
    public T visit(NasmJg inst);
    public T visit(NasmJmp inst);
    public T visit(NasmMov inst);
    public T visit(NasmPush inst);
    public T visit(NasmSub inst);
    public T visit(NasmEmpty inst);
    public T visit(NasmInt inst);

    public T visit(NasmAddress operand);
    public T visit(NasmConstant operand);
    public T visit(NasmLabel operand);
    public T visit(NasmRegister operand);

    public T visit(NasmResb pseudoInst);
    public T visit(NasmResw pseudoInst);
    public T visit(NasmResd pseudoInst);
    public T visit(NasmResq pseudoInst);
    public T visit(NasmRest pseudoInst);

    
}
	

