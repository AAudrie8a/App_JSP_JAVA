SET SERVEROUTPUT ON;


DROP TABLE PRODUCTO;
DROP TABLE CATEGORIA;
DROP SEQUENCE idCategoriaSeq;
DROP SEQUENCE idProductoSeq;
SELECT * FROM CATEGORIA;

SELECT P.nombreProducto, c.nombrecategoria FROM PRODUCTO P
JOIN CATEGORIA C
ON p.idcategoria = c.idcategoria;

CREATE SEQUENCE idCategoriaSeq start with 1 increment by 1;
CREATE TABLE CATEGORIA(
    idCategoria NUMBER  default idCategoriaSeq.nextval,
    nombreCategoria VARCHAR(500) NOT NULL, 
    PRIMARY KEY(idCategoria)
);

CREATE SEQUENCE idProductoSeq start with 1 increment by 1;
CREATE TABLE PRODUCTO (
    idProducto NUMBER  default idProductoSeq.nextval,
    idCategoria NUMBER NOT NULL,
    nombreProducto VARCHAR(500) NOT NULL,
    PRIMARY KEY(idProducto)
);


ALTER TABLE PRODUCTO ADD CONSTRAINT FK_CATEGORIA_PRODUCTO FOREIGN KEY (idCategoria) REFERENCES CATEGORIA(idCategoria);

INSERT INTO CATEGORIA VALUES (0,'Condimentos');
INSERT INTO CATEGORIA (nombreCategoria) VALUES ('Embutidos');
INSERT INTO PRODUCTO(idcategoria, nombreproducto) VALUES (0, 'Pimienta');
INSERT INTO PRODUCTO(idcategoria, nombreproducto) VALUES (1, 'Jamón');
INSERT INTO PRODUCTO(idcategoria, nombreproducto) VALUES (1, 'Salchicha');


CREATE SEQUENCE idExamenSeq START WITH 1 INCREMENT BY 1;

CREATE TABLE EXAMENES (
    idExamen NUMBER DEFAULT idExamenSeq.nextval,
    idClase NUMBER NOT NULL,
    numPreguntas NUMBER NOT NULL,
    fechaRealizacion DATE NOT NULL,
    PRIMARY KEY(idExamen)
);

CREATE SEQUENCE idClaseSeq START WITH 1 INCREMENT BY 1;

CREATE TABLE CLASE (
    idClase NUMBER DEFAULT idClaseSeq.nextval,
    nombreClase varchar(1000) NOT NULL,
    PRIMARY KEY (idClase)
);


CREATE SEQUENCE idAlumnoSeq START WITH 1 INCREMENT BY 1;
CREATE TABLE ALUMNO (
    carnetAlumno NUMBER DEFAULT idAlumnoSeq.NEXTVAL,
    nombre VARCHAR(500) NOT NULL,
    apellido VARCHAR(500) NOT NULL,
    PRIMARY KEY (carnetAlumno)
);

CREATE SEQUENCE idActividadSeq START WITH 1 INCREMENT BY 1;
CREATE TABLE ACTIVIDAD_ALUMNO (
    idActividad NUMBER DEFAULT idActividadSeq.NEXTVAL,
    calificacion FLOAT NOT NULL,
    idExamen NUMBER NOT NULL,
    carnetAlumno NUMBER NOT NULL,
    PRIMARY KEY(idActividad)
);

ALTER TABLE ACTIVIDAD_ALUMNO ADD CONSTRAINT FK_ALUMNO_ACTIVIDAD FOREIGN KEY (carnetAlumno) REFERENCES ALUMNO(carnetAlumno);
ALTER TABLE ACTIVIDAD_ALUMNO ADD CONSTRAINT FK_EXAMEN_ACTIVIDAD FOREIGN KEY (idExamen) REFERENCES EXAMENES(idExamen);


INSERT INTO EXAMENES(examenes.idclase,examenes.numpreguntas, examenes.fecharealizacion) VALUES (1, 10, '30-04-2023');
INSERT INTO EXAMENES(examenes.idclase,examenes.numpreguntas, examenes.fecharealizacion) VALUES (2, 30, '30-04-2023');
INSERT INTO EXAMENES(examenes.idclase,examenes.numpreguntas, examenes.fecharealizacion) VALUES (3, 30, '30-04-2023');
INSERT INTO EXAMENES(examenes.idclase,examenes.numpreguntas, examenes.fecharealizacion) VALUES (4, 15, '30-04-2023');

INSERT INTO ALUMNO (nombre, apellido) VALUES('Audrie Annelisse', 'del Cid Ochoa');
INSERT INTO ALUMNO (nombre, apellido) VALUES('Rodrigo Alejandro', 'del Cid Ochoa');
INSERT INTO ALUMNO (nombre, apellido) VALUES('Johanna Nieves', 'Ochoa Boeckmann');

INSERT INTO ACTIVIDAD_ALUMNO (calificacion, idExamen, carnetAlumno) VALUES (8, 2, 1);
INSERT INTO ACTIVIDAD_ALUMNO (calificacion, idExamen, carnetAlumno) VALUES (25, 3, 1);
INSERT INTO ACTIVIDAD_ALUMNO (calificacion, idExamen, carnetAlumno) VALUES (28, 3, 2);
INSERT INTO ACTIVIDAD_ALUMNO (calificacion, idExamen, carnetAlumno) VALUES (20, 4, 2);
INSERT INTO ACTIVIDAD_ALUMNO (calificacion, idExamen, carnetAlumno) VALUES (30, 4, 0);
INSERT INTO ACTIVIDAD_ALUMNO (calificacion, idExamen, carnetAlumno) VALUES (10, 5, 0);


SELECT A.nombre AS NOMBRE, A.apellido AS APELLIDO, AA.calificacion AS NOTA, E.numPreguntas AS TOTAL, ROUND(((AA.calificacion/E.numPreguntas*100)),2) AS PROCENTAJE FROM ACTIVIDAD_ALUMNO AA
INNER JOIN EXAMENES E
ON e.idexamen = AA.idExamen
INNER JOIN ALUMNO A
ON A.carnetAlumno = AA.carnetAlumno
ORDER BY A.carnetAlumno;


SELECT * FROM ACTIVIDAD_ALUMNO;
SELECT * FROM EXAMENES;
SELECT * FROM ALUMNO ORDER BY carnetAlumno desc;

CREATE OR REPLACE PROCEDURE Login (carnet IN NUMBER, nombre IN VARCHAR2, res OUT NUMBER)
IS
    v_count ALUMNO.nombre%TYPE;
BEGIN
    SELECT a.nombre INTO v_count FROM ALUMNO A
    WHERE carnet = a.carnetalumno AND nombre = a.nombre;
    
    IF v_count = nombre THEN
        res := 1;
    ELSE
        res := 0;
    END IF;
    
    --DBMS_OUTPUT.PUT_LINE(res);
END;


DECLARE
  v_res NUMBER;
BEGIN
  Login(41, 'Juan', v_res);
  DBMS_OUTPUT.PUT_LINE(v_res);
END;


CREATE OR REPLACE PROCEDURE Login (carnet IN NUMBER, nombre IN VARCHAR2, res OUT SYS_REFCURSOR)
IS
BEGIN
    OPEN res FOR
    SELECT carnetalumno FROM ALUMNO 
    WHERE carnet= alumno.carnetalumno AND nombre= alumno.nombre;
     
    --DBMS_OUTPUT.PUT_LINE(res);
END;

CALL Login(41, 'Juan');




SELECT a.nombre FROM ALUMNO A
    WHERE 41 = a.carnetalumno AND 'Juans' = a.nombre;


COMMIT;