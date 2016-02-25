package bac.service.impl;

import bac.converter.ParticipantConverter;
import bac.dto.DtoList;
import bac.dto.ParticipantDto;
import bac.dto.QuestionnaireDto;
import bac.exception.ServiceException;
import bac.model.Participant;
import bac.model.Questionnaire;
import bac.repository.ParticipantRepository;
import bac.service.ParticipantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class ParticipantServiceImpl implements ParticipantService{

    @Autowired
    private ParticipantRepository participantRepository;
    @Autowired
    private ParticipantConverter participantConverter;

    public ParticipantServiceImpl() {
    }

    @Override
    public ParticipantDto create(ParticipantDto toCreate) throws ServiceException {
        if(toCreate == null)
            throw new IllegalArgumentException("Illegal Argument: Null");

        // validate
        // TODO : VALIDATION

        // set Registration date
        toCreate.setCreationDate(new Date());

        // convert
        Participant participant = participantConverter.toEntity(toCreate);

        // create
        participant = participantRepository.save(participant);

        // convert back and return
        return participantConverter.toDto(participant);
    }

    @Override
    public ParticipantDto read(ParticipantDto toRead) throws ServiceException {

        // validate

        // search
        Participant participant = participantRepository.findOne(toRead.getId());

        // convert and return
        return participantConverter.toDto(participant);
    }

    @Override
    public DtoList<ParticipantDto> readAllPerQuestionnaire(QuestionnaireDto questionnaireDto) {

        // validate

        // search
        Questionnaire finder = new Questionnaire();
        finder.setId(questionnaireDto.getId());
        List<Participant> result = participantRepository.findByQuestionnaire(finder);

        // convert and return
        return participantConverter.toDtoList(result);
    }
}
