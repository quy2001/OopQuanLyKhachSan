create database HotelManagementSystem
go

use HotelManagementSystem
go

create table users
(
			email varchar(200) primary key,
			name varchar(200) not null,
			password varchar(200) not null,
			securityQuestion varchar(500) not null,
			answer varchar(200) not null,
			address varchar(200) not null,
			status varchar(20) not null
);
go

create table room
(
			roomNo varchar(10) primary key,
			roomType varchar(200) not null,
			peoplePerRoom int not null,
			pricePerDay int not null
);
go

--tầng 1 ==> 20 phòng
insert into room values ('101', 'Standard Room', 1, 100);--giá phòng = 100$ ==> + 40&/người
insert into room values ('102', 'Standard Room', 1, 100);
insert into room values ('103', 'Standard Room', 1, 100);
insert into room values ('104', 'Standard Room', 1, 100);
insert into room values ('105', 'Standard Room', 1, 100)

insert into room values ('106', 'Standard Room', 2, 140);
insert into room values ('107', 'Standard Room', 2, 140);
insert into room values ('108', 'Standard Room', 2, 140);
insert into room values ('109', 'Standard Room', 2, 140);
insert into room values ('110', 'Standard Room', 2, 140);

insert into room values ('111', 'Superior Room', 1, 150);--giá phòng =150$ ==> + 50$/người
insert into room values ('112', 'Superior Room', 1, 150);
insert into room values ('113', 'Superior Room', 1, 150);
insert into room values ('114', 'Superior Room', 2, 200);
insert into room values ('115', 'Superior Room', 2, 200);
insert into room values ('116', 'Superior Room', 2, 200);
insert into room values ('117', 'Superior Room', 3, 250);
insert into room values ('118', 'Superior Room', 3, 250);
insert into room values ('119', 'Superior Room', 4, 300);
insert into room values ('120', 'Superior Room', 4, 300);

--tầng 2 ==>20phòng
insert into room values ('201', 'Standard Room', 1, 100);
insert into room values ('202', 'Standard Room', 1, 100);
insert into room values ('203', 'Standard Room', 1, 100);
insert into room values ('204', 'Standard Room', 1, 100);
insert into room values ('205', 'Standard Room', 1, 100);

insert into room values ('206', 'Standard Room', 2, 140);
insert into room values ('207', 'Standard Room', 2, 140);
insert into room values ('208', 'Standard Room', 2, 140);
insert into room values ('209', 'Standard Room', 2, 140);
insert into room values ('210', 'Standard Room', 2, 140);

insert into room values ('211', 'Superior Room', 1, 150);
insert into room values ('212', 'Superior Room', 1, 150);
insert into room values ('213', 'Superior Room', 1, 150);
insert into room values ('214', 'Superior Room', 2, 200);
insert into room values ('215', 'Superior Room', 2, 200);
insert into room values ('216', 'Superior Room', 2, 200);
insert into room values ('217', 'Superior Room', 3, 250);
insert into room values ('218', 'Superior Room', 3, 250);
insert into room values ('219', 'Superior Room', 4, 300);
insert into room values ('220', 'Superior Room', 4, 300);

--tầng 3 ==> 10 phòng
insert into room values ('301', 'Standard Room', 1, 100);
insert into room values ('302', 'Standard Room', 2, 140);

insert into room values ('303', 'Superior Room', 1, 150);
insert into room values ('304', 'Superior Room', 4, 350);

insert into room values ('305', 'Deluxe Room', 2, 260);--giá phòng = 200$ ==> + 60$/người
insert into room values ('306', 'Deluxe Room', 2, 260);

insert into room values ('307', 'Suite Room', 2, 330);--giá phòng 250$ ==> + 80$/người
insert into room values ('308', 'Suite Room', 3, 410);
insert into room values ('309', 'Suite Room', 3, 410);
insert into room values ('310', 'Suite Room', 4, 490);

--tầng 4 ==> 15 phòng 
insert into room values ('401', 'Standard Room', 1, 100);
insert into room values ('402', 'Standard Room', 1, 100);
insert into room values ('403', 'Standard Room', 2, 140);
insert into room values ('404', 'Standard Room', 2, 140);

