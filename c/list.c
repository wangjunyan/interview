#include <stdio.h>

struct item 
{
    int value;
    struct item * next;
};

struct item * new_item(int value)
{
    struct item *node = (struct item *)malloc(sizeof(struct item));
    node->value = value;
    node->next = NULL;
}

struct item * add_front(struct item *list, struct item *node)
{
    node->next = list;
    return node;
}

struct item * add_end(struct item *list, struct item *node)
{
    struct item *p;
    if (list == NULL)
    {
        return node;
    }
    p = list;
    while(p->next != NULL)
    {
        p = p->next;
    }
    p->next = node;
    return list;
}

struct item * find_item(struct item *list, int value)
{
    struct item *p;
    for(p = list; p != NULL; p = p->next)
    {
        if (value == p->value)
        {
            return p;
        }
    }
    return NULL;
}

void free_list(struct item *list)
{
    struct item *p;
    p = list;
    while (p != NULL)
    {
        p = p->next;
        free(list);
        list = p;
    }
}

void print_list(struct item *list)
{
    struct item *p;
    for(p = list; p != NULL; p=p->next)
    {
        printf("value = %d\n", p->value);
    }
}

void loop_test(struct item *list)
{
    struct item *p;
    struct item *q;
    int i;
    int p_step = 0;
    int q_step = 0;
    struct item *merge_point;
    int loop_length;
    int list_length;
    if (list == NULL || list->next == NULL)
    {
        printf("loop test: false\n");
        return;
    }
    p = list;
    q = list;
    while (1)
    {
        p = p->next;
        p_step ++;
        if (p == NULL)
        {
            printf("loop test: false\n");
            printf("list length: %d\n", p_step);
            return;
        }
        q = q->next;
        q_step++;
        if (q == NULL)
        {
            printf("loop test: false\n");
            printf("list length: %d\n", q_step);
            return;
        } else
        {
            q = q->next;
            q_step++;
            if (q == NULL)
            {
                printf("loop test: false\n");
                printf("list length: %d\n", q_step);
                return;
            }
        }
        if (p == q)
        {
            printf("loop test: true\n");
            break;
        }
    }
    p_step = 0;
    q_step = 0;
    do {
        p = p->next;
        p_step++;
        q = q->next->next;
    } while (p != q);
    loop_length = p_step;
    printf("loop length: %d\n", p_step);

    p = list;
    q = list;
    p_step = 0;
    for(i = 0; i < loop_length; i++) q = q->next;
    while(p != q){
        p = p->next;
        p_step++;
        q = q->next;
    }
    printf("merge point value: %d\n", p->value);
    printf("link list length: %d\n", p_step+loop_length);
    return;

}

void test_01()
{
    struct item *list = NULL;
    int i;
    struct item *p;
    struct item *q;
    loop_test(list);
    for(i = 1; i < 9; i++)
    {
        list = add_end(list, new_item(i));
        loop_test(list);
    }

    //print_list(list);
    q = find_item(list, 8);
    for(i = 1; i < 9; i++)
    {
        p = find_item(list, i);
        q->next = p;
        loop_test(list);
    }
    return;
}

void main()
{
    test_01();
    return;
}


