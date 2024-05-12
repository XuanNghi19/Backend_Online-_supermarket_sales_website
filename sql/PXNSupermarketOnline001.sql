--categories
INSERT INTO categories (category_name, image_url) VALUES ('Rau c?, tr�i c�y', 'Rau_cu_trai_cay.jpeg');
INSERT INTO categories (category_name, image_url) VALUES ('Th?t, tr?ng, h?i s?n', 'Thit_trung_hai_san.jpeg');
INSERT INTO categories (category_name, image_url) VALUES ('Th?c ?n ch? bi?n, b�n t??i', 'Thuc_an_che_bien_bun_tuoi.jpeg');
INSERT INTO categories (category_name, image_url) VALUES ('Th?c ph?m ?�ng m�t', 'Thuc_pham_dong_mat.jpeg');
INSERT INTO categories (category_name, image_url) VALUES ('B�nh, k?o, snack', 'Banh_keo_snack.jpeg');
INSERT INTO categories (category_name, image_url) VALUES ('S?a, s?n ph?m t? s?a', 'Sua_san_pham_tu_sua.jpeg');
INSERT INTO categories (category_name, image_url) VALUES ('Th?c u?ng', 'Thuc_uong.jpeg');
INSERT INTO categories (category_name, image_url) VALUES ('Gia v?, g?o, th?c ph?m kh�', 'Gia_vi_gao_thuc_pham_kho.jpeg');
INSERT INTO categories (category_name, image_url) VALUES ('S?n ph?m cho b�', 'San_pham_cho_be.jpeg');
INSERT INTO categories (category_name, image_url) VALUES ('Ch?m s�c c� nh�n', 'Cham_soc_ca_nhan.jpeg');
INSERT INTO categories (category_name, image_url) VALUES ('Nh� c?a v� ??i s?ng', 'Nha_cua_va_doi_song.jpeg');