insert into room values ('405', 'Deluxe Room', 4, 380);
insert into room values ('406', 'Deluxe Room', 2, 260);
insert into room values ('407', 'Deluxe Room', 2, 260);
insert into room values ('408', 'Deluxe Room', 3, 320);
insert into room values ('409', 'Deluxe Room', 4, 380);
insert into room values ('410', 'Deluxe Room', 4, 380);

insert into room values ('411', 'Superior Room', 1, 150);
insert into room values ('412', 'Superior Room', 2, 200);
insert into room values ('413', 'Superior Room', 3, 250);
insert into room values ('414', 'Superior Room', 3, 250);
insert into room values ('415', 'Superior Room', 4, 300);
go

create table booking
(
			idBooking int primary key,
			name varchar(200) not null,
			email varchar(200) not null,
			nationality varchar(100) not null,
			mobilePhone varchar(20) not null,
			bookingDate date not null,
			bookingTime varchar(15) not null,
			guestName varchar(200),
			guestEmail varchar(200),
			cardholderName varchar(200) not null,
			cardType varchar(200) not null,
			cardNumber varchar(20) not null,
			expirationDate date not null
);
go

create table detailBooking
(
			idBooking int,
			roomNo varchar(10),
			checkinDate date not null,
			checkoutDate date not null,
			status varchar(20) not null,
			cancelDate date,
			constraint PK_detailBooking primary key (idBooking, roomNo),
			constraint FK_detailBooking_room foreign key (roomNo) references room(roomNo)
);
go

--Tầng 1 ==> 20 phòng
insert into booking values (1, 'null', 'null', 'null', 'null','2001-01-01', '19:45', '', '', 'null', 'Master Card', 'null', '2001-01-01');
insert into detailBooking values (1, '101', '2001-01-01', '2001-01-01', 'Booked', '');
insert into booking values (2, 'null', 'null', 'null', 'null','2001-01-01', '19:45', '', '', 'null', 'Master Card', 'null', '2001-01-01');
insert into detailBooking values (2, '102', '2001-01-01', '2001-01-01', 'Booked', '');
insert into booking values (3, 'null', 'null', 'null', 'null','2001-01-01', '19:45', '', '', 'null', 'Master Card', 'null', '2001-01-01');
insert into detailBooking values (3, '103', '2001-01-01', '2001-01-01', 'Booked', '');
insert into booking values (4, 'null', 'null', 'null', 'null','2001-01-01', '19:45', '', '', 'null', 'Master Card', 'null', '2001-01-01');
insert into detailBooking values (4, '104', '2001-01-01', '2001-01-01', 'Booked', '');
insert into booking values (5, 'null', 'null', 'null', 'null','2001-01-01', '19:45', '', '', 'null', 'Master Card', 'null', '2001-01-01');
insert into detailBooking values (5, '105', '2001-01-01', '2001-01-01', 'Booked', '');

insert into booking values (6, 'null', 'null', 'null', 'null','2001-01-01', '19:45', '', '', 'null', 'Master Card', 'null', '2001-01-01');
insert into detailBooking values (6, '106', '2001-01-01', '2001-01-01', 'Booked', '');
insert into booking values (7, 'null', 'null', 'null', 'null','2001-01-01', '19:45', '', '', 'null', 'Master Card', 'null', '2001-01-01');
insert into detailBooking values (7, '107', '2001-01-01', '2001-01-01', 'Booked', '');
insert into booking values (8, 'null', 'null', 'null', 'null','2001-01-01', '19:45', '', '', 'null', 'Master Card', 'null', '2001-01-01');
insert into detailBooking values (8, '108', '2001-01-01', '2001-01-01', 'Booked', '');
insert into booking values (9, 'null', 'null', 'null', 'null','2001-01-01', '19:45', '', '', 'null', 'Master Card', 'null', '2001-01-01');
insert into detailBooking values (9, '109', '2001-01-01', '2001-01-01', 'Booked', '');
insert into booking values (10, 'null', 'null', 'null', 'null','2001-01-01', '19:45', '', '', 'null', 'Master Card', 'null', '2001-01-01');
insert into detailBooking values (10, '110', '2001-01-01', '2001-01-01', 'Booked', '');

