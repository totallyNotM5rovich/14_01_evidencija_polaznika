public class NeispravniPodaciException extends Exception {
    private String dodatnoObjasnjenje;
    public NeispravniPodaciException(String dodatnoObjasnjenje) {
        super("Neispravan unos! ");
        this.dodatnoObjasnjenje = dodatnoObjasnjenje;
    }

    @Override
    public String getMessage() {
        return super.getMessage() + dodatnoObjasnjenje;
    }
}