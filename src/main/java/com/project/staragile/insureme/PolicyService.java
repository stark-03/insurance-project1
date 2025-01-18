package com.project.staragile.insureme;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PolicyService {
    
    @Autowired
    private PolicyRepository policyRepository;
    
    public Policy createPolicy() {
        Policy policy = generateDummyPolicy();
        return policyRepository.save(policy);
    }

    public Policy updatePolicy(Policy updatedPolicy) {
        return policyRepository.save(updatedPolicy);
    }
    
    public boolean deletePolicy(Integer policyId) {
        if (policyRepository.existsById(policyId)) {
            policyRepository.deleteById(policyId);
            return true;
        }
        return false;
    }
    
    public Policy searchPolicy(Integer policyId) {
        return policyRepository.findById(policyId).orElse(null);
    }

    public Policy generateDummyPolicy() {
        return new Policy(1, "Shubham", "Individual", 10000, "10-Sep-2021", "10-Sep-2022");
    }

    public Policy registerPolicy(Policy policy) {
        return policyRepository.save(policy);
    }

    public Policy getPolicyDetails(Integer policyId) {
        return policyRepository.findById(policyId).orElse(null);
    }
}
