#include "Instrument.h"
#include "waveforms/Sawtooth.h"
#include "waveforms/Sine.h"
#include "waveforms/Square.h"
#include "waveforms/Triangle.h"

Instrument::Instrument(AudioHost *host) {
    this->host = host;
    wave = new Sine();
    wave->host = host;
    envelope->initialize(host);
    lowPass->host = host;
}

void multiply(float *target, float *modulation, uint32_t size) {
    for (uint32_t i = 0; i < size; i++) {
        target[i] *= modulation[i];
    }
}

void multiply(float *target, float value, uint32_t size) {
    for (uint32_t i = 0; i < size; i++) {
        target[i] *= value;
    }
}

void add(float *target, float *other, uint32_t size) {
    for (uint32_t i = 0; i < size; i++) {
        target[i] += other[i];
    }
}

void Instrument::render(float *buffer, uint32_t count) {
    float *modulation = envelope->render(count);
    float *waveform = wave->render(count);
    multiply(waveform, modulation, count);
    lowPass->input = waveform;
    float *filtered = lowPass->render(count);
    multiply(filtered, lowPass->influence, count);
    multiply(waveform, 1 - lowPass->influence, count);
    add(waveform, filtered, count);
    add(buffer, waveform, count);
}

void Instrument::startNote(float frequency) {
    wave->setFrequency(frequency);
    envelope->startNote();
    lowPass->frequency = frequency;
    lowPass->update();
}

void Instrument::endNote() {
    envelope->endNote();
}

void Instrument::setWaveform(WaveformType waveform) {
    Waveform *old = wave;
    switch (waveform) {
        case SINE:
            wave = new Sine();
            break;
        case SAWTOOTH:
            wave = new Sawtooth();
            break;
        case SQUARE:
            wave = new Square();
            break;
        case TRIANGLE:
            wave = new Triangle();
    }
    wave->host = host;
    delete old;
}