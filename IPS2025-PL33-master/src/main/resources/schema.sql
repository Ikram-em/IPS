-- ----------------------------------------------------------------------
-- BORRAR TABLAS EXISTENTES
-- ----------------------------------------------------------------------

--Para giis.demo.tkrun:
DROP TABLE IF EXISTS SesionLesion;
DROP TABLE Lesion;
DROP TABLE User_log;
DROP TABLE User_session;
DROP TABLE HorarioEspecifico;
DROP TABLE HorarioSemanal;
DROP TABLE EmpleadoNoDeportivo; 
DROP TABLE EmpleadoDeportivo;
DROP TABLE Empleado;
DROP TABLE Equipo;
DROP TABLE Plantilla;
DROP TABLE Producto;
DROP TABLE User;
DROP TABLE Purchase;
DROP TABLE Purchase_Item;
DROP TABLE FranjaEntrevista;
DROP TABLE Partido;
DROP TABLE EquipoExterno;
DROP TABLE Entrenamiento;
DROP TABLE Tribune;
DROP TABLE Section;
DROP TABLE AsientoReservado;
DROP TABLE Instalacion;
DROP TABLE ReservaExterna;
DROP TABLE LaborJardineria;
DROP TABLE Accionista;
DROP TABLE Campana;
DROP TABLE Abono;
DROP TABLE PurchaseAbono;
DROP TABLE detalles_merchandising;
DROP TABLE Ingresos;
DROP TABLE PrecioEntradas;
DROP TABLE Noticias;
DROP TABLE Suplemento;
DROP TABLE Accion;
DROP TABLE Posicion;
DROP TABLE Rol;
DROP TABLE STOCK;
DROP TABLE Gasto;
DROP TABLE RestockOrder;
DROP TABLE RestockDetail;
DROP TABLE EventoPartido;
-- ----------------------------------------------------------------------
-- CREACIÓN DE TABLAS
-- ----------------------------------------------------------------------

CREATE TABLE Instalacion (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    nombre VARCHAR(255)
);

CREATE TABLE Rol(
    id_rol INTEGER PRIMARY KEY AUTOINCREMENT,
    nombre VARCHAR(255)
);

-- 1. Tabla principal de empleados
CREATE TABLE Empleado(
    id_empleado INTEGER PRIMARY KEY AUTOINCREMENT,
    dni VARCHAR(255) NOT NULL,
    nombre VARCHAR(255) NOT NULL,
    apellido VARCHAR(255) NOT NULL,
    fecha_nacimiento DATE NOT NULL,
    telefono INT NOT NULL,
    salario_anual DECIMAL(10,2) NOT NULL,
    fecha_creacion DATETIME DEFAULT CURRENT_TIMESTAMP,
    id_instalacion INTEGER,
    CONSTRAINT FK_Empleado_Instalacion FOREIGN KEY (id_instalacion) REFERENCES Instalacion(id)
);

CREATE TABLE Posicion(
    id_posicion INTEGER PRIMARY KEY AUTOINCREMENT,
    nombre VARCHAR(255),
    id_rol INTEGER,
    tipo VARCHAR(255),
    CONSTRAINT FK_Rol_Posicion FOREIGN KEY (id_rol) REFERENCES Rol(id_rol),
    CHECK (tipo IN ('Deportiva', 'No Deportiva'))
);

-- 2. Subtabla de empleados no deportivos
CREATE TABLE EmpleadoNoDeportivo(
    id_empleado INTEGER PRIMARY KEY AUTOINCREMENT,
    id_posicion INTEGER,
    CONSTRAINT FK_Empleado_No_Deportivo FOREIGN KEY (id_empleado) REFERENCES Empleado(id_empleado),
    CONSTRAINT FK_Posicion_Empleado_No_Deportivo FOREIGN KEY (id_posicion) REFERENCES Posicion(id_posicion)
);

