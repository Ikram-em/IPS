-- =========================================
-- BASE DE DATOS: DATOS INICIALES (VERSIÓN COMPLETA, INGRESOS MAXIMIZADOS)
-- =========================================

-- --------------------------------------------------
-- INSTALACIONES
-- --------------------------------------------------
INSERT INTO Instalacion (nombre)
VALUES 
('Campo A'),
('Campo B'),
('Pista 1'),
('Campo Principal'),
('Campo C'),
('Campo D');

INSERT INTO Rol (nombre)
VALUES
('Gerente'),
('PersonalDeVentas'),
('PersonalDeReservas'),
('Entrenador'),
('Comunicacion'),
('GestorDeAcciones'),
('Medico');

INSERT INTO Posicion (nombre, tipo, id_rol)
VALUES
('Gerente', 'No Deportiva', 1),
('Vendedor de entradas', 'No Deportiva', 2),
('Encargado de tienda', 'No Deportiva', 2),
('Gestor de instalaciones', 'No Deportiva', 3),
('Empleado de tienda', 'No Deportiva', 2),
('Jardinería', 'No Deportiva', null),
('Cocina', 'No Deportiva', null),
('Director de comunicaciones', 'No Deportiva', 5),
('Medico', 'No Deportiva', 7),
('Jugador', 'Deportiva', null),
('Entrenador', 'Deportiva', 4),
('Gestor de acciones', 'No Deportiva', 6);

-- --------------------------------------------------
-- EMPLEADOS AJUSTADOS
-- --------------------------------------------------

-- No Deportivos (id 1 a 3)
INSERT INTO Empleado (dni, nombre, apellido, fecha_nacimiento, telefono, salario_anual, id_instalacion)
VALUES 
('12345678A', 'Laura', 'Pérez', '1985-06-15', 612345678, 2800.00, 1),
('12345678B', 'Sara', 'Pérez', '1985-06-15', 612345678, 2800.00, 2),
('12345678C', 'Maria', 'Pérez', '1985-06-15', 612345678, 2800.00, 2);

-- Entrenadores senior (id 4 a 7)
INSERT INTO Empleado (dni, nombre, apellido, fecha_nacimiento, telefono, salario_anual, id_instalacion)
VALUES 
('65432198A', 'José', 'Martínez', '1978-02-15', 611223344, 3900.00, 1),
('65432198B', 'Andrés', 'López', '1980-07-12', 611334455, 3800.00, 1),
('65432198C', 'Raquel', 'Serrano', '1983-11-09', 611445566, 3700.00, 1),
('65432198D', 'Manuel', 'Gutiérrez', '1975-04-23', 611556677, 3950.00, 1);

-- Entrenadores adicionales (id 8 a 15)
INSERT INTO Empleado (dni, nombre, apellido, fecha_nacimiento, telefono, salario_anual, id_instalacion)
VALUES 
('65432198E', 'Pablo', 'Crespo', '1982-09-11', 611667788, 3750.00, 1),
('65432198F', 'Lucía', 'Ramírez', '1984-12-02', 611778899, 3650.00, 1),
('65432198G', 'Mario', 'Alonso', '1979-05-07', 611889900, 3800.00, 1),
('20000001A', 'Ismael', 'Cano', '1990-03-15', 630000001, 3500.00, 1),
('20000002B', 'Claudia', 'Domínguez', '1988-07-22', 630000002, 3550.00, 1),
('20000003C', 'Óscar', 'Sáez', '1991-06-10', 630000003, 3450.00, 1),
('20000004D', 'Beatriz', 'Nieto', '1989-09-28', 630000004, 3500.00, 1),
('20000005E', 'Iván', 'Torres', '1992-11-15', 630000005, 3400.00, 1);

-- Jugadores Seniors (id 16 a 35)
INSERT INTO Empleado (dni, nombre, apellido, fecha_nacimiento, telefono, salario_anual)
VALUES 
('12345678A', 'Carlos', 'García', '1995-03-15', 600123456, 3200.00),
('23456789B', 'Luis', 'Martínez', '1994-07-22', 600234567, 3150.00),
('34567890C', 'Jorge', 'López', '1996-05-10', 600345678, 3100.00),
('45678901D', 'Miguel', 'Sánchez', '1993-11-30', 600456789, 3300.00),
('56789012E', 'Raúl', 'Pérez', '1997-01-25', 600567890, 3050.00),
('67890123F', 'David', 'Gómez', '1995-09-14', 600678901, 3250.00),
('78901234G', 'Adrián', 'Ruiz', '1992-04-17', 600789012, 3350.00),
('89012345H', 'Sergio', 'Torres', '1996-12-05', 600890123, 3100.00),
('90123456I', 'Iván', 'Ramírez', '1993-06-27', 600901234, 3200.00),
('01234567J', 'Manuel', 'Fernández', '1997-03-19', 601012345, 3000.00),
('11234567K', 'Pablo', 'Jiménez', '1994-10-01', 601112345, 3100.00),
('21234567L', 'Javier', 'Morales', '1995-02-09', 601212345, 300.00),
('31234567M', 'Álvaro', 'Castro', '1992-08-12', 601312345, 3350.00),
('41234567N', 'Daniel', 'Ortiz', '1993-05-23', 601412345, 3250.00),
('51234567O', 'Francisco', 'Vega', '1996-09-29', 601512345, 3100.00),
('61234567P', 'Alberto', 'Molina', '1994-11-11', 601612345, 3150.00),
('71234567Q', 'Rubén', 'Soto', '1995-07-07', 601712345, 3200.00),
('81234567R', 'Diego', 'Reyes', '1993-01-20', 601812345, 3400.00),
('91234567S', 'Fernando', 'Navarro', '1997-06-03', 601912345, 2950.00),
('01234567T', 'Marcos', 'Díaz', '1994-04-16', 602012345, 3100.00);

-- Prebenjamines (id 36 a 42)
INSERT INTO Empleado (dni, nombre, apellido, fecha_nacimiento, telefono, salario_anual)
VALUES 
('10000001A','Leo','Martín','2018-05-10',620000001,25.00),
('10000002B','Álex','González','2019-03-22',620000002,225.00),
('10000013M','Iker','Martín','2018-07-01',620000013,100.00),
('10000014N','Thiago','Pérez','2019-02-17',620000014,25.00),
('10000015O','Adrián','Santos','2018-11-23',620000015,25.00),
('10000016P','Mario','Delgado','2019-04-09',620000016,205.00),
('10000017Q','Lucas','Román','2018-09-14',620000017,205.00);

-- Benjamines (id 43 a 49)
INSERT INTO Empleado (dni, nombre, apellido, fecha_nacimiento, telefono, salario_anual)
VALUES 
('10000003C','Mario','Serrano','2016-07-15',620000003,325.00),
('10000004D','Pablo','Ruiz','2015-11-03',620000004,35.00),
('10000018R','Diego','Castillo','2016-03-12',620000018,325.00),
('10000019S','Joel','Vicente','2015-07-28',620000019,325.00),
('10000020T','Nico','Gallego','2015-10-10',620000020,25.00),
('10000021U','Álex','Muñiz','2016-05-03',620000021,35.00),
('10000022V','Bruno','Garrido','2015-12-19',620000022,35.00);


-- --------------------------------------------------
-- Alevines (id 50 a 63)
-- --------------------------------------------------
INSERT INTO Empleado (dni, nombre, apellido, fecha_nacimiento, telefono, salario_anual)
VALUES 
('10000005E','Sergio','Navarro','2014-02-12',620000005,250.00),
('10000006F','Lucas','Hernández','2013-09-25',620000006,20.00),
('10000023W','Unai','Marín','2013-08-06',620000023,20.00),
('10000024X','Pol','Rosales','2014-03-29',620000024,250.00),
('10000025Y','Ian','Herrero','2013-11-15',620000025,20.00),
('10000026Z','Martí','Cortés','2014-01-25',620000026,20.00),
('10000027A','Izan','Durán','2013-05-20',620000027,250.00),
('30000001A','Enzo','Giménez','2013-06-11',640000001,30.00),
('30000002B','Samuel','Peña','2012-10-05',640000002,30.00),
('30000003C','Martín','Lara','2013-02-20',640000003,30.00),
('30000004D','Noah','Corral','2012-09-30',640000004,30.00),
('30000005E','Eric','Muñoz','2013-01-18',640000005,30.00),
('30000006F','Tomás','Ríos','2012-12-03',640000006,30.00),
('30000007G','Joel','Herrera','2013-08-25',640000007,30.00);


-- Se añaden nuevos jardineros con salarios ajustados
INSERT INTO Empleado (dni, nombre, apellido, fecha_nacimiento, telefono, salario_anual, id_instalacion)
VALUES
('40000001A', 'Antonio', 'Ramos', '1980-04-10', 650000001, 200.00, 1),
('40000002B', 'Francisco', 'Gil', '1979-07-18', 650000002, 190.00, 2),
('40000003C', 'Raúl', 'Morales', '1985-05-23', 650000003, 2050.00, 2),
('40000004D', 'David', 'Santos', '1982-03-09', 650000004, 2100.00, 1),
('40000005E', 'Miguel', 'Vega', '1984-11-27', 650000005, 2150.00, 1),
('40000006F', 'José', 'Navarro', '1981-01-13', 650000006, 1900.00, 2),
('40000007G', 'Alberto', 'Pérez', '1983-09-02', 650000007, 2050.00, 2),
('40000008H', 'Carlos', 'Gómez', '1978-12-20', 650000008, 2200.00, 1);

