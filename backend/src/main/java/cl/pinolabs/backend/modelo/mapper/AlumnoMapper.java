package cl.pinolabs.backend.modelo.mapper;
import cl.pinolabs.backend.dto.entity.AlumnoDTO;
import cl.pinolabs.backend.modelo.entity.Alumno;
import org.mapstruct.*;
import java.util.List;

@Mapper(componentModel = "spring")
public interface AlumnoMapper {
        @Mappings({
                @Mapping(source = "id", target = "id"),
                @Mapping(source = "rut", target = "rut"),
                @Mapping(source = "nombre", target = "nombre"),
                @Mapping(source = "apellido", target = "apellido")
        })
        AlumnoDTO toALumnoDTO(Alumno alumno);
        List<AlumnoDTO> toALumnosDTO(List<Alumno> alumnos);
        @InheritInverseConfiguration
        Alumno toAlumno(AlumnoDTO alumnoDTO);
    }