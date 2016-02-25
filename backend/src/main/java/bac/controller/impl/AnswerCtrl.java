package bac.controller.impl;

import bac.dto.AnswerDto;
import bac.dto.DtoList;
import bac.exception.ServiceException;
import bac.rest.AnswerRest;
import bac.service.AnswerService;
import bac.util.DtoFactory;
import bac.util.ModelFactory;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/questionnaires/{questionnaireId}")
@Api(value = "/answers", description = "Answer Administration")
public class AnswerCtrl {

    @Autowired
    private AnswerService answerService;

    public AnswerCtrl() {
    }

    // CREATE
    @RequestMapping(method = RequestMethod.POST, value = "/participants/{participantId}/{questionId}")
    @ApiOperation(value = "Create new Answer", notes = "")
    public ResponseEntity<AnswerRest> create(@PathVariable Long questionnaireId, @PathVariable Long participantId, @PathVariable Long questionId, @RequestBody AnswerRest answer, UriComponentsBuilder builder) throws ServiceException, InstantiationException, IllegalAccessException, HttpRequestMethodNotSupportedException {
        if (answerService == null)
            throw new HttpRequestMethodNotSupportedException("POST");

        AnswerDto response = answerService.create(DtoFactory.toDto(answer));
        AnswerRest newAnswer = ModelFactory.answer(response);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        return new ResponseEntity<>(newAnswer, headers, HttpStatus.CREATED);
    }

    // READ ALL PER Questionnaire
    @RequestMapping(method = RequestMethod.GET, value="/questions/{questionId}/getAllAnswers")
    @ApiOperation(value = "Retrieve all Answers per Question", notes = "")
    public ResponseEntity<List<AnswerRest>> readAllAnswersPerQuestion(@PathVariable Long questionnaireId, @PathVariable Long questionId) throws ServiceException, HttpRequestMethodNotSupportedException {
        if (answerService == null)
            throw new HttpRequestMethodNotSupportedException("GET");

        DtoList<AnswerDto> response =
                answerService.readAllAnswersPerQuestion(questionId);
        List<AnswerRest> answers = new ArrayList<>();
        for(AnswerDto dto : response){
            answers.add(ModelFactory.answer(dto));
        }

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        return new ResponseEntity<>(answers, headers, HttpStatus.OK);
    }

    // READ ALL PER Questionnaire
    @RequestMapping(method = RequestMethod.GET, value="/participant/{participantId}/getAllAnswers")
    @ApiOperation(value = "Retrieve all Answers per Participant", notes = "")
    public ResponseEntity<List<AnswerRest>> readAllAnswersPerParticipant(@PathVariable Long questionnaireId, @PathVariable Long participantId) throws ServiceException, HttpRequestMethodNotSupportedException {
        if (answerService == null)
            throw new HttpRequestMethodNotSupportedException("GET");

        DtoList<AnswerDto> response =
                answerService.readAllAnswersPerParticipant(participantId);
        List<AnswerRest> answers = new ArrayList<>();
        for(AnswerDto dto : response){
            answers.add(ModelFactory.answer(dto));
        }

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        return new ResponseEntity<>(answers, headers, HttpStatus.OK);
    }
}
