package bac.util;

import bac.dto.*;
import bac.rest.*;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Value;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;


public class ModelFactory {
    @Value("${config.url.context}")
    private static String baseUrl;
    @Value("${config.context}")
    private static String context;
    @Value("${config.build}")
    private static String buildNumber;
    @Value("${config.build}")
    private static String version;
    @Value("${full-version}")
    private static String fullVersion;
    @Value("${config.api.version}")
    private static String apiVersion;

    public static UserRest user(UserDto dto) {
        UserRest userRest = new UserRest();
        setValuesFromDto(dto, userRest);
        return userRest;
    }

    public static QuestionnaireRest questionnaire(QuestionnaireDto dto) {
        QuestionnaireRest userRest = new QuestionnaireRest();
        setValuesFromDto(dto, userRest);
        return userRest;
    }

    public static PageRest page(PageDto dto) {
        PageRest pageRest = new PageRest();
        setValuesFromDto(dto, pageRest);
        return pageRest;
    }

    public static MetaPageRest metaPage(MetaPageDto dto) {
        MetaPageRest metaPageRest = new MetaPageRest();
        setValuesFromDto(dto, metaPageRest);
        return metaPageRest;
    }

    public static OpenQuestionRest openQuestion(OpenQuestionDto dto) {
        OpenQuestionRest openQuestionRest = new OpenQuestionRest();
        setValuesFromDto(dto, openQuestionRest);
        return openQuestionRest;
    }

    public static ParticipantRest participant(ParticipantDto dto) {
        ParticipantRest participantRest = new ParticipantRest();
        setValuesFromDto(dto, participantRest);
        return participantRest;
    }

    public static MultipleChoiceRest multipleChoice(MultipleChoiceDto dto) {
        MultipleChoiceRest multipleChoiceRest = new MultipleChoiceRest();
        setValuesFromDto(dto, multipleChoiceRest);
        return multipleChoiceRest;
    }

    public static AnswerRest answer(AnswerDto dto) {
        AnswerRest answerRest = new AnswerRest();
        setValuesFromDto(dto, answerRest);
        return answerRest;
    }

    public static LogRest log(LogDto dto) {
        LogRest logRest = new LogRest();
        setValuesFromDto(dto, logRest);
        return logRest;
    }

    private static void setValuesFromDto(EntityDto from, EntityModelRest to) {
        BeanUtils.copyProperties(from, to);
        to.setSelfId(from.getId());
    }

}
