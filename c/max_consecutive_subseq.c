#include<stdio.h>

float maxsubseqsum(float a[], int len){
    float global_max = 0;
    float suffix_max = 0;
    int i = 0;
    for(i = 0; i < len; i++){
        if(a[i] + suffix_max > global_max){
            suffix_max = suffix_max + a[i];
            global_max = suffix_max;
        }else{
            if(a[i] + suffix_max > 0){
                suffix_max = a[i] + suffix_max;
            }else{
                suffix_max = 0;
            }
        }
    }
    return global_max;
}

int main(){
    float a[8] = {2, -3, 1.5, -1, 3, -2, -3, 3};
    float sum = maxsubseqsum(a, 8);
    printf("the max consecutive subseq sum = %f\n", sum);
    return 0;
}


