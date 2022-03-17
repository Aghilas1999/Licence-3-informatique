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
initialiser :	push	ebp	;sauvegarde la valeur de ebp
	mov	ebp,	esp	;nouvelle valeur de ebp
	push	eax	;sauvegarde de eax
	push	ebx	;sauvegarde de ebx
	push	ecx	;sauvegarde de ecx
	push	edx	;sauvegarde de edx
	sub	esp,	0	;allocation des variables locales
	mov	eax,	0	;
	imul	eax,	4	;
	mov	dword [tab+eax],	8	;Affect
	mov	eax,	1	;
	imul	eax,	4	;
	mov	dword [tab+eax],	6	;Affect
	mov	eax,	2	;
	imul	eax,	4	;
	mov	dword [tab+eax],	9	;Affect
	mov	eax,	3	;
	imul	eax,	4	;
	mov	dword [tab+eax],	9	;Affect
	mov	eax,	4	;
	imul	eax,	4	;
	mov	dword [tab+eax],	4	;Affect
	mov	eax,	5	;
	imul	eax,	4	;
	mov	dword [tab+eax],	2	;Affect
	mov	eax,	6	;
	imul	eax,	4	;
	mov	dword [tab+eax],	3	;Affect
	mov	eax,	7	;
	imul	eax,	4	;
	mov	dword [tab+eax],	1	;Affect
	mov	eax,	8	;
	imul	eax,	4	;
	mov	dword [tab+eax],	4	;Affect
	mov	eax,	9	;
	imul	eax,	4	;
	mov	dword [tab+eax],	5	;Affect
	add	esp,	0	;désallocation des variables locales
	pop	edx	;restaure edx
	pop	ecx	;restaure ecx
	pop	ebx	;restaure ebx
	pop	eax	;restaure eax
	pop	ebp	;restaure la valeur de ebp
	ret	;
afficher :	push	ebp	;sauvegarde la valeur de ebp
	mov	ebp,	esp	;nouvelle valeur de ebp
	push	eax	;sauvegarde de eax
	push	ebx	;sauvegarde de ebx
	push	ecx	;sauvegarde de ecx
	push	edx	;sauvegarde de edx
	sub	esp,	16	;allocation des variables locales
	mov	dword [ebp-4],	0	;Affect
l0 :	mov	ebx,	1	;Affect
	mov	eax,	dword [ebp-4]	;JumpIfLess 1
	cmp	eax,	dword [ebp+12]	;on passe par un registre temporaire
	jl	l2	;JumpIfLess 2
	mov	ebx,	0	;Affect
l2 :	cmp	ebx,	0	;JumpIfEqual 1
	je	l1	;JumpIfEqual 2
	mov	eax,	dword [ebp-4]	;
	imul	eax,	4	;
	mov	eax,	dword [tab+eax]	;Write 1
	call	iprintLF	;Write 2
	mov	eax,	dword [ebp-4]	;
	add	eax,	1	;
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
echanger :	push	ebp	;sauvegarde la valeur de ebp
	mov	ebp,	esp	;nouvelle valeur de ebp
	push	eax	;sauvegarde de eax
	push	ebx	;sauvegarde de ebx
	push	ecx	;sauvegarde de ecx
	push	edx	;sauvegarde de edx
	sub	esp,	16	;allocation des variables locales
	mov	eax,	dword [ebp+12]	;
	imul	eax,	4	;
	mov	eax,	dword [tab+eax]	;Affect
	mov	dword [ebp-4],	eax	;on passe par un registre temporaire
	mov	eax,	dword [ebp+16]	;
	imul	eax,	4	;
	mov	ebx,	dword [ebp+12]	;
	imul	ebx,	4	;
	mov	eax,	dword [tab+eax]	;Affect
	mov	dword [tab+ebx],	eax	;on passe par un registre temporaire
	mov	ebx,	dword [ebp+16]	;
	imul	ebx,	4	;
	mov	eax,	dword [ebp-4]	;Affect
	mov	dword [tab+ebx],	eax	;on passe par un registre temporaire
	add	esp,	16	;désallocation des variables locales
	pop	edx	;restaure edx
	pop	ecx	;restaure ecx
	pop	ebx	;restaure ebx
	pop	eax	;restaure eax
	pop	ebp	;restaure la valeur de ebp
	ret	;
