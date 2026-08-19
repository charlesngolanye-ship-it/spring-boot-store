-- 1. Insert Categories
INSERT INTO categories (name)
VALUES ('Produce'),
       ('Dairy & Eggs'),
       ('Bakery'),
       ('Pantry & Staples'),
       ( 'Beverages');

-- 2. Insert Products
INSERT INTO products (name, price, description, category_id)
VALUES
-- Produce
('Organic Bananas (Bunch)', 1.99, 'Fresh organic bananas, approx. 3 lbs per bunch.', 1),
('Honeycrisp Apples (1 lb)', 2.49, 'Crisp, sweet, and juicy Honeycrisp apples locally sourced.', 1),

-- Dairy & Eggs
('Grade A Large White Eggs (12 ct)', 3.29, 'One dozen cage-free, Grade A large eggs.', 2),
('Whole Milk (1 Gallon)', 4.19, 'Pasteurized grade A whole milk with 3.25% milkfat.', 2),
('Greek Whole Milk Yogurt - Vanilla (32 oz)', 5.49,
 'Rich and creamy probiotic Greek yogurt flavored with real vanilla.', 2),

-- Bakery
('Artisanal Sourdough Bread', 4.99, 'Freshly baked sourdough loaf with a crispy crust and chewy center.', 3),
('Whole Wheat Bagels (6 ct)', 3.79, 'Pre-sliced 100% whole wheat bagels rich in fiber.', 3),

-- Pantry & Staples
('Extra Virgin Olive Oil (16.9 fl oz)', 8.99, 'Cold-pressed extra virgin olive oil imported from Italy.', 4),
('Organic Black Beans (15 oz can)', 1.29, 'Low-sodium canned organic black beans, ready to eat.', 4),

-- Beverages
('Sparkling Mineral Water (12 pk)', 6.99, 'Zero-calorie crisp sparkling mineral water in 12 oz aluminum cans.', 5);