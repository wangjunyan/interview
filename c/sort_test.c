#include <stdio.h>

void swap(int v[], int i, int j)
{
	int temp;
	temp = v[i];
	v[i] = v[j];
	v[j] = temp;
}

void quicksort(int v[], int left, int right)
{
	int i, last;
	if (left >= right)
		return;
	swap(v, left, (left + right) / 2);
	last = left;
	for (i = left + 1; i <= right; i++)
	{
		if (v[i] < v[left])
			swap(v, ++last, i);
	}
	swap(v, left, last);
	quicksort(v, left, last - 1);
	quicksort(v, last + 1, right);
}

void bubblesort(int v[], int n)
{
	int i,j;
	char exchange;
	for (i = 0; i < n-1; i++)
	{
		exchange = 0;
	    for (j = n - 1; j >= i; j--)
	    {
	        if (v[j+1] < v[j])
	        {
	            swap(v, j, j+1);
	            exchange = 1;
	        }
	    }
		if (exchange == 0)
			return;
	}
}

void insertsort(int v[], int n)
{
	int i, j;
	for (i = 1; i < n; i++)
	{
		for (j = i; j > 0 && v[j-1] > v[j]; j--)
		{
			swap(v, j-1, j);
		}
	}
}

void insertsort2(int v[], int n)
{
	int i, j, t;
	for (i = 1; i < n; i++)
	{
		t = v[i];
		for (j = i; j > 0 && v[j-1] > t; j--)
			v[j] = v[j-1];
		v[j] = t;
	}
}


void merge(int v[], int tmp[], int lpos, int rpos, int rend)
{
	int i, lend, num, tmppos;
	lend = rpos - 1;
	tmppos = lpos;
	num = rend - lpos + 1;
	while(lpos <= lend && rpos <= rend)
	{
		if (v[lpos] <= v[rpos])
			tmp[tmppos++] = v[lpos++];
		else
			tmp[tmppos++] = v[rpos++];
	}
	while (lpos <= lend)
	{
		tmp[tmppos++] = v[lpos++];
	}
	while (rpos <= rend)
	{
		tmp[tmppos++] = v[rpos++];
	}
	for (i = 0; i < num; i++, rend--)
	{
		v[rend] = tmp[rend];
	}
}

void msort(int v[], int tmp[], int left, int right)
{
	int center;
	if(left < right)
	{
	center = (left + right) / 2;
	msort(v, tmp, left, center);
	msort(v, tmp, center+1, right);
	merge(v, tmp, left, center+1, right);
	}
}

void mergesort(int v[], int n)
{
	int *tmpArray;
	tmpArray = malloc(n * sizeof(int));
	if (tmpArray != NULL)
	{
		msort(v, tmpArray, 0, n-1);
		free(tmpArray);
	}
}

int main(void)
{
	int i;
	int v[10] = {4, 9, 10, 2, 3, 8, 5, 1, 7, 6};
	for (i = 0; i < 10; i++) printf("%d\t", v[i]);
	quicksort(v, 0, 9);
	printf("\n");
	for (i = 0; i < 10; i++) printf("%d\t", v[i]);
	return 0;
}


