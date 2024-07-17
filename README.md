Database Setup

create a database named `blog_db`.
Execute the following SQL queries to create tables for users and posts:


-- Create users table
CREATE TABLE `users` (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    email VARCHAR(100) UNIQUE NOT NULL,
    password VARCHAR(255) NOT NULL,
    role VARCHAR(50) NOT NULL
);



-- Create posts table
CREATE TABLE `posts` (
    id INT AUTO_INCREMENT PRIMARY KEY,
    title VARCHAR(255) NOT NULL,
    content TEXT,
    imageUrl VARCHAR(255),
    videoUrl VARCHAR(255),
    createdAt TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updatedAt TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);


IDE Setup

Import the project into your Java IDE (Eclipse).

Dependencies
Ensure mysql-connector-java-x.y.z.jar
commons-io-1.3.2.jar
commons-lang3-3.14.0.jar
jbcrypt-0.4.jar
commons-io-1.3.2.jar

is included in the WEB-INF/lib directory.
Configuration

Update database connection details in DBUtil.java .
Deployment

Deploy the project on a servlet container like Apache Tomcat.
Usage
Admin Tasks: Log in as Admin to manage posts via the Admin dashboard.
Viewer Interaction: Browse posts, view details, and search for specific posts.
Security
Password Security: Store passwords securely using BCrypt hashing.
Data Validation: Prevent SQL injection with prepared statements.
