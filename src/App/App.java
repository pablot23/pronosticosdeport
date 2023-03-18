package App;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

public class App {

    // public  List<Partido> partidosList = new ArrayList<Partido>();
   // public  List<Pronostico> pronosticosList = new ArrayList<Pronostico>();

    public static  void main(String[] args) throws Exception {

        // Leo los resultados y obtengo un array de los pronosticos
        List<Partido> partidosList = leerResultados();
        List<Pronostico> pronosticosList = leerPronosticos(partidosList);
        
        // Calculo los puntos
        int puntos= 0;
        for (Pronostico p : pronosticosList ) {
             puntos += p.getPuntos();
        }
        System.out.println("Puntos obtenidos: " + puntos);        
    }

    static List<Partido> leerResultados() {
        // inicializo variables
        int nroDeRonda = 1; 
        int nroDeMatch = 0; // inicializo en 0, por def el archivo viene en orden
       // Ronda ronda = new Ronda(nroDeRonda, null);
        Scanner scResu = null;
   
        File resultados = new File("C:\\Users\\Willy\\Desktop\\TrabajoPrácticoIntegrador\\PronosticosDeportivos\\PronDep\\resultados.csv");
        // leer archivo resultados.cvs y lo guardo en una coleccion de objetos Partido
        List<Partido> partidosList = new ArrayList<Partido>();
        try {
            scResu = new Scanner(resultados);
            while (scResu.hasNextLine()) {
                nroDeMatch++;        
                // Objetivo1, crear los objetos de la info que se encuentran en el archivo resultados.csv
                String[] datos = scResu.nextLine().split(",");
                // leo datos

                int    goles1  = Integer.parseInt(datos[1]);
                int    goles2  = Integer.parseInt(datos[3]);
      
                // agrego el partido al array de partidos
            
                partidosList.add(new Partido(new Equipo(datos[0], ""), new Equipo(datos[2], ""), goles1, goles2, nroDeRonda, nroDeMatch));
            }
            scResu.close();         
            return partidosList;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return null;
        } 
    } 

    static List<Pronostico> leerPronosticos(List<Partido> partidosList) {

      // inicializo variables
      int nroDeRonda = 1;
   //   Ronda ronda = new Ronda(nroDeRonda, null);
      ResultadoEnum resultado = null;

      Equipo equipoUno = new Equipo(null, null);
      Equipo equipoDos = new Equipo(null, null);

      int nroDeMatch = 0;
      // inicializo variables
      // crear una variable de tipo Equipo


      Pronostico pronostico = new Pronostico(null, null, null, nroDeRonda, 0);
      File pronosticos = new File("C:\\Users\\Willy\\Desktop\\TrabajoPrácticoIntegrador\\PronosticosDeportivos\\PronDep\\pronosticos.csv");
      Scanner scPron = null;        
      List<Pronostico> pronosticosList = new ArrayList<Pronostico>();
      try {
          
          scPron = new Scanner(pronosticos);

          while (scPron.hasNextLine()) {
              nroDeMatch++;             
              // Objetivo1, crear los objetos de la info que se encuentran en el archivo resultados.csv
              String[] datos = scPron.nextLine().split(",");

              // busco en el array de resultados ya leido
              for (Partido partido : partidosList) {
                  if(partido.getMatchNro() == nroDeMatch){
                      pronostico.setPartido(partido);
                      equipoUno = partido.getEquipo1();
                      equipoDos = partido.getEquipo2();
                  }
              }

              if(datos[1]=="X"){
                // si gana1 = x;        
                pronostico.setResultadoPronosticado(resultado.GANADOR);
                pronostico.setEquipo(equipoUno);
               }
    
               if(datos[2]=="X"){
                // empate = true;
                pronostico.setResultadoPronosticado(resultado.EMPATE);
                pronostico.setEquipo(equipoDos);
               }
               if(datos[3]=="X"){
                // gana2 = true;
                pronostico.setResultadoPronosticado(resultado.GANADOR);
                pronostico.setEquipo(equipoDos);
               }
               pronostico.setMatchNro(nroDeMatch);
               pronostico.setRondaNro(nroDeRonda);
                 // agrego el partido al array de partidos
                 pronosticosList.add(pronostico);     
            }
          
          scPron.close();
          return pronosticosList;  

      } catch (FileNotFoundException e) {
          e.printStackTrace();
          return null;
      } 

    }

}
