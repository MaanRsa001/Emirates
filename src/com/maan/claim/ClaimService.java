package com.maan.claim;

import java.util.List;
import java.util.Map;

public class ClaimService {
	private ClaimDao dao=new ClaimDao();
	public void getclaimDetails(ClaimBean bean, String policyNo) {
		dao.getclaimDetails(bean, policyNo);
	}
	public Integer countPolicyNo(ClaimBean bean) {
		return dao.getCountPolicyNo(bean);
	}
	public void saveLossDetails(ClaimBean bean) {
		dao.saveLossDetails(bean);
	}
	public List<Map<String, Object>> getClaimIntimation(ClaimBean bean) {
		return dao.getClaimIntimation(bean);
	}
	public List<Map<String, Object>> getClaimPendingDetails(ClaimBean bean) {
		return dao.getClaimPendingDetails(bean);
	}
	public void getclaimIntimationDetails(ClaimBean bean) {
		dao.getclaimIntimationDetails(bean);
	}
	public int updateClaimIntimation(ClaimBean bean) {
		return dao.updateClaim(bean);
	}
	public int countClaimPolicyNo(ClaimBean bean) {
		return dao.countClaimPolicyNo(bean);
	}

}
