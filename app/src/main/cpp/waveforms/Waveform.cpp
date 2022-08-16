#include "Waveform.h"

void Waveform::doRender(uint32_t sampleCount) {
    renderWaveform(sampleCount);
    for (uint32_t i = 0; i < sampleCount; i++) {
        buffer[i] *= amplitude;
    }
}