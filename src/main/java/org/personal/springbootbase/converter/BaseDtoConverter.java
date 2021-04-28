package org.personal.springbootbase.converter;

import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

@Component
public abstract class BaseDtoConverter<E,D> implements ConverterToDto<E,D> {

    @Override
    public List<D> toDtoList(List<E> entities) {
        return toDtoList(entities,this::toDto);
    }

    @Override
    public List<D> toDtoList(List<E> entities, Function<? super E, ? extends D> mapper) {
        if(entities==null|| entities.isEmpty()){
            return Collections.emptyList();
        }
        return entities.stream().map(mapper).collect(Collectors.toList());
    }
}
