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
);

-- comment
 
select * 
	from comment 
    where user_id = 1;
    
select * 
	from comment
	where id = 1;
    
delete
	from comment
	where id = 1;
    
insert into comment(user_id, post_id, comment, created) 
values(1, 1, "This is the post..",current_timestamp());

desc comment;

update comment 
	set comment = "wow!! this is a nice one "
    where user_id = 1;
    
select * 
	from comment
    where post_id = 1;
    
-- friend

insert into friend(user_id, friend_id, status)
values(1, 2, "Accepted");

select friend_id from friend where user_id = 1;

select user_id from friend where user_id = 1;

delete from friend where user_id = 1;

select * from friend where user_id = 1;


-- like

insert into likes(id, user_id, post_id)
values(1, 1, 1);

select * from likes where id = 1;

select * from likes where post_id = 1;

delete from likes where id = 1;


-- post

insert into post(id, user_id, image, caption, mode)
values(1, 1, "kqsuweid","wow","Public");

update post
set caption = "wow! Nice Post"
where id = 1;

select * from post where id = 1;

delete from post where id = 1;

select * from post where user_id = 1;


-- user

insert into user(id, user_name, email_id, gender, image, age, bio, password)
values(1,"john","john@gmail.com","Male","qsldmejt",24,"hello I am john", "a;dfkj234;kdf");

select * from user where id = 1;

update user 
set bio = "hello I am John and programmer"
where id = 1;

delete from user where id = 1;

select * from user where user_name = "john";