-- 3 Subtabla de empleados deportivos: jugadores y entrenadores
CREATE TABLE EmpleadoDeportivo(
    id_empleado INTEGER PRIMARY KEY AUTOINCREMENT,
    id_posicion INTEGER,
    CONSTRAINT FK_Empleado_Deportivo FOREIGN KEY (id_empleado) REFERENCES Empleado(id_empleado),
    CONSTRAINT FK_Posicion_Empleado_Deportivo FOREIGN KEY (id_posicion) REFERENCES Posicion(id_posicion)
);

-- 4. Tabla de equipos
CREATE TABLE Equipo(
	id_equipo INTEGER PRIMARY KEY AUTOINCREMENT,
	nombre VARCHAR(255) NOT NULL,
	id_primer_entrenador INTEGER NOT NULL,
    id_segundo_entrenador INTEGER NOT NULL,
    tipo_equipo VARCHAR(255) NOT NULL,
    categoria_equipo VARCHAR(255),
    CONSTRAINT FK_PrimerEntrenador FOREIGN KEY (id_primer_entrenador) REFERENCES EmpleadoDeportivo(id_empleado),
    CONSTRAINT FK_SegundoEntrenador FOREIGN KEY (id_segundo_entrenador) REFERENCES EmpleadoDeportivo(id_empleado),
    CHECK (tipo_equipo IN ('profesional_primer_equipo','profesional_filial','formacion')),
    CHECK (categoria_equipo IN ('juvenil','cadete','infantil','alevin','benjamin','prebejamin'))
);


-- 5. Plantilla de equipo
CREATE TABLE Plantilla(
    id_equipo INTEGER NOT NULL,
    id_jugador INTEGER NOT NULL,
    CONSTRAINT PK_Plantilla PRIMARY KEY (id_equipo, id_jugador),
    CONSTRAINT FK_Equipo_Plantilla_Equipo FOREIGN KEY (id_equipo) REFERENCES Equipo(id_equipo) ON DELETE CASCADE,
    CONSTRAINT FK_Equipo_Plantilla_Jugador FOREIGN KEY (id_jugador) REFERENCES EmpleadoDeportivo(id_empleado) ON DELETE CASCADE
);

-- 6. Productos
CREATE TABLE Producto(
    id_producto INTEGER PRIMARY KEY AUTOINCREMENT,
    nombre VARCHAR(255) NOT NULL,
    tipo VARCHAR(255) NOT NULL,
    precio DECIMAL(10,2) NOT NULL,
    imagen VARCHAR(255),
    stock INTEGER DEFAULT 0,            
    stock_minimo INTEGER DEFAULT 0,     
    id_proveedor INTEGER,
    precio_mayorista DECIMAL(10,2) NOT NULL,
    CONSTRAINT CHECK_tipo 
        CHECK (tipo IN ('Indumentaria', 'Accesorios', 'Deportes', 'Coleccionables')),
    CONSTRAINT fk_producto_proveedor
        FOREIGN KEY (id_proveedor) REFERENCES Proveedor(id_proveedor)
);


-- 7. Usuarios
CREATE TABLE User(
    id_user INTEGER PRIMARY KEY AUTOINCREMENT,
    username VARCHAR(255) UNIQUE NOT NULL,
    password VARCHAR(255),
    id_rol INTEGER,
    id_empleado INTEGER,
    CONSTRAINT FK_User_Rol FOREIGN KEY (id_rol) REFERENCES Rol(id_rol),
    CONSTRAINT FK_User_Employee FOREIGN KEY (id_empleado) REFERENCES Empleado(id_empleado)
);

-- 8. Compras
CREATE TABLE Purchase(
	id_purchase INTEGER PRIMARY KEY AUTOINCREMENT,
	id_user INTEGER,
	fecha DATETIME,
	precio DECIMAL(10,2) NOT NULL,
	tipo VARCHAR(255),
	CONSTRAINT CHECK_tipo CHECK (tipo IN ('Merchandising', 'Entradas')),
	CONSTRAINT FK_User_Purchase FOREIGN KEY (id_user) REFERENCES User(id_user)
);

