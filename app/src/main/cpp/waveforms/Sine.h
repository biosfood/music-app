#ifndef MUSIC_SINE_H
#define MUSIC_SINE_H

class Sine;

#include <stdint.h>
#include "Waveform.h"

class Sine : public Waveform {
private:
    float phaseStep, phase = 0;
public:
    void renderWaveform(uint32_t frameCount);

    void setFrequency(float freq);
};

#endif