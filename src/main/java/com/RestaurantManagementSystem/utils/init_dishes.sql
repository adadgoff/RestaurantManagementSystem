CREATE TABLE dishes
(
    id          SERIAL PRIMARY KEY,
    name        VARCHAR(255) NOT NULL,
    description TEXT,
    price       INTEGER      NOT NULL,
    imageIds    INTEGER[],
    reviewIds   INTEGER[]
);

