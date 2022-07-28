cmake_minimum_required(VERSION 3.18.1)

project("music")

add_library(
        music

        SHARED

        SineWave.cpp
        AndroidBridge.cpp
        AudioEngine.cpp
)

find_library(
        log-lib
        log)

target_link_libraries(
        music
        ${log-lib}
        aaudio
)