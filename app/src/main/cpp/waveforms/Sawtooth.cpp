#include "Sawtooth.h"

void Sawtooth::setFrequency(float frequency) {
    step = 2 * frequency / (double) host->sampleRate;
}

void Sawtooth::renderWaveform(uint32_t frameCount) {
    for (uint32_t i = 0; i < frameCount; i++) {
        buffer[i] = value;
        value += step;
        if (value > 1) {
            value = -1;
        }
    }
}
