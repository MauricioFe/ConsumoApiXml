package com.example.consumoapixml;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class ParseUsuarioJson {
    public static List<Usuario> parseDados(String conteudo) {
        try {
            List<Usuario> usuarioList = new ArrayList<>();
            JSONArray jsonArray = new JSONArray(conteudo);

            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                Usuario usuario = new Usuario();
                usuario.setNome(jsonObject.getString("Nome"));
                usuarioList.add(usuario);
            }
            return usuarioList;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
