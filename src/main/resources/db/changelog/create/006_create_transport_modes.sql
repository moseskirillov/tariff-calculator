CREATE TABLE public.transport_modes
(
    id     serial4      NOT NULL,
    "name" varchar(255) NOT NULL,
    CONSTRAINT transport_modes_pkey PRIMARY KEY (id)
);