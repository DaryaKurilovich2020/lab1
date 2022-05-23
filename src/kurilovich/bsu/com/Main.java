package kurilovich.bsu.com;

import java.util.Map;

public class Main {
    public static void main(String[] args) throws ParseException {
        
//        Вывести список доступных тестов.
        System.out.println("Доступные тесты");
        TestDAO testDAO = new TestDAO();
        for (Test test : testDAO.getAll()) {
            System.out.println(test.toString());
        }
//        Вывести список вопросов теста.
        System.out.println("Список вопросов теста");
        Test test = testDAO.getByIdWithQuestions(3);
        for (Question question : test.getQuestions()) {
            System.out.println(question.toString());
        }
//        Вывести результаты теста заданного студента.
        System.out.println("Результаты теста 3 студента 1");
        AnswerDAO answerDAO = new AnswerDAO();
        Map<Integer, Answer> answerMap = answerDAO.getByTestId(3);
        Map<Integer, Answer> givenAnswerMap = answerDAO.getByTestIdGivenAnswers(3, 1);
        int total = 0;
        int count = 0;
        for (Integer question : givenAnswerMap.keySet()) {
            if (answerMap.get(question).getValue().equals(givenAnswerMap.get(question).getValue())) {
                count++;
            }
            total++;
        }
        System.out.println("Результат " + count + "/" + total);
//        Назначить тест студенту, пройти тест, записать результаты теста (просуммировать результат ответа на каждый вопрос теста).
        System.out.println("Назначить тест студенту, пройти тест, записать результаты теста (просуммировать результат ответа на каждый вопрос теста)");
        Student student = new Student(2, "Arseniy Borsukov");
        Tutor tutor = new Tutor(4, "Artem Moroz");
        Test test1 = new Test(4, student,tutor);
        testDAO.update(test1);
        Question question1 = new Question(6);
        answerDAO.insert(new Answer(question1,1));
        Question question2 = new Question(7);
        answerDAO.insert(new Answer(question2,1));
        answerMap = answerDAO.getByTestId(3);
        givenAnswerMap = answerDAO.getByTestIdGivenAnswers(3, 1);
        total = 0;
        count = 0;
        for (Integer question : givenAnswerMap.keySet()) {
            if (answerMap.get(question).getValue().equals(givenAnswerMap.get(question).getValue())) {
                count++;
            }
            total++;
        }
        System.out.println("Результат " + count + "/" + total);

    }
}
