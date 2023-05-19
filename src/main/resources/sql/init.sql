CREATE TABLE exchange_log (
                            id SERIAL PRIMARY KEY,
                            date TIMESTAMP NOT NULL,
                            currency CHAR(3) NOT NULL CHECK (currency ~ '^[A-Z]{3}$'),
                            rate DECIMAL(10, 2) NOT NULL CHECK (rate > 0),
                            operation VARCHAR(50) NOT NULL,
                            amount DECIMAL(10, 2) NOT NULL CHECK (amount > 0),
                            status BOOLEAN NOT NULL DEFAULT TRUE
);

CREATE TABLE currency (
                            ccy VARCHAR(3) PRIMARY KEY CHECK (ccy ~ '^[A-Z]{3}$'),
                            base_ccy VARCHAR(3) CHECK (base_ccy ~ '^[A-Z]{3}$'),
                            buy DECIMAL(10, 5) CHECK (buy >= 0),
                            sale DECIMAL(10, 5) CHECK (sale >= 0)
);