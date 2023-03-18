package App;
public class Ronda {
    private String nro;
    private Partido[] partidos;

    public Ronda(String  nro, Partido[] partidos) {
        this.nro = nro;
        this.partidos = partidos;
    }
    public String getNro() {
        return this.nro;
    }
    public Partido[] getPartidos() {
        return this.partidos;
    }
    
    public void setNro(String nro) {
        this.nro = nro;
    }
    public void setPartidos(Partido[] partidos) {
        this.partidos = partidos;
    }
    
}
