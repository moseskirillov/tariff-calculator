CREATE TABLE public.calculation_methods
(
    id                    serial4      NOT NULL,
    "name"                varchar(255) NOT NULL,
    technical_description text         NOT NULL,
    applicable_to         varchar(255) NOT NULL,
    CONSTRAINT calculation_methods_check CHECK (((applicable_to)::text = ANY
                                                 ((ARRAY ['delivery_price'::character varying, 'additional_services_price'::character varying, 'all'::character varying])::text[]))),
    CONSTRAINT calculation_methods_pkey PRIMARY KEY (id)
);