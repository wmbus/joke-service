package nl.ordina.jtech.wmbus.jokeservice.mapper;

import nl.ordina.jtech.wmbus.jokeservice.domain.Joke;
import nl.ordina.jtech.wmbus.jokeservice.dto.JokeDto;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING,
        injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface JokeMapper {

    @Mapping(target = "randomJoke", source = "joke")
    JokeDto toJokeDto(Joke joke);
}
