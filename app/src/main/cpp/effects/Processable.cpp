#include "Processable.h"

float *Processable::render(uint32_t sampleCount) {
    if (sampleCount > bufferSize) {
        bufferSize = sampleCount;
        if (buffer) {
            delete buffer;
        }
        buffer = new float[sampleCount];
    }
    doRender(sampleCount);
    return buffer;
}