package bac.converter;

import bac.dto.ParticipantDto;
import bac.model.Participant;
import bac.model.Questionnaire;
import bac.repository.ParticipantRepository;
import bac.repository.QuestionnaireRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ParticipantConverter extends Converter<ParticipantDto, Participant>{

    @Autowired
    private QuestionnaireRepository questionnaireRepository;

    public ParticipantConverter(){}


    @Override
    public ParticipantDto newDto() {
        return new ParticipantDto();
    }

    @Override
    public Participant newEntity() {
        return new Participant();
    }


    public ParticipantDto toDto(Participant entity){
        ParticipantDto participantDto = super.toDto(entity);

        participantDto.setQuestionnaireId(entity.getId());

        return participantDto;
    }

    public Participant toEntity(ParticipantDto dto){
        Participant entity = super.toEntity(dto);

        Questionnaire questionnaire = questionnaireRepository.findOne(dto.getQuestionnaireId());
        if(questionnaire == null)
            throw new IllegalArgumentException("Invalid Dto");
        entity.setQuestionnaire(questionnaire);

        return entity;
    }
}
