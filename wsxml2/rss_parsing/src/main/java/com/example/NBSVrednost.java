package com.example;

import org.w3c.dom.Element;

public class NBSVrednost {
    private String valuta;
    private String vrednost;

    public String getValuta() {
        return valuta;
    }
    public String getVrednost() {
        return vrednost;
    }
    public static NBSVrednost fromElements(Element valuta, Element vrednost) {
        NBSVrednost nbs = new NBSVrednost();
        nbs.valuta = valuta.getTextContent();
        nbs.vrednost = vrednost.getTextContent();
        return nbs;
    }
}
