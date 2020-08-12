INSERT INTO insurance_types (name) values ('Страхование дома');
INSERT INTO insurance_types (name) values ('Страхование авто');
INSERT INTO insurance_types (name) values ('Страхование жизни');
INSERT INTO insurance_types (name) values ('Страхование имущества');



INSERT INTO contracts (reg_number, insurance_type_id, insurance_amount, insurance_payment)
VALUES ('2020/1', 1, 200000, 222);

INSERT INTO contracts (reg_number, insurance_type_id, insurance_amount, insurance_payment)
VALUES ('2020/2', 2, 300000, 333);

INSERT INTO contracts (reg_number, insurance_type_id, insurance_amount, insurance_payment)
VALUES ('2020/3', 3, 400000, 4444);

INSERT INTO contracts (reg_number, insurance_type_id, insurance_amount, insurance_payment)
VALUES ('2020/4', 4, 500000, 45000.34);

INSERT INTO contracts (reg_number, insurance_type_id, insurance_amount, insurance_payment)
VALUES ('2020/1/1', 1, 200000, 222);

INSERT INTO contracts (reg_number, insurance_type_id, insurance_amount, insurance_payment)
VALUES ('2020/2/1', 2, 300000, 333);

INSERT INTO contracts (reg_number, insurance_type_id, insurance_amount, insurance_payment)
VALUES ('2020/3/1', 3, 400000, 4444);

INSERT INTO contracts (reg_number, insurance_type_id, insurance_amount, insurance_payment)
VALUES ('2020/4/1', 4, 500000, 45000.34);

INSERT INTO contracts (reg_number, insurance_type_id, insurance_amount, insurance_payment)
VALUES ('2020/5', 1, 200000, 151000);

INSERT INTO contracts (reg_number, insurance_type_id, insurance_amount, insurance_payment)
VALUES ('2020/6', 2, 300000, 205000);

INSERT INTO contracts (reg_number, insurance_type_id, insurance_amount, insurance_payment)
VALUES ('2020/7', 3, 400000, 30000);

INSERT INTO contracts (reg_number, insurance_type_id, insurance_amount, insurance_payment)
VALUES ('2020/8', 4, 500000, 45000.34);

INSERT INTO contracts (reg_number, insurance_type_id, insurance_amount, insurance_payment)
VALUES ('2020/9', 1, 200000, 15000);

INSERT INTO contracts (reg_number, insurance_type_id, insurance_amount, insurance_payment)
VALUES ('2020/10', 2, 300000, 20000);

INSERT INTO contracts (reg_number, insurance_type_id, insurance_amount, insurance_payment)
VALUES ('2020/11', 3, 400000, 30000);

INSERT INTO contracts (reg_number, insurance_type_id, insurance_amount, insurance_payment)
VALUES ('2020/12', 4, 500000, 45000.34);

INSERT INTO contracts (reg_number, insurance_type_id, insurance_amount, insurance_payment)
VALUES ('2020/13', 1, 200000, 15000);

INSERT INTO contracts (reg_number, insurance_type_id, insurance_amount, insurance_payment)
VALUES ('2020/14', 2, 300000, 20000);

INSERT INTO contracts (reg_number, insurance_type_id, insurance_amount, insurance_payment)
VALUES ('2020/15', 3, 400000, 30000);

INSERT INTO contracts (reg_number, insurance_type_id, insurance_amount, insurance_payment)
VALUES ('2020/16', 4, 300000, 12000.2);

INSERT INTO contracts (reg_number, insurance_type_id, insurance_amount, insurance_payment)
VALUES ('2020/17', 1, 200000, 15000);

INSERT INTO contracts (reg_number, insurance_type_id, insurance_amount, insurance_payment)
VALUES ('2020/18', 2, 300000, 20000);

INSERT INTO contracts (reg_number, insurance_type_id, insurance_amount, insurance_payment)
VALUES ('2020/19', 3, 400000, 30000);

INSERT INTO contracts (reg_number, insurance_type_id, insurance_amount, insurance_payment)
VALUES ('2020/20', 4, 500000, 45000.34);

INSERT INTO users (username, password)
VALUES ('testUser', '$2a$04$Mssud3YK3ghL72a9LQubQ.J1hDxPDBDTWHPydQ8pBMypzFHtGJFNa');

INSERT INTO users (username, password)
VALUES ('admin', '$2a$04$Mssud3YK3ghL72a9LQubQ.J1hDxPDBDTWHPydQ8pBMypzFHtGJFNa');

INSERT INTO user_roles (user_id, roles)
values (1, 'ROLE_USER');

INSERT INTO user_roles (user_id, roles)
values (2, 'ROLE_ADMIN');
