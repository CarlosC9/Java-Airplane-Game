drop database if exists db_game_airplane;

create database db_game_airplane;

use db_game_airplane;

create table url_images (
id_Img int auto_increment primary key,
ruta_img varchar(30)
);

insert into url_images values(null,"/resources/airplane.png");

select * from url_images;