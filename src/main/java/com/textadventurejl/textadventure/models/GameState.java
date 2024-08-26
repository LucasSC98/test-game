package com.textadventurejl.textadventure.models;

public class GameState {
    private String location;
    private String message;
    private Inventario inventario;

    public GameState() {
        this.location = "start";
        this.message = "Digite 'start' para começar o jogo.";
        this.inventario = new Inventario();
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Inventario getInventario() {
        return inventario;
    }
}
