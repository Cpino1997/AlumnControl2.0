package cl.pinolabs.backend.modelo.mapper;


import cl.pinolabs.backend.dto.entity.ApoderadoDTO;
import cl.pinolabs.backend.modelo.entity.Apoderado;
import cl.pinolabs.backend.modelo.entity.Curso;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ApoderadoMapper {
    @Mappings({
            @Mapping(source = "id", target = "id"),
            @Mapping(source = "rut", target = "rut"),
            @Mapping(source = "nombre", target = "nombre"),
            @Mapping(source = "apellido", target = "apellido"),
            @Mapping(source = "correo", target = "correo"),
            @Mapping(source = "telefono", target = "telefono")
    })
    ApoderadoDTO toApoderadoDTO(Apoderado apoderado);
    List<ApoderadoDTO> toApoderadosDTO(List<Apoderado> apoderados);
    @InheritInverseConfiguration
    Apoderado toApoderado(ApoderadoDTO apoderadoDTO);
}
