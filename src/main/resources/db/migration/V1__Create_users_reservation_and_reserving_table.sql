-- Create 'users' table
CREATE TABLE IF NOT EXISTS users
(
    id       BIGINT AUTO_INCREMENT PRIMARY KEY,
    email    VARCHAR(255) NOT NULL,
    password VARCHAR(255) NOT NULL,
    role     VARCHAR(50)  NOT NULL DEFAULT 'ROLE_USER',
    username VARCHAR(255)
);

-- Create 'reserving_table' table (Note the corrected table name)
CREATE TABLE IF NOT EXISTS reserving_table
(
    id       BIGINT AUTO_INCREMENT PRIMARY KEY,
    number   INT                                                NOT NULL,
    capacity INT                                                NOT NULL,
    pph      DOUBLE                                             NOT NULL,
    status   ENUM ('AVAILABLE', 'BOOKED', 'OCCUPIED', 'CLOSED') NOT NULL DEFAULT 'AVAILABLE'
);

-- Create 'reservation' table
CREATE TABLE IF NOT EXISTS reservation
(
    id          BIGINT AUTO_INCREMENT PRIMARY KEY,
    user_id     BIGINT    NOT NULL,
    table_id    BIGINT    NOT NULL,
    start_time  TIMESTAMP NOT NULL,
    end_time    TIMESTAMP NOT NULL,
    checked_in  BOOLEAN DEFAULT FALSE,
    checked_out BOOLEAN DEFAULT FALSE,
    FOREIGN KEY (user_id) REFERENCES users (id),
    FOREIGN KEY (table_id) REFERENCES reserving_table (id)
);
