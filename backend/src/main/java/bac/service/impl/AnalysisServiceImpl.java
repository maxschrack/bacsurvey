package bac.service.impl;

import bac.converter.AnswerConverter;
import bac.dto.QuestionnaireDto;
import bac.exception.ServiceException;
import bac.model.*;
import bac.model.enums.ELogType;
import bac.model.enums.EQuestionType;
import bac.repository.*;
import bac.rest.analysis.*;
import bac.service.AnalysisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.FileWriter;
import java.io.IOException;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

@Service
public class AnalysisServiceImpl implements AnalysisService{

    @Autowired
    private LogRepository logRepository;
    @Autowired
    private QuestionnaireRepository questionnaireRepository;
    @Autowired
    private PageRepository pageRepository;
    @Autowired
    private QuestionRepository questionRepository;
    @Autowired
    private AnswerRepository answerRepository;
    @Autowired
    private AnswerConverter answerConverter;

    @Override
    public AnalyzeResponseRest getVisitsAndTimePerPage(QuestionnaireDto dto) throws ServiceException {

        AnalyzeResponseRest response = new AnalyzeResponseRest();
        Questionnaire questionnaire = questionnaireRepository.findOne(dto.getId());

        getVisitsAndTimePerPage(questionnaire, response);

        getResponsePerQuestion(questionnaire, response);

        return response;
    }

    private void getResponsePerQuestion(Questionnaire questionnaire, AnalyzeResponseRest response) {


        // get all pages of the questionnaire
        List<Page> pages = pageRepository.findByQuestionnaire(questionnaire);

        // get all questions per page
        List<Question> questions = new ArrayList<>();
        for(Page p : pages){
            questions.addAll(questionRepository.findByPage(p));
        }

        List<ResponseStatisticRest> nonResponses = new ArrayList<>();
        List<ResponseStatisticRest> responses = new ArrayList<>();
        for(Question q : questions){
            ResponseStatisticRest restResponse = new ResponseStatisticRest();
            restResponse.setId(q.getId());
            restResponse.setText(q.getText());
            restResponse.setCounterNonResponse(answerRepository.countNonResponsesPerQuestion(q.getId()));
            restResponse.setCounterResponse(answerRepository.countResponsesPerQuestion(q.getId()));
            response.getQuestionResponses().add(restResponse);
            response.getAvgTime_question().add(logRepository.getAvgTimePerObject(q.getId(), ELogType.question.toString()));
        }
    }

    private void getVisitsAndTimePerPage(Questionnaire questionnaire, AnalyzeResponseRest response){
        // Get Visits and Time Per Start Page
        response.setStartPageVisits(logRepository.getVisitsPerPage(questionnaire.getStartPage().getId(), ELogType.startpage));


        Double avgVisitTime_startPage = logRepository.getAvgTimePerObject(questionnaire.getStartPage().getId(), ELogType.startpage.toString());
        if(avgVisitTime_startPage != null)
            response.setAvgVisitTime_startPage(avgVisitTime_startPage);
        else
            response.setAvgVisitTime_startPage(0.0);

        // Get Visits per Page
        //ArrayList<Long> visits_pages = new ArrayList<>();
        //ArrayList<Integer> avgVisitTime_pages = new ArrayList<>();
        for(Page page : questionnaire.getPages()){
            response.getPageVisits().add(logRepository.getVisitsPerPage(page.getId(), ELogType.page));

            Double temp = logRepository.getAvgTimePerObject(page.getId(), ELogType.page.toString());
            if(temp != null)
                response.getAvgVisitTime_pages().add(temp);
            else
                response.getAvgVisitTime_pages().add(0.0);
        }

        // Get Visits and Time Per End Page
        response.setEndPageVisits(logRepository.getVisitsPerPage(questionnaire.getEndPage().getId(), ELogType.endpage));

    }


    public void getResponsesPerQuestion(QuestionnaireDto dto) throws ServiceException {

    }

    @Override
    public AnalyzeAnswerRest getAllAnswersPerQuestion(Long id) throws ServiceException {

        Question question = questionRepository.findOne(id);

        if(question instanceof OpenQuestion){
            AnalyzeOpenQuestionRest rest = new AnalyzeOpenQuestionRest();
            rest.setType(EQuestionType.oq);

            List<Answer> temp = answerRepository.getAnswersNotNullPerQuestion(question.getId());
            for(Answer answer : temp){
                rest.getAnswers().add(answer.getAnswer());
            }
            return rest;
        }else if(question instanceof MultipleChoice){
            AnalyzeMultipleChoiceRest rest = new AnalyzeMultipleChoiceRest();
            rest.setType(EQuestionType.mc);
            for(MultipleChoiceAnswer answer : ((MultipleChoice) question).getAnswers()){
                AnswerCounterRest ac = new AnswerCounterRest();

                Long counter = answerRepository.getAnswerCounter(question.getId(), answer.getText());

                ac.setAnswer(answer.getText());
                ac.setCounter(counter);

                rest.getAnswers().add(ac);
            }
            // Search for empty answers
            AnswerCounterRest ac = new AnswerCounterRest();
            Long counter = answerRepository.getAnswerCounter(question.getId(), "");
            ac.setAnswer("");
            ac.setCounter(counter);
            rest.getAnswers().add(ac);

            return rest;
        }
        return null;
    }

    @Override
    public void generateResponseReport(Long questionnaireId) {

        FileWriter writer = null;

        try {
            writer = new FileWriter("test.csv");

            writer.append("DisplayName");
            writer.append(',');
            writer.append("Age");
            writer.append('\n');

            writer.append("MKYONG");
            writer.append(',');
            writer.append("26");
            writer.append('\n');

            writer.append("YOUR NAME");
            writer.append(',');
            writer.append("29");
            writer.append('\n');

            //generate whatever data you want

            writer.flush();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
