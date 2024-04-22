This is a Demo Cards Application 

Technologies going to use,
Java 17
Spring Boot 2.5.4
JPA
MySQL
Lombok
Maven
API Documentation swagger 2
IntelliJ Idea for IDE

Dependency
– MySQL:

<dependency>
  <groupId>com.mysql</groupId>
  <artifactId>mysql-connector-j</artifactId>
  <scope>runtime</scope>
</dependency>

Configure Spring Datasource, JPA, App properties
Open src/main/resources/application.yml

For MySQL
spring.datasource.url=jdbc:mysql://localhost:3306/cards_snapshot?useSSL=false
spring.datasource.username=root
spring.datasource.password=Pass123

spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQLDialect
spring.jpa.hibernate.ddl-auto=update

# App Properties
tokenSecret: card24kjljtrt

**Run Spring Boot application**
mvn spring-boot:run

Run following SQL insert statements for user inserts

INSERT INTO cards_snapshot.users (
    email_address,
    role,
    active,
    user_token,
    user_id,
    first_name,
    last_name,
    password,
    encrypted_password,
    created_on,
    updated_on
) VALUES (
    'karis@gmail.com',
    0, -- Assuming UserRole is an enum with values like 'USER', 'ADMIN', etc.
    true,
    '$2a$10$NXv3tDNhPXU3FVoP34ugAudt7utp9YD.WsiTC9xKVUutBZCvHlE.O',
    'user002',
    'mike',
    'karis',
    '1234567',
    '$2a$10$NXv3tDNhPXU3FVoP34ugAudt7utp9YD.WsiTC9xKVUutBZCvHlE.O',
    '2024-04-19 12:00:00', -- Format: 'YYYY-MM-DD HH:MM:SS'
    '2024-04-19 12:00:00'
);


INSERT INTO cards_snapshot.users (
    email_address,
    role,
    active,
    user_token,
    user_id,
    first_name,
    last_name,
    password,
    encrypted_password,
    created_on,
    updated_on
) VALUES (
    'sucre@gmail.com',
    1, -- Assuming UserRole is an enum with values like 'USER', 'ADMIN', etc.
    true,
    '$2a$10$NXv3tDNhPXU3FVoP34ugAudt7utp9YD.WsiTC9xKVUutBZCvHlE.O',
    'user002',
    'mike',
    'karis',
    '1234567',
    '$2a$10$NXv3tDNhPXU3FVoP34ugAudt7utp9YD.WsiTC9xKVUutBZCvHlE.O',
    '2024-04-19 12:00:00', -- Format: 'YYYY-MM-DD HH:MM:SS'
    '2024-04-19 12:00:00'
);

