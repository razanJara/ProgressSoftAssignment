create DATABASE if not exists werehouse;
       create table werehouse.deal(
           id bigint not null,
           from_currency varchar(40) not null,
           to_currency varchar(40) not null,
           deal_amount double not null,
           deal_timestamp datetime not null,
           primary key (id)
       );