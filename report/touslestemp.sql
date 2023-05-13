-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Máy chủ: 127.0.0.1
-- Thời gian đã tạo: Th4 26, 2023 lúc 12:00 PM
-- Phiên bản máy phục vụ: 10.4.24-MariaDB
-- Phiên bản PHP: 7.4.29

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Cơ sở dữ liệu: `touslestemp`
--

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `address`
--

CREATE TABLE `address` (
  `address_id` bigint(20) NOT NULL,
  `name` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Đang đổ dữ liệu cho bảng `address`
--

INSERT INTO `address` (`address_id`, `name`) VALUES
(1, 'Vĩnh Thọ'),
(2, 'Vĩnh Phước'),
(3, 'Vĩnh Hải'),
(4, '333333'),
(6, '5345'),
(7, '34332'),
(8, 'Vĩnh lương');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `category`
--

CREATE TABLE `category` (
  `category_id` bigint(20) NOT NULL,
  `is_activated` bit(1) NOT NULL,
  `name` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Đang đổ dữ liệu cho bảng `category`
--

INSERT INTO `category` (`category_id`, `is_activated`, `name`) VALUES
(1, b'1', 'Trà đào'),
(2, b'0', 'Cà phê'),
(3, b'1', 'Đá xay'),
(4, b'0', 'Trà'),
(5, b'0', 'Sữa gạo'),
(6, b'0', 'loại 1'),
(7, b'0', 'loại 2'),
(8, b'0', 'Cà phê đen'),
(9, b'1', '456564'),
(10, b'0', 'Quản lí'),
(11, b'0', '2'),
(12, b'0', 'eqwewqew'),
(13, b'0', 'ytyyyyy');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `product`
--

CREATE TABLE `product` (
  `product_id` bigint(20) NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  `inventory` int(11) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  `price` double NOT NULL,
  `category` bigint(20) DEFAULT NULL,
  `size` bigint(20) DEFAULT NULL,
  `store` bigint(20) DEFAULT NULL,
  `image` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Đang đổ dữ liệu cho bảng `product`
--

INSERT INTO `product` (`product_id`, `description`, `inventory`, `name`, `price`, `category`, `size`, `store`, `image`) VALUES
(39, 'Bạc xỉu là một loại đồ uống được làm từ cà phê có pha sữa nhưng phần sữa sẽ nhiều hơn so với phần cà phê.', 10, 'Bạc sỉu', 32000, 3, 2, 5, NULL),
(40, 'Sữa gạo vị Caramel, tốt cho sức khỏe, không có trà nên dễ dùng cho nhiều lứa tuổi. Sữa gạo sẽ ngon hơn khi dùng kèm trân châu trắng. 7/4', 5, 'Sữa gạo Caramel', 27000, 5, 2, 4, NULL),
(87, '432', 42, 'Cà phê đen', 444324, 1, 1, 12, 'https://firebasestorage.googleapis.com/v0/b/touslestemp-37361.appspot.com/o/uploads%2Fproducts%2Fuser-avatar3.png?alt=media&token=0667c46f-191f-43ae-baaf-77a651d46aec'),
(94, '32132', 123, '31', 13232321, 1, 1, 4, '');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `role`
--

CREATE TABLE `role` (
  `role_id` bigint(20) NOT NULL,
  `name` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Đang đổ dữ liệu cho bảng `role`
--

INSERT INTO `role` (`role_id`, `name`) VALUES
(1, 'ROLE_ADMIN'),
(2, 'ROLE_STOREMANAGER');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `salary`
--

CREATE TABLE `salary` (
  `salary_id` bigint(20) NOT NULL,
  `basic_salary` int(11) NOT NULL,
  `name` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Đang đổ dữ liệu cho bảng `salary`
--

INSERT INTO `salary` (`salary_id`, `basic_salary`, `name`) VALUES
(1, 18, 'Phục vụ'),
(2, 20, 'Pha chế'),
(3, 18, 'Bảo vệ'),
(13, 434, 'Cà phê đen');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `size`
--

CREATE TABLE `size` (
  `size_id` bigint(20) NOT NULL,
  `size` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Đang đổ dữ liệu cho bảng `size`
--

INSERT INTO `size` (`size_id`, `size`) VALUES
(1, 'S'),
(2, 'M'),
(3, 'L');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `staff`
--

CREATE TABLE `staff` (
  `id` bigint(20) NOT NULL,
  `email` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `phone` varchar(255) DEFAULT NULL,
  `staff_group` bigint(20) DEFAULT NULL,
  `store` bigint(20) DEFAULT NULL,
  `image` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Đang đổ dữ liệu cho bảng `staff`
--

INSERT INTO `staff` (`id`, `email`, `name`, `phone`, `staff_group`, `store`, `image`) VALUES
(17, 'tamnlt2@fsoft.com.vn', 'demo firebase', '3', 3, 1, 'https://firebasestorage.googleapis.com/v0/b/touslestemp-37361.appspot.com/o/uploads%2Fstaff%2Fnv5.png?alt=media&token=31954934-1d6f-432d-b365-09e40807c1bc'),
(19, '4344@gmail.com', 'sfdffffffff', '4', 2, 8, 'https://firebasestorage.googleapis.com/v0/b/touslestemp-37361.appspot.com/o/uploads%2Fstaff%2Fangular-spring-boot-download-csv-pdf-excel-architecture.png?alt=media&token=e0857f30-6486-4989-a95a-7adf421c36af'),
(20, 'tam.nlt.61cntt@ntu.edu.vn', 'tesstsendmail', '12332132', 1, 8, 'https://firebasestorage.googleapis.com/v0/b/touslestemp-37361.appspot.com/o/uploads%2Fstaff%2Fnv5.png?alt=media&token=a1066880-bd03-4f8b-899d-0ca52ccab3db'),
(22, 'tam.nlt.61cntt@ntu.edu.vn', '2q432143', '4324324', 1, 5, 'https://firebasestorage.googleapis.com/v0/b/touslestemp-37361.appspot.com/o/uploads%2Fstaff%2Fmau-anh-the-526x600.png?alt=media&token=4ec1e19b-02d5-4ca5-bcaf-44a3b927c606'),
(23, 'tamnlt2@fsoft.com.vn', '545435', '432', 1, 2, 'https://firebasestorage.googleapis.com/v0/b/touslestemp-37361.appspot.com/o/uploads%2Fstaff%2Fhero-img-2-1.png?alt=media&token=9aab9aae-bfb0-4f12-9d1f-1f9060ab30b3'),
(25, '3243@gmail.com', '321', '31', 3, 12, 'https://firebasestorage.googleapis.com/v0/b/touslestemp-37361.appspot.com/o/uploads%2Fstaff%2Fuser-avatar.png?alt=media&token=7cf20df3-e07e-4811-a075-9f2e30cf1963'),
(26, '11@gmail.com', 'MoYu', '1', 2, 12, 'https://firebasestorage.googleapis.com/v0/b/touslestemp-37361.appspot.com/o/uploads%2Fstaff%2Fuser-avatar.png?alt=media&token=286a73cd-b8d4-4d09-8ae1-f2debcf597be'),
(27, '432', 'Cà phê đen', '3', 3, 12, 'https://firebasestorage.googleapis.com/v0/b/touslestemp-37361.appspot.com/o/uploads%2Fstaff%2Fuser-avatar.png?alt=media&token=543747c2-934c-4d83-ad97-927f142f4178'),
(28, '4', 'Cà phê đen4343432', '4', 2, 8, 'https://firebasestorage.googleapis.com/v0/b/touslestemp-37361.appspot.com/o/uploads%2Fstaff%2Fuser-avatar.png?alt=media&token=9900045e-fc12-4d5b-9552-c97a95c9f108'),
(29, '2', '432432', '2', 1, 19, 'https://firebasestorage.googleapis.com/v0/b/touslestemp-37361.appspot.com/o/uploads%2Fstaff%2Fregister2.png?alt=media&token=ab003e88-788a-4db9-a1d3-45763bbaa43c');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `staff_group`
--

CREATE TABLE `staff_group` (
  `staff_group_id` bigint(20) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  `is_activated` bit(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Đang đổ dữ liệu cho bảng `staff_group`
--

INSERT INTO `staff_group` (`staff_group_id`, `name`, `is_activated`) VALUES
(1, 'Quản lí', b'0'),
(2, 'Thu ngân', b'1'),
(3, 'Phục vụ', b'1'),
(4, 'Bảo vệ', b'1'),
(5, 'Pha chế', b'1'),
(6, 'Prewreha chế', b'0');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `store`
--

CREATE TABLE `store` (
  `store_id` bigint(20) NOT NULL,
  `email` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `phone` varchar(255) DEFAULT NULL,
  `address` bigint(20) DEFAULT NULL,
  `address_detail` varchar(255) DEFAULT NULL,
  `image` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Đang đổ dữ liệu cho bảng `store`
--

INSERT INTO `store` (`store_id`, `email`, `name`, `phone`, `address`, `address_detail`, `image`) VALUES
(1, 'touslestemyersin@gmail.com', 'Tous Les Temp Yersin', '384567353', 2, '02 Đường 2/4 Vĩnh Hải', 'https://firebasestorage.googleapis.com/v0/b/touslestemp-37361.appspot.com/o/uploads%2Ftouslestemp.jpg?alt=media&token=3305c87f-6911-4ce2-bb61-b5b7ee2e9b3f'),
(2, 'touslestemp@gmail.com', 'Tous Les Temp Vĩnh Lương', '32484873', 8, '12 Phan Trọng Tuệ Vĩnh Lương Nha Trang', 'https://firebasestorage.googleapis.com/v0/b/touslestemp-37361.appspot.com/o/uploads%2Fhero-img-2-1.png?alt=media&token=8a702fa8-d445-44fe-8778-034b4eb511c4'),
(3, 'touslestemlethanhton@gmail.com', 'Tous Les Temp Lê Thánh Tôn', '98756723', 3, NULL, 'https://firebasestorage.googleapis.com/v0/b/touslestemp-37361.appspot.com/o/uploads%2Fhero-img-2-1.png?alt=media&token=55611af6-e457-4627-8e71-f166d47da70e'),
(4, 'touslestemngdinhchieu@gmail.com', 'Tous Les Temp Nguyễn Đình Chiểu', '945088654', 1, NULL, NULL),
(5, 'touslestemvotru@gmail.com', 'Tous Les Temp Võ Trứ', '974221113', 3, NULL, 'https://firebasestorage.googleapis.com/v0/b/touslestemp-37361.appspot.com/o/uploads%2Fnv4.png?alt=media&token=99d37c8a-7b50-4eff-8fda-7f167e064f36'),
(8, 'tamnlt2@fsoft.com.vn', '432432', '432432', 2, '432432', NULL),
(12, 'rewre@gmail.com', 'demo firebase', '32421432443242', 4, '2', 'https://firebasestorage.googleapis.com/v0/b/touslestemp-37361.appspot.com/o/hero-img-2-1.png?alt=media&token=1d26fd0e-7589-425f-a060-f95d08d724a5'),
(19, 'moyu@gmail.com333333', 'Cà phê đen9', '35543543', 1, 'moyu@gmail.com32443', 'https://firebasestorage.googleapis.com/v0/b/touslestemp-37361.appspot.com/o/uploads%2Fnv8.png?alt=media&token=81e7b522-1548-4574-851f-15db7242c3ea'),
(20, 'rewre@gmail.com', 'erew', '34242', 2, 'rewre@gmail.com', 'https://firebasestorage.googleapis.com/v0/b/touslestemp-37361.appspot.com/o/uploads%2Fstore%2Fproduct_18_soda_detox_hat_chia.png?alt=media&token=b78b64f1-49a5-4f37-b2a4-cc05e4629d3d'),
(21, 'tamnlt2@fsoft.com.vn', 'sau khi thực hiện OTP', '321', 2, '321321', 'https://firebasestorage.googleapis.com/v0/b/touslestemp-37361.appspot.com/o/uploads%2Fstore%2Fdfd%20tham%20kh%E1%BA%A3o.png?alt=media&token=821fd3fb-20ef-4944-a85a-478430a0d4e6');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `time_keeping`
--

CREATE TABLE `time_keeping` (
  `timekeeping_id` bigint(20) NOT NULL,
  `month` int(11) NOT NULL,
  `num_of_shift` int(11) NOT NULL,
  `staff` bigint(20) DEFAULT NULL,
  `salary` bigint(20) DEFAULT NULL,
  `created_date` datetime(6) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Đang đổ dữ liệu cho bảng `time_keeping`
--

INSERT INTO `time_keeping` (`timekeeping_id`, `month`, `num_of_shift`, `staff`, `salary`, `created_date`) VALUES
(9, 2, 22, 19, 2, NULL),
(12, 1, 30, 17, 2, '2023-04-26 12:09:59.000000'),
(13, 1, 30, 17, 2, '2023-04-26 12:21:44.000000'),
(14, 12, 10, 17, 2, '2023-04-26 12:28:00.000000'),
(15, 999, 10, 17, 2, '2023-04-26 12:36:25.000000'),
(16, 1, 3, 17, 1, '2023-04-26 12:45:57.000000'),
(17, 11, 1, 26, 3, '2023-04-26 13:48:24.000000'),
(19, 2, 2, 23, 1, '2023-04-26 14:20:08.000000'),
(23, 1, 30, 25, 1, '2023-04-26 07:00:00.000000'),
(25, 1, 30, 23, 2, '2023-04-26 14:25:33.000000'),
(26, 1, 30, 17, 2, '2023-04-26 16:06:40.000000'),
(28, 1, 30, 20, 2, '2023-04-26 07:00:00.000000'),
(33, 2, 132, 22, 2, '2023-04-26 16:10:43.000000');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `user`
--

CREATE TABLE `user` (
  `user_id` bigint(20) NOT NULL,
  `created_date` datetime(6) NOT NULL,
  `display_name` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `enabled` bit(1) DEFAULT NULL,
  `modified_date` datetime(6) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `provider` varchar(255) DEFAULT NULL,
  `provider_user_id` varchar(255) DEFAULT NULL,
  `secret` varchar(255) DEFAULT NULL,
  `using_2fa` bit(1) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Đang đổ dữ liệu cho bảng `user`
--

INSERT INTO `user` (`user_id`, `created_date`, `display_name`, `email`, `enabled`, `modified_date`, `password`, `provider`, `provider_user_id`, `secret`, `using_2fa`) VALUES
(51, '2023-04-22 17:56:41.000000', 'AdminTouslesTemp', 'admin@gmail.com', b'1', '2023-04-22 17:56:41.000000', '$2a$10$BM0YQjq2COrzZlwvYefNNO6hAHWeC.SNlilUmHPsaeYTfXJw21kIu', 'local', NULL, NULL, b'0'),
(52, '2023-04-22 17:59:09.000000', 'TamNLT2', 'imstudent2210@gmail.com', b'1', '2023-04-22 17:59:09.000000', '$2a$10$R4XULPlFrXtE2yqDad.JveU54Qkkghc0HSlJi9w9zVvVe7rnA/WNa', 'local', NULL, 'M7TMSBJOR2X2ACJPGY4PXP4WN6PWBNZW', b'1'),
(62, '2023-04-24 11:35:22.000000', 'testmail', 'tam.nlt.61cntt@ntu.edu.vn', b'1', '2023-04-24 11:35:22.000000', '$2a$10$EUtsK/ejQUibVLRmhCpF3uC2A4CadJqOe3kGpreoQNT8W4sOqdMlK', 'local', NULL, '5FAL4IJJXEIUFN6HKBBFIYXK5YEOI3VU', b'1'),
(63, '2023-04-24 21:56:50.000000', '4324', 'imstudent12210@gmail.com', b'1', '2023-04-24 21:56:50.000000', '$2a$10$0fYRqMHFvUdWYvvC5y.fmO3dAyI7WMoQ6bflY3J/KL77.yaQRB7nO', 'local', NULL, 'QEH5DY3QDP4NZ43OTY26SQBHGQDK2Z4D', b'1'),
(64, '2023-04-24 23:50:17.000000', 'Diệp Từ Trung', 'trungdiep77@gmail.com', b'1', '2023-04-24 23:50:17.000000', '$2a$10$NQt5rmlyTjROuh5KqKpMeOyj1V9zPaqTag614cq7PM/EUOgBpzciy', 'local', NULL, 'YGYH4CXAENA577TP7SXIN3QHGQITK43P', b'1'),
(65, '2023-04-26 10:57:39.000000', 'tam2912', 'tam29122021@gmail.com', b'1', '2023-04-26 10:57:39.000000', '$2a$10$lTBgz7iJuMfmZOpy0GXyI.w8IndMzFeGQFPKfNCnyrak9JIMVMMrG', 'local', NULL, 'XHFLJFZRCONNTH2UJ364CPU7Y3JB7VIC', b'1');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `user_role`
--

CREATE TABLE `user_role` (
  `user_id` bigint(20) NOT NULL,
  `role_id` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Đang đổ dữ liệu cho bảng `user_role`
--

INSERT INTO `user_role` (`user_id`, `role_id`) VALUES
(51, 1),
(51, 2),
(52, 1),
(62, 1),
(63, 1),
(64, 1),
(65, 1);

--
-- Chỉ mục cho các bảng đã đổ
--

--
-- Chỉ mục cho bảng `address`
--
ALTER TABLE `address`
  ADD PRIMARY KEY (`address_id`);

--
-- Chỉ mục cho bảng `category`
--
ALTER TABLE `category`
  ADD PRIMARY KEY (`category_id`);

--
-- Chỉ mục cho bảng `product`
--
ALTER TABLE `product`
  ADD PRIMARY KEY (`product_id`),
  ADD KEY `FKqx9wikktsev17ctu0kcpkrafc` (`category`),
  ADD KEY `FKs1kqbnmfuc3o0efvokjxcwe69` (`size`),
  ADD KEY `FKt1oh09ilmvlkynrmcvurtrrd5` (`store`);

--
-- Chỉ mục cho bảng `role`
--
ALTER TABLE `role`
  ADD PRIMARY KEY (`role_id`);

--
-- Chỉ mục cho bảng `salary`
--
ALTER TABLE `salary`
  ADD PRIMARY KEY (`salary_id`);

--
-- Chỉ mục cho bảng `size`
--
ALTER TABLE `size`
  ADD PRIMARY KEY (`size_id`);

--
-- Chỉ mục cho bảng `staff`
--
ALTER TABLE `staff`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKrdql2x1ja4t1y1g11wbte6rwk` (`staff_group`),
  ADD KEY `FKe29um6t60d981pfaiga3ul3tr` (`store`);

--
-- Chỉ mục cho bảng `staff_group`
--
ALTER TABLE `staff_group`
  ADD PRIMARY KEY (`staff_group_id`);

--
-- Chỉ mục cho bảng `store`
--
ALTER TABLE `store`
  ADD PRIMARY KEY (`store_id`),
  ADD KEY `FKqms7gw8gcgot7br0xiqhp7x21` (`address`);

--
-- Chỉ mục cho bảng `time_keeping`
--
ALTER TABLE `time_keeping`
  ADD PRIMARY KEY (`timekeeping_id`),
  ADD KEY `FKhjv3cgweole5au8ao7hg6fe1k` (`staff`),
  ADD KEY `FKmpxu8nxrqkov8a405lmi0cywk` (`salary`);

--
-- Chỉ mục cho bảng `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`user_id`);

--
-- Chỉ mục cho bảng `user_role`
--
ALTER TABLE `user_role`
  ADD PRIMARY KEY (`user_id`,`role_id`),
  ADD KEY `FKa68196081fvovjhkek5m97n3y` (`role_id`);

--
-- AUTO_INCREMENT cho các bảng đã đổ
--

--
-- AUTO_INCREMENT cho bảng `address`
--
ALTER TABLE `address`
  MODIFY `address_id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- AUTO_INCREMENT cho bảng `category`
--
ALTER TABLE `category`
  MODIFY `category_id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=14;

--
-- AUTO_INCREMENT cho bảng `product`
--
ALTER TABLE `product`
  MODIFY `product_id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=95;

--
-- AUTO_INCREMENT cho bảng `role`
--
ALTER TABLE `role`
  MODIFY `role_id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT cho bảng `salary`
--
ALTER TABLE `salary`
  MODIFY `salary_id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=14;

--
-- AUTO_INCREMENT cho bảng `size`
--
ALTER TABLE `size`
  MODIFY `size_id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT cho bảng `staff`
--
ALTER TABLE `staff`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=30;

--
-- AUTO_INCREMENT cho bảng `staff_group`
--
ALTER TABLE `staff_group`
  MODIFY `staff_group_id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT cho bảng `store`
--
ALTER TABLE `store`
  MODIFY `store_id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=26;

--
-- AUTO_INCREMENT cho bảng `time_keeping`
--
ALTER TABLE `time_keeping`
  MODIFY `timekeeping_id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=34;

--
-- AUTO_INCREMENT cho bảng `user`
--
ALTER TABLE `user`
  MODIFY `user_id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=66;

--
-- Các ràng buộc cho các bảng đã đổ
--

--
-- Các ràng buộc cho bảng `product`
--
ALTER TABLE `product`
  ADD CONSTRAINT `FKqx9wikktsev17ctu0kcpkrafc` FOREIGN KEY (`category`) REFERENCES `category` (`category_id`),
  ADD CONSTRAINT `FKs1kqbnmfuc3o0efvokjxcwe69` FOREIGN KEY (`size`) REFERENCES `size` (`size_id`),
  ADD CONSTRAINT `FKt1oh09ilmvlkynrmcvurtrrd5` FOREIGN KEY (`store`) REFERENCES `store` (`store_id`);

--
-- Các ràng buộc cho bảng `staff`
--
ALTER TABLE `staff`
  ADD CONSTRAINT `FKe29um6t60d981pfaiga3ul3tr` FOREIGN KEY (`store`) REFERENCES `store` (`store_id`) ON DELETE CASCADE,
  ADD CONSTRAINT `FKrdql2x1ja4t1y1g11wbte6rwk` FOREIGN KEY (`staff_group`) REFERENCES `staff_group` (`staff_group_id`) ON DELETE CASCADE;

--
-- Các ràng buộc cho bảng `store`
--
ALTER TABLE `store`
  ADD CONSTRAINT `FKqms7gw8gcgot7br0xiqhp7x21` FOREIGN KEY (`address`) REFERENCES `address` (`address_id`);

--
-- Các ràng buộc cho bảng `time_keeping`
--
ALTER TABLE `time_keeping`
  ADD CONSTRAINT `FKhjv3cgweole5au8ao7hg6fe1k` FOREIGN KEY (`staff`) REFERENCES `staff` (`id`),
  ADD CONSTRAINT `FKmpxu8nxrqkov8a405lmi0cywk` FOREIGN KEY (`salary`) REFERENCES `salary` (`salary_id`);

--
-- Các ràng buộc cho bảng `user_role`
--
ALTER TABLE `user_role`
  ADD CONSTRAINT `FK859n2jvi8ivhui0rl0esws6o` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`),
  ADD CONSTRAINT `FKa68196081fvovjhkek5m97n3y` FOREIGN KEY (`role_id`) REFERENCES `role` (`role_id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
