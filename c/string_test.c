#include <stdio.h>
#include <ctype.h>

int atoi(char s[])
{
	int i, n, sign;
	for (i = 0; isspace(s[i]); i++)
		;
	sign = (s[i]=='-') ? -1 : 1;
	if (s[i] == '+' || s[i] == '-')
		i++;
	for (n = 0; isdigit(s[i]); i++)
		n = n * 10 + (s[i] - '0');
	return sign * n;
}

void reverse(char s[])
{
	int i, j;
	char c;
	for (i = 0, j = strlen(s)-1; i < j; i++, j--)
	{
	    c = s[i];
		s[i] = s[j];
		s[j] = c;
	}
}

void itoa(int n, char s[])
{
	int i, sign;
	if ((sign = n) < 0)
		n = -n;
	i = 0;
	do
	{
		s[i++] = n % 10 + '0';
	}while((n /= 10) > 0);
	if (sign < 0)
		s[i++] = '-';
	s[i] = '\0';
	reverse(s);
}

char* strcpy(char *dst, const char *src)
{
	char *addr = dst;
	while ((*dst++ = *src++) != '\0');
	return addr;
}

int strcmp(char *s, char *t)
{
	for( ; *s == *t; s++, t++)
		if (*s == '\0')
			return 0;
	return *s - *t;
}

char* strncpy(char *dst, const char *src, int count)
{
	char *addr = dst;
	while(count)
	{
	    *dst = *src;
		if (*src == '\0')
			break;
		dst++;
		src++;
		count--;
	}   
	return addr;
}

size_t strlen(const char *s)
{
	int length = 0;
	while (s != '\0')
	{
		s++;
	    length++;
	}
	return length;
}

void squeeze(char s[], char c)
{
	int i, j;
	for (i=j=0; s[i] != '\0'; i++)
	{
		if (s[i] != c)
			s[j++] = s[i];
	}
	s[j] = '\0';
}

void strcat(char s[], char t[])
{
	int i,j;
	i = j = 0;
	while (s[i] != '\0')
		i++;
	while((s[i++] = t[j++]) != '\0');
}

int trim(char s[])
{
	int n;
	for (n = strlen(s) - 1; n >= 0; n--)
	{
		if (s[n] != ' ' && s[n] != '\n' && s[n] != '\t')
			break;
	}
	s[n+1] = '\0';
	return n;
}

int strindex(char s[], char t[])
{
	int i, j, k;
	for (i = 0; s[i] != '\0'; i++)
	{
		for (j = i, k = 0; t[k] != '\0' && s[j] == t[k]; j++, k++)
			;
	    if ( k > 0 && t[k] == '\0')
			return i;
	}
	return -1;
}

void* memcpy(void *dst, const void *src, size_t num)
{
	unsigned char *dst_byte;
	unsigned char *src_byte;
	unsigned int *dst_aligned = (unsigned int *)dst;
	unsigned int *src_aligned = (unsigned int *)src;

	if ((((unsigned int)dst_aligned & 0x3) == 0) 
	   && (((unsigned int)src_aligned & 0x3) == 0) 
	   && (num >= 4))
	{
		while (num >= 4)
		{
			*dst_aligned++ = *src_aligned++;
			num -= 4;
        }
	}
	
	dst_byte = (unsigned char *)dst_aligned;
	src_byte = (unsigned char *)src_aligned;
	while (num--)
		*dst_byte++ = *src_byte++;
	return dst;
}

int main(void)
{
	char s1[] = "123";
	char s2[] = "+974";
	char s3[] = "-649";
	printf("%d\n%d\n%d\n", atoi(s1), atoi(s2), atoi(s3));

	char s[] = "1234567890";
	printf("%s\n", s);
	reverse(s);
	printf("%s\n", s);

	char s21[10];
	char s22[10];
	int n1 = 123, n2 = -938;
	itoa(n1, s21);
	itoa(n2, s22); 
	printf("%s\n", s21);
	printf("%s\n", s22);

	return 0;
}

