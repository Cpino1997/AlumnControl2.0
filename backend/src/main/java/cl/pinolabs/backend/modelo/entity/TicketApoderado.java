package cl.pinolabs.backend.modelo.entity;

import javax.persistence.*;

@Entity
@Table(name = "ticket_apoderado")
public class TicketApoderado {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

}
