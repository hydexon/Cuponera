-- phpMyAdmin SQL Dump
-- version 4.8.5
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Jun 23, 2020 at 03:09 AM
-- Server version: 10.1.38-MariaDB
-- PHP Version: 5.6.40

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `cuponera`
--

-- --------------------------------------------------------

--
-- Table structure for table `clienteconfirmtoken`
--

CREATE TABLE `clienteconfirmtoken` (
  `TokenId` int(11) NOT NULL,
  `Cliente` int(11) NOT NULL,
  `Token` varchar(72) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `clientes`
--

CREATE TABLE `clientes` (
  `ClienteId` int(11) NOT NULL,
  `Username` varchar(32) NOT NULL,
  `Nombres` varchar(75) NOT NULL,
  `Apellidos` varchar(75) NOT NULL,
  `DUI` varchar(9) NOT NULL,
  `Telefono` varchar(12) NOT NULL,
  `Correo` varchar(64) NOT NULL,
  `Contrasena` varchar(256) NOT NULL,
  `Confirmado` int(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `clientes`
--

INSERT INTO `clientes` (`ClienteId`, `Username`, `Nombres`, `Apellidos`, `DUI`, `Telefono`, `Correo`, `Contrasena`, `Confirmado`) VALUES
(1, 'cliente1', 'Foo', 'Bar', '123456789', '22227464', 'correo@hotmail.com', '1234', 1);

-- --------------------------------------------------------

--
-- Table structure for table `cupones`
--

CREATE TABLE `cupones` (
  `CuponId` varchar(14) NOT NULL,
  `Promocion` int(11) NOT NULL,
  `Cliente` int(11) NOT NULL,
  `Estado` int(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `cupones`
--

INSERT INTO `cupones` (`CuponId`, `Promocion`, `Cliente`, `Estado`) VALUES
('1', 1, 1, 0);

-- --------------------------------------------------------

--
-- Table structure for table `dependientes`
--

CREATE TABLE `dependientes` (
  `DependienteId` int(11) NOT NULL,
  `Nombre` varchar(48) NOT NULL,
  `Apellidos` varchar(48) NOT NULL,
  `Correo` varchar(32) NOT NULL,
  `Contrasena` varchar(256) NOT NULL,
  `Username` varchar(32) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `dependientes`
--

INSERT INTO `dependientes` (`DependienteId`, `Nombre`, `Apellidos`, `Correo`, `Contrasena`, `Username`) VALUES
(1, 'Jaime', 'Perez', 'jperez@test.com', '1234', 'jperez');

-- --------------------------------------------------------

--
-- Table structure for table `empresas`
--

CREATE TABLE `empresas` (
  `EmpresaId` varchar(6) NOT NULL,
  `Nombre` varchar(60) NOT NULL,
  `Direccion` varchar(128) NOT NULL,
  `Telefono` varchar(12) NOT NULL,
  `Correo` varchar(50) NOT NULL,
  `Rubro` int(11) NOT NULL,
  `Comision` double NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `empresas`
--

INSERT INTO `empresas` (`EmpresaId`, `Nombre`, `Direccion`, `Telefono`, `Correo`, `Rubro`, `Comision`) VALUES
('1', 'Hotel Intercontinental', 'Boulevard De Los Heroes, Y Avenue Sisimiles, San Salvador', '22113333', 'contacto@intercontinental.com.sv', 1, 10);

-- --------------------------------------------------------

--
-- Table structure for table `justificacionrechazos`
--

CREATE TABLE `justificacionrechazos` (
  `JustificacionId` int(11) NOT NULL,
  `Promocion` int(11) NOT NULL,
  `Descripcion` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `promociones`
--

CREATE TABLE `promociones` (
  `PromoId` int(11) NOT NULL,
  `PrecioRegular` double NOT NULL,
  `PrecioOferta` double NOT NULL,
  `FechaInicio` date NOT NULL,
  `FechaFinal` date NOT NULL,
  `FechaLimite` date NOT NULL,
  `CantidadLimite` int(11) DEFAULT NULL,
  `Descripcion` varchar(255) NOT NULL,
  `Empresa` varchar(6) NOT NULL,
  `StatusCupon` int(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `promociones`
--

INSERT INTO `promociones` (`PromoId`, `PrecioRegular`, `PrecioOferta`, `FechaInicio`, `FechaFinal`, `FechaLimite`, `CantidadLimite`, `Descripcion`, `Empresa`, `StatusCupon`) VALUES
(1, 100, 80, '2020-06-19', '2020-06-30', '2020-06-30', 10, 'Hospedaje para dos personas', '1', 1);

-- --------------------------------------------------------

--
-- Table structure for table `roles`
--

CREATE TABLE `roles` (
  `RolId` int(11) NOT NULL,
  `Rol` varchar(32) NOT NULL,
  `RolVal` int(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `roles`
--

INSERT INTO `roles` (`RolId`, `Rol`, `RolVal`) VALUES
(1, 'Administrador', 0),
(2, 'AdministradorEmpresaOfertante', 1);

-- --------------------------------------------------------

--
-- Table structure for table `rubros`
--

CREATE TABLE `rubros` (
  `RubroId` int(11) NOT NULL,
  `Rubro` varchar(90) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `rubros`
--

INSERT INTO `rubros` (`RubroId`, `Rubro`) VALUES
(1, 'Hosteleria');

-- --------------------------------------------------------

--
-- Table structure for table `usuarios`
--

CREATE TABLE `usuarios` (
  `UserId` int(11) NOT NULL,
  `NombreUsuario` varchar(32) NOT NULL,
  `NombreCompleto` varchar(128) NOT NULL,
  `Correo` varchar(48) NOT NULL,
  `Contrasena` varchar(256) NOT NULL,
  `Rol` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `usuarios`
--

INSERT INTO `usuarios` (`UserId`, `NombreUsuario`, `NombreCompleto`, `Correo`, `Contrasena`, `Rol`) VALUES
(1, 'Root', 'Administrador', 'root@root.com', 'root', 1);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `clienteconfirmtoken`
--
ALTER TABLE `clienteconfirmtoken`
  ADD PRIMARY KEY (`TokenId`),
  ADD KEY `Cliente` (`Cliente`);

--
-- Indexes for table `clientes`
--
ALTER TABLE `clientes`
  ADD PRIMARY KEY (`ClienteId`);

--
-- Indexes for table `cupones`
--
ALTER TABLE `cupones`
  ADD PRIMARY KEY (`CuponId`),
  ADD KEY `Promocion` (`Promocion`),
  ADD KEY `Cliente` (`Cliente`);

--
-- Indexes for table `dependientes`
--
ALTER TABLE `dependientes`
  ADD PRIMARY KEY (`DependienteId`);

--
-- Indexes for table `empresas`
--
ALTER TABLE `empresas`
  ADD PRIMARY KEY (`EmpresaId`),
  ADD KEY `Rubro` (`Rubro`);

--
-- Indexes for table `justificacionrechazos`
--
ALTER TABLE `justificacionrechazos`
  ADD PRIMARY KEY (`JustificacionId`),
  ADD KEY `Promocion` (`Promocion`);

--
-- Indexes for table `promociones`
--
ALTER TABLE `promociones`
  ADD PRIMARY KEY (`PromoId`),
  ADD KEY `Empresa` (`Empresa`);

--
-- Indexes for table `roles`
--
ALTER TABLE `roles`
  ADD PRIMARY KEY (`RolId`);

--
-- Indexes for table `rubros`
--
ALTER TABLE `rubros`
  ADD PRIMARY KEY (`RubroId`);

--
-- Indexes for table `usuarios`
--
ALTER TABLE `usuarios`
  ADD PRIMARY KEY (`UserId`),
  ADD KEY `Rol` (`Rol`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `clienteconfirmtoken`
--
ALTER TABLE `clienteconfirmtoken`
  MODIFY `TokenId` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `clientes`
--
ALTER TABLE `clientes`
  MODIFY `ClienteId` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT for table `dependientes`
--
ALTER TABLE `dependientes`
  MODIFY `DependienteId` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT for table `justificacionrechazos`
--
ALTER TABLE `justificacionrechazos`
  MODIFY `JustificacionId` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `promociones`
--
ALTER TABLE `promociones`
  MODIFY `PromoId` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT for table `roles`
--
ALTER TABLE `roles`
  MODIFY `RolId` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `rubros`
--
ALTER TABLE `rubros`
  MODIFY `RubroId` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT for table `usuarios`
--
ALTER TABLE `usuarios`
  MODIFY `UserId` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `clienteconfirmtoken`
--
ALTER TABLE `clienteconfirmtoken`
  ADD CONSTRAINT `clienteconfirmtoken_ibfk_1` FOREIGN KEY (`Cliente`) REFERENCES `clientes` (`ClienteId`);

--
-- Constraints for table `cupones`
--
ALTER TABLE `cupones`
  ADD CONSTRAINT `cupones_ibfk_1` FOREIGN KEY (`Promocion`) REFERENCES `promociones` (`PromoId`),
  ADD CONSTRAINT `cupones_ibfk_2` FOREIGN KEY (`Cliente`) REFERENCES `clientes` (`ClienteId`);

--
-- Constraints for table `empresas`
--
ALTER TABLE `empresas`
  ADD CONSTRAINT `empresas_ibfk_1` FOREIGN KEY (`Rubro`) REFERENCES `rubros` (`RubroId`);

--
-- Constraints for table `justificacionrechazos`
--
ALTER TABLE `justificacionrechazos`
  ADD CONSTRAINT `justificacionrechazos_ibfk_1` FOREIGN KEY (`Promocion`) REFERENCES `promociones` (`PromoId`);

--
-- Constraints for table `promociones`
--
ALTER TABLE `promociones`
  ADD CONSTRAINT `promociones_ibfk_1` FOREIGN KEY (`Empresa`) REFERENCES `empresas` (`EmpresaId`);

--
-- Constraints for table `usuarios`
--
ALTER TABLE `usuarios`
  ADD CONSTRAINT `usuarios_ibfk_1` FOREIGN KEY (`Rol`) REFERENCES `roles` (`RolId`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
