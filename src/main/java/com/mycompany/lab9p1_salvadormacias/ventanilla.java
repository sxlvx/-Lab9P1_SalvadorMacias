package com.mycompany.lab9p1_salvadormacias;

import java.util.ArrayList;
import java.util.Arrays;

public class ventanilla {

    private ArrayList<Integer> colaPrioridad;
    private ArrayList<Integer> colaRegular;
    private int[] ventanilla;
    private char[] clientesVentanilla;

    public ventanilla(int tamano) {
        colaPrioridad = new ArrayList<>();
        colaRegular = new ArrayList<>();
        ventanilla = new int[tamano];
        clientesVentanilla = new char[tamano];
        Arrays.fill(ventanilla, 0);
        Arrays.fill(clientesVentanilla, ' ');
    }

    public ArrayList<Integer> getColaPrioridad() {
        return colaPrioridad;
    }

    public void setColaPrioridad(ArrayList<Integer> colaPrioridad) {
        this.colaPrioridad = colaPrioridad;
    }

    public ArrayList<Integer> getColaRegular() {
        return colaRegular;
    }

    public void setColaRegular(ArrayList<Integer> colaRegular) {
        this.colaRegular = colaRegular;
    }

    public int[] getVentanilla() {
        return ventanilla;
    }

    public void setVentanilla(int[] ventanilla) {
        this.ventanilla = ventanilla;
    }

    public char[] getClientesEnVentanilla() {
        return clientesVentanilla;
    }

    public void setClientesEnVentanilla(char[] clientesEnVentanilla) {
        this.clientesVentanilla = clientesEnVentanilla;
    }

    public void agregarClienteRegular(int tiempoConsulta) {
        colaRegular.add(tiempoConsulta);
    }

    public void agregarClientePreferencial(int tiempoConsulta) {
        colaPrioridad.add(tiempoConsulta);
    }

    public int encontrarVentanillaDisponible() {
        int i = 0;
        while (i < ventanilla.length) {
            if (ventanilla[i] == 0) {
                return i;
            }
            i++;
        }
        return -1;
    }

    public void actualizarVentanillas() {
        int i = 0;
        do {
            if (ventanilla[i] > 0) {
                ventanilla[i]--;
                if (ventanilla[i] == 0) {
                    clientesVentanilla[i] = ' ';
                }
            }
            i++;
        } while (i < ventanilla.length);
    }

    public void correrSimulacion(int tiempoTotal) {
        int tiempoActual = 0;
        while (tiempoActual < tiempoTotal && (!colaPrioridad.isEmpty() || !colaRegular.isEmpty())) {
            actualizarVentanillas();
            int ventanillaDisponible = encontrarVentanillaDisponible();
            if (ventanillaDisponible != -1) {
                while (!colaPrioridad.isEmpty() && ventanillaDisponible != -1) {
                    int tiempoConsulta = colaPrioridad.remove(0);
                    ventanilla[ventanillaDisponible] = tiempoConsulta;
                    clientesVentanilla[ventanillaDisponible] = 'P';
                    ventanillaDisponible = encontrarVentanillaDisponible();
                }
                while (!colaRegular.isEmpty() && ventanillaDisponible != -1) {
                    int tiempoConsulta = colaRegular.remove(0);
                    ventanilla[ventanillaDisponible] = tiempoConsulta;
                    clientesVentanilla[ventanillaDisponible] = 'R';
                    ventanillaDisponible = encontrarVentanillaDisponible();
                }
            }
            imp_clienven();
            imp_ven();
            tiempoActual++;
            System.out.println("\n");
        }
    }

    public void imp_ven() {
        int i = 0;
        do {
            System.out.print("[" + ventanilla[i] + "]");
            i++;
        } while (i < ventanilla.length);
        System.out.println("");
    }

    public void imp_clienven() {
        int i = 0;
        do {
            System.out.print("[" + clientesVentanilla[i] + "]");
            i++;
        } while (i < clientesVentanilla.length);
        System.out.println("");
    }

    public int TiempoTotal() {
        int tiempoTotal = 0;
        int i = 0;
        int j = 0;
        while (i < colaPrioridad.size()) {
            tiempoTotal += colaPrioridad.get(i);
            i++;
        }
        while (j < colaRegular.size()) {
            tiempoTotal += colaRegular.get(j);
            j++;
        }
        return tiempoTotal;
    }
}