-- Vendedor de entradas (id 72)
INSERT INTO Empleado (dni, nombre, apellido, fecha_nacimiento, telefono, salario_anual, id_instalacion)
VALUES
('80000001A', 'María', 'Suárez', '1980-04-10', 650000001, 2700.00, 1);

-- Medico (id 73)
INSERT INTO Empleado (dni, nombre, apellido, fecha_nacimiento, telefono, salario_anual, id_instalacion)
VALUES
('80000002A', 'Sara', 'García', '1980-04-11', 650008001, 27000.00, 2);


INSERT INTO EmpleadoNoDeportivo (id_empleado, id_posicion)
VALUES
(1, 1),
(2, 1),
(3, 6),
(64, 6),
(65, 6),
(66, 6),
(67, 6),
(68, 6),
(69, 6),
(70, 6),
(71, 6),
(72, 2),
(73, 7);

-- --------------------------------------------------
-- EMPLEADOS DEPORTIVOS
-- --------------------------------------------------

INSERT INTO EmpleadoDeportivo (id_empleado, id_posicion)
VALUES
(4, 11),
(5, 11),
(6, 11),
(7, 11),
(8, 11),
(9, 11),
(10, 11),
(11, 11),
(12, 11),
(13, 11),
(14, 11),
(15, 11),
(16, 10),
(17, 10),
(18, 10),
(19, 10),
(20, 10),
(21, 10),
(22, 10),
(23, 10),
(24, 10),
(25, 10),
(26, 10),
(27, 10),
(28, 10),
(29, 10),
(30, 10),
(31, 10),
(32, 10),
(33, 10),
(34, 10),
(35, 10);

-- --------------------------------------------------
-- JUGADORES DE FORMACION
-- --------------------------------------------------
INSERT INTO EmpleadoDeportivo (id_empleado, id_posicion)
VALUES
(36, 10),
(37, 10),
(38, 10),
(39, 10),
(40, 10),
(41, 10),
(42, 10),
(43, 10),
(44, 10),
(45, 10),
(46, 10),
(47, 10),
(48, 10),
(49, 10),
(50, 10),
(51, 10),
(52, 10),
(53, 10),
(54, 10),
(55, 10),
(56, 10),
(57, 10),
(58, 10),
(59, 10),
(60, 10),
(61, 10),
(62, 10),
(63, 10);

-- --------------------------------------------------
-- EQUIPOS Y PLANTILLAS
-- --------------------------------------------------

-- Equipo Alevín A
INSERT INTO Equipo (nombre, id_primer_entrenador, id_segundo_entrenador, tipo_equipo, categoria_equipo)
VALUES 
('Alevín A',11,12,'formacion','alevin'),
('Equipo Profesional Prueba',4,5,'profesional_primer_equipo',NULL),
('Equipo Alevín Prueba',11,12,'formacion','alevin'),
('Equipo Filial',8,9,'profesional_filial',NULL);

-- Plantilla Alevín A
INSERT INTO Plantilla (id_equipo,id_jugador) 
VALUES
(1,57),(1,58),(1,59),(1,60),(1,61),(1,62),(1,63),(1,36),(1,37),
(2,16),(2,17),(2,18),(2,19),(2,20),(2,21),(2,22),
(3,50),(3,51),(3,52),(3,53),(3,54),
(4,23),(4,24),(4,25),(4,26),(4,27),(4,28),(4,29);

-- --------------------------------------------------
-- HORARIOS, ENTRENAMIENTOS Y PARTIDOS
-- --------------------------------------------------

-- Horario semanal del empleado 1
INSERT INTO HorarioSemanal (id_empleado, dia, hora_inicio, hora_fin)
VALUES
(1,'Lunes','08:00','16:00'),
(1,'Martes','08:00','16:00'),
(1,'Miércoles','08:00','16:00'),
(1,'Jueves','08:00','16:00'),
(1,'Viernes','08:00','12:00'),
(1,'Sábado','08:00','12:00'),
(1,'Domingo',NULL,NULL),

(3,'Lunes','08:00','16:00'),
(3,'Martes','08:00','16:00'),
(3,'Miércoles','08:00','16:00'),
(3,'Jueves','08:00','16:00'),
(3,'Viernes','08:00','12:00'),
(3,'Sábado','08:00','12:00'),
(3,'Domingo',NULL,NULL);


-- Horario específico del empleado 1
INSERT INTO HorarioEspecifico (id_empleado, fecha, dia_semana, hora_inicio, hora_fin)
VALUES		
(1, '2025-12-06', 'Sábado', '08:00', '09:00'),
(2, '2025-12-06', 'Sábado', '09:00', '12:00');


-- Entrenamientos
INSERT INTO Entrenamiento (id_equipo, fecha, hora_inicio, hora_fin, id_instalacion)
VALUES
(1, '2025-12-01', '09:00', '10:30', 1),
(1, '2025-12-02', '11:00', '12:30', 3),
(1, '2025-12-03', '11:00', '12:30', 3),
(1, '2025-12-04', '11:00', '12:30', 3);
-- Franjas de entrevista (jugadores profesionales)
INSERT INTO FranjaEntrevista (id_jugador, fecha, hora_inicio, hora_fin)
VALUES
(16,'2025-10-21','10:00','10:30'),
(16,'2025-10-24','09:00','10:30'),
(16,'2025-10-24','08:00','08:30'),
(16,'2025-10-24','10:00','11:30'),
(17,'2025-10-24','10:30','11:00'),
(18,'2025-10-24','11:00','11:30'),
(19,'2025-10-24','11:30','12:00');

INSERT INTO FranjaEntrevista (id_jugador, fecha, hora_inicio, hora_fin, medio_comunicacion)
VALUES
(16,'2025-10-27','10:00','10:30','Marca'),
(24, '2025-11-28', '12:15', '12:45', 'Marca');

-- Franjas de entrevista solo para jugadores profesionales del equipo 10
INSERT INTO FranjaEntrevista (id_jugador, fecha, hora_inicio, hora_fin) VALUES
(16, '2025-10-20', '10:00', '10:30'),
(17, '2025-10-20', '10:30', '11:00'),
(18, '2025-10-21', '11:00', '11:30'),
(19, '2025-10-21', '11:30', '12:00');

-- Equipo externo
INSERT INTO EquipoExterno (nombre)
VALUES 
('Barcelona'),
('Real Madrid');

-- Entrenamientos
INSERT INTO Entrenamiento (id_equipo, fecha, hora_inicio, hora_fin, id_instalacion)
VALUES
(1, '2025-12-01', '09:00', '10:30', 1),
(1, '2025-12-02', '11:00', '12:30', 3),
(1, '2025-12-03', '11:00', '12:30', 3),
(1, '2025-12-04', '11:00', '12:30', 3);

-- --------------------------------------------------
-- RESERVAS EXTERNAS
-- --------------------------------------------------
INSERT INTO ReservaExterna (nombre_usuario, num_tarjeta, id_instalacion, fecha, hora_inicio, hora_fin, precio_total)
VALUES
('Juan Pérez', '1234567812345678', 1, '2025-12-01', '12:00', '13:00', 50.00),
('María López', '8765432187654321', 2, '2025-12-02', '13:00', '15:30', 55.00),
('Carlos Gómez', '1111222233334444', 3, '2025-12-03', '14:00', '15:30', 60.00),
('Ana Fernández', '5555666677778888', 4, '2025-12-04', '13:00', '14:00', 45.00),
('Luis Martínez', '9999000011112222', 1, '2025-12-05', '14:30', '15:30', 50.00),
('Sofía Torres', '2222333344445555', 2, '2025-12-06', '14:00', '15:00', 40.00),
('Miguel Sánchez', '3333444455556666', 3, '2025-12-07', '15:00', '16:00', 60.00),
('Laura Ramírez', '4444555566667777', 4, '2025-12-08', '15:00', '17:00', 50.00),
('Pedro Díaz', '6666777788889999', 1, '2025-12-09', '09:00', '10:30', 45.00),
('Elena Navarro', '7777888899990000', 2, '2025-12-10', '10:30', '11:30', 50.00),
('Diego Torres', '8888999900001111', 3, '2025-12-11', '11:00', '12:30', 60.00),
('Valentina Ruiz', '9999111122223333', 4, '2025-12-12', '12:00', '13:00', 50.00),
('Héctor García', '1111333344445555', 1, '2025-12-13', '13:00', '14:30', 55.00),
('Camila Soto', '2222444455556666', 2, '2025-12-14', '14:00', '15:00', 45.00),
('Ricardo Molina', '3333555566667777', 3, '2025-12-15', '15:00', '16:30', 60.00),
('Natalia Paredes', '4444666677778888', 4, '2025-12-16', '16:00', '17:00', 50.00);

