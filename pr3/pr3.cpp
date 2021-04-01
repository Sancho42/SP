//Напишите программу, в которой создается квадратная матрица(реализуется через
//двумерный массив). Матрица заполняется случайными числами, после чего выполняется
//«поворот по часовой стрелке»: первый столбец становится первой строкой, второй столбец
//становится второй строкой, и так далее.
#include <iostream>

using namespace std;

#define MATRIX_SIZE 5

int main(){
	int** matrix_source = new int*[MATRIX_SIZE];
	int** matrix_destination = new int*[MATRIX_SIZE];

	for (int i = 0; i < MATRIX_SIZE; ++i){
		matrix_source[i] = new int[MATRIX_SIZE];
		matrix_destination[i] = new int[MATRIX_SIZE];
		for (int j = 0; j < MATRIX_SIZE; ++j){
			matrix_source[i][j] = rand() % 89 + 10;
			matrix_destination[i][j] = 0;
		}
	}

	for (int i = 0; i < MATRIX_SIZE; ++i){
		for (int j = 0; j < MATRIX_SIZE; ++j)
			cout << matrix_source[i][j] << ' ';
		cout << endl;
	}
	for (int i = 0; i < MATRIX_SIZE; ++i)
		cout << "---";
	cout << endl;


	asm(
		".intel_syntax noprefix\n\t"

		"	mov	rax, 0\n\t"  				// i = 0
		".outer:\n\t"
		"	cmp	rax, rcx\n\t"
		"	jge	.EXIT\n\t"
		"	mov	rbx, 0\n\t"					// j = 0
		".inner:\n\t"
		"	cmp	rbx, rcx\n\t"
		"	jge	inc_outer\n\t"
		"	mov r9, [rsi + rax * 8]\n\t"  	// matrix_source[i]
		"	mov r8d, [r9 + rbx * 4]\n\t"   	// matrix_source[i][j]
		"	mov r10, rcx\n\t"				// MATRIX_SIZE
		"	sub r10, rax\n\t"				// MATRIX_SIZE - i
		"	sub r10, 1\n\t"					// MATRIX_SIZE - i - 1
		"	mov r13, [rdi + rbx * 8]\n\t" 	// matrix_destination[j]
		"	mov [r13 + r10 * 4], r8d\n\t"  	// matrix_destination[j][MATRIX_SIZE - i - 1]
		"	add	rbx, 1\n\t"					// ++j
		"	jmp	.inner\n\t"
		"inc_outer:\n\t"
		"	add	rax, 1\n\t" 				// ++i
		"	jmp	.outer\n\t"
		".EXIT:\n\t"
		:
		: "S" (matrix_source), "D" (matrix_destination), "c" (MATRIX_SIZE)
		:
	);


	//for (int i = 0; i < MATRIX_SIZE; ++i){
	//	for (int j = 0; j < MATRIX_SIZE; ++j){
	//		matrix_destination[j][MATRIX_SIZE - i - 1] = matrix_source[i][j];
	//	}
	//}


	for (int i = 0; i < MATRIX_SIZE; ++i){
		for (int j = 0; j < MATRIX_SIZE; ++j)
			cout << matrix_destination[i][j] << ' ';
		cout << endl;
	}

	for (int i = 0; i < MATRIX_SIZE; ++i){
		delete matrix_source[i];
		delete matrix_destination[i];
	}
	delete matrix_source;
	delete matrix_destination;

	return 0;
}