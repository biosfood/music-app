#include <cstdint>
#include "Square.h"

void Square::setFrequency(float frequency) {
    period = 1 / frequency;
    step = 1 / (double) host->sampleRate;
}

void Square::renderWaveform(uint32_t frameCount) {
    for (uint32_t i = 0; i < frameCount; i++) {
        buffer[i] = position > period / 2 ? 1 : -1;
        position += step;
        if (position > period) {
            position = 0;
        }
    }
}