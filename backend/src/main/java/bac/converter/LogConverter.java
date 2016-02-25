package bac.converter;

import bac.converter.Converter;
import bac.dto.LogDto;
import bac.model.Log;
import bac.model.Participant;
import bac.model.Question;
import bac.model.Questionnaire;
import bac.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class LogConverter extends Converter<LogDto, Log>{

    @Autowired
    private LogRepository logRepository;
    @Autowired
    private QuestionnaireRepository questionnaireRepository;
    @Autowired
    private QuestionRepository questionRepository;
    @Autowired
    private ParticipantRepository participantRepository;
    @Autowired
    private PageRepository pageRepository;
    @Autowired
    private MetaPageRepository metaPageRepository;

    public LogConverter() {
    }

    @Override
    public LogDto newDto() {
        return new LogDto();
    }

    @Override
    public Log newEntity() {
        return new Log();
    }

    @Override
    public LogDto toDto(Log entity){
        LogDto dto = super.toDto(entity);

        dto.setQuestionnaireId(entity.getQuestionnaire().getId());
        dto.setParticipantId(entity.getParticipant().getId());

        return dto;
    }

    @Override
    public Log toEntity(LogDto dto){
        Log entity = super.toEntity(dto);

        Questionnaire questionnaire = questionnaireRepository.findOne(dto.getQuestionnaireId());
        if(questionnaire == null)
            throw new IllegalArgumentException("Invalid Dto");
        entity.setQuestionnaire(questionnaire);

        Participant participant = participantRepository.findOne(dto.getParticipantId());
        if(participant == null)
            throw new IllegalArgumentException("Invalid Dto");
        entity.setParticipant(participant);

        return entity;
    }
}
