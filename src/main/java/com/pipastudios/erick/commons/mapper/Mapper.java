package com.pipastudios.erick.commons.mapper;

import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public interface Mapper<E, T> {

  T convertTransferObject(E e);

  default List<T> convertTransferObject(List<E> e) {
    return e.stream()
        .filter(applyEntityFilter())
        .map(this::convertTransferObject)
        .collect(Collectors.toList());
  }

  default Set<T> convertTransferObject(Set<E> e) {
    return e.stream()
        .filter(applyEntityFilter())
        .map(this::convertTransferObject)
        .collect(Collectors.toSet());
  }

  default List<T> convertTransferObject(Stream<E> e) {
    return e.filter(applyEntityFilter())
        .map(this::convertTransferObject)
        .collect(Collectors.toList());
  }

  E convertEntity(T t);

  default List<E> convertEntity(List<T> t) {
    return t.stream()
        .filter(applyTargetFilter())
        .map(this::convertEntity)
        .collect(Collectors.toList());
  }

  default List<E> convertEntity(Stream<T> t) {
    return t.filter(applyTargetFilter()).map(this::convertEntity).collect(Collectors.toList());
  }

  default Set<E> convertEntity(Set<T> t) {
    return t.stream()
        .filter(applyTargetFilter())
        .map(this::convertEntity)
        .collect(Collectors.toSet());
  }

  default Predicate<T> applyTargetFilter() {
    return Objects::nonNull;
  }

  default Predicate<E> applyEntityFilter() {
    return Objects::nonNull;
  }
}
