public class ResultadoCambio {
    
    private String urlString;
    private String tasa;
    private String monedaCambio;

    // Constructor
    public ResultadoCambio(String urlString, String tasa, String monedaCambio) {
        this.urlString = urlString;
        this.tasa = tasa;
        this.monedaCambio = monedaCambio;
    }

    // Getters
    public String getUrlString() {
        return urlString;
    }

    public String getTasa() {
        return tasa;
    }

    public String getMonedaCambio() {
        return monedaCambio;
    }
}
