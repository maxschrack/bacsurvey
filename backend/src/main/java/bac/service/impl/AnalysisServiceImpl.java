package bac.service.impl;

import bac.converter.AnswerConverter;
import bac.dto.QuestionDto;
import bac.dto.QuestionnaireDto;
import bac.exception.ServiceException;
import bac.model.Page;
import bac.model.Question;
import bac.model.Questionnaire;
import bac.model.enums.ELogType;
import bac.repository.*;
import bac.rest.analysis.AnalyzeResponseRest;
import bac.rest.analysis.ResponseStatisticRest;
import bac.service.AnalysisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
        }
    }

    private void getVisitsAndTimePerPage(Questionnaire questionnaire, AnalyzeResponseRest response){
        // Get Visits and Time Per Start Page
        response.setStartPageVisits(logRepository.getVisitsPerPage(questionnaire.getStartPage().getId(), ELogType.startpage));


        Integer avgVisitTime_startPage = logRepository.getTimePerPage(questionnaire.getStartPage().getId(), ELogType.startpage.toString());
        if(avgVisitTime_startPage != null)
            response.setAvgVisitTime_startPage(avgVisitTime_startPage);
        else
            response.setAvgVisitTime_startPage(0);

        // Get Visits per Page
        //ArrayList<Long> visits_pages = new ArrayList<>();
        //ArrayList<Integer> avgVisitTime_pages = new ArrayList<>();
        for(Page page : questionnaire.getPages()){
            response.getPageVisits().add(logRepository.getVisitsPerPage(page.getId(), ELogType.page));

            Integer temp = logRepository.getTimePerPage(page.getId(), ELogType.page.toString());
            if(temp != null)
                response.getAvgVisitTime_pages().add(temp);
            else
                response.getAvgVisitTime_pages().add(0);
        }

        // Get Visits and Time Per End Page
        response.setEndPageVisits(logRepository.getVisitsPerPage(questionnaire.getEndPage().getId(), ELogType.endpage));

    }

    @Override
    public void getResponsesPerQuestion(QuestionnaireDto dto) throws ServiceException {

    }

    @Override
    public void getAllAnswersPerQuestion(QuestionDto dto) throws ServiceException {

    }
}