insert into booking values (11, 'null', 'null', 'null', 'null','2001-01-01', '19:45', '', '', 'null', 'Master Card', 'null', '2001-01-01');
insert into detailBooking values (11, '111', '2001-01-01', '2001-01-01', 'Booked', '');
insert into booking values (12, 'null', 'null', 'null', 'null','2001-01-01', '19:45', '', '', 'null', 'Master Card', 'null', '2001-01-01');
insert into detailBooking values (12, '112', '2001-01-01', '2001-01-01', 'Booked', '');
insert into booking values (13, 'null', 'null', 'null', 'null','2001-01-01', '19:45', '', '', 'null', 'Master Card', 'null', '2001-01-01');
insert into detailBooking values (13, '113', '2001-01-01', '2001-01-01', 'Booked', '');
insert into booking values (14, 'null', 'null', 'null', 'null','2001-01-01', '19:45', '', '', 'null', 'Master Card', 'null', '2001-01-01');
insert into detailBooking values (14, '114', '2001-01-01', '2001-01-01', 'Booked', '');
insert into booking values (15, 'null', 'null', 'null', 'null','2001-01-01', '19:45', '', '', 'null', 'Master Card', 'null', '2001-01-01');
insert into detailBooking values (15, '115', '2001-01-01', '2001-01-01', 'Booked', '');

insert into booking values (16, 'null', 'null', 'null', 'null','2001-01-01', '19:45', '', '', 'null', 'Master Card', 'null', '2001-01-01');
insert into detailBooking values (16, '116', '2001-01-01', '2001-01-01', 'Booked', '');
insert into booking values (17, 'null', 'null', 'null', 'null','2001-01-01', '19:45', '', '', 'null', 'Master Card', 'null', '2001-01-01');
insert into detailBooking values (17, '117', '2001-01-01', '2001-01-01', 'Booked', '');
insert into booking values (18, 'null', 'null', 'null', 'null','2001-01-01', '19:45', '', '', 'null', 'Master Card', 'null', '2001-01-01');
insert into detailBooking values (18, '118', '2001-01-01', '2001-01-01', 'Booked', '');
insert into booking values (19, 'null', 'null', 'null', 'null','2001-01-01', '19:45', '', '', 'null', 'Master Card', 'null', '2001-01-01');
insert into detailBooking values (19, '119', '2001-01-01', '2001-01-01', 'Booked', '');
insert into booking values (20, 'null', 'null', 'null', 'null','2001-01-01', '19:45', '', '', 'null', 'Master Card', 'null', '2001-01-01');
insert into detailBooking values (20, '120', '2001-01-01', '2001-01-01', 'Booked', '');

--tầng 2 ==> 20 phòng
insert into booking values (21, 'null', 'null', 'null', 'null','2001-01-01', '19:45', '', '', 'null', 'Master Card', 'null', '2001-01-01');
insert into detailBooking values (21, '201', '2001-01-01', '2001-01-01', 'Booked', '');
insert into booking values (22, 'null', 'null', 'null', 'null','2001-01-01', '19:45', '', '', 'null', 'Master Card', 'null', '2001-01-01');
insert into detailBooking values (22, '202', '2001-01-01', '2001-01-01', 'Booked', '');
insert into booking values (23, 'null', 'null', 'null', 'null','2001-01-01', '19:45', '', '', 'null', 'Master Card', 'null', '2001-01-01');
insert into detailBooking values (23, '203', '2001-01-01', '2001-01-01', 'Booked', '');
insert into booking values (24, 'null', 'null', 'null', 'null','2001-01-01', '19:45', '', '', 'null', 'Master Card', 'null', '2001-01-01');
insert into detailBooking values (24, '204', '2001-01-01', '2001-01-01', 'Booked', '');
insert into booking values (25, 'null', 'null', 'null', 'null','2001-01-01', '19:45', '', '', 'null', 'Master Card', 'null', '2001-01-01');
insert into detailBooking values (25, '205', '2001-01-01', '2001-01-01', 'Booked', '');

