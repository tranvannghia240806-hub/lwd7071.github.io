-- Chay truoc khi start ung dung
CREATE DATABASE webst2_restapi;
GO
USE webst2_restapi;
GO
-- Hibernate tu tao 2 bang (ddl-auto=update):
-- categories(category_id PK, category_name, icon)
-- products(product_id PK, product_name, quantity, unit_price, images,
--           description, discount, create_date, status, category_id FK)
