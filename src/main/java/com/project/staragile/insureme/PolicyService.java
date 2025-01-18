package com.project.staragile.insureme;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PolicyService {

    @Autowired
    PolicyRepository policyRepository;
    
    public Policy createPolicy() {
        Policy policy = generateDummyPolicy();
        return policyRepository.save(policy);  // Ensure it returns the saved Policy object
    }

    public Policy updatePolicy(Policy updatedPolicy) {
        return policyRepository.save(updatedPolicy);  // Save the updated Policy object
    }
    
    public boolean deletePolicy(Long policyId) {
        if (policyRepository.existsById(policyId)) {
            policyRepository.deleteById(policyId);
            return true;
        }
        return false;
    }
    
    public Policy searchPolicy(Long policyId) {
        return policyRepository.findById(policyId).orElse(null);  // Return Policy if found, otherwise null
    }

    public Policy generateDummyPolicy() {
        return new Policy(1L, "Shubham", "Individual", 10000, "10-Sep-2021", "10-Sep-2022");  // Use Long for IDs
    }

    public Policy registerPolicy(Policy policy) {
        return policyRepository.save(policy);  // Save the Policy and return it
    }

    public Policy getPolicyDetails(Long policyId) {
        return policyRepository.findById(policyId).orElse(null);  // Return Policy details if found, otherwise null
    }
}
