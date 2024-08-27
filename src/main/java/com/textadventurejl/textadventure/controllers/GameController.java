package com.textadventurejl.textadventure.controllers;

import com.textadventurejl.textadventure.models.GameState;
import com.textadventurejl.textadventure.models.Item;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class GameController {

    private GameState gameState = new GameState();

    @GetMapping("/game")
    public String showGame(Model model) {
        model.addAttribute("gameState", gameState);
        model.addAttribute("inventario", gameState.getInventario().listarItens());
        return "game";
    }

    @PostMapping("/game")
    public String processInput(@RequestParam String input, Model model) {
        processCommand(input.toLowerCase());
        model.addAttribute("gameState", gameState);
        model.addAttribute("inventario", gameState.getInventario().listarItens());
        return "game";
    }

    private void processCommand(String input) {
        switch (gameState.getLocation()) {
            case "start":
                handleStart(input);
                break;
            case "floresta":
                Floresta(input);
                break;
            default:
                gameState.setMessage("Comando não reconhecido.");
        }
    }
    private String mostrarInventario() {
        StringBuilder inventarioMsg = new StringBuilder();
        for (Item item : gameState.getInventario().listarItens()) {
            inventarioMsg.append("<div>").append(item.getNome()).append("</div>");
            inventarioMsg.append("<div>").append(item.getQuantidade()).append("</div>");
        }
        return inventarioMsg.toString();
    }
    private void handleStart(String input) {
        if (input.contains("start")) {
            gameState.setLocation("floresta");
            gameState.setMessage("Você está em uma floresta escura. O que você quer fazer?");
        } else {
            gameState.setMessage("Digite 'start' para começar o jogo.");
        }
    }

    private void Floresta(String input) {
        if (input.contains("explorar")) {
            gameState.setMessage("Você explora a floresta e encontra um caminho escondido, você acha um galho no chão");
        } else if (input.contains("pegar galho")) {
            Item galho = new Item("Galho", 1, 1);
            gameState.getInventario().adicionarItem(galho);
            gameState.setMessage("Você pegou um galho. parece ser útil, você deseja seguir em frente");
        }else if (input.contains("seguir")) {
            gameState.setLocation("floresta");
            gameState.setMessage("Você segue o caminho e chega a uma caverna misteriosa, está escuro e você encontra uma lanterna");
        } else if (input.contains("pegar lanterna")) {
            Item lanterna = new Item("Lanterna", 2, 1);
            gameState.getInventario().adicionarItem(lanterna);
            gameState.setMessage("Você pegou a lanterna, melhor usar ela");
        } else if (input.contains("usar lanterna")) {
            gameState.setMessage("Consegui usar a lanterna mas ela está meio fraca, espero encontrar algumas pilhas");
        } else {
            gameState.setMessage("Comando não reconhecido.");
        }
    }
}
