package com.project.staragile.insureme;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class InsureMeApplicationTests {

    @Autowired
    private PolicyService policyService;

    @Test
    void contextLoads() {
        assertNotNull(policyService, "PolicyService should be auto-wired and not null");
    }

    @Test
    void testCreatePolicy() {
        Policy policy = new Policy(0, "Shubham", "Individual", 10000, "10-Sep-2021", "10-Sep-2022");
        Policy createdPolicy = policyService.registerPolicy(policy);
        assertNotNull(createdPolicy.getPolicyId(), "Policy ID should be auto-generated and not null");
        assertEquals("Shubham", createdPolicy.getPolicyHolderName());
    }

    @Test
    void testSearchPolicy() {
        int nonExistentPolicyId = 999;
        Policy policy = policyService.searchPolicy(nonExistentPolicyId);
        assertEquals(null, policy, "Search should return null for a non-existent policy");
    }
}
