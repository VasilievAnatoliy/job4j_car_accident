CREATE TABLE IF NOT EXISTS type (
    id serial primary key,
    name varchar(2000)
);

CREATE TABLE IF NOT EXISTS accident (
    id serial primary key,
    name varchar(2000),
    text varchar(2000),
    address varchar(2000),
    type_id INT NOT NULL REFERENCES type(id)
);

CREATE TABLE IF NOT EXISTS rule (
    id serial primary key,
    name varchar(2000)
);

CREATE TABLE IF NOT EXISTS accident_rule (
    accident_id INT NOT NULL REFERENCES accident(id),
    rule_id INT NOT NULL REFERENCES rule(id)
);

insert into type(name)values('Две машины'), ('Машина и человек'), ('Машина и велосипед');

insert into accident(name, text, address, type_id)
            values('Лобовое столкновение', 'Описание столкновения','address1', '1');
insert into accident(name, text, address, type_id)
            values('Сбит человек на переходе', 'Описание происшествия','address2', '2');
insert into accident(name, text, address, type_id)
            values('ДТП во дворе', 'Описание ситуации','address3', '3');

insert into rule(name) values('Статья 1'),('Статья 2'),('Статья 3'),('Статья 4');
insert into accident_rule(accident_id, rule_id) values('1', '1'), ('2', '2'), ('3', '3'), ('3', '4');