-- User table creation
CREATE SEQUENCE nfw_user_id_seq;

CREATE TABLE IF NOT EXISTS nfw_users (
  nfw_user_id BIGSERIAL NOT NULL,
  nfw_user_name VARCHAR(255) NOT NULL,
  nfw_user_phone_number VARCHAR(10) NOT NULL UNIQUE,
  is_volunteer BOOLEAN NOT NULL,
  device_id VARCHAR(64) NOT NULL,
  device_token VARCHAR(64) NOT NULL,
  PRIMARY KEY(nfw_user_id)
);

ALTER TABLE nfw_users ALTER COLUMN nfw_user_id SET DEFAULT NEXTVAL('nfw_user_id_seq');

-- Donation table creation

CREATE SEQUENCE nfw_donation_id_seq;

CREATE TABLE IF NOT EXISTS nfw_donations (
  nfw_donation_id BIGSERIAL NOT NULL,
  nfw_donor_phone_number VARCHAR(10),
  donation_food_type VARCHAR(32) NOT NULL,
  donation_quantity VARCHAR(5),
  donation_address VARCHAR(512),
  lat VARCHAR(20),
  long VARCHAR(20),
  donation_status VARCHAR(32),
  PRIMARY KEY(nfw_donation_id)
);

ALTER TABLE nfw_donations ALTER COLUMN nfw_donation_id SET DEFAULT NEXTVAL('nfw_donation_id_seq');

-- Food consumer location creation

CREATE SEQUENCE consumer_id_seq;

CREATE TABLE IF NOT EXISTS nfw_consumers (
  consumer_id BIGSERIAL NOT NULL,
  consumer_name VARCHAR(255),
  consumer_phone_number VARCHAR(10),
  consumer_quantity VARCHAR(5),
  consumer_address VARCHAR(512),
  lat VARCHAR(20),
  long VARCHAR(20),
  is_active BOOLEAN NOT NULL,
  PRIMARY KEY(consumer_id)
);

ALTER TABLE nfw_consumers ALTER COLUMN consumer_id SET DEFAULT NEXTVAL('consumer_id_seq');