#include "AudioHost.h"
#include <android/log.h>
#include <thread>
#include <mutex>

const uint32_t bufferSize = 2;
aaudio_data_callback_result_t dataCallback(
        AAudioStream *stream,
        void *userData,
        void *audioData,
        int32_t sampleCount) {
    float *buffer = static_cast<float *>(audioData);
    for (uint32_t i = 0; i < sampleCount; i++) {
        buffer[i] = 0;
    }
    AudioHost *thiz = static_cast<AudioHost *>(userData);
    for (auto const &instrument: *thiz->instruments) {
        instrument->render(buffer, sampleCount);
    }
    return AAUDIO_CALLBACK_RESULT_CONTINUE;
}

void errorCallback(AAudioStream *stream, void *data, aaudio_result_t error) {
    __android_log_print(ANDROID_LOG_ERROR, "AudioHost", "an error occurred");
}

AudioHost::AudioHost() {
    AAudioStreamBuilder *streamBuilder;
    AAudio_createStreamBuilder(&streamBuilder);
    AAudioStreamBuilder_setFormat(streamBuilder, AAUDIO_FORMAT_PCM_FLOAT);
    AAudioStreamBuilder_setChannelCount(streamBuilder, 1);
    AAudioStreamBuilder_setPerformanceMode(streamBuilder, AAUDIO_PERFORMANCE_MODE_LOW_LATENCY);
    AAudioStreamBuilder_setDataCallback(streamBuilder, ::dataCallback, this);
    AAudioStreamBuilder_setErrorCallback(streamBuilder, ::errorCallback, this);

    aaudio_result_t result = AAudioStreamBuilder_openStream(streamBuilder, &stream);
    if (result != AAUDIO_OK) {
        __android_log_print(ANDROID_LOG_ERROR, "AudioHost", "Error opening stream %s",
                            AAudio_convertResultToText(result));
        return;
    }

    this->sampleRate = AAudioStream_getSampleRate(stream);

    AAudioStream_setBufferSizeInFrames(
            stream, AAudioStream_getFramesPerBurst(stream) * bufferSize);

    result = AAudioStream_requestStart(stream);
    if (result != AAUDIO_OK) {
        __android_log_print(ANDROID_LOG_ERROR, "AudioHost", "Error starting stream %s",
                            AAudio_convertResultToText(result));
        return;
    }

    AAudioStreamBuilder_delete(streamBuilder);
}