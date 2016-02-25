package bac.service;

import bac.model.Page;
import bac.model.Question;
import bac.model.Questionnaire;
import bac.model.User;
import bac.repository.PageRepository;
import bac.repository.QuestionRepository;
import bac.repository.QuestionnaireRepository;
import bac.repository.UserRepository;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

/**
 * Created by max on 16/02/16.
 */
@RunWith(JUnit4.class)
public class QuestionServiceTest {

    UserRepository userRepository;
    QuestionnaireRepository questionnaireRepository;
    PageRepository pageRepository;
    QuestionRepository questionRepository;

    private static User user;
    private static Questionnaire questionnaire;
    private static Page page;
    private static Question openQuestion;
    private static Question multipleChoiceQuestion;

    @Before
    public void setUp() throws Exception {

    }
}
