package ma.ehtp.gestionrisqueit.services;


public enum UploadFileTypes {
    A("text1"),
    B("text2"),
    C("text3"),
    D("text4");

    private String text;

    UploadFileTypes(String text) {
        this.text = text;
    }

    public String getText() {
        return this.text;
    }

    public static UploadFileTypes fromString(String text) {
        for (UploadFileTypes b : UploadFileTypes.values()) {
            if (b.text.equalsIgnoreCase(text)) {
                return b;
            }
        }
        return null;
    }
}