package bac.controller;

import bac.dto.QuestionDto;
import bac.dto.QuestionnaireDto;
import bac.exception.ServiceException;
import bac.rest.analysis.AnalyzeAnswerRest;
import bac.rest.analysis.AnalyzeResponseRest;
import bac.service.AnalysisService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/analysis")
@Api(value = "/analysis", description = "Analyze Questionnaire")
public class AnalysisCtrl {

    @Autowired
    private AnalysisService analysisService;

    public AnalysisCtrl(){

    }

    // READ ALL PER Questionnaire
    @RequestMapping(method = RequestMethod.GET, value = "/getResponseAnalysis/{id}")
    @ApiOperation(value = "Retrieve Response Analysis", notes = "")
    public ResponseEntity<AnalyzeResponseRest> getResponseAnalysis(@PathVariable Long id) throws ServiceException, HttpRequestMethodNotSupportedException {
        if (analysisService == null)
            throw new HttpRequestMethodNotSupportedException("GET");

        AnalyzeResponseRest response = analysisService.getVisitsAndTimePerPage(new QuestionnaireDto(id));

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        return new ResponseEntity<>(response, headers, HttpStatus.OK);
    }

    // READ ALL ANSWER PER Questionnaire
    @RequestMapping(method = RequestMethod.GET, value = "/getAnswerAnalysis/{id}")
    @ApiOperation(value = "Retrieve Answer Analysis", notes = "")
    public ResponseEntity<AnalyzeAnswerRest> getAnswerAnalysis(@PathVariable Long id) throws ServiceException, HttpRequestMethodNotSupportedException {
        if (analysisService == null)
            throw new HttpRequestMethodNotSupportedException("GET");

        AnalyzeAnswerRest response = analysisService.getAllAnswersPerQuestion(id);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        return new ResponseEntity<>(response, headers, HttpStatus.OK);
    }
}