-- 9. Items de compra
CREATE TABLE Purchase_Item(
    id_purchase_item INTEGER PRIMARY KEY AUTOINCREMENT,
    id_purchase INTEGER,
    id_producto INTEGER,
    quantity INTEGER,
    CONSTRAINT FK_Purchase_Item FOREIGN KEY (id_purchase) REFERENCES Purchase(id_purchase),
    CONSTRAINT FK_PurchaseItem_Producto FOREIGN KEY (id_producto) REFERENCES Producto(id_producto)
);

-- 10. Horario semanal
CREATE TABLE HorarioSemanal(
    id_horario INTEGER PRIMARY KEY AUTOINCREMENT,
    id_empleado INTEGER NOT NULL,
    dia VARCHAR(10) NOT NULL,
    hora_inicio VARCHAR(255),
    hora_fin VARCHAR(255),
    CONSTRAINT FK_empleado_Horario FOREIGN KEY (id_empleado) REFERENCES Empleado(id_empleado)
);


-- 11. Horario específico
CREATE TABLE HorarioEspecifico(
    id_horario INTEGER PRIMARY KEY AUTOINCREMENT,
    id_empleado INTEGER NOT NULL,
    fecha DATE NOT NULL,
    dia_semana VARCHAR(255),
    hora_inicio VARCHAR(255),
    hora_fin VARCHAR(255),
    CONSTRAINT FK_empleado_Horario_Especifico FOREIGN KEY (id_empleado) REFERENCES Empleado(id_empleado)
);

-- 12 Tabla para las franajs horarias de entrevistas
CREATE TABLE FranjaEntrevista (
    id_franja INTEGER PRIMARY KEY AUTOINCREMENT,
    id_jugador INTEGER NOT NULL,
    fecha DATE NOT NULL,
    hora_inicio TIME NOT NULL,
    hora_fin TIME NOT NULL,
    medio_comunicacion VARCHAR(255),
    CONSTRAINT FK_FranjaEntrevista_Jugador FOREIGN KEY (id_jugador) REFERENCES EmpleadoDeportivo(id_empleado)
);

-- 13 Tabla equipo externo
CREATE TABLE EquipoExterno (
	id_equipo INTEGER PRIMARY KEY AUTOINCREMENT,
	nombre VARCHAR(255)
);

CREATE TABLE Partido (
    id_partido INTEGER PRIMARY KEY AUTOINCREMENT,
    fecha DATE NOT NULL,
    hora_inicio TIME NOT NULL,
    hora_fin TIME NOT NULL,
    id_equipo INTEGER,
    id_equipo_externo INTEGER,
    tieneSuplemento INTEGER DEFAULT 0,
    precioSuplemento REAL DEFAULT 0.0,
    id_instalacion INTEGER,
    goles_local INTEGER,
    goles_visitante INTEGER,
    CONSTRAINT FK_equipo_interno_Partido FOREIGN KEY (id_equipo) REFERENCES Equipo(id_equipo),
    CONSTRAINT FK_equipo_externo_Partido FOREIGN KEY (id_equipo_externo) REFERENCES EquipoExterno(id_equipo),
    CONSTRAINT FK_Instalacion_Partido FOREIGN KEY (id_instalacion) REFERENCES Instalacion(id)
);

-- 15 Tabla entrenamientos
CREATE TABLE Entrenamiento (
    id_entrenamiento INTEGER PRIMARY KEY AUTOINCREMENT,
    id_equipo INTEGER NOT NULL,
    fecha DATE NOT NULL,
    hora_inicio TIME NOT NULL,
    hora_fin TIME NOT NULL,
    id_instalacion INTEGER,
    CONSTRAINT FK_Entrenamiento_Jugador FOREIGN KEY (id_equipo) REFERENCES Equipo(id_equipo),
    CONSTRAINT FK_Instalacion_Entrenamiento FOREIGN KEY (id_instalacion) REFERENCES Instalacion(id)
);
-- 12. Reservas externas
CREATE TABLE ReservaExterna (
    id_reserva INTEGER PRIMARY KEY AUTOINCREMENT,
    nombre_usuario VARCHAR(255) NOT NULL,
    num_tarjeta VARCHAR(16) NOT NULL,
    id_instalacion INTEGER,
    fecha DATE NOT NULL,
    hora_inicio DATETIME NOT NULL,
    hora_fin DATETIME NOT NULL,
    precio_total DECIMAL(10,2) NOT NULL,
    UNIQUE(id_instalacion, fecha, hora_inicio, hora_fin),
    CONSTRAINT FK_Instalacion_Reserva FOREIGN KEY (id_instalacion) REFERENCES Instalacion(id)
);

