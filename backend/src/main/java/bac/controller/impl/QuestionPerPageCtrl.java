package bac.controller.impl;

import bac.dto.*;
import bac.exception.ServiceException;
import bac.model.MultipleChoice;
import bac.rest.MultipleChoiceRest;
import bac.rest.OpenQuestionRest;
import bac.rest.QuestionRest;
import bac.service.QuestionService;
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
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/users/{userId}/questionnaires/{questionnaireId}/pages/{pageId}/questions")
@Api(value = "/questions", description = "Question Administration")
public class QuestionPerPageCtrl {

    @Autowired
    private QuestionService questionService;

    public QuestionPerPageCtrl(){
    }

    // CREATE OPEN QUESTION
    @RequestMapping(method = RequestMethod.POST, value = "/newOpenQuestion")
    @ApiOperation(value = "Create a new Open Question", notes = "")
    public ResponseEntity<QuestionRest> create(@PathVariable Long userId, @PathVariable Long questionnaireId, @PathVariable Long pageId, @RequestBody OpenQuestionRest openQuestion, UriComponentsBuilder builder) throws ServiceException, InstantiationException, IllegalAccessException, HttpRequestMethodNotSupportedException {
        if (questionService == null)
            throw new HttpRequestMethodNotSupportedException("POST");

        OpenQuestionDto response = questionService.createOpenQuestion(DtoFactory.toDto(openQuestion));
        QuestionRest newQuestion = ModelFactory.openQuestion(response);

        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(
                builder.path("/users/{userId}/questionnaires/{questionnaireId}/pages/{pageId}/question/{questionId}")
                        .buildAndExpand(userId, questionnaireId, pageId, response.getId().toString()).toUri());
        headers.setContentType(MediaType.APPLICATION_JSON);
        return new ResponseEntity<>(newQuestion, headers, HttpStatus.CREATED);
    }

    // CREATE MULTIPLE CHOICE QUESTION
    @RequestMapping(method = RequestMethod.POST, value = "/newMultipleChoiceQuestion")
    @ApiOperation(value = "Create a new Multiple Choice Question", notes = "")
    public ResponseEntity<QuestionRest> create(@PathVariable Long userId, @PathVariable Long questionnaireId, @PathVariable Long pageId, @RequestBody MultipleChoiceRest question, UriComponentsBuilder builder) throws ServiceException, InstantiationException, IllegalAccessException, HttpRequestMethodNotSupportedException {
        if (questionService == null)
            throw new HttpRequestMethodNotSupportedException("POST");

        MultipleChoiceDto temp = questionService.createMultipleChoiceQuestion(DtoFactory.toDto(question));


        QuestionDto response = questionService.read(temp.getId());

        QuestionRest newQuestion = ModelFactory.multipleChoice((MultipleChoiceDto) response);

        HttpHeaders headers = new HttpHeaders();
       /* headers.setLocation(
                builder.path("/users/{userId}/questionnaires/{questionnaireId}/pages/{pageId}/question/{questionId}")
                        .buildAndExpand(userId, questionnaireId, pageId, response.getId().toString()).toUri());*/
        headers.setContentType(MediaType.APPLICATION_JSON);
        return new ResponseEntity<>(newQuestion, headers, HttpStatus.CREATED);
    }

    // READ
    @RequestMapping(method = RequestMethod.GET, value = "/{questionId}")
    @ApiOperation(value = "Retrieve an Question", notes = "")
    public ResponseEntity<QuestionRest> read(@PathVariable Long userId, @PathVariable Long questionnaireId, @PathVariable Long pageId, @PathVariable Long questionId) throws ServiceException, HttpRequestMethodNotSupportedException {
        if (questionService == null)
            throw new HttpRequestMethodNotSupportedException("GET");

        // retrieve question from db
        QuestionDto response = questionService.read(questionId);

        QuestionRest question;

        if(response instanceof OpenQuestionDto) {
            question = ModelFactory.openQuestion((OpenQuestionDto)response);
        }else{
            question = ModelFactory.multipleChoice((MultipleChoiceDto) response);
        }
        // send response
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        return new ResponseEntity<>(question, headers, HttpStatus.OK);
    }

/*    // UPDATE
    @RequestMapping(method = RequestMethod.PUT, value = "/{questionId}")
    @ApiOperation(value = "Update a Question", notes = "")
    public ResponseEntity<QuestionRest> update(@PathVariable Long userId, @PathVariable Long questionnaireId, @PathVariable Long pageId, @PathVariable Long questionId, @RequestBody QuestionRest question, UriComponentsBuilder builder) throws ServiceException, InstantiationException, IllegalAccessException, HttpRequestMethodNotSupportedException {
        if (questionService == null)
            throw new HttpRequestMethodNotSupportedException("PUT");

        QuestionDto toUpdate = DtoFactory.toDto(question);
        toUpdate.setId(questionId);
        QuestionDto response = questionService.update(toUpdate);
        QuestionRest updatedQuestion = ModelFactory.question(response);

        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(
                builder.path("/users/{userId}/questionnaires/{questionnaireId}/pages/{pageId}/question/{questionId}\"")
                        .buildAndExpand(userId, questionnaireId, response.getId().toString()).toUri());
        headers.setContentType(MediaType.APPLICATION_JSON);
        return new ResponseEntity<>(updatedQuestion, headers, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/{questionId}")
    @ApiOperation(value = "Delete a Question", notes = "")
    public ResponseEntity<QuestionRest> delete(@PathVariable Long userId, @PathVariable Long questionnaireId, @PathVariable Long pageId, @PathVariable Long questionId) throws ServiceException, HttpRequestMethodNotSupportedException {
        if (questionService == null)
            throw new HttpRequestMethodNotSupportedException("DELETE");

        QuestionDto response = questionService.delete(new QuestionDto(questionId));
        QuestionRest c = ModelFactory.question(response);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        return new ResponseEntity<>(c, headers, HttpStatus.OK);
    }
    */

    // READ ALL PER Questionnaire
    @RequestMapping(method = RequestMethod.GET)
    @ApiOperation(value = "Retrieve all Questions per Page", notes = "")
    public ResponseEntity<List<QuestionRest>> read(@PathVariable Long userId, @PathVariable Long questionnaireId, @PathVariable Long pageId) throws ServiceException, HttpRequestMethodNotSupportedException {
        if (questionService == null)
            throw new HttpRequestMethodNotSupportedException("GET");

        DtoList<QuestionDto> response =
                questionService.readAllPerPage(new PageDto(pageId));
        List<QuestionRest> questions = new ArrayList<>();
        for(QuestionDto dto : response){
            if(dto instanceof OpenQuestionDto){
                questions.add(ModelFactory.openQuestion((OpenQuestionDto) dto));
            }else if(dto instanceof MultipleChoiceDto){
                questions.add(ModelFactory.multipleChoice((MultipleChoiceDto) dto));
            }
        }

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        return new ResponseEntity<>(questions, headers, HttpStatus.OK);
    }

}
