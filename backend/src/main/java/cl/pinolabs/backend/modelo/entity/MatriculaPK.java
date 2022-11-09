package cl.pinolabs.backend.modelo.entity;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class MatriculaPK implements Serializable {
    @Column(name="id_alumno")
    private int idAlumno;
    @Column(name="id_alumno")
    private int idCurso;

    public int getIdAlumno() {
        return idAlumno;
    }

    public void setIdAlumno(int idAlumno) {
        this.idAlumno = idAlumno;
    }

    public int getIdCurso() {
        return idCurso;
    }

    public void setIdCurso(int idCurso) {
        this.idCurso = idCurso;
    }

    public MatriculaPK() {
    }

    public MatriculaPK(int idAlumno, int idCurso) {
        this.idAlumno = idAlumno;
        this.idCurso = idCurso;
    }
}