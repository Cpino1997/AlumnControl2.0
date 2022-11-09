package cl.pinolabs.backend.dto.entity;

import javax.persistence.Column;

public class TicketDTO {
    private Integer id;
    private String obs;
    private Integer idaux;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getObs() {
        return obs;
    }

    public void setObs(String obs) {
        this.obs = obs;
    }

    public Integer getIdaux() {
        return idaux;
    }

    public void setIdaux(Integer idaux) {
        this.idaux = idaux;
    }
}
