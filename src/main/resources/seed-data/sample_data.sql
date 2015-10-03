DELETE FROM user;
DELETE FROM social_user;


INSERT INTO user (id, email_address, first_name, last_name, uuid) VALUES (2005, 'wendel.schultz@gmail.com', 'Wendel', 'Schultz', 'f68de1ab-8bce-4800-ad4f-35319ed1e9b7');
INSERT INTO social_user(id, type_id, uuid, user_id) VALUES (1000, 'FacebookProfile#10153112725031845', '2be6bd01-6f3a-4f34-825a-7555bfd2b133', 2005);

