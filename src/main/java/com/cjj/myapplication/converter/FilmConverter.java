package com.cjj.myapplication.converter;

import com.cjj.myapplication.api.dto.FilmDTO;
import com.cjj.myapplication.model.Film;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface FilmConverter {
    FilmConverter INSTANCE = Mappers.getMapper(FilmConverter.class);

    @Mappings({
            @Mapping(source = "filmName",target = "film_name"),
            @Mapping(source = "initialName",target = "initial_name"),
            @Mapping(source = "releaseYear",target = "release_year"),
            @Mapping(source = "evaluateNumber",target = "evaluate_number"),
            @Mapping(source = "imageSrc",target = "image_src"),
    })
    Film FilmDTOToFilm(FilmDTO filmDTO);

    @Mappings({
            @Mapping(source = "film_name",target = "filmName"),
            @Mapping(source = "initial_name",target = "initialName"),
            @Mapping(source = "release_year",target = "releaseYear"),
            @Mapping(source = "evaluate_number",target = "evaluateNumber"),
            @Mapping(source = "image_src",target = "imageSrc"),
    })
    FilmDTO FilmToFilmDTO(Film film);
    @Mappings({
            @Mapping(source = "film_name",target = "filmName"),
            @Mapping(source = "initial_name",target = "initialName"),
            @Mapping(source = "release_year",target = "releaseYear"),
            @Mapping(source = "evaluate_number",target = "evaluateNumber"),
            @Mapping(source = "image_src",target = "imageSrc"),
    })
    List<FilmDTO> FilmListToFilmDTOList(List<Film> films);

}