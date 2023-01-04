CREATE TABLE public.vehicle_types
(
    id     serial4      NOT NULL,
    "name" varchar(255) NOT NULL,
    CONSTRAINT vehicle_types_pkey PRIMARY KEY (id)
);