CREATE SEQUENCE public.tbl_province_province_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


CREATE TABLE IF NOT EXISTS public.tbl_province
(
    province_id integer NOT NULL DEFAULT nextval('tbl_province_province_id_seq'::regclass),
    name character varying(50) COLLATE pg_catalog."default" NOT NULL,
    CONSTRAINT tbl_province_pkey PRIMARY KEY (province_id)
);

ALTER TABLE IF EXISTS public.tbl_province
    OWNER to postgres;

CREATE SEQUENCE public.tbl_location_location_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

CREATE TABLE IF NOT EXISTS public.tbl_location
(
    location_id integer NOT NULL DEFAULT nextval('tbl_location_location_id_seq'::regclass),
    name character varying(50) COLLATE pg_catalog."default" NOT NULL,
    province_id integer,
    CONSTRAINT tbl_location_pkey PRIMARY KEY (location_id),
    CONSTRAINT fkm9whte2fqj9j9aqt0jcay9mae FOREIGN KEY (province_id)
    REFERENCES public.tbl_province (province_id) MATCH SIMPLE
    ON UPDATE NO ACTION
    ON DELETE NO ACTION
    );

ALTER TABLE IF EXISTS public.tbl_location
    OWNER to postgres;

-- Table: public.tbl_role

-- DROP TABLE IF EXISTS public.tbl_role;

CREATE TABLE IF NOT EXISTS public.tbl_role
(
    role_id character varying(20) COLLATE pg_catalog."default" NOT NULL,
    name character varying(50) COLLATE pg_catalog."default" NOT NULL,
    CONSTRAINT tbl_role_pkey PRIMARY KEY (role_id)
);

ALTER TABLE IF EXISTS public.tbl_role
    OWNER to postgres;

-- Table: public.tbl_account

-- DROP TABLE IF EXISTS public.tbl_account;

CREATE TABLE IF NOT EXISTS public.tbl_account
(
    account_id character varying(36) COLLATE pg_catalog."default" NOT NULL,
    created_at timestamp(6) without time zone NOT NULL,
    password character varying(100) COLLATE pg_catalog."default" NOT NULL,
    updated_at timestamp(6) without time zone,
    username character varying(50) COLLATE pg_catalog."default" NOT NULL,
    role_id character varying(20) COLLATE pg_catalog."default" NOT NULL,
    CONSTRAINT tbl_account_pkey PRIMARY KEY (account_id),
    CONSTRAINT uk_miuqup74scqvyefkdlcoyou5n UNIQUE (username),
    CONSTRAINT fkjf56l4qfqutptyaq1f70sxhxr FOREIGN KEY (role_id)
    REFERENCES public.tbl_role (role_id) MATCH SIMPLE
                            ON UPDATE NO ACTION
                            ON DELETE NO ACTION
    );

ALTER TABLE IF EXISTS public.tbl_account
    OWNER to postgres;

-- Table: public.tbl_userr

-- DROP TABLE IF EXISTS public.tbl_userr;

CREATE TABLE IF NOT EXISTS public.tbl_userr
(
    user_id character varying(36) COLLATE pg_catalog."default" NOT NULL,
    address character varying(50) COLLATE pg_catalog."default" NOT NULL,
    created_at timestamp(6) without time zone NOT NULL,
    email character varying(50) COLLATE pg_catalog."default" NOT NULL,
    full_name character varying(50) COLLATE pg_catalog."default" NOT NULL,
    phone_number character varying(12) COLLATE pg_catalog."default" NOT NULL,
    status boolean,
    updated_at timestamp(6) without time zone,
    account_id character varying(36) COLLATE pg_catalog."default" NOT NULL,
    CONSTRAINT tbl_userr_pkey PRIMARY KEY (user_id),
    CONSTRAINT uk_j02nea3ot69gys4nsrymgfffl UNIQUE (email),
    CONSTRAINT fknyydkvpt87eran4nqidkerukh FOREIGN KEY (account_id)
    REFERENCES public.tbl_account (account_id) MATCH SIMPLE
                            ON UPDATE NO ACTION
                            ON DELETE NO ACTION
);

ALTER TABLE IF EXISTS public.tbl_userr
    OWNER to postgres;

CREATE TABLE IF NOT EXISTS public.tbl_leave
(
    leave_id character varying(36) COLLATE pg_catalog."default" NOT NULL,
    approved boolean NOT NULL,
    created_at timestamp(6) without time zone NOT NULL,
    date_end timestamp(6) without time zone NOT NULL,
    date_start timestamp(6) without time zone NOT NULL,
    reason character varying(500) COLLATE pg_catalog."default" NOT NULL,
    user_id character varying(36) COLLATE pg_catalog."default",
    CONSTRAINT tbl_leave_pkey PRIMARY KEY (leave_id),
    CONSTRAINT fkk5cnxx0mmqfnonyjgt0ktgi98 FOREIGN KEY (user_id)
    REFERENCES public.tbl_userr (user_id) MATCH SIMPLE
                            ON UPDATE NO ACTION
                            ON DELETE NO ACTION
);