insert into booking values (26, 'null', 'null', 'null', 'null','2001-01-01', '19:45', '', '', 'null', 'Master Card', 'null', '2001-01-01');
insert into detailBooking values (26, '206', '2001-01-01', '2001-01-01', 'Booked', '');
insert into booking values (27, 'null', 'null', 'null', 'null','2001-01-01', '19:45', '', '', 'null', 'Master Card', 'null', '2001-01-01');
insert into detailBooking values (27, '207', '2001-01-01', '2001-01-01', 'Booked', '');
insert into booking values (28, 'null', 'null', 'null', 'null','2001-01-01', '19:45', '', '', 'null', 'Master Card', 'null', '2001-01-01');
insert into detailBooking values (28, '208', '2001-01-01', '2001-01-01', 'Booked', '');
insert into booking values (29, 'null', 'null', 'null', 'null','2001-01-01', '19:45', '', '', 'null', 'Master Card', 'null', '2001-01-01');
insert into detailBooking values (29, '209', '2001-01-01', '2001-01-01', 'Booked', '');
insert into booking values (30, 'null', 'null', 'null', 'null','2001-01-01', '19:45', '', '', 'null', 'Master Card', 'null', '2001-01-01');
insert into detailBooking values (30, '210', '2001-01-01', '2001-01-01', 'Booked', '');

insert into booking values (31, 'null', 'null', 'null', 'null','2001-01-01', '19:45', '', '', 'null', 'Master Card', 'null', '2001-01-01');
insert into detailBooking values (31, '211', '2001-01-01', '2001-01-01', 'Booked', '');
insert into booking values (32, 'null', 'null', 'null', 'null','2001-01-01', '19:45', '', '', 'null', 'Master Card', 'null', '2001-01-01');
insert into detailBooking values (32, '212', '2001-01-01', '2001-01-01', 'Booked', '');
insert into booking values (33, 'null', 'null', 'null', 'null','2001-01-01', '19:45', '', '', 'null', 'Master Card', 'null', '2001-01-01');
insert into detailBooking values (33, '213', '2001-01-01', '2001-01-01', 'Booked', '');
insert into booking values (34, 'null', 'null', 'null', 'null','2001-01-01', '19:45', '', '', 'null', 'Master Card', 'null', '2001-01-01');
insert into detailBooking values (34, '214', '2001-01-01', '2001-01-01', 'Booked', '');
insert into booking values (35, 'null', 'null', 'null', 'null','2001-01-01', '19:45', '', '', 'null', 'Master Card', 'null', '2001-01-01');
insert into detailBooking values (35, '215', '2001-01-01', '2001-01-01', 'Booked', '');

insert into booking values (36, 'null', 'null', 'null', 'null','2001-01-01', '19:45', '', '', 'null', 'Master Card', 'null', '2001-01-01');
insert into detailBooking values (36, '216', '2001-01-01', '2001-01-01', 'Booked', '');
insert into booking values (37, 'null', 'null', 'null', 'null','2001-01-01', '19:45', '', '', 'null', 'Master Card', 'null', '2001-01-01');
insert into detailBooking values (37, '217', '2001-01-01', '2001-01-01', 'Booked', '');
insert into booking values (38, 'null', 'null', 'null', 'null','2001-01-01', '19:45', '', '', 'null', 'Master Card', 'null', '2001-01-01');
insert into detailBooking values (38, '218', '2001-01-01', '2001-01-01', 'Booked', '');
insert into booking values (39, 'null', 'null', 'null', 'null','2001-01-01', '19:45', '', '', 'null', 'Master Card', 'null', '2001-01-01');
insert into detailBooking values (39, '219', '2001-01-01', '2001-01-01', 'Booked', '');
insert into booking values (40, 'null', 'null', 'null', 'null','2001-01-01', '19:45', '', '', 'null', 'Master Card', 'null', '2001-01-01');
insert into detailBooking values (40, '220', '2001-01-01', '2001-01-01', 'Booked', '');

