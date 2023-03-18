package com.mycompany.lab9p1_salvadormacias;

import java.util.Scanner;

import java.util.Random;

public class Lab9P1_SalvadorMacias {

    public static Scanner lea = new Scanner(System.in);

    public static void main(String[] args) {
        menu();
    }

    public static void menu() {
        boolean salir = true;
        do {
            System.out.println("Bienvenido al menu");
            System.out.println("------------------------------------------------------------------");
            System.out.println("Opciones disponibles:\n1.Pase a la ventanilla N...\n2.Salir");
            System.out.println("Ingrese opcion: ");
            int opc = lea.nextInt();
            switch (opc) {
                case 1: {
                    System.out.print("Ingrese el número de clientes a generar: ");
                    int numClientes = lea.nextInt();
                    System.out.print("Ingrese el número de escritorios disponibles: ");
                    int esc_dis = lea.nextInt();
                    Random random = new Random();
                    ventanilla ventanilla = new ventanilla(esc_dis);
                    for (int i = 0; i < numClientes; i++) {
                        int tiempoConsulta = random.nextInt(5) + 1;
                        if (random.nextDouble() < 0.5) {
                            ventanilla.agregarClienteRegular(tiempoConsulta);
                        } else {
                            ventanilla.agregarClientePreferencial(tiempoConsulta);
                        }
                    }
                    int tiempoTotal = ventanilla.TiempoTotal();
                    System.out.print("Tiempo total: ");
                    System.out.println(tiempoTotal);
                    System.out.println("Cola de prioridad: " + ventanilla.getColaPrioridad());
                    System.out.println("Cola regular: " + ventanilla.getColaRegular());
                    ventanilla.correrSimulacion(tiempoTotal);
                    System.out.println("------------------------------------------------------------------");

                }
                break;

                case 2: {

                    salir = false;
                    System.out.println("Se abandonara el programa ");
                    System.out.println("------------------------------------------------------------------");

                }
                break;

                default: {
                    System.out.println("La opcion ingresada es invalida");
                    System.out.println("------------------------------------------------------------------");

                }

            }//fin switch

        } while (salir != false);
    }//fin menu

}//fin clase
