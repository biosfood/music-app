#include "Sawtooth.h"

void Sawtooth::initialize(AudioHost *host) {
    this->host = host;
}

void Sawtooth::setFrequency(float frequency) {
    step = frequency / (double) host->sampleRate;
}

void Sawtooth::render(float *data, uint32_t frameCount) {
    for (uint32_t i = 0; i < frameCount; i++) {
        data[i] += value * amplitude;
        value += step;
        if (value > 1) {
            value = 0;
        }
    }
}
