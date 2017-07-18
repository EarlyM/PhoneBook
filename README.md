CREATE TABLE `users` (
  `username` varchar(50) NOT NULL,
  `password` varchar(50) NOT NULL,
  `role` varchar(15) NOT NULL,
  `fio` varchar(50) NOT NULL,
  PRIMARY KEY (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8

CREATE TABLE `contacts` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `surname` varchar(50) NOT NULL,
  `name` varchar(50) NOT NULL,
  `patronymic` varchar(50) NOT NULL,
  `mobile_phone` varchar(15) NOT NULL,
  `home_phone` varchar(15) DEFAULT NULL,
  `address` varchar(50) DEFAULT NULL,
  `e_mail` varchar(50) DEFAULT NULL,
  `username` varchar(50) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `contacts_ibfk_1_idx` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8

property file example

mvc.prefix=/WEB-INF/
mvc.suffix=.jsp
spring.http.encoding.charset=UTF-8
dataSource.enabled=true
dataSource.driverClassName=com.mysql.jdbc.Driver
dataSource.url=jdbc:mysql://localhost:3306/phonebook
dataSource.username=root
dataSource.password=root
hibernate.dialect=org.hibernate.dialect.MySQLDialect
hibernate.hbm2ddl.auto=update

or

mvc.prefix=/WEB-INF/
mvc.suffix=.jsp
spring.http.encoding.charset=UTF-8
dataSource.enabled=true
filePath.user=D:/user.csv
filePath.contact=D:/contact.csv