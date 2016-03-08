package bac.controller;

import bac.dto.DtoList;
import bac.dto.LogDto;
import bac.exception.ServiceException;
import bac.model.enums.ELogType;
import bac.rest.LogRest;
import bac.service.LogService;
import bac.util.DtoFactory;
import bac.util.ModelFactory;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/logs/")
@Api(value = "/logs", description = "Log Administration")
public class LogCtrl {

    @Autowired
    private LogService logService;

    public LogCtrl() {
    }

    // CREATE
    @RequestMapping(method = RequestMethod.POST, value="/newLog")
    @ApiOperation(value = "Create new Log", notes = "")
    public ResponseEntity<LogRest> create(@RequestBody LogRest log, UriComponentsBuilder builder) throws ServiceException, InstantiationException, IllegalAccessException, HttpRequestMethodNotSupportedException {
        if (logService == null)
            throw new HttpRequestMethodNotSupportedException("POST");

        LogDto response = logService.create(DtoFactory.toDto(log));
        LogRest newLog = ModelFactory.log(response);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        return new ResponseEntity<>(newLog, headers, HttpStatus.CREATED);
    }

    // UPDATE
    @RequestMapping(method = RequestMethod.PUT, value = "/updateLog")
    @ApiOperation(value = "Update a Log", notes = "")
    public ResponseEntity<LogRest> update(@RequestBody LogRest log, UriComponentsBuilder builder) throws ServiceException, InstantiationException, IllegalAccessException, HttpRequestMethodNotSupportedException {
        if (logService == null)
            throw new HttpRequestMethodNotSupportedException("PUT");

        LogDto toUpdate = DtoFactory.toDto(log);
        toUpdate.setId(log.getSelfId());
        LogDto response = logService.update(toUpdate);
        LogRest updatedPage = ModelFactory.log(response);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        return new ResponseEntity<>(updatedPage, headers, HttpStatus.OK);
    }

    // READ ALL PER Questionnaire
    @RequestMapping(method = RequestMethod.GET, value = "/getAllPerObject/{objectId}/{type}")
    @ApiOperation(value = "Retrieve all Logs per ObjectType", notes = "")
    public ResponseEntity<List<LogRest>> readAllLogsPerQuestion(@PathVariable Long objectId, @PathVariable ELogType type) throws ServiceException, HttpRequestMethodNotSupportedException {
        if (logService == null)
            throw new HttpRequestMethodNotSupportedException("GET");

        DtoList<LogDto> response =
                logService.readAllLogsPerObject(objectId, type);
        List<LogRest> logs = new ArrayList<>();
        for(LogDto dto : response){
            logs.add(ModelFactory.log(dto));
        }

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        return new ResponseEntity<>(logs, headers, HttpStatus.OK);
    }
}
