package cl.pinolabs.backend.dto.entity;

import java.time.LocalDate;

public class MatriculaDTO {
    private Integer idAlumno;
    private Integer idCurso;
    private AlumnoDTO alumnoDTO;
    private CursoDTO cursoDTO;

    private LocalDate fecha;

    public Integer getIdAlumno() {
        return idAlumno;
    }

    public void setIdAlumno(Integer idAlumno) {
        this.idAlumno = idAlumno;
    }

    public Integer getIdCurso() {
        return idCurso;
    }

    public void setIdCurso(Integer idCurso) {
        this.idCurso = idCurso;
    }

    public AlumnoDTO getAlumnoDTO() {
        return alumnoDTO;
    }

    public void setAlumnoDTO(AlumnoDTO alumnoDTO) {
        this.alumnoDTO = alumnoDTO;
    }

    public CursoDTO getCursoDTO() {
        return cursoDTO;
    }

    public void setCursoDTO(CursoDTO cursoDTO) {
        this.cursoDTO = cursoDTO;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }
}
