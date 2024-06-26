create DATABASE if not exists werehouse;
       create table werehouse.deal(
           id bigint not null,
           from_currency varchar(3) not null,
           to_currency varchar(3) not null,
           deal_amount double not null,
           deal_timestamp timestamp not null default CURRENT_TIMESTAMP,
           primary key (id)
       );