--tầng 3 ==> 10 phòng
insert into booking values (41, 'null', 'null', 'null', 'null','2001-01-01', '19:45', '', '', 'null', 'Master Card', 'null', '2001-01-01');
insert into detailBooking values (41, '301', '2001-01-01', '2001-01-01', 'Booked', '');
insert into booking values (42, 'null', 'null', 'null', 'null','2001-01-01', '19:45', '', '', 'null', 'Master Card', 'null', '2001-01-01');
insert into detailBooking values (42, '302', '2001-01-01', '2001-01-01', 'Booked', '');
insert into booking values (43, 'null', 'null', 'null', 'null','2001-01-01', '19:45', '', '', 'null', 'Master Card', 'null', '2001-01-01');
insert into detailBooking values (43, '303', '2001-01-01', '2001-01-01', 'Booked', '');
insert into booking values (44, 'null', 'null', 'null', 'null','2001-01-01', '19:45', '', '', 'null', 'Master Card', 'null', '2001-01-01');
insert into detailBooking values (44, '304', '2001-01-01', '2001-01-01', 'Booked', '');
insert into booking values (45, 'null', 'null', 'null', 'null','2001-01-01', '19:45', '', '', 'null', 'Master Card', 'null', '2001-01-01');
insert into detailBooking values (45, '305', '2001-01-01', '2001-01-01', 'Booked', '');

insert into booking values (46, 'null', 'null', 'null', 'null','2001-01-01', '19:45', '', '', 'null', 'Master Card', 'null', '2001-01-01');
insert into detailBooking values (46, '306', '2001-01-01', '2001-01-01', 'Booked', '');
insert into booking values (47, 'null', 'null', 'null', 'null','2001-01-01', '19:45', '', '', 'null', 'Master Card', 'null', '2001-01-01');
insert into detailBooking values (47, '307', '2001-01-01', '2001-01-01', 'Booked', '');
insert into booking values (48, 'null', 'null', 'null', 'null','2001-01-01', '19:45', '', '', 'null', 'Master Card', 'null', '2001-01-01');
insert into detailBooking values (48, '308', '2001-01-01', '2001-01-01', 'Booked', '');
insert into booking values (49, 'null', 'null', 'null', 'null','2001-01-01', '19:45', '', '', 'null', 'Master Card', 'null', '2001-01-01');
insert into detailBooking values (49, '309', '2001-01-01', '2001-01-01', 'Booked', '');
insert into booking values (50, 'null', 'null', 'null', 'null','2001-01-01', '19:45', '', '', 'null', 'Master Card', 'null', '2001-01-01');
insert into detailBooking values (50, '310', '2001-01-01', '2001-01-01', 'Booked', '');

--tầng 4 ==> 15 phòng
insert into booking values (51, 'null', 'null', 'null', 'null','2001-01-01', '19:45', '', '', 'null', 'Master Card', 'null', '2001-01-01');
insert into detailBooking values (51, '401', '2001-01-01', '2001-01-01', 'Booked', '');
insert into booking values (52, 'null', 'null', 'null', 'null','2001-01-01', '19:45', '', '', 'null', 'Master Card', 'null', '2001-01-01');
insert into detailBooking values (52, '402', '2001-01-01', '2001-01-01', 'Booked', '');
insert into booking values (53, 'null', 'null', 'null', 'null','2001-01-01', '19:45', '', '', 'null', 'Master Card', 'null', '2001-01-01');
insert into detailBooking values (53, '403', '2001-01-01', '2001-01-01', 'Booked', '');
insert into booking values (54, 'null', 'null', 'null', 'null','2001-01-01', '19:45', '', '', 'null', 'Master Card', 'null', '2001-01-01');
insert into detailBooking values (54, '404', '2001-01-01', '2001-01-01', 'Booked', '');
insert into booking values (55, 'null', 'null', 'null', 'null','2001-01-01', '19:45', '', '', 'null', 'Master Card', 'null', '2001-01-01');
insert into detailBooking values (55, '405', '2001-01-01', '2001-01-01', 'Booked', '');

