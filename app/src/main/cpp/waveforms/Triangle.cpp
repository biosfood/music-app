#include "Triangle.h"

void Triangle::setFrequency(float frequency) {
    step = 4 * frequency / (double) host->sampleRate;
}

void Triangle::renderWaveform(uint32_t frameCount) {
    for (uint32_t i = 0; i < frameCount; i++) {
        buffer[i] = value;
        value += step;
        if (value > 1) {
            step *= -1;
            value = 1;
        } else if (value < -1) {
            step *= -1;
            value = -1;
        }
    }
}