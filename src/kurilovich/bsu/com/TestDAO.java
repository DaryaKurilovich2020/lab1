package kurilovich.bsu.com;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class TestDAO extends AbstractDAO<Test> {

    public static final String InsertStatement = "insert into tests(student_id, tutor_id) values(?, ?);";
    public static final String GetByIdWithQuestions = " select * from tests inner join tutors on tests.tutor_id = tutors.id inner join questions on tests.id = questions.test_id inner join students on tests.student_id = students.id where tests.id=?;";
    public static final String GetById = " select * from tests inner join tutors on tests.tutor_id = tutors.id inner join students on tests.student_id = students.id where tests.id=?;";
    public static final String UpdateStatement = "update tests set student_id = ?, tutor_id =? where id=?;";
    public static final String DeleteStatement = "delete from tests where id=?;";
    public static final String GetAll = "select * from tests inner join tutors on tests.tutor_id = tutors.id inner join students on tests.student_id = students.id";
    public static final String GetByStudentId = " select * from tests inner join tutors on tests.tutor_id = tutors.id inner join questions on tests.id = questions.test_id inner join students on tests.student_id = students.id where students.id=? and tests.id = ?;";

    @Override
    public void insert(Test test) {
        try {
            Connection connection = DBConnector.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(InsertStatement);
            preparedStatement.setInt(1, test.getStudent().getId());
            preparedStatement.setInt(2, test.getTutor().getId());
            preparedStatement.executeUpdate();
            //DBConnector.returnConnection();
            DBConnector.returnConnection(connection);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Test getByIdWithQuestions(int id) {
        Test test = null;
        try {
            Connection connection = DBConnector.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(GetByIdWithQuestions);
            preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery();
            List<Question> questionList = new ArrayList<>();
            if (rs.next()) {
                questionList.add(new Question(rs.getInt("questions.id"), rs.getString("questions.text")));
                test = new Test(id, new Student(rs.getInt("student_id"), rs.getString("students.name")), new Tutor(rs.getInt("tutor_id"), rs.getString("tutors.name")));
            }
            while (rs.next()) {
                questionList.add(new Question(rs.getInt("questions.id"), rs.getString("questions.text")));
            }
            test.setQuestions(questionList);
//            DBConnector.returnConnection();
            DBConnector.returnConnection(connection);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return test;
    }

    @Override
    public Test getById(int id) {
        Test test = null;
        try {
            Connection connection = DBConnector.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(GetById);
            preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery();
            List<Question> questionList = new ArrayList<>();
            if (rs.next()) {
                questionList.add(new Question(rs.getInt("questions.id"), rs.getString("questions.text")));
                test = new Test(id, new Student(rs.getInt("student_id"), rs.getString("students.name")), new Tutor(rs.getInt("tutor_id"), rs.getString("tutors.name")));
            }
            while (rs.next()) {
                questionList.add(new Question(rs.getInt("questions.id"), rs.getString("questions.text")));
            }
            test.setQuestions(questionList);
        //    DBConnector.returnConnection();
            DBConnector.returnConnection(connection);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return test;
    }
    public Test getByStudentId(int test_id, int student_id) {
        Test test = null;
        try {
            Connection connection = DBConnector.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(GetByStudentId);
            preparedStatement.setInt(1, student_id);
            preparedStatement.setInt(2, test_id);
            ResultSet rs = preparedStatement.executeQuery();
            List<Question> questionList = new ArrayList<>();
            if (rs.next()) {
                test = new Test(test_id, new Student(rs.getInt("student_id"), rs.getString("students.name")), new Tutor(rs.getInt("tutor_id"), rs.getString("tutors.name")));
            }
         //   DBConnector.returnConnection();
            DBConnector.returnConnection(connection);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return test;
    }


    @Override
    public void update(Test test) {
        try {
            Connection connection = DBConnector.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(UpdateStatement);
            preparedStatement.setInt(1, test.getStudent().getId());
            preparedStatement.setInt(2, test.getTutor().getId());
            preparedStatement.setInt(3, test.getId());
            preparedStatement.executeUpdate();
       //     DBConnector.returnConnection();
            DBConnector.returnConnection(connection);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(Test test) {
        try {
            Connection connection = DBConnector.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(DeleteStatement);
            preparedStatement.setInt(1, test.getId());
            preparedStatement.executeUpdate();
        //    DBConnector.returnConnection();
            DBConnector.returnConnection(connection);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public ArrayList<Test> getAll() {
        ArrayList<Test> tests = new ArrayList<>();
        try {
            Connection connection = DBConnector.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(GetAll);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                tests.add(new Test(rs.getInt("id"), new Student(rs.getInt("student_id"), rs.getString("students.name")), new Tutor(rs.getInt("tutor_id"), rs.getString("tutors.name"))));
            }
      //      DBConnector.returnConnection();
            DBConnector.returnConnection(connection);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return tests;
    }
}
