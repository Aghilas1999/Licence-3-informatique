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
	sub	esp,	16	;allocation des variables locales
	mov	dword [ebp-4],	10	;Affect
l0 :	mov	ebx,	1	;Affect
	mov	eax,	0	;JumpIfLess 1
	cmp	eax,	dword [ebp-4]	;on passe par un registre temporaire
	jl	l2	;JumpIfLess 2
	mov	ebx,	0	;Affect
l2 :	cmp	ebx,	0	;JumpIfEqual 1
	je	l1	;JumpIfEqual 2
	mov	eax,	dword [ebp-4]	;Write 1
	call	iprintLF	;Write 2
	mov	eax,	dword [ebp-4]	;
	sub	eax,	1	;
	mov	dword [ebp-4],	eax	;Affect
	jmp	l0	;Jump
l1 :	mov	eax,	0	;Write 1
	call	iprintLF	;Write 2
	add	esp,	16	;désallocation des variables locales
	pop	edx	;restaure edx
	pop	ecx	;restaure ecx
	pop	ebx	;restaure ebx
	pop	eax	;restaure eax
	pop	ebp	;restaure la valeur de ebp
	ret	;
