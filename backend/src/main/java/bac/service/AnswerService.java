package bac.service;

import bac.dto.AnswerDto;
import bac.dto.DtoList;
import bac.dto.ParticipantDto;
import bac.dto.QuestionDto;
import bac.exception.ServiceException;

public interface AnswerService {
    AnswerDto create(AnswerDto toCreate) throws ServiceException;
    AnswerDto read(AnswerDto toRead) throws ServiceException;
    AnswerDto update(AnswerDto toUpdate) throws ServiceException;
    AnswerDto delete(AnswerDto toDelete) throws ServiceException;

    DtoList<AnswerDto> readAllAnswersPerQuestion(Long questionId) throws ServiceException;
    DtoList<AnswerDto> readAllAnswersPerParticipant(Long participantId) throws ServiceException;
}
