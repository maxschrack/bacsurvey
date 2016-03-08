package bac.service;

import bac.dto.QuestionDto;
import bac.dto.QuestionnaireDto;
import bac.exception.ServiceException;
import bac.rest.analysis.AnalyzeResponseRest;

public interface AnalysisService {

    /**
     * Returns the amount of visitors per Page (Start Page, Question Pages, End Page
     * @param dto
     * @throws ServiceException
     */
    AnalyzeResponseRest getVisitsAndTimePerPage(QuestionnaireDto dto) throws ServiceException;

    /**
     * Returns the amount of Responses per Page
     * @param dto
     * @throws ServiceException
     */
    void getResponsesPerQuestion(QuestionnaireDto dto) throws ServiceException;

    /**
     * Returns all given Answers per Question
     * @param dto
     * @throws ServiceException
     */
    void getAllAnswersPerQuestion(QuestionDto dto) throws ServiceException;

}
