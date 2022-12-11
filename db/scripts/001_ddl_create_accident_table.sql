CREATE TABLE IF NOT EXISTS accident (
   id SERIAL PRIMARY KEY,
   type INT,
   name VARCHAR,
   text VARCHAR,
   address VARCHAR,
   created TIMESTAMP WITHOUT TIME ZONE DEFAULT now()
);