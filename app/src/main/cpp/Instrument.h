#ifndef MUSIC_INSTRUMENT_H
#define MUSIC_INSTRUMENT_H

class Instrument;

#include "effects/Envelope.h"
#include "waveforms/Waveform.h"
#include "AudioHost.h"
#include "effects/LowPass.h"

class Instrument {
private:
    AudioHost *host;
public:
    Instrument(AudioHost *host);

    Envelope *const envelope = new Envelope();
    Waveform *wave;
    LowPass *lowPass = new LowPass();

    void render(float *buffer, uint32_t count);
    void startNote(float frequency);
    void endNote();
    void setWaveform(WaveformType waveform);
};

#endif
