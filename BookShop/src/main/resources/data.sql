CREATE TABLE IF NOT EXISTS Author
(
    id
    UUID
    PRIMARY
    KEY,
    name
    VARCHAR
(
    255
)
    );
CREATE TABLE IF NOT EXISTS Book
(
    id
    UUID
    PRIMARY
    KEY,
    title
    VARCHAR
(
    255
),
    author_id UUID,
    genre VARCHAR
(
    255
),
    price DOUBLE PRECISION,
    pages INTEGER,
    viewCount INTEGER DEFAULT 0,
    availableCopies INTEGER,
    FOREIGN KEY
(
    author_id
) REFERENCES Author
(
    id
)
    );

INSERT INTO Author (id, name)
VALUES ('123e4567-e89b-12d3-a456-426614174000', 'John Doe');
INSERT INTO Book (id, title, author_id, genre, price, pages, viewCount, availableCopies)
VALUES ('223e4567-e89b-12d3-a456-426614174001', 'Sample Book', '123e4567-e89b-12d3-a456-426614174000', 'Fiction', 19.99,
        350, 0, 10);
