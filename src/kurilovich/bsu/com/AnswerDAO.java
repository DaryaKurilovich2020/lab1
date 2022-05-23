package kurilovich.bsu.com;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class AnswerDAO extends AbstractDAO<Answer> {
    private final String GetByTestId = "select * from answers inner join questions on question_id=questions.id where questions.test_id = ?";
    private final String GetByTestIdGivenAnswers = "select * from students_answers inner join students on student_id = students.id inner join tests on test_id= tests.id where test_id =? and students_answers.student_id =?;";
    private final String InsertAnswer = "insert into answers(question_id, value) values(?, ?);";

    @Override
    public void insert(Answer entity) {
        try {
            Connection connection = DBConnector.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(InsertAnswer);
            preparedStatement.setInt(1, entity.getQuestion().getId());
            preparedStatement.setInt(2, entity.getValue());
            preparedStatement.executeUpdate();
    //        DBConnector.returnConnection();
            DBConnector.returnConnection(connection);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public Answer getById(int id) {
        return null;
    }

    @Override
    public void update(Answer entity) {

    }

    @Override
    public void delete(Answer entity) {

    }

    @Override
    public ArrayList<Answer> getAll() {
        return null;
    }

    public Map<Integer, Answer> getByTestId(int test_id) {
        Map<Integer, Answer> answerMap = new HashMap<>();
        try {
            Connection connection = DBConnector.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(GetByTestId);
            preparedStatement.setInt(1, test_id);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                answerMap.put(rs.getInt("question_id"), new Answer(rs.getInt("id"), rs.getInt("value")));
            }
  //          DBConnector.returnConnection();
            DBConnector.returnConnection(connection);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return answerMap;
    }

    public Map<Integer, Answer> getByTestIdGivenAnswers(int test_id, int student_id) {
        Map<Integer, Answer> answerMap = new HashMap<>();
        try {
            Connection connection = DBConnector.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(GetByTestIdGivenAnswers);
            preparedStatement.setInt(1, test_id);
            preparedStatement.setInt(2, student_id);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                answerMap.put(rs.getInt("question_id"), new Answer(rs.getInt("id"), rs.getInt("students_answers.value")));
            }
     //       DBConnector.returnConnection();
            DBConnector.returnConnection(connection);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return answerMap;
    }
}
