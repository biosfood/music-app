#ifndef MUSIC_TRIANGLE_H
#define MUSIC_TRIANGLE_H


#include "Waveform.h"

class Triangle : public Waveform {
private:
    float value = 0, step = 0;
public:
    void renderWaveform(uint32_t frameCount);

    void setFrequency(float freq);
};


#endif
