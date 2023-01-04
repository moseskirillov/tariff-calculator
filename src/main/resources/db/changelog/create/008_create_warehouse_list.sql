CREATE TABLE public.warehouse_list
(
    id      serial4      NOT NULL,
    "name"  varchar(255) NOT NULL,
    city    varchar(255) NOT NULL,
    address varchar(255) NULL,
    fm      bool         NOT NULL,
    CONSTRAINT warehouse_list_pkey PRIMARY KEY (id)
);