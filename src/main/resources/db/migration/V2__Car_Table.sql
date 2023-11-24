CREATE TABLE cars(
    id BIGSERIAL PRIMARY KEY,
    model VARCHAR(100) NOT NULL,
    year INTEGER NOT NULL,
    brand_id BIGINT REFERENCES brands(id)
)