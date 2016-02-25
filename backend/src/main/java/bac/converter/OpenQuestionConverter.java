package bac.converter;

import bac.dto.OpenQuestionDto;
import bac.model.OpenQuestion;
import bac.model.Page;
import bac.model.Question;
import bac.repository.OpenQuestionRepository;
import bac.repository.PageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class OpenQuestionConverter extends Converter<OpenQuestionDto, OpenQuestion> {

    @Autowired
    private PageRepository pageRepository;

    public OpenQuestionConverter() {
    }

    @Override
    public OpenQuestionDto newDto() {
        return new OpenQuestionDto();
    }

    @Override
    public OpenQuestion newEntity() {
        return new OpenQuestion();
    }

    @Override
    public OpenQuestionDto toDto(OpenQuestion entity){
        OpenQuestionDto dto = super.toDto(entity);

        dto.setPageId(entity.getPage().getId());

        return dto;
    }

    @Override
    public OpenQuestion toEntity(OpenQuestionDto dto){

        OpenQuestion entity = super.toEntity(dto);

        Page page = pageRepository.findOne(dto.getPageId());
        if(page == null)
            throw new IllegalArgumentException("Invalid Dto");
        entity.setPage(page);

        return entity;
    }
}
