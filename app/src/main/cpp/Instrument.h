#ifndef MUSIC_INSTRUMENT_H
#define MUSIC_INSTRUMENT_H

class Instrument;

#include "AudioHost.h"
#include "Envelope.h"

class Instrument {
private:
    AudioHost *host;
public:
    Instrument(AudioHost *host);

    Envelope *const envelope = new Envelope();
    Waveform *wave = new Sine();

    void render(float *buffer, uint32_t count);

    void startNote(float frequency);

    void endNote();

    void setWaveform(WaveformType waveform);
};

#endif
