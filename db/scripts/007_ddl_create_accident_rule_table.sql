CREATE TABLE IF NOT EXISTS accident_rule (
   PRIMARY KEY (accident_id, rule_id),
   accident_id int not null REFERENCES accident(id),
   rule_id int not null REFERENCES rule(rule_id)
);