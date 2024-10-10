import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

public class App {
    public static void main(String[] args) {

        int opciones = 0;
        Scanner lectura = new Scanner(System.in);

        while (true) {
            System.out.println("************************************");
            System.out.println("Bienvenido al Conversor de Monedas =]");
            System.out.println("1. Dólar =>> Peso Colombiano");
            System.out.println("2. Peso Colombiano =>> Dólar");
            System.out.println("3. Dólar =>> Real Brasileño");
            System.out.println("4. Real Brasileño =>> Dólar");
            System.out.println("5. Dólar =>> Peso Argentino");
            System.out.println("6. Peso Argentino =>> Dólar");
            System.out.println("7. Salir");
            System.out.print("Elige una opcion valida: ");

            opciones = lectura.nextInt();
            lectura.nextLine();

            if (opciones == 7){
                System.out.println("Saliendo del programa. ¡Hasta luego!");
                break;
            }

            while (opciones > 7 || opciones < 1) {
                System.out.print("Elige una opcion valida: ");
                opciones = lectura.nextInt();
                lectura.nextLine();

                if (opciones == 7){
                    System.out.println("Saliendo del programa. ¡Hasta luego!");
                    break;
                }
            }

            if (opciones == 7){
                break;
            }

            System.out.print("Ingrese el valor que desea convertir: ");
            double valorConvertir = lectura.nextDouble();

            ConsultaTasaDeCambio convertir = new ConsultaTasaDeCambio();
            ResultadoCambio resultado = convertir.buscarOpcionCambio(opciones, valorConvertir);

            // Obtener los valores retornados
            String urlString = resultado.getUrlString();
            String tasa = resultado.getTasa();
            String monedaCambio = resultado.getMonedaCambio();

            try {
                // Crear la conexión
                URL url = new URL(urlString);
                HttpURLConnection conexion = (HttpURLConnection) url.openConnection();
                conexion.setRequestMethod("GET");

                // Comprobar el código de respuesta
                int responseCode = conexion.getResponseCode();
                if (responseCode == 200) {
                    // Leer la respuesta
                    BufferedReader in = new BufferedReader(new InputStreamReader(conexion.getInputStream()));
                    StringBuilder response = new StringBuilder();
                    String inputLine;

                    while ((inputLine = in.readLine()) != null) {
                        response.append(inputLine);
                    }
                    in.close();

                    // * Parsear el JSON usando Gson
                    Gson gson = new Gson();
                    JsonObject jsonObject = gson.fromJson(response.toString(), JsonObject.class);

                    // Extraer valores
                    String baseCode = jsonObject.get("base_code").getAsString();
                    double conversion = jsonObject.getAsJsonObject("conversion_rates").get(tasa).getAsDouble();

                    // Imprimir resultados
                    System.out.println("************************************");
                    System.out
                            .println("El valor " + valorConvertir + " " + baseCode + " a " + monedaCambio + " es de:");
                    System.out.println((valorConvertir * conversion) + "$");

                } else {
                    System.out.println("Error: " + responseCode);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
           
        }

        lectura.close();

    }
}
