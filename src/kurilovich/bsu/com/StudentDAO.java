package kurilovich.bsu.com;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StudentDAO extends AbstractDAO<Student> {
    public static final String InsertStatement = "insert into students(name) values(?);";
    public static final String GetById = "select * from students where id=?;";
    public static final String UpdateStatement = "update student set name=? where id=?;";
    public static final String DeleteStatement = "delete from student where id=?;";
    public static final String GetAll = "select * from student;";


    @Override
    public void insert(Student student) {
        try {
            Connection connection = DBConnector.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(InsertStatement);
            preparedStatement.setString(1, student.getName());
            preparedStatement.executeUpdate();
        //    DBConnector.returnConnection();
            DBConnector.returnConnection(connection);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public Student getById(int id) {
        Student student = null;
        try {
            Connection connection = DBConnector.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(GetById);
            preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) {
                student = new Student(id, rs.getString("name"));
            }
      //      DBConnector.returnConnection();
            DBConnector.returnConnection(connection);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return student;
    }

    @Override
    public void update(Student student) {
        try {
            Connection connection = DBConnector.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(UpdateStatement);
            preparedStatement.setString(1, student.getName());
            preparedStatement.executeUpdate();
        //    DBConnector.returnConnection();
            DBConnector.returnConnection(connection);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(Student student) {
        try {
            Connection connection = DBConnector.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(DeleteStatement);
            preparedStatement.setInt(1, student.getId());
            preparedStatement.executeUpdate();
 //           DBConnector.returnConnection();
            DBConnector.returnConnection(connection);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public ArrayList<Student> getAll() {
        ArrayList<Student> clients = new ArrayList<>();
        try {
            Connection connection = DBConnector.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(GetAll);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                clients.add(new Student(rs.getInt("id"), rs.getString("name")));
            }
   //         DBConnector.returnConnection();
            DBConnector.returnConnection(connection);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return clients;
    }

}
