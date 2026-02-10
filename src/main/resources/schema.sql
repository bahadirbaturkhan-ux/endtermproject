CREATE TABLE IF NOT EXISTS customers (
                                         id SERIAL PRIMARY KEY,
                                         name VARCHAR(100) NOT NULL,
    email VARCHAR(120) UNIQUE NOT NULL,
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
    );

CREATE TABLE IF NOT EXISTS accounts (
                                        id SERIAL PRIMARY KEY,
                                        account_number VARCHAR(30) UNIQUE NOT NULL,
    balance NUMERIC(12,2) NOT NULL CHECK (balance >= 0),
    type VARCHAR(20) NOT NULL,
    customer_id INT NOT NULL REFERENCES customers(id) ON DELETE CASCADE,
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
    );

CREATE INDEX IF NOT EXISTS idx_accounts_customer_id ON accounts(customer_id);
CREATE INDEX IF NOT EXISTS idx_accounts_type ON accounts(type);
