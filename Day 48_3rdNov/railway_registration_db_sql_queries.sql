create schema railway_registration_db;

use railway_registration_db;

create table passenger_info(
passenger_id int primary key auto_increment, 
passenger_name varchar(20), 
passenger_gender varchar(20), 
passenger_age int 
) ;

create table train_info(
train_number int primary key, 
train_name varchar(20), 
train_source varchar(20), 
train_destination varchar(20), 
ac_seat_price int, 
gen_seat_price int
);

create table available_dates(
train_number int , 
train_days varchar(20), 
foreign key(train_number) references train_info(train_number) on delete cascade on update cascade
);

create table seats(
train_number int, 
travel_date date,
available_ac_seats int,
booked_ac_seats int, 
avialable_gen_seats int, 
booked_gen_seats int, 
foreign key(train_number) references train_info(train_number) on delete cascade on update cascade
);


create table ticket_booking_info(
ticket_id int primary key auto_increment, 
number_of_seats_booked int, 
train_number int, 
booking_date date, 
date_of_travel date, 
seat_type enum('AC', 'GENERAL'),
booking_status enum('CONFIRMED','WAITING'), 
payment_status enum('COMPLETE','DECLINED'),
train_source varchar(20), 
train_destination varchar(20), 
foreign key(train_number) references train_info(train_number)
);

create table passenger_ticket(
ticket_id int, 
passenger_id int,  
foreign key(ticket_id) references ticket_booking_info(ticket_id) on delete cascade on update cascade,  
foreign key(passenger_id) references passenger_info(passenger_id) on delete cascade on update cascade
);

 alter table seats add primary key(travel_date,train_number);

ALTER TABLE seats ADD available_ac_waiting int;
ALTER TABLE seats ADD available_ac_booked int;
ALTER TABLE seats ADD available_gen_waiting int;
ALTER TABLE seats ADD available_gen_booked int;




