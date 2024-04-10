DROP TABLE IF EXISTS students;
DROP TABLE IF EXISTS promotions;

CREATE TABLE IF NOT EXISTS promotions (
    promotion_id SERIAL PRIMARY KEY,
    promotion_name VARCHAR(255) NOT NULL,
    promotion_year INTEGER NOT NULL
);

CREATE TABLE IF NOT EXISTS students (
    student_id SERIAL PRIMARY KEY,
    first_name VARCHAR(255) NOT NULL,
    last_name VARCHAR(255) NOT NULL,
    email VARCHAR(255) UNIQUE NOT NULL,
    phone_number VARCHAR(255),
    absence_count INTEGER DEFAULT 0,
    is_delegate BOOLEAN DEFAULT false,
    promotion_id INTEGER NOT NULL,
    UNIQUE(first_name, last_name),
    FOREIGN KEY (promotion_id) REFERENCES promotions(promotion_id)
);
