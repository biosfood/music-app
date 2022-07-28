#ifndef MUSIC_SINEWAVE_H
#define MUSIC_SINEWAVE_H

#include <stdint.h>

class SineWave {
private:
    float phaseStep = 0.01, phase = 0;
public:
    float amplitude = 0.3, frequency = 440.0f;
    void render(float *data, uint32_t frameCount);
    void setSampleRate(uint32_t sampleRate);
};

#endif //MUSIC_SINEWAVE_H