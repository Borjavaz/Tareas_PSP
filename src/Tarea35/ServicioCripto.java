package Tarea35;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.google.gson.JsonParser;
import com.google.gson.JsonObject;
import com.google.gson.JsonArray;

public class ServicioCripto {

    //variables, de la api de CoinLore y de Gson
    private static final String URL_API_LISTA = "https://api.coinlore.net/api/tickers/";
    private static final String URL_API_MONEDA = "https://api.coinlore.net/api/ticker/?id=";
    private final Gson gson = new Gson();

    /**
     * Busca la moneda por nombre o símbolo para obtener su ID.
     */
    public String buscarIdPorNombreOSimbolo(String entrada) throws Exception {
        //le paso la url de la lista de CoinLore
        String respuestaJson = enviarPeticionGet(URL_API_LISTA);

        //Obtengo datos/contenido del campo "data", que es una lista ordenada de monedas
        JsonObject datosCompletos = JsonParser.parseString(respuestaJson).getAsJsonObject();
        JsonArray arrayDatos = datosCompletos.getAsJsonArray("data");

        //paso lo que introduce el user a minusculas
        String entradaNormalizada = entrada.toLowerCase();

        //creo un bucle para recorrer cada elemento dentro de la lista
        for (int i = 0; i < arrayDatos.size(); i++) {
            JsonObject moneda = arrayDatos.get(i).getAsJsonObject();
            //Cojo los valores de los campos con nombre: "name", "symbol"
            String nombre = moneda.get("name").getAsString().toLowerCase();
            String simbolo = moneda.get("symbol").getAsString().toLowerCase();

            //si hay coincidencia en nombre y simbolo, extraigo el ID
            if (nombre.equals(entradaNormalizada) || simbolo.equals(entradaNormalizada)) {
                return moneda.get("id").getAsString();
            }
        }
        return null;
    }

    /**
     * Consulta el ticker por ID y devuelve el objeto Criptomoneda.
     */
    public Criptomoneda obtenerDatosMoneda(String idMoneda) throws Exception {
       // Le paso la URL que hace referencia a la moneda
        String urlFinal = URL_API_MONEDA + idMoneda;
        String respuestaJson = enviarPeticionGet(urlFinal);

        // resuelvo problemas de parseo, y guarda objetos en lista
        TypeToken<List<Criptomoneda>> tipoLista = new TypeToken<List<Criptomoneda>>() {};
        List<Criptomoneda> listaMonedas = gson.fromJson(respuestaJson, tipoLista.getType());

        //compruebo si el parseo fue exitoso
        if (listaMonedas != null && !listaMonedas.isEmpty()) {
            return listaMonedas.get(0);
        }
        return null;
    }

    /**
     * Función base para realizar la Petición GET y obtener la respuesta
     */
    private String enviarPeticionGet(String urlString) throws Exception {
        //creo un objeto URL con la direccion de internet
        URL url = new URL(urlString);
        //aabro la conexión
        HttpURLConnection conexion = (HttpURLConnection) url.openConnection();
        //le indico que la petición es un GET (petición de datos)
        conexion.setRequestMethod("GET");

        //verifico que el codigo de respuesta esta bien (200) para saber que la petición fue exiosa.
        if (conexion.getResponseCode() != HttpURLConnection.HTTP_OK) {
            throw new Exception("Error HTTP: " + conexion.getResponseCode());
        }

        //Lector que se encarga de leer los datos que vienen del servidor. Lee linea por linea
        BufferedReader lector = new BufferedReader(new InputStreamReader(conexion.getInputStream()));
        String lineaEntrada;
        StringBuilder respuesta = new StringBuilder();

        while ((lineaEntrada = lector.readLine()) != null) {
            respuesta.append(lineaEntrada);
        }
        //cierro el lector
        lector.close();

        //me devuelve el contenido en String
        return respuesta.toString();
    }
}