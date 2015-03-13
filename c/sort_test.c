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

int partition(int v[], int left, int right)
{
    int pivot = v[left];
    int l = left;
    int r = right;
    int middle;
    while(l < r){
        while(v[l] <= pivot && l <= right) l++;
        while(v[r] > pivot && r >= left) r--;
        if(l < r) swap(v, l, r);
    }
    middle = r;
    swap(v, left, middle);
    return middle;
}

void quicksort2(int v[], int left, int right)
{
    int middle;
    if(left < right){
        middle = partition(v, left, right);
        quicksort2(v, left, middle-1);
        quicksort2(v, middle+1, right);
    }
}

int select = 0;
void selection(int v[], int left, int right, int k)
{
    //int select;
    int middle;
    if(left == right){
        select = left;
        //printf("select=%d\n", select);
        return select;
    }else{
        middle = partition(v, left, right);
        //printf("middle=%d\n", v[middle]);
        if(middle-left+1 >= k) {
            selection(v, left, middle, k);
        }else{
            selection(v, middle+1, right, k-(middle-left+1));
        }
    }
    return 0;
}

int majority(int x[], int n){
    int c, m, i, r, cnt;
    c = x[0];
    m = 1;
    for(i=1; i<n; i++){
        if(m == 0){
            c = x[i];
            m = 1;
        }else{
            if(c == x[i]){
                m++;
            }else{
                m--;
            }
        }
    }
    if(m == 0){
        r = -1;
    }else{
        cnt = 0;
        for(i=0; i<n; i++){
            if(x[i]==c) cnt++;
        }
        if(cnt > n/2){
            r = c;
        }else{
            r = -1;
        }
    }
    return r;
}

int main(void)
{
	int i;
    int k = 5;
    int s;
	int v[10] = {4, 9, 10, 2, 3, 8, 5, 1, 7, 6};
    int t[10];
	for (i = 0; i < 10; i++) printf("%d\t", v[i]);
    memcpy(t, v, sizeof(int)*10);
	//quicksort2(v, 0, 9);

    for(k = 1; k <= 10; k++){
        printf("\n");
        memcpy(v, t, sizeof(int)*10);
        selection(v, 0, 9, k);
        printf("the %dth number: %d\n", k, v[select]);
    }
	printf("\n");
	//for (i = 0; i < 10; i++) printf("%d\t", v[i]);
    printf("\n");

    int x[] = {1, 5, 2, 5, 5, 3, 5};
    printf("majority = %d\n", majority(x, 7));
	return 0;
}


