#ifndef MUSIC_ENVELOPE_H
#define MUSIC_ENVELOPE_H

#include <cstdint>

class Envelope;

class Envelope {
private:
    uint32_t bufferSize = 0;
    float *buffer;
public:
    float attack, delay, sustain, release;
    float value = 1;

    void startNote();

    void endNote();

    float *render(uint32_t sampleCount);
};


#endif