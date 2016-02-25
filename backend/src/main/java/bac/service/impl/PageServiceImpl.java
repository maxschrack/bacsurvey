package bac.service.impl;

import bac.converter.PageConverter;
import bac.dto.DtoList;
import bac.dto.PageDto;
import bac.dto.QuestionnaireDto;
import bac.exception.ServiceException;
import bac.model.Page;
import bac.model.Questionnaire;
import bac.repository.PageRepository;
import bac.service.PageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
public class PageServiceImpl implements PageService{

    @Autowired
    private PageRepository pageRepository;
    @Autowired
    private PageConverter pageConverter;

    public PageServiceImpl() {
    }

    /*public PageServiceImpl(PageRepository pageRepository,
                           PageConverter pageConverter) {
        this.pageRepository = pageRepository;
        this.pageConverter = pageConverter;
    }*/

    @Override
    @Transactional
    public PageDto create(PageDto toCreate) throws ServiceException {
        if(toCreate == null)
            throw new IllegalArgumentException("Illegal Argument: Null");

        // validate
        // TODO : VALIDATION

        // convert
        Page page = pageConverter.toEntity(toCreate);

        // create
        page = pageRepository.save(page);

        // convert back and return
        return pageConverter.toDto(page);
    }

    @Override
    public PageDto read(PageDto toRead) throws ServiceException {

        // validate

        // search
        Page page = pageRepository.findOne(toRead.getId());

        // convert and return
        return pageConverter.toDto(page);
    }

    @Override
    @Transactional
    public PageDto update(PageDto toUpdate) throws ServiceException {

        // validate

        // convert
        Page oldPage = pageConverter.toEntity(toUpdate);

        // update
        Page newPage = pageRepository.save(oldPage);

        // convert and return
        return pageConverter.toDto(newPage);
    }

    @Override
    @Transactional
    public PageDto delete(PageDto toDelete) throws ServiceException {

        // validate

        // retrieve page
        Page page = pageRepository.findOne(toDelete.getId());

        // delete page
        pageRepository.delete(toDelete.getId());

        return pageConverter.toDto(page);
    }

    @Override
    public DtoList<PageDto> readAll() throws ServiceException {

        // read all
        List<Page> pages = pageRepository.findAll();

        // convert and return
        return pageConverter.toDtoList(pages);
    }

    @Override
    public DtoList<PageDto> readAllPerQuestionnaire(QuestionnaireDto questionnaireDto) {

        // validate

        //
        Questionnaire finder = new Questionnaire();
        finder.setId(questionnaireDto.getId());
        List<Page> result = pageRepository.findByQuestionnaire(finder);

        // convert and return
        return pageConverter.toDtoList(result);
    }

}