-- Las contraseñas son 'laura_perez', 'maria_suarez', 'ismael_cano', 'claudia_dominguez', 'jose_martinez', 'andres_lopez' y 'sara_medico'
INSERT INTO User (username, password, id_rol, id_empleado) 
VALUES 
('lPerez', '$2a$12$be5/pfqkvbvVMD31jS6aj.jThQZ/LEileUvkcHTKgfsDEBJHkyeqS', 1, 1),
('mSuarez', '$2a$12$0P2wQdGe0P3J4JGLt3OIx.UZQYJ7CeX.dby3Z2Za5PVNjZfnYnfia', 2, 72),
('iCano', '$2a$12$AW6gF9xj94bGSRKJNWnhver3KJ14oUSXj4dSSwDJC3uRa7i8xjEce', 4, 11),
('cDominguez', '$2a$12$I/UUdA3v1EzRSV1n3HyAR.QDrRftXjHmv4QuVGtNHty8U81ZCinoC', 4, 12),
('jMartinez', '$2a$12$LJm.yXUinoa0b.6qqo5CiOQ0c7d/h/HF15d5AUhV9bvm8sP2KbEem', 4, 4),
('aLopez', '$2a$12$0VnZPtz4twV7gqngVevfaeT4k96eRKG0V9leuDI.HYikHnAZy38l.', 4, 5),
('saraMedico', '$2a$12$mXWflCWbKH3k0EKOheyMGeiuj1690yGFc4ZWoHP0Re392CO.WHRC2', 7, 73);

INSERT INTO Tribune (nombre)
VALUES
('A'),
('B'),
('C'),
('D');

INSERT INTO Section (nombre)
VALUES
('A'),
('B'),
('C'),
('D'),
('E'),
('F');

-- --------------------------------------------------
-- PRODUCTOS Y USUARIOS
-- --------------------------------------------------

INSERT INTO Producto (nombre, tipo, precio, imagen, stock, precio_mayorista)
VALUES
('Camiseta','Indumentaria',25,'/images/oviedo.png', 50, 15),
('Gorro','Accesorios',10,'/images/gorro_oviedo.png', 30, 5),
('Bufanda','Accesorios',13,'/images/bufanda_oviedo.png', 20, 7);

INSERT INTO Campana(fase, estado, n_accionesDisponibles, n_accionesVendidas, fecha_creacion)
VALUES
(3, 'Inactiva', 18, 14, datetime('2025-11-01')),
(1, 'Activa', 30, 23, datetime('2025-11-06'));


-- Contraseñas: juan_perez y martin_gonzales¡
INSERT INTO Accionista (nombre, apellido, dni, fecha_nacimiento, username, password) 
VALUES 
('Accionista', 'sin acciones', '12345678A', date('1978-11-06'), 'jPerez', '$2a$12$u20Uvfh0VXpewbcRNV4zxe4Qc1fjEvrnfdBfmA2DBi8kC7d41gOuS'),
('Accionista', 'con acciones', '12345678B', date('1978-05-06'), 'mGonzales', '$2a$12$tjGHYD0T10gIYLOSduCG1e./9kXVZ8c5Hs8Q4cszHj9HJJhX5oG1K');

INSERT INTO Accion(id_accionista, fecha_creacion, id_campana, en_venta)
VALUES 
(2, datetime('2025-11-02'), 1, 1),
(2, datetime('2025-11-03'), 1, 0);


-- Valores para probar la venta de entradas
INSERT INTO Purchase (id_user, fecha, precio, tipo)
VALUES
(2, '2025-11-20', 300, 'Entradas'),
(2, '2025-11-20', 300, 'Entradas'),
(2, '2025-11-20', 300, 'Entradas'),
(2, '2025-11-20', 300, 'Entradas'),
(2, '2025-11-28', 300, 'Entradas'),
(2, '2025-11-20', 300, 'Entradas'),
(2, '2025-11-26', 300, 'Entradas'),
(2, '2025-11-22', 300, 'Entradas'),
(2, '2025-11-20', 300, 'Entradas'),
(2, '2025-11-20', 300, 'Entradas');

INSERT INTO AsientoReservado (id_partido, id_tribune, id_section, fila, cantidad_asientos, asiento_inicial, id_purchase)
VALUES
(3, 1, 1, 1, 10, 1, 1),
(3, 1, 1, 2, 10, 1, 2),
(3, 1, 1, 3, 10, 1, 3),
(3, 1, 1, 4, 10, 1, 4),
(3, 1, 1, 5, 10, 1, 5),
(3, 1, 1, 6, 10, 1, 6),
(3, 1, 1, 7, 10, 1, 7),
(3, 1, 1, 8, 10, 1, 8),
(3, 1, 1, 9, 10, 1, 9),
(3, 1, 1, 10, 10, 1, 10); 

INSERT INTO LaborJardineria (id_empleado, fecha, hora_inicio, hora_fin, id_instalacion)
VALUES (3, '2025-12-01', '11:00', '14:00', 1);

INSERT INTO noticias (titulo, contenido, imagen, publicada, fecha_publicacion)
VALUES
('Victoria importante en casa',
 'Nuestro equipo se impuso por 3-1 en el partido de liga celebrado ayer en nuestro estadio. 
 Los goles fueron obra de nuestros delanteros tras una primera parte muy igualada.',
 'c.png',
 1,
 DATE('now')
),
('Mejoras en las instalaciones deportivas',
 'El club ha anunciado mejoras en los vestuarios y la zona de calentamiento para incrementar la comodidad de los jugadores.',
 'b.png',
 1,
 DATE('now')
),
('Nuevo fichaje para reforzar la delantera',
 'El club ha cerrado la incorporación de un nuevo delantero centro para reforzar el ataque esta temporada.',
 NULL,
 0,
 DATE('now')
);


/*************************************************************************************************************************************/
/*  VALORES DE LESIONES **************************************************************************************************************/
/*************************************************************************************************************************************/
INSERT INTO Lesion (id_jugador, id_partido, prioridad, diagnostico)
VALUES 
(57, 7, 'Alta', 'Rotura de tibia'),
(53, 4, 'Media', 'Esguince de muñeca'),
(26, 5, 'Media', 'Esguince de tobillo'),
(18, 2, 'Alta', 'Rotura de tobillo');

INSERT INTO SesionLesion (id_lesion, accion, prioridad, diagnostico, alta_medica)
VALUES
(1, 'CREAR', 'Alta', 'Rotura de tibia', 0),
(2, 'CREAR', 'Media', 'Esguince de muñeca', 0),
(3, 'CREAR', 'Media', 'Esguince de tobillo', 0),
(4, 'CREAR', 'Alta', 'Rotura de tobillo', 0);

-- Abonos de diciembre 2025
INSERT INTO Abono (dni_cliente, id_tribune, id_section, fila, id_asiento, fecha_inicio, fecha_fin, precio_total)
VALUES
('12345678D', 1, 1, 1, 6, '2025-12-01', '2026-05-31', 200.0),
('12345678E', 1, 2, 2, 7, '2025-12-02', '2026-05-31', 180.0),
('12345678F', 2, 3, 3, 8, '2025-12-03', '2026-05-31', 220.0),
('12345678G', 2, 4, 4, 9, '2025-12-04', '2026-05-31', 250.0),
('12345678H', 3, 5, 5, 10, '2025-12-05', '2026-05-31', 210.0),
('12345678I', 1, 1, 1, 11, '2025-12-06', '2026-05-31', 200.0),
('12345678J', 1, 2, 2, 12, '2025-12-07', '2026-05-31', 180.0),
('12345678K', 2, 3, 3, 13, '2025-12-08', '2026-05-31', 220.0),
('12345678L', 2, 4, 4, 14, '2025-12-09', '2026-05-31', 250.0),
('12345678M', 3, 5, 5, 15, '2025-12-10', '2026-05-31', 210.0),
('12345678N', 1, 1, 1, 16, '2025-12-11', '2026-05-31', 200.0),
('12345678O', 1, 2, 2, 17, '2025-12-12', '2026-05-31', 180.0),
('12345678P', 2, 3, 3, 18, '2025-12-13', '2026-05-31', 220.0),
('12345678Q', 2, 4, 4, 19, '2025-12-14', '2026-05-31', 250.0),
('12345678R', 3, 5, 5, 20, '2025-12-15', '2026-05-31', 210.0),
('12345678S', 1, 1, 1, 21, '2025-12-16', '2026-05-31', 200.0);




-- Pagos de suplemento
INSERT INTO Suplemento (idAbono, idPartido, precio)
VALUES 
(1, 1, 20.0),
(2, 2, 22.0),
(3, 3, 25.0),
(4, 4, 18.0),
(5, 5, 30.0),
(6, 6, 28.0),
(7, 7, 26.0),
(8, 8, 24.0),
(9, 9, 20.0),
(10, 10, 32.0);


-- LOG
INSERT INTO User_session (user_id, fecha_inicio)
VALUES
(1, '2025-11-26 18:08:13.633'),
(1, '2025-11-27 15:40:17.552'),
(2, '2025-11-27 15:43:55.558'),
(2, '2025-11-25 15:43:55.558'),
(3, '2025-11-24 15:43:55.558'),
(4, '2025-11-23 15:43:55.558'),
(5, '2025-11-27 15:55:47.584'),
(7, '2025-11-27 15:57:57.043');

INSERT INTO User_log (session_id, accion, detalles, fecha_hora)
VALUES
-- Sesiones de lPerez
(1, 'LOGIN', 'Inicio de sesión usuarioId=1', '2025-11-26 18:08:13.633'),
(1, 'BOTON_CLICK', 'Gestionar Empleados', '2025-11-26 18:08:17.434'),
(1, 'BOTON_CLICK', 'Acceso menú consultar empleados', '2025-11-26 18:08:26.914'),
(1, 'LOGOUT', 'Cierre de sesión', '2025-11-26 18:10:26.914'),

