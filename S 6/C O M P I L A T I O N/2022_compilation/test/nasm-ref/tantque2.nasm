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
	sub	esp,	32	;allocation des variables locales
	mov	dword [ebp-4],	0	;Affect
l0 :	mov	eax,	1	;Affect
	cmp	dword [ebp-4],	4	;JumpIfLess 1
	jl	l2	;JumpIfLess 2
	mov	eax,	0	;Affect
l2 :	cmp	eax,	0	;JumpIfEqual 1
	je	l1	;JumpIfEqual 2
	mov	dword [ebp-20],	0	;Affect
l3 :	mov	eax,	1	;Affect
	cmp	dword [ebp-20],	4	;JumpIfLess 1
	jl	l5	;JumpIfLess 2
	mov	eax,	0	;Affect
l5 :	cmp	eax,	0	;JumpIfEqual 1
	je	l4	;JumpIfEqual 2
	mov	eax,	dword [ebp-4]	;Write 1
	call	iprintLF	;Write 2
	mov	eax,	dword [ebp-20]	;Write 1
	call	iprintLF	;Write 2
	mov	eax,	dword [ebp-20]	;
	add	eax,	1	;
	mov	dword [ebp-20],	eax	;Affect
	jmp	l3	;Jump
l4 :	mov	eax,	dword [ebp-4]	;
	add	eax,	1	;
	mov	dword [ebp-4],	eax	;Affect
	jmp	l0	;Jump
l1 :	add	esp,	32	;désallocation des variables locales
	pop	edx	;restaure edx
	pop	ecx	;restaure ecx
	pop	ebx	;restaure ebx
	pop	eax	;restaure eax
	pop	ebp	;restaure la valeur de ebp
	ret	;