--products
    -- Category: Rau c?, tr�i c�y
    INSERT INTO products (product_name, price, category_id, description, quantity) VALUES ('C� chua', 15000, 1, 'C� chua s?ch', 4);
    INSERT INTO products (product_name, price, category_id, description, quantity) VALUES ('D?a h?u', 30000, 1, 'D?a h?u t??i ngon', 4);
    INSERT INTO products (product_name, price, category_id, description, quantity) VALUES ('B� ??', 20000, 1, 'B� ?? gi�u dinh d??ng', 4);
    INSERT INTO products (product_name, price, category_id, description, quantity) VALUES ('D?a', 25000, 1, 'D?a t??i ng?t', 4);
    INSERT INTO products (product_name, price, category_id, description, quantity) VALUES ('Chu?i', 12000, 1, 'Chu?i ch�n m?ng', 4);
    
    -- Category: Th?t, tr?ng, h?i s?n
    INSERT INTO products (product_name, price, category_id, description, quantity) VALUES ('Th?t b�', 150000, 2, 'Th?t b� nh?p kh?u', 4);
    INSERT INTO products (product_name, price, category_id, description, quantity) VALUES ('C� h?i', 180000, 2, 'C� h?i t??i s?ch', 4);
    INSERT INTO products (product_name, price, category_id, description, quantity) VALUES ('Tr?ng g�', 25000, 2, 'Tr?ng g� ??m b?o ch?t l??ng', 4);
    INSERT INTO products (product_name, price, category_id, description, quantity) VALUES ('T�m', 80000, 2, 'T�m t??i ngon', 4);
    INSERT INTO products (product_name, price, category_id, description, quantity) VALUES ('G� ta', 120000, 2, 'G� ta s?ch', 4);
    
    -- Category: Th?c ?n ch? bi?n, b�n t??i
    INSERT INTO products (product_name, price, category_id, description, quantity) VALUES ('B�n ri�u', 25000, 3, 'B�n ri�u ngon', 4);
    INSERT INTO products (product_name, price, category_id, description, quantity) VALUES ('B�nh m�', 10000, 3, 'B�nh m� m?m gi�n', 4);
    INSERT INTO products (product_name, price, category_id, description, quantity) VALUES ('C?m g�', 35000, 3, 'C?m g� h?p d?n', 4);
    INSERT INTO products (product_name, price, category_id, description, quantity) VALUES ('B�nh cu?n', 30000, 3, 'B�nh cu?n th?m ngon', 4);
    INSERT INTO products (product_name, price, category_id, description, quantity) VALUES ('Ph? b�', 40000, 3, 'Ph? b� n�ng h?i', 4);
    
    -- Category: Th?c ph?m ?�ng m�t
    INSERT INTO products (product_name, price, category_id, description, quantity) VALUES ('Kem', 25000, 4, 'Kem s?a ngon', 4);
    INSERT INTO products (product_name, price, category_id, description, quantity) VALUES ('Sinh t?', 35000, 4, 'Sinh t? tr�i c�y t??i', 4);
    INSERT INTO products (product_name, price, category_id, description, quantity) VALUES ('S?a ??u n�nh', 20000, 4, 'S?a ??u n�nh nguy�n ch?t', 4);
    INSERT INTO products (product_name, price, category_id, description, quantity) VALUES ('Kem chu?i', 30000, 4, 'Kem chu?i th?m ngon', 4);
    INSERT INTO products (product_name, price, category_id, description, quantity) VALUES ('N??c �p tr�i c�y', 25000, 4, 'N??c �p tr�i c�y t??i', 4);
    
    -- Category: B�nh, k?o, snack
    INSERT INTO products (product_name, price, category_id, description, quantity) VALUES ('B�nh quy', 15000, 5, 'B�nh quy socola', 4);
    INSERT INTO products (product_name, price, category_id, description, quantity) VALUES ('K?o', 5000, 5, 'K?o ng?t ng�o', 4);
    INSERT INTO products (product_name, price, category_id, description, quantity) VALUES ('Snack khoai t�y', 12000, 5, 'Snack khoai t�y gi�n r?m', 4);
    INSERT INTO products (product_name, price, category_id, description, quantity) VALUES ('B�nh bao', 10000, 5, 'B�nh bao th?m ngon', 4);
    INSERT INTO products (product_name, price, category_id, description, quantity) VALUES ('B�nh gato', 25000, 5, 'B�nh gato b?', 4);
    
    -- Category: S?a, s?n ph?m t? s?a
    INSERT INTO products (product_name, price, category_id, description, quantity) VALUES ('S?a t??i', 20000, 6, 'S?a t??i ngon', 4);
    INSERT INTO products (product_name, price, category_id, description, quantity) VALUES ('B?', 30000, 6, 'B? s?ch', 4);
    INSERT INTO products (product_name, price, category_id, description, quantity) VALUES ('S?a chua', 10000, 6, 'S?a chua t? nhi�n', 4);
    INSERT INTO products (product_name, price, category_id, description, quantity) VALUES ('B�nh flan', 25000, 6, 'B�nh flan ng?t ng�o', 4);
    INSERT INTO products (product_name, price, category_id, description, quantity) VALUES ('Kem socola', 30000, 6, 'Kem socola th?m ngon', 4);
    
    -- Category: Th?c u?ng
    INSERT INTO products (product_name, price, category_id, description, quantity) VALUES ('N??c ng?t', 10000, 7, 'N??c ng?t l?nh', 4);
    INSERT INTO products (product_name, price, category_id, description, quantity) VALUES ('C� ph�', 20000, 7, 'C� ph� ?en th?m', 4);
    INSERT INTO products (product_name, price, category_id, description, quantity) VALUES ('Tr� ?�', 15000, 7, 'Tr� ?� th?m ngon', 4);
    INSERT INTO products (product_name, price, category_id, description, quantity) VALUES ('S?a ??c', 15000, 7, 'S?a ??c ng?t ng�o', 4);
    INSERT INTO products (product_name, price, category_id, description, quantity) VALUES ('Sinh t? b?', 30000, 7, 'Sinh t? b? th?m ngon', 4);
    
    -- Category: Gia v?, g?o, th?c ph?m kh�
    INSERT INTO products (product_name, price, category_id, description, quantity) VALUES ('G?o', 30000, 8, 'G?o ngon', 4);
    INSERT INTO products (product_name, price, category_id, description, quantity) VALUES ('???ng', 20000, 8, '???ng tr?ng', 4);
    INSERT INTO products (product_name, price, category_id, description, quantity) VALUES ('Mu?i', 5000, 8, 'Mu?i bi?n', 4);
    INSERT INTO products (product_name, price, category_id, description, quantity) VALUES ('B?t ng?t', 15000, 8, 'B?t ng?t ?a d?ng', 4);
    INSERT INTO products (product_name, price, category_id, description, quantity) VALUES ('H?t ti�u', 25000, 8, 'H?t ti�u s?ch', 4);
    
    -- Category: S?n ph?m cho b�
    INSERT INTO products (product_name, price, category_id, description, quantity) VALUES ('B?m', 150000, 9, 'B?m cho b� s? sinh', 4);
    INSERT INTO products (product_name, price, category_id, description, quantity) VALUES ('S?a b?t', 200000, 9, 'S?a b?t d�nh cho tr? s? sinh', 4);
    INSERT INTO products (product_name, price, category_id, description, quantity) VALUES ('D?u g?i', 50000, 9, 'D?u g?i cho tr? nh?', 4);
    INSERT INTO products (product_name, price, category_id, description, quantity) VALUES ('X� ph�ng', 20000, 9, 'X� ph�ng cho tr? em', 4);
    INSERT INTO products (product_name, price, category_id, description, quantity) VALUES ('B�nh s?a', 50000, 9, 'B�nh s?a ch?ng ?au b?ng', 4);
    
    -- Category: Ch?m s�c c� nh�n
    INSERT INTO products (product_name, price, category_id, description, quantity) VALUES ('Gel t?m', 30000, 10, 'Gel t?m h??ng hoa', 4);
    INSERT INTO products (product_name, price, category_id, description, quantity) VALUES ('Kem ?�nh r?ng', 20000, 10, 'Kem ?�nh r?ng gi�p s�ng tr?ng r?ng', 4);
    INSERT INTO products (product_name, price, category_id, description, quantity) VALUES ('D?u g?i', 35000, 10, 'D?u g?i ch?m s�c t�c', 4);
    INSERT INTO products (product_name, price, category_id, description, quantity) VALUES ('S?a d??ng th?', 40000, 10, 'S?a d??ng th? l�m m?m da', 4);
    INSERT INTO products (product_name, price, category_id, description, quantity) VALUES ('N??c hoa', 100000, 10, 'N??c hoa th?m l�u', 4);
    
    -- Category: Nh� c?a v� ??i s?ng
    INSERT INTO products (product_name, price, category_id, description, quantity) VALUES ('Ch?n ga g?i ??m', 500000, 11, 'B? ch?n ga g?i ??m cao c?p', 4);
    INSERT INTO products (product_name, price, category_id, description, quantity) VALUES ('B�t ??a', 150000, 11, 'B�t ??a s? cao c?p', 4);
    INSERT INTO products (product_name, price, category_id, description, quantity) VALUES ('?�n', 200000, 11, '?�n trang tr� ph�ng kh�ch', 4);
    INSERT INTO products (product_name, price, category_id, description, quantity) VALUES ('N?m', 800000, 11, 'N?m l� xo cao c?p', 4);
    INSERT INTO products (product_name, price, category_id, description, quantity) VALUES ('B�n gh?', 1000000, 11, 'B? b�n gh? ?n hi?n ??i', 4);

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
