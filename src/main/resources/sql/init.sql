CREATE TABLE exchange_log (
                            id SERIAL PRIMARY KEY,
                            date TIMESTAMP NOT NULL,
                            currency VARCHAR(3) NOT NULL CHECK (currency ~ '^[A-Z]{3}$'),
                            rate NUMERIC(10, 2) NOT NULL CHECK (rate > 0),
                            operation VARCHAR(50) NOT NULL,
                            amount DECIMAL(10, 2) NOT NULL CHECK (amount > 0),
                            status BOOLEAN NOT NULL DEFAULT TRUE
);

CREATE TABLE currency (
                            ccy VARCHAR(3) PRIMARY KEY CHECK (ccy ~ '^[A-Z]{3}$'),
                            base_ccy VARCHAR(3) CHECK (base_ccy ~ '^[A-Z]{3}$'),
                            buy NUMERIC(10, 5) CHECK (buy >= 0),
                            sale NUMERIC(10, 5) CHECK (sale >= 0)
);

INSERT INTO currency (ccy, base_ccy, buy, sale)
VALUES ('USD', 'UAH', 36.112, 37.223),
       ('EUR', 'UAH', 38.544, 39.311);

INSERT INTO exchange_log (date, currency, rate, operation, amount, status)
VALUES
    ('2021-06-10 10:00:00', 'USD', 31.5, 'buy', 100, true),
    ('2021-06-20 14:30:00', 'EUR', 41.2, 'sale', 200, true),
    ('2021-07-20 10:00:00', 'USD', 31.5, 'buy', 100, true),
    ('2021-07-10 14:30:00', 'EUR', 41.2, 'sale', 200, true),
    ('2021-08-20 10:00:00', 'USD', 31.5, 'buy', 100, true),
    ('2021-08-20 14:30:00', 'EUR', 41.2, 'sale', 200, true),
    ('2021-09-20 10:00:00', 'USD', 31.5, 'buy', 100, true),
    ('2021-09-20 14:30:00', 'EUR', 41.2, 'sale', 200, true),
    ('2021-10-20 10:00:00', 'USD', 31.5, 'buy', 100, true),
    ('2021-10-10 14:30:00', 'EUR', 41.2, 'sale', 200, true),
    ('2021-11-20 10:00:00', 'USD', 31.5, 'buy', 100, true),
    ('2021-11-20 14:30:00', 'EUR', 41.2, 'sale', 200, true),
    ('2021-12-20 10:00:00', 'USD', 31.5, 'buy', 100, true),
    ('2021-12-10 14:30:00', 'EUR', 41.2, 'sale', 200, true),
    ('2023-01-20 10:00:00', 'USD', 31.5, 'buy', 100, true),
    ('2023-01-20 14:30:00', 'EUR', 41.2, 'sale', 200, true),
    ('2023-02-20 10:00:00', 'USD', 31.5, 'buy', 100, true),
    ('2023-02-10 14:30:00', 'EUR', 41.2, 'sale', 200, true),
    ('2023-03-20 10:00:00', 'USD', 41.5, 'buy', 100, true),
    ('2023-03-20 14:30:00', 'EUR', 41.2, 'sale', 200, true),
    ('2023-04-10 10:00:00', 'USD', 41.5, 'buy', 100, true),
    ('2023-04-20 14:30:00', 'EUR', 41.2, 'sale', 200, true),
    ('2023-05-10 10:00:00', 'USD', 41.5, 'buy', 100, true),
    ('2023-05-20 14:30:00', 'EUR', 41.2, 'sale', 200, true),
    ('2023-06-10 10:00:00', 'USD', 31.5, 'buy', 100, true),
    ('2023-06-20 14:30:00', 'EUR', 41.2, 'sale', 200, true),
    ('2023-07-20 10:00:00', 'USD', 31.5, 'buy', 100, true),
    ('2023-07-10 14:30:00', 'EUR', 41.2, 'sale', 200, true),
    ('2023-08-20 10:00:00', 'USD', 31.5, 'buy', 100, true),
    ('2023-08-20 14:30:00', 'EUR', 41.2, 'sale', 200, true),
    ('2023-09-10 10:00:00', 'USD', 31.5, 'buy', 100, true),
    ('2023-09-20 14:30:00', 'EUR', 41.2, 'sale', 200, true),
    ('2023-10-10 10:00:00', 'USD', 31.5, 'buy', 100, true),
    ('2023-10-20 14:30:00', 'EUR', 41.2, 'sale', 200, true),
    ('2023-11-20 10:00:00', 'USD', 31.5, 'buy', 100, true),
    ('2023-11-10 14:30:00', 'EUR', 41.2, 'sale', 200, true),
    ('2023-12-20 10:00:00', 'USD', 31.5, 'buy', 100, true),
    ('2023-12-20 14:30:00', 'EUR', 41.2, 'sale', 200, true),
    ('2022-01-10 10:00:00', 'USD', 31.5, 'buy', 100, true),
    ('2022-01-20 14:30:00', 'EUR', 41.2, 'sale', 200, true),
    ('2022-02-20 10:00:00', 'USD', 41.5, 'buy', 100, true),
    ('2022-02-20 14:30:00', 'EUR', 41.2, 'sale', 200, true),
    ('2022-03-10 10:00:00', 'USD', 41.5, 'buy', 100, true),
    ('2022-03-20 14:30:00', 'EUR', 41.2, 'sale', 200, true),
    ('2022-04-20 10:00:00', 'USD', 41.5, 'buy', 100, true),
    ('2022-04-10 14:30:00', 'EUR', 41.2, 'sale', 200, true),
    ('2022-05-20 10:00:00', 'USD', 31.5, 'buy', 100, true),
    ('2022-05-20 14:30:00', 'EUR', 41.2, 'sale', 200, true);