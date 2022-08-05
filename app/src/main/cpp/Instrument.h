#ifndef MUSIC_INSTRUMENT_H
#define MUSIC_INSTRUMENT_H

class Instrument;

#include "AudioHost.h"

class Instrument {
public:
    Instrument(AudioHost *host);
    SineWave *wave;
    void render(float *buffer, uint32_t count);
};

#endif //MUSIC_INSTRUMENT_H
