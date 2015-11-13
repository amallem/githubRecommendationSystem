# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table language (
  repo_name                 varchar(255) not null,
  repo_lang                 varchar(255),
  constraint pk_language primary key (repo_name))
;




# --- !Downs

SET FOREIGN_KEY_CHECKS=0;

drop table language;

SET FOREIGN_KEY_CHECKS=1;

