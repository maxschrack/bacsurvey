package bac.converter;

import bac.dto.Dto;
import bac.dto.DtoList;
import bac.model.Entity;
import org.springframework.beans.BeanUtils;


import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public abstract class Converter<D extends Dto, E extends Entity> {

    public D toDto(E entitiy){
        D dto = newDto();
        BeanUtils.copyProperties(entitiy, dto);
        return dto;
    }

    public E toEntity(D dto){
        E entity = newEntity();
        BeanUtils.copyProperties(dto, entity);
        return entity;
    }

    public abstract D newDto();

    public abstract  E newEntity();

    public DtoList<D> toDtoList(List<E> entities){
        ArrayList<D> result = entities.stream().map(this::toDto).collect(Collectors.toCollection(ArrayList::new));
        return new DtoList<>(result);
    }
}
