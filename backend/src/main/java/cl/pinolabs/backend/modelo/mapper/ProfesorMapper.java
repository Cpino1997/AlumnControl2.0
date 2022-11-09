package cl.pinolabs.backend.modelo.mapper;

import cl.pinolabs.backend.dto.entity.ProfesorDTO;
import cl.pinolabs.backend.modelo.entity.Profesor;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import java.util.List;

@Mapper(componentModel = "spring")
public interface ProfesorMapper {
    @Mappings({
            @Mapping(source = "id", target = "id"),
            @Mapping(source = "rut", target = "rut"),
            @Mapping(source = "nombre", target = "nombre"),
            @Mapping(source = "apellido", target = "apellido"),
            @Mapping(source = "correo", target = "correo"),
            @Mapping(source = "valor", target = "valor"),
            @Mapping(source = "banco", target = "banco"),
            @Mapping(source = "cuenta", target = "cuenta"),
            @Mapping(source = "asignatura", target = "asignatura"),
            @Mapping(source = "numero", target = "numero"),


    })
    ProfesorDTO toProfesorDTO(Profesor profesor);
    List<ProfesorDTO> toProfesoresDTO(List<Profesor> profesores);
    @InheritInverseConfiguration
    Profesor toProfesor(ProfesorDTO profesorDTO);
}
