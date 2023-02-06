DROP TABLE "USERDATA";
CREATE TABLE "USERDATA"
(
"USER_ID" INT not null primary key
        GENERATED ALWAYS AS IDENTITY
        (START WITH 0, INCREMENT BY 1),   
"EMAIL" VARCHAR(50), 
"PASSWORD" VARCHAR(50), 
"FNAME" VARCHAR(50),   
"LNAME" VARCHAR(50),
"USERTYPE" VARCHAR(10)
);
DROP TABLE "PRODUCTS";
CREATE TABLE "PRODUCTS"
(
"PRODUCT_ID" INT not null primary key
        GENERATED ALWAYS AS IDENTITY
        (START WITH 0, INCREMENT BY 1),
"NAME" VARCHAR(50),
"DESCRIPTION" VARCHAR(100),   
"PRICE" FLOAT, 
"IMAGE_LOCATION" VARCHAR(100),
"CATEGORY" VARCHAR(30)
); 