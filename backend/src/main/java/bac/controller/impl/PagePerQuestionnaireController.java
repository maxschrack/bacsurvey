package bac.controller.impl;

import bac.dto.DtoList;
import bac.dto.PageDto;
import bac.dto.QuestionnaireDto;
import bac.exception.ServiceException;
import bac.rest.PageRest;
import bac.service.PageService;
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
@RequestMapping("/users/{userId}/questionnaires/{questionnaireId}/pages")
@Api(value = "/pages", description = "Page Administration")
public class PagePerQuestionnaireController {

    @Autowired
    private PageService pageService;


    public PagePerQuestionnaireController(){
    }

    // CREATE
    @RequestMapping(method = RequestMethod.POST)
    @ApiOperation(value = "Create a new Page", notes = "")
    public ResponseEntity<PageRest> create(@PathVariable Long userId, @PathVariable Long questionnaireId, @RequestBody PageRest page, UriComponentsBuilder builder) throws ServiceException, InstantiationException, IllegalAccessException, HttpRequestMethodNotSupportedException {
        if (pageService == null)
            throw new HttpRequestMethodNotSupportedException("POST");

        PageDto response = pageService.create(DtoFactory.toDto(page));
        PageRest newPage = ModelFactory.page(response);

        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(
                builder.path("/users/{userId}/questionnaires/{questionnaireId}/pages/{pageId}")
                        .buildAndExpand(userId, questionnaireId, response.getId().toString()).toUri());
        headers.setContentType(MediaType.APPLICATION_JSON);
        return new ResponseEntity<>(newPage, headers, HttpStatus.CREATED);
    }

    // READ
    @RequestMapping(method = RequestMethod.GET, value = "/{pageId}")
    @ApiOperation(value = "Retrieve an Page", notes = "")
    public ResponseEntity<PageRest> read(@PathVariable Long userId, @PathVariable Long questionnaireId, @PathVariable Long pageId) throws ServiceException, HttpRequestMethodNotSupportedException {
        if (pageService == null)
            throw new HttpRequestMethodNotSupportedException("GET");

        // retrieve page from db
        PageDto response = pageService.read(new PageDto(pageId));
        PageRest page = ModelFactory.page(response);

        // send response
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        return new ResponseEntity<>(page, headers, HttpStatus.OK);
    }

    // UPDATE
    @RequestMapping(method = RequestMethod.PUT, value = "/{pageId}")
    @ApiOperation(value = "Update a Page", notes = "")
    public ResponseEntity<PageRest> update(@PathVariable Long userId, @PathVariable Long questionnaireId, @PathVariable Long pageId, @RequestBody PageRest page, UriComponentsBuilder builder) throws ServiceException, InstantiationException, IllegalAccessException, HttpRequestMethodNotSupportedException {
        if (pageService == null)
            throw new HttpRequestMethodNotSupportedException("PUT");

        PageDto toUpdate = DtoFactory.toDto(page);
        toUpdate.setId(pageId);
        PageDto response = pageService.update(toUpdate);
        PageRest updatedPage = ModelFactory.page(response);

        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(
                builder.path("/users/{userId}/questionnaires/{questionnaireId}/pages/{pageId}")
                        .buildAndExpand(userId, questionnaireId, response.getId().toString()).toUri());
        headers.setContentType(MediaType.APPLICATION_JSON);
        return new ResponseEntity<>(updatedPage, headers, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/{pageId}")
    @ApiOperation(value = "Delete a Page", notes = "")
    public ResponseEntity<PageRest> delete(@PathVariable Long userId, @PathVariable Long questionnaireId, @PathVariable Long pageId) throws ServiceException, HttpRequestMethodNotSupportedException {
        if (pageService == null)
            throw new HttpRequestMethodNotSupportedException("DELETE");

        PageDto response = pageService.delete(new PageDto(pageId));
        PageRest c = ModelFactory.page(response);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        return new ResponseEntity<>(c, headers, HttpStatus.OK);
    }

    // READ ALL PER Questionnaire
    @RequestMapping(method = RequestMethod.GET)
    @ApiOperation(value = "Retrieve all Pages per Questionnaire", notes = "")
    public ResponseEntity<List<PageRest>> read(@PathVariable Long userId, @PathVariable Long questionnaireId) throws ServiceException, HttpRequestMethodNotSupportedException {
        if (pageService == null)
            throw new HttpRequestMethodNotSupportedException("GET");

        DtoList<PageDto> response =
                pageService.readAllPerQuestionnaire(new QuestionnaireDto(questionnaireId));
        List<PageRest> pages = new ArrayList<>();
        for(PageDto dto : response){
            pages.add(ModelFactory.page(dto));
        }

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        return new ResponseEntity<>(pages, headers, HttpStatus.OK);
    }
}
