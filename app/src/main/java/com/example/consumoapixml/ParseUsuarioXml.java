package com.example.consumoapixml;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
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
            while (eventType != XmlPullParser.END_DOCUMENT){
                
            }
            return usuarioList;
        } catch (XmlPullParserException e) {
            e.printStackTrace();
            return null;
        }
    }
}
