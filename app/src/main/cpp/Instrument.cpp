#include "Instrument.h"

Instrument::Instrument(AudioHost *host) {
    wave->initialize(host);
    envelope->initialize(host);
}

float *multiply(float *target, float *modulation, uint32_t size) {
    for (uint32_t i = 0; i < size; i++) {
        target[i] *= modulation[i];
    }
    return target;
}

void Instrument::render(float *buffer, uint32_t count) {
    float *modulation = envelope->render(count);
    wave->render(buffer, count);
    multiply(buffer, modulation, count);
}

void Instrument::startNote(float frequency) {
    wave->setFrequency(frequency);
    envelope->startNote();
}

void Instrument::endNote(float frequency) {
    envelope->endNote();
}