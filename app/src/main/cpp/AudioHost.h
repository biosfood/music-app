#ifndef MUSIC_AUDIO_HOST_H
#define MUSIC_AUDIO_HOST_H

class AudioHost;

#include "SineWave.h"
#include "Instrument.h"
#include <aaudio/AAudio.h>
#include <list>

class AudioHost {
private:
    AAudioStream *stream;
public:
    uint32_t sampleRate = 0;
    double masterVolume = 1.0f;
    std::list<Instrument *> *instruments = new std::list<Instrument *>();
    AudioHost();
};


#endif //MUSIC_AUDIO_HOST_H
