package bac.service;


import bac.dto.DtoList;
import bac.dto.ParticipantDto;
import bac.dto.QuestionnaireDto;
import bac.exception.ServiceException;

public interface ParticipantService {

    ParticipantDto create(ParticipantDto toCreate) throws ServiceException;

    ParticipantDto read(ParticipantDto toRead) throws ServiceException;

    DtoList<ParticipantDto> readAllPerQuestionnaire(QuestionnaireDto questionnaireDto);

}
