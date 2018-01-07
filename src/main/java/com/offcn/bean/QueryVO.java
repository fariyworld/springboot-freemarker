package com.offcn.bean;

import java.util.HashMap;
import java.util.Map;

public class QueryVO {

	private String result;

	private double[] voteArr;
	private String[] peoArr;

	private Map<Integer, Map<String, Double>> map = new HashMap<Integer, Map<String, Double>>();

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public double[] getVoteArr() {
		return voteArr;
	}

	public void setVoteArr(double[] voteArr) {
		this.voteArr = voteArr;
	}

	public String[] getPeoArr() {
		return peoArr;
	}

	public void setPeoArr(String[] peoArr) {
		this.peoArr = peoArr;
	}

	public Map<Integer, Map<String, Double>> getMap() {
		return map;
	}

	public void setMap(Map<Integer, Map<String, Double>> map) {
		this.map = map;
	}

}