trier :	push	ebp	;sauvegarde la valeur de ebp
	mov	ebp,	esp	;nouvelle valeur de ebp
	push	eax	;sauvegarde de eax
	push	ebx	;sauvegarde de ebx
	push	ecx	;sauvegarde de ecx
	push	edx	;sauvegarde de edx
	sub	esp,	48	;allocation des variables locales
	mov	eax,	dword [ebp+12]	;Affect
	mov	dword [ebp-36],	eax	;on passe par un registre temporaire
	mov	dword [ebp-4],	1	;Affect
l3 :	mov	eax,	1	;Affect
	cmp	dword [ebp-4],	1	;JumpIfEqual 1
	je	l5	;JumpIfEqual 2
	mov	eax,	0	;Affect
l5 :	cmp	eax,	0	;JumpIfEqual 1
	je	l4	;JumpIfEqual 2
	mov	dword [ebp-4],	0	;Affect
	mov	dword [ebp-20],	0	;Affect
l6 :	mov	eax,	dword [ebp-36]	;
	sub	eax,	1	;
	mov	ebx,	1	;Affect
	cmp	dword [ebp-20],	eax	;JumpIfLess 1
	jl	l8	;JumpIfLess 2
	mov	ebx,	0	;Affect
l8 :	cmp	ebx,	0	;JumpIfEqual 1
	je	l7	;JumpIfEqual 2
	mov	ebx,	dword [ebp-20]	;
	add	ebx,	1	;
	mov	eax,	1	;Affect
	mov	ebx,	ebx	;
	imul	ebx,	4	;
	mov	ecx,	dword [ebp-20]	;
	imul	ecx,	4	;
	mov	ebx,	dword [tab+ebx]	;JumpIfLess 1
	cmp	ebx,	dword [tab+ecx]	;on passe par un registre temporaire
	jl	l11	;JumpIfLess 2
	mov	eax,	0	;Affect
l11 :	cmp	eax,	0	;JumpIfEqual 1
	je	l10	;JumpIfEqual 2
	push	dword [ebp-20]	;Param
	mov	eax,	dword [ebp-20]	;
	add	eax,	1	;
	push	eax	;Param
	sub	esp,	4	;allocation mémoire pour la valeur de retour
	call	echanger	;
	pop	eax	;récupération de la valeur de retour
	add	esp,	8	;désallocation des arguments
	mov	dword [ebp-4],	1	;Affect
l10 :	mov	eax,	dword [ebp-20]	;
	add	eax,	1	;
	mov	dword [ebp-20],	eax	;Affect
	jmp	l6	;Jump
l7 :	mov	eax,	dword [ebp-36]	;
	sub	eax,	1	;
	mov	dword [ebp-36],	eax	;Affect
	jmp	l3	;Jump
l4 :	add	esp,	48	;désallocation des variables locales
	pop	edx	;restaure edx
	pop	ecx	;restaure ecx
	pop	ebx	;restaure ebx
	pop	eax	;restaure eax
	pop	ebp	;restaure la valeur de ebp
	ret	;
main :	push	ebp	;sauvegarde la valeur de ebp
	mov	ebp,	esp	;nouvelle valeur de ebp
	push	eax	;sauvegarde de eax
	push	ebx	;sauvegarde de ebx
	push	ecx	;sauvegarde de ecx
	push	edx	;sauvegarde de edx
	sub	esp,	0	;allocation des variables locales
	sub	esp,	4	;allocation mémoire pour la valeur de retour
	call	initialiser	;
	pop	eax	;récupération de la valeur de retour
	push	10	;Param
	sub	esp,	4	;allocation mémoire pour la valeur de retour
	call	afficher	;
	pop	eax	;récupération de la valeur de retour
	add	esp,	4	;désallocation des arguments
	push	10	;Param
	sub	esp,	4	;allocation mémoire pour la valeur de retour
	call	trier	;
	pop	eax	;récupération de la valeur de retour
	add	esp,	4	;désallocation des arguments
	push	10	;Param
	sub	esp,	4	;allocation mémoire pour la valeur de retour
	call	afficher	;
	pop	eax	;récupération de la valeur de retour
	add	esp,	4	;désallocation des arguments
	add	esp,	0	;désallocation des variables locales
	pop	edx	;restaure edx
	pop	ecx	;restaure ecx
	pop	ebx	;restaure ebx
	pop	eax	;restaure eax
	pop	ebp	;restaure la valeur de ebp
	ret	;
