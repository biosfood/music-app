#include "SineWave.h"
#include <math.h>
#include <android/log.h>

void SineWave::setSampleRate(uint32_t sampleRate) {
    phaseStep = (2 * M_PI * frequency) / (double) sampleRate;
}

void SineWave::render(float *data, uint32_t frameCount) {
    for (uint32_t i = 0; i < frameCount; i++) {
        data[i] = (float)(sin(phase) * amplitude);
        phase += phaseStep;
        if (phase > 2*M_PI) {
            phase -= 2*M_PI;
        }
    }
}