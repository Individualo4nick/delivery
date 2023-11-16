Create table card_data (
    id int Primary key auto_increment,
    card_number VARCHAR(30) NOT NULL UNIQUE,
    card_date VARCHAR(5) NOT NULL UNIQUE
);
Create table user(
    id int Primary key auto_increment,
    username VARCHAR(30) NOT NULL UNIQUE,
    password VARCHAR(150) NOT NULL UNIQUE,
    roleUser smallint not null
);
Create table type_sale (
    id int Primary key auto_increment,
    title VARCHAR(30) NOT NULL
);
Create table courier (
    id int Primary key auto_increment,
    name VARCHAR(50) NOT NULL,
    surname VARCHAR(50) NOT NULL,
    salary SMALLINT NOT NULL,
    order_count INT DEFAULT 0 NOT NULL
);
Create table client (
    id int Primary key auto_increment,
    name VARCHAR(30) NOT NULL,
    surname VARCHAR(30) NOT NULL,
    address VARCHAR(100) NOT NULL,
    type_sale_id INT DEFAULT 1,
    order_count INT DEFAULT 0 NOT NULL,
    card_data_id INT NOT NULL,
    foreign key (card_data_id) references card_data (id) on update cascade on delete restrict,
    foreign key (type_sale_id) references type_sale (id) on update cascade on delete restrict
);

Create table clientorder (
    id int Primary key auto_increment,
    title VARCHAR(50) NOT NULL,
    description VARCHAR(100) NOT NULL,
    total DECIMAL(4) NOT NULL,
    stage VARCHAR(30) NOT NULL,
    courier_id INT,
    client_id INT,
    foreign key (courier_id) references courier (id) on update cascade on delete restrict,
    foreign key (client_id) references client (id) on update cascade on delete restrict
);
Create table support (
    id int Primary key auto_increment,
    name VARCHAR(50) NOT NULL,
    surname VARCHAR(50) NOT NULL,
    salary DECIMAL(4) NOT NULL,
    number VARCHAR(12) NOT NULL
);
Create table product (
    id int Primary key auto_increment,
    title VARCHAR(50) NOT NULL,
    description VARCHAR(100) NOT NULL,
    price DECIMAL(4) NOT NULL,
    order_id INT,
    foreign key (order_id) references clientorder (id) on update cascade on delete restrict
);
Create table client_and_courier (
    client_id INT NOT NULL,
    courier_id INT NOT NULL,
    primary key CLUSTERED(client_id ASC, courier_id ASC),
    foreign key (client_id) references client (id) on update cascade on delete restrict,
    foreign key (courier_id) references courier(id) on update cascade on delete restrict
);
Create table chat_with_support (
    client_id INT,
    support_id INT,
    primary key CLUSTERED(client_id ASC, support_id ASC),
    foreign key (client_id) references client (id) on update cascade on delete restrict,
    foreign key (support_id) references support (id) on update cascade on delete restrict
);
Create table transport (
    id int Primary key auto_increment,
    title VARCHAR(30),
    courier_id INT,
    foreign key (courier_id) references courier (id) on update cascade on delete restrict
);


INSERT INTO card_data (card_number, card_date)
VALUES
    ('12345678', '10/25'),
    ('12312312', '11/24'),
    ('12341234', '12/26'),
    ('12345123', '11/23'),
    ('87654321', '06/28');
INSERT INTO type_sale (title)
VALUES
    ('0%'),
    ('5%'),
    ('10%'),
    ('15%'),
    ('20%');
INSERT INTO client (name, surname, address, card_data_id)
VALUES
    ('Name1', 'Surname1', 'Address1', 1),
    ('Name2', 'Surname2', 'Address2', 2),
    ('Name3', 'Surname3', 'Address3', 3),
    ('Name4', 'Surname4', 'Address4', 4),
    ('Name5', 'Surname5', 'Address5', 5);
INSERT INTO courier (name, surname, salary)
VALUES
    ('Name1', 'Surname1', 1000),
    ('Name2', 'Surname2', 2000),
    ('Name3', 'Surname3', 3000),
    ('Name4', 'Surname4', 4000),
    ('Name5', 'Surname5', 5000);
INSERT INTO clientorder (title, description, total, stage, client_id, courier_id)
VALUES
    ('Title1', 'Description1', 600, 'Stage1', 1, 1),
    ('Title2', 'Description2', 700, 'Stage2', 2, 2),
    ('Title3', 'Description3', 300, 'Stage3', 3, 3),
    ('Title4', 'Description4', 400, 'Stage4', 4, 4),
    ('Title5', 'Description5', 500, 'Stage5', 5, 5);
INSERT INTO product (title, description, price, order_id)
VALUES
    ('Title1', 'Description1', 100, 1),
    ('Title2', 'Description2', 200, 2),
    ('Title3', 'Description3', 300, 3),
    ('Title4', 'Description4', 400, 4),
    ('Title5', 'Description5', 500, 5);
INSERT INTO support (name, surname, salary, number)
VALUES
    ('Name1', 'Surname1', 1000, 'Number1'),
    ('Name2', 'Surname2', 2000, 'Number2'),
    ('Name3', 'Surname3', 3000, 'Number3'),
    ('Name4', 'Surname4', 4000, 'Number4'),
    ('Name5', 'Surname5', 5000, 'Number5');
INSERT INTO transport (title, courier_id)
VALUES
    ('Title1', 1),
    ('Title2', 2),
    ('Title3', 3),
    ('Title4', 4),
    ('Title5', 5);
INSERT INTO client_and_courier (client_id, courier_id)
VALUES
    (1, 1),
    (2, 2),
    (3, 3),
    (4, 4),
    (5, 5);
INSERT INTO chat_with_support (client_id, support_id)
VALUES
    (1, 1),
    (2, 2),
    (3, 3),
    (4, 4),
    (5, 5);
