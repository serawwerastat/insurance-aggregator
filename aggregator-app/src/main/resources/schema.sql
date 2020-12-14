DROP TABLE IF EXISTS users;
CREATE TABLE insurers
(
    id       INT          NOT NULL AUTO_INCREMENT,
    name     VARCHAR(255) NOT NULL,
    insurer_url VARCHAR(255) NOT NULL,
    premium_path VARCHAR(255) NOT NULL,
    insurer_type VARCHAR(255) NOT NULL,
    is_active     boolean      NOT NULL,
    PRIMARY KEY (id)
);