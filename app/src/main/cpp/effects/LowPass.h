#ifndef MUSIC_LOWPASS_H
#define MUSIC_LOWPASS_H

#include "Effect.h"

class LowPass : public Effect {
private:
    float charge = 0;
    float capacitance = 0;
    float inverseCapacitance = 0;
    float timeStep = 0;
public:
    void update();

    void doRender(uint32_t sampleCount);
};

#endif