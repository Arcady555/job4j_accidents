CREATE TABLE IF NOT EXISTS accidents (
   id SERIAL PRIMARY KEY,
   name VARCHAR,
   text VARCHAR,
   address VARCHAR,
   created TIMESTAMP
);