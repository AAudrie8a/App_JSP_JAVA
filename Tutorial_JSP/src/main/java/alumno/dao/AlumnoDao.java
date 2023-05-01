package alumno.dao;

import db.connection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import alumno.model.Alumno;

public class AlumnoDao {
	public int postAlumno(Alumno alumno) throws ClassNotFoundException {
		String INSERT_ALUMNO_SQL = "INSERT INTO ALUMNO"+"(nombre, apellido) VALUES"+"(?, ?)";
		int result = 0;
		System.out.println(alumno.getNombre() + " " + alumno.getApellido());
		System.out.println(INSERT_ALUMNO_SQL);
		try (Connection conn = connection.getConnection();

				PreparedStatement preparedStatement = conn.prepareStatement(INSERT_ALUMNO_SQL)) {
			preparedStatement.setString(1, alumno.getNombre());
			preparedStatement.setString(2, alumno.getApellido());
			System.out.println(preparedStatement);
			result = preparedStatement.executeUpdate();

		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}

		return result;
	}

}
