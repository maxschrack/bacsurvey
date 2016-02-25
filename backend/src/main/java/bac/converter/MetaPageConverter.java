package bac.converter;

import bac.dto.MetaPageDto;
import bac.model.MetaPage;
import bac.model.Questionnaire;
import bac.repository.PageRepository;
import bac.repository.QuestionnaireRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class MetaPageConverter extends Converter<MetaPageDto, MetaPage>{

    @Autowired
    private QuestionnaireRepository questionnaireRepository;

    public MetaPageConverter(){}

    @Override
    public MetaPageDto newDto() {
        return new MetaPageDto();
    }

    @Override
    public MetaPage newEntity() {
        return new MetaPage();
    }

    @Override
    public MetaPageDto toDto(MetaPage entity){
        MetaPageDto dto = super.toDto(entity);

        dto.setQuestionnaireId(entity.getQuestionnaire().getId());

        return dto;
    }

    @Override
    public MetaPage toEntity(MetaPageDto dto){
        MetaPage entity = super.toEntity(dto);

        Questionnaire questionnaire = questionnaireRepository.findOne(dto.getQuestionnaireId());
        if(questionnaire == null)
            throw new IllegalArgumentException("Invalid Dto");
        entity.setQuestionnaire(questionnaire);

        return entity;
    }
}
