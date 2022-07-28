#include <jni.h>
#include <android/input.h>
#include "AudioEngine.h"
#include <jni.h>
#include <string>

static AudioEngine *audioEngine;

extern "C" {

JNIEXPORT void JNICALL
Java_com_lukas_music_MainActivity_startAudio(JNIEnv *env, jobject activity) {
    audioEngine = new AudioEngine();
}

JNIEXPORT void JNICALL
Java_com_lukas_music_MainActivity_muteAudio(JNIEnv *env, jobject activity, float volume) {
    audioEngine->stop();
}

JNIEXPORT void JNICALL
Java_com_lukas_music_MainActivity_unmuteAudio(JNIEnv *env, jobject activity, float volume) {
    audioEngine->start();
}
}