cmake_minimum_required(VERSION 3.18.1)

project("music")

add_library(
        music

        SHARED

        waveforms/Sine.cpp
        waveforms/Sawtooth.cpp
        waveforms/Waveform.cpp
        waveforms/Square.cpp
        waveforms/Triangle.cpp
        JavaFunctions.cpp
        AudioHost.cpp
        Instrument.cpp
        effects/Envelope.cpp
        effects/Processable.cpp
        effects/Effect.cpp
        effects/LowPass.cpp
        effects/Noise.cpp
        effects/Distortion.cpp
)

find_library(
        log-lib
        log)

target_link_libraries(
        music
        ${log-lib}
        aaudio
)