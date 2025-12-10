package Tarea35;

public class Criptomoneda {

    // Variables que coinciden con los campos del JSON de CoinLore
    private String id;
    private String name;
    private String symbol;
    private String rank;
    private String price_usd;
    private String percent_change_24h;


    public String obtenerNombre() {return name;}
    public String obtenerSimbolo() {return symbol;}
    public String obtenerId() {return id;}
    public String obtenerRanking() {return rank;}
    public String obtenerPrecioUsd() {return price_usd;}
    public String obtenerCambio24h() {return percent_change_24h;}
}