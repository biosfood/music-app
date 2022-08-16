#ifndef MUSIC_ENVELOPE_H
#define MUSIC_ENVELOPE_H

class Envelope;

#include <cstdint>
#include "../AudioHost.h"
#include "Processable.h"

enum EnvelopePhase {
    NONE, ATTACK, DELAY, SUSTAIN, RELEASE,
};

class Envelope : public Processable {
private:
    EnvelopePhase phase;
    float attackIncrement, delayIncrement, releaseIncrement;
    float value = 0;
public:
    float attack = 0.05, delay = 0.2, sustain = 0.75, release = 1;

    void initialize(AudioHost *host);

    void startNote();

    void endNote();

    void doRender(uint32_t sampleCount);
};


#endif