CREATE TABLE public.agreements
(
    id          serial4      NOT NULL,
    "name"      varchar(255) NOT NULL,
    client_id   int4         NOT NULL,
    valid_from  date         NOT NULL,
    valid_until date         NOT NULL,
    CONSTRAINT agreements_pkey PRIMARY KEY (id),
    CONSTRAINT agreements_client_id_foreign FOREIGN KEY (client_id) REFERENCES public.clients (id)
);