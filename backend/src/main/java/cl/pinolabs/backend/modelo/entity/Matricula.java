package cl.pinolabs.backend.modelo.entity;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name="matricula")
public class Matricula {

    @EmbeddedId
    private MatriculaPK id;
    @Column(name = "fecha_matricula")
    private LocalDate fecha;
    @ManyToOne
    @MapsId("idAlumno")
    private Alumno alumno;
    @ManyToOne
    @MapsId("idCurso")
    private Curso curso;

    public MatriculaPK getId() {
        return id;
    }

    public void setId(MatriculaPK id) {
        this.id = id;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public Alumno getAlumno() {
        return alumno;
    }

    public void setAlumno(Alumno alumno) {
        this.alumno = alumno;
    }

    public Curso getCurso() {
        return curso;
    }

    public void setCurso(Curso curso) {
        this.curso = curso;
    }
}
