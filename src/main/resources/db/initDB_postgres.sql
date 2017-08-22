DROP TABLE IF EXISTS users;
DROP TABLE IF EXISTS comments;
DROP TABLE IF EXISTS orders_products;
DROP TABLE IF EXISTS orders;
DROP TABLE IF EXISTS products;

CREATE TABLE products (
  id          SERIAL PRIMARY KEY,
  name        TEXT NOT NULL,
  price       NUMERIC(10,2),
  color       INTEGER,
  capacity    INTEGER,
  display     TEXT,
  description TEXT
);

CREATE TABLE orders (
  id            SERIAL PRIMARY KEY,
  total_amount  NUMERIC(10,2) NOT NULL,
  datetime      TIMESTAMP WITH TIME ZONE NOT NULL,
  name          TEXT NOT NULL,
  email         TEXT NOT NULL,
  phone         TEXT,
  address       TEXT NOT NULL
);

CREATE TABLE orders_products (
  order_id    INTEGER NOT NULL,
  product_id  INTEGER NOT NULL,
  FOREIGN KEY (order_id) REFERENCES orders (id) ON DELETE CASCADE,
  FOREIGN KEY (product_id) REFERENCES products (id) ON DELETE CASCADE
);

CREATE TABLE comments (
  id          SERIAL PRIMARY KEY,
  product_id  INTEGER NOT NULL,
  author      TEXT NOT NULL,
  datetime    TIMESTAMP WITH TIME ZONE NOT NULL,
  text        TEXT,
  rating      INTEGER,
  FOREIGN KEY (product_id) REFERENCES products (id) ON DELETE CASCADE
);

CREATE TABLE users (
  id          SERIAL PRIMARY KEY,
  email       TEXT NOT NULL UNIQUE,
  password    TEXT NOT NULL,
  name        TEXT NOT NULL,
  role        INTEGER NOT NULL
);