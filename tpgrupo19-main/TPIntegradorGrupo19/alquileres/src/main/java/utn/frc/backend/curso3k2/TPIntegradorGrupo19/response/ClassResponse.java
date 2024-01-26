package utn.frc.backend.curso3k2.TPIntegradorGrupo19.response;

public class ClassResponse {

    private String moneda;
    private Double importe;

    public ClassResponse(String moneda, Double importe) {
        this.moneda = moneda;
        this.importe = importe;
    }

    public ClassResponse() {

    }

    public String getMoneda() {
        return moneda;
    }

    public void setMoneda(String moneda) {
        this.moneda = moneda;
    }

    public Double getImporte() {
        return importe;
    }

    public void setImporte(Double importe) {
        this.importe = importe;
    }

    @Override
    public String toString() {
        return "MyResponseClass{" +
                "moneda='" + moneda + '\'' +
                ", importe=" + importe +
                '}';
    }
}
