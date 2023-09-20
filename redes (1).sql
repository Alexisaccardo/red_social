-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1:3306
-- Tiempo de generación: 20-09-2023 a las 16:12:20
-- Versión del servidor: 8.0.31
-- Versión de PHP: 8.0.26

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `redes`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `sociales`
--

DROP TABLE IF EXISTS `sociales`;
CREATE TABLE IF NOT EXISTS `sociales` (
  `codigo` int NOT NULL AUTO_INCREMENT,
  `user` varchar(30) NOT NULL,
  `mensaje` varchar(1000) NOT NULL,
  `user_recibe` varchar(30) NOT NULL,
  `estado` varchar(30) NOT NULL,
  PRIMARY KEY (`codigo`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Volcado de datos para la tabla `sociales`
--

INSERT INTO `sociales` (`codigo`, `user`, `mensaje`, `user_recibe`, `estado`) VALUES
(1, 'andres22', 'Hola, buenas tardes amigo, ya te hice el favor que me pidió', 'johan10', 'Visto'),
(2, 'johan10', 'Parce gracias', 'andres22', 'Enviado'),
(3, 'johan10', 'Hola, espero que se encuentre bien, le comento que ya recibi el pedido', 'andres22', 'Visto'),
(4, 'Adri13', 'Hola hijo, como estas?', 'johan10', 'Enviado'),
(5, 'maikel', 'Hola madre, espero este bien', 'Adri13', 'Enviado'),
(6, 'medina16', 'Hola madre, como estás?', 'Adri13', 'Visto');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `usuarios`
--

DROP TABLE IF EXISTS `usuarios`;
CREATE TABLE IF NOT EXISTS `usuarios` (
  `user` varchar(30) NOT NULL,
  `password` varchar(30) NOT NULL,
  PRIMARY KEY (`user`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Volcado de datos para la tabla `usuarios`
--

INSERT INTO `usuarios` (`user`, `password`) VALUES
('Adri13', 'adriana13'),
('andres22', '12345'),
('johan10', 'abc123'),
('maikel', 'maikel'),
('medina16', 'medina16');
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
