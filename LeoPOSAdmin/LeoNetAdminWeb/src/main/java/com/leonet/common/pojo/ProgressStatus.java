package com.leonet.common.pojo;

public class ProgressStatus {
	private long total;
	private long processed;
	private double percent;
	private String elapsedTime;
	private String eta;
	private boolean completed;
	private String totalTime;

	public ProgressStatus() {
	}

	public ProgressStatus(long total, long processed, double percent, String elapsedTime, String eta, boolean completed,
			String totalTime) {
		this.total = total;
		this.processed = processed;
		this.percent = percent;
		this.elapsedTime = elapsedTime;
		this.eta = eta;
		this.completed = completed;
		this.totalTime = totalTime;
	}

	public long getTotal() {
		return total;
	}

	public long getProcessed() {
		return processed;
	}

	public double getPercent() {
		return percent;
	}

	public String getElapsedTime() {
		return elapsedTime;
	}

	public String getEta() {
		return eta;
	}

	public boolean isCompleted() {
		return completed;
	}

	public String getTotalTime() {
		return totalTime;
	}
}
