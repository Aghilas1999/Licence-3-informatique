package c3a;

public enum C3aOp{
	arithAdd,
	arithSub,
	arithMult,
	arithDiv,
	funcCall,
	funcParam,
	funcValRet,
	funcBegin,
	funcEnd,
	assign,                                                    //affectation et transferts temporaires <-> variables
	alloc, // allouer de la place pour variables
	jump,
	jump_if_less,
	jump_if_less_or_equal,
	jumpIfEqual,
	jump_if_not_equal,
	jump_if_greater,
	jump_if_greater_or_equal,
	sys_read,
	sys_write; // lire et écrire (interruptions système)
}
