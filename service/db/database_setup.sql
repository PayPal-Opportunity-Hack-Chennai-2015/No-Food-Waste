-- User table creation
CREATE SEQUENCE nfw_user_id_seq;

CREATE TABLE IF NOT EXISTS nfw_users (
  nfw_user_id BIGSERIAL NOT NULL,
  nfw_user_name VARCHAR(255) NOT NULL UNIQUE,
  nfw_user_phone_number INT,
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
  nfw_donor_phone_number INT,
  donation_food_type VARCHAR(32) NOT NULL UNIQUE,
  donation_quantity INT,
  donation_address VARCHAR(512),
  lat VARCHAR(9),
  long VARCHAR(9),
  donation_status VARCHAR(32),
  PRIMARY KEY(nfw_donation_id)
);

ALTER TABLE nfw_donations ALTER COLUMN nfw_donation_id SET DEFAULT NEXTVAL('nfw_donation_id_seq');

-- Food consumer location creation

CREATE SEQUENCE food_consumer_id_seq;

CREATE TABLE IF NOT EXISTS food_consumers (
  food_consumer_id BIGSERIAL NOT NULL,
  food_consumer_name VARCHAR(255),
  lat DECIMAL(9,6),
  long DECIMAL(9,6),
  is_active BOOLEAN NOT NULL,
  PRIMARY KEY(food_consumer_id)
);

ALTER TABLE food_consumers ALTER COLUMN food_consumer_id SET DEFAULT NEXTVAL('food_consumer_id_seq');