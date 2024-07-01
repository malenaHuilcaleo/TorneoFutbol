import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class pruebas {
    public static void main(String[] args) {
        Equipo[] equipos=new Equipo[10];
        //String archivoEquipos= "Equipos.txt";
        //String archivoJugadores="Jugadores.txt";
        //procesarListaEquipos(archivoEquipos, equipos);  
        //procesarListaJugadores(archivoJugadores, equipos);
        
        Jugador [] todosLosJugadores={
    new Jugador("González", "Juan", 12345678),
    new Jugador("López", "María", 23456789),
    new Jugador("Martínez", "Carlos", 34567890),
    
   
    
   
 
  

  
    //150


        };
       long startTime = System.nanoTime();
// Llamar al método de ordenamiento a probar (Bubble Sort, Merge Sort, etc.)/*
        ordenarJugadoresSegunNombre(todosLosJugadores); // Ejemplo: Llamar al método de ordenamiento a probar
        
        long endTime = System.nanoTime();

        long tiempoEjecucion = endTime - startTime;

        System.out.println("tiempo de ejecucion burbuja "+tiempoEjecucion+"ms");
  /*  
        //mergesort
      long startTime1 = System.nanoTime();
        mergeSortPorNombre(todosLosJugadores, 0, todosLosJugadores.length-1); // Ejemplo: Llamar al método de ordenamiento a probar
        
         long endTime1 = System.nanoTime();

        long  tiempoEjecucion1 = endTime1 - startTime1;

        System.out.println("tiempo de ejecucion merge "+tiempoEjecucion1+"ns");
  */ 

    }
    public static Jugador[]obtenerTodosLosJugadores(Equipo[]arr){
        //recorre el arreglo equipo y retorna un arreglo con todos los jugadores
        
        int i=0, cantJugadoresTotal=0;
        boolean cantCompletada=false, cargaCompleta=false;
        //calculo la longitud del arreglo
        while(i<arr.length&& !cantCompletada){
            if(arr[i]!=null){
                cantJugadoresTotal+=arr[i].getCantJugadores(); 
                i++; 
            }else{
                cantCompletada=true;
            }
        }
        Jugador[] todosLosJugadores= new Jugador [cantJugadoresTotal];
        //cargo el arreglo de todos los jugadores
        int indice=0;
        i=0;
        while(i<arr.length&&!cargaCompleta){
            if(arr[i]!=null){
                Jugador[]temp=arr[i].getJugadores();
                int j=0;
                while(j<arr[i].getCantJugadores()){
                    if(temp[j]!=null){
                        todosLosJugadores[indice++]=temp[j];
                    }
                    j++;
                }

            }else{
                cargaCompleta=true;
            }
            i++;
        }
        return todosLosJugadores;
    }
    
    //burbuja
    public static void ordenarJugadoresSegunNombre(Jugador[]arr){
        Jugador aux;
        int n=arr.length,i=0,j;
        boolean ordenado=false;

        while(i< n-1 && !ordenado){
            ordenado=true;
            for(j=0; j<n-i-1; j++){
                if(arr[j].compareToNombres(arr[j+1])>0){
                    ordenado= false;
                    aux=arr[j];
                    arr[j]=arr[j+1];
                    arr[j+1]=aux;
                }

            }
            i++;
        }

    }

    //mergesort


    public static void mergeSortPorNombre(Jugador[] arr, int ini, int fin) {
        if (ini < fin) {
            int mitad = (ini + fin) / 2;
            mergeSortPorNombre(arr, ini, mitad);
            mergeSortPorNombre(arr, mitad + 1, fin);
            merge(arr, ini, mitad, fin);
        }
    }
    public static void merge(Jugador[] arr, int ini, int mitad, int fin) {
        int posA = ini, posB = mitad + 1, posC = 0;
        Jugador[] aux = new Jugador[fin - ini + 1];

        while (posA <= mitad && posB <= fin) {
            if (arr[posA].compareToNombres(arr[posB]) < 0) {
                aux[posC++] = arr[posA++];
            } else {
                aux[posC++] = arr[posB++];
            }
        }

        while (posA <= mitad) {
            aux[posC++] = arr[posA++];
        }

        while (posB <= fin) {
            aux[posC++] = arr[posB++];
        }
        posC=0;
        while (posC<aux.length) {
            arr[ini + posC] = aux[posC];
            posC++;
        }
    }



    public static void procesarListaEquipos(String nombeArchivo,Equipo[]arr){
        int i=0;
    
        try{
            FileReader leerArchivo = new FileReader(nombeArchivo);
            BufferedReader blectura = new BufferedReader(leerArchivo);
            String linea = null;

            while ((linea = blectura.readLine()) != null && i < arr.length) {
            // Dividir la línea usando split con el delimitador ";"
                String[] datosEquipo = linea.split(";");

            // Asegurarse de que la línea tiene el formato correcto
                if (datosEquipo.length == 2) {
                    String nombre = datosEquipo[0].trim();
                    char categoria = datosEquipo[1].trim().charAt(0);

                    Equipo eq = new Equipo(nombre, categoria);
                    arr[i] = eq;
                     i++;
                }
            }
            blectura.close();
        }catch (FileNotFoundException ex) {
            System.err.println(ex.getMessage());
        }
        catch (IOException ex) {
            System.err.println("Error leyendo algun archivo.");
        }
    }
    public static void procesarListaJugadores(String nombeArchivo,Equipo[]arr){
        

        try{
            FileReader leerArchivo = new FileReader(nombeArchivo);
            BufferedReader blectura = new BufferedReader(leerArchivo);
            String linea = null;

            while ((linea = blectura.readLine()) != null) {
                // Dividir la línea usando split con el delimitador ";"
                String[] datosJugador = linea.split(";");

                // Asegurarse de que la línea tiene el formato correcto
                if (datosJugador.length == 6) {
                    String apellido = datosJugador[0].trim();
                    String nombre = datosJugador[1].trim();
                    int edad = Integer.parseInt(datosJugador[2].trim());
                    int dni = Integer.parseInt(datosJugador[3].trim());
                    int numCamiseta = Integer.parseInt(datosJugador[4].trim());
                    String nomEquipo = datosJugador[5].trim();

                    int pos = buscarPosEquipo(arr, nomEquipo);
                    if (pos != -1) {
                        Jugador nuevoJug = new Jugador(apellido, nombre, dni, edad, numCamiseta, nomEquipo);
                        verificaryCargarNuevoJugador(arr, nuevoJug);
                    }
                }
            
            }
            blectura.close();
        }catch (FileNotFoundException ex) {
            System.err.println(ex.getMessage() + "\nSignifica que el archivo del "       + "que queriamos leer no existe.");
        }
        catch (IOException ex) {
            System.err.println("Error leyendo algun archivo.");
        }
    }










    public static boolean verificaryCargarNuevoJugador(Equipo[]arrEquipos, Jugador nuevJugador){
        boolean cargaExitosa=false;
        
        String nombreEquipo=nuevJugador.getNomEquipo();
        //busco el equipo
        int pos=buscarPosEquipo(arrEquipos, nombreEquipo);
        if(pos!=-1){
            //el equipo existe puedo continuar 
            int dni= nuevJugador.getDni();
            //verifico que no este cargado en ningun equipo
            if(!jugadorExisteEnEquipos(arrEquipos, dni)){

                if(arrEquipos[pos].agregarJugador(nuevJugador))
                    cargaExitosa=true; 
            }
        }
        return cargaExitosa;
    }
    public static boolean jugadorExisteEnEquipos(Equipo[] arrEquipos, int dni){
        int i=0;
        boolean existeEnEquipo=false;
        while(i<arrEquipos.length && arrEquipos[i]!=null&&!existeEnEquipo){
                existeEnEquipo= arrEquipos[i].buscarJugadorPorDni(dni)!=null;
                i++;
            }
        return existeEnEquipo;
    }


    public static int buscarPosEquipo(Equipo[] equipos, String nombreClub){
        //busco la posicion del equipo
        int posicionEquipo=-1, i=0;
        boolean encontrado=false;

        while(!encontrado&& i<10 && equipos[i]!=null){
            if(nombreClub.equalsIgnoreCase(equipos[i].getNombre())){
                posicionEquipo=i;
                encontrado=true;
            }
            i++;
        }
    return posicionEquipo; 
}

}