(2, 'LOGIN', 'Inicio de sesión usuarioId=1', '2025-11-27 15:40:17.552'),
(2, 'BOTON_CLICK', 'Gestionar Empleados', '2025-11-27 15:40:35.125'),
(2, 'BOTON_CLICK', 'Acceso menú consultar empleados', '2025-11-27 15:40:36.905'),
(2, 'BOTON_CLICK', 'Guardar modificaciones', '2025-11-27 15:40:50.369'),
(2, 'BOTON_CLICK', 'Empleado modificado con id=44', '2025-11-27 15:40:51.751'),
(2, 'LOGOUT', 'Cierre de sesión', '2025-11-27 15:40:57.119'),

-- Sesiones de mSuarez

(3, 'LOGIN', 'Inicio de sesión usuarioId=2', '2025-11-27 15:43:55.558'),
(3, 'BOTON_CLICK', 'Acceso a Tienda', '2025-11-27 15:43:58.463'),
(3, 'BOTON_CLICK', 'Acceso a tienda de productos', '2025-11-27 15:44:00.181'),
(3, 'BOTON_CLICK', 'Añadir producto al carrito: idProducto=1', '2025-11-27 15:44:01.476'),
(3, 'BOTON_CLICK', 'Añadir producto al carrito: idProducto=3', '2025-11-27 15:44:03.393'),
(3, 'BOTON_CLICK', 'Finalizar compra', '2025-11-27 15:44:05.428'),
(3, 'COMPRA_PRODUCTO', 'idProducto=1, cantidad=1, usuarioId=2', '2025-11-27 15:44:05.437'),
(3, 'COMPRA_PRODUCTO', 'idProducto=3, cantidad=3, usuarioId=2', '2025-11-27 15:44:05.451'),
(3, 'LOGOUT', 'Cierre de sesión', '2025-11-27 15:44:19.322'),


(4, 'LOGIN', 'Inicio de sesión usuarioId=2', '2025-11-25 15:43:55.558'),
(4, 'BOTON_CLICK', 'Menu Abonos', '2025-11-25 15:43:58.463'),
(4, 'BOTON_CLICK', 'Pagar suplemento partido', '2025-11-25 15:44:00.181'),
(4, 'BOTON_CLICK', 'Pago de suplemento realizado para partido con id=23', '2025-11-25 15:44:01.476'),
(4, 'BOTON_CLICK', 'Se ha abierto la factura de compra', '2025-11-25 15:44:03.393'),
(4, 'LOGOUT', 'Cierre de sesión', '2025-11-25 15:44:19.322'),

-- iCano
(5, 'LOGIN', 'Inicio de sesión usuarioId=3', '2025-11-24 15:43:55.558'),
(5, 'LOGOUT', 'Cierre de sesión', '2025-11-24 15:44:19.322'),

-- cDominguez
(6, 'LOGIN', 'Inicio de sesión usuarioId=4', '2025-11-23 15:43:55.558'),
(6, 'BOTON_CLICK', 'Gestionar Franjas', '2025-11-23 15:43:58.463'),
(6, 'BOTON_CLICK', 'Gestionar franjas', '2025-11-23 15:44:00.181'),
(6, 'LOGOUT', 'Cierre de sesión', '2025-11-23 15:44:19.322'),


-- jMartinez
(7, 'LOGIN', 'Inicio de sesión usuarioId=5', '2025-11-27 15:55:47.584'),
(7, 'LOGOUT', 'Cierre de sesión', '2025-11-27 15:56:39.824'),

-- saraMedico
(8, 'LOGIN', 'Inicio de sesión usuarioId=7', '2025-11-27 15:57:57.043'),
(8, 'BOTON_CLICK', 'Portal Médico', '2025-11-27 15:57:58.14'),
(8, 'BOTON_CLICK', 'Acceso lesión con id=1', '2025-11-27 15:58:00.043'),
(8, 'BOTON_CLICK', 'Dada de alta lesión con id=1', '2025-11-27 15:58:02.427'),
(8, 'LOGOUT', 'Cierre de sesión', '2025-11-27 15:58:06.938');


