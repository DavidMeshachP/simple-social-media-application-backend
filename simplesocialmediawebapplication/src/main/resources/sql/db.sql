-- -- table for users 

-- CREATE TABLE users (
--     id INT PRIMARY KEY AUTO_INCREMENT,
--     user_name VARCHAR(50) NOT NULL,
--     email_id VARCHAR(50) UNIQUE NOT NULL,
--     gender ENUM('Male', 'Female') NOT NULL,
--     image VARCHAR(100),
--     age INT NOT NULL,
--     bio VARCHAR(255) NOT NULL,
--     password VARCHAR(30) NOT NULL,
--     created DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
--     modified DATETIME DEFAULT NULL
-- );

-- -- table for friends

-- CREATE TABLE friends (
--     user_id INT,
--     friend_id INT,
--     created DATETIME DEFAULT CURRENT_TIMESTAMP,
--     modified DATETIME DEFAULT NULL,
--     status INT NOT NULL,
--     FOREIGN KEY (user_id)
--         REFERENCES users (id),
--     FOREIGN KEY (user_id)
--         REFERENCES users (id)
-- );

-- -- table for posts

-- CREATE TABLE posts (
--     id INT PRIMARY KEY AUTO_INCREMENT,
--     user_id INT NOT NULL,
--     image VARCHAR(100) NOT NULL,
--     caption VARCHAR(50),
--     mode ENUM('private', 'public') DEFAULT 'private' NOT NULL,
--     created DATETIME DEFAULT CURRENT_TIMESTAMP NOT NULL,
--     modified DATETIME DEFAULT NULL,
--     FOREIGN KEY (user_id)
--         REFERENCES users (id)
-- );

-- -- table for likes

-- CREATE TABLE likes (
--     id INT PRIMARY KEY AUTO_INCREMENT,
--     user_id INT NOT NULL,
--     post_id INT NOT NULL,
--     FOREIGN KEY (user_id)
--         REFERENCES users (id),
--     FOREIGN KEY (post_id)
--         REFERENCES posts (id)
-- );

-- -- table for comments

-- CREATE TABLE comments (
--     post_id INT NOT NULL,
--     user_id INT NOT NULL,
--     comments VARCHAR(250) NOT NULL,
--     created DATETIME DEFAULT CURRENT_TIMESTAMP NOT NULL,
--     modified DATETIME,
--     FOREIGN KEY (post_id)
--         REFERENCES posts (id),
--     FOREIGN KEY (user_id)
--         REFERENCES users (id)
-- );

-- -- comment
 
-- select * 
-- 	from comment 
--     where user_id = 1;
    
-- select * 
-- 	from comment
-- 	where id = 1;
    
-- delete
-- 	from comment
-- 	where id = 1;
    
-- insert into comment(user_id, post_id, comment, created) 
-- values(1, 1, "This is the post..",current_timestamp());

-- desc comment;

-- update comment 
-- 	set comment = "wow!! this is a nice one "
--     where user_id = 1;
    
-- select * 
-- 	from comment
--     where post_id = 1;
    
-- -- friend

-- insert into friend(user_id, friend_id, status)
-- values(1, 2, "Accepted");

-- select friend_id from friend where user_id = 1;

-- select user_id from friend where user_id = 1;

-- delete from friend where user_id = 1;

-- select * from friend where user_id = 1;


-- -- like

-- insert into likes(id, user_id, post_id)
-- values(1, 1, 1);

-- select * from likes where id = 1;

-- select * from likes where post_id = 1;

-- delete from likes where id = 1;


-- -- post

-- insert into post(id, user_id, image, caption, mode)
-- values(1, 1, "kqsuweid","wow","Public");

-- update post
-- set caption = "wow! Nice Post"
-- where id = 1;

-- select * from post where id = 1;

-- delete from post where id = 1;

-- select * from post where user_id = 1;


-- -- user

-- insert into user(id, user_name, email_id, gender, image, age, bio, password)
-- values(1,"john","john@gmail.com","Male","qsldmejt",24,"hello I am john", "a;dfkj234;kdf");

-- select * from user where id = 1;

-- update user 
-- set bio = "hello I am John and programmer"
-- where id = 1;

-- delete from user where id = 1;

-- select * from user where user_name = "john";

-- users table

CREATE TABLE users (
    id INT PRIMARY KEY AUTO_INCREMENT,
    user_name VARCHAR(50) NOT NULL,
    email_id VARCHAR(255) UNIQUE NOT NULL,
    gender ENUM('Male', 'Female', 'Transgender') NOT NULL,
    image BLOB,
    date_of_birth DATE NOT NULL,
    description TEXT NOT NULL,
    password VARCHAR(20) NOT NULL,
    created TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    modified TIMESTAMP DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP
);

alter table users
modify column image mediumblob;


-- check constraint cannot be used with curdate or current_date() functions
alter table users 
add constraint check_dob
check(date_of_birth between (current_date() - interval 100 year) and (current_date() - interval 14 year));

delimiter //

//

create trigger check_dob_before_insert
before insert on users
for each row
begin

	if new.date_of_birth not between(current_date() - interval 100 year) and (current_date() - interval 14 year) 
		then
			signal sqlstate '45000'
            set message_text = 'the date of birth is not between the age gap.';
	end if;

