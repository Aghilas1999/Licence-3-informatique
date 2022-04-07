%include	'io.asm'

section	.bss
sinput :	resb	255	;reserve a 255 byte space in memory for the users input string
tab :	resd	160	;variable globale

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
	mov	eax,	2	;
	imul	eax,	4	;
	mov	dword [tab+eax],	2	;Affect
	mov	eax,	2	;
	imul	eax,	4	;
	mov	ebx,	dword [tab+eax]	;
	add	ebx,	4	;
	mov	eax,	2	;
	imul	eax,	4	;
	mov	dword [tab+eax],	ebx	;Affect
	mov	eax,	2	;
	imul	eax,	4	;
	mov	eax,	dword [tab+eax]	;Write 1
	call	iprintLF	;Write 2
	add	esp,	0	;désallocation des variables locales
	pop	edx	;restaure edx
	pop	ecx	;restaure ecx
	pop	ebx	;restaure ebx
	pop	eax	;restaure eax
	pop	ebp	;restaure la valeur de ebp
	ret	;
