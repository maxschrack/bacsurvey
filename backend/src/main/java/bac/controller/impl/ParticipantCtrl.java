package bac.controller.impl;

import bac.dto.DtoList;
import bac.dto.ParticipantDto;
import bac.dto.QuestionnaireDto;
import bac.exception.ServiceException;
import bac.rest.ParticipantRest;
import bac.service.ParticipantService;
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
@RequestMapping("/questionnaires/{questionnaireId}/participants")
@Api(value = "/participant", description = "Participant Administration")
public class ParticipantCtrl {

    @Autowired
    private ParticipantService participantService;

    public ParticipantCtrl(){
    }

    // CREATE
    @RequestMapping(method = RequestMethod.POST, value = "/newParticipant")
    @ApiOperation(value = "Create new Participant", notes = "")
    public ResponseEntity<ParticipantRest> createStartParticipant(@PathVariable Long questionnaireId, @RequestBody ParticipantRest participant, UriComponentsBuilder builder) throws ServiceException, InstantiationException, IllegalAccessException, HttpRequestMethodNotSupportedException {
        if (participantService == null)
            throw new HttpRequestMethodNotSupportedException("POST");

        ParticipantDto response = participantService.create(DtoFactory.toDto(participant));
        ParticipantRest newParticipant = ModelFactory.participant(response);

        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(
                builder.path("/questionnaires/{questionnaireId}/participant/{participantId}")
                        .buildAndExpand(questionnaireId, response.getId().toString()).toUri());
        headers.setContentType(MediaType.APPLICATION_JSON);
        return new ResponseEntity<>(newParticipant, headers, HttpStatus.CREATED);
    }

    // READ
    @RequestMapping(method = RequestMethod.GET, value = "/{participantId}")
    @ApiOperation(value = "Retrieve an Participant", notes = "")
    public ResponseEntity<ParticipantRest> read(@PathVariable Long questionnaireId, @PathVariable Long participantId) throws ServiceException, HttpRequestMethodNotSupportedException {
        if (participantService == null)
            throw new HttpRequestMethodNotSupportedException("GET");

        // retrieve participant from db
        ParticipantDto response = participantService.read(new ParticipantDto(participantId));
        ParticipantRest participant = ModelFactory.participant(response);

        // send response
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        return new ResponseEntity<>(participant, headers, HttpStatus.OK);
    }

    // READ ALL PER Questionnaire
    @RequestMapping(method = RequestMethod.GET)
    @ApiOperation(value = "Retrieve all Participants per Questionnaire", notes = "")
    public ResponseEntity<List<ParticipantRest>> read(@PathVariable Long questionnaireId) throws ServiceException, HttpRequestMethodNotSupportedException {
        if (participantService == null)
            throw new HttpRequestMethodNotSupportedException("GET");

        DtoList<ParticipantDto> response =
                participantService.readAllPerQuestionnaire(new QuestionnaireDto(questionnaireId));
        List<ParticipantRest> participants = new ArrayList<>();
        for(ParticipantDto dto : response){
            participants.add(ModelFactory.participant(dto));
        }

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        return new ResponseEntity<>(participants, headers, HttpStatus.OK);
    }
}
