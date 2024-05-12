--categories
INSERT INTO categories (category_name, image_url) VALUES ('Rau c?, trái cây', 'Rau_cu_trai_cay.jpeg');
INSERT INTO categories (category_name, image_url) VALUES ('Th?t, tr?ng, h?i s?n', 'Thit_trung_hai_san.jpeg');
INSERT INTO categories (category_name, image_url) VALUES ('Th?c ?n ch? bi?n, bún t??i', 'Thuc_an_che_bien_bun_tuoi.jpeg');
INSERT INTO categories (category_name, image_url) VALUES ('Th?c ph?m ?ông mát', 'Thuc_pham_dong_mat.jpeg');
INSERT INTO categories (category_name, image_url) VALUES ('Bánh, k?o, snack', 'Banh_keo_snack.jpeg');
INSERT INTO categories (category_name, image_url) VALUES ('S?a, s?n ph?m t? s?a', 'Sua_san_pham_tu_sua.jpeg');
INSERT INTO categories (category_name, image_url) VALUES ('Th?c u?ng', 'Thuc_uong.jpeg');
INSERT INTO categories (category_name, image_url) VALUES ('Gia v?, g?o, th?c ph?m khô', 'Gia_vi_gao_thuc_pham_kho.jpeg');
INSERT INTO categories (category_name, image_url) VALUES ('S?n ph?m cho bé', 'San_pham_cho_be.jpeg');
INSERT INTO categories (category_name, image_url) VALUES ('Ch?m sóc cá nhân', 'Cham_soc_ca_nhan.jpeg');
INSERT INTO categories (category_name, image_url) VALUES ('Nhà c?a và ??i s?ng', 'Nha_cua_va_doi_song.jpeg');

