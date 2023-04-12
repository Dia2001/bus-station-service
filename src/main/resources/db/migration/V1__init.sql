CREATE TABLE tbl_role
(
    role_id varchar(20) NOT NULL,
    name    varchar(50) NOT NULL,
    PRIMARY KEY (role_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE tbl_account
(
    account_id varchar(36)  NOT NULL,
    created_at datetime(6) NOT NULL,
    password   varchar(100) NOT NULL,
    updated_at datetime(6) DEFAULT NULL,
    username   varchar(50)  NOT NULL,
    role_id    varchar(20)  NOT NULL,
    PRIMARY KEY (account_id),
    UNIQUE KEY UK_miuqup74scqvyefkdlcoyou5n (username),
    KEY          FKjf56l4qfqutptyaq1f70sxhxr (role_id),
    CONSTRAINT FKjf56l4qfqutptyaq1f70sxhxr FOREIGN KEY (role_id) REFERENCES tbl_role (role_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE tbl_userr
(
    user_id      varchar(36) NOT NULL,
    address      varchar(50) NOT NULL,
    created_at   datetime(6) NOT NULL,
    email        varchar(50) NOT NULL,
    full_name    varchar(50) NOT NULL,
    phone_number varchar(12) NOT NULL,
    status       bit(1) DEFAULT NULL,
    updated_at   datetime(6) DEFAULT NULL,
    account_id   varchar(36) NOT NULL,
    PRIMARY KEY (user_id),
    UNIQUE KEY UK_j02nea3ot69gys4nsrymgfffl (email)
,
    KEY            FKnyydkvpt87eran4nqidkerukh (account_id),
    CONSTRAINT FKnyydkvpt87eran4nqidkerukh FOREIGN KEY (account_id) REFERENCES tbl_account (account_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE tbl_token
(
    token_id   varchar(36)  NOT NULL,
    expired    bit(1)       DEFAULT NULL,
    revoked    bit(1)       DEFAULT NULL,
    token      varchar(250) NOT NULL,
    token_type varchar(255) DEFAULT NULL,
    account_id varchar(36)  DEFAULT NULL,
    PRIMARY KEY (token_id),
    UNIQUE KEY UK_c5dy8rus7r6nkaicmi5cglfbt (token),
    KEY          FKcek6yryfv9tyeseti645pwbhu (account_id),
    CONSTRAINT FKcek6yryfv9tyeseti645pwbhu FOREIGN KEY (account_id) REFERENCES tbl_account (account_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE tbl_employee
(
    employee_id varchar(36) NOT NULL,
    created_at  datetime(6) NOT NULL,
    dob         datetime(6) NOT NULL,
    update_at   datetime(6) DEFAULT NULL,
    yoe         int         NOT NULL,
    user_id     varchar(36) DEFAULT NULL,
    PRIMARY KEY (employee_id),
    KEY           FKq07jafwg1ete3fldweo3a94cj (user_id),
    CONSTRAINT FKq07jafwg1ete3fldweo3a94cj FOREIGN KEY (user_id) REFERENCES tbl_userr (user_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE tbl_leave
(
    leave_id   varchar(36)  NOT NULL,
    created_at datetime(6) NOT NULL,
    date_end   datetime(6) NOT NULL,
    date_start datetime(6) NOT NULL,
    reason     varchar(500) NOT NULL,
    user_id    varchar(36) DEFAULT NULL,
    PRIMARY KEY (leave_id),
    KEY          FKk5cnxx0mmqfnonyjgt0ktgi98 (user_id),
    CONSTRAINT FKk5cnxx0mmqfnonyjgt0ktgi98 FOREIGN KEY (user_id) REFERENCES tbl_userr (user_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE tbl_province
(
    province_id int         NOT NULL AUTO_INCREMENT,
    name        varchar(50) NOT NULL,
    PRIMARY KEY (province_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


CREATE TABLE tbl_city
(
    city_id     int         NOT NULL AUTO_INCREMENT,
    name        varchar(50) NOT NULL,
    province_id int DEFAULT NULL,
    PRIMARY KEY (city_id),
    KEY           FKgwql0duanim53l63pnnalpd5v (province_id),
    CONSTRAINT FKgwql0duanim53l63pnnalpd5v FOREIGN KEY (province_id) REFERENCES tbl_province (province_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE tbl_trip
(
    trip_id        varchar(36) NOT NULL,
    created_at     datetime(6) NOT NULL,
    province_end   varchar(50) NOT NULL,
    province_start varchar(50) NOT NULL,
    status         bit(1) DEFAULT NULL,
    time_start     datetime(6) NOT NULL,
    update_at      datetime(6) DEFAULT NULL,
    PRIMARY KEY (trip_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE tbl_trip_user
(
    trip_id varchar(36) NOT NULL,
    user_id varchar(36) NOT NULL,
    PRIMARY KEY (trip_id, user_id),
    KEY       FKt3crknn21s16ni9bsg04c1mtq (user_id),
    CONSTRAINT FKj33ss9ojbilfq3ta9upbnil0d FOREIGN KEY (trip_id) REFERENCES tbl_trip (trip_id),
    CONSTRAINT FKt3crknn21s16ni9bsg04c1mtq FOREIGN KEY (user_id) REFERENCES tbl_userr (user_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE tbl_car
(
    car_id     varchar(36) NOT NULL,
    car_number int         NOT NULL,
    created_at datetime(6) NOT NULL,
    status     bit(1)      NOT NULL,
    update_at  datetime(6) DEFAULT NULL,
    trip_id    varchar(36) DEFAULT NULL,
    PRIMARY KEY (car_id),
    UNIQUE KEY UK_a019h68xgj2101wbsqwrv7s1x (car_number),
    KEY          FKhddb32fmkf2snqy3fdr3ruh8f (trip_id),
    CONSTRAINT FKhddb32fmkf2snqy3fdr3ruh8f FOREIGN KEY (trip_id) REFERENCES tbl_trip (trip_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE tbl_chair
(
    chair_id     varchar(36) NOT NULL,
    chair_number int         NOT NULL,
    status       bit(1)      NOT NULL,
    car_id       varchar(36) DEFAULT NULL,
    PRIMARY KEY (chair_id),
    KEY            FKtcviu05416twi9424d34c9lpr (car_id),
    CONSTRAINT FKtcviu05416twi9424d34c9lpr FOREIGN KEY (car_id) REFERENCES tbl_car (car_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;



CREATE TABLE tbl_order
(
    order_id   varchar(15) NOT NULL,
    created_at datetime(6) NOT NULL,
    user_id    varchar(36) DEFAULT NULL,
    PRIMARY KEY (order_id),
    KEY          FKmi6t4qluyug1738dflp887ajk (user_id),
    CONSTRAINT FKmi6t4qluyug1738dflp887ajk FOREIGN KEY (user_id) REFERENCES tbl_userr (user_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE tbl_ticket
(
    ticket_id     varchar(36)    NOT NULL,
    address_end   varchar(50)    NOT NULL,
    address_start varchar(50)    NOT NULL,
    price         decimal(38, 2) NOT NULL,
    PRIMARY KEY (ticket_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE tbl_order_detail
(
    order_detail_id varchar(36) NOT NULL,
    created_at      datetime(6) NOT NULL,
    status          varchar(20) NOT NULL,
    update_at       datetime(6) DEFAULT NULL,
    chair_id        varchar(36) DEFAULT NULL,
    order_id        varchar(15) DEFAULT NULL,
    ticket_id       varchar(36) DEFAULT NULL,
    PRIMARY KEY (order_detail_id),
    KEY               FK3ogsvc6366g3po61oxiuhstun (chair_id),
    KEY               FKnjncq9emp0cdhj0xbe3kk06h8 (order_id),
    KEY               FK8s24v14pe905qwp8dh7ao9fhj (ticket_id),
    CONSTRAINT FK3ogsvc6366g3po61oxiuhstun FOREIGN KEY (chair_id) REFERENCES tbl_chair (chair_id),
    CONSTRAINT FK8s24v14pe905qwp8dh7ao9fhj FOREIGN KEY (ticket_id) REFERENCES tbl_ticket (ticket_id),
    CONSTRAINT FKnjncq9emp0cdhj0xbe3kk06h8 FOREIGN KEY (order_id) REFERENCES tbl_order (order_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;