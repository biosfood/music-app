#ifndef MUSIC_ENVELOPE_H
#define MUSIC_ENVELOPE_H

class Envelope;

#include <cstdint>
#include "AudioHost.h"

enum EnvelopePhase {
    NONE, ATTACK, DELAY, SUSTAIN, RELEASE,
};

class Envelope {
private:
    uint32_t bufferSize = 0;
    float *buffer;
    EnvelopePhase phase;
    float attackIncrement, delayIncrement, releaseIncrement;
    float value = 1;
public:
    float attack = 0.05, delay = 0.2, sustain = 0.75, release = 0.1;

    void initialize(AudioHost *host);

    void startNote();

    void endNote();

    float *render(uint32_t sampleCount);
};


#endif