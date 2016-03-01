package bac.controller;

import bac.dto.MetaPageDto;
import bac.dto.QuestionnaireDto;
import bac.exception.ServiceException;
import bac.rest.MetaPageRest;
import bac.service.MetaPageService;
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

@RestController
@RequestMapping("/metaPages")
@Api(value = "/metaPages", description = "MetaPage Administration")
public class MetaPageCtrl {

    @Autowired
    private MetaPageService metaPageService;


    public MetaPageCtrl() {
    }

    // CREATE
    @RequestMapping(method = RequestMethod.POST, value = "/newStartPage")
    @ApiOperation(value = "Create new StartPage", notes = "")
    public ResponseEntity<MetaPageRest> createStartPage(@RequestBody MetaPageRest page, UriComponentsBuilder builder) throws ServiceException, InstantiationException, IllegalAccessException, HttpRequestMethodNotSupportedException {
        if (metaPageService == null)
            throw new HttpRequestMethodNotSupportedException("POST");

        MetaPageDto response = metaPageService.createStartPage(DtoFactory.toDto(page));
        MetaPageRest newPage = ModelFactory.metaPage(response);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        return new ResponseEntity<>(newPage, headers, HttpStatus.CREATED);
    }

    // CREATE
    @RequestMapping(method = RequestMethod.POST, value = "/newEndPage")
    @ApiOperation(value = "Create new EndPage", notes = "")
    public ResponseEntity<MetaPageRest> createEndPage(@RequestBody MetaPageRest page, UriComponentsBuilder builder) throws ServiceException, InstantiationException, IllegalAccessException, HttpRequestMethodNotSupportedException {
        if (metaPageService == null)
            throw new HttpRequestMethodNotSupportedException("POST");

        MetaPageDto response = metaPageService.createEndPage(DtoFactory.toDto(page));
        MetaPageRest newPage = ModelFactory.metaPage(response);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        return new ResponseEntity<>(newPage, headers, HttpStatus.CREATED);
    }

    // READ
    @RequestMapping(method = RequestMethod.GET, value = "/getStartPage/{questionnaireId}")
    @ApiOperation(value = "Retrieve the Questionnaire's StartPage", notes = "")
    public ResponseEntity<MetaPageRest> readStartPageByQuestionnaire(@PathVariable Long questionnaireId) throws ServiceException, HttpRequestMethodNotSupportedException {
        if (metaPageService == null)
            throw new HttpRequestMethodNotSupportedException("GET");

        // retrieve page from db
        MetaPageDto response = metaPageService.readStartPagePerQuestionnaire(new QuestionnaireDto(questionnaireId));
        MetaPageRest page = null;
        if(response != null)
            page = ModelFactory.metaPage(response);

        // send response
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        return new ResponseEntity<>(page, headers, HttpStatus.OK);
    }

    // READ
    @RequestMapping(method = RequestMethod.GET, value = "/getEndPage/{questionnaireId}")
    @ApiOperation(value = "Retrieve the Questionnaire's EndPage", notes = "")
    public ResponseEntity<MetaPageRest> readEndPageByQuestionnaire(@PathVariable Long questionnaireId) throws ServiceException, HttpRequestMethodNotSupportedException {
        if (metaPageService == null)
            throw new HttpRequestMethodNotSupportedException("GET");

        // retrieve page from db
        MetaPageDto response = metaPageService.readEndPagePerQuestionnaire(new QuestionnaireDto(questionnaireId));
        MetaPageRest page = null;
        if(response != null)
            page = ModelFactory.metaPage(response);

        // send response
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        return new ResponseEntity<>(page, headers, HttpStatus.OK);
    }

    // UPDATE
    @RequestMapping(method = RequestMethod.PUT, value = "/updatePage")
    @ApiOperation(value = "Update a Page", notes = "")
    public ResponseEntity<MetaPageRest> update(@RequestBody MetaPageRest page, UriComponentsBuilder builder) throws ServiceException, InstantiationException, IllegalAccessException, HttpRequestMethodNotSupportedException {
        if (metaPageService == null)
            throw new HttpRequestMethodNotSupportedException("PUT");

        MetaPageDto toUpdate = DtoFactory.toDto(page);
        toUpdate.setId(page.getSelfId());
        MetaPageDto response = metaPageService.updateMetaPage(toUpdate);
        MetaPageRest updatedPage = ModelFactory.metaPage(response);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        return new ResponseEntity<>(updatedPage, headers, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/deletePage/{pageId}")
    @ApiOperation(value = "Delete a Page", notes = "")
    public ResponseEntity<MetaPageRest> delete(@PathVariable Long pageId) throws ServiceException, HttpRequestMethodNotSupportedException {
        if (metaPageService == null)
            throw new HttpRequestMethodNotSupportedException("DELETE");

        MetaPageDto response = metaPageService.deleteMetaPage(new MetaPageDto(pageId));
        MetaPageRest c = ModelFactory.metaPage(response);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        return new ResponseEntity<>(c, headers, HttpStatus.OK);
    }
}
