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
    id                 BIGINT AUTO_INCREMENT PRIMARY KEY,
    user_id            BIGINT      NOT NULL,
    table_id           BIGINT      NOT NULL,
    checked_in_time    DATETIME(6) NOT NULL,
    checked_out_time   DATETIME(6) NULL,
    is_checked_in      BOOLEAN     NOT NULL DEFAULT FALSE,
    is_checked_out     BOOLEAN     NOT NULL DEFAULT FALSE,
    reservation_status ENUM ('PENDING', 'CONFIRMED', 'COMPLETED', 'CANCELLED')
                                   NOT NULL DEFAULT 'COMPLETED',
    CONSTRAINT fk_reservation_user
        FOREIGN KEY (user_id) REFERENCES users (id)
            ON UPDATE CASCADE ON DELETE RESTRICT,
    CONSTRAINT fk_reservation_table
        FOREIGN KEY (table_id) REFERENCES reserving_table (id)
            ON UPDATE CASCADE ON DELETE RESTRICT
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_unicode_ci;