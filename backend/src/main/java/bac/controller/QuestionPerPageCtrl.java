package bac.controller;

import bac.dto.*;
import bac.exception.ServiceException;
import bac.model.MultipleChoice;
import bac.rest.MultipleChoiceAnswerRest;
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
@RequestMapping("questions")
@Api(value = "/questions", description = "Question Administration")
public class QuestionPerPageCtrl {

    @Autowired
    private QuestionService questionService;

    public QuestionPerPageCtrl(){
    }

    // CREATE OPEN QUESTION
    @RequestMapping(method = RequestMethod.POST, value = "/newOpenQuestion")
    @ApiOperation(value = "Create a new Open Question", notes = "")
    public ResponseEntity<QuestionRest> create(@RequestBody OpenQuestionRest openQuestion, UriComponentsBuilder builder) throws ServiceException, InstantiationException, IllegalAccessException, HttpRequestMethodNotSupportedException {
        if (questionService == null)
            throw new HttpRequestMethodNotSupportedException("POST");

        OpenQuestionDto response = questionService.createOpenQuestion(DtoFactory.toDto(openQuestion));
        QuestionRest newQuestion = ModelFactory.openQuestion(response);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        return new ResponseEntity<>(newQuestion, headers, HttpStatus.CREATED);
    }

    // CREATE MULTIPLE CHOICE QUESTION
    @RequestMapping(method = RequestMethod.POST, value = "/newMultipleChoiceQuestion")
    @ApiOperation(value = "Create a new Multiple Choice Question", notes = "")
    public ResponseEntity<QuestionRest> create(@RequestBody MultipleChoiceRest question, UriComponentsBuilder builder) throws ServiceException, InstantiationException, IllegalAccessException, HttpRequestMethodNotSupportedException {
        if (questionService == null)
            throw new HttpRequestMethodNotSupportedException("POST");

        MultipleChoiceDto temp = questionService.createMultipleChoiceQuestion(toDto(question));


        QuestionDto response = questionService.read(temp.getId());

        QuestionRest newQuestion = ModelFactory.multipleChoice((MultipleChoiceDto) response);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        return new ResponseEntity<>(newQuestion, headers, HttpStatus.CREATED);
    }

    private MultipleChoiceDto toDto(MultipleChoiceRest question){
        MultipleChoiceDto dto = new MultipleChoiceDto();
        dto.setText(question.getText());
        dto.setPageId(question.getPageId());
        dto.setMandatory(question.getMandatory());
        dto.setPosition(question.getPosition());
        dto.setIsSingleChoice(question.getIsSingleChoice());
        List<MultipleChoiceAnswerDto> answers = new ArrayList<>();
        for(MultipleChoiceAnswerRest answer : question.getAnswers()){
            MultipleChoiceAnswerDto answerDto = new MultipleChoiceAnswerDto();
            answerDto.setText(answer.getText());
            if(answer.getSelfId()!= null && answer.getSelfId() != 0L) {
                answerDto.setId(answer.getSelfId());
            }
            answers.add(answerDto);
        }
        dto.setAnswers(answers);
        return dto;
    }

    // READ
    /*@RequestMapping(method = RequestMethod.GET, value = "/{questionId}")
    @ApiOperation(value = "Retrieve an Question", notes = "")
    public ResponseEntity<QuestionRest> read(@PathVariable Long questionId) throws ServiceException, HttpRequestMethodNotSupportedException {
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
    }*/

    // UPDATE
    @RequestMapping(method = RequestMethod.PUT, value = "/updateOpenQuestion")
    @ApiOperation(value = "Update a Open Question", notes = "")
    public ResponseEntity<QuestionRest> updateOpenQuestion(@RequestBody OpenQuestionRest question, UriComponentsBuilder builder) throws ServiceException, InstantiationException, IllegalAccessException, HttpRequestMethodNotSupportedException {
        if (questionService == null)
            throw new HttpRequestMethodNotSupportedException("PUT");

        OpenQuestionDto toUpdate = DtoFactory.toDto(question);
        toUpdate.setId(question.getSelfId());
        OpenQuestionDto response = questionService.updateOpenQuestion(toUpdate);
        OpenQuestionRest updatedQuestion = ModelFactory.openQuestion(response);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        return new ResponseEntity<>(updatedQuestion, headers, HttpStatus.OK);
    }

    // UPDATE
    @RequestMapping(method = RequestMethod.PUT, value = "/updateMultipleChoiceQuestion")
    @ApiOperation(value = "Update a Multiple Choice Question", notes = "")
    public ResponseEntity<MultipleChoiceRest> updateOpenQuestion(@RequestBody MultipleChoiceRest question, UriComponentsBuilder builder) throws ServiceException, InstantiationException, IllegalAccessException, HttpRequestMethodNotSupportedException {
        if (questionService == null)
            throw new HttpRequestMethodNotSupportedException("PUT");

        MultipleChoiceDto toUpdate = toDto(question);
        toUpdate.setId(question.getSelfId());
        MultipleChoiceDto temp = questionService.updateMultipleChoiceQuestion(toUpdate);

        QuestionDto response = questionService.read(temp.getId());

        MultipleChoiceRest updatedQuestion = ModelFactory.multipleChoice((MultipleChoiceDto) response);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        return new ResponseEntity<>(updatedQuestion, headers, HttpStatus.OK);
    }


    @RequestMapping(method = RequestMethod.DELETE, value = "/delete/{questionId}")
    @ApiOperation(value = "Delete a Question", notes = "")
    public ResponseEntity<QuestionRest> delete(@PathVariable Long questionId) throws ServiceException, HttpRequestMethodNotSupportedException {
        if (questionService == null)
            throw new HttpRequestMethodNotSupportedException("DELETE");

        questionService.delete(questionId);


        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        return new ResponseEntity<>(headers, HttpStatus.OK);
    }


    // READ ALL PER Questionnaire
    @RequestMapping(method = RequestMethod.GET, value="/readAllPerPage/{pageId}")
    @ApiOperation(value = "Retrieve all Questions per Page", notes = "")
    public ResponseEntity<List<QuestionRest>> readAllPerPage(@PathVariable Long pageId) throws ServiceException, HttpRequestMethodNotSupportedException {
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
