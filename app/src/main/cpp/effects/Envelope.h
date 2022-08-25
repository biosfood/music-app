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
    AudioHost *host;
public:
    float attack = 0.05, delay = 0.05, sustain = 0.7, release = 0.1;

    void initialize(AudioHost *host);

    void update();

    void startNote();
    void endNote();
    void doRender(uint32_t sampleCount);
};


#endif