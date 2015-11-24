# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table language (
  repo_name                 varchar(255),
  repo_lang                 varchar(255))
;

create table recommendation (
  repository_name           varchar(255) not null,
  repository_owner          varchar(255),
  actor                     varchar(255),
  type                      varchar(255),
  constraint pk_recommendation primary key (repository_name))
;




# --- !Downs

SET FOREIGN_KEY_CHECKS=0;

drop table language;

drop table recommendation;

SET FOREIGN_KEY_CHECKS=1;

