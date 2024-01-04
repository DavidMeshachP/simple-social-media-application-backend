-- table for users 

CREATE TABLE users (
    id INT PRIMARY KEY AUTO_INCREMENT,
    user_name VARCHAR(50) NOT NULL,
    email_id VARCHAR(50) UNIQUE NOT NULL,
    gender ENUM('Male', 'Female') NOT NULL,
    image VARCHAR(100),
    age INT NOT NULL,
    bio VARCHAR(255) NOT NULL,
    password VARCHAR(30) NOT NULL,
    created DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    modified DATETIME DEFAULT NULL
);

-- table for friends

CREATE TABLE friends (
    user_id INT,
    friend_id INT,
    created DATETIME DEFAULT CURRENT_TIMESTAMP,
    modified DATETIME DEFAULT NULL,
    status INT NOT NULL,
    FOREIGN KEY (user_id)
        REFERENCES users (id),
    FOREIGN KEY (user_id)
        REFERENCES users (id)
);

-- table for posts

CREATE TABLE posts (
    id INT PRIMARY KEY AUTO_INCREMENT,
    user_id INT NOT NULL,
    image VARCHAR(100) NOT NULL,
    caption VARCHAR(50),
    mode ENUM('private', 'public') DEFAULT 'private' NOT NULL,
    created DATETIME DEFAULT CURRENT_TIMESTAMP NOT NULL,
    modified DATETIME DEFAULT NULL,
    FOREIGN KEY (user_id)
        REFERENCES users (id)
);

-- table for likes

CREATE TABLE likes (
    id INT PRIMARY KEY AUTO_INCREMENT,
    user_id INT NOT NULL,
    post_id INT NOT NULL,
    FOREIGN KEY (user_id)
        REFERENCES users (id),
    FOREIGN KEY (post_id)
        REFERENCES posts (id)
);

-- table for comments

CREATE TABLE comments (
    post_id INT NOT NULL,
    user_id INT NOT NULL,
    comments VARCHAR(250) NOT NULL,
    created DATETIME DEFAULT CURRENT_TIMESTAMP NOT NULL,
    modified DATETIME,
    FOREIGN KEY (post_id)
        REFERENCES posts (id),
    FOREIGN KEY (user_id)
        REFERENCES users (id)
);`