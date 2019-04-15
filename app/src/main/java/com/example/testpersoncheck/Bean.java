package com.example.testpersoncheck;

public class Bean {
    private float confidence;

    private float[] thresholds;

    private int[] rectA;

    private int[]rectB;

    private int errno;

    private String request_id;

    public float getConfidence() {
        return confidence;
    }

    public void setConfidence(float confidence) {
        this.confidence = confidence;
    }

    public void setThresholds(float[] thresholds) {
        this.thresholds = thresholds;
    }

    public void setRectA(int[] rectA) {
        this.rectA = rectA;
    }

    public void setRectB(int[] rectB) {
        this.rectB = rectB;
    }

    public void setErrno(int errno) {
        this.errno = errno;
    }

    public void setRequest_id(String request_id) {
        this.request_id = request_id;
    }

    public float[] getThresholds() {
        return thresholds;
    }

    public int[] getRectA() {
        return rectA;
    }

    public int[] getRectB() {
        return rectB;
    }

    public int getErrno() {
        return errno;
    }

    public String getRequest_id() {
        return request_id;
    }
}
