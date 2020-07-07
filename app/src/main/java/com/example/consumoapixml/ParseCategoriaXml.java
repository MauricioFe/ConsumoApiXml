package com.example.consumoapixml;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

public class ParseCategoriaXml {

    public static List<Categoria> parseDados(String conteudo) {
        try {
            boolean dadosNaTag = false;
            String tagAtual = "";
            Categoria categoria = null;
            List<Categoria> categoriaList = new ArrayList<>();
            XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
            XmlPullParser parser = factory.newPullParser();

            parser.setInput(new StringReader(conteudo));
            int eventType = parser.getEventType();
            while (eventType != XmlPullParser.END_DOCUMENT) {
                switch (eventType) {
                    case XmlPullParser.START_TAG:
                        tagAtual = parser.getName();
                        if (tagAtual.equals("Categoria")) {
                            dadosNaTag = true;
                            categoria = new Categoria();
                            categoriaList.add(categoria);
                        }
                        break;
                    case XmlPullParser.END_TAG:
                        if (parser.getName().equals("Categoria")) {
                            dadosNaTag = false;
                        }
                        tagAtual = "";
                        break;

                    case XmlPullParser.TEXT:
                        if (dadosNaTag && categoria != null) {
                            switch (tagAtual) {
                                case "id":
                                    categoria.setId(Integer.parseInt(parser.getText()));
                                    break;
                                case "descricao":
                                    categoria.setDescricao(parser.getText());
                                    break;
                                default:
                                    break;
                            }
                        }
                        break;
                }
                eventType = parser.next();
            }

            return categoriaList;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
