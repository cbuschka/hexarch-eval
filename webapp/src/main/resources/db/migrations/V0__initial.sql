create table stock_entry
(
    id               identity primary key,
    supplier_no      varchar(80) not null,
    item_no          varchar(80) not null,
    amount           int         not null check ( amount >= 0 ),
    stock_updated_at timestamp   not null,
    constraint stock_entry_ak1 unique (supplier_no, item_no)
);
