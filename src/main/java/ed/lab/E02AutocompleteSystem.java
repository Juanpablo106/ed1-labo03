package ed.lab;
import java.util.*;

public class E02AutocompleteSystem {

    private Map<String, Integer> frecuencia;
    private StringBuilder fraseActual;

    public E02AutocompleteSystem(String[] frases, int[] frecuencias) {
        frecuencia = new HashMap<>();
        fraseActual = new StringBuilder();

        for (int i = 0; i < frases.length; i++) {
            frecuencia.put(frases[i], frecuencias[i]);
        } //
    }

    public List<String> input(char c) {
        List<String> resultado = new ArrayList<>();

        if (c == '#') {
            String fraseFinal = fraseActual.toString();
            frecuencia.put(fraseFinal, frecuencia.getOrDefault(fraseFinal, 0) + 1);
            fraseActual = new StringBuilder();
            return resultado;
        }

        fraseActual.append(c);
        String prefijo = fraseActual.toString();

        PriorityQueue<String> cola = new PriorityQueue<>((a, b) -> {
            int frecuenciaA = frecuencia.get(a);
            int frecuenciaB = frecuencia.get(b);
            if (frecuenciaA != frecuenciaB) {
                return Integer.compare(frecuenciaB, frecuenciaA); // más frecuente primero
            }
            return a.compareTo(b); // si igual, lexicográficamente
        });

        for (String frase : frecuencia.keySet()) {
            if (frase.startsWith(prefijo)) {
                cola.offer(frase);
            }
        }

        int k = 3;
        while (!cola.isEmpty() && k-- > 0) {
            resultado.add(cola.poll());
        }

        return resultado;
    }
}