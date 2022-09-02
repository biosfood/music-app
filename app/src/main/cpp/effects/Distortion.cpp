#include "Distortion.h"

void Distortion::update() {
}

void Distortion::doRender(uint32_t sampleCount) {
    for (uint32_t i = 0; i < sampleCount; i++) {
        float value = input[i] * parameter1;
        if (value > 1.f) {
            value = 1.f;
        } else if (value < -1.f) {
            value = -1.f;
        }
        buffer[i] = value;
    }
}