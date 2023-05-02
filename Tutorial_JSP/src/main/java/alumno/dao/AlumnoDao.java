package alumno.dao;

import db.connection;
import oracle.jdbc.OracleTypes;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Types;
import java.sql.ResultSet;

import alumno.model.Alumno;

public class AlumnoDao {
	public int postAlumno(Alumno alumno) throws ClassNotFoundException {
		String INSERT_ALUMNO_SQL = "INSERT INTO ALUMNO" + "(nombre, apellido) VALUES" + "(?, ?)";
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

	public void updateAlumno(Alumno alumno) throws ClassNotFoundException {
		String UPDATE_ALUMNO_SQL = "UPDATE ALUMNO SET nombre=?, apellido =? where carnetAlumno =?";
		int result = 0;
		System.out.println(alumno.getNombre() + " " + alumno.getApellido());
		System.out.println(UPDATE_ALUMNO_SQL);
		try (Connection conn = connection.getConnection();

				PreparedStatement preparedStatement = conn.prepareStatement(UPDATE_ALUMNO_SQL)) {
			preparedStatement.setString(1, alumno.getNombre());
			preparedStatement.setString(2, alumno.getApellido());
			preparedStatement.setInt(3, alumno.getCarnet());
			System.out.println(preparedStatement);
			preparedStatement.executeUpdate();

		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}

	}

	public int postLogin(Alumno alumno) throws ClassNotFoundException {
		int result = 0;
		System.out.println(alumno.getCarnet() + " " + alumno.getNombre());

		try (Connection conn = connection.getConnection();
				CallableStatement preparedStatement = conn.prepareCall("{call Login(?, ?, ?)}")) {

			preparedStatement.setInt(1, alumno.getCarnet());
			preparedStatement.setString(2, alumno.getNombre());
			preparedStatement.registerOutParameter(3, OracleTypes.NUMBER);

			preparedStatement.execute();

			result = preparedStatement.getInt(3);

			System.out.println(result);

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return result;
	}

}
