#ifndef MUSIC_EFFECT_H
#define MUSIC_EFFECT_H

#include <cstdint>
#include "Processable.h"
#include "../AudioHost.h"

class Effect : public Processable {
public:
    float parameter1, frequency;
    float *input;
    AudioHost *host;

    virtual void doRender(uint32_t sampleCount) = 0;

    virtual void update() = 0;
};

#endif