package bac.service.impl;

import bac.converter.QuestionnaireConverter;
import bac.dto.DtoList;
import bac.dto.QuestionnaireDto;
import bac.dto.UserDto;
import bac.exception.ServiceException;
import bac.model.Questionnaire;
import bac.model.User;
import bac.repository.QuestionnaireRepository;
import bac.service.QuestionnaireService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
public class QuestionnaireServiceImpl implements QuestionnaireService{

    @Autowired
    private QuestionnaireRepository questionnaireRepository;
    @Autowired
    private QuestionnaireConverter questionnaireConverter;

    public QuestionnaireServiceImpl() {
    }

    /*public QuestionnaireServiceImpl(QuestionnaireRepository questionnaireRepository,
                           QuestionnaireConverter questionnaireConverter) {
        this.questionnaireRepository = questionnaireRepository;
        this.questionnaireConverter = questionnaireConverter;
    }*/

    @Override
    @Transactional
    public QuestionnaireDto create(QuestionnaireDto toCreate) throws ServiceException {
        if(toCreate == null)
            throw new IllegalArgumentException("Illegal Argument: Null");

        // validate
        // TODO : VALIDATION

        // set Registration date
        toCreate.setCreated(new Date());

        // convert
        Questionnaire questionnaire = questionnaireConverter.toEntity(toCreate);

        // create
        questionnaire = questionnaireRepository.save(questionnaire);

        // convert back and return
        return questionnaireConverter.toDto(questionnaire);
    }

    @Override
    public QuestionnaireDto read(QuestionnaireDto toRead) throws ServiceException {

        // validate

        // search
        Questionnaire questionnaire = questionnaireRepository.findOne(toRead.getId());

        // convert and return
        return questionnaireConverter.toDto(questionnaire);
    }

    @Override
    @Transactional
    public QuestionnaireDto update(QuestionnaireDto toUpdate) throws ServiceException {

        // validate

        // convert
        Questionnaire oldQuestionnaire = questionnaireConverter.toEntity(toUpdate);

        // update
        Questionnaire newQuestionnaire = questionnaireRepository.save(oldQuestionnaire);

        // convert and return
        return questionnaireConverter.toDto(newQuestionnaire);
    }

    @Override
    @Transactional
    public QuestionnaireDto delete(QuestionnaireDto toDelete) throws ServiceException {

        // validate

        // retrieve questionnaire
        Questionnaire questionnaire = questionnaireRepository.findOne(toDelete.getId());

        // delete questionnaire
        questionnaireRepository.delete(toDelete.getId());

        return questionnaireConverter.toDto(questionnaire);
    }

    @Override
    public DtoList<QuestionnaireDto> readAll() throws ServiceException {

        // read all
        List<Questionnaire> questionnaires = questionnaireRepository.findAll();

        // convert and return
        return questionnaireConverter.toDtoList(questionnaires);
    }

    @Override
    public DtoList<QuestionnaireDto> readAllPerUser(UserDto userDto) {

        // validate

        //
        User finder = new User();
        finder.setId(userDto.getId());
        List<Questionnaire> result = questionnaireRepository.findByUser(finder);

        // convert and return
        return questionnaireConverter.toDtoList(result);

    }
}