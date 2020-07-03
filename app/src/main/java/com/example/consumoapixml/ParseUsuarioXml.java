package com.example.consumoapixml;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

public class ParseUsuarioXml {
    public static List<Usuario> parseDados(String conteudo) {
        try {
            boolean dadosNaTag = false;
            String tagAtual = "";
            Usuario usuario = null;
            List<Usuario> usuarioList = new ArrayList<>();

            XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
            XmlPullParser parser = factory.newPullParser();
            parser.setInput(new StringReader(conteudo));

            int eventType = parser.getEventType();
            while (eventType != XmlPullParser.END_DOCUMENT) {
                switch (eventType) {
                    case XmlPullParser.START_TAG:
                        tagAtual = parser.getName();
                        if (tagAtual.equals("usuarios")) {
                            dadosNaTag = true;
                            usuario = new Usuario();
                            usuarioList.add(usuario);
                        }
                        break;
                    case XmlPullParser.END_TAG:
                        if (parser.getName().equals("usuarios")) {
                            dadosNaTag = false;
                        }
                        tagAtual = "";
                        break;
                    case XmlPullParser.TEXT:
                        if (dadosNaTag && usuario != null) {
                            switch (tagAtual) {
                                case "id":
                                    usuario.setId(Integer.parseInt(parser.getText()));
                                    break;
                                case "nome":
                                    usuario.setNome(parser.getText());
                                    break;
                                case "idade":
                                    usuario.setIdade(Integer.parseInt(parser.getText()));
                                    break;
                                default:
                                    break;
                            }
                        }
                }
                eventType = parser.next();
            }
            return usuarioList;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
