cmake_minimum_required(VERSION 3.18.1)

project("music")

add_library(
        music

        SHARED

        waveforms/Sine.cpp
        waveforms/Sawtooth.cpp
        JavaFunctions.cpp
        AudioHost.cpp
        Instrument.cpp
        Envelope.cpp
)

find_library(
        log-lib
        log)

target_link_libraries(
        music
        ${log-lib}
        aaudio
)