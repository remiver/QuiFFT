package org.quifft.audioread;

import java.io.File;

import javax.sound.sampled.AudioFormat;

/**
 * Wrapper for raw PCM(header-less) file
 */
public class PCMFile extends File {
    private final File mFile;
    private final AudioFormat mAudioFormat;

    public PCMFile(File file, AudioFormat format) {
        super(file.getAbsolutePath());
        this.mFile = file;
        this.mAudioFormat = format;
    }

    public File getFile() {
        return mFile;
    }

    public AudioFormat getAudioFormat() {
        return mAudioFormat;
    }
}
