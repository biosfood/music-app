#ifndef MUSIC_INSTRUMENT_H
#define MUSIC_INSTRUMENT_H

class Instrument;

#include "SineWave.h"
#include "AudioHost.h"
#include "Envelope.h"

class Instrument {
public:
    Instrument(AudioHost *host);

    Envelope *const envelope = new Envelope();
    SineWave *const wave = new SineWave();

    void render(float *buffer, uint32_t count);

    void startNote(float frequency);

    void endNote(float frequency);
};

#endif
