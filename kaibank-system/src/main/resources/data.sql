insert into kaibank.bank (id, address, name, physical_amount, virtual_amount)
VALUES (1, 'Ratuse 23, 50603, KaiBank', 'KaiBank', 0.0, 10000000);

INSERT INTO kaibank.branch
(id, created_by_user, created_time, version_id, address, name, bank_id)
VALUES (1, 'SYSTEM', curdate() , 0, 'Ratuse 23, 50603, KaiBank, Tartu', 'Tartu', 1);

INSERT INTO kaibank.branch
(id, created_by_user, created_time, version_id, address, name, bank_id)
VALUES (2, 'SYSTEM', curdate() , 0, 'Narva mnt 25, 10120, KaiBank, Tallinn', 'Tallinn', 1);

INSERT INTO kaibank.role
(id, created_by_user, created_time, version_id, role)
VALUES (1, 'SYSTEM', curdate(), 0, 'ROLE_ADMIN');

INSERT INTO kaibank.role
(id, created_by_user, created_time, version_id, role)
VALUES (2, 'SYSTEM', curdate(), 0, 'ROLE_MANAGER');

INSERT INTO kaibank.role
(id, created_by_user, created_time, version_id, role)
VALUES (3, 'SYSTEM', curdate(), 0, 'ROLE_CUSTOMER');

INSERT INTO kaibank.customer
(id, created_by_user, created_time, version_id, address, attempts, date_of_birth, email, id_no, name, password, phone_no, pin, sex, user_status, username, is_first_login)
VALUES(1, 'SYSTEM', curdate(), 0, 'Narva mnt 25', 0, '1990-11-25 00:00:00', 'ann@gmail.com', '123456', 'Ann', '$2a$10$lB6/PKg2/JC4XgdMDXyjs.dLC9jFNAuuNbFkL9udcXe/EBjxSyqxW', '+37123456', 1234, 'FEMALE', 'ACTIVE', 'ann', 0);

INSERT INTO kaibank.customer_role
(role_id, customer_id)
VALUES(3, 1);

INSERT INTO kaibank.employee
(id, created_by_user, created_time, version_id, attempts, date_of_birth, email, name, password, phone_no, sex, user_status, username, is_first_login, branch_id)
VALUES(1, 'SYSTEM', curdate(), 0, 0, '1987-10-22 00:00:00', 'admin@gmail.com', 'Admin', '$2a$10$lB6/PKg2/JC4XgdMDXyjs.dLC9jFNAuuNbFkL9udcXe/EBjxSyqxW', '+37123456', 'FEMALE', 'ACTIVE', 'admin', 0, 1);

INSERT INTO kaibank.employee_role
(role_id, employee_id)
VALUES(1, 1);

INSERT INTO kaibank.customer
(id, created_by_user, created_time, version_id, address, attempts, date_of_birth, email, id_no, name, password, phone_no, pin, sex, user_status, username)
VALUES(2, 'SYSTEM', curdate(), 0, 'Narva mnt 25', 0, '1990-11-25 00:00:00', 'ann2@gmail.com', '123456', 'Ann2', '$2a$10$lB6/PKg2/JC4XgdMDXyjs.dLC9jFNAuuNbFkL9udcXe/EBjxSyqxW', '+37123456', 1234, 'MALE', 'ACTIVE', 'ann2');

INSERT INTO kaibank.account
(id, created_by_user, created_time, modified_by_user, modified_time, version_id, account_no, account_status, account_type, available_amount, iban, name, open_date, total_amount, branch_id, customer_id)
VALUES(1,	'SYSTEM',	curdate(),	NULL,	NULL,	0,	'111111',	'ACTIVE',	'SAVINGS',	0,	'EE11110000',	'ARMUD',	curdate(),	0,	1,	1);

INSERT INTO kaibank.transaction
(id, created_by_user, created_time, modified_by_user, modified_time, version_id, amount, message, prev_trans_id, recipient_id, transaction_date, transaction_status, transaction_type, account_id, customer_id)
VALUES(2,	'SYSTEM',	curdate(),	NULL,	NULL,	0,	1500,	NULL,	NULL,	NULL,	curdate(),	'HOLD',	'DEPOSIT',	1,	1);