end;

//

delimiter ;

delimiter //

//

create trigger check_dob_before_update
before update on users
for each row
begin

	if new.date_of_birth not between(current_date() - interval 100 year) and (current_date() - interval 14 year) 
		then
			signal sqlstate '45000'
            set message_text = 'the date of birth is not between the age gap.(update)';
	end if;

end;

//

delimiter ;

-- first value
insert into users(user_name,email_id,gender,image,date_of_birth,description,password)
values('john','john@gmail.com','Male',unhex('89504E470D0A1A0A0000000D4948445200000005000000070806000000AA527DCA0000000A49444154789C630001000003000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000'),'2000-01-12','hello i am john','john@john');

-- 1st value update check for update age gap costraint(success)
update users
set date_of_birth = '1899-01-01'
where id = 1;

-- 1st value update check 
update users
set date_of_birth = '2001-01-01'
where id = 1;

-- error for age gap
insert into users(user_name,email_id,gender,image,date_of_birth,description,password)
values('john','jojhn@gmail.com','Male',unhex('89504E470D0A1A0A0000000D4948445200000005000000070806000000AA527DCA0000000A49444154789C630001000003000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000'),'1899-01-12','hello i am john','john@john');

-- entering large blob data check ( the image data is too long , it is inserting , check notepad file in desktp for the image binary data )

-- insert into users(user_name,email_id,gender,image,date_of_birth,description,password)
-- values('john','jojhn@gmail.com','Male','','2000-01-12','hello i am john','john@john');

select * from users;

-- friends table

CREATE TABLE friends (
    user_id INT,
    friend_id INT,
    created TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    modified TIMESTAMP DEFAULT NULL,
    status ENUM('Accepted', 'Rejected', 'Pending') NOT NULL,
    FOREIGN KEY (user_id)
        REFERENCES users (id),
    FOREIGN KEY (friend_id)
        REFERENCES users (id)
);

-- user_id and friend_id different check
alter table friends
add constraint check_both_id_are_same_or_not
check(user_id != friend_id);

-- trigger to check whether the inserted value is the inverse value of existing value

delimiter //

//

create trigger check_inverse_insertion_for_friends
before insert on friends
for each row
begin

	declare friend_count int;
    
    set friend_count = 0;
    
    select count(*) into friend_count
    from friends
    where (NEW.user_id,NEW.friend_id) in ((user_id,friend_id),(friend_id,user_id));
    
    if friend_count > 0 then
		signal sqlstate '45000'
        set message_text = 'inverse insertion of already present record is not allowed, try updating the available record';
    end if;

end;

//

delimiter ;

drop trigger if exists check_inverse_insertion_for_friends;

-- 1st insert check 
insert into friends(user_id,friend_id,status)
values('1','2','Accepted');

-- insert same user_id check success.
insert into friends(user_id,friend_id,status)
values('1','1','Rejected');

-- insert invert user and friend id check for trigger success
insert into friends(user_id,friend_id,status)
values('2','1','Rejected');

select * from friends;

-- posts table 

CREATE TABLE posts (
    id INT PRIMARY KEY AUTO_INCREMENT,
    user_id INT NOT NULL,
    caption TEXT,
    mode ENUM('Private', 'Public') DEFAULT 'Private' NOT NULL,
    created TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
    modified TIMESTAMP DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
    FOREIGN KEY (user_id)
        REFERENCES users (id)
);

-- 1st insert ok
insert into posts(user_id,caption,mode)
values('1','Deep in History','Public');

delete from posts where id = 2;

insert into posts(user_id,caption,mode)
values('1','Long Ago','Private');

-- posts_images table 

create table posts_images (
	id int primary key auto_increment,
	post_id int not null,
    image mediumblob not null,
    foreign key (post_id)
		references posts(id)
);

-- 1st insert for posts_images success image data too long in a file in desktop
insert into posts_images(post_id,image)
values(1,'');

-- likes

CREATE TABLE likes (
    id INT PRIMARY KEY AUTO_INCREMENT,
    user_id INT NOT NULL,
    post_id INT NOT NULL,
    FOREIGN KEY (user_id)
        REFERENCES users (id),
    FOREIGN KEY (post_id)
        REFERENCES posts (id)
);

alter table likes 
add constraint check_same_like_or_not
unique(user_id,post_id);

-- 1st entry done, second entry for same entry constraint fails ok
insert into likes(user_id,post_id)
values('1','1');

update likes 
set user_id = '2'
where id = 1;

select * from likes;

-- comments table 

CREATE TABLE comments (
    id INT PRIMARY KEY AUTO_INCREMENT,
    post_id INT NOT NULL,
    user_id INT NOT NULL,
    comments TEXT NOT NULL,
    created DATETIME DEFAULT CURRENT_TIMESTAMP NOT NULL,
    modified DATETIME DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
    FOREIGN KEY (post_id)
        REFERENCES posts (id),
    FOREIGN KEY (user_id)
        REFERENCES users (id)
);

insert into comments(post_id,user_id,comments) 
values('1','1','wooo hoooo woooo hoooo');

insert into comments(post_id,user_id,comments) 
values('1','1','whaaaaaaaaaaaaaaaatt!!');

update comments 
set comments = 'woooooooowww!!'
where id = '2';

select * from comments;