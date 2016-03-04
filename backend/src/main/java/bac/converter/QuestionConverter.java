package bac.converter;

import bac.dto.*;
import bac.model.*;
import bac.repository.MultipleChoiceRepository;
import bac.repository.OpenQuestionRepository;
import bac.repository.PageRepository;
import bac.repository.QuestionRepository;
import com.sun.org.apache.xpath.internal.operations.Mult;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class QuestionConverter{

    @Autowired
    private PageRepository pageRepository;

    public QuestionConverter() {
    }

    public OpenQuestionDto newOpenQuestionDto(){
        return new OpenQuestionDto();
    }

    public MultipleChoiceDto newMultipleChoiceDto(){
        return new MultipleChoiceDto();
    }

    public Question newOpenQuestionEntity() {
        return new OpenQuestion();
    }
    public Question newMultipleChoiceEntity() {
        return new MultipleChoice();
    }


    public QuestionDto toDto(Question entity){
        QuestionDto dto;
        if(entity instanceof OpenQuestion){
            dto = newOpenQuestionDto();
            ((OpenQuestionDto) dto).setIsLong(((OpenQuestion) entity).getIsLong());
            ((OpenQuestionDto) dto).setValidationType(((OpenQuestion) entity).getValidationType());
        }else{
            dto = newMultipleChoiceDto();
            ((MultipleChoiceDto) dto).setIsSingleChoice(((MultipleChoice) entity).getIsSingleChoice());
            List<MultipleChoiceAnswerDto> answers = new ArrayList<>();
            if(((MultipleChoice) entity).getAnswers() != null){
                for(MultipleChoiceAnswer answer : ((MultipleChoice) entity).getAnswers()){
                    MultipleChoiceAnswerDto multipleChoiceAnswerDto = new MultipleChoiceAnswerDto();
                    multipleChoiceAnswerDto.setId(answer.getId());
                    multipleChoiceAnswerDto.setText(answer.getText());
                    answers.add(multipleChoiceAnswerDto);
                }
            }
            ((MultipleChoiceDto) dto).setAnswers(answers);
        }

        dto.setId(entity.getId());
        dto.setText(entity.getText());
        dto.setMandatory(entity.getMandatory());
        dto.setPosition(entity.getPosition());
        dto.setDeleted(entity.getDeleted());
        dto.setPageId(entity.getPage().getId());

        return dto;
    }

    public Question toEntity(QuestionDto dto){

        Question entity;
        if(dto instanceof OpenQuestionDto) {
            entity = newOpenQuestionEntity();
            ((OpenQuestion) entity).setIsLong(((OpenQuestionDto) dto).getIsLong());
            ((OpenQuestion) entity).setValidationType(((OpenQuestionDto) dto).getValidationType());
        }else{
            entity = newMultipleChoiceEntity();
            ((MultipleChoice) entity).setIsSingleChoice(((MultipleChoiceDto) dto).getIsSingleChoice());
            List<MultipleChoiceAnswer> answers = new ArrayList<>();
            for(MultipleChoiceAnswerDto answer : ((MultipleChoiceDto) dto).getAnswers()){
                MultipleChoiceAnswer temp = new MultipleChoiceAnswer();
                temp.setText(answer.getText());
                answers.add(temp);
            }
            ((MultipleChoice) entity).setAnswers(answers);
        }

        entity.setId(dto.getId());
        entity.setText(dto.getText());
        entity.setMandatory(dto.getMandatory());
        entity.setPosition(dto.getPosition());
        entity.setDeleted(dto.getDeleted());

        Page page = pageRepository.findOne(dto.getPageId());
        if(page == null)
            throw new IllegalArgumentException("Invalid Dto");
        entity.setPage(page);

        return entity;
    }

    public DtoList<QuestionDto> toDtoList(List<Question> entities){
        ArrayList<QuestionDto> result = new ArrayList<>();
        for(Question question : entities) {
            result.add(this.toDto(question));
        }
        return new DtoList<>(result);
    }
}
