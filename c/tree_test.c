#include<stdio.h>

typedef struct TreeNode *Position;
typedef struct TreeNode *SearchTree;

struct TreeNode
{
	int element;
	SearchTree left;
	SearchTree right;
};

SearchTree MakeEmpty(SearchTree T)
{
	if (T != NULL)
	{
		MakeEmpty(T->left);
		MakeEmpty(T->right);
		free(T);
	}
	return NULL;
}

Position Find(int x, SearchTree t)
{
	if (t== NULL)
		return NULL;
	if(x < t->element)
		return Find(x, t->left);
	else if (x > t->element)
		return Find(x, t->right);
	else
		return t;
}

Position FindMax(SearchTree t)
{
	if (t != NULL)
		while(t->right != NULL)
			t = t->right;
	return t;
}

Position FindMin(SearchTree t)
{
	if ( t== NULL)
		return NULL;
	else if ( t->left == NULL)
		return t;
	else
		return FindMin(t->left);
}


SearchTree Insert(int x, SearchTree t)
{
	if (t == NULL)
	{
	    t = malloc(sizeof(struct TreeNode));
		if (t == NULL)
			;
	    else
		{
			t->element = x;
			t->left = t->right = NULL;
		}
	}
	else if(x < t->element)
		t->left = Insert(x, t->left);
	else if(x > t->element)
		t->right = Insert(x, t->right);
	return t;
}


void PrintTree(SearchTree t)
{
	if (t != NULL)
	{
		PrintTree(t->left);
		//PrintElement(t->element);
		printf("%d\t", t->element);
		PrintTree(t->right);
	}
}

int Max(int a, int b)
{
	if(a >= b)
		return a;
	else
		return b;
}

int Height(SearchTree t)
{
	if (t == NULL)
		return -1;
	else
		return 1 + Max(Height(t->left), Height(t->right));
}

int main()
{
	SearchTree t = NULL;
	t = Insert(6, t);
	t = Insert(4, t);
	t = Insert(8, t);
	t = Insert(3, t);
	t = Insert(5, t);
	t = Insert(7, t);
	PrintTree(t);
	printf("Tree Height = %d\n", Height(t));
	return 0;
}




