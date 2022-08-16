#ifndef MUSIC_SAWTOOTH_H
#define MUSIC_SAWTOOTH_H


#include <cstdint>
#include "../AudioHost.h"
#include "Waveform.h"

class Sawtooth : public Waveform {
private:
    float value = 0, step = 0;
public:
    void renderWaveform(uint32_t frameCount);

    void setFrequency(float freq);
};


#endif
