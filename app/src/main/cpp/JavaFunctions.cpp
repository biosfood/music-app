#include <jni.h>
#include <android/input.h>
#include "AudioHost.h"
#include <jni.h>
#include <string>
#include <list>

static AudioHost *audioHost;


template <class _InputIterator>
void *listGet(_InputIterator iterator, uint32_t n) {
    for (uint32_t i = 0; i < n; i++) {
        iterator++;
    }
    return *iterator;
}

extern "C" {

JNIEXPORT void JNICALL
Java_com_lukas_music_MainActivity_00024Companion_startAudio(JNIEnv *env, jobject activity) {
    audioHost = new AudioHost();
}

extern "C"
JNIEXPORT jint JNICALL
Java_com_lukas_music_instruments_InternalInstrument_createInstrument(JNIEnv *env, jobject thiz) {
    uint32_t result = audioHost->instruments->size();
    Instrument *instrument = new Instrument(audioHost);
    audioHost->instruments->push_back(instrument);
    return result;
}

extern "C"
JNIEXPORT void JNICALL
Java_com_lukas_music_instruments_InternalInstrument_setInstrumentActive(JNIEnv *env, jobject thiz,
                                                                        jint id, jboolean active) {
    Instrument *instrument = static_cast<Instrument *>(listGet(audioHost->instruments->begin(),
                                                               id));
    instrument->wave->amplitude = active ? 0.3 : 0.0;
}
}
extern "C"
JNIEXPORT void JNICALL
Java_com_lukas_music_instruments_InternalInstrument_startNote(JNIEnv *env, jobject thiz,
                                                              jint id, jdouble frequency) {
    Instrument *instrument = static_cast<Instrument *>(listGet(audioHost->instruments->begin(),
                                                               id));
    instrument->startNote(frequency);
}

extern "C"
JNIEXPORT void JNICALL
Java_com_lukas_music_instruments_InternalInstrument_endNote(JNIEnv *env, jobject thiz,
                                                            jint id) {
    Instrument *instrument = static_cast<Instrument *>(listGet(audioHost->instruments->begin(),
                                                               id));
    instrument->endNote();
}