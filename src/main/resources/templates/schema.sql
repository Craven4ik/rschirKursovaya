CREATE SEQUENCE IF NOT EXISTS hibernate_sequence START WITH 1 INCREMENT BY 1;

CREATE TABLE owner
(
    owner_id INTEGER GENERATED BY DEFAULT AS IDENTITY NOT NULL,
    name     VARCHAR(255),
    email    VARCHAR(255),
    CONSTRAINT pk_owner PRIMARY KEY (owner_id)
);

CREATE TABLE contract
(
    contract_id INTEGER GENERATED BY DEFAULT AS IDENTITY NOT NULL,
    subject     VARCHAR(255),
    price       INTEGER                                  NOT NULL,
    "case"      VARCHAR(255),
    owner_id    INTEGER,
    CONSTRAINT pk_contract PRIMARY KEY (contract_id)
);

ALTER TABLE contract
    ADD CONSTRAINT FK_CONTRACT_ON_OWNER FOREIGN KEY (owner_id) REFERENCES owner (owner_id);