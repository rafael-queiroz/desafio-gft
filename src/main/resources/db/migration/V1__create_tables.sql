CREATE SEQUENCE user_api_id_seq;
CREATE SEQUENCE skill_id_seq;
CREATE SEQUENCE api_key_id_seq;

CREATE TABLE user_api
(
    id bigint NOT NULL DEFAULT nextval('user_api_id_seq'::regclass),
    address character varying(255) COLLATE pg_catalog."default",
    created_at timestamp without time zone,
    date_of_birth date,
    email character varying(255) COLLATE pg_catalog."default",
    name character varying(255) COLLATE pg_catalog."default",
    CONSTRAINT user_api_pkey PRIMARY KEY (id)
);

CREATE TABLE skill
(
    id bigint NOT NULL DEFAULT nextval('skill_id_seq'::regclass),
    description character varying(255) COLLATE pg_catalog."default" NOT NULL,
    id_user bigint,
    CONSTRAINT skill_pkey PRIMARY KEY (id),
    CONSTRAINT fkin89d3ik3ho37tgn9yk1nw1y9 FOREIGN KEY (id_user)
        REFERENCES public.user_api (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
);

CREATE TABLE api_key
(
    id bigint NOT NULL DEFAULT nextval('api_key_id_seq'::regclass),
    api_key character varying(255) COLLATE pg_catalog."default",
    CONSTRAINT api_key_pkey PRIMARY KEY (id)
);

ALTER SEQUENCE user_api_id_seq OWNED BY user_api.id;
ALTER SEQUENCE skill_id_seq OWNED BY skill.id;
ALTER SEQUENCE api_key_id_seq OWNED BY api_key.id;

INSERT INTO api_key(api_key)VALUES('ndqu9h18937413bc09dh2e-ndj');