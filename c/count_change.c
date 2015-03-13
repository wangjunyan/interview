#include<stdio.h>
#include<stdlib.h>

int first_denomination(int kinds_of_coins){
    if(kinds_of_coins == 1){
        return 1;
    }else if(kinds_of_coins == 2){
        return 5;
    }else if(kinds_of_coins == 3){
        return 10;
    }else if(kinds_of_coins == 4){
        return 25;
    }else if(kinds_of_coins == 5){
        return 50;
    }
    return 0;
}

int cc(int amount, int k){
    if(amount == 0){
        return 1;
    }

    if(amount < 0 || k==0){
        return 0;
    }

    return cc(amount, k-1) + cc(amount-first_denomination(k), k);
}

int main(int argc, char* argv[]){
    int i;
    int amount;
    for(i=0; i<argc; i++){
        printf("argv[%d] = %s\n", i, argv[i]);
    }
    amount = atoi(argv[1]);
    printf("cc(%d) = %d\n", amount, cc(amount, 5));
    return 0;
}
