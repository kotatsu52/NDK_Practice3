#include <string.h>
#include <time.h>
#include <jni.h>
#include <android/log.h>

char TAG[]="NDK_PRACTICE3";

// copy memory
double
Java_my_kotatsu_ndk_1practice3_MainActivity_copyBuffer( JNIEnv* env, jobject thiz, int memsize)
{
	char *src;
	char *dest;
	clock_t start,stop;

	src = malloc(memsize);
	dest = malloc(memsize);

	memset(src,'a',memsize);
	start = clock();
	
    //★メモリコピー処理
    
	stop = clock();
    
    free(src);
    free(dest);

	return (1000.0f*(double)(stop-start)/CLOCKS_PER_SEC);

}

// floating point arithmetic
double
Java_my_kotatsu_ndk_1practice3_MainActivity_floatPoint( JNIEnv* env, jobject thiz, long times )
{
	clock_t start,stop;
	start = clock();
    
    //★浮動小数点計算
    
	stop = clock();

	return (1000.0f*(double)(stop-start)/CLOCKS_PER_SEC);
}

// integer arithmetic
double
Java_my_kotatsu_ndk_1practice3_MainActivity_integer( JNIEnv* env, jobject thiz, long times )
{
	clock_t start,stop;
	start = clock();

    //★整数計算
    
    stop = clock();

	return (1000.0f*(double)(stop-start)/CLOCKS_PER_SEC);
}

