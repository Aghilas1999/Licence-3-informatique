#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <time.h>

void quick_sort (int *a, int n) {
	if (n < 2)
		return;
	int p = a[n / 2];
	int *l = a;
	int *r = a + n - 1;
	int t;
	while (l <= r) {
		if (*l < p) {
			l++;
		}
		else if (*r > p) {
			r--;
		}
		else {
			t = *l;
			*l = *r;
			*r = t;
			l++;
			r--;
		}
	}
	quick_sort(a, r - a + 1);
	quick_sort(l, a + n - l);
}

int main (int argc, char *argv[]){
	int i;
	int MAX = atoi(argv[1]);
	int * tableau ;
	tableau = malloc(MAX * sizeof(int));
	
	for (i=0;i<MAX;i++){
		tableau[i]=rand()*i;
	}
	
	clock_t temps_initial, temps_final;
	double temps_cpu;
	temps_initial = clock();
	quick_sort(tableau, MAX);
	temps_final = clock ();
	temps_cpu = ((double) temps_final-temps_initial) / CLOCKS_PER_SEC;
	printf("%f", temps_cpu);
	free(tableau);
	return 0;
}