ALTER TABLE IF EXISTS public.tbl_leave
    OWNER to postgres;


CREATE TABLE IF NOT EXISTS public.tbl_token
(
    token_id character varying(36) COLLATE pg_catalog."default" NOT NULL,
    expired boolean,
    revoked boolean,
    token character varying(250) COLLATE pg_catalog."default" NOT NULL,
    token_type character varying(255) COLLATE pg_catalog."default",
    account_id character varying(36) COLLATE pg_catalog."default",
    CONSTRAINT tbl_token_pkey PRIMARY KEY (token_id),
    CONSTRAINT uk_c5dy8rus7r6nkaicmi5cglfbt UNIQUE (token),
    CONSTRAINT fkcek6yryfv9tyeseti645pwbhu FOREIGN KEY (account_id)
    REFERENCES public.tbl_account (account_id) MATCH SIMPLE
    ON UPDATE NO ACTION
    ON DELETE NO ACTION
);

ALTER TABLE IF EXISTS public.tbl_token
    OWNER to postgres;

CREATE TABLE IF NOT EXISTS public.tbl_ticket
(
    ticket_id character varying(36) COLLATE pg_catalog."default" NOT NULL,
    address_end character varying(50) COLLATE pg_catalog."default" NOT NULL,
    address_start character varying(50) COLLATE pg_catalog."default" NOT NULL,
    drop_off_location character varying(50) COLLATE pg_catalog."default",
    pick_up_location character varying(50) COLLATE pg_catalog."default",
    price numeric(38,2) NOT NULL,
    CONSTRAINT tbl_ticket_pkey PRIMARY KEY (ticket_id)
 );

ALTER TABLE IF EXISTS public.tbl_ticket
    OWNER to postgres;


CREATE TABLE IF NOT EXISTS public.tbl_car
(
    car_id character varying(36) COLLATE pg_catalog."default" NOT NULL,
    car_number integer NOT NULL,
    created_at timestamp(6) without time zone NOT NULL,
    status boolean NOT NULL,
    update_at timestamp(6) without time zone,
    CONSTRAINT tbl_car_pkey PRIMARY KEY (car_id),
    CONSTRAINT uk_a019h68xgj2101wbsqwrv7s1x UNIQUE (car_number)
);

ALTER TABLE IF EXISTS public.tbl_car
    OWNER to postgres;

CREATE TABLE IF NOT EXISTS public.tbl_employee
(
    employee_id character varying(36) COLLATE pg_catalog."default" NOT NULL,
    created_at timestamp(6) without time zone NOT NULL,
    dob timestamp(6) without time zone NOT NULL,
    update_at timestamp(6) without time zone,
    yoe integer NOT NULL,
    car_id character varying(36) COLLATE pg_catalog."default",
    user_id character varying(36) COLLATE pg_catalog."default",
    CONSTRAINT tbl_employee_pkey PRIMARY KEY (employee_id),
    CONSTRAINT fklq378dc1lcfhd2ixptp0anpux FOREIGN KEY (car_id)
    REFERENCES public.tbl_car (car_id) MATCH SIMPLE
                            ON UPDATE NO ACTION
                            ON DELETE NO ACTION,
    CONSTRAINT fkq07jafwg1ete3fldweo3a94cj FOREIGN KEY (user_id)
    REFERENCES public.tbl_userr (user_id) MATCH SIMPLE
                            ON UPDATE NO ACTION
                            ON DELETE NO ACTION
);

ALTER TABLE IF EXISTS public.tbl_employee
    OWNER to postgres;

--
CREATE TABLE IF NOT EXISTS public.tbl_trip
(
    trip_id character varying(36) COLLATE pg_catalog."default" NOT NULL,
    created_at timestamp(6) without time zone NOT NULL,
    province_end character varying(50) COLLATE pg_catalog."default" NOT NULL,
    province_start character varying(50) COLLATE pg_catalog."default" NOT NULL,
    status boolean,
    time_start timestamp(6) without time zone NOT NULL,
    update_at timestamp(6) without time zone,
    CONSTRAINT tbl_trip_pkey PRIMARY KEY (trip_id)
    );


ALTER TABLE IF EXISTS public.tbl_trip
    OWNER to postgres;

