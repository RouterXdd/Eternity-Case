package eternity.content;

import mindustry.game.*;

public class EtSchematics {
    public static Schematic
            commBase, defaultFrost;
    public static void load(){
        commBase = Schematics.readBase64("bXNjaAF4nGNgZmBmZmDJS8xNZRBwzs/NTcxLcUosTg1OzkjNZeBOSS1OLsosKMnMz2NgYGDLSUxKzSlmYIqOZWSQSi1JLcrLLKnUTQaq102G6NVNAnKAShlBiBFIAgEAe6wZ+w==");
        defaultFrost = Schematics.readBase64("bXNjaAF4nBWLOwqAMBAFX4zY2Fl5iZxC8AKWYhHXFQP5SLKNtzeBYaoZaGiNPtrAmJaUec2pyEYPByuOMF5cKLtXXIoABm9P9gXdfijMLJyjk8+QLWyo3uZuew1Vo+oHLFgaVQ==");
    }
}
