-- Create the Experience table
CREATE TABLE Experience (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    Position VARCHAR(255),
    Company VARCHAR(255),
    start_date VARCHAR(255),
    end_date VARCHAR(255),
    Responsibilities TEXT,
    user_id INT,
    FOREIGN KEY (user_id) REFERENCES User(id)
);

-- Insert data into the Experience table
INSERT INTO Experience (Position, Company, start_date, end_date, Responsibilities, user_id) 
VALUES ('Junior System Engineer', 'Heitech Padu Berhad', 'December 2017', 'March 2020', 'During my tenure in LAN Operation from October 2019 to March 2020, my responsibilities primarily revolved around optimizing existing Wi-Fi services, offering comprehensive support for network, server, and application troubleshooting, and ensuring the service portal remained updated and functional. With a focus on LAN infrastructure, server management, and programming tasks, I also undertook the creation of user manuals and technical references for system users. Prior to this, in my role within Network Security R&D from December 2017 to October 2019, I engaged in various facets of research and development, including the development and enhancement of web application systems and the creation of product prototypes. Additionally, I provided crucial support in troubleshooting network and backend server issues, conducted internal data backups, and maintained service portals. My responsibilities also encompassed generating detailed reports to document the progress and outcomes of system development efforts.', 1);
