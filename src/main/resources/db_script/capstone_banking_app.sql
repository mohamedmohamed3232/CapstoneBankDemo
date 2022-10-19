-- This is the SQL statement that helps set up the database
 DROP DATABASE IF EXISTS mo_bank;

 create DATABASE mo_bank;


 use mo_bank;

 -- USERS TABLE STRUCTURE:
 create TABLE users(
	user_id INT NOT NULL AUTO_INCREMENT PRIMARY KEY ,
    first_name VARCHAR(50) NOT NULL,
    last_name VARCHAR(50) NOT NULL,
    email VARCHAR(255) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    created_at TIMESTAMP DEFAULT NOW(),
    updated_at TIMESTAMP DEFAULT NOW()
 );


 -- BANK ACCOUNTS TABLE STRUCTURE:
 create TABLE accounts(
	 account_id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
     user_id INT,
     account_number VARCHAR(100) NOT NULL,
     account_name VARCHAR(50) NOT NULL,
     account_type VARCHAR(50) NOT NULL,
     balance DECIMAL(18, 2) DEFAULT 0.00,
     created_at TIMESTAMP DEFAULT NOW(),
     updated_at TIMESTAMP DEFAULT NOW(),
     FOREIGN KEY(user_id) REFERENCES users(user_id) ON delete CASCADE
 );


 -- TRANSACTION HISTORY TABLE:
 create TABLE transaction_history(
	transaction_id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    account_id INT,
    transaction_type VARCHAR(50) NOT NULL,
    amount DECIMAL(18, 2),
    source VARCHAR(50) NULL,
    status VARCHAR(50) NULL, -- success / failed
    reason_code VARCHAR(100) NULL, -- INSUFFICIENT FUNDS
    created_at TIMESTAMP DEFAULT NOW(),
    FOREIGN KEY(account_id) REFERENCES accounts(account_id) ON delete CASCADE
 );

 -- PAYMENTS TABLE STRUCTURE:
 create TABLE payments(
	payment_id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    account_id INT,
    beneficiary VARCHAR(50) NULL,
    beneficiary_acc_no VARCHAR(255) NULL,
    amount DECIMAL(18, 2) NULL,
    reference_no VARCHAR(100) NULL,
    status VARCHAR(50) NULL, -- success / failed
    reason_code VARCHAR(100) NULL, -- INSUFFICIENT FUNDS
    created_at TIMESTAMP DEFAULT NOW(),
    FOREIGN KEY(account_id) REFERENCES accounts(account_id) ON delete CASCADE
 );


 -- TRANSACTION HISTORY VIEW:
 create view v_transaction_history
 as
 select
	t.transaction_id,
    a.account_id,
    u.user_id,
    t.transaction_type,
    t.amount,
    t.source,
    t.status,
    t.reason_code,
    t.created_at
from
	transaction_history as t
inner join
	accounts as a
on
	t.account_id = a.account_id
inner join
	userS as u
on
	a.user_id = u.user_id;



 -- PAYMENT HISTORY VIEW:
 create view v_payments
 as
 select
	p.payment_id,
    a.account_id,
    u.user_id,
    p.beneficiary,
    p.beneficiary_acc_no,
    p.amount,
    p.status,
    p.reference_no,
    p.reason_code,
    p.created_at
from
 payments as p
inner join
	accounts as a
 on
	p.account_id = a.account_id
inner join
	users as u
on
	a.user_id = u.user_id;





 select * from v_transaction_history;

 select * from v_payments;















