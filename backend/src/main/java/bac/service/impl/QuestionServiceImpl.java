package bac.service.impl;

import bac.converter.MultipleChoiceConverter;
import bac.converter.OpenQuestionConverter;
import bac.converter.QuestionConverter;
import bac.dto.*;
import bac.exception.ServiceException;
import bac.model.*;
import bac.repository.MultipleChoiceAnswerRepository;
import bac.repository.MultipleChoiceRepository;
import bac.repository.OpenQuestionRepository;
import bac.repository.QuestionRepository;
import bac.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class QuestionServiceImpl implements QuestionService{

    @Autowired
    private OpenQuestionRepository openQuestionRepository;
    @Autowired
    private OpenQuestionConverter openQuestionConverter;

    @Autowired
    private MultipleChoiceRepository multipleChoiceRepository;
    @Autowired
    private MultipleChoiceConverter multipleChoiceConverter;

    @Autowired
    private MultipleChoiceAnswerRepository multipleChoiceAnswerRepository;

    @Autowired
    private QuestionRepository questionRepository;
    @Autowired
    private QuestionConverter questionConverter;

    public QuestionServiceImpl() {
    }
    /*
    public QuestionServiceImpl(OpenQuestionRepository openQuestionRepository,
                               OpenQuestionConverter openQuestionConverter,
                               MultipleChoiceRepository multipleChoiceRepository,
                               MultipleChoiceConverter multipleChoiceConverter,
                               QuestionRepository questionRepository,
                               QuestionConverter questionConverter,
                               MultipleChoiceAnswerRepository multipleChoiceAnswerRepository) {
        this.openQuestionRepository = openQuestionRepository;
        this.openQuestionConverter = openQuestionConverter;
        this.multipleChoiceRepository = multipleChoiceRepository;
        this.multipleChoiceConverter = multipleChoiceConverter;
        this.questionRepository = questionRepository;
        this.questionConverter = questionConverter;
        this.multipleChoiceAnswerRepository = multipleChoiceAnswerRepository;
    }*/

    // ##########################   OPEN QUESTION   #################################
    @Override
    @Transactional
    public OpenQuestionDto createOpenQuestion(OpenQuestionDto toCreate) {
        if(toCreate == null)
            throw new IllegalArgumentException("Illegal Argument: Null");

        // validate
        // TODO : VALIDATION

        // convert
        OpenQuestion openQuestion = openQuestionConverter.toEntity(toCreate);

        // create
        openQuestion = openQuestionRepository.save(openQuestion);

        // convert back and return
        return openQuestionConverter.toDto(openQuestion);
    }


    // ##########################   MC QUESTION   #################################
    @Override
    @Transactional
    public MultipleChoiceDto createMultipleChoiceQuestion(MultipleChoiceDto toCreate) {
        if(toCreate == null)
            throw new IllegalArgumentException("Illegal Argument: Null");

        // validate

        // convert
        MultipleChoice multipleChoice = multipleChoiceConverter.toEntity(toCreate);

        // create
        MultipleChoice newMultipleChoice = multipleChoiceRepository.save(multipleChoice);

        // Create Answers
        for(String answer : toCreate.getAnswers()){
            MultipleChoiceAnswer toSave = new MultipleChoiceAnswer();
            toSave.setText(answer);
            toSave.setMultipleChoice(newMultipleChoice);
            multipleChoiceAnswerRepository.save(toSave);
        }
        // convert back and return
        return multipleChoiceConverter.toDto(newMultipleChoice);
    }

    @Override
    public OpenQuestionDto readOpenQuestion(OpenQuestionDto toRead) throws ServiceException {

        // validate

        // search
        OpenQuestion question = openQuestionRepository.findOne(toRead.getId());

        // convert and return
        return openQuestionConverter.toDto(question);
    }

    @Override
    @Transactional
    public OpenQuestionDto updateOpenQuestion(OpenQuestionDto toUpdate) throws ServiceException {

        // validate

        // convert
        OpenQuestion oldQuestion = openQuestionConverter.toEntity(toUpdate);

        // update
        OpenQuestion newQuestion = openQuestionRepository.save(oldQuestion);

        // convert and return
        return openQuestionConverter.toDto(newQuestion);
    }

    @Override
    @Transactional
    public OpenQuestionDto deleteOpenQuestion(OpenQuestionDto toDelete) throws ServiceException {

        // validate

        // retrieve question
        OpenQuestion question = openQuestionRepository.findOne(toDelete.getId());

        // delete question
        openQuestionRepository.delete(toDelete.getId());

        return openQuestionConverter.toDto(question);
    }

    @Override
    public DtoList<QuestionDto> readAllPerPage(PageDto pageDto) {

        // validate

        //
        Page finder = new Page();
        finder.setId(pageDto.getId());
        List<Question> result = questionRepository.findByPage(finder);

        // convert and return
        return questionConverter.toDtoList(result);
    }

    @Override
    public QuestionDto readMultipleChoice(Long questionId) {

        // validate

        // search
        Question question = multipleChoiceRepository.findOne(questionId);

        // convert and return
        return questionConverter.toDto(question);
    }

    @Override
    public QuestionDto read(Long questionId) {

        // validate

        // search
        Question question = questionRepository.findOne(questionId);

        if(question instanceof MultipleChoice){
            if(((MultipleChoice)question).getAnswers() == null){
                List<MultipleChoiceAnswer> answers = multipleChoiceAnswerRepository.findByMultipleChoice(questionId);
                ((MultipleChoice) question).setAnswers(answers);
            }
        }

        // convert and return
        return questionConverter.toDto(question);
    }
}
