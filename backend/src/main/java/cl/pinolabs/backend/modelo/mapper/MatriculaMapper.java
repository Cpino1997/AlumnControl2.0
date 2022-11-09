package cl.pinolabs.backend.modelo.mapper;


import cl.pinolabs.backend.dto.entity.MatriculaDTO;
import cl.pinolabs.backend.modelo.entity.Matricula;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "spring", uses = {AlumnoMapper.class, CursoMapper.class})
public interface MatriculaMapper {

    @Mappings({
            @Mapping(source = "id.idAlumno", target = "idAlumno"),
            @Mapping(source = "id.idCurso", target = "idCurso"),
            @Mapping(source = "fecha", target = "fecha"),
            @Mapping(source = "alumno", target = "alumnoDTO"),
            @Mapping(source = "curso", target = "cursoDTO"),
    })
    MatriculaDTO toMatriculaDTO(Matricula matricula);
    List<MatriculaDTO> toMatriculaDTOs(List<Matricula> matriculas);

    @InheritInverseConfiguration
    Matricula toMatricula(MatriculaDTO matriculaDTO);
}