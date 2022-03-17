package nasm;

public abstract class NasmOperand{
    public boolean isGeneralRegister(){
	return false;
    }
    public  abstract <T> T accept(NasmVisitor <T> visitor);
}
