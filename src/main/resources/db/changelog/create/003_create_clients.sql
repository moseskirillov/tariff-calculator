CREATE TABLE public.clients
(
    id     serial4      NOT NULL,
    "name" varchar(255) NOT NULL,
    score  float4       NULL,
    CONSTRAINT clients_pkey PRIMARY KEY (id)
);