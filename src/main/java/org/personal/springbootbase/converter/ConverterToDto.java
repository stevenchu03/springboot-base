package org.personal.springbootbase.converter;

import java.util.List;
import java.util.function.Function;

public interface ConverterToDto<E,D> {

    D toDto(E entity);

    List<D> toDtoList(List<E> entities);

    List<D> toDtoList(List<E> entities, Function<? super E, ? extends D> mapper);
}
