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

@WebServlet("/trm")
public class TrmServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String date = "2023-12-31"; // Cambia la fecha según sea necesario
        String urlString = String.format("https://trm-colombia.vercel.app/?date=%s", date);

        // Hacer la petición a la API del TRM
        URL url = new URL(urlString);
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
        JSONObject data = json.getJSONObject("data");
        double trmValue = data.getDouble("value");

        // Enviar el resultado a la página JSP
        request.setAttribute("trm", trmValue);
        request.getRequestDispatcher("trm.jsp").forward(request, response);
    }
}
