package bac.util;

import bac.dto.*;
import bac.rest.*;
import org.springframework.beans.BeanUtils;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

public class DtoFactory {

    private static final Map<Class, Class> MAPPINGS;

    static {
        Map<Class, Class> aMap = new LinkedHashMap<>();
        aMap.put(UserRest.class, UserDto.class);
        aMap.put(QuestionnaireRest.class, QuestionnaireDto.class);
        aMap.put(PageRest.class, PageDto.class);
        aMap.put(OpenQuestionRest.class, OpenQuestionDto.class);
        aMap.put(MultipleChoiceRest.class, MultipleChoiceDto.class);
        aMap.put(MetaPageRest.class, MetaPageDto.class);
        aMap.put(ParticipantRest.class, ParticipantDto.class);
        aMap.put(AnswerRest.class, AnswerDto.class);
        aMap.put(LogRest.class, LogDto.class);
        MAPPINGS = Collections.unmodifiableMap(aMap);
    }

    /**
     * Converts the rest model to a dto model.
     * ATTENTION: If you use the DtoFactory and require additional attributes to be set,
     * you must to implement your own method!
     *
     * @param model - model to be converted
     * @param <E>   - class of the expected dto, taken from MAPPINGS
     * @return - dto object containing all attributes passed by the rest model, WITHOUT an ID.
     */
    public static <E extends EntityDto> E toDto(EntityModelRest model) {
        Class clazz = MAPPINGS.get(model.getClass());
        E dto;
        try {
            dto = (E) clazz.newInstance();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        BeanUtils.copyProperties(model, dto);
        dto.setId(null);
        return dto;
    }
}