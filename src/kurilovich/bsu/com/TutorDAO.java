package kurilovich.bsu.com;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TutorDAO extends AbstractDAO<Tutor> {

    public static final String InsertStatement = "insert into tutors(name) values(?);";
    public static final String GetById = "select * from tutors where id=?;";
    public static final String UpdateStatement = "update tutors set name=? where id=?;";
    public static final String DeleteStatement = "delete from tutors where id=?;";
    public static final String GetAll = "select * from tutors;";


    @Override
    public void insert(Tutor tutor) {
        try {
            Connection connection = DBConnector.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(InsertStatement);
            preparedStatement.setString(1, tutor.getName());
            preparedStatement.executeUpdate();
   //         DBConnector.returnConnection();
            DBConnector.returnConnection(connection);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public Tutor getById(int id) {
        Tutor tutor = null;
        try {
            Connection connection = DBConnector.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(GetById);
            preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) {
                tutor = new Tutor(id, rs.getString("name"));
            }
   //         DBConnector.returnConnection();
            DBConnector.returnConnection(connection);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return tutor;
    }

    @Override
    public void update(Tutor tutor) {
        try {
            Connection connection = DBConnector.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(UpdateStatement);
            preparedStatement.setString(1, tutor.getName());
            preparedStatement.executeUpdate();
  //          DBConnector.returnConnection();
            DBConnector.returnConnection(connection);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(Tutor tutor) {
        try {
            Connection connection = DBConnector.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(DeleteStatement);
            preparedStatement.setInt(1, tutor.getId());
            preparedStatement.executeUpdate();
  //          DBConnector.returnConnection();
            DBConnector.returnConnection(connection);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public ArrayList<Tutor> getAll() {
        ArrayList<Tutor> tutors = new ArrayList<>();
        try {
            Connection connection = DBConnector.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(GetAll);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                tutors.add(new Tutor(rs.getInt("id"), rs.getString("name")));
            }
     //       DBConnector.returnConnection();
            DBConnector.returnConnection(connection);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return tutors;
    }
}
