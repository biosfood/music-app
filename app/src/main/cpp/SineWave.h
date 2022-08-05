#ifndef MUSIC_SINEWAVE_H
#define MUSIC_SINEWAVE_H

class SineWave;

#include <stdint.h>
#include "AudioHost.h"

class SineWave {
private:
    float phaseStep, phase = 0;
    float frequency;
    AudioHost *host;
public:
    SineWave(AudioHost *host);
    float amplitude = 0.0f;
    void render(float *data, uint32_t frameCount);
    void setFrequency(float freq);
};

#endif //MUSIC_SINEWAVE_H