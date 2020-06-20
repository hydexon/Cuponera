DROP DATABASE IF EXISTS Cuponera;
CREATE DATABASE Cuponera;
USE Cuponera;

CREATE TABLE Roles(
    RolId  INTEGER NOT NULL PRIMARY KEY AUTO_INCREMENT,
    Rol    VARCHAR(32) NOT NULL,
    RolVal INTEGER(1) NOT NULL
);

CREATE TABLE Usuarios(
    UserId INTEGER NOT NULL PRIMARY KEY AUTO_INCREMENT,
    NombreUsuario VARCHAR(32) NOT NULL,
    NombreCompleto VARCHAR(128) NOT NULL,
    Correo VARCHAR(48) NOT NULL,
    Contrasena VARCHAR(256) NOT NULL,
    Rol INTEGER NOT NULL,
    FOREIGN KEY(Rol) REFERENCES Roles(RolId)
);

CREATE TABLE Rubros(
    RubroId INTEGER NOT NULL PRIMARY KEY AUTO_INCREMENT,
    Rubro   VARCHAR(90) NOT NULL
);

CREATE TABLE Empresas(
    EmpresaId   VARCHAR(6) NOT NULL PRIMARY KEY,
    Nombre      VARCHAR(60) NOT NULL,
    Direccion   VARCHAR(128) NOT NULL,
    Telefono    VARCHAR(12) NOT NULL,
    Correo      VARCHAR(50) NOT NULL,
    Rubro       INTEGER NOT NULL,
    Comision    DOUBLE NOT NULL,
    FOREIGN KEY(Rubro) REFERENCES Rubros(RubroId)
);


CREATE TABLE Promociones(
    PromoId INTEGER NOT NULL PRIMARY KEY AUTO_INCREMENT,
    PrecioRegular DOUBLE NOT NULL,
    PrecioOferta  DOUBLE NOT NULL,
    FechaInicio   DATE   NOT NULL,
    FechaFinal    DATE   NOT NULL,
    FechaLimite   DATE   NOT NULL,
    CantidadLimite INTEGER,
    Descripcion   VARCHAR(255) NOT NULL,
    Empresa     VARCHAR(6) NOT NULL,
    StatusCupon  INTEGER(1) NOT NULL,
    FOREIGN KEY(Empresa) REFERENCES Empresas(EmpresaId)
);

CREATE TABLE JustificacionRechazos(
    JustificacionId INTEGER NOT NULL PRIMARY KEY AUTO_INCREMENT,
    Promocion INTEGER NOT NULL,
    Descripcion VARCHAR(255) NOT NULL,
    FOREIGN KEY(Promocion) REFERENCES Promociones(PromoId)
);


CREATE TABLE Clientes(
    ClienteId  INTEGER NOT NULL PRIMARY KEY AUTO_INCREMENT,
    Username   VARCHAR(32) NOT NULL,
    Nombres    VARCHAR(75) NOT NULL,
    Apellidos  VARCHAR(75) NOT NULL,
    DUI        VARCHAR(9)  NOT NULL,
    Telefono   VARCHAR(12) NOT NULL,
    Correo     VARCHAR(64) NOT NULL,
    Contrasena VARCHAR(256) NOT NULL,
    Confirmado INTEGER(1) NOT NULL
);

CREATE TABLE ClienteConfirmToken(
    TokenId   INTEGER NOT NULL PRIMARY KEY AUTO_INCREMENT,
    Cliente   INTEGER NOT NULL,
    Token     VARCHAR(72) NOT NULL,
    FOREIGN KEY(Cliente) REFERENCES Clientes(ClienteId)
);

CREATE TABLE Dependientes(
    DependienteId INTEGER NOT NULL PRIMARY KEY AUTO_INCREMENT,
    Nombre       VARCHAR(48) NOT NULL,
    Apellidos    VARCHAR(48) NOT NULL,
    Correo       VARCHAR(32) NOT NULL,
    Contrasena   VARCHAR(256) NOT NULL,
    Username     VARCHAR(32) NOT NULL
);

CREATE TABLE Cupones(
    CuponId VARCHAR(14) NOT NULL PRIMARY KEY,
    Promocion INTEGER NOT NULL,
    Cliente INTEGER NOT NULL,
    Estado  INTEGER(1) NOT NULL,
    FOREIGN KEY(Promocion) REFERENCES Promociones(PromoId),
    FOREIGN KEY(Cliente)   REFERENCES Clientes(ClienteId)
);


INSERT INTO Roles VALUES(1, 'Administrador', 0);
INSERT INTO Roles VALUES(2, 'AdministradorEmpresaOfertante', 1);

INSERT INTO Usuarios VALUES(NULL, 'Root', 'Administrador', 'root@root.com', 'root', 1);
