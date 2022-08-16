#include "../AudioHost.h"
#include "Sine.h"
#include <math.h>
#include <android/log.h>

void Sine::setFrequency(float frequency) {
    phaseStep = (2 * M_PI * frequency) / (double) host->sampleRate;
}

void Sine::renderWaveform(uint32_t frameCount) {
    for (uint32_t i = 0; i < frameCount; i++) {
        buffer[i] = sin(phase);
        phase += phaseStep;
        if (phase > 2 * M_PI) {
            phase -= 2 * M_PI;
        }
    }
}
