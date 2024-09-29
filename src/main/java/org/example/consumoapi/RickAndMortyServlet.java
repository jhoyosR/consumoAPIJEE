package org.example.consumoapi;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

@WebServlet("/rickandmorty")
public class RickAndMortyServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Hacer la petición a la API de Rick and Morty
        URL url = new URL("https://rickandmortyapi.com/api/character/1"); // Obtén el personaje con ID 1
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("GET");

        // Leer la respuesta
        BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuilder content = new StringBuilder();
        while ((inputLine = in.readLine()) != null) {
            content.append(inputLine);
        }
        in.close();
        con.disconnect();

        // Procesar la respuesta JSON
        JSONObject json = new JSONObject(content.toString());
        String characterName = json.getString("name");

        // Enviar el resultado a la página JSP
        request.setAttribute("character", characterName);
        request.getRequestDispatcher("rickandmorty.jsp").forward(request, response);
    }
}

