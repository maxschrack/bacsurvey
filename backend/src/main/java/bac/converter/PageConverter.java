package bac.converter;

import bac.dto.PageDto;
import bac.model.Page;
import bac.model.Question;
import bac.model.Questionnaire;
import bac.repository.PageRepository;
import bac.repository.QuestionRepository;
import bac.repository.QuestionnaireRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class PageConverter extends Converter<PageDto, Page>{

    @Autowired
    private QuestionnaireRepository questionnaireRepository;
    @Autowired
    private QuestionRepository questionRepository;

    public PageConverter(){}

    @Override
    public PageDto newDto() {
        return new PageDto();
    }

    @Override
    public Page newEntity() {
        return new Page();
    }

    @Override
    public PageDto toDto(Page entity){
        PageDto dto = super.toDto(entity);

        dto.setQuestionnaireId(entity.getQuestionnaire().getId());

        List<Question> questions = entity.getQuestions();
        List<Long> questionIds = new ArrayList<>();
        if(questions != null){
            for(Question question : questions){
                questionIds.add(question.getId());
            }
            dto.setQuestionIds(questionIds);
        }

        return dto;
    }

    @Override
    public Page toEntity(PageDto dto){
        Page entity = super.toEntity(dto);

        Questionnaire questionnaire = questionnaireRepository.findOne(dto.getQuestionnaireId());
        if(questionnaire == null)
            throw new IllegalArgumentException("Invalid Dto");
        entity.setQuestionnaire(questionnaire);

        List<Question> questions = new ArrayList<>();
        for(Long questionIds : dto.getQuestionIds()){
            Question question = questionRepository.findOne(questionIds);
            if(question == null)
                throw new IllegalArgumentException("Invalid dto");
            questions.add(question);
        }

        return entity;
    }
}
