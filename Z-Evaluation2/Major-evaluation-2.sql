create database patient_helpdesk;
use patient_helpdesk;


create table hospital(
hospital_id varchar(20) primary key not null,
hospital_name varchar(30) not null,
hospital_location varchar(30)
)engine=InnoDB;


create table helpdesk(
helpdesk_id varchar(20) primary key not null,
hospital_id varchar(20),
foreign key(hospital_id) references hospital(hospital_id)on delete cascade
)engine=InnoDB;


create table registration(
patient_id varchar(20) primary key not null,
patient_name varchar(30),
patient_age int,
patient_gender varchar(10)
)engine=InnoDB;


create table doctors(
doctor_id varchar(20) primary key not null,
doctor_name varchar(30),
max_patient int,
hospital_id varchar(20),
foreign key(hospital_id)references hospital(hospital_id)on delete cascade
)engine=InnoDB;

create table doctor_patientslist(
patient_id varchar(20),
doctor_id varchar(20),
patient_name varchar(30),
patient_age int,
patient_gender varchar(10),
foreign key(patient_id) references registration(patient_id)on delete cascade,
foreign key(doctor_id) references doctors(doctor_id)on delete cascade
)engine=InnoDB;


create table out_patient(
patient_id varchar(20),
doctor_id varchar(20),
patient_name varchar(30),
patient_age int,
foreign key(patient_id)references registration(patient_id)on delete cascade,
foreign key(doctor_id)references doctors(doctor_id)on delete cascade
)engine=InnoDB;


create table doctor_report(
patient_id varchar(20),
doctor_id varchar(20),
findings varchar(100),
bill_paid varchar(10),
foreign key(patient_id)references registration(patient_id)on delete cascade,
foreign key(doctor_id)references doctors(doctor_id)on delete cascade
)engine=InnoDB;


create table patient_details(
patient_id varchar(20),
doctor_id varchar(20),
patient_name varchar(30),
patient_age int,
foreign key(patient_id)references registration(patient_id)on delete cascade,
foreign key(doctor_id)references doctors(doctor_id)on delete cascade
)engine=InnoDB;

create table patient_report(
patient_id varchar(20),
patient_name varchar(30),
doctor_id varchar(20),
findings varchar(100),
foreign key(patient_id)references registration(patient_id)
)engine=InnoDB;


create table ward(
ward_id varchar(20) primary key,
ward_name varchar(20),
doctor_id varchar(20),
ward_size int,
hospital_id varchar(20),
foreign key(doctor_id)references doctors(doctor_id),
foreign key(hospital_id)references hospital(hospital_id)
)engine=InnoDB;

create table in_patient(
patient_id varchar(20),
patient_name varchar(30),
ward_id varchar(20),
ward_name varchar(20),
insurance_covered varchar(10),
amount_paid int,
foreign key(patient_id) references registration(patient_id) on delete cascade,
foreign key(ward_id) references ward(ward_id)on delete cascade
)engine=InnoDB;

create table ward_report(
patient_id varchar(20),
patient_name varchar(30),
doctor_id varchar(20),
surgery_type varchar(20),
findings varchar(100),
admitted_days int,
bill_payment varchar(20),
foreign key(patient_id) references registration(patient_id) on delete cascade
)engine=InnoDB;

create table in_patient_report(
patient_id varchar(20) primary key,
patient_name varchar(30),
doctor_id varchar(20),
surgery_type varchar(20),
findings varchar(100),
admitted_days int,
foreign key(patient_id)references registration(patient_id)on delete cascade
)engine=InnoDB;
