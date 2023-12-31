#include <android/log.h>
#include "../Instrument.h"
#include "Envelope.h"

void Envelope::initialize(AudioHost *host) {
    this->host = host;
    update();
}

void Envelope::update() {
    attackIncrement = 1 / attack / host->sampleRate;
    delayIncrement = 1 / delay / host->sampleRate;
    releaseIncrement = 1 / release / host->sampleRate;
}

void Envelope::startNote() {
    phase = ATTACK;
}

void Envelope::endNote() {
    phase = RELEASE;
}

void Envelope::doRender(uint32_t sampleCount) {
    for (int i = 0; i < sampleCount; ++i) {
        switch (phase) {
            case ATTACK:
                value += attackIncrement;
                if (value > 1) {
                    value = 1;
                    phase = DELAY;
                }
                break;
            case DELAY:
                value -= delayIncrement;
                if (value < sustain) {
                    value = sustain;
                    phase = SUSTAIN;
                }
                break;
            case RELEASE:
                value -= releaseIncrement;
                if (value < 0) {
                    value = 0;
                    phase = NONE;
                }
                break;
        }
        buffer[i] = value;
    }
}