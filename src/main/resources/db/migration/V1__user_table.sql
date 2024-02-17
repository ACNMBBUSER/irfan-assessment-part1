CREATE TABLE IF NOT EXISTS User (
    id INT AUTO_INCREMENT PRIMARY KEY,
    userName VARCHAR(255) NOT NULL,
    Email VARCHAR(255) NOT NULL,
    phone_number VARCHAR(255) NOT NULL
);

-- Insert data
INSERT INTO User (userName, Email, phone_number) VALUES
    ('Muhammad Irfan Zulkefly', 'irfanzulkefly144@gmail.com', '0193205891');