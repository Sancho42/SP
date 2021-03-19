;Задача: дан массив из 10 слов. Инвертировать все отрицательные
;числа и найти сумму элементов полученного массива.

format ELF

section '.text' executable

public _start
_start:
        mov esi, array
        mov ecx, 10
        xor eax, eax
	xor ebx, ebx

        lp:
                lodsw
                test ax, 1000000000000000b
                jz skip
                not ax
                add ax, 1
        skip:
                add bx, ax
        loop lp


exit:
        mov eax,1
        int 0x80

section '.data' writeable

array dw 1,-2,3,-4,-5,6,-7,-8,-9,10 ; 55

