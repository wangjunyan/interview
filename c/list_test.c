#include <stdio.h>
#include <string.h>

typedef struct Nameval Nameval;
struct Nameval
{
	char *name;
	int value;
	Nameval *next;
};

Nameval* newitem(char *name, int value)
{
	Nameval *newp;
	newp = (Nameval *)malloc(sizeof(Nameval));
	newp->name = name;
	newp->value = value;
	newp->next = NULL;
	return newp;
}

Nameval* addfront(Nameval *listp, Nameval *newp)
{
	newp->next = listp;
	return newp;
}

Nameval* addend(Nameval *listp, Nameval *newp)
{
	Nameval *p;
	if (listp == NULL)
		return newp;
	for (p = listp; p->next != NULL; p = p->next)
		;
	p->next = newp;
	return listp;
}

Nameval* lookup(Nameval *listp, char *name)
{
	for (; listp != NULL; listp = listp->next)
	{
		if (strcmp(name, listp->name) == 0)
			return listp;
	}
	return NULL;
}

void freeall(Nameval *listp)
{
	Nameval *next;
	for(; listp != NULL; listp = next)
	{
		next = listp->next;
		free(listp);
	}
}

Nameval* delitem(Nameval *listp, char *name)
{
	Nameval *p, *prev;
	prev = NULL;
	for (p = listp; p != NULL; p = p->next)
	{
		if (strcmp(name, p->name) == 0)
		{
			if (prev == NULL)
				listp = p->next;
			else
				prev->next = p->next;
			free(p);
			return listp;
		}
		prev = p;
	}
	return NULL;
}

void printnv(Nameval *listp)
{
	for (; listp != NULL; listp = listp->next)
	{
		printf("%s, %d\n", listp->name, listp->value);
	}
}

Nameval* reverse(Nameval* head)
{
	Nameval *p1, *p2, *p3;
	if (head == NULL || head->next == NULL)
		return head;
	p1 = head;
	p2 = p1->next;
	while(p2)
	{
		p3 = p2->next;
		p2->next = p1;
		p1 = p2;
		p2 = p3;
	}
	head->next = NULL;
	head = p1;
	return head;
}

Nameval* reverse2(Nameval *head)
{
	Nameval *p1, *p2, *p3;
	int n;
	if (head == NULL || head->next == NULL || head->next->next == NULL)
		return head;
	p1 = head;
	p2 = head->next->next;
	n = 1;
	while (p2)
	{
		p3 = p2->next;
		if (n % 2 == 1)
		{
			p2->next = p1;
			p1 = p2;
			p2 = p3;
		}
		else
		{
			p2->next = p1->next;
			p1->next = p2;
			p2 = p3;
		}
		n++;
	}
	head->next->next = NULL;
	head = p1;
	return head;
}

int main(void)
{
	Nameval *listp = NULL;
	listp = newitem("wjy", 10);
	listp = addfront(listp, newitem("lq", 5));
	listp = addend(listp, newitem("yy", 18));
	listp = addend(listp, newitem("lxy", 19));
	listp = addend(listp, newitem("wer", 48));
	printnv(listp);
	listp = reverse(listp);
	printnv(listp);
	return 0;
}
