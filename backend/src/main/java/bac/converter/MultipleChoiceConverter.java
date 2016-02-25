package bac.converter;


import bac.dto.MultipleChoiceDto;
import bac.model.MultipleChoice;
import bac.model.MultipleChoiceAnswer;
import bac.model.Page;
import bac.repository.MultipleChoiceRepository;
import bac.repository.PageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@Component
public class MultipleChoiceConverter  extends Converter<MultipleChoiceDto, MultipleChoice> {

    @Autowired
    private MultipleChoiceRepository multipleChoiceRepository;
    @Autowired
    private PageRepository pageRepository;

    public MultipleChoiceConverter() {
    }

    @Override
    public MultipleChoiceDto newDto() {
        return new MultipleChoiceDto();
    }

    @Override
    public MultipleChoice newEntity() {
        return new MultipleChoice();
    }

    @Override
    public MultipleChoiceDto toDto(MultipleChoice entity){
        MultipleChoiceDto dto = super.toDto(entity);

        dto.setPageId(entity.getPage().getId());

        return dto;
    }

    @Override
    public MultipleChoice toEntity(MultipleChoiceDto dto){

        MultipleChoice entity = super.toEntity(dto);
        entity.setAnswers(null);
        Page page = pageRepository.findOne(dto.getPageId());
        if(page == null)
            throw new IllegalArgumentException("Invalid Dto");
        entity.setPage(page);

        return entity;
    }
}
