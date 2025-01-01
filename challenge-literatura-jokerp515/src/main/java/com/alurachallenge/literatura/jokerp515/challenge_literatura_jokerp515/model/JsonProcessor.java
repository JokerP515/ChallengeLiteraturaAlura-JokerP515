package com.alurachallenge.literatura.jokerp515.challenge_literatura_jokerp515.model;

import java.util.List;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

public class JsonProcessor {
    private final Gson gson;

    public JsonProcessor() {
        this.gson = new Gson();
    }

    public Book processFirstBook(String jsonResponse) {
        // Parsear el JSON
        JsonObject jsonObject = gson.fromJson(jsonResponse, JsonObject.class);

        // Verificar si hay resultados
        int count = jsonObject.get("count").getAsInt();
        if (count < 1) {
            //System.out.println("No hay resultados en el JSON.");
            return null; // Retornar null si no hay resultados
        }

        // Obtener el primer resultado (results[0])
        JsonArray resultsArray = jsonObject.getAsJsonArray("results");
        JsonObject firstResult = resultsArray.get(0).getAsJsonObject();

        // Obtener datos del libro
        String title = firstResult.get("title").getAsString();
        JsonArray languagesJson = firstResult.getAsJsonArray("languages");
        List<String> languages = gson.fromJson(languagesJson, List.class);
        int downloadCount = firstResult.get("download_count").getAsInt();

        // Obtener datos del autor
        JsonArray authorsArray = firstResult.getAsJsonArray("authors");
        if (authorsArray.size() < 1) {
            System.out.println("El libro no tiene un autor definido.");
            return null; // Retornar null si no hay autores
        }
        JsonObject authorJson = authorsArray.get(0).getAsJsonObject();
        String authorName = authorJson.get("name").getAsString();
        int birthYear = authorJson.has("birth_year") ? authorJson.get("birth_year").getAsInt() : 0;
        int deathYear = authorJson.has("death_year") ? authorJson.get("death_year").getAsInt() : 0;

        // Crear el autor
        Author author = new Author(authorName, birthYear, deathYear);

        // Crear y retornar el libro
        return new Book(title, languages, downloadCount, author);
    }
}