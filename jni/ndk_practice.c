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
	int i = 0;
	clock_t start,stop;

	src = malloc(memsize);
	dest = malloc(memsize);

	memset(src,'a',memsize);
	start = clock();
	memcpy(src,dest,memsize);
	stop = clock();
    
    free(src);
    free(dest);

	return (1000.0f*(double)(stop-start)/CLOCKS_PER_SEC);

}

// floating point arithmetic
double
Java_my_kotatsu_ndk_1practice3_MainActivity_floatPoint( JNIEnv* env, jobject thiz, long times )
{
	float f=0;
	long i;
	clock_t start,stop;
	start = clock();

	// times回演算
	for( i=0; i< times; i++){
		f += 0.0000001f;
	}
	__android_log_print(ANDROID_LOG_WARN, "native-activity", "%f",f);
	stop = clock();

	return (1000.0f*(double)(stop-start)/CLOCKS_PER_SEC);
}

// integer arithmetic
double
Java_my_kotatsu_ndk_1practice3_MainActivity_integer( JNIEnv* env, jobject thiz, long times )
{
	int j=0;
	long i;
	clock_t start,stop;
	start = clock();

	// times回演算
	for( i=0; i< times; i++){
		j++;
	}
	__android_log_print(ANDROID_LOG_WARN, "native-activity", "%d",j);
	stop = clock();

	return (1000.0f*(double)(stop-start)/CLOCKS_PER_SEC);
}

