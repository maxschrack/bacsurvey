package bac.controller.impl;

import bac.dto.DtoList;
import bac.dto.QuestionnaireDto;
import bac.dto.UserDto;
import bac.exception.ServiceException;
import bac.repository.QuestionnaireRepository;
import bac.rest.QuestionnaireRest;
import bac.service.QuestionnaireService;
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
import java.util.stream.Collectors;


@RestController
@RequestMapping("/users/{userId}/questionnaires")
@Api(value = "/questionnaires", description = "Questionnaire Administration")
public class QuestionnairePerUserController {

    @Autowired
    private QuestionnaireService questionnaireService;

    public QuestionnairePerUserController(){
    }

    // CREATE
    @RequestMapping(method = RequestMethod.POST)
    @ApiOperation(value = "Create a new Questionnaire", notes = "")
    public ResponseEntity<QuestionnaireRest> create(@PathVariable Long userId, @RequestBody QuestionnaireRest questionnaire, UriComponentsBuilder builder) throws ServiceException, InstantiationException, IllegalAccessException, HttpRequestMethodNotSupportedException {
        if (questionnaireService == null)
            throw new HttpRequestMethodNotSupportedException("POST");

        QuestionnaireDto response = questionnaireService.create(DtoFactory.toDto(questionnaire));
        QuestionnaireRest newQuestionnaire = ModelFactory.questionnaire(response);

        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(
                builder.path("/users/{userId}/questionnaires/{questionnaireId}")
                        .buildAndExpand(userId, response.getId().toString()).toUri());
        headers.setContentType(MediaType.APPLICATION_JSON);
        return new ResponseEntity<>(newQuestionnaire, headers, HttpStatus.CREATED);
    }

    // READ
    @RequestMapping(method = RequestMethod.GET, value = "/{questionnaireId}")
    @ApiOperation(value = "Retrieve an Questionnaire", notes = "")
    public ResponseEntity<QuestionnaireRest> read(@PathVariable Long userId, @PathVariable Long questionnaireId) throws ServiceException, HttpRequestMethodNotSupportedException {
        if (questionnaireService == null)
            throw new HttpRequestMethodNotSupportedException("GET");

        // retrieve questionnaire from db
        QuestionnaireDto response = questionnaireService.read(new QuestionnaireDto(questionnaireId));
        QuestionnaireRest questionnaire = ModelFactory.questionnaire(response);

        // send response
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        return new ResponseEntity<>(questionnaire, headers, HttpStatus.OK);
    }

    // UPDATE
    @RequestMapping(method = RequestMethod.PUT, value = "/{questionnaireId}")
    @ApiOperation(value = "Update a Questionnaire", notes = "")
    public ResponseEntity<QuestionnaireRest> update(@PathVariable Long userId, @PathVariable Long questionnaireId, @RequestBody QuestionnaireRest questionnaire, UriComponentsBuilder builder) throws ServiceException, InstantiationException, IllegalAccessException, HttpRequestMethodNotSupportedException {
        if (questionnaireService == null)
            throw new HttpRequestMethodNotSupportedException("PUT");

        QuestionnaireDto toUpdate = DtoFactory.toDto(questionnaire);
        toUpdate.setId(questionnaireId);
        QuestionnaireDto response = questionnaireService.update(toUpdate);
        QuestionnaireRest updatedQuestionnaire = ModelFactory.questionnaire(response);

        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(
                builder.path("/questionnaires/{questionnaireId}")
                        .buildAndExpand(response.getId().toString()).toUri());
        headers.setContentType(MediaType.APPLICATION_JSON);
        return new ResponseEntity<>(updatedQuestionnaire, headers, HttpStatus.OK);
    }

    // DELETE
    @RequestMapping(method = RequestMethod.DELETE, value = "/{questionnaireId}")
    @ApiOperation(value = "Delete a Questionnaire", notes = "")
    public ResponseEntity<QuestionnaireRest> delete(@PathVariable Long userId, @PathVariable Long questionnaireId) throws ServiceException, HttpRequestMethodNotSupportedException {
        if (questionnaireService == null)
            throw new HttpRequestMethodNotSupportedException("DELETE");

        QuestionnaireDto response = questionnaireService.delete(new QuestionnaireDto(questionnaireId));
        QuestionnaireRest c = ModelFactory.questionnaire(response);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        return new ResponseEntity<>(c, headers, HttpStatus.OK);
    }

    // READ ALL PER USER
    // READ
    @RequestMapping(method = RequestMethod.GET)
    @ApiOperation(value = "Retrieve all Questionnaires per User", notes = "")
    public ResponseEntity<List<QuestionnaireRest>> read(@PathVariable Long userId) throws ServiceException, HttpRequestMethodNotSupportedException {
        if (questionnaireService == null)
            throw new HttpRequestMethodNotSupportedException("GET");

        DtoList<QuestionnaireDto> response =
                questionnaireService.readAllPerUser(new UserDto(userId));
        List<QuestionnaireRest> questionnaires = new ArrayList<>();
        for(QuestionnaireDto dto : response){
            questionnaires.add(ModelFactory.questionnaire(dto));
        }

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        return new ResponseEntity<>(questionnaires, headers, HttpStatus.OK);
    }
}

