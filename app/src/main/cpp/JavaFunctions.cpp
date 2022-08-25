#include <jni.h>
#include <android/input.h>
#include "AudioHost.h"
#include "Instrument.h"
#include <jni.h>
#include <string>
#include <list>

static AudioHost *audioHost;


template<class _InputIterator>
void *listGet(_InputIterator iterator, uint32_t n) {
    for (uint32_t i = 0; i < n; i++) {
        iterator++;
    }
    return *iterator;
}

Instrument *getInstrument(uint32_t id) {
    return static_cast<Instrument *>(listGet(audioHost->instruments->begin(), id));
}

template<class _InputIterator>
void listSet(_InputIterator iterator, uint32_t n, void *value) {
    for (uint32_t i = 0; i < n; i++) {
        iterator++;
    }
    *iterator = static_cast<Instrument *>(value);
}

extern "C" {

JNIEXPORT void JNICALL
Java_com_lukas_music_MainActivity_00024Companion_startAudio(JNIEnv *env, jobject activity) {
    audioHost = new AudioHost();
}

JNIEXPORT jint JNICALL
Java_com_lukas_music_instruments_InternalInstrument_createInstrument(JNIEnv *env, jobject thiz) {
    uint32_t result = audioHost->instruments->size();
    Instrument *instrument = new Instrument(audioHost);
    audioHost->instruments->push_back(instrument);
    return result;
}

JNIEXPORT void JNICALL
Java_com_lukas_music_instruments_InternalInstrument_startNote(JNIEnv *env, jobject thiz,
                                                              jint id, jdouble frequency) {
    getInstrument(id)->startNote(frequency);
}

JNIEXPORT void JNICALL
Java_com_lukas_music_instruments_InternalInstrument_endNote(JNIEnv *env, jobject thiz,
                                                            jint id) {
    getInstrument(id)->endNote();
}
JNIEXPORT void JNICALL
Java_com_lukas_music_ui_fragments_PlayFragment_setMasterVolume(JNIEnv *env, jobject thiz,
                                                               jdouble volume) {
    audioHost->masterVolume = volume;
}
JNIEXPORT void JNICALL
Java_com_lukas_music_instruments_InternalInstrument_setInstrumentWaveform(JNIEnv *env, jobject thiz,
                                                                          jint id, jint waveform) {
    getInstrument(id)->setWaveform(static_cast<WaveformType>(waveform));
}

JNIEXPORT void JNICALL
Java_com_lukas_music_instruments_InternalInstrument_setVolume(JNIEnv *env, jobject thiz, jint id,
                                                              jfloat volume) {
    getInstrument(id)->wave->amplitude = volume;
}

JNIEXPORT void JNICALL
Java_com_lukas_music_instruments_InternalInstrument_destroy(JNIEnv *env, jobject thiz, jint id) {
    listSet(audioHost->instruments->begin(), id, nullptr);
    delete getInstrument(id);
}

JNIEXPORT void JNICALL
Java_com_lukas_music_instruments_InternalInstrument_updateEnvelopeParameters(JNIEnv *env,
                                                                             jobject thiz, jint id,
                                                                             jfloat attack,
                                                                             jfloat delay,
                                                                             jfloat sustain,
                                                                             jfloat release) {
    Instrument *instrument = getInstrument((id));
    Envelope *envelope = instrument->envelope;
    envelope->attack = attack;
    envelope->delay = delay;
    envelope->sustain = sustain;
    envelope->release = release;
    envelope->update();
}

JNIEXPORT void JNICALL
Java_com_lukas_music_instruments_InternalInstrument_applyEffectAttributes(JNIEnv *env, jobject thiz,
                                                                          jint id,
                                                                          jint effect_number,
                                                                          jfloat influence,
                                                                          jfloat parameter1) {
    Instrument *instrument = getInstrument(id);
    Effect *effect;
    switch (effect_number) {
        case 0:
            effect = instrument->lowPass;
            break;
    }
    effect->influence = influence;
    effect->parameter1 = parameter1;
}
}