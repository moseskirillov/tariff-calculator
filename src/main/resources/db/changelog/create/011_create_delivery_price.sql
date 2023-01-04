CREATE TABLE public.delivery_price
(
    id                    serial4       NOT NULL,
    agreement_id          int4          NOT NULL,
    goods_type_id         int4          NOT NULL,
    sender_code           varchar(255)  NULL,
    sender_index_from     varchar(255)  NULL,
    sender_index_to       varchar(255)  NULL,
    sender_zone           varchar(255)  NULL,
    sender_city           varchar(255)  NULL,
    sender_region         varchar(255)  NULL,
    sender_retail         bool          NOT NULL,
    sender_fm             bool          NOT NULL,
    consignee_code        int4          NULL,
    consignee_index_from  varchar(255)  NULL,
    consignee_index_to    varchar(255)  NULL,
    consignee_zone        varchar(255)  NULL,
    consignee_city        varchar(255)  NULL,
    consignee_region      varchar(255)  NULL,
    consignee_retail      bool          NOT NULL,
    consignee_fm          bool          NOT NULL,
    calculation_method_id int4          NOT NULL,
    transport_mode_id     int4          NULL,
    vehicle_type_id       int4          NULL,
    unit_type             varchar(255)  NOT NULL,
    from_qty              int4          NOT NULL,
    to_qty                int4          NOT NULL,
    minimal_price         numeric(8, 2) NOT NULL,
    base_price            numeric(8, 2) NOT NULL,
    add_unit_price        numeric(8, 2) NULL,
    univ_unit_price       numeric(8, 2) NULL,
    day_of_week           varchar       NULL,
    CONSTRAINT delivery_price_pkey PRIMARY KEY (id),
    CONSTRAINT delivery_price_agreement_id_foreign FOREIGN KEY (agreement_id) REFERENCES public.agreements (id),
    CONSTRAINT delivery_price_calculation_method_id_foreign FOREIGN KEY (calculation_method_id) REFERENCES public.calculation_methods (id),
    CONSTRAINT delivery_price_goods_type_id_foreign FOREIGN KEY (goods_type_id) REFERENCES public.goods_types (id),
    CONSTRAINT delivery_price_transport_mode_id_foreign FOREIGN KEY (transport_mode_id) REFERENCES public.transport_modes (id),
    CONSTRAINT delivery_price_vehicle_type_id_foreign FOREIGN KEY (vehicle_type_id) REFERENCES public.vehicle_types (id)
);