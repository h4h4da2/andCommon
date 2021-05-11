package com.hhda.andcommon.util.upload;

interface ProgressListener {
    void update(long bytesRead, long contentLength, boolean done);
}