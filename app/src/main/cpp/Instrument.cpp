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
    noise->host = host;
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

void processEffect(float *waveform, uint32_t count, Effect *effect) {
    if (effect->influence < 0.01f) {
        return;
    }
    effect->input = waveform;
    float *effectOutput = effect->render(count);
    multiply(effectOutput, effect->influence, count);
    multiply(waveform, 1 - effect->influence, count);
    add(waveform, effectOutput, count);
}

void Instrument::render(float *buffer, uint32_t count) {
    float *waveform = wave->render(count);
    processEffect(waveform, count, lowPass);
    processEffect(waveform, count, noise);
    multiply(waveform, envelope->render(count), count);
    multiply(waveform, volume, count);
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