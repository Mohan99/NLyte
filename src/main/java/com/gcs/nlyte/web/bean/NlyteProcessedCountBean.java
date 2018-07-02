package com.gcs.nlyte.web.bean;

public class NlyteProcessedCountBean {

	private int custStgId;
	private long matchCount;
	private long unMatchCount;
	private long multiMatchedCount;
	public int getCustStgId() {
		return custStgId;
	}
	public void setCustStgId(int custStgId) {
		this.custStgId = custStgId;
	}
	public long getMatchCount() {
		return matchCount;
	}
	public void setMatchCount(long matchCount) {
		this.matchCount = matchCount;
	}
	public long getUnMatchCount() {
		return unMatchCount;
	}
	public void setUnMatchCount(long unMatchCount) {
		this.unMatchCount = unMatchCount;
	}
	public long getMultiMatchedCount() {
		return multiMatchedCount;
	}
	public void setMultiMatchedCount(long multiMatchedCount) {
		this.multiMatchedCount = multiMatchedCount;
	}
	@Override
	public String toString() {
		return "NlyteProcessedCountBean [custStgId=" + custStgId + ", matchCount=" + matchCount + ", unMatchCount="
				+ unMatchCount + ", multiMatchedCount=" + multiMatchedCount + "]";
	}
	
	
}