insert into booking values (56, 'null', 'null', 'null', 'null','2001-01-01', '19:45', '', '', 'null', 'Master Card', 'null', '2001-01-01');
insert into detailBooking values (56, '406', '2001-01-01', '2001-01-01', 'Booked', '');
insert into booking values (57, 'null', 'null', 'null', 'null','2001-01-01', '19:45', '', '', 'null', 'Master Card', 'null', '2001-01-01');
insert into detailBooking values (57, '407', '2001-01-01', '2001-01-01', 'Booked', '');
insert into booking values (58, 'null', 'null', 'null', 'null','2001-01-01', '19:45', '', '', 'null', 'Master Card', 'null', '2001-01-01');
insert into detailBooking values (58, '408', '2001-01-01', '2001-01-01', 'Booked', '');
insert into booking values (59, 'null', 'null', 'null', 'null','2001-01-01', '19:45', '', '', 'null', 'Master Card', 'null', '2001-01-01');
insert into detailBooking values (59, '409', '2001-01-01', '2001-01-01', 'Booked', '');
insert into booking values (60, 'null', 'null', 'null', 'null','2001-01-01', '19:45', '', '', 'null', 'Master Card', 'null', '2001-01-01');
insert into detailBooking values (60, '410', '2001-01-01', '2001-01-01', 'Booked', '');

insert into booking values (61, 'null', 'null', 'null', 'null','2001-01-01', '19:45', '', '', 'null', 'Master Card', 'null', '2001-01-01');
insert into detailBooking values (61, '411', '2001-01-01', '2001-01-01', 'Booked', '');
insert into booking values (62, 'null', 'null', 'null', 'null','2001-01-01', '19:45', '', '', 'null', 'Master Card', 'null', '2001-01-01');
insert into detailBooking values (62, '412', '2001-01-01', '2001-01-01', 'Booked', '');
insert into booking values (63, 'null', 'null', 'null', 'null','2001-01-01', '19:45', '', '', 'null', 'Master Card', 'null', '2001-01-01');
insert into detailBooking values (63, '413', '2001-01-01', '2001-01-01', 'Booked', '');
insert into booking values (64, 'null', 'null', 'null', 'null','2001-01-01', '19:45', '', '', 'null', 'Master Card', 'null', '2001-01-01');
insert into detailBooking values (64, '414', '2001-01-01', '2001-01-01', 'Booked', '');
insert into booking values (65, 'null', 'null', 'null', 'null','2001-01-01', '19:45', '', '', 'null', 'Master Card', 'null', '2001-01-01');
insert into detailBooking values (65, '415', '2001-01-01', '2001-01-01', 'Booked', '');
go




create table food
(
			idFood varchar(10) primary key,
			foodName varchar(200) not null,
			price int not null,
			status varchar(20) not null
);
go

