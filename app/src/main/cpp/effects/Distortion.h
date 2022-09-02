#ifndef MUSIC_DISTORTION_H
#define MUSIC_DISTORTION_H

#include "Effect.h"

class Distortion : public Effect {
public:
    void update();

    void doRender(uint32_t sampleCount);
};


#endif
