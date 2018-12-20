-- phpMyAdmin SQL Dump
-- version 4.8.2
-- https://www.phpmyadmin.net/
--
-- Servidor: localhost:3306
-- Tiempo de generación: 20-12-2018 a las 11:10:56
-- Versión del servidor: 5.7.23
-- Versión de PHP: 7.1.21

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `wildcart`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `factura`
--

CREATE TABLE `factura` (
  `id` int(11) NOT NULL,
  `fecha` datetime DEFAULT NULL,
  `iva` float DEFAULT NULL,
  `id_usuario` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Volcado de datos para la tabla `factura`
--

INSERT INTO `factura` (`id`, `fecha`, `iva`, `id_usuario`) VALUES
(1, '2018-12-03 00:00:00', 985, 3),
(2, NULL, 21, 1),
(3, '2018-12-04 00:00:00', 21, 1),
(4, NULL, 20, 1),
(5, '2018-12-04 00:00:00', 21, 1),
(6, '2018-12-07 00:00:00', 21, 1),
(10, '2018-12-11 00:00:00', 21, 1),
(11, '2018-12-10 00:00:00', 0, 1),
(12, NULL, 31, 1),
(13, '2018-12-05 00:00:00', 31, 4),
(14, '2018-01-02 00:00:00', 24, 2),
(15, '2018-02-06 00:00:00', 112, 1),
(16, '2018-12-11 00:00:00', 44, 2),
(17, '2018-12-11 00:00:00', 44, 1),
(18, '2018-12-11 00:00:00', 21, 1),
(19, '2018-12-14 00:00:00', 56, 5),
(21, '2018-12-14 00:00:00', 14233300000, 2),
(22, '2018-12-19 00:00:00', 21, 1),
(23, '2018-12-19 00:00:00', 21, 1);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `linea`
--

CREATE TABLE `linea` (
  `id` int(11) NOT NULL,
  `cantidad` int(11) DEFAULT NULL,
  `id_producto` int(11) DEFAULT NULL,
  `id_factura` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Volcado de datos para la tabla `linea`
--

INSERT INTO `linea` (`id`, `cantidad`, `id_producto`, `id_factura`) VALUES
(1, 5, 28, 1),
(3, 1, 3, 3),
(4, 1, 3, 4),
(5, 1, 3, 5),
(6, 1, 1, 5),
(7, 1, 5, 5),
(8, 1, 9, 5),
(9, 1, 10, 5),
(10, 1, 2, 6),
(11, 1, 2, 10),
(12, 1, 3, 10),
(13, 1, 1, 10),
(14, 1, 6, 18),
(15, 8, 9, 18),
(16, 123, 3, 1),
(17, 66, 2, 1),
(18, 999999999, 56, 1),
(19, 554, 21, 1),
(20, 123, 4, 1),
(21, 324234, 1, 1),
(22, 123456789, 4, 1),
(23, 999999999, 2, 1),
(24, 999999999, 2, 1),
(25, 1, 1, 22),
(26, 1, 2, 22),
(27, 1, 3, 22),
(28, 1, 6, 22),
(29, 1, 5, 22),
(30, 1, 4, 22),
(31, 1, 7, 22),
(32, 1, 8, 22),
(33, 1, 9, 22),
(34, 1, 10, 22),
(35, 1, 11, 22),
(36, 1, 12, 22),
(37, 1, 13, 22),
(38, 1, 14, 22),
(39, 1, 15, 22),
(40, 1, 16, 22),
(41, 1, 17, 22),
(42, 1, 18, 22),
(43, 1, 19, 22),
(44, 1, 20, 22),
(45, 1, 1, 23),
(46, 1, 2, 23),
(47, 1, 3, 23);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `producto`
--

CREATE TABLE `producto` (
  `id` int(11) NOT NULL,
  `codigo` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `desc` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `existencias` int(11) DEFAULT NULL,
  `precio` float DEFAULT NULL,
  `foto` varchar(512) COLLATE utf8_unicode_ci DEFAULT NULL,
  `id_tipoProducto` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Volcado de datos para la tabla `producto`
--

INSERT INTO `producto` (`id`, `codigo`, `desc`, `existencias`, `precio`, `foto`, `id_tipoProducto`) VALUES
(1, 'HEC3555', 'sistema trabajo', 8, 100.23, 'minions.jpg', 4),
(2, 'holi', 'tubo tubos', 11, 79.97, 'minions.jpg', 2),
(3, 'DP7J843K9F', 'tubo trabajo', 56, 26.6, 'minions.jpg', 2),
(4, 'POLHU234', 'soporte capilar', 61, 28.53, '', 1),
(5, 'POLHU234', 'tubo tubos', 60, 69.7, '', 5),
(6, 'OJ563S', 'tubo tubos', 19, 8.65, '', 2),
(7, 'OJ563S', ' accesorio tubos', 20, 40.91, '', 2),
(8, 'OJ563S', 'soporte tren', 20, 59.65, '', 5),
(9, '99OPED', ' accesorio trabajo', 5, 3.06, '', 1),
(10, 'POLHU234', ' accesorio coche', 83, 42.78, '', 3),
(11, 'HEC3555', 'estaci�n capilar', 61, 2.44, '', 4),
(12, 'HEC3555', 'soporte tren', 20, 93.73, '', 5),
(13, 'OJ563S', 'estaci�n tubos', 11, 56.8, '', 2),
(14, 'OJ563S', 'tubo tubos', 14, 95.39, '', 3),
(15, 'OJ563S', 'soporte tren', 84, 1.84, '', 1),
(16, 'OJ563S', 'sistema coche', 84, 85.48, '', 3),
(17, 'HEC3555', ' accesorio tubos', 20, 6.95, '', 4),
(18, 'DP7J843K9F', 'tubo capilar', 14, 1.44, '', 4),
(19, 'OJ563S', ' accesorio capilar', 61, 62.65, '', 5),
(20, 'POLHU234', ' accesorio capilar', 11, 86.95, '', 2),
(21, 'POLHU234', 'estaci�n tubos', 15, 19.48, '', 5),
(22, 'DP7J843K9F', 'estaci�n tubos', 12, 40.7, '', 5),
(23, 'POLHU234', 'soporte tubos', 12, 18.72, '', 3),
(24, 'POLHU234', ' accesorio tubos', 85, 78.79, '', 4),
(25, 'DP7J843K9F', 'estaci�n capilar', 12, 100.69, '', 3),
(26, '99OPED', 'sistema tren', 85, 7.38, '', 5),
(27, 'OJ563S', 'sistema tren', 12, 6.66, '', 4),
(28, 'DP7J843K9F', 'tubo coche', 15, 61.33, '', 2),
(29, 'DP7J843K9F', 'tubo capilar', 21, 3.82, '', 4),
(30, 'OJ563S', 'soporte trabajo', 21, 58.92, '', 3),
(31, 'DP7J843K9F', 'sistema capilar', 85, 61.95, '', 5),
(32, 'DP7J843K9F', ' accesorio tren', 85, 50.41, '', 4),
(33, '99OPED', 'estaci�n tren', 62, 93.05, '', 3),
(34, '99OPED', 'sistema trabajo', 21, 1.33, '', 2),
(35, 'HEC3555', ' accesorio tren', 12, 32.33, '', 1),
(36, 'OJ563S', 'sistema tubos', 62, 47.89, '', 1),
(37, 'POLHU234', 'soporte trabajo', 15, 91.05, '', 5),
(38, 'HEC3555', 'estaci�n capilar', 62, 93.59, '', 3),
(39, 'POLHU234', 'tubo trabajo', 15, 47.27, '', 5),
(40, 'POLHU234', 'soporte coche', 62, 81.7, '', 1),
(41, 'POLHU234', ' accesorio tren', 62, 61.27, '', 5),
(42, 'POLHU234', 'tubo tubos', 85, 78.36, '', 1),
(43, 'OJ563S', 'soporte coche', 85, 7.12, '', 3),
(44, 'HEC3555', ' accesorio trabajo', 12, 35.47, '', 3),
(45, '99OPED', 'estaci�n tubos', 62, 1.04, '', 2),
(46, '99OPED', 'sistema tren', 12, 71.67, '', 3),
(47, 'OJ563S', 'estaci�n capilar', 21, 80.58, '', 5),
(48, 'DP7J843K9F', 'estaci�n capilar', 12, 12.5, '', 5),
(49, 'POLHU234', 'tubo tren', 15, 46.88, '', 2),
(50, 'POLHU234', 'estaci�n coche', 12, 68.79, '', 1),
(51, 'HEC3555', 'tubo trabajo', 85, 24.21, '', 3),
(52, 'OJ563S', 'tubo capilar', 62, 4.16, '', 5),
(53, 'OJ563S', 'soporte trabajo', 15, 17.9, '', 5),
(54, '99OPED', 'soporte capilar', 62, 48.79, '', 5),
(55, 'HEC3555', 'sistema trabajo', 15, 62.66, '', 1),
(56, 'OJ563S', 'soporte trabajo', 62, 57.53, '', 4),
(57, 'POLHU234', ' accesorio capilar', 15, 48.74, '', 5),
(58, 'HEC3555', 'sistema tubos', 15, 85.26, '', 2),
(59, 'POLHU234', 'soporte tubos', 21, 93.26, '', 1),
(60, 'POLHU234', 'estaci�n tren', 62, 62.22, '', 4),
(61, 'OJ563S', 'sistema tubos', 15, 87.98, '', 2),
(62, 'POLHU234', 'sistema capilar', 12, 95.5, '', 4),
(63, 'OJ563S', 'tubo capilar', 62, 3.61, '', 4),
(64, 'DP7J843K9F', ' accesorio tren', 12, 1.37, '', 3),
(65, 'DP7J843K9F', 'soporte tubos', 85, 63.37, '', 1),
(66, 'OJ563S', 'sistema trabajo', 85, 85.71, '', 1),
(67, 'DP7J843K9F', 'estaci�n tren', 15, 15.99, '', 5),
(68, 'OJ563S', 'sistema capilar', 62, 36.58, '', 3),
(69, 'POLHU234', 'estaci�n tubos', 62, 39.99, '', 5),
(70, 'POLHU234', 'tubo coche', 85, 74.54, '', 2),
(71, 'HEC3555', ' accesorio tren', 85, 97.02, '', 2),
(72, 'HEC3555', 'tubo tren', 12, 21.93, '', 3),
(73, 'POLHU234', 'soporte coche', 15, 22.49, '', 1),
(74, 'HEC3555', ' accesorio tren', 85, 2.43, '', 3),
(75, 'HEC3555', 'estaci�n tren', 15, 96.53, '', 1),
(76, 'DP7J843K9F', 'soporte tubos', 21, 66.78, '', 3),
(77, 'DP7J843K9F', 'tubo tubos', 15, 77.55, '', 4),
(78, 'DP7J843K9F', 'tubo tubos', 15, 59.18, '', 3),
(79, 'OJ563S', 'sistema tubos', 15, 3.99, '', 5),
(80, 'DP7J843K9F', 'tubo coche', 62, 92.44, '', 5),
(81, 'DP7J843K9F', ' accesorio tubos', 12, 77.91, '', 3),
(82, 'HEC3555', ' accesorio coche', 85, 87.14, '', 4),
(83, '99OPED', 'soporte coche', 15, 36.5, '', 5),
(84, 'OJ563S', 'tubo tren', 85, 52.51, '', 2),
(85, 'POLHU234', ' accesorio tren', 62, 93.86, '', 4),
(86, '99OPED', 'soporte tren', 12, 42.5, '', 2),
(87, 'POLHU234', 'estaci�n tubos', 21, 96.79, '', 4),
(88, '99OPED', 'soporte coche', 21, 11.93, '', 2),
(89, 'OJ563S', 'estaci�n tren', 62, 83.64, '', 5),
(90, 'POLHU234', 'sistema tubos', 21, 22.02, '', 2),
(91, 'HEC3555', ' accesorio tren', 15, 64.92, '', 1),
(92, 'POLHU234', 'sistema tren', 85, 4.57, '', 1),
(93, 'OJ563S', 'soporte trabajo', 15, 58.72, '', 2),
(94, 'HEC3555', ' accesorio tubos', 15, 87.71, '', 5),
(95, 'POLHU234', 'tubo capilar', 62, 12.46, '', 1),
(96, '99OPED', ' accesorio trabajo', 85, 14.29, '', 1),
(97, 'DP7J843K9F', 'soporte capilar', 15, 25.26, '', 3),
(98, 'HEC3555', 'tubo tubos', 12, 46.8, '', 2),
(99, 'DP7J843K9F', 'soporte tren', 15, 52.77, '', 3),
(100, 'OJ563S', 'soporte capilar', 62, 49.44, '', 5),
(101, 'B2', 'Herramienta Largo para desbrozar', 1, 18.63, 'minions.jpg', 1),
(102, 'B2', 'Mecanismo Electrico para cortar', 2, 35.36, 'minions.jpg', 1),
(103, 'K4', 'Aparejo Facil para perforar', 3, 13.04, 'minions.jpg', 1),
(104, 'U8', 'Aparejo Facil para lijar', 4, 100.34, 'minions.jpg', 1),
(105, 'C3', 'Mecanismo Circular para romper', 4, 11.7, 'minions.jpg', 1),
(106, 'sdaf2', 'fsafdsa', 4, 2, NULL, 1),
(107, 'AAAAAAAAAAAAAAAA', 'AAAAAAAAAAAAAAAA', 55, 4.32, NULL, 5),
(108, 'asfda', 'sdf', 3, 9.51, NULL, 1),
(109, 'GGGG', 'GGGG', 2, 2.12434, NULL, 3);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tipoproducto`
--

CREATE TABLE `tipoproducto` (
  `id` int(11) NOT NULL,
  `desc` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Volcado de datos para la tabla `tipoproducto`
--

INSERT INTO `tipoproducto` (`id`, `desc`) VALUES
(1, 'Juegos'),
(2, 'Vehiculos'),
(3, 'Oficina'),
(4, 'Consumibles'),
(5, 'Herramientas'),
(6, 'Gafas');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tipousuario`
--

CREATE TABLE `tipousuario` (
  `id` int(11) NOT NULL,
  `desc` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Volcado de datos para la tabla `tipousuario`
--

INSERT INTO `tipousuario` (`id`, `desc`) VALUES
(1, 'Administrador'),
(2, 'jajaja'),
(3, 'Cliente');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `usuario`
--

CREATE TABLE `usuario` (
  `id` int(11) NOT NULL,
  `dni` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `nombre` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `ape1` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `ape2` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `login` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `pass` varchar(512) COLLATE utf8_unicode_ci DEFAULT NULL,
  `id_tipoUsuario` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Volcado de datos para la tabla `usuario`
--

INSERT INTO `usuario` (`id`, `dni`, `nombre`, `ape1`, `ape2`, `login`, `pass`, `id_tipoUsuario`) VALUES
(1, '04631408N', 'hector', 'martinez', 'martinez', 'hec3555', '8C5FD24986A4248CEBF8BE6F4AA27CAB81412C1CA042830765EAC60544F880C5', 1),
(2, '78451627G', 'CSGO', 'ROCKET', 'Escribano', 'usua666', '8C5FD24986A4248CEBF8BE6F4AA27CAB81412C1CA042830765EAC60544F880C5', 3),
(3, '14756425l', 'Maria', 'Gomez', 'Gomez', 'usua6', NULL, 2),
(4, '7845162f', 'Marcos', 'Escribano', 'Belmonte', 'usuar5', NULL, 2),
(5, '54698532o', 'Marcos', 'Belmonte', 'Belmonte', 'us2', NULL, 2),
(6, '7845162f', 'Lidia', 'Perez', 'Belmonte', 'usua6', NULL, 1),
(7, '14756425l', 'Lidia', 'Belmonte', 'Pozuelo', 'usu435', NULL, 1),
(8, '54698532o', 'Hector', 'Martinez', 'Escribano', 'usua95f', NULL, 2),
(9, '7845162f', 'Hector', 'Gomez', 'Escribano', 'usua6', NULL, 2),
(10, '54698532o', 'Pedro', 'Escribano', 'Escribano', 'usuar5', NULL, 1),
(11, '54698532o', 'Maria', 'Belmonte', 'Gomez', 'usu435', NULL, 2),
(12, '14756425l', 'Hector', 'Martinez', 'Escribano', 'us2', NULL, 1),
(13, '04631408j', 'kevin', 'Gomez', 'Perez', 'usuar5', NULL, 1),
(14, '04631408j', 'Hector', 'Escribano', 'Perez', 'usua6', NULL, 1),
(15, '54698532o', 'Alex', 'Escribano', 'Belmonte', 'usuar5', NULL, 2),
(16, '54698532o', 'Maria', 'Martinez', 'Belmonte', 'usuar5', NULL, 2),
(17, '04631408j', 'Alex', 'Pozuelo', 'Belmonte', 'usua6', NULL, 3),
(18, '7845162f', 'Alex', 'Perez', 'Pozuelo', 'us2', NULL, 3),
(19, '7845162f', 'kevin', 'Perez', 'Escribano', 'us2', NULL, 1),
(20, '04631408j', 'Marcos', 'Pozuelo', 'Belmonte', 'us2', NULL, 1),
(21, '04631408j', 'Hector', 'Escribano', 'Escribano', 'us2', NULL, 1),
(22, '54698532o', 'Maria', 'Gomez', 'Belmonte', 'usua95f', NULL, 2),
(23, '54698532o', 'Marcos', 'Pozuelo', 'Perez', 'usua95f', NULL, 1),
(24, '54698532o', 'Alex', 'Gomez', 'Escribano', 'usu435', NULL, 1),
(25, '14756425l', 'kevin', 'Pozuelo', 'Escribano', 'us2', NULL, 2),
(26, '04631408j', 'kevin', 'Gomez', 'Perez', 'us2', NULL, 1),
(27, '14756425l', 'Marcos', 'Belmonte', 'Escribano', 'usuar5', NULL, 1),
(28, '7845162f', 'Pedro', 'Escribano', 'Perez', 'usu435', NULL, 2),
(29, '54698532o', 'Marcos', 'Martinez', 'Belmonte', 'usuar5', NULL, 2),
(30, '54698532o', 'Lidia', 'Martinez', 'Pozuelo', 'usuar5', NULL, 1),
(31, '04631408j', 'Hector', 'Belmonte', 'Escribano', 'usuar5', NULL, 1),
(32, '04631408j', 'Lidia', 'Gomez', 'Martinez', 'usuar5', NULL, 2),
(33, '04631408j', 'Marcos', 'Gomez', 'Belmonte', 'us2', NULL, 1),
(34, '7845162f', 'kevin', 'Pozuelo', 'Belmonte', 'usua95f', NULL, 2),
(35, '54698532o', 'Maria', 'Gomez', 'Martinez', 'usu435', NULL, 1),
(36, '7845162f', 'Alex', 'Pozuelo', 'Pozuelo', 'usua95f', NULL, 1),
(37, '14756425l', 'Lidia', 'Perez', 'Escribano', 'usua95f', NULL, 1),
(38, '14756425l', 'Pedro', 'Belmonte', 'Martinez', 'us2', NULL, 1),
(39, '54698532o', 'Pedro', 'Gomez', 'Martinez', 'usua6', NULL, 2),
(40, '14756425l', 'Maria', 'Gomez', 'Perez', 'usu435', NULL, 1),
(41, '7845162f', 'Hector', 'Escribano', 'Pozuelo', 'usua95f', NULL, 2),
(42, '7845162f', 'Maria', 'Gomez', 'Martinez', 'us2', NULL, 1),
(43, '04631408j', 'Hector', 'Perez', 'Perez', 'usuar5', NULL, 1),
(44, '54698532o', 'kevin', 'Martinez', 'Martinez', 'usua6', NULL, 2),
(45, '7845162f', 'Marcos', 'Escribano', 'Martinez', 'usu435', NULL, 1),
(46, '04631408j', 'Maria', 'Gomez', 'Pozuelo', 'us2', NULL, 2),
(47, '14756425l', 'Hector', 'Belmonte', 'Gomez', 'usua95f', NULL, 2),
(48, '54698532o', 'Maria', 'Belmonte', 'Gomez', 'usua95f', NULL, 1),
(49, '7845162f', 'kevin', 'Martinez', 'Gomez', 'usuar5', NULL, 1),
(50, '7845162f', 'Maria', 'Belmonte', 'Belmonte', 'usuar5', NULL, 1),
(51, '14756425l', 'Marcos', 'Perez', 'Perez', 'usuar5', NULL, 1),
(52, '54698532o', 'Alex', 'Perez', 'Escribano', 'us2', NULL, 1),
(53, '54698532o', 'kevin', 'Martinez', 'Escribano', 'us2', NULL, 2),
(54, '54698532o', 'kevin', 'Belmonte', 'Martinez', 'usua6', NULL, 1),
(55, '14756425l', 'Maria', 'Belmonte', 'Gomez', 'usu435', NULL, 1),
(56, '7845162f', 'Hector', 'Pozuelo', 'Pozuelo', 'us2', NULL, 2),
(57, '04631408j', 'Maria', 'Escribano', 'Perez', 'usu435', NULL, 2),
(58, '7845162f', 'Lidia', 'Gomez', 'Perez', 'us2', NULL, 2),
(59, '14756425l', 'Alex', 'Gomez', 'Pozuelo', 'us2', NULL, 1),
(60, '7845162f', 'kevin', 'Belmonte', 'Gomez', 'usua6', NULL, 1),
(61, '54698532o', 'Maria', 'Escribano', 'Perez', 'usua6', NULL, 1),
(62, '04631408j', 'kevin', 'Pozuelo', 'Escribano', 'us2', NULL, 1),
(63, '54698532o', 'kevin', 'Belmonte', 'Pozuelo', 'us2', NULL, 1),
(64, '7845162f', 'kevin', 'Pozuelo', 'Pozuelo', 'usuar5', NULL, 1),
(65, '04631408j', 'Lidia', 'Gomez', 'Pozuelo', 'usua6', NULL, 1),
(66, '14756425l', 'Hector', 'Escribano', 'Belmonte', 'usua95f', NULL, 1),
(67, '14756425l', 'Marcos', 'Martinez', 'Belmonte', 'usua95f', NULL, 2),
(68, '04631408j', 'Alex', 'Pozuelo', 'Gomez', 'usu435', NULL, 1),
(69, '54698532o', 'Lidia', 'Escribano', 'Escribano', 'us2', NULL, 2),
(70, '7845162f', 'Hector', 'Escribano', 'Belmonte', 'usuar5', NULL, 2),
(71, '04631408j', 'Marcos', 'Martinez', 'Escribano', 'us2', NULL, 2),
(72, '04631408j', 'Pedro', 'Perez', 'Martinez', 'us2', NULL, 2),
(73, '54698532o', 'Alex', 'Gomez', 'Martinez', 'usua95f', NULL, 2),
(74, '54698532o', 'kevin', 'Escribano', 'Escribano', 'usua6', NULL, 1),
(75, '54698532o', 'Marcos', 'Martinez', 'Pozuelo', 'usuar5', NULL, 2),
(76, '54698532o', 'Lidia', 'Belmonte', 'Martinez', 'usu435', NULL, 2),
(77, '04631408j', 'Marcos', 'Gomez', 'Belmonte', 'usuar5', NULL, 2),
(78, '14756425l', 'Maria', 'Perez', 'Martinez', 'usu435', NULL, 1),
(79, '7845162f', 'Marcos', 'Martinez', 'Belmonte', 'usu435', NULL, 2),
(80, '14756425l', 'Marcos', 'Martinez', 'Gomez', 'usuar5', NULL, 2),
(81, '14756425l', 'Hector', 'Pozuelo', 'Belmonte', 'usua6', NULL, 1),
(82, '14756425l', 'Maria', 'Belmonte', 'Escribano', 'usu435', NULL, 2),
(83, '04631408j', 'kevin', 'Pozuelo', 'Perez', 'usua6', NULL, 2),
(84, '7845162f', 'kevin', 'Pozuelo', 'Gomez', 'usu435', NULL, 2),
(85, '04631408j', 'Pedro', 'Escribano', 'Pozuelo', 'usuar5', NULL, 2),
(86, '7845162f', 'Alex', 'Belmonte', 'Perez', 'usuar5', NULL, 2),
(87, '14756425l', 'Pedro', 'Pozuelo', 'Perez', 'usuar5', NULL, 2),
(88, '14756425l', 'Pedro', 'Martinez', 'Martinez', 'usuar5', NULL, 1),
(89, '04631408j', 'Hector', 'Pozuelo', 'Martinez', 'us2', NULL, 1),
(90, '04631408j', 'Marcos', 'Pozuelo', 'Escribano', 'usuar5', NULL, 1),
(91, '7845162f', 'Lidia', 'Gomez', 'Belmonte', 'us2', NULL, 1),
(92, '14756425l', 'Lidia', 'Perez', 'Gomez', 'usuar5', NULL, 1),
(93, '04631408j', 'kevin', 'Gomez', 'Escribano', 'usua6', NULL, 1),
(94, '7845162f', 'Hector', 'Escribano', 'Martinez', 'us2', NULL, 1),
(95, '54698532o', 'Marcos', 'Pozuelo', 'Gomez', 'us2', NULL, 2),
(96, '14756425l', 'Pedro', 'Gomez', 'Escribano', 'usua6', NULL, 1),
(97, '04631408j', 'Lidia', 'Pozuelo', 'Perez', 'usua6', NULL, 2),
(99, '04631408j', 'Alex', 'Escribano', 'Escribano', 'usuar5', NULL, 2),
(100, '14756425l', 'kevin', 'Belmonte', 'Perez', 'usua6', NULL, 1),
(101, '04631408j', 'Hector', 'Pozuelo', 'Perez', 'usuar5', NULL, 1),
(102, '54698532o', 'Pedro', 'Pozuelo', 'Martinez', 'usua6', NULL, 1),
(103, '14756425l', 'kevin', 'Belmonte', 'Escribano', 'usu435', NULL, 2),
(104, '7845162f', 'Lidia', 'Pozuelo', 'Martinez', 'usu435', NULL, 1),
(105, '04631408j', 'Maria', 'Belmonte', 'Gomez', 'usua6', NULL, 1),
(106, '14756425l', 'Marcos', 'Belmonte', 'Martinez', 'usuar5', NULL, 1),
(107, '76567856A', 'yytur', 'tyuty', 'utyu', 'yt', '70626D4B2FF263500A9354F703F41BDE3EA0B81FD1C873A8E3F0BEAECD2F052C', 2);

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `factura`
--
ALTER TABLE `factura`
  ADD PRIMARY KEY (`id`),
  ADD KEY `fk_factura_usuario1_idx` (`id_usuario`);

--
-- Indices de la tabla `linea`
--
ALTER TABLE `linea`
  ADD PRIMARY KEY (`id`),
  ADD KEY `fk_linea_producto1_idx` (`id_producto`),
  ADD KEY `fk_linea_factura1_idx` (`id_factura`);

--
-- Indices de la tabla `producto`
--
ALTER TABLE `producto`
  ADD PRIMARY KEY (`id`),
  ADD KEY `fk_producto_tipoProducto1_idx` (`id_tipoProducto`);

--
-- Indices de la tabla `tipoproducto`
--
ALTER TABLE `tipoproducto`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `tipousuario`
--
ALTER TABLE `tipousuario`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `usuario`
--
ALTER TABLE `usuario`
  ADD PRIMARY KEY (`id`),
  ADD KEY `fk_usuario_tipoUsuario_idx` (`id_tipoUsuario`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `factura`
--
ALTER TABLE `factura`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=24;

--
-- AUTO_INCREMENT de la tabla `linea`
--
ALTER TABLE `linea`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=48;

--
-- AUTO_INCREMENT de la tabla `producto`
--
ALTER TABLE `producto`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=110;

--
-- AUTO_INCREMENT de la tabla `tipoproducto`
--
ALTER TABLE `tipoproducto`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT de la tabla `tipousuario`
--
ALTER TABLE `tipousuario`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT de la tabla `usuario`
--
ALTER TABLE `usuario`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=108;

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `factura`
--
ALTER TABLE `factura`
  ADD CONSTRAINT `fk_factura_usuario1` FOREIGN KEY (`id_usuario`) REFERENCES `usuario` (`id`) ON DELETE SET NULL ON UPDATE CASCADE;

--
-- Filtros para la tabla `linea`
--
ALTER TABLE `linea`
  ADD CONSTRAINT `fk_linea_factura1` FOREIGN KEY (`id_factura`) REFERENCES `factura` (`id`) ON DELETE SET NULL ON UPDATE CASCADE,
  ADD CONSTRAINT `fk_linea_producto1` FOREIGN KEY (`id_producto`) REFERENCES `producto` (`id`) ON DELETE SET NULL ON UPDATE CASCADE;

--
-- Filtros para la tabla `producto`
--
ALTER TABLE `producto`
  ADD CONSTRAINT `fk_producto_tipoProducto1` FOREIGN KEY (`id_tipoProducto`) REFERENCES `tipoproducto` (`id`) ON DELETE SET NULL ON UPDATE CASCADE;

--
-- Filtros para la tabla `usuario`
--
ALTER TABLE `usuario`
  ADD CONSTRAINT `fk_usuario_tipoUsuario` FOREIGN KEY (`id_tipoUsuario`) REFERENCES `tipousuario` (`id`) ON DELETE SET NULL ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
