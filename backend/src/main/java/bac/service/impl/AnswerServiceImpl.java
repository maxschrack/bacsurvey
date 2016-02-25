package bac.service.impl;


import bac.converter.AnswerConverter;
import bac.dto.AnswerDto;
import bac.dto.DtoList;
import bac.dto.ParticipantDto;
import bac.dto.QuestionDto;
import bac.exception.ServiceException;
import bac.model.Answer;
import bac.model.Participant;
import bac.model.Question;
import bac.repository.AnswerRepository;
import bac.service.AnswerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AnswerServiceImpl implements AnswerService{

    @Autowired
    private AnswerRepository answerRepository;
    @Autowired
    private AnswerConverter answerConverter;

    public AnswerServiceImpl() {
    }

    @Override
    public AnswerDto create(AnswerDto toCreate) throws ServiceException {
        if(toCreate == null)
            throw new IllegalArgumentException("Illegal Argument: Null");

        // validate
        // TODO : VALIDATION

        // convert
        Answer answer = answerConverter.toEntity(toCreate);

        // create
        answer = answerRepository.save(answer);

        // convert back and return
        return answerConverter.toDto(answer);
    }

    @Override
    public AnswerDto read(AnswerDto toRead) throws ServiceException {

        // validate

        // search
        Answer answer = answerRepository.findOne(toRead.getId());

        // convert and return
        return answerConverter.toDto(answer);
    }

    @Override
    public AnswerDto update(AnswerDto toUpdate) throws ServiceException {

        // validate

        // convert
        Answer oldAnswer = answerConverter.toEntity(toUpdate);

        // update
        Answer newAnswer = answerRepository.save(oldAnswer);

        // convert and return
        return answerConverter.toDto(newAnswer);
    }

    @Override
    public AnswerDto delete(AnswerDto toDelete) throws ServiceException {

        // validate

        // retrieve answer
        Answer answer = answerRepository.findOne(toDelete.getId());

        // delete answer
        answerRepository.delete(toDelete.getId());

        return answerConverter.toDto(answer);
    }

    @Override
    public DtoList<AnswerDto> readAllAnswersPerQuestion(Long questionId) throws ServiceException {

        // validate

        //
        Question finder = new Question();
        finder.setId(questionId);
        List<Answer> result = answerRepository.findByQuestion(finder);

        // convert and return
        return answerConverter.toDtoList(result);
    }

    @Override
    public DtoList<AnswerDto> readAllAnswersPerParticipant(Long participantId) throws ServiceException {

        // validate

        //
        Participant finder = new Participant();
        finder.setId(participantId);
        List<Answer> result = answerRepository.findByParticipant(finder);

        // convert and return
        return answerConverter.toDtoList(result);
    }
}