--products
    -- Category: Rau c?, trái cây
    INSERT INTO products (product_name, price, category_id, description, quantity) VALUES ('Cà chua', 15000, 1, 'Cà chua s?ch', 4);
    INSERT INTO products (product_name, price, category_id, description, quantity) VALUES ('D?a h?u', 30000, 1, 'D?a h?u t??i ngon', 4);
    INSERT INTO products (product_name, price, category_id, description, quantity) VALUES ('Bí ??', 20000, 1, 'Bí ?? giàu dinh d??ng', 4);
    INSERT INTO products (product_name, price, category_id, description, quantity) VALUES ('D?a', 25000, 1, 'D?a t??i ng?t', 4);
    INSERT INTO products (product_name, price, category_id, description, quantity) VALUES ('Chu?i', 12000, 1, 'Chu?i chín m?ng', 4);
    
    -- Category: Th?t, tr?ng, h?i s?n
    INSERT INTO products (product_name, price, category_id, description, quantity) VALUES ('Th?t bò', 150000, 2, 'Th?t bò nh?p kh?u', 4);
    INSERT INTO products (product_name, price, category_id, description, quantity) VALUES ('Cá h?i', 180000, 2, 'Cá h?i t??i s?ch', 4);
    INSERT INTO products (product_name, price, category_id, description, quantity) VALUES ('Tr?ng gà', 25000, 2, 'Tr?ng gà ??m b?o ch?t l??ng', 4);
    INSERT INTO products (product_name, price, category_id, description, quantity) VALUES ('Tôm', 80000, 2, 'Tôm t??i ngon', 4);
    INSERT INTO products (product_name, price, category_id, description, quantity) VALUES ('Gà ta', 120000, 2, 'Gà ta s?ch', 4);
    
    -- Category: Th?c ?n ch? bi?n, bún t??i
    INSERT INTO products (product_name, price, category_id, description, quantity) VALUES ('Bún riêu', 25000, 3, 'Bún riêu ngon', 4);
    INSERT INTO products (product_name, price, category_id, description, quantity) VALUES ('Bánh mì', 10000, 3, 'Bánh mì m?m giòn', 4);
    INSERT INTO products (product_name, price, category_id, description, quantity) VALUES ('C?m gà', 35000, 3, 'C?m gà h?p d?n', 4);
    INSERT INTO products (product_name, price, category_id, description, quantity) VALUES ('Bánh cu?n', 30000, 3, 'Bánh cu?n th?m ngon', 4);
    INSERT INTO products (product_name, price, category_id, description, quantity) VALUES ('Ph? bò', 40000, 3, 'Ph? bò nóng h?i', 4);
    
    -- Category: Th?c ph?m ?ông mát
    INSERT INTO products (product_name, price, category_id, description, quantity) VALUES ('Kem', 25000, 4, 'Kem s?a ngon', 4);
    INSERT INTO products (product_name, price, category_id, description, quantity) VALUES ('Sinh t?', 35000, 4, 'Sinh t? trái cây t??i', 4);
    INSERT INTO products (product_name, price, category_id, description, quantity) VALUES ('S?a ??u nành', 20000, 4, 'S?a ??u nành nguyên ch?t', 4);
    INSERT INTO products (product_name, price, category_id, description, quantity) VALUES ('Kem chu?i', 30000, 4, 'Kem chu?i th?m ngon', 4);
    INSERT INTO products (product_name, price, category_id, description, quantity) VALUES ('N??c ép trái cây', 25000, 4, 'N??c ép trái cây t??i', 4);
    
    -- Category: Bánh, k?o, snack
    INSERT INTO products (product_name, price, category_id, description, quantity) VALUES ('Bánh quy', 15000, 5, 'Bánh quy socola', 4);
    INSERT INTO products (product_name, price, category_id, description, quantity) VALUES ('K?o', 5000, 5, 'K?o ng?t ngào', 4);
    INSERT INTO products (product_name, price, category_id, description, quantity) VALUES ('Snack khoai tây', 12000, 5, 'Snack khoai tây giòn r?m', 4);
    INSERT INTO products (product_name, price, category_id, description, quantity) VALUES ('Bánh bao', 10000, 5, 'Bánh bao th?m ngon', 4);
    INSERT INTO products (product_name, price, category_id, description, quantity) VALUES ('Bánh gato', 25000, 5, 'Bánh gato b?', 4);
    
    -- Category: S?a, s?n ph?m t? s?a
    INSERT INTO products (product_name, price, category_id, description, quantity) VALUES ('S?a t??i', 20000, 6, 'S?a t??i ngon', 4);
    INSERT INTO products (product_name, price, category_id, description, quantity) VALUES ('B?', 30000, 6, 'B? s?ch', 4);
    INSERT INTO products (product_name, price, category_id, description, quantity) VALUES ('S?a chua', 10000, 6, 'S?a chua t? nhiên', 4);
    INSERT INTO products (product_name, price, category_id, description, quantity) VALUES ('Bánh flan', 25000, 6, 'Bánh flan ng?t ngào', 4);
    INSERT INTO products (product_name, price, category_id, description, quantity) VALUES ('Kem socola', 30000, 6, 'Kem socola th?m ngon', 4);
    
    -- Category: Th?c u?ng
    INSERT INTO products (product_name, price, category_id, description, quantity) VALUES ('N??c ng?t', 10000, 7, 'N??c ng?t l?nh', 4);
    INSERT INTO products (product_name, price, category_id, description, quantity) VALUES ('Cà phê', 20000, 7, 'Cà phê ?en th?m', 4);
    INSERT INTO products (product_name, price, category_id, description, quantity) VALUES ('Trà ?á', 15000, 7, 'Trà ?á th?m ngon', 4);
    INSERT INTO products (product_name, price, category_id, description, quantity) VALUES ('S?a ??c', 15000, 7, 'S?a ??c ng?t ngào', 4);
    INSERT INTO products (product_name, price, category_id, description, quantity) VALUES ('Sinh t? b?', 30000, 7, 'Sinh t? b? th?m ngon', 4);
    
    -- Category: Gia v?, g?o, th?c ph?m khô
    INSERT INTO products (product_name, price, category_id, description, quantity) VALUES ('G?o', 30000, 8, 'G?o ngon', 4);
    INSERT INTO products (product_name, price, category_id, description, quantity) VALUES ('???ng', 20000, 8, '???ng tr?ng', 4);
    INSERT INTO products (product_name, price, category_id, description, quantity) VALUES ('Mu?i', 5000, 8, 'Mu?i bi?n', 4);
    INSERT INTO products (product_name, price, category_id, description, quantity) VALUES ('B?t ng?t', 15000, 8, 'B?t ng?t ?a d?ng', 4);
    INSERT INTO products (product_name, price, category_id, description, quantity) VALUES ('H?t tiêu', 25000, 8, 'H?t tiêu s?ch', 4);
    
    -- Category: S?n ph?m cho bé
    INSERT INTO products (product_name, price, category_id, description, quantity) VALUES ('B?m', 150000, 9, 'B?m cho bé s? sinh', 4);
    INSERT INTO products (product_name, price, category_id, description, quantity) VALUES ('S?a b?t', 200000, 9, 'S?a b?t dành cho tr? s? sinh', 4);
    INSERT INTO products (product_name, price, category_id, description, quantity) VALUES ('D?u g?i', 50000, 9, 'D?u g?i cho tr? nh?', 4);
    INSERT INTO products (product_name, price, category_id, description, quantity) VALUES ('Xà phòng', 20000, 9, 'Xà phòng cho tr? em', 4);
    INSERT INTO products (product_name, price, category_id, description, quantity) VALUES ('Bình s?a', 50000, 9, 'Bình s?a ch?ng ?au b?ng', 4);
    
    -- Category: Ch?m sóc cá nhân
    INSERT INTO products (product_name, price, category_id, description, quantity) VALUES ('Gel t?m', 30000, 10, 'Gel t?m h??ng hoa', 4);
    INSERT INTO products (product_name, price, category_id, description, quantity) VALUES ('Kem ?ánh r?ng', 20000, 10, 'Kem ?ánh r?ng giúp sáng tr?ng r?ng', 4);
    INSERT INTO products (product_name, price, category_id, description, quantity) VALUES ('D?u g?i', 35000, 10, 'D?u g?i ch?m sóc tóc', 4);
    INSERT INTO products (product_name, price, category_id, description, quantity) VALUES ('S?a d??ng th?', 40000, 10, 'S?a d??ng th? làm m?m da', 4);
    INSERT INTO products (product_name, price, category_id, description, quantity) VALUES ('N??c hoa', 100000, 10, 'N??c hoa th?m lâu', 4);
    
    -- Category: Nhà c?a và ??i s?ng
    INSERT INTO products (product_name, price, category_id, description, quantity) VALUES ('Ch?n ga g?i ??m', 500000, 11, 'B? ch?n ga g?i ??m cao c?p', 4);
    INSERT INTO products (product_name, price, category_id, description, quantity) VALUES ('Bát ??a', 150000, 11, 'Bát ??a s? cao c?p', 4);
    INSERT INTO products (product_name, price, category_id, description, quantity) VALUES ('?èn', 200000, 11, '?èn trang trí phòng khách', 4);
    INSERT INTO products (product_name, price, category_id, description, quantity) VALUES ('N?m', 800000, 11, 'N?m lò xo cao c?p', 4);
    INSERT INTO products (product_name, price, category_id, description, quantity) VALUES ('Bàn gh?', 1000000, 11, 'B? bàn gh? ?n hi?n ??i', 4);

--DELETE FROM product_images;

INSERT INTO product_images (product_id, image_url)
WITH img AS (
    SELECT level AS img_num
    FROM dual
    CONNECT BY LEVEL <= 3
)
SELECT p.id,
       p.product_name || '_' || img.img_num || '.jpg' AS image_url
FROM products p
CROSS JOIN img
ORDER BY p.id, img.img_num;

Select * from products;
Select * from users;
