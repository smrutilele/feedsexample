drop table FEEDS_USER if exists;
create table FEEDS_USER (
  userName varchar(50) primary key not null,
  password varchar(50) not null
);

drop table FEEDS_USER_FOLLOWER if exists;
create table FEEDS_USER_FOLLOWER (
  userName varchar(50) not null,
  followerUserName varchar(50) not null
);

drop table FEEDS_MESSAGE if exists;
create table FEEDS_MESSAGE (
  messageId integer primary key AUTO_INCREMENT,
  userName varchar(50) not null,
  textMessage varchar(250) not null,
  publishDate timestamp
);