-- 17 Tabla tribuna
CREATE TABLE Tribune (
	id INTEGER PRIMARY KEY AUTOINCREMENT,
	nombre VARCHAR(1),
	CONSTRAINT CHECK_nombre CHECK (nombre IN ('A', 'B', 'C', 'D'))
);

-- 18 Tabla seccion
CREATE TABLE Section (
	id INTEGER PRIMARY KEY AUTOINCREMENT,
	nombre VARCHAR(1),
	CONSTRAINT CHECK_nombre CHECK (nombre IN ('A', 'B', 'C', 'D', 'E', 'F'))
);

-- 19 Tabla asientosReservados
CREATE TABLE AsientoReservado (
	id_reserva INTEGER PRIMARY KEY AUTOINCREMENT,
	id_partido INTEGER,
	id_tribune INTEGER,
	id_section INTEGER,
	fila INTEGER,
	cantidad_asientos INTEGER,
	asiento_inicial INTEGER,
	id_purchase INTEGER,
	CONSTRAINT CHECK_fila CHECK (fila <= 10),
	CONSTRAINT CHECK_cant_asientos CHECK (cantidad_asientos <= 15),
	CONSTRAINT FK_partido_Asiento FOREIGN KEY (id_partido) REFERENCES Partido(id_partido),
	CONSTRAINT FK_tribuna_Asiento FOREIGN KEY (id_tribune) REFERENCES Tribune(id),
	CONSTRAINT FK_seccion_Asiento FOREIGN KEY (id_section) REFERENCES Section(id),
	CONSTRAINT FK_purchase_Asiento FOREIGN KEY (id_purchase) REFERENCES Purchase(id_purchase)
);

-- 20. Ingresos
CREATE TABLE Ingresos (
    id_ingreso INTEGER PRIMARY KEY AUTOINCREMENT,
    tipo TEXT NOT NULL,
    concepto TEXT NOT NULL,
    fecha_hora TEXT,
    total REAL NOT NULL
);

-- 21. Detalles de merchandising
CREATE TABLE detalles_merchandising(
    id_detalle INTEGER PRIMARY KEY AUTOINCREMENT,
    id_ingreso INTEGER,
    producto TEXT,
    unidades INTEGER,
    precio_unitario REAL,
    FOREIGN KEY (id_ingreso) REFERENCES Ingresos(id_ingreso)
);

-- 22 Precios entradas de partidos
CREATE TABLE PrecioEntradas(
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    id_partido INTEGER,
    id_tribune INTEGER,
    id_section INTEGER,
    precio DECIMAL(10,2),
    CONSTRAINT FK_tribuna_precio FOREIGN KEY (id_tribune) REFERENCES Tribune(id),
	CONSTRAINT FK_seccion_precio FOREIGN KEY (id_section) REFERENCES Section(id),
    CONSTRAINT FK_partido_precio FOREIGN KEY (id_partido) REFERENCES Partido(id_partido)
);

-- 23. Campaña de acciones
CREATE TABLE Campana (
	id_campana INTEGER PRIMARY KEY AUTOINCREMENT,
	fase INTEGER,
	estado VARCHAR(255) NOT NULL,
	n_accionesDisponibles INTEGER,
	n_accionesVendidas INTEGER,
    fecha_creacion DATETIME,
	CONSTRAINT check_fase CHECK (fase IN (1,2,3)),
	CONSTRAINT check_estado CHECK (estado IN ('Activa', 'Inactiva'))
);

