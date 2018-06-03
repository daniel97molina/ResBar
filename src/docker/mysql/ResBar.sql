-- MySQL Script generated by MySQL Workbench
-- vie 20 abr 2018 13:48:51 CST
-- Model: New Model    Version: 1.0
-- MySQL Workbench Forward Engineering

-- -----------------------------------------------------
-- Schema resbar
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema resbar
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `resbar` DEFAULT CHARACTER SET utf8 ;
USE `resbar` ;

-- -----------------------------------------------------
-- Table `resbar`.`Categoria`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `resbar`.`Categoria` (
  `idCategoria` INT NOT NULL,
  `nombre` VARCHAR(200) NOT NULL,
  PRIMARY KEY (`idCategoria`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_spanish_ci;


-- -----------------------------------------------------
-- Table `resbar`.`Producto`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `resbar`.`Producto` (
  `idProducto` INT NOT NULL,
  `nombre` VARCHAR(200) NOT NULL,
  `precio` DECIMAL(10,4) NOT NULL,
  `idCategoria` INT NOT NULL,
  `area` CHAR(1) NOT NULL,
  PRIMARY KEY (`idProducto`),
  INDEX `fk_idCategoria_idx` (`idCategoria` ASC),
  CONSTRAINT `fk_idCategoria`
    FOREIGN KEY (`idCategoria`)
    REFERENCES `resbar`.`Categoria` (`idCategoria`)
    ON DELETE RESTRICT
    ON UPDATE CASCADE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_spanish_ci;


-- -----------------------------------------------------
-- Table `resbar`.`Orden`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `resbar`.`Orden` (
  `idOrden` INT NOT NULL,
  `mesero` VARCHAR(100) NOT NULL,
  `mesa` VARCHAR(100) NOT NULL,
  `cliente` VARCHAR(100) NOT NULL,
  `fecha` DATETIME NOT NULL,
  `comentario` VARCHAR(350) NULL,
  `total` DECIMAL(10,4) NOT NULL,
  `estado` TINYINT(1) NOT NULL,
  PRIMARY KEY (`idOrden`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_spanish_ci;


-- -----------------------------------------------------
-- Table `resbar`.`DetalleOrden`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `resbar`.`DetalleOrden` (
  `idOrden` INT NOT NULL,
  `idProducto` INT NOT NULL,
  `cantidad` DECIMAL(8,2) NOT NULL,
  PRIMARY KEY (`idOrden`, `idProducto`),
  INDEX `fk_idProducto_idx` (`idProducto` ASC),
  CONSTRAINT `fk_idOrden`
    FOREIGN KEY (`idOrden`)
    REFERENCES `resbar`.`Orden` (`idOrden`)
    ON DELETE RESTRICT
    ON UPDATE CASCADE,
  CONSTRAINT `fk_idProducto`
    FOREIGN KEY (`idProducto`)
    REFERENCES `resbar`.`Producto` (`idProducto`)
    ON DELETE RESTRICT
    ON UPDATE CASCADE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_spanish_ci;


-- -----------------------------------------------------
-- Table `resbar`.`Parametro`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `resbar`.`Parametro` (
  `idParametro` INT NOT NULL,
  `nombre` VARCHAR(100) NOT NULL,
  `valor` VARCHAR(400) NOT NULL,
  PRIMARY KEY (`idParametro`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_spanish_ci;


-- -----------------------------------------------------
-- Data for table `resbar`.`Parametro`
-- -----------------------------------------------------

USE `resbar`;
INSERT INTO `resbar`.`Parametro` (`idParametro`, `nombre`, `valor`) VALUES (01, 'empresa', 'Tienda ABC');
INSERT INTO `resbar`.`Parametro` (`idParametro`, `nombre`, `valor`) VALUES (02, 'direccion', 'Final calle ABC');
INSERT INTO `resbar`.`Parametro` (`idParametro`, `nombre`, `valor`) VALUES (03, 'telefonos', '2440-1536 y 723745');
INSERT INTO `resbar`.`Parametro` (`idParametro`, `nombre`, `valor`) VALUES (04, 'NIT', '0210-050343-00-1');
INSERT INTO `resbar`.`Parametro` (`idParametro`, `nombre`, `valor`) VALUES (05, 'NRC', '24367-5');
INSERT INTO `resbar`.`Parametro` (`idParametro`, `nombre`, `valor`) VALUES (06, 'Giro', 'Cafeteria y Restaurante');
INSERT INTO `resbar`.`Parametro` (`idParametro`, `nombre`, `valor`) VALUES (07, 'email', 'algo@gmail.com');
COMMIT;