CREATE TABLE IF NOT EXISTS public.tbl_trip_user
(
    trip_id character varying(36) COLLATE pg_catalog."default" NOT NULL,
    user_id character varying(36) COLLATE pg_catalog."default" NOT NULL,
    CONSTRAINT tbl_trip_user_pkey PRIMARY KEY (trip_id, user_id),
    CONSTRAINT fkj33ss9ojbilfq3ta9upbnil0d FOREIGN KEY (trip_id)
    REFERENCES public.tbl_trip (trip_id) MATCH SIMPLE
    ON UPDATE NO ACTION
    ON DELETE NO ACTION,
    CONSTRAINT fkt3crknn21s16ni9bsg04c1mtq FOREIGN KEY (user_id)
    REFERENCES public.tbl_userr (user_id) MATCH SIMPLE
    ON UPDATE NO ACTION
    ON DELETE NO ACTION
);

ALTER TABLE IF EXISTS public.tbl_trip_user
    OWNER to postgres;


CREATE TABLE IF NOT EXISTS public.tbl_trip_car
(
    car_id character varying(36) COLLATE pg_catalog."default" NOT NULL,
    trip_id character varying(36) COLLATE pg_catalog."default" NOT NULL,
    CONSTRAINT tbl_trip_car_pkey PRIMARY KEY (car_id, trip_id),
    CONSTRAINT fk1b2c30m7emuax2ybvda3qf3v0 FOREIGN KEY (trip_id)
    REFERENCES public.tbl_trip (trip_id) MATCH SIMPLE
    ON UPDATE NO ACTION
    ON DELETE NO ACTION,
    CONSTRAINT fk4unuf3b88p9qddin9r0nnybk5 FOREIGN KEY (car_id)
    REFERENCES public.tbl_car (car_id) MATCH SIMPLE
    ON UPDATE NO ACTION
    ON DELETE NO ACTION
 );

ALTER TABLE IF EXISTS public.tbl_trip_car
    OWNER to postgres;


CREATE TABLE IF NOT EXISTS public.tbl_chair
(
    chair_id character varying(36) COLLATE pg_catalog."default" NOT NULL,
    chair_number integer NOT NULL,
    status boolean NOT NULL,
    car_id character varying(36) COLLATE pg_catalog."default",
    CONSTRAINT tbl_chair_pkey PRIMARY KEY (chair_id),
    CONSTRAINT fktcviu05416twi9424d34c9lpr FOREIGN KEY (car_id)
    REFERENCES public.tbl_car (car_id) MATCH SIMPLE
    ON UPDATE NO ACTION
    ON DELETE NO ACTION
);

ALTER TABLE IF EXISTS public.tbl_chair
    OWNER to postgres;

CREATE TABLE IF NOT EXISTS public.tbl_order
(
    order_id character varying(15) COLLATE pg_catalog."default" NOT NULL,
    created_at timestamp(6) without time zone NOT NULL,
    trip_id character varying(36) COLLATE pg_catalog."default",
    user_id character varying(36) COLLATE pg_catalog."default",
    CONSTRAINT tbl_order_pkey PRIMARY KEY (order_id),
    CONSTRAINT fkiq3dagakovd0rjy2kb77ib8au FOREIGN KEY (trip_id)
    REFERENCES public.tbl_trip (trip_id) MATCH SIMPLE
                            ON UPDATE NO ACTION
                            ON DELETE NO ACTION,
    CONSTRAINT fkmi6t4qluyug1738dflp887ajk FOREIGN KEY (user_id)
    REFERENCES public.tbl_userr (user_id) MATCH SIMPLE
                            ON UPDATE NO ACTION
                            ON DELETE NO ACTION
);

ALTER TABLE IF EXISTS public.tbl_order
    OWNER to postgres;


CREATE TABLE IF NOT EXISTS public.tbl_order_detail
(
    order_detail_id character varying(36) COLLATE pg_catalog."default" NOT NULL,
    created_at timestamp(6) without time zone NOT NULL,
    status boolean NOT NULL,
    update_at timestamp(6) without time zone,
    chair_id character varying(36) COLLATE pg_catalog."default",
    order_id character varying(15) COLLATE pg_catalog."default",
    ticket_id character varying(36) COLLATE pg_catalog."default",
    CONSTRAINT tbl_order_detail_pkey PRIMARY KEY (order_detail_id),
    CONSTRAINT fk3ogsvc6366g3po61oxiuhstun FOREIGN KEY (chair_id)
    REFERENCES public.tbl_chair (chair_id) MATCH SIMPLE
                            ON UPDATE NO ACTION
                            ON DELETE NO ACTION,
    CONSTRAINT fk8s24v14pe905qwp8dh7ao9fhj FOREIGN KEY (ticket_id)
    REFERENCES public.tbl_ticket (ticket_id) MATCH SIMPLE
                            ON UPDATE NO ACTION
                            ON DELETE NO ACTION,
    CONSTRAINT fknjncq9emp0cdhj0xbe3kk06h8 FOREIGN KEY (order_id)
    REFERENCES public.tbl_order (order_id) MATCH SIMPLE
                            ON UPDATE NO ACTION
                            ON DELETE NO ACTION
);

ALTER TABLE IF EXISTS public.tbl_order_detail
    OWNER to postgres;