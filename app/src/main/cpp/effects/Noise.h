#ifndef MUSIC_NOISE_H
#define MUSIC_NOISE_H

#include "Effect.h"

class Noise : public Effect {
    void update();

    void doRender(uint32_t sampleCount);
};


#endif
