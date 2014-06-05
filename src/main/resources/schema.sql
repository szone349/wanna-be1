drop table CONTACT;
CREATE TABLE if not exists CONTACT (
  id INT NOT NULL IDENTITY,
  name varchar(100) NOT NULL,
  address varchar(200) NOT NULL,  
  phonenumber varchar(100) NOT NULL);
