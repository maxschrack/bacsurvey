package bac.service;

import bac.dto.QuestionDto;
import bac.dto.QuestionnaireDto;
import bac.exception.ServiceException;
import bac.rest.analysis.AnalyzeAnswerRest;
import bac.rest.analysis.AnalyzeResponseRest;
import bac.rest.analysis.ProcessModelRest;
import bac.rest.analysis.ProcessRest;

import java.util.List;

public interface AnalysisService {

    /**
     * Returns the amount of visitors per Page (Start Page, Question Pages, End Page
     * @param dto
     * @throws ServiceException
     */
    AnalyzeResponseRest getVisitsAndTimePerPage(QuestionnaireDto dto) throws ServiceException;

    AnalyzeAnswerRest getAllAnswersPerQuestion(Long id) throws ServiceException;

    void generateResponseReport(Long questionnaireId);

    void generateAnswerReport(Long questionnaireId);

    ProcessRest getProcessModel(Long questionnaireId);

}
