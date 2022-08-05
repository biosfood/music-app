#include "SineWave.h"
#include <math.h>
#include <android/log.h>

SineWave::SineWave(AudioHost *host) {
    this->host = host;
}

void SineWave::setFrequency(float freq) {
    frequency = freq;
    phaseStep = (2 * M_PI * frequency) / (double) host->sampleRate;
}

void SineWave::render(float *data, uint32_t frameCount) {
    for (uint32_t i = 0; i < frameCount; i++) {
        data[i] += (float)(sin(phase) * amplitude);
        phase += phaseStep;
        if (phase > 2*M_PI) {
            phase -= 2*M_PI;
        }
    }
}
