#include "Noise.h"
#include <cstdlib>
#include <ctime>

void Noise::update() {
    srand(time(0));
}

const static int q = 15;
const static float c1 = (1 << q) - 1;
const static float c2 = ((int) (c1 / 3)) + 1;
const static float c3 = 1.f / c1;

void Noise::doRender(uint32_t sampleCount) {
    for (uint32_t i = 0; i < sampleCount; i++) {
        float random = ((float) rand() / (float) (RAND_MAX + 1));
        buffer[i] =
                (2.f * ((random * c2) + (random * c2) + (random * c2)) - 3.f * (c2 - 1.f)) * c3 /
                10;
    }
}