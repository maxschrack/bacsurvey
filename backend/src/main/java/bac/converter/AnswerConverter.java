package bac.converter;

import bac.dto.AnswerDto;
import bac.model.Answer;
import bac.model.Participant;
import bac.model.Question;
import bac.repository.AnswerRepository;
import bac.repository.ParticipantRepository;
import bac.repository.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AnswerConverter extends Converter<AnswerDto, Answer>{

    @Autowired
    private AnswerRepository answerRepository;
    @Autowired
    private QuestionRepository questionRepository;
    @Autowired
    private ParticipantRepository participantRepository;

    public AnswerConverter() {
    }

    @Override
    public AnswerDto newDto() {
        return new AnswerDto();
    }

    @Override
    public Answer newEntity() {
        return new Answer();
    }

    @Override
    public AnswerDto toDto(Answer entity){
        AnswerDto dto = super.toDto(entity);

        dto.setQuestionId(entity.getQuestion().getId());
        dto.setParticipantId(entity.getParticipant().getId());

        return dto;
    }

    @Override
    public Answer toEntity(AnswerDto dto){
        Answer entity = super.toEntity(dto);

        Question question = questionRepository.findOne(dto.getQuestionId());
        if(question == null)
            throw new IllegalArgumentException("Invalid Dto");
        entity.setQuestion(question);

        Participant participant = participantRepository.findOne(dto.getParticipantId());
        if(participant == null)
            throw new IllegalArgumentException("Invalid Dto");
        entity.setParticipant(participant);

        return entity;
    }
}
