INSERT INTO kitchen (id, name) VALUES (1, 'French');
INSERT INTO kitchen (id, name) VALUES (2, 'Americaninha');
INSERT INTO kitchen (id, name) VALUES (3, 'Indiana');
INSERT INTO restaurant (name, shipping_fee, kitchen_id) VALUES ('El Gourmet', 15.0, 1);
INSERT INTO restaurant (name, shipping_fee, kitchen_id) VALUES ('Tradição Mineira', 30.0, 2);
INSERT INTO restaurant (name, shipping_fee, kitchen_id) VALUES ('Kinpai', 25.0, 3);

INSERT INTO state (id, name) VALUES (1, 'Minas Gerais');
INSERT INTO state (id, name) VALUES (2, 'São Paulo');
INSERT INTO state (id, name) VALUES (3, 'Ceará');

INSERT INTO city (id, name, state_id) VALUES (1, 'Uberlândia', 1);
INSERT INTO city (id, name, state_id) VALUES (2, 'Belo Horizonte', 1);
INSERT INTO city (id, name, state_id) VALUES (3, 'São Paulo', 2);
INSERT INTO city (id, name, state_id) VALUES (4, 'Campinas', 2);
INSERT INTO city (id, name, state_id) VALUES (5, 'Fortaleza', 3);

INSERT INTO payment_method (id, description, restaurant_id) VALUES (1, 'Credit Card', 1);
INSERT INTO payment_method (id, description, restaurant_id) VALUES (2, 'Debit Card', 2);
INSERT INTO payment_method (id, description, restaurant_id) VALUES (3, 'Cash', 3);

INSERT INTO permission (id, name, description) VALUES (1, 'SELECT_KITCHENS', 'Allow select kitchens');
INSERT INTO permission (id, name, description) VALUES (2, 'EDIT_KITCHENS', 'Allow edit kitchens');