#include<stdio.h>

#define NHASH 11

enum {MULTIPLIER = 31};
unsigned int hash(char *str)
{
	unsigned int h;
	unsigned char *p;
	h = 0;
	for (p = (unsigned char*)str; *p != '\0'; p++)
		h = MULTIPLIER * h + *p;
	return h % NHASH;
}


typedef struct Nameval Nameval;

struct Nameval
{   
	char *name;
	int value;
	Nameval *next;
};

Nameval* symtab[NHASH];

Nameval* lookup(char *name, int create, int value)
{
	int h;
	Nameval *sym;
	h = hash(name);
	for (sym = symtab[h]; sym != NULL; sym = sym->next)
		if (strcmp(name, sym->name) == 0)
			return sym;
	if (create)
	{
		sym = (Nameval *)malloc(sizeof(Nameval));
		sym->name = name;
		sym->value = value;
		sym->next = symtab[h];
		symtab[h] = sym;
	}
	return sym;
}

int main()
{
	lookup("aaa", 1, 1);
	lookup("bbb", 1, 2);
	lookup("ccc", 1, 3);
	lookup("ddd", 1, 4);
	printf("aaa -> %d\n", lookup("aaa", 0, 0)->value);
	printf("bbb -> %d\n", lookup("bbb", 0, 0)->value);
	printf("ddd -> %d\n", lookup("ddd", 0, 0)->value);
	return 0;
}

