%include	'io.asm'

section	.bss
sinput :	resb	255	;reserve a 255 byte space in memory for the users input string

section	.text
global _start
_start:
	sub	esp,	4	;allocation mémoire pour la valeur de retour
	call	main	;
	pop	eax	;récupération de la valeur de retour
	mov	ebx,	0	; valeur de retour du programme
	mov	eax,	1	; code de sortie
	int 0x80	;
main :	push	ebp	;sauvegarde la valeur de ebp
	mov	ebp,	esp	;nouvelle valeur de ebp
	push	eax	;sauvegarde de eax
	push	ebx	;sauvegarde de ebx
	push	ecx	;sauvegarde de ecx
	push	edx	;sauvegarde de edx
	sub	esp,	0	;allocation des variables locales
	mov	eax,	1	;JumpIfEqual 1
	cmp	eax,	0	;on passe par un registre temporaire
	je	l3	;JumpIfEqual 2
	mov	eax,	0	;JumpIfEqual 1
	cmp	eax,	0	;on passe par un registre temporaire
	je	l3	;JumpIfEqual 2
	mov	ebx,	1	;Affect
	jmp	l2	;Jump
l3 :	mov	ebx,	0	;Affect
l2 :	mov	eax,	1	;jumpIfNotEqual 1
	cmp	eax,	0	;on passe par un registre temporaire
	jne	l1	;jumpIfNotEqual 2
	cmp	ebx,	0	;jumpIfNotEqual 1
	jne	l1	;jumpIfNotEqual 2
	mov	eax,	0	;Affect
	jmp	l0	;Jump
l1 :	mov	eax,	1	;Affect
l0 :	mov	eax,	eax	;Write 1
	call	iprintLF	;Write 2
	add	esp,	0	;désallocation des variables locales
	pop	edx	;restaure edx
	pop	ecx	;restaure ecx
	pop	ebx	;restaure ebx
	pop	eax	;restaure eax
	pop	ebp	;restaure la valeur de ebp
	ret	;
