public enum SmjerSortiranjaEnum {
    ASC("uzlazno"), DESC("silazno"), SHUFFLE("nasumicno");

    private final String opis;

    SmjerSortiranjaEnum(String opis) {
        this.opis = opis;
    }

    public String getOpis() {
        return opis;
    }
}
