package c3a;

public class C3aLabel extends C3aOperand{
    public int number; // numéro de l'étiquette
    private int line;   // ligne de l'opération correspondant à l'étiquette
    //    public String name;
    
    //    public C3aLabel(int number, int line, String name){
    public C3aLabel(int number){
	this.number = number;
	this.line = 0;
	//	this.name = name;
    }

    public int getNumber(){return this.number;}
    public int getLine(){return this.line;}

    public String toString(){
	return "l" + this.number;
    }

    public void affect(C3aLabel other) {
      this.number = other.number;
      this.line = other.line;
    }
    
    public <T> T accept(C3aVisitor <T> visitor) {
        return visitor.visit(this);
    }
}