--24. Accionista
CREATE TABLE Accionista (
	id_accionista INTEGER PRIMARY KEY AUTOINCREMENT,
	username VARCHAR(255) unique NOT NULL,
    password VARCHAR(255),
    nombre VARCHAR(255),
    apellido VARCHAR(255),
    dni VARCHAR,
    fecha_nacimiento DATE
);

CREATE TABLE Accion (
    id_accion INTEGER PRIMARY KEY AUTOINCREMENT,
    id_accionista INTEGER NOT NULL,
    fecha_creacion DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    id_campana INTEGER NOT NULL,
    en_venta BOOLEAN DEFAULT 0,
    CONSTRAINT FK_Accion_Accionista FOREIGN KEY (id_accionista) REFERENCES Accionista(id_accionista),
    CONSTRAINT FK_Accion_Campana FOREIGN KEY (id_campana) REFERENCES Campana(id_campana)
);


--25. Tabla de noticias
CREATE TABLE Noticias (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    titulo TEXT NOT NULL,
    contenido TEXT NOT NULL,
    imagen TEXT,
    publicada boolean DEFAULT 0,        
    fecha_publicacion DATETIME DEFAULT CURRENT_TIMESTAMP
);

-- ----------------------------------------------------------------------
-- TABLAS DE ABONOS
-- ----------------------------------------------------------------------

-- Tabla Abono adaptada para DNI
CREATE TABLE Abono (
    id_abono INTEGER PRIMARY KEY AUTOINCREMENT,
    dni_cliente TEXT NOT NULL,
    id_tribune INTEGER NOT NULL,
    id_section INTEGER NOT NULL,
    fila INTEGER NOT NULL,
    id_asiento INTEGER NOT NULL,
    fecha_inicio DATE NOT NULL,
    fecha_fin DATE NOT NULL,
    precio_total DECIMAL(10,2) NOT NULL,
    CONSTRAINT FK_abono_cliente FOREIGN KEY (dni_cliente) REFERENCES User(dni),
    CONSTRAINT FK_abono_tribuna FOREIGN KEY (id_tribune) REFERENCES Tribune(id),
    CONSTRAINT FK_abono_seccion FOREIGN KEY (id_section) REFERENCES Section(id),
    CONSTRAINT CHECK_fila CHECK (fila BETWEEN 1 AND 10)
);


-- Tabla purchaseAbono (corregida)
CREATE TABLE PurchaseAbono (
    idAbono INTEGER PRIMARY KEY AUTOINCREMENT,
    dni_cliente VARCHAR(20) NOT NULL,
    idAsiento INTEGER NOT NULL,
    tribuna TEXT NOT NULL,
    seccion TEXT NOT NULL,
    fila INTEGER NOT NULL,
    precio REAL NOT NULL,
    fechaCompra TEXT NOT NULL,
    UNIQUE (tribuna, seccion, idAsiento, fila)
);



CREATE TABLE LaborJardineria (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    id_empleado INTEGER,
    fecha DATE,
    hora_inicio TIME,
    hora_fin TIME,
    id_instalacion INTEGER,
    CONSTRAINT FK_Instalacion_labor_jardineria FOREIGN KEY (id_instalacion) REFERENCES Instalacion(id),
    CONSTRAINT FK_empleado_labor_jardineria FOREIGN KEY (id_empleado) REFERENCES Empleado(id_empleado)
);

CREATE TABLE Suplemento (
    id_suplemento INTEGER PRIMARY KEY AUTOINCREMENT,
    idAbono INTEGER NOT NULL,
    idPartido INTEGER NOT NULL,
    precio REAL NOT NULL,
    fechaCompra DATETIME DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT FK_Suplemento_Abono FOREIGN KEY (idAbono) REFERENCES Abono(id_abono),
    CONSTRAINT FK_Suplemento_Partido FOREIGN KEY (idPartido) REFERENCES Partido(id_partido)
);

