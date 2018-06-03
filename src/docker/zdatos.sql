USE `resbar`;
INSERT INTO `resbar`.`Categoria` (`idCategoria`, `nombre`) VALUES (01, 'Bebidas');
INSERT INTO `resbar`.`Categoria` (`idCategoria`, `nombre`) VALUES (02, 'Entradas');
INSERT INTO `resbar`.`Categoria` (`idCategoria`, `nombre`) VALUES (03, 'Postres');
INSERT INTO `resbar`.`Producto` (`idProducto`, `nombre`,`precio`,`idCategoria`,`area`) VALUES (01, 'Coca-cola',1.00,01,'B');
INSERT INTO `resbar`.`Producto` (`idProducto`, `nombre`,`precio`,`idCategoria`,`area`) VALUES (02, 'Fanta',1.00,01,'B');
INSERT INTO `resbar`.`Producto` (`idProducto`, `nombre`,`precio`,`idCategoria`,`area`) VALUES (03, 'Cuba libre',2.50,01,'B');
INSERT INTO `resbar`.`Producto` (`idProducto`, `nombre`,`precio`,`idCategoria`,`area`) VALUES (04, 'Carnitas',4.50,02,'C');
INSERT INTO `resbar`.`Producto` (`idProducto`, `nombre`,`precio`,`idCategoria`,`area`) VALUES (05, 'Bocas Mixtas',3.75,02,'C');
INSERT INTO `resbar`.`Producto` (`idProducto`, `nombre`,`precio`,`idCategoria`,`area`) VALUES (06, 'Pechuguitas',2.75,02,'C');
INSERT INTO `resbar`.`Producto` (`idProducto`, `nombre`,`precio`,`idCategoria`,`area`) VALUES (07, 'Tres Leches',1.50,03,'C');
INSERT INTO `resbar`.`Producto` (`idProducto`, `nombre`,`precio`,`idCategoria`,`area`) VALUES (08, 'Malteada',1.75,03,'C');
INSERT INTO `resbar`.`Orden` (`idOrden`, `mesero`,`mesa`,`cliente`,`fecha`,`comentario`,`total`,`estado`) VALUES (01, 'juan','M1','jose castro','2018-05-17','bebida sin hielo',18.0000,1);
INSERT INTO `resbar`.`Orden` (`idOrden`, `mesero`,`mesa`,`cliente`,`fecha`,`comentario`,`total`,`estado`) VALUES (02, 'jose','M2','irvin molina','2018-05-17','sin salsa',25.0000,1);
INSERT INTO `resbar`.`Orden` (`idOrden`, `mesero`,`mesa`,`cliente`,`fecha`,`comentario`,`total`,`estado`) VALUES (03, 'Xiomara','M4','andrea','2018-05-17','doble porcion de papas',21.0000,1);
INSERT INTO `resbar`.`Orden` (`idOrden`, `mesero`,`mesa`,`cliente`,`fecha`,`comentario`,`total`,`estado`) VALUES (04, 'Luis','M5','David','2018-05-17','tortilla extra',21.0000,1);

INSERT INTO `resbar`.`Orden` (`idOrden`, `mesero`,`mesa`,`cliente`,`fecha`,`comentario`,`total`,`estado`) VALUES (05, 'Luis','M5','cliente-terminado','2018-05-10','tortilla extra',21.0000,0);
INSERT INTO `resbar`.`Orden` (`idOrden`, `mesero`,`mesa`,`cliente`,`fecha`,`comentario`,`total`,`estado`) VALUES (06, 'juan','M5','cliente-terminado2','2018-05-20','tortilla extra',21.0000,0);
INSERT INTO `resbar`.`Orden` (`idOrden`, `mesero`,`mesa`,`cliente`,`fecha`,`comentario`,`total`,`estado`) VALUES (07, 'Xiomara','M5','cliente-terminado3','2018-05-23','tortilla extra',21.0000,0);
INSERT INTO `resbar`.`Orden` (`idOrden`, `mesero`,`mesa`,`cliente`,`fecha`,`comentario`,`total`,`estado`) VALUES (08, 'Luis','M5','cliente-terminado','2018-05-25','tortilla extra',21.0000,0);

INSERT INTO `resbar`.`DetalleOrden` (`idOrden`, `idProducto`,`cantidad`) VALUES (01, 3,2);
INSERT INTO `resbar`.`DetalleOrden` (`idOrden`, `idProducto`,`cantidad`) VALUES (02, 1,4);
INSERT INTO `resbar`.`DetalleOrden` (`idOrden`, `idProducto`,`cantidad`) VALUES (03, 4,1);
COMMIT;
