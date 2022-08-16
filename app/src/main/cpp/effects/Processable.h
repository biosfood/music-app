#ifndef MUSIC_PROCESSABLE_H
#define MUSIC_PROCESSABLE_H


#include <cstdint>

class Processable {
protected:
    float *buffer = nullptr;
    uint32_t bufferSize = 0;
public:
    float *render(uint32_t sampleCount);

    virtual void doRender(uint32_t sampleCount) = 0;
};


#endif
