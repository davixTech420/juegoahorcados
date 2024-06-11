package com.edu.sena.entitis;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class ahorcado {
    private List<String> palabras;
    private String palabraSecreta;
    private char[] estadoPalabra;
    private int intentos;
    private String muñeco = ""; // cadena para dibujar el muñeco del ahorcado

    public ahorcado() {
        palabras = new ArrayList<>();
        cargarPalabras();
    }

    private void cargarPalabras() {
        try {
            File archivo = new File("src\\main\\java\\com\\edu\\sena\\entitis\\palabras.txt");
            Scanner scanner = new Scanner(archivo);
            while (scanner.hasNextLine()) {
                palabras.add(scanner.nextLine());
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("No se encontró el archivo de palabras.");
            System.exit(1);
        }
    }

    private String elegirPalabraAleatoria() {
        Random random = new Random();
        return palabras.get(random.nextInt(palabras.size()));
    }

    public void jugar() {
        Scanner scanner = new Scanner(System.in);
        boolean jugar = true;
        while (jugar) {
            System.out.println("¿Qué deseas hacer?");
            System.out.println("1. Jugar al ahorcado");
            System.out.println("2. Salir");
            int opcion = scanner.nextInt();
            switch (opcion) {
                case 1:
                    palabraSecreta = elegirPalabraAleatoria();
                    estadoPalabra = new char[palabraSecreta.length()];
                    for (int i = 0; i < estadoPalabra.length; i++) {
                        estadoPalabra[i] = '_';
                    }
                    intentos = 7; // número de intentos permitidos
                    muñeco = ""; // reiniciar el muñeco
                    while (intentos > 0) {
                        System.out.println("Estado actual de la palabra: " + new String(estadoPalabra));
                        System.out.print("Ingrese una letra: ");
                        char letra = scanner.next().charAt(0);
                        if (palabraSecreta.indexOf(letra) != -1) {
                            for (int i = 0; i < palabraSecreta.length(); i++) {
                                if (palabraSecreta.charAt(i) == letra) {
                                    estadoPalabra[i] = letra;
                                }
                            }
                            if (new String(estadoPalabra).equals(palabraSecreta)) {
                                System.out.println("¡Felicidades! Has adivinado la palabra.");
                                break;
                            }
                        } else {
                            intentos--;
                            dibujarMuñeco(intentos); // dibujar una parte del muñeco
                            System.out.println("La letra no está en la palabra. Te quedan " + intentos + " intentos.");
                        }
                    }
                    if (intentos == 0) {
                        System.out.println(
                                "Lo siento, no has adivinado la palabra. La palabra secreta era: " + palabraSecreta);
                        System.out.println("Muñeco completo:");
                        System.out.println(muñeco);
                    }
                    break;
                case 2:
                    jugar = false;
                    break;
                default:
                    System.out.println("Opción inválida. Intente nuevamente.");
            }
        }
    }

    private void dibujarMuñeco(int intentos) {
        switch (intentos) {
            case 6:
                muñeco += "   _____\n";
                break;
            case 5:
                muñeco += "  /  |  \\\n";
                break;
            case 4:
                muñeco += " /   |   \\\n";
                break;
            case 3:
                muñeco += "|    o    |\n";
                break;
            case 2:
                muñeco += "|   /|\\   |\n";
                break;
            case 1:
                muñeco += "|____|____|\n";
                break;
            case 0:
                muñeco += "    / \\  |\n";
                break;
        }
        System.out.println(muñeco);
    }
}