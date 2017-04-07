import java.util.*;

/**
 * Programa para calcular todos los supersets posibles formados por n elementos dados
 * 
 * @author Ernesto Echeverría González
 * @since 07-04-2017
 * @version 1.0.0
 */
public class SuperSets {
  // char[] datos = {'A', 'B', 'C', 'D', 'E'};
  
  public static ArrayList<Set<Set<Object> > > possibleSetTree(Object[] datos) {
    // Guardamos el tamaño máximo (o tamaño del conjunto formado por todos los elementos)
    final int tamanio = datos.length;
    // Creamos el set de primer nivel (conjuntos de tamaño 1) y el vector de conjuntos
    Set<Set<Object> > firstLevel = new HashSet<Set<Object> >();
    ArrayList<Set<Set<Object> > > vectorSet = new ArrayList<Set<Set<Object> > >();
  
    // Rellenamos el primer nivel y lo añadimos al vector de conjuntos
    for(int i = 0; i < datos.length; i++) {
      Set<Object> auxiliar = new HashSet<Object>();
      auxiliar.add(datos[i]);
      firstLevel.add(auxiliar);
    }
    vectorSet.add(firstLevel);
  
    // Creamos los demás niveles y los añadimos hasta completar el tamaño máximo
    Set<Set<Object> > nLevel = new HashSet<Set<Object> >(firstLevel);
    for(int k = 0; k < tamanio; k++) {
      Set<Set<Object> > contenedor = new HashSet<Set<Object> >();
      for(int i = 0; i < datos.length; i++) {
        for(Set<Object> iterator1: firstLevel) {
          for(Set<Object> iterator2: nLevel) {
            Set<Object> union = new HashSet<Object>(iterator1);
            union.addAll(iterator2);
            if(union.size() == (k + 1)) 
            contenedor.add(union);
          }
        }
      }
      vectorSet.add(contenedor);
      nLevel = contenedor;
    }
    vectorSet.remove(0);
    return vectorSet;
  }
  
  // Ejemplo de prueba con un set inicial de las 5 primeras letras del abecedario
  public static void main(String[] args) {
    Character[] datos = {'A', 'B', 'C', 'D', 'E'};
    System.out.println(possibleSetTree(datos) + "");
  }
}