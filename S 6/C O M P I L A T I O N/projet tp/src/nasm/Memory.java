package nasm;

/* Big Endian */

public class Memory{
    /* stack segment */
    private int ss;

    /* data segment */
    private int ds;

    protected int ebp;
    protected int esp;
    private int memSize;
    private int dataSize;
    private int stackSize;
    private byte[] mem;

    public Memory(int dataSize, int stackSize){
	this.dataSize = dataSize;
	this.stackSize = stackSize;
	this.memSize = dataSize + stackSize;
	ss = memSize - 1;
	esp = ss;
	mem = new byte[memSize];
	ds = 0;
    }

    public void printStack(){
	for(int adr = ss - 3; adr > esp; adr = adr - 4){
	    //	    System.out.print("[" + adr + "] " + readInt(adr) + "\t");
	    System.out.print(readInt(adr) + "\t");
	}
	System.out.println();
    }
    
    public int readInt(int address){
	//	System.out.println("read memory at address " + address);
	if((address < 0) || (address + 3 > ss))
            throw new RuntimeException("segmentation fault");
	return bytesToInt(mem[address], mem[address + 1], mem[address + 2], mem[address + 3]);
    }

    public void writeInt(int address, int value){
	if((address < 0) || (address + 3 > ss))
            throw new RuntimeException("segmentation fault");

	byte[] fourBytes = intToBytes(value);
	mem[address] = fourBytes[0];
	mem[address + 1] = fourBytes[1];
	mem[address + 2] = fourBytes[2];
	mem[address + 3] = fourBytes[3];
    }
    
    public void pushInt(int value){
	if(esp - 3 < dataSize)
            throw new RuntimeException("stack overflow");

	byte[] fourBytes = intToBytes(value);
	esp--;
	mem[esp] = fourBytes[3];
	esp--;
	mem[esp] = fourBytes[2];
	esp--;
	mem[esp] = fourBytes[1];
	esp--;
	mem[esp] = fourBytes[0];
	/*	mem[esp--] = fourBytes[3];
	mem[esp--] = fourBytes[2];
	mem[esp--] = fourBytes[1];
	mem[esp--] = fourBytes[0];
*/

    }

    public int popInt(){
	if(esp + 3 >= ss)
            throw new RuntimeException("stack underflow");
	byte byte0 = mem[esp];
	esp++;
	byte byte1 = mem[esp];
	esp++;
	byte byte2 = mem[esp];
	esp++;
	byte byte3 = mem[esp];
	esp++;
	
	/*	byte byte0 = mem[++esp];
	byte byte1 = mem[++esp];
	byte byte2 = mem[++esp];
	byte byte3 = mem[++esp];*/
	
	return bytesToInt(byte0, byte1, byte2, byte3);
    }

    public int bytesToInt(byte byte0, byte byte1, byte byte2, byte byte3){
        return  ((byte0 & 0xFF) << 24) |
                ((byte1 & 0xFF) << 16) |
                ((byte2 & 0xFF) << 8) |
                ((byte3 & 0xFF) << 0);
    }
    
    public byte[] intToBytes2( int data ) {    
    byte[] result = new byte[4];
    result[0] = (byte) ((data & 0xFF000000) >> 24);
    result[1] = (byte) ((data & 0x00FF0000) >> 16);
    result[2] = (byte) ((data & 0x0000FF00) >> 8);
    result[3] = (byte) ((data & 0x000000FF) >> 0);
    return result;        
}
    public byte[] intToBytes(int i)
    {
	byte[] result = new byte[4];
	
	result[0] = (byte) (i >> 24);
	result[1] = (byte) (i >> 16);
	result[2] = (byte) (i >> 8);
	result[3] = (byte) (i /*>> 0*/);
	
	return result;
    }

    /*    public static void main(String[] args){
	Memory mem = new Memory(100, 100);
	mem.pushInt(3467);
	int v = mem.popInt();
	System.out.println("val =" + v);
	v = mem.popInt();

	mem.writeInt(96, 234);
	int val = mem.readInt(96);
	System.out.println("val =" + val);
	
	}*/
    
}
