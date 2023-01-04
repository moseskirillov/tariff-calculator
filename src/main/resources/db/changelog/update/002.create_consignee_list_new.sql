drop table if exists consignee_list;
CREATE TABLE "consignee_list"
(
    "id"      serial       NOT NULL,
    "city"    VARCHAR(255) NOT NULL,
    "name"    VARCHAR(255) NOT NULL,
    "address" VARCHAR(255) NULL
);
ALTER TABLE
    "consignee_list"
    ADD PRIMARY KEY ("id");