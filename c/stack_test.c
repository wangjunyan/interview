#include<stdio.h>

#define EmptyTOS (-1)
#define MinStackSize (5)

struct StackRecord
{
	int capacity;
	int topofstack;
	int *array;
};

typedef struct StackRecord *Stack;

Stack CreateStack(int maxelements)
{
	Stack s;
	if (maxelements < MinStackSize)
		;
	s = malloc(sizeof(struct StackRecord));
	if (s == NULL)
		;
	s->array = malloc(sizeof(int) * maxelements);
	if (s->array == NULL)
		;
	s->capacity = maxelements;
	s->topofstack = EmptyTOS;
	return s;
}

void DisposeStack(Stack s)
{
	if (s != NULL)
	{
		free(s->array);
		free(s);
	}
}

void Push(int x, Stack s)
{
	if (s->topofstack >= s->capacity - 1)
		;
	else
		s->array[++s->topofstack] = x;
}

int Top(Stack s)
{
	if (s->topofstack != EmptyTOS)
		return s->array[s->topofstack];
	return 0;
}

int TopAndPop(Stack s)
{
   if (s->topofstack != EmptyTOS)
		return s->array[s->topofstack--];
	return 0;
}

int main()
{
	Stack s = CreateStack(10);
	Push(1, s);
	Push(2, s);
	Push(3, s);
	printf("%d\n", TopAndPop(s));
	printf("%d\n", TopAndPop(s));
	printf("%d\n", TopAndPop(s));
}

