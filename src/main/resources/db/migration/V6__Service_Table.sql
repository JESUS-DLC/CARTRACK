CREATE TABLE services(
   id BIGSERIAL PRIMARY KEY,
   name VARCHAR(100) NOT NULL,
   description TEXT,
   cost numeric(20,2) NOT NULL,
   date DATE NOT NULL,
   mileage BIGINT NOT NULL,
   place VARCHAR(255) NOT NULL,
   status BOOLEAN NOT NULL,
   car_id BIGINT REFERENCES cars(id) ON DELETE CASCADE
);