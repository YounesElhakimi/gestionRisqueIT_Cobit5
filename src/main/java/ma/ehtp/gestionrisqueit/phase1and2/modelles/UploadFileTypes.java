package ma.ehtp.gestionrisqueit.modelles;


public enum UploadFileTypes {
    DOC_PHASE1_ITAPE1( "Please specify the different documents needed for the implementation of the roadmap:"),
    DOC_PHASE1_ITAPE2("Please specify the different documents needed for the implementation of the roadmap"),
    DOC_PHASE1_ITAPE3("Please specify the different documents needed for the implementation of the roadmap:"),
    DOC_PHASE2_ITAPE1("Please specify the different documents needed for the implementation of the roadmap:"),
    DOC_PHASE3_ITAPE1("Please specify the different documents needed for the implementation of the roadmap:"),
    DOC_PHASE3_ITAPE2("Please specify the different documents needed for the implementation of the roadmap:"),
    DOC_PHASE4_ITAPE1("Please specify the different documents needed for the implementation of the roadmap:"),
    DOC_PHASE4_ITAPE2("Please specify the different documents needed for the implementation of the roadmap:"),
    DOC_PHASE4_AUDITS_CONTROLS("Please upload reports of evaluations, audits and controls:"),
    DOC_PHASE5_ITAPE1(" Please specify the different documents needed for the implementation of the roadmap:"),
    DOC_PHASE5_ITAPE2(" Please specify the different documents needed for the implementation of the roadmap:"),

    ;

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

    public static UploadFileTypes isExist(String text) {
        for (UploadFileTypes b : UploadFileTypes.values()) {
            if (b.text.equalsIgnoreCase(text)) {
                return b;
            }
        }
        return null;
    }
}