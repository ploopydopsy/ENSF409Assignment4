package edu.ucalgary.oop;

public class StreamingPlatform {
    private final String NAME;
    private Content[] catalog = new Content[0];
    private Boolean[] premium = new Boolean[0];

    public StreamingPlatform(String name) {
        this.NAME = name;
    }

    public String getName() {
        return NAME;
    }

    public Content[] getCatalog() {
        return catalog;
    }

    public void addContent(Content media, Boolean isPremium) {
        Content[] newCatalog = new Content[catalog.length + 1];
        Boolean[] newPremium = new Boolean[premium.length + 1];

        System.arraycopy(catalog, 0, newCatalog, 0, catalog.length);
        System.arraycopy(premium, 0, newPremium, 0, premium.length);

        newCatalog[catalog.length] = media;
        newPremium[premium.length] = isPremium;

        catalog = newCatalog;
        premium = newPremium;
    }

    public void removeContent(Content media) {
        int index = -1;
        for (int i = 0; i < catalog.length; i++) {
            if (catalog[i] == media) {
                index = i;
                break;
            }
        }
        if (index == -1) return;

        Content[] newCatalog = new Content[catalog.length - 1];
        Boolean[] newPremium = new Boolean[premium.length - 1];

        int j = 0;
        for (int i = 0; i < catalog.length; i++) {
            if (i != index) {
                newCatalog[j] = catalog[i];
                newPremium[j] = premium[i];
                j++;
            }
        }
        catalog = newCatalog;
        premium = newPremium;
    }

    public String playContent(Content media) throws ContentAccessRestrictedException {
        for (int i = 0; i < catalog.length; i++) {
            if (catalog[i].equals(media)) {
                return catalog[i].toString();
            }
        }
        throw new ContentAccessRestrictedException("Content unavailable");
    }

    public boolean isPremium(Content media) {
        for (int i = 0; i < catalog.length; i++) {
            if (catalog[i].equals(media)) {
                return premium[i];
            }
        }
        return false;
    }
}
