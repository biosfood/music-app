#ifndef MUSIC_WAVEFORM_H
#define MUSIC_WAVEFORM_H

class Waveform;

enum WaveformType {
    SINE = 0,
    SAWTOOTH = 1,
    SQUARE = 2,
    TRIANGLE = 3,
};

#include "../effects/Processable.h"
#include "../AudioHost.h"

class Waveform : public Processable {
public:
    AudioHost *host;

    void doRender(uint32_t sampleCount);

    virtual void renderWaveform(uint32_t sampleCount) = 0;

    virtual void setFrequency(float freq) = 0;
};


#endif
