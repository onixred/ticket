CREATE TABLE tickets (
  id SERIAL PRIMARY KEY,
  task_name VARCHAR(255),
  description VARCHAR(255),
  status INTEGER REFERENCES status(id),
  author INTEGER REFERENCES users(id),
  executor INTEGER REFERENCES users(id)
);
