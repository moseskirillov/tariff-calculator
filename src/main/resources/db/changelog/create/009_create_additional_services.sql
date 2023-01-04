CREATE TABLE public.additional_services
(
    id                    serial4      NOT NULL,
    "name"                varchar(255) NOT NULL,
    calculation_method_id int4         NOT NULL,
    CONSTRAINT additional_services_pkey PRIMARY KEY (id),
    CONSTRAINT additional_services_calculation_method_id_foreign FOREIGN KEY (calculation_method_id) REFERENCES public.calculation_methods (id)
);