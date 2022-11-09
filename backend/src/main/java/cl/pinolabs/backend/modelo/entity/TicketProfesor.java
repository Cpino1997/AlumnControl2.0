package cl.pinolabs.backend.modelo.entity;
import javax.persistence.*;

@Entity
@Table(name = "ticket_profe")
public class TicketProfesor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

}