-- Tabla para el control de Stock
CREATE TABLE STOCK (
    id_producto      NUMBER PRIMARY KEY,
    cantidad_actual  NUMBER NOT NULL,
    cantidad_minima  NUMBER NOT NULL,
    CONSTRAINT fk_stock_producto
        FOREIGN KEY (id_producto)
        REFERENCES PRODUCTO(id_producto)
);

-- Tabla para guardar la sesion de los usuarios
CREATE TABLE User_session (
    session_id INTEGER PRIMARY KEY AUTOINCREMENT,
    user_id INT NOT NULL,
    fecha_inicio DATETIME DEFAULT CURRENT_TIMESTAMP
);


-- Tabla para guardar el log de cada sesion
CREATE TABLE User_log (
    log_id INTEGER PRIMARY KEY AUTOINCREMENT,
    session_id INT NOT NULL,
    accion VARCHAR(100) NOT NULL,
    detalles TEXT,
    fecha_hora DATETIME DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (session_id) REFERENCES User_session(session_id)
);


CREATE TABLE Lesion (
	id_lesion INTEGER PRIMARY KEY AUTOINCREMENT,
	id_jugador INTEGER NOT NULL,
	id_partido INTEGER,
	id_entreno INTEGER,
	prioridad VARCHAR(254) NOT NULL,
	diagnostico TEXT NOT NULL,
	alta_medica INTEGER NOT NULL DEFAULT 0,
	fecha_lesion DATETIME DEFAULT CURRENT_TIMESTAMP,
	FOREIGN KEY(id_jugador) REFERENCES Empleado(id_empleado),
	FOREIGN KEY(id_partido) REFERENCES Partido(id_partido),
	FOREIGN KEY(id_entreno) REFERENCES Entrenamiento(id_entrenamiento),
	CONSTRAINT CHECK_PRIORIDAD CHECK (prioridad in ('Alta', 'Media', 'Baja')),
	CONSTRAINT CHECK_ALTA_MEDICA CHECK (alta_medica BETWEEN 0 AND 1)
);


CREATE TABLE SesionLesion (
    id_sesion INTEGER PRIMARY KEY AUTOINCREMENT,
    id_lesion INTEGER NOT NULL,
    accion TEXT NOT NULL,
    prioridad TEXT,
    diagnostico TEXT,
    alta_medica INTEGER,
    fecha_hora DATETIME DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (id_lesion) REFERENCES Lesion(id_lesion)
);

CREATE TABLE Gasto (
    id_gasto INTEGER PRIMARY KEY AUTOINCREMENT,
    total DECIMAL(10,2) NOT NULL,
    tipo VARCHAR(255),
    fecha_hora TEXT,
    concepto VARCHAR(255),
    CONSTRAINT CHECK_tipo CHECK (tipo IN ('Restock'))
);

CREATE TABLE RestockOrder (
    id_order INTEGER PRIMARY key AUTOINCREMENT,
    total DECIMAL(10,2) NOT NULL,
    fecha DATE,
    resumen VARCHAR(255),
    finalizada BOOLEAN DEFAULT 0
);

CREATE TABLE RestockDetail (
    id_detail INTEGER PRIMARY KEY AUTOINCREMENT,
    id_producto INTEGER,
    id_order INTEGER,
    cantidad INTEGER,
    FOREIGN KEY (id_producto) REFERENCES Producto(id_producto),
    FOREIGN KEY (id_order) REFERENCES RestockOrder(id_order)
);

CREATE TABLE EventoPartido (
    id_evento INTEGER PRIMARY KEY AUTOINCREMENT,
    id_partido INTEGER NOT NULL,
    id_jugador INTEGER,
    tipo TEXT NOT NULL CHECK (tipo IN ('Gol','TarjetaAmarilla','TarjetaRoja','Lesion')),
    FOREIGN KEY(id_partido) REFERENCES Partido(id_partido),
    FOREIGN KEY(id_jugador) REFERENCES EmpleadoDeportivo(id_empleado)
);