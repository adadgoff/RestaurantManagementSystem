CREATE TABLE Dishes
(
    Id          SERIAL PRIMARY KEY,
    Name        VARCHAR(255) NOT NULL,
    Description TEXT         NOT NULL,
    Price       INTEGER      NOT NULL,
    ImageIds    INTEGER[],
    ReviewIds   INTEGER[]
);

