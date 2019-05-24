package co.edu.sena.dwbh.service.dto;

import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the FichaHasTrimestre entity.
 */
public class FichaHasTrimestreDTO implements Serializable {

    private Long id;

    private Long trimestreId;

    private String trimestreNombreTrimestre;

    private Long fichaId;

    private String fichaNumeroFicha;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getTrimestreId() {
        return trimestreId;
    }

    public void setTrimestreId(Long trimestreId) {
        this.trimestreId = trimestreId;
    }

    public String getTrimestreNombreTrimestre() {
        return trimestreNombreTrimestre;
    }

    public void setTrimestreNombreTrimestre(String trimestreNombreTrimestre) {
        this.trimestreNombreTrimestre = trimestreNombreTrimestre;
    }

    public Long getFichaId() {
        return fichaId;
    }

    public void setFichaId(Long fichaId) {
        this.fichaId = fichaId;
    }

    public String getFichaNumeroFicha() {
        return fichaNumeroFicha;
    }

    public void setFichaNumeroFicha(String fichaNumeroFicha) {
        this.fichaNumeroFicha = fichaNumeroFicha;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        FichaHasTrimestreDTO fichaHasTrimestreDTO = (FichaHasTrimestreDTO) o;
        if (fichaHasTrimestreDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), fichaHasTrimestreDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "FichaHasTrimestreDTO{" +
            "id=" + getId() +
            ", trimestre=" + getTrimestreId() +
            ", trimestre='" + getTrimestreNombreTrimestre() + "'" +
            ", ficha=" + getFichaId() +
            ", ficha='" + getFichaNumeroFicha() + "'" +
            "}";
    }
}
