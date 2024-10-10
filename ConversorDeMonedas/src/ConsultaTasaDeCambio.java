
public class ConsultaTasaDeCambio {


    public ResultadoCambio buscarOpcionCambio(int opciones, double valorConvertir){

        String urlString = "NULL";
        String tasa = "USD";
        String monedaCambio = "Dólar";

        switch (opciones) {
            case 1:
                urlString = "https://v6.exchangerate-api.com/v6/8efe7f1f62ee382d193f3017/latest/USD";
                tasa = "COP";
                monedaCambio = "Peso Colombiano";
                break;
            case 2:
                urlString = "https://v6.exchangerate-api.com/v6/8efe7f1f62ee382d193f3017/latest/COP";
                tasa = "USD";
                monedaCambio = "Dólar Estadounidense";
                break;
            case 3:
                urlString = "https://v6.exchangerate-api.com/v6/8efe7f1f62ee382d193f3017/latest/USD";
                tasa = "BRL";
                monedaCambio = "Real Brasileño";
                break;
            case 4:
                urlString = "https://v6.exchangerate-api.com/v6/8efe7f1f62ee382d193f3017/latest/BRL";
                tasa = "USD";
                monedaCambio = "Dólar Estadounidense";
                break;
            case 5:
                urlString = "https://v6.exchangerate-api.com/v6/8efe7f1f62ee382d193f3017/latest/USD";
                tasa = "ARS";
                monedaCambio = "Peso Argentino";
                break;
            case 6:
                urlString = "https://v6.exchangerate-api.com/v6/8efe7f1f62ee382d193f3017/latest/ARS";
                tasa = "USD";
                monedaCambio = "Dólar Estadounidense";
                break;
            default:
                System.out.println("Opcion no es valida");
                break;    

        }

        return new ResultadoCambio(urlString, tasa, monedaCambio);

    }

}    


