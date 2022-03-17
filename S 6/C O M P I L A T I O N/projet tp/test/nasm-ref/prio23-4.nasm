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
	mov	ebx,	1	;Affect
	mov	ecx,	1	;JumpIfEqual 1
	cmp	ecx,	0	;on passe par un registre temporaire
	je	l0	;JumpIfEqual 2
	mov	ebx,	0	;Affect
l0 :	mov	edx,	1	;Affect
	mov	ecx,	0	;JumpIfEqual 1
	cmp	ecx,	0	;on passe par un registre temporaire
	je	l1	;JumpIfEqual 2
	mov	edx,	0	;Affect
l1 :	mov	eax,	ebx	;
	idiv	edx	;
	mov	eax,	eax	;
	mov	eax,	eax	;Write 1
	call	iprintLF	;Write 2
	add	esp,	0	;désallocation des variables locales
	pop	edx	;restaure edx
	pop	ecx	;restaure ecx
	pop	ebx	;restaure ebx
	pop	eax	;restaure eax
	pop	ebp	;restaure la valeur de ebp
	ret	;
