CREATE TABLE Prices
(
    price_list BIGINT PRIMARY KEY,
    product_id BIGINT         NOT NULL,
    brand_id   BIGINT         NOT NULL,
    priority   INT            NOT NULL,
    price      DECIMAL(19, 2) NOT NULL,
    curr       VARCHAR(3)     NOT NULL,
    start_date DATETIME       NOT NULL,
    end_date   DATETIME       NOT NULL
);