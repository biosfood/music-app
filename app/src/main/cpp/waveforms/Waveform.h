#ifndef MUSIC_WAVEFORM_H
#define MUSIC_WAVEFORM_H

enum WaveformType {
    SINE = 0,
    SAWTOOTH = 1,
};

class Waveform {
protected:
    AudioHost *host;
public:
    float amplitude = 0.3f;

    virtual void initialize(AudioHost *host) = 0;

    virtual void render(float *data, uint32_t frameCount) = 0;

    virtual void setFrequency(float freq) = 0;
};


#endif
