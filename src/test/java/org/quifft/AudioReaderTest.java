package org.quifft;

import org.junit.Test;
import org.quifft.audioread.AudioReader;
import org.quifft.audioread.AudioReaderFactory;
import org.quifft.audioread.PCMFile;
import org.quifft.audioread.PCMReader;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.File;
import java.io.IOException;

import static org.junit.Assert.*;

public class AudioReaderTest {

    private static final File testAudio =
            TestUtils.getAudioFile("600hz-tone-3secs-mono.wav");
    private static final File testPcmAudio =
            TestUtils.getAudioFile("600hz-tone-3secs-mono.pcm");
    private static final int PCM_AUDIO_SAMPLE_RATE = 16000;
    private static final int PCM_AUDIO_SAMPLE_SIZE = 16;
    private static final int PCM_AUDIO_CHANNEL_SIZE = 1;
    private static final int PCM_AUDIO_DURATION = 3;

    @Test
    public void Should_Extract_Correct_Number_Of_Samples_From_WAV() throws IOException, UnsupportedAudioFileException {
        int SAMPLE_RATE = 44100;
        int AUDIO_DURATION_SEC = 3;
        int expectedSampleCount = SAMPLE_RATE * AUDIO_DURATION_SEC;

        AudioReader reader = new PCMReader(testAudio);
        int[] extractedSamples = reader.getWaveform();

        assertEquals(expectedSampleCount, extractedSamples.length);
    }

    @Test
    public void Should_Extract_Correct_Number_Of_Samples_From_RAW_PCM()
            throws IOException, UnsupportedAudioFileException {
        AudioFormat format = new AudioFormat(
                new AudioFormat.Encoding("PCM_SIGNED"), PCM_AUDIO_SAMPLE_RATE,
                PCM_AUDIO_SAMPLE_SIZE, PCM_AUDIO_CHANNEL_SIZE, 2,
                PCM_AUDIO_SAMPLE_RATE, false);

        AudioReader reader = new PCMReader(new PCMFile(testPcmAudio, format));
        int[] extractedSamples = reader.getWaveform();
        int expectedSampleCount = PCM_AUDIO_SAMPLE_RATE * PCM_AUDIO_DURATION;

        assertEquals(expectedSampleCount, extractedSamples.length);
    }

    @Test
    public void Instantiate_AudioReaderFactory_To_Make_Cobertura_Happy() {
        new AudioReaderFactory();
    }
}
