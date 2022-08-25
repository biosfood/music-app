#ifndef MUSIC_SQUARE_H
#define MUSIC_SQUARE_H

#include "Waveform.h"

class Square : public Waveform {
private:
    float position, period, step;
public:
    void renderWaveform(uint32_t frameCount);

    void setFrequency(float freq);
};

#endif
