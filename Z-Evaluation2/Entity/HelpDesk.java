package com.Robosoftin.patientHelpdesk.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class HelpDesk {
    private String helpdesk_id;
    private String hospital_id;
}


/*
create schema railway_registration_db;

use railway_registration_db;

create table passenger_info(
passenger_id int primary key not null,
passenger_name varchar(255) not null
);

create table train_info(
train_no int primary key not null,
train_name varchar(255) not null,
train_source varchar(255) not null, 
train_destination varchar(255) not null
);

create table train_coaches(
coach_type int primary key not null,
number_of_seats int not null
);

create table train_coach(
train_no int not null,
coach_type int not null,
foreign key (train_no) references train_info(train_no),
foreign key (coach_type) references train_coaches(coach_type)
);

create table ticket_info(
ticket_id int primary key not null,
passenger_id int not null,
train_no int not null,
coach_type varchar(255) not null,
no_of_seats int not null,
train_source varchar(255) not null,
train_destination varchar(255) not null,
ticket_amount int not null,
booked_date date not null,
ticket_status varchar(255) not null,
foreign key (passenger_id) references passenger_info(passenger_id),
foreign key (train_no) references train_info(train_no)
);

create table waiting_ticket(
waiting_ticket_id int primary key not null,
ticket_info_ticket_id int,
foreign key (ticket_info_ticket_id) references ticket_info(ticket_id)
);

*/