insert into food values ('BDMT', 'Bun Dau Mam Tom', 10, 'Available');
insert into food values ('SLNA', 'Sup Luon Nghe An', 15, 'Available');
insert into food values ('CDHT', 'Cu Do Ha Tinh', 10, 'Available');
insert into food values ('BT', 'Bit Tet', 30, 'Available');
insert into food values ('BC', 'Bun Cha', 15, 'Available');
insert into food values ('PHO', 'Pho', 20, 'Available');
insert into food values ('BMSV', 'Banh Mi Sot Vang', 15, 'Available');
insert into food values ('RMXT', 'Rau Muong Xao Toi', 5, 'Available');
insert into food values ('CGHS', 'Cha Gio Hai San', 20, 'Available');
insert into food values ('SHS', 'Sup Hai San', 15, 'Available');
insert into food values ('SSG', 'Sup Sen Ga', 15, 'Available');
insert into food values ('SBN', 'Sup Bi Ngo', 5, 'Available');
insert into food values ('THT', 'Tom Hap Toi', 15, 'Available');
insert into food values ('THND', 'Tom Hap Nuoc Dua', 15, 'Available');
insert into food values ('TRM', 'Tom Rang Me', 15, 'Available');
insert into food values ('GQ', 'Ga Quay', 35, 'Available');
insert into food values ('GCT', 'Ga Chia Tay', 35, 'Available');
insert into food values ('VQ', 'Vit Quay', 30, 'Available');
insert into food values ('BBQ', 'Suon Nuong BBQ', 40, 'Available');
insert into food values ('SCT', 'Suon Chien Toi', 25, 'Available');
insert into food values ('SCN', 'Suon Xao Chua Ngot', 25, 'Available');
insert into food values ('BCSG', 'Bo Cuon Sai Gon', 45, 'Available');
insert into food values ('BLL', 'Bo Luc Lac Xot Tieu Den', 45, 'Available');
insert into food values ('CTX', 'Cai Thia Xao Dau Hao', 5, 'Available');
insert into food values ('BCXN', 'Bong Cai Xanh Xao Nam Dong Co', 25, 'Available');
insert into food values ('CCDC', 'Com Chien Duong Chau', 15, 'Available');
insert into food values ('CCDB', 'Com Chien Dua Bo', 25, 'Available');
insert into food values ('CCBB', 'Com Chien Bo Bam', 25, 'Available');
insert into food values ('MXTT', 'Mi Xao Tom Thit', 15, 'Available');
insert into food values ('TP', 'Tao Pho', 5, 'Available');
insert into food values ('BFL', 'Banh Flan', 10, 'Available');
insert into food values ('CDD', 'Che Dau Do', 5, 'Available');
insert into food values ('NN', 'Nuoc Ngot', 3, 'Available');
insert into food values ('RV', 'Ruou Vang', 20, 'Available');
insert into food values ('RTM', 'Ruou Tao Meo', 5, 'Available');
insert into food values ('RNHT', 'Ruou Nep Ha Tinh', 5, 'Available');
insert into food values ('NL', 'Nuoc Loc', 2, 'Available');
go


create table checkin
(
			idCheckin int primary key,
			checkinDate date not null,
			checkinTime varchar(15) not null,
			idBooking int not null,
			constraint FK_checkin_booking foreign key (idBooking) references booking(idBooking)
);
go

create table checkout
(
			idCheckout int primary key,
			checkoutDate date not null,
			checkoutTime varchar(15) not null,
			idBooking int not null,
			servicesPrice int not null,
			totalPrice int not null,
			constraint FK_checkout_booking foreign key (idBooking) references booking(idBooking)
);
go

insert into checkout values
			(0, '2001-01-01', '12:34', 1, 0, 0);
go

create table customer
(
			idCus int primary key,
			idBooking int not null,
			idCheckin int not null,
			idCheckout int not null,
			name varchar(200) not null,
			email varchar(200) not null,
			nationality varchar(100) not null,
			mobilePhone varchar(20) not null,
			gender varchar(10) not null,
			idProof varchar(20) not null,
			address varchar(200) not null,
			roomNO varchar(10) not null,
			constraint FK_customer_booking foreign key (idBooking) references booking(idBooking),
			constraint FK_customer_checkin foreign key (idCheckin) references checkin(idCheckin),
			constraint FK_customer_checkout foreign key (idCheckout) references checkout(idCheckout)
);
go

create table foodOrder
(
			idOrder int primary key,
			idCus int not null,
			orderDate date not null,
			orderTime varchar(20) not null,
			totalPrice int not null,
			constraint FK_foodOrder_customer foreign key (idCus) references customer(idCus)
);
go

create table detailFoodOrder
(
			idOrder int,
			idFood varchar(10),
			qty int not null,
			price int not null,
			amount int not null,
			constraint PK_DetailFoodOrder primary key (idOrder,idFood),
			constraint FK_Food_ID foreign key (idFood) references food(idFood),
			constraint FK_Order_ID foreign key (idOrder) references foodOrder(idOrder)
);
go

