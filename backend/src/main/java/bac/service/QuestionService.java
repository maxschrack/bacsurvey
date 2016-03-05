package bac.service;

import bac.dto.*;
import bac.exception.ServiceException;

import java.util.List;

public interface QuestionService {

    // OPEN QUESTION
    OpenQuestionDto createOpenQuestion(OpenQuestionDto toCreate) throws ServiceException;
    OpenQuestionDto readOpenQuestion(OpenQuestionDto toRead) throws ServiceException;
    OpenQuestionDto updateOpenQuestion(OpenQuestionDto toUpdate) throws ServiceException;
    OpenQuestionDto deleteOpenQuestion(OpenQuestionDto toDelete) throws ServiceException;

    void delete(Long questionId) throws ServiceException;

    // MULTIPLE CHOICE
    MultipleChoiceDto createMultipleChoiceQuestion(MultipleChoiceDto toCreate);
    MultipleChoiceDto updateMultipleChoiceQuestion(MultipleChoiceDto toUpdate) throws ServiceException;


    QuestionDto readMultipleChoice(Long id) throws ServiceException;

    DtoList<QuestionDto> readAllPerPage(PageDto pageDto);

    QuestionDto read(Long questionId);
}
