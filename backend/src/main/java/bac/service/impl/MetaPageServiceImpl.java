package bac.service.impl;

import bac.converter.MetaPageConverter;
import bac.dto.MetaPageDto;
import bac.dto.PageDto;
import bac.dto.QuestionnaireDto;
import bac.exception.ServiceException;
import bac.model.MetaPage;
import bac.model.Questionnaire;
import bac.repository.MetaPageRepository;
import bac.repository.QuestionRepository;
import bac.repository.QuestionnaireRepository;
import bac.service.MetaPageService;
import bac.service.QuestionService;
import bac.service.QuestionnaireService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MetaPageServiceImpl implements MetaPageService{

    @Autowired
    private MetaPageRepository metaPageRepository;
    @Autowired
    private MetaPageConverter metaPageConverter;
    @Autowired
    private QuestionnaireRepository questionnaireRepository;

    public MetaPageServiceImpl() {
    }

    /*public MetaPageServiceImpl(MetaPageRepository metaPageRepository,
                               MetaPageConverter metaPageConverter,
                               QuestionnaireRepository questionnaireRepository) {
        this.metaPageRepository = metaPageRepository;
        this.metaPageConverter = metaPageConverter;
        this.questionnaireRepository = questionnaireRepository;
    }*/

    @Override
    public MetaPageDto createStartPage(MetaPageDto toCreate) throws ServiceException {

        // create
        MetaPage startPage = createMetaPage(toCreate);

        // update questionnaire
        Questionnaire questionnaire = questionnaireRepository.findOne(toCreate.getQuestionnaireId());
        questionnaire.setStartPage(startPage);
        questionnaireRepository.save(questionnaire);

        // convert and return
        return metaPageConverter.toDto(startPage);
    }

    @Override
    public MetaPageDto createEndPage(MetaPageDto toCreate) throws ServiceException {

        // create
        MetaPage endPage = createMetaPage(toCreate);

        // update questionnaire
        Questionnaire questionnaire = questionnaireRepository.findOne(toCreate.getQuestionnaireId());
        questionnaire.setEndPage(endPage);
        questionnaireRepository.save(questionnaire);

        // convert and return
        return metaPageConverter.toDto(endPage);
    }



    private MetaPage createMetaPage(MetaPageDto toCreate) throws ServiceException {
        if(toCreate == null)
            throw new IllegalArgumentException("Illegal Argument: Null");

        // validate

        // convert
        MetaPage metaPage = metaPageConverter.toEntity(toCreate);

        // create
        return metaPageRepository.save(metaPage);
    }



    @Override
    public MetaPageDto readStartPagePerQuestionnaire(QuestionnaireDto toRead) throws ServiceException {

        // validate

        // search
        Questionnaire finder = questionnaireRepository.findOne(toRead.getId());
        if(finder.getStartPage() != null){
            MetaPage result = metaPageRepository.findOne(finder.getStartPage().getId());
            return metaPageConverter.toDto(result);
        }else{
            return null;
        }
    }

    @Override
    public MetaPageDto readEndPagePerQuestionnaire(QuestionnaireDto toRead) throws ServiceException {

        // validate

        // search
        Questionnaire finder = questionnaireRepository.findOne(toRead.getId());
        MetaPage result = metaPageRepository.findOne(finder.getEndPage().getId());

        // convert and return
        return metaPageConverter.toDto(result);
    }

    @Override
    public MetaPageDto updateMetaPage(MetaPageDto toUpdate) throws ServiceException {

        // validate

        // convert
        MetaPage oldMetaPage = metaPageConverter.toEntity(toUpdate);

        // update
        MetaPage newMetaPage = metaPageRepository.save(oldMetaPage);

        // convert and return
        return metaPageConverter.toDto(newMetaPage);
    }

    @Override
    public MetaPageDto deleteMetaPage(MetaPageDto toDelete) throws ServiceException {

        // validate

        // retrieve page
        MetaPage metaPage = metaPageRepository.findOne(toDelete.getId());

        // delete page
        metaPageRepository.delete(toDelete.getId());

        return metaPageConverter.toDto(metaPage);
    }
}
