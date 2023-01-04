CREATE TABLE public.consignee_list
(
    id      serial4      NOT NULL,
    "name"  varchar(255) NOT NULL,
    address varchar(255) NULL,
    retail  bool         NOT NULL,
    CONSTRAINT consignee_list_pkey PRIMARY KEY (id)
);