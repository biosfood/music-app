#include "Instrument.h"
#include "waveforms/Sawtooth.h"

Instrument::Instrument(AudioHost *host) {
    this->host = host;
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
}

void Instrument::startNote(float frequency) {
    wave->setFrequency(frequency);
    envelope->startNote();
}

void Instrument::endNote() {
    envelope->endNote();
}

void Instrument::setWaveform(WaveformType waveform) {
    // delete &wave;
    switch (waveform) {
        case SINE:
            wave = new Sine();
            break;
        case SAWTOOTH:
            wave = new Sawtooth();
            break;
    }
    wave->initialize(host);
}