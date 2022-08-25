#include "../Instrument.h"
#include <math.h>

// direct model of a RC-filter, R = 1 Ohm for convenience

void LowPass::update() {
    charge = 0;
    inverseCapacitance = 2 * M_PI * frequency * pow(2, parameter1);
    capacitance = 1 / inverseCapacitance;
    timeStep = 1 / (float) host->sampleRate;
}

void LowPass::doRender(uint32_t sampleCount) {
    for (uint32_t i = 0; i < sampleCount; i++) {
        charge += (input[i] - charge * inverseCapacitance) * timeStep;
        buffer[i] = charge * inverseCapacitance;
    }
}