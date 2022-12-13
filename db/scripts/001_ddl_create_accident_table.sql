CREATE TABLE IF NOT EXISTS accident (
   id SERIAL PRIMARY KEY,
   name VARCHAR,
   text VARCHAR,
   address VARCHAR,
   created TIMESTAMP WITHOUT TIME ZONE DEFAULT now()
);