#include <stdio.h>
#include <string.h>

#define NELEMS(array) (sizeof(array)/sizeof(array[0]))

typedef struct Nameval Nameval;
struct Nameval
{
	char *name;
	int value;
};

Nameval htmlchars[] = 
{
	"AElig", 0x00c6,
	"Aacute", 0x00c1,
	"Acirc", 0x00c2,
	"frac", 0x0054,
	"zete", 0x03b6,
};


int lookup(char *word, char *array[])
{
	int i;
	for ( i = 0; array[i] != NULL; i++)
		if (strcmp(word, array[i]) == 0)
			return i;
	return -1;
}

int lookup2(char *name, Nameval tab[], int ntab)
{
	int low, high, mid, cmp;
	low = 0;
	high = ntab - 1;
	while (low <= high)
	{
		mid = (low + high) / 2;
		cmp = strcmp(name, tab[mid].name);
		if (cmp < 0)
			high = mid - 1;
		else if (cmp > 0)
			low = mid + 1;
		else
			return mid;
	}
	return -1;
}

int lookup3(char *name, Nameval tab[], int ntab)
{
    return rank(name, tab, 0, ntab-1);
}

int rank(char *name, Nameval tab[], int lo, int hi)
{
    if(lo > hi) return -1;
    int mid = lo + (hi - lo) / 2;
    int cmp = strcmp(name, tab[mid].name);
    if(cmp < 0)
        return rank(name, tab, lo, mid - 1);
    else if(cmp > 0)
        return rank(name, tab, mid + 1, hi);
    else
        return mid;
}

int main()
{
	char *flab[] = {"at", "just", "quit", "real", NULL};
	char word[] = "just";
	printf("%d\n", lookup(word, flab));

	printf("%d\n", lookup2("Aacute", htmlchars, NELEMS(htmlchars)));
	printf("%d\n", lookup2("zete", htmlchars, NELEMS(htmlchars)));
	printf("%d\n", lookup2("rete", htmlchars, NELEMS(htmlchars)));

    printf("%d\n", lookup3("Aacute", htmlchars, NELEMS(htmlchars)));
    printf("%d\n", lookup3("zete", htmlchars, NELEMS(htmlchars)));
    printf("%d\n", lookup3("rete", htmlchars, NELEMS(htmlchars)));
}

