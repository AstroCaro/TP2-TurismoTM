DROP TABLE IF EXISTS "clientes";
CREATE TABLE "clientes" (
	"id_cliente"	INTEGER NOT NULL,
	"nombre"	TEXT NOT NULL,
	"fk_tipoatraccion"	INTEGER NOT NULL,
	"presupuesto"	INTEGER NOT NULL,
	"tiempo_disponible"	REAL NOT NULL,
	FOREIGN KEY("fk_tipoatraccion") REFERENCES "tipo atraccion"("id_tipoatraccion"),
	PRIMARY KEY("id_cliente" AUTOINCREMENT)
);

DROP TABLE IF EXISTS "tipo atraccion";
CREATE TABLE "tipo atraccion" (
	"id_tipoatraccion" INTEGER NOT NULL,
	"tipo_atraccion" TEXT NOT NULL,
	PRIMARY KEY("id_tipoatraccion" AUTOINCREMENT)
);

DROP TABLE IF EXISTS "atracciones";
CREATE TABLE "atracciones" (
	"id_atraccion"	INTEGER NOT NULL,
	"nombre"	TEXT NOT NULL UNIQUE,
	"costo"	INTEGER NOT NULL,
	"tiempo"	REAL NOT NULL,
	"cupos_disponibles"	INTEGER NOT NULL,
	"fk_tipoatraccion"	INTEGER NOT NULL,
	FOREIGN KEY("fk_tipoatraccion") REFERENCES "tipo atraccion"("id_tipoatraccion"),
	PRIMARY KEY("id_atraccion" AUTOINCREMENT)
)

DROP TABLE IF EXISTS "promociones";
CREATE TABLE "promociones" (
	"id_promocion"	INTEGER NOT NULL,
	"nombre"	TEXT NOT NULL UNIQUE,
	"fk_tipopromocion"	INTEGER NOT NULL,
	"fk_tipoatraccion"	INTEGER NOT NULL,
	"costo"	INTEGER,
	"descuento"	REAL,
	"atraccion_gratis"	INTEGER,
	PRIMARY KEY("id_promocion" AUTOINCREMENT),
	FOREIGN KEY("fk_tipopromocion") REFERENCES "tipo promocion"("id_tipopromocion"),
	FOREIGN KEY("fk_tipoatraccion") REFERENCES "tipo atraccion"("id_tipoatraccion")
)

DROP TABLE IF EXISTS "tipo promocion";
CREATE TABLE "tipo promocion" (
	"id_tipopromocion" INTEGER NOT NULL,
	"tipo_promocion" TEXT NOT NULL,
	PRIMARY KEY("id_tipopromocion" AUTOINCREMENT)
);

DROP TABLE IF EXISTS "promocion-atraccion";
CREATE TABLE "promocion-atraccion" (
	"fk_promocion"	INTEGER NOT NULL,
	"fk_atraccion"	INTEGER NOT NULL,
	FOREIGN KEY("fk_atraccion") REFERENCES "atracciones"("id_atraccion"),
	FOREIGN KEY("fk_promocion") REFERENCES "promociones"("id_promocion")
);

DROP TABLE IF EXISTS "itinerarios";
CREATE TABLE "itinerarios" (
	"fk_cliente"	INTEGER NOT NULL,
	"fk_atraccion"	INTEGER,
	"fk_promocion"	INTEGER,
	"costo"	INTEGER,
	"tiempo"	REAL,
	FOREIGN KEY("fk_promocion") REFERENCES "promociones"("id_promocion"),
	FOREIGN KEY("fk_atraccion") REFERENCES "atracciones"("id_atraccion"),
	FOREIGN KEY("fk_cliente") REFERENCES "clientes"("id_cliente")
)

INSERT INTO "tipo atraccion" VALUES 
	(1, 'AVENTURA'),
	(2, 'DEGUSTACION'),
	(3, 'PAISAJE');

INSERT INTO "clientes" VALUES
	(1,'Eowyn', 1, 10, 8),
	(2,'Gandalf', 3, 100, 5),
	(3,'Sam', 2, 360, 80),
	(4,'Galadriel', 3, 120, 5),
	(5,'Gimli', 1, 250, 20),
	(6,'Theoden', 3, 50, 10),
	(7,'Pippin',2, 80, 12),
	(8,'Aragorn',1, 200, 15),
	(9,'Arwen', 3, 60, 10),
	(10,'Merry', 2, 40, 12);

INSERT INTO "atracciones" VALUES
	(1,'Moria', 10, 2, 6, 1),
	(2,'Bosque Negro', 3, 4, 12, 1),
	(3,'Mordor', 25, 3, 4, 1),
	(4,'Gondor', 15, 5, 20, 1),
	(5,'Prancing Pony', 8, 2, 40, 2),
	(6,'La Comarca', 3, 6.5, 150, 2),
	(7,'Lothlorien', 35, 1, 30, 2),
	(8,'Isengard', 10, 3, 25, 2),
	(9,'Abismo de Helm', 5, 2, 15, 3),
	(10,'Erebor', 12, 3, 32, 3),
	(11,'Minas Tirith', 5, 2.5, 25, 3),
	(12,'Rivendell', 20, 4, 10, 3);
	
INSERT INTO "tipo promocion" VALUES
	(1, 'ABSOLUTA'),
	(2, 'PORCENTUAL'),
	(3, 'AxB');
	

INSERT INTO "promociones" VALUES
	(1,'PromocionAbsoluta 1', 1, 2, 36, NULL, NULL),
	(2, 'PromocionAbsoluta 2', 1, 3, 15, NULL, NULL),
	(3, 'PromocionPorcentual 1', 2, 1, NULL, 0.20, NULL),
	(4, 'PromocionPorcentual 2', 2, 1, NULL, 0.30, NULL),
	(5, 'PromocionAxB 1', 3, 3, NULL, NULL, 10),
	(6, 'PromocionAxB 2',3, 1, NULL, NULL, 2);
	
INSERT INTO "promocion-atraccion" VALUES
	(1, 6),
	(1, 7),
	(2, 10),
	(2, 11),
	(3, 2),
	(3, 3),
	(4, 1),
	(4, 4),
	(5, 9),
	(5, 10),
	(5, 11),
	(6, 1),
	(6, 2),
	(6, 3);