-- INGRESOS AÑOS 2020-2027
INSERT INTO Ingresos (tipo, concepto, fecha_hora, total)
VALUES
('ABONO_TEMPORADA', 'Abono mensual 2020-01', '2020-01-15 09:00:00', 500.00),
('CAMPANA_ACCIONISTAS', 'Campaña apoyo 2020-01', '2020-01-10 09:00:00', 600.00),
('ENTRADA_PARTIDO', 'Partido 2020-01', '2020-01-20 09:00:00', 250.00),
('MERCHANDISING', 'Producto merchandising 2020-01', '2020-01-05 09:00:00', 200.00),
('ACCIONISTA', 'Cuota accionista 2020-01', '2020-01-25 09:00:00', 1200.00),
('RESERVA_EXTERNA', 'Reserva empresa 2020-01', '2020-01-18 09:00:00', 800.00),
('TIENDA', 'Compra tienda 2020-01', '2020-01-12 09:00:00', 300.00),
('SUPLEMENTO', 'Suplemento 2020-01', '2020-01-22 09:00:00', 100.00),
('ABONO_TEMPORADA', 'Abono mensual 2020-02', '2020-02-15 09:00:00', 520.00),
('CAMPANA_ACCIONISTAS', 'Campaña apoyo 2020-02', '2020-02-10 09:00:00', 620.00),
('ENTRADA_PARTIDO', 'Partido 2020-02', '2020-02-20 09:00:00', 270.00),
('MERCHANDISING', 'Producto merchandising 2020-02', '2020-02-05 09:00:00', 210.00),
('ACCIONISTA', 'Cuota accionista 2020-02', '2020-02-25 09:00:00', 1250.00),
('ABONO_TEMPORADA', 'Abono mensual 2020-03', '2020-03-15 09:00:00', 540.00),
('CAMPANA_ACCIONISTAS', 'Campaña apoyo 2020-03', '2020-03-10 09:00:00', 640.00),
('ENTRADA_PARTIDO', 'Partido 2020-03', '2020-03-20 09:00:00', 280.00),
('MERCHANDISING', 'Producto merchandising 2020-03', '2020-03-05 09:00:00', 220.00),
('ACCIONISTA', 'Cuota accionista 2020-03', '2020-03-25 09:00:00', 1300.00),
('RESERVA_EXTERNA', 'Reserva empresa 2020-03', '2020-03-18 09:00:00', 850.00),
('TIENDA', 'Compra tienda 2020-03', '2020-03-12 09:00:00', 320.00),
('SUPLEMENTO', 'Suplemento 2020-03', '2020-03-22 09:00:00', 120.00),
('ABONO_TEMPORADA', 'Abono mensual 2020-04', '2020-04-15 09:00:00', 560.00),
('CAMPANA_ACCIONISTAS', 'Campaña apoyo 2020-04', '2020-04-10 09:00:00', 660.00),
('ENTRADA_PARTIDO', 'Partido 2020-04', '2020-04-20 09:00:00', 300.00),
('MERCHANDISING', 'Producto merchandising 2020-04', '2020-04-05 09:00:00', 230.00),
('ACCIONISTA', 'Cuota accionista 2020-04', '2020-04-25 09:00:00', 1350.00),
('RESERVA_EXTERNA', 'Reserva empresa 2020-04', '2020-04-18 09:00:00', 880.00),
('TIENDA', 'Compra tienda 2020-04', '2020-04-12 09:00:00', 330.00),
('SUPLEMENTO', 'Suplemento 2020-04', '2020-04-22 09:00:00', 130.00),
('ABONO_TEMPORADA', 'Abono mensual 2020-05', '2020-05-15 09:00:00', 580.00),
('CAMPANA_ACCIONISTAS', 'Campaña apoyo 2020-05', '2020-05-10 09:00:00', 680.00),
('ENTRADA_PARTIDO', 'Partido 2020-05', '2020-05-20 09:00:00', 320.00),
('MERCHANDISING', 'Producto merchandising 2020-05', '2020-05-05 09:00:00', 240.00),
('ACCIONISTA', 'Cuota accionista 2020-05', '2020-05-25 09:00:00', 1400.00),
('ABONO_TEMPORADA', 'Abono mensual 2020-06', '2020-06-15 09:00:00', 600.00),
('CAMPANA_ACCIONISTAS', 'Campaña apoyo 2020-06', '2020-06-10 09:00:00', 700.00),
('ENTRADA_PARTIDO', 'Partido 2020-06', '2020-06-20 09:00:00', 340.00),
('MERCHANDISING', 'Producto merchandising 2020-06', '2020-06-05 09:00:00', 250.00),
('ABONO_TEMPORADA', 'Abono mensual 2020-07', '2020-07-15 09:00:00', 620.00),
('CAMPANA_ACCIONISTAS', 'Campaña apoyo 2020-07', '2020-07-10 09:00:00', 720.00),
('ENTRADA_PARTIDO', 'Partido 2020-07', '2020-07-20 09:00:00', 360.00),
('MERCHANDISING', 'Producto merchandising 2020-07', '2020-07-05 09:00:00', 260.00),
('ACCIONISTA', 'Cuota accionista 2020-07', '2020-07-25 09:00:00', 1500.00),
('RESERVA_EXTERNA', 'Reserva empresa 2020-07', '2020-07-18 09:00:00', 950.00),
('TIENDA', 'Compra tienda 2020-07', '2020-07-12 09:00:00', 360.00),
('SUPLEMENTO', 'Suplemento 2020-07', '2020-07-22 09:00:00', 160.00),
('ABONO_TEMPORADA', 'Abono mensual 2020-08', '2020-08-15 09:00:00', 640.00),
('CAMPANA_ACCIONISTAS', 'Campaña apoyo 2020-08', '2020-08-10 09:00:00', 740.00),
('ENTRADA_PARTIDO', 'Partido 2020-08', '2020-08-20 09:00:00', 380.00),
('MERCHANDISING', 'Producto merchandising 2020-08', '2020-08-05 09:00:00', 270.00),
('ACCIONISTA', 'Cuota accionista 2020-08', '2020-08-25 09:00:00', 1550.00),
('ABONO_TEMPORADA', 'Abono mensual 2020-09', '2020-09-15 09:00:00', 660.00),
('CAMPANA_ACCIONISTAS', 'Campaña apoyo 2020-09', '2020-09-10 09:00:00', 760.00),
('ENTRADA_PARTIDO', 'Partido 2020-09', '2020-09-20 09:00:00', 400.00),
('MERCHANDISING', 'Producto merchandising 2020-09', '2020-09-05 09:00:00', 280.00),
('ACCIONISTA', 'Cuota accionista 2020-09', '2020-09-25 09:00:00', 1600.00),
('RESERVA_EXTERNA', 'Reserva empresa 2020-09', '2020-09-18 09:00:00', 1000.00),
('TIENDA', 'Compra tienda 2020-09', '2020-09-12 09:00:00', 380.00),
('SUPLEMENTO', 'Suplemento 2020-09', '2020-09-22 09:00:00', 180.00),
('ABONO_TEMPORADA', 'Abono mensual 2020-10', '2020-10-15 09:00:00', 680.00),
('CAMPANA_ACCIONISTAS', 'Campaña apoyo 2020-10', '2020-10-10 09:00:00', 780.00),
('ENTRADA_PARTIDO', 'Partido 2020-10', '2020-10-20 09:00:00', 420.00),
('MERCHANDISING', 'Producto merchandising 2020-10', '2020-10-05 09:00:00', 290.00),
('ABONO_TEMPORADA', 'Abono mensual 2020-11', '2020-11-15 09:00:00', 700.00),
('CAMPANA_ACCIONISTAS', 'Campaña apoyo 2020-11', '2020-11-10 09:00:00', 800.00),
('ENTRADA_PARTIDO', 'Partido 2020-11', '2020-11-20 09:00:00', 440.00),
('MERCHANDISING', 'Producto merchandising 2020-11', '2020-11-05 09:00:00', 300.00),
('ACCIONISTA', 'Cuota accionista 2020-11', '2020-11-25 09:00:00', 1700.00),
('RESERVA_EXTERNA', 'Reserva empresa 2020-11', '2020-11-18 09:00:00', 1050.00),
('TIENDA', 'Compra tienda 2020-11', '2020-11-12 09:00:00', 400.00),
('SUPLEMENTO', 'Suplemento 2020-11', '2020-11-22 09:00:00', 200.00),
('ABONO_TEMPORADA', 'Abono mensual 2020-12', '2020-12-15 09:00:00', 720.00),
('CAMPANA_ACCIONISTAS', 'Campaña apoyo 2020-12', '2020-12-10 09:00:00', 820.00),
('ENTRADA_PARTIDO', 'Partido 2020-12', '2020-12-20 09:00:00', 460.00),
('MERCHANDISING', 'Producto merchandising 2020-12', '2020-12-05 09:00:00', 310.00),
('ACCIONISTA', 'Cuota accionista 2020-12', '2020-12-25 09:00:00', 1750.00),
('ABONO_TEMPORADA', 'Abono mensual 2021-01', '2021-01-15 09:00:00', 750.00),
('CAMPANA_ACCIONISTAS', 'Campaña apoyo 2021-01', '2021-01-10 09:00:00', 820.00),
('ENTRADA_PARTIDO', 'Partido 2021-01', '2021-01-20 09:00:00', 500.00),
('MERCHANDISING', 'Producto merchandising 2021-01', '2021-01-05 09:00:00', 350.00),
('ABONO_TEMPORADA', 'Abono mensual 2021-02', '2021-02-15 09:00:00', 770.00),
('CAMPANA_ACCIONISTAS', 'Campaña apoyo 2021-02', '2021-02-10 09:00:00', 840.00),
('ENTRADA_PARTIDO', 'Partido 2021-02', '2021-02-20 09:00:00', 520.00),
('MERCHANDISING', 'Producto merchandising 2021-02', '2021-02-05 09:00:00', 360.00),
('ACCIONISTA', 'Cuota accionista 2021-02', '2021-02-25 09:00:00', 1850.00),
('RESERVA_EXTERNA', 'Reserva empresa 2021-02', '2021-02-18 09:00:00', 1220.00),
('TIENDA', 'Compra tienda 2021-02', '2021-02-12 09:00:00', 560.00),
('SUPLEMENTO', 'Suplemento 2021-02', '2021-02-22 09:00:00', 260.00),
('ABONO_TEMPORADA', 'Abono mensual 2021-03', '2021-03-15 09:00:00', 790.00),
('CAMPANA_ACCIONISTAS', 'Campaña apoyo 2021-03', '2021-03-10 09:00:00', 860.00),
('ENTRADA_PARTIDO', 'Partido 2021-03', '2021-03-20 09:00:00', 540.00),
('MERCHANDISING', 'Producto merchandising 2021-03', '2021-03-05 09:00:00', 370.00),
('ACCIONISTA', 'Cuota accionista 2021-03', '2021-03-25 09:00:00', 1900.00),
('RESERVA_EXTERNA', 'Reserva empresa 2021-03', '2021-03-18 09:00:00', 1240.00),
('TIENDA', 'Compra tienda 2021-03', '2021-03-12 09:00:00', 570.00),
('SUPLEMENTO', 'Suplemento 2021-03', '2021-03-22 09:00:00', 270.00),
('ABONO_TEMPORADA', 'Abono mensual 2021-04', '2021-04-15 09:00:00', 810.00),
('CAMPANA_ACCIONISTAS', 'Campaña apoyo 2021-04', '2021-04-10 09:00:00', 880.00),
('ENTRADA_PARTIDO', 'Partido 2021-04', '2021-04-20 09:00:00', 560.00),
('MERCHANDISING', 'Producto merchandising 2021-04', '2021-04-05 09:00:00', 380.00),
('ACCIONISTA', 'Cuota accionista 2021-04', '2021-04-25 09:00:00', 1950.00),
('RESERVA_EXTERNA', 'Reserva empresa 2021-04', '2021-04-18 09:00:00', 1260.00),
('TIENDA', 'Compra tienda 2021-04', '2021-04-12 09:00:00', 580.00),
('SUPLEMENTO', 'Suplemento 2021-04', '2021-04-22 09:00:00', 280.00),
('ABONO_TEMPORADA', 'Abono mensual 2021-05', '2021-05-15 09:00:00', 830.00),
('CAMPANA_ACCIONISTAS', 'Campaña apoyo 2021-05', '2021-05-10 09:00:00', 900.00),
('ENTRADA_PARTIDO', 'Partido 2021-05', '2021-05-20 09:00:00', 580.00),
('MERCHANDISING', 'Producto merchandising 2021-05', '2021-05-05 09:00:00', 390.00),
('ACCIONISTA', 'Cuota accionista 2021-05', '2021-05-25 09:00:00', 2000.00),
('RESERVA_EXTERNA', 'Reserva empresa 2021-05', '2021-05-18 09:00:00', 1280.00),
('TIENDA', 'Compra tienda 2021-05', '2021-05-12 09:00:00', 590.00),
('SUPLEMENTO', 'Suplemento 2021-05', '2021-05-22 09:00:00', 290.00),
('ABONO_TEMPORADA', 'Abono mensual 2021-06', '2021-06-15 09:00:00', 850.00),
('CAMPANA_ACCIONISTAS', 'Campaña apoyo 2021-06', '2021-06-10 09:00:00', 920.00),
('ENTRADA_PARTIDO', 'Partido 2021-06', '2021-06-20 09:00:00', 600.00),
('MERCHANDISING', 'Producto merchandising 2021-06', '2021-06-05 09:00:00', 400.00),
('ACCIONISTA', 'Cuota accionista 2021-06', '2021-06-25 09:00:00', 2050.00),
('RESERVA_EXTERNA', 'Reserva empresa 2021-06', '2021-06-18 09:00:00', 1300.00),
('TIENDA', 'Compra tienda 2021-06', '2021-06-12 09:00:00', 600.00),
('SUPLEMENTO', 'Suplemento 2021-06', '2021-06-22 09:00:00', 300.00),
('ABONO_TEMPORADA', 'Abono mensual 2021-07', '2021-07-15 09:00:00', 870.00),
('CAMPANA_ACCIONISTAS', 'Campaña apoyo 2021-07', '2021-07-10 09:00:00', 940.00),
('ENTRADA_PARTIDO', 'Partido 2021-07', '2021-07-20 09:00:00', 620.00),
('MERCHANDISING', 'Producto merchandising 2021-07', '2021-07-05 09:00:00', 410.00),
('ACCIONISTA', 'Cuota accionista 2021-07', '2021-07-25 09:00:00', 2100.00),
('RESERVA_EXTERNA', 'Reserva empresa 2021-07', '2021-07-18 09:00:00', 1320.00),
('TIENDA', 'Compra tienda 2021-07', '2021-07-12 09:00:00', 610.00),
('SUPLEMENTO', 'Suplemento 2021-07', '2021-07-22 09:00:00', 310.00),
('ABONO_TEMPORADA', 'Abono mensual 2021-08', '2021-08-15 09:00:00', 890.00),
('CAMPANA_ACCIONISTAS', 'Campaña apoyo 2021-08', '2021-08-10 09:00:00', 960.00),
('ENTRADA_PARTIDO', 'Partido 2021-08', '2021-08-20 09:00:00', 640.00),
('MERCHANDISING', 'Producto merchandising 2021-08', '2021-08-05 09:00:00', 420.00),
('ACCIONISTA', 'Cuota accionista 2021-08', '2021-08-25 09:00:00', 2150.00),
('RESERVA_EXTERNA', 'Reserva empresa 2021-08', '2021-08-18 09:00:00', 1340.00),
('TIENDA', 'Compra tienda 2021-08', '2021-08-12 09:00:00', 620.00),
('SUPLEMENTO', 'Suplemento 2021-08', '2021-08-22 09:00:00', 320.00),
('ABONO_TEMPORADA', 'Abono mensual 2021-09', '2021-09-15 09:00:00', 910.00),
('CAMPANA_ACCIONISTAS', 'Campaña apoyo 2021-09', '2021-09-10 09:00:00', 980.00),
('ENTRADA_PARTIDO', 'Partido 2021-09', '2021-09-20 09:00:00', 660.00),
('MERCHANDISING', 'Producto merchandising 2021-09', '2021-09-05 09:00:00', 430.00),
('ACCIONISTA', 'Cuota accionista 2021-09', '2021-09-25 09:00:00', 2200.00),
('RESERVA_EXTERNA', 'Reserva empresa 2021-09', '2021-09-18 09:00:00', 1360.00),
('TIENDA', 'Compra tienda 2021-09', '2021-09-12 09:00:00', 630.00),
('SUPLEMENTO', 'Suplemento 2021-09', '2021-09-22 09:00:00', 330.00),
('ABONO_TEMPORADA', 'Abono mensual 2021-10', '2021-10-15 09:00:00', 93000.00),
('CAMPANA_ACCIONISTAS', 'Campaña apoyo 2021-10', '2021-10-10 09:00:00', 1000.00),
('ENTRADA_PARTIDO', 'Partido 2021-10', '2021-10-20 09:00:00', 680.00),
('MERCHANDISING', 'Producto merchandising 2021-10', '2021-10-05 09:00:00', 440.00),
('ACCIONISTA', 'Cuota accionista 2021-10', '2021-10-25 09:00:00', 2250.00),
('RESERVA_EXTERNA', 'Reserva empresa 2021-10', '2021-10-18 09:00:00', 1380.00),
('TIENDA', 'Compra tienda 2021-10', '2021-10-12 09:00:00', 640.00),
('SUPLEMENTO', 'Suplemento 2021-10', '2021-10-22 09:00:00', 340.00),
('ABONO_TEMPORADA', 'Abono mensual 2021-11', '2021-11-15 09:00:00', 950.00),
('CAMPANA_ACCIONISTAS', 'Campaña apoyo 2021-11', '2021-11-10 09:00:00', 1020.00),
('ENTRADA_PARTIDO', 'Partido 2021-11', '2021-11-20 09:00:00', 700.00),
('MERCHANDISING', 'Producto merchandising 2021-11', '2021-11-05 09:00:00', 450.00),
('ACCIONISTA', 'Cuota accionista 2021-11', '2021-11-25 09:00:00', 2300.00),
('RESERVA_EXTERNA', 'Reserva empresa 2021-11', '2021-11-18 09:00:00', 1400.00),
('TIENDA', 'Compra tienda 2021-11', '2021-11-12 09:00:00', 650.00),
('SUPLEMENTO', 'Suplemento 2021-11', '2021-11-22 09:00:00', 350.00),
('ABONO_TEMPORADA', 'Abono mensual 2021-12', '2021-12-15 09:00:00', 9700.00),
('CAMPANA_ACCIONISTAS', 'Campaña apoyo 2021-12', '2021-12-10 09:00:00', 1040.00),
('ENTRADA_PARTIDO', 'Partido 2021-12', '2021-12-20 09:00:00', 720.00),
('MERCHANDISING', 'Producto merchandising 2021-12', '2021-12-05 09:00:00', 460.00),
('ACCIONISTA', 'Cuota accionista 2021-12', '2021-12-25 09:00:00', 2350.00),
('RESERVA_EXTERNA', 'Reserva empresa 2021-12', '2021-12-18 09:00:00', 1420.00),
('TIENDA', 'Compra tienda 2021-12', '2021-12-12 09:00:00', 660.00),
('SUPLEMENTO', 'Suplemento 2021-12', '2021-12-22 09:00:00', 360.00),
('ABONO_TEMPORADA', 'Abono mensual 2022-01', '2022-01-15 09:00:00', 800.00),
('CAMPANA_ACCIONISTAS', 'Campaña apoyo 2022-01', '2022-01-10 09:00:00', 870.00),
('ENTRADA_PARTIDO', 'Partido 2022-01', '2022-01-20 09:00:00', 550.00),
('ABONO_TEMPORADA', 'Abono mensual 2022-02', '2022-02-15 09:00:00', 820.00),
('CAMPANA_ACCIONISTAS', 'Campaña apoyo 2022-02', '2022-02-10 09:00:00', 890.00),
('ENTRADA_PARTIDO', 'Partido 2022-02', '2022-02-20 09:00:00', 570.00),
('MERCHANDISING', 'Producto merchandising 2022-02', '2022-02-05 09:00:00', 390.00),
('ACCIONISTA', 'Cuota accionista 2022-02', '2022-02-25 09:00:00', 1950.00),
('RESERVA_EXTERNA', 'Reserva empresa 2022-02', '2022-02-18 09:00:00', 1320.00),
('TIENDA', 'Compra tienda 2022-02', '2022-02-12 09:00:00', 610.00),
('ABONO_TEMPORADA', 'Abono mensual 2022-03', '2022-03-15 09:00:00', 840.00),
('CAMPANA_ACCIONISTAS', 'Campaña apoyo 2022-03', '2022-03-10 09:00:00', 910.00),
('ABONO_TEMPORADA', 'Abono anual 2023', '2023-01-05 09:00:00', 580000.00),
('CAMPANA_ACCIONISTAS', 'Campaña apoyo 2023', '2023-03-20 09:00:00', 700.00),
('ENTRADA_PARTIDO', 'Partido Enero 2023', '2023-01-25 09:00:00', 300.00),
('MERCHANDISING', 'Gorra oficial 2023', '2023-02-10 09:00:00', 190.00),
('ACCIONISTA', 'Cuota accionista 2023', '2023-01-28 09:00:00', 1350.00),
('RESERVA_EXTERNA', 'Reserva empresa 2023', '2023-04-05 09:00:00', 950.00),
('ABONO_TEMPORADA', 'Abono anual 2024', '2024-02-01 09:00:00', 600.00),
('CAMPANA_ACCIONISTAS', 'Campaña apoyo 2024', '2024-04-10 09:00:00', 750.00),
('ENTRADA_PARTIDO', 'Partido Febrero 2024', '2024-02-15 09:00:00', 350.00),
('MERCHANDISING', 'Camiseta edición 2024', '2024-03-05 09:00:00', 220.00),
('ACCIONISTA', 'Cuota accionista 2024', '2024-01-30 09:00:00', 1400.00),
('RESERVA_EXTERNA', 'Reserva empresa 2024', '2024-04-20 09:00:00', 980.00),
('TIENDA', 'Compra en tienda 2024', '2024-05-25 09:00:00', 380.00),
('SUPLEMENTO', 'Suplemento especial 2024', '2024-06-30 09:00:00', 150.00),
('ABONO_TEMPORADA', 'Abono mensual 2025-01', '2025-01-15 09:00:00', 650.00),
('CAMPANA_ACCIONISTAS', 'Campaña apoyo 2025-01', '2025-01-10 09:00:00', 720.00),
('ENTRADA_PARTIDO', 'Partido 2025-01', '2025-01-20 09:00:00', 420.00),
('MERCHANDISING', 'Producto merchandising 2025-01', '2025-01-05 09:00:00', 230.00),
('ACCIONISTA', 'Cuota accionista 2025-01', '2025-01-25 09:00:00', 1500.00),
('RESERVA_EXTERNA', 'Reserva empresa 2025-01', '2025-01-18 09:00:00', 1020.00),
('TIENDA', 'Compra tienda 2025-01', '2025-01-12 09:00:00', 410.00),
('SUPLEMENTO', 'Suplemento 2025-01', '2025-01-22 09:00:00', 170.00),
('ABONO_TEMPORADA', 'Abono mensual 2025-02', '2025-02-15 09:00:00', 670.00),
('CAMPANA_ACCIONISTAS', 'Campaña apoyo 2025-02', '2025-02-10 09:00:00', 740.00),
('ENTRADA_PARTIDO', 'Partido 2025-02', '2025-02-20 09:00:00', 440.00),
('MERCHANDISING', 'Producto merchandising 2025-02', '2025-02-05 09:00:00', 240.00),
('ACCIONISTA', 'Cuota accionista 2025-02', '2025-02-25 09:00:00', 1550.00),
('RESERVA_EXTERNA', 'Reserva empresa 2025-02', '2025-02-18 09:00:00', 1040.00),
('TIENDA', 'Compra tienda 2025-02', '2025-02-12 09:00:00', 420.00),
('SUPLEMENTO', 'Suplemento 2025-02', '2025-02-22 09:00:00', 180.00),
('ABONO_TEMPORADA', 'Abono mensual 2025-03', '2025-03-15 09:00:00', 690.00),
('CAMPANA_ACCIONISTAS', 'Campaña apoyo 2025-03', '2025-03-10 09:00:00', 760.00),
('ENTRADA_PARTIDO', 'Partido 2025-03', '2025-03-20 09:00:00', 460.00),
('MERCHANDISING', 'Producto merchandising 2025-03', '2025-03-05 09:00:00', 250.00),
('ACCIONISTA', 'Cuota accionista 2025-03', '2025-03-25 09:00:00', 1600.00),
('RESERVA_EXTERNA', 'Reserva empresa 2025-03', '2025-03-18 09:00:00', 1060.00),
('TIENDA', 'Compra tienda 2025-03', '2025-03-12 09:00:00', 430.00),
('SUPLEMENTO', 'Suplemento 2025-03', '2025-03-22 09:00:00', 190.00),
('ABONO_TEMPORADA', 'Abono mensual 2025-04', '2025-04-15 09:00:00', 710.00),
('CAMPANA_ACCIONISTAS', 'Campaña apoyo 2025-04', '2025-04-10 09:00:00', 780.00),
('ENTRADA_PARTIDO', 'Partido 2025-04', '2025-04-20 09:00:00', 40.00),
('ABONO_TEMPORADA', 'Abono mensual 2025-05', '2025-05-15 09:00:00', 730.00),
('CAMPANA_ACCIONISTAS', 'Campaña apoyo 2025-05', '2025-05-10 09:00:00', 800.00),
('ENTRADA_PARTIDO', 'Partido 2025-05', '2025-05-20 09:00:00', 500.00),
('MERCHANDISING', 'Producto merchandising 2025-05', '2025-05-05 09:00:00', 270.00),
('ACCIONISTA', 'Cuota accionista 2025-05', '2025-05-25 09:00:00', 1700.00),
('ABONO_TEMPORADA', 'Abono mensual 2025-06', '2025-06-15 09:00:00', 750.00),
('CAMPANA_ACCIONISTAS', 'Campaña apoyo 2025-06', '2025-06-10 09:00:00', 820.00),
('ENTRADA_PARTIDO', 'Partido 2025-06', '2025-06-20 09:00:00', 520.00),
('MERCHANDISING', 'Producto merchandising 2025-06', '2025-06-05 09:00:00', 280.00),
('ACCIONISTA', 'Cuota accionista 2025-06', '2025-06-25 09:00:00', 1750000.00),
('RESERVA_EXTERNA', 'Reserva empresa 2025-06', '2025-06-18 09:00:00', 1120.00),
('TIENDA', 'Compra tienda 2025-06', '2025-06-12 09:00:00', 460.00),
('SUPLEMENTO', 'Suplemento 2025-06', '2025-06-22 09:00:00', 220.00),
('ABONO_TEMPORADA', 'Abono mensual 2025-07', '2025-07-15 09:00:00', 770.00),
('CAMPANA_ACCIONISTAS', 'Campaña apoyo 2025-07', '2025-07-10 09:00:00', 840.00),
('ENTRADA_PARTIDO', 'Partido 2025-07', '2025-07-20 09:00:00', 540.00),
('MERCHANDISING', 'Producto merchandising 2025-07', '2025-07-05 09:00:00', 290.00),
('ACCIONISTA', 'Cuota accionista 2025-07', '2025-07-25 09:00:00', 1800.00),
('RESERVA_EXTERNA', 'Reserva empresa 2025-07', '2025-07-18 09:00:00', 1140.00),
('TIENDA', 'Compra tienda 2025-07', '2025-07-12 09:00:00', 470.00),
('SUPLEMENTO', 'Suplemento 2025-07', '2025-07-22 09:00:00', 23000.00),
('ABONO_TEMPORADA', 'Abono mensual 2025-08', '2025-08-15 09:00:00', 790.00),
('CAMPANA_ACCIONISTAS', 'Campaña apoyo 2025-08', '2025-08-10 09:00:00', 860.00),
('ENTRADA_PARTIDO', 'Partido 2025-08', '2025-08-20 09:00:00', 560.00),
('ABONO_TEMPORADA', 'Abono mensual 2025-09', '2025-09-15 09:00:00', 810.00),
('CAMPANA_ACCIONISTAS', 'Campaña apoyo 2025-09', '2025-09-10 09:00:00', 880.00),
('ENTRADA_PARTIDO', 'Partido 2025-09', '2025-09-20 09:00:00', 580.00),
('MERCHANDISING', 'Producto merchandising 2025-09', '2025-09-05 09:00:00', 310.00),
('ACCIONISTA', 'Cuota accionista 2025-09', '2025-09-25 09:00:00', 1900.00),
('RESERVA_EXTERNA', 'Reserva empresa 2025-09', '2025-09-18 09:00:00', 1180.00),
('TIENDA', 'Compra tienda 2025-09', '2025-09-12 09:00:00', 490.00),
('SUPLEMENTO', 'Suplemento 2025-09', '2025-09-22 09:00:00', 250.00),
('ABONO_TEMPORADA', 'Abono mensual 2025-10', '2025-10-15 09:00:00', 830.00),
('CAMPANA_ACCIONISTAS', 'Campaña apoyo 2025-10', '2025-10-10 09:00:00', 900.00),
('ENTRADA_PARTIDO', 'Partido 2025-10', '2025-10-20 09:00:00', 600.00),
('MERCHANDISING', 'Producto merchandising 2025-10', '2025-10-05 09:00:00', 320.00),
('ACCIONISTA', 'Cuota accionista 2025-10', '2025-10-25 09:00:00', 1950.00),
('RESERVA_EXTERNA', 'Reserva empresa 2025-10', '2025-10-18 09:00:00', 1200.00),
('TIENDA', 'Compra tienda 2025-10', '2025-10-12 09:00:00', 500.00),
('SUPLEMENTO', 'Suplemento 2025-10', '2025-10-22 09:00:00', 260.00),
('ABONO_TEMPORADA', 'Abono anual 2025', '2025-11-05 09:00:00', 600.00),
('CAMPANA_ACCIONISTAS', 'Campaña apoyo 2025', '2025-11-12 09:00:00', 700.00),
('ENTRADA_PARTIDO', 'Partido Noviembre 2025', '2025-11-20 09:00:00', 400.00),
('MERCHANDISING', 'Bufanda edición 2025', '2025-11-18 09:00:00', 220.00),
('ACCIONISTA', 'Cuota accionista 2025', '2025-11-10 09:00:00', 1450.00),
('RESERVA_EXTERNA', 'Reserva empresa 2025', '2025-11-15 09:00:00', 100.00),
('TIENDA', 'Compra en tienda 2025', '2025-11-22 09:00:00', 5000.00),
('SUPLEMENTO', 'Suplemento especial 2025', '2025-11-26 09:00:00', 1600.00),
('ABONO_TEMPORADA', 'Abono mensual 2025-12', '2025-12-15 09:00:00', 850.00),
('CAMPANA_ACCIONISTAS', 'Campaña apoyo 2025-12', '2025-12-10 09:00:00', 920.00),
('ENTRADA_PARTIDO', 'Partido 2025-12', '2025-12-20 09:00:00', 10000.00),
('MERCHANDISING', 'Producto merchandising 2025-12', '2025-12-05 09:00:00', 340.00),
('ACCIONISTA', 'Cuota accionista 2025-12', '2025-12-25 09:00:00', 20000.00),
('RESERVA_EXTERNA', 'Reserva empresa 2025-12', '2025-12-18 09:00:00', 1220.00),
('TIENDA', 'Compra tienda 2025-12', '2025-12-06 09:00:00', 520.00),
('TIENDA', 'Compra tienda 2025-12', '2025-12-01 09:00:00', 520.00),
('RESERVA_EXTERNA', 'Reserva empresa 2025-12', '2025-12-01 09:00:00', 1220.00),
('SUPLEMENTO', 'Suplemento 2025-12', '2025-12-22 09:00:00', 280.00),
('ACCIONISTA', 'Cuota accionista 2025', '2025-12-02 09:00:00', 1450.00),
('ABONO_TEMPORADA', 'Abono mensual 2025-07', '2025-12-04 09:00:00', 770.00);


-- Ordenes de restock
INSERT INTO RestockOrder (total, fecha, finalizada, resumen)
VALUES 
(30, '2025-11-22 09:00:00', 1, 'Camiseta x2'),
(20, '2025-11-23 09:00:00', 0, 'Camiseta x1, Gorro x1'),
(14, '2025-11-23 11:54:00', 1, 'Bufanda x2'),
(7, '2025-11-23 11:54:00', 0, 'Bufanda x1'),
(17, '2025-11-23 11:54:00', 0, 'Gorro x2, Bufanda x1'),
(15, '2025-11-23 11:54:00', 1, 'Camiseta x1');


-- Detalles del restock
INSERT INTO RestockDetail (id_producto, id_order, cantidad)
VALUES
(1, 1, 2),
(1, 2, 1),
(2, 2, 1),
(3, 3, 2),
(3, 4, 1),
(2, 5, 2),
(3, 5, 1),
(1, 6, 1);

-- GASTOS RESTOCK 2025, 2024, 2023, 2022
INSERT INTO Gasto (total, tipo, fecha_hora, concepto)
VALUES
-- 2025
(25, 'Restock', '2025-12-01 10:15:00', 'Camiseta x1, Bufanda x1'),
(40, 'Restock', '2025-12-03 14:30:00', 'Sudadera x2'),
(12, 'Restock', '2025-12-04 09:45:00', 'Gorro x2'),
(50, 'Restock', '2025-12-05 16:20:00', 'Chaqueta x1, Pantalón x1'),
(18, 'Restock', '2025-12-06 11:00:00', 'Camiseta x2'),
(18, 'Restock', '2025-12-01 11:00:00', 'Camiseta x2'),
(18, 'Restock', '2025-12-02 11:00:00', 'Camiseta x2'),
(18, 'Restock', '2025-12-02 11:00:00', 'Camiseta x2'),

