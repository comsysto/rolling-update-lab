--liquibase formatted sql
--changeset apodznoev:1
CREATE TABLE coffee_fans
(
    username         VARCHAR(255) NOT NULL PRIMARY KEY,
    favourite_coffee VARCHAR(255) NOT NULL,
    cups_drunk        int          NOT NULL,
    app_version      VARCHAR(255) NOT NULL
);

CREATE SEQUENCE username_id_seq START 1;

--rollback drop table coffee_fans;
--rollback drop sequence username_id_seq;