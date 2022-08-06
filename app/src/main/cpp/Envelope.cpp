#include "Envelope.h"

void Envelope::startNote() {
    value = 1;
}

void Envelope::endNote() {
    value = 0;
}

float *Envelope::render(uint32_t sampleCount) {
    if (sampleCount > bufferSize) {
        bufferSize = sampleCount;
        buffer = new float[sampleCount];
    }
    for (int i = 0; i < sampleCount; ++i) {
        buffer[i] = value;
    }
    return buffer;
}