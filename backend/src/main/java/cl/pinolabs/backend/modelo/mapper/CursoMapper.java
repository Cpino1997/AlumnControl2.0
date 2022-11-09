package cl.pinolabs.backend.modelo.mapper;

import cl.pinolabs.backend.dto.entity.CursoDTO;
import cl.pinolabs.backend.modelo.entity.Curso;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "spring", uses = {AlumnoMapper.class})
public interface CursoMapper {
    @Mappings({
            @Mapping(source = "id", target = "id"),
            @Mapping(source = "nombre", target = "nombre"),
            @Mapping(source = "codigo", target = "codigo"),
            @Mapping(source = "idProfesor", target = "idProfesor"),
            @Mapping(source = "profesor", target = "profesorDTO")
    })
    CursoDTO toCursoDTO(Curso curso);
    List<CursoDTO> toCursosDTO(List<Curso> cursos);
    @InheritInverseConfiguration
    Curso toCurso(CursoDTO cursoDTO);
}
