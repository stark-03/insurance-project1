-- Create the policy table
CREATE TABLE IF NOT EXISTS policy (
    policy_id INT PRIMARY KEY AUTO_INCREMENT,
    policy_holder_name VARCHAR(255),
    policy_type VARCHAR(255),
    policy_price DOUBLE,
    policy_start_date VARCHAR(255),
    policy_end_date VARCHAR(255)
);

-- Insert the data
INSERT INTO policy (policy_holder_name, policy_type, policy_price, policy_start_date, policy_end_date)
VALUES ('Health Insurance', 'Health', 10000, '2023-01-01', '2024-01-01');

INSERT INTO policy (policy_holder_name, policy_type, policy_price, policy_start_date, policy_end_date)
VALUES ('Life Insurance', 'Life', 20000, '2023-02-01', '2024-02-01');
