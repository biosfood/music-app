#include "Instrument.h"

Instrument::Instrument(AudioHost *host) {
    this->wave = new SineWave(host);
}

void Instrument::render(float *buffer, uint32_t count) {
    this->wave->render(buffer, count);
}