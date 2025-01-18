@Service
public class PolicyService {

    @Autowired
    private PolicyRepository policyRepository;

    public Policy createPolicy() {
        Policy policy = generateDummyPolicy();
        return policyRepository.save(policy);
    }

    @Transactional
    public Policy updatePolicy(Policy updatedPolicy) {
        if (!policyRepository.existsById(updatedPolicy.getPolicyId())) {
            throw new IllegalArgumentException("Policy ID not found: " + updatedPolicy.getPolicyId());
        }
        return policyRepository.save(updatedPolicy);
    }

    public boolean deletePolicy(Integer policyId) {
        if (!policyRepository.existsById(policyId)) {
            throw new IllegalArgumentException("Policy ID not found: " + policyId);
        }
        policyRepository.deleteById(policyId);
        return true;
    }

    public Policy searchPolicy(Integer policyId) {
        return policyRepository.findById(policyId)
                .orElseThrow(() -> new IllegalArgumentException("Policy ID not found: " + policyId));
    }

    private Policy generateDummyPolicy() {
        return new Policy(0, "Shubham", "Individual", 10000, "10-Sep-2021", "10-Sep-2022");
    }

    public Policy registerPolicy(Policy policy) {
        if (policy.getPolicyHolderName() == null || policy.getPolicyHolderName().isEmpty()) {
            throw new IllegalArgumentException("Policy holder name cannot be null or empty");
        }
        return policyRepository.save(policy);
    }

    public Policy getPolicyDetails(Integer policyId) {
        return policyRepository.findById(policyId)
                .orElseThrow(() -> new IllegalArgumentException("Policy ID not found: " + policyId));
    }
}