-- 2024
(30, 'Restock', '2024-03-12 10:00:00', 'Camiseta x2'),
(22, 'Restock', '2024-07-05 15:30:00', 'Bufanda x2'),
(35, 'Restock', '2024-11-18 09:20:00', 'Sudadera x1, Gorro x1'),

-- 2023
(28, 'Restock', '2023-01-10 11:15:00', 'Camiseta x1'),
(40, 'Restock', '2023-06-22 14:45:00', 'Sudadera x2'),
(15, 'Restock', '2023-09-30 16:00:00', 'Gorro x1, Bufanda x1'),

-- 2022
(18, 'Restock', '2022-02-08 09:50:00', 'Camiseta x1'),
(25, 'Restock', '2022-05-15 13:10:00', 'Bufanda x2'),
(50, 'Restock', '2022-10-20 17:30:00', 'Chaqueta x1, Sudadera x1');

-- Partido entre el equipo profesional y el equipo externo
-- Partidos
-- Partidos de noviembre
INSERT INTO Partido (fecha, hora_inicio, hora_fin, id_equipo, id_equipo_externo, tieneSuplemento, precioSuplemento, id_instalacion)
VALUES
('2025-11-04', '18:00', '20:00', 1, 1, 0, 0.0, 1),
('2025-11-07', '17:00', '19:00', 1, 2, 0, 0.0, 1),
('2025-11-10', '19:00', '21:00', 1, 1, 0, 0.0, 1),
('2025-11-14', '18:30', '20:30', 1, 2, 0, 0.0, 1),
('2025-11-18', '16:00', '18:00', 1, 1, 0, 0.0, 1),
('2025-11-22', '17:30', '19:30', 1, 2, 0, 0.0, 1),
('2025-11-25', '18:00', '20:00', 1, 1, 0, 0.0, 1),
('2025-11-28', '19:00', '21:00', 1, 2, 0, 0.0, 1);

