CREATE TABLE IF NOT EXISTS "clientes" (
	"id_cliente"	INTEGER NOT NULL,
	"nombre"	TEXT NOT NULL,
	"fk_tipoatraccion"	INTEGER NOT NULL,
	"tiempo_disponible"	REAL NOT NULL,
	"presupuesto"	INTEGER NOT NULL,
	FOREIGN KEY("fk_tipoatraccion") REFERENCES "tipo atraccion"("id_tipoatraccion"),
	PRIMARY KEY("id_cliente" AUTOINCREMENT)
);

CREATE TABLE IF NOT EXISTS "tipo atraccion" (
	"id_tipoatraccion" INTEGER NOT NULL,
	"tipo_atraccion" TEXT NOT NULL,
	PRIMARY KEY("id_tipoatraccion" AUTOINCREMENT)
);

CREATE TABLE IF NOT EXISTS "atracciones" (
	"id_atraccion"	INTEGER NOT NULL,
	"nombre"	TEXT NOT NULL,
	"fk_tipoatraccion"	INTEGER NOT NULL,
	"duracion"	REAL NOT NULL,
	"costo"	INTEGER NOT NULL,
	"cupo_disponible"	INTEGER NOT NULL,
	PRIMARY KEY("id_atraccion" AUTOINCREMENT),
	FOREIGN KEY("fk_tipoatraccion") REFERENCES "tipo atraccion"("id_tipoatraccion")
);

CREATE TABLE IF NOT EXISTS "promociones" (
	"id_promocion"	INTEGER NOT NULL,
	"nombre"	TEXT NOT NULL,
	"fk_tipopromocion"	INTEGER NOT NULL,
	"fk_tipoatraccion"	INTEGER NOT NULL,
	"costo"	INTEGER,
	"descuento"	REAL,
	PRIMARY KEY("id_promocion" AUTOINCREMENT),
	FOREIGN KEY("fk_tipopromocion") REFERENCES "tipo promocion"("id_tipopromocion"),
	FOREIGN KEY("fk_tipoatraccion") REFERENCES "tipo atraccion"("id_tipoatraccion")
);

CREATE TABLE IF NOT EXISTS "tipo promocion" (
	"id_tipopromocion" INTEGER NOT NULL,
	"tipo_promocion" TEXT NOT NULL,
	PRIMARY KEY("id_tipopromocion" AUTOINCREMENT)
);


CREATE TABLE IF NOT EXISTS "promocion atraccion" (
	"fk_promocion"	INTEGER NOT NULL,
	"fk_atraccion"	INTEGER NOT NULL,
	FOREIGN KEY("fk_atraccion") REFERENCES "atracciones"("id_atraccion"),
	FOREIGN KEY("fk_promocion") REFERENCES "promociones"("id_promocion")
);

CREATE TABLE IF NOT EXISTS "itinerarios" (
	"fk_cliente"	INTEGER NOT NULL,
	"fk_atraccion"	INTEGER NOT NULL,
	"tiempo_total"	REAL,
	FOREIGN KEY("fk_atraccion") REFERENCES "atracciones"("id_atraccion"),
	FOREIGN KEY("fk_cliente") REFERENCES "clientes"("id_cliente")
);
