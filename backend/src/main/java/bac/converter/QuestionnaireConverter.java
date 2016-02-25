package bac.converter;

import bac.dto.QuestionnaireDto;
import bac.model.MetaPage;
import bac.model.Page;
import bac.model.Questionnaire;
import bac.model.User;
import bac.repository.MetaPageRepository;
import bac.repository.PageRepository;
import bac.repository.QuestionnaireRepository;
import bac.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class QuestionnaireConverter extends Converter<QuestionnaireDto, Questionnaire>{

    @Autowired
    private PageRepository pageRepository;
    @Autowired
    private MetaPageRepository metaPageRepository;
    @Autowired
    private UserRepository userRepository;

    public QuestionnaireConverter(){}

    @Override
    public QuestionnaireDto newDto() {
        return new QuestionnaireDto();
    }

    @Override
    public Questionnaire newEntity() {
        return new Questionnaire();
    }

    @Override
    public QuestionnaireDto toDto(Questionnaire entity){
        QuestionnaireDto dto = super.toDto(entity);

        // get user id for dto object
        dto.setUserId(entity.getUser().getId());

        // add page ids
        List<Page> pages = entity.getPages();
        List<Long> pageIds = new ArrayList<>();
        if(pages != null){
            for(Page page : pages){
                pageIds.add(page.getId());
            }
            dto.setPageIds(pageIds);
        }
        // set startpage's id
        if(entity.getStartPage() != null)
            dto.setStartPageId(entity.getStartPage().getId());
        // set endpage's id
        if(entity.getEndPage() != null)
            dto.setEndPageId(entity.getEndPage().getId());

        return dto;
    }

    @Override
    public Questionnaire toEntity(QuestionnaireDto dto){
        Questionnaire entity = super.toEntity(dto);

        List<Page> pages = new ArrayList<>();

        for(Long pageId : dto.getPageIds()){
           Page page = pageRepository.findOne(pageId);
            if(page == null)
                throw new IllegalArgumentException("Invalid Dto");
            pages.add(page);
        }
        entity.setPages(pages);

        // setStartPage
        if(dto.getStartPageId() != null){
            MetaPage startPage = metaPageRepository.findOne(dto.getStartPageId());
            if(startPage != null){
                entity.setStartPage(startPage);
            }
        }

        // set endPage
        if(dto.getStartPageId() != null) {
            MetaPage endPage = metaPageRepository.findOne(dto.getEndPageId());
            if (endPage == null) {
                entity.setEndPage(endPage);
            }
        }

        // set User
        User user = userRepository.findOne(dto.getUserId());
        if(user == null)
            throw new IllegalArgumentException("Invalid Dto");
        entity.setUser(user);

        return entity;
    }
}
