package com.github.teren4m.blockcanary;

import android.content.Context;

import java.io.File;

public interface IBlockCanaryContext {

    int getConfigBlockThreshold();

    boolean isNeedDisplay();

    String getQualifier();

    String getUid();

    String getNetworkType();

    Context getContext();

    String getLogPath();

    boolean zipLogFile(File[] src, File dest);

    void uploadLogFile(File zippedFile);

    String getStackFoldPrefix();
}