-- Partidos para diciembre 2025
INSERT INTO Partido (fecha, hora_inicio, hora_fin, id_equipo, id_equipo_externo, tieneSuplemento, precioSuplemento, id_instalacion)
VALUES 
('2025-11-26','16:00','18:00',1,2, 1, 20.0, 1),
('2025-12-02','18:00','20:00',2,3, 0, 0, 2),
('2025-12-03','15:30','17:30',3,4, 1, 25.0, 3),
('2025-12-04','19:00','21:00',4,1, 0, 0, 4),
('2025-12-05','17:00','19:00',1,3, 1, 30.0, 5),
('2025-12-06','16:00','18:00',2,4, 0, 0, 6),
('2025-12-07','18:30','20:30',3,1, 1, 15.0, 1),
('2025-12-08','17:00','19:00',4,2, 0, 0, 2),
('2025-12-09','16:00','18:00',1,4, 1, 22.0, 3),
('2025-12-10','19:00','21:00',2,1, 0, 0, 4),
('2025-12-11','15:00','17:00',3,2, 1, 28.0, 5),
('2025-12-12','18:00','20:00',4,3, 0, 0, 6),
('2025-12-13','16:30','18:30',1,2, 1, 18.0, 1),
('2025-12-14','17:30','19:30',2,3, 0, 0, 2),
('2025-12-15','16:00','18:00',3,4, 1, 25.0, 3),
('2025-12-16','19:00','21:00',4,1, 0, 0, 4);


INSERT INTO Partido (fecha, hora_inicio, hora_fin, id_equipo, id_equipo_externo, tieneSuplemento, precioSuplemento, id_instalacion)
VALUES
('2022-11-04', '18:00', '20:00', 1, 1, 0, 0.0, 1),
('2022-11-07', '17:00', '19:00', 1, 2, 0, 0.0, 1),
('2022-11-10', '19:00', '21:00', 1, 1, 0, 0.0, 1);

INSERT INTO Partido (fecha, hora_inicio, hora_fin, id_equipo, id_equipo_externo, tieneSuplemento, precioSuplemento, id_instalacion)
VALUES
('2024-11-04', '18:00', '20:00', 1, 1, 0, 0.0, 1),
('2024-11-07', '17:00', '19:00', 1, 2, 0, 0.0, 1),
('2024-11-10', '19:00', '21:00', 1, 1, 0, 0.0, 1);

INSERT INTO Partido (fecha, hora_inicio, hora_fin, id_equipo, id_equipo_externo, tieneSuplemento, precioSuplemento, id_instalacion)
VALUES
('2020-11-04', '18:00', '20:00', 1, 1, 0, 0.0, 1),
('2020-11-07', '17:00', '19:00', 1, 2, 0, 0.0, 1),
('2020-11-10', '19:00', '21:00', 1, 1, 0, 0.0, 1);

-- Actualización de Marcadores
-- IDs 25 al 33

UPDATE Partido SET goles_local = 3, goles_visitante = 1 WHERE id_partido = 25;
UPDATE Partido SET goles_local = 2, goles_visitante = 2 WHERE id_partido = 26;
UPDATE Partido SET goles_local = 2, goles_visitante = 0 WHERE id_partido = 27;
UPDATE Partido SET goles_local = 2, goles_visitante = 3 WHERE id_partido = 28;
UPDATE Partido SET goles_local = 2, goles_visitante = 1 WHERE id_partido = 29;
UPDATE Partido SET goles_local = 3, goles_visitante = 2 WHERE id_partido = 30;
UPDATE Partido SET goles_local = 3, goles_visitante = 3 WHERE id_partido = 31;
UPDATE Partido SET goles_local = 4, goles_visitante = 2 WHERE id_partido = 32;
UPDATE Partido SET goles_local = 1, goles_visitante = 1 WHERE id_partido = 33;

-- Los partidos insertados son:
-- PARTIDOS 2022: id_partido 1, 2, 3
-- PARTIDOS 2024: id_partido 4, 5, 6
-- PARTIDOS 2020: id_partido 7, 8, 9

-- Los 9 partidos insertados son:
-- PARTIDOS 2022: id_partido 25, 26, 27
-- PARTIDOS 2024: id_partido 28, 29, 30
-- PARTIDOS 2020: id_partido 31, 32, 33

INSERT INTO EventoPartido (id_partido, id_jugador, tipo)
VALUES
-- === PARTIDOS 2022 (IDs 25, 26, 27) ===
-- Partido 25 (2022-11-04) 
(25, 58, 'Gol'), 
(25, 58, 'Gol'),
(25, 36, 'Gol'),    
(25, 57, 'TarjetaAmarilla'),

-- Partido 26 (2022-11-07) 
(26, 58, 'TarjetaAmarilla'),
(26, 58, 'TarjetaRoja'), 
(26, 60, 'Gol'),
(26, 37, 'Gol'),

-- Partido 27 (2022-11-10)
(27, 36, 'Gol'),
(27, 36, 'Gol'),
(27, 59, 'TarjetaAmarilla'),

-- === PARTIDOS 2024 (IDs 28, 29, 30) ===
-- Partido 28 (2024-11-04)
(28, 58, 'Gol'),
(28, 58, 'TarjetaAmarilla'),
(28, 61, 'Gol'),
(28, 36, 'TarjetaAmarilla'),

-- Partido 29 (2024-11-07)
(29, 58, 'Gol'),
(29, 58, 'TarjetaAmarilla'),
(29, 37, 'Gol'),
(29, 62, 'TarjetaRoja'),

-- Partido 30 (2024-11-10)
(30, 58, 'Gol'),
(30, 58, 'Gol'),
(30, 58, 'Gol'),
(30, 63, 'TarjetaAmarilla'),

-- === PARTIDOS 2020 (IDs 31, 32, 33) ===
-- Partido 31 (2020-11-04) 
(31, 58, 'Gol'),
(31, 58, 'TarjetaRoja'),
(31, 57, 'Gol'),
(31, 36, 'Gol'),

-- Partido 32 (2020-11-07)
(32, 62, 'Gol'),
(32, 60, 'Gol'),
(32, 59, 'Gol'),
(32, 36, 'Gol'),

-- Partido 33 (2020-11-10)
(33, 58, 'Gol'),
(33, 58, 'TarjetaAmarilla'),
(33, 37, 'TarjetaRoja'),
(33, 36, 'TarjetaAmarilla');