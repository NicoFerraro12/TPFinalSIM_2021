package objects;

import front.vista.simulacion.VentanaPrincipal;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Random;
import javax.swing.table.DefaultTableModel;

public class Calculator {

    //cantidad de simulaciones realizadas 
    int cantExperimentos = 1;
    private final DecimalFormat df = new DecimalFormat("0.00");

    int acumSiSuperaLimite = 0;
    DefaultTableModel tm;
    DefaultTableModel tmPrimerTiro;
    DefaultTableModel tmSegundoTiroPara3;
    DefaultTableModel tmSegundoTiroPara4;
    DefaultTableModel tmSegundoTiroPara5;
    DefaultTableModel tmSegundoTiroPara6;
    DefaultTableModel tmSegundoTiroPara7;
    DefaultTableModel tmSegundoTiroPara8;
    DefaultTableModel tmSegundoTiroPara9;
    DefaultTableModel tmSegundoTiro;

    private static final int COL_VALOR = 0;
    private static final int COL_P_ACUM = 2;
    private int acumPtos3 = 0;
    private int acumPtos4 = 0;
    private int acumPtos5 = 0;
    private int acumPtos6 = 0;
    private int acumPtos7 = 0;
    private int acumPtos8 = 0;
    private int acumPtos9 = 0;
    private int acumPtos10 = 0;
    private int contPinos3 = 0;
    private int contPinos4 = 0;
    private int contPinos5 = 0;
    private int contPinos6 = 0;
    private int contPinos7 = 0;
    private int contPinos8 = 0;
    private int contPinos9 = 0;
    private int contPinos10 = 0;

    private final HashMap<Integer, Float> puntosMedios = new HashMap<>();

    public Calculator() {

    }

    public HashMap<Integer, Float> getPtosMedios() {
        return this.puntosMedios;
    }
    
    public void vaciarMap() {
        this.puntosMedios.clear();
    }

    public void especificoTablas(VentanaPrincipal tablaSimulacion, int cantSim,
            int desde, int hasta, int cantRondas, int puntosPrimerTiro, int puntosSegundoTiro, int valorASuperar) {
        especificoSeteoTablas(tablaSimulacion);
        for (int i = 0; i < cantSim; i++) {
            if ((i >= desde - 1 && i <= hasta - 1) || i == cantSim - 1) {
                simulacionEnGrafico(cantRondas, puntosPrimerTiro, puntosSegundoTiro, valorASuperar);
            } else {
                simulacion(cantRondas, puntosPrimerTiro, puntosSegundoTiro, valorASuperar);
            }
        }
        
        if (contPinos3 != 0) {
            puntosMedios.put(3, (float)acumPtos3 / contPinos3);
            System.out.println("");
        }
        if (contPinos4 != 0) {
            puntosMedios.put(4, (float)acumPtos4 / contPinos4);
        } 
        if (contPinos5 != 0) {
            puntosMedios.put(5, (float)acumPtos5 / contPinos5);
        } 
        if (contPinos6 != 0) {
            puntosMedios.put(6, (float)acumPtos6 / contPinos6);
        } 
        if (contPinos7 != 0) {
            puntosMedios.put(7, (float)acumPtos7 / contPinos7);
        } 
        if (contPinos8 != 0) {
            puntosMedios.put(8, (float)acumPtos8 / contPinos8);
        } 
        if (contPinos9 != 0) {
            puntosMedios.put(9, (float)acumPtos9 / contPinos9);
        } 
        if (contPinos10 != 0) {
            puntosMedios.put(10, (float)acumPtos10 / contPinos10);
        }
    }

    public void simulacion(int cantRondas, int puntosPrimerTiro, int puntosSegundoTiro, int valorASuperar) {
        Random r = new Random();
        int puntaje;
        int puntosAcum = 0;
        boolean flag = false;

        //       0       1       2          3        4         5                6                  7               8                       9            10
        // simulacion  ronda    rnd1    1erTiro     rnd2    2doTiro     totalPinosTirados   puntosTotales   puntosAcumulados    booleano superoLos120   acumuladorSiSupera120  
        for (int i = 0; i < cantRondas; i++) {
            float rnd1 = r.nextFloat();
            int tiro1 = calculoTiro1(rnd1);

            if (tiro1 == 10) {
                puntaje = puntosPrimerTiro;
                puntosAcum += puntaje;
                if (puntosAcum >= valorASuperar && flag == false) {
                    acumSiSuperaLimite++;
                    flag = true;
                }
            } else {
                float rnd2 = r.nextFloat();
                int tiro2 = calculoTiro2(tiro1, rnd2);
                int cantPinosTirados = tiro1 + tiro2;
                puntaje = (cantPinosTirados == 10) ? puntosSegundoTiro : cantPinosTirados;
                puntosAcum += puntaje;
                if (puntosAcum >= valorASuperar && flag == false) {
                    acumSiSuperaLimite++;
                    flag = true;
                }
            }
            switch (tiro1) {
                case 3:
                    acumPtos3 += puntaje;
                    contPinos3++;
                    break;
                case 4:
                    acumPtos4 += puntaje;
                    contPinos4++;
                    break;
                case 5:
                    acumPtos5 += puntaje;
                    contPinos5++;
                    break;
                case 6:
                    acumPtos6 += puntaje;
                    contPinos6++;
                    break;
                case 7:
                    acumPtos7 += puntaje;
                    contPinos7++;
                    break;
                case 8:
                    acumPtos8 += puntaje;
                    contPinos8++;
                    break;
                case 9:
                    acumPtos9 += puntaje;
                    contPinos9++;
                    break;
                case 10:
                    acumPtos10 += puntaje;
                    contPinos10++;
                    break;
            }
        }
        cantExperimentos++;
    }

    public void simulacionEnGrafico(int cantRondas, int puntosPrimerTiro, int puntosSegundoTiro, int valorASuperar) {
        Random r = new Random();
        int contador = 1;
        int puntaje;
        int puntosAcum = 0;
        boolean flag = false;

        //       0       1       2          3        4         5                6                  7               8                       9            10
        // simulacion  ronda    rnd1    1erTiro     rnd2    2doTiro     totalPinosTirados   puntosTotales   puntosAcumulados    booleano superoLos120   acumuladorSiSupera120  
        for (int i = 0; i < cantRondas; i++) {

            float rnd1 = r.nextFloat();
            int tiro1 = calculoTiro1(rnd1);

            if (tiro1 == 10) {
                puntaje = puntosPrimerTiro;
                puntosAcum += puntaje;
                tm.addRow(new Object[]{cantExperimentos, contador, df.format(rnd1), tiro1, "-", "-", tiro1, puntaje, puntosAcum, (puntosAcum >= valorASuperar) ? true : false, acumSiSuperaLimite});
                if (puntosAcum >= valorASuperar && flag == false) {
                    acumSiSuperaLimite++;
                    flag = true;
                }
            } else {
                float rnd2 = r.nextFloat();
                int tiro2 = calculoTiro2(tiro1, rnd2);
                int cantPinosTirados = tiro1 + tiro2;
                puntaje = (cantPinosTirados == 10) ? puntosSegundoTiro : cantPinosTirados;
                puntosAcum += puntaje;
                tm.addRow(new Object[]{cantExperimentos, contador, df.format(rnd1), tiro1, df.format(rnd2), tiro2,
                    cantPinosTirados, puntaje, puntosAcum, (puntosAcum >= valorASuperar), acumSiSuperaLimite});
                if (puntosAcum >= valorASuperar && flag == false) {
                    acumSiSuperaLimite++;
                    flag = true;
                }
            }
            contador++;
            switch (tiro1) {
                case 3:
                    acumPtos3 += puntaje;
                    contPinos3++;
                    break;
                case 4:
                    acumPtos4 += puntaje;
                    contPinos4++;
                    break;
                case 5:
                    acumPtos5 += puntaje;
                    contPinos5++;
                    break;
                case 6:
                    acumPtos6 += puntaje;
                    contPinos6++;
                    break;
                case 7:
                    acumPtos7 += puntaje;
                    contPinos7++;
                    break;
                case 8:
                    acumPtos8 += puntaje;
                    contPinos8++;
                    break;
                case 9:
                    acumPtos9 += puntaje;
                    contPinos9++;
                    break;
                case 10:
                    acumPtos10 += puntaje;
                    contPinos10++;
                    break;
            }
        }
        cantExperimentos++;
    }

    private int calculoTiro1(float rnd1) {
        int nPinosTirados = 0;
        for (int i = 0; i < tmPrimerTiro.getRowCount(); i++) {
            if (rnd1 < (float) tmPrimerTiro.getValueAt(i, COL_P_ACUM)) {
                nPinosTirados = (int) tmPrimerTiro.getValueAt(i, COL_VALOR);
                break;
            }
        }
        return nPinosTirados;
    }

    private int calculoTiro2(int tiro1, float rnd2) {
        int nPinosTirados = 0;

        switch (tiro1) {
            case 3:
                for (int i = 0; i < tmSegundoTiroPara3.getRowCount(); i++) {
                    if (rnd2 < (float) tmSegundoTiroPara3.getValueAt(i, COL_P_ACUM)) {
                        nPinosTirados = (int) tmSegundoTiroPara3.getValueAt(i, COL_VALOR);
                        break;
                    }
                }
                break;
            case 4:
                for (int i = 0; i < tmSegundoTiroPara4.getRowCount(); i++) {
                    if (rnd2 < (float) tmSegundoTiroPara4.getValueAt(i, COL_P_ACUM)) {
                        nPinosTirados = (int) tmSegundoTiroPara4.getValueAt(i, COL_VALOR);
                        break;
                    }
                }
                break;
            case 5:
                for (int i = 0; i < tmSegundoTiroPara5.getRowCount(); i++) {
                    if (rnd2 < (float) tmSegundoTiroPara5.getValueAt(i, COL_P_ACUM)) {
                        nPinosTirados = (int) tmSegundoTiroPara5.getValueAt(i, COL_VALOR);
                        break;
                    }
                }
                break;
            case 6:
                for (int i = 0; i < tmSegundoTiroPara6.getRowCount(); i++) {
                    if (rnd2 < (float) tmSegundoTiroPara6.getValueAt(i, COL_P_ACUM)) {
                        nPinosTirados = (int) tmSegundoTiroPara6.getValueAt(i, COL_VALOR);
                        break;
                    }
                }
                break;
            case 7:
                for (int i = 0; i < tmSegundoTiroPara7.getRowCount(); i++) {
                    if (rnd2 < (float) tmSegundoTiroPara7.getValueAt(i, COL_P_ACUM)) {
                        nPinosTirados = (int) tmSegundoTiroPara7.getValueAt(i, COL_VALOR);
                        break;
                    }
                }
                break;
            case 8:
                for (int i = 0; i < tmSegundoTiroPara8.getRowCount(); i++) {
                    if (rnd2 < (float) tmSegundoTiroPara8.getValueAt(i, COL_P_ACUM)) {
                        nPinosTirados = (int) tmSegundoTiroPara8.getValueAt(i, COL_VALOR);
                        break;
                    }
                }
                break;
            case 9:
                for (int i = 0; i < tmSegundoTiroPara9.getRowCount(); i++) {
                    if (rnd2 < (float) tmSegundoTiroPara9.getValueAt(i, COL_P_ACUM)) {
                        nPinosTirados = (int) tmSegundoTiroPara9.getValueAt(i, COL_VALOR);
                        break;
                    }
                }
                break;
        }
        return nPinosTirados;
    }

    private void especificoSeteoTablas(VentanaPrincipal tablaSimulacion) {
        tm = (DefaultTableModel) tablaSimulacion._tblMontecarlo.getModel();
        tmPrimerTiro = (DefaultTableModel) tablaSimulacion.tblPrimerTiro.getModel();
        tmSegundoTiroPara3 = (DefaultTableModel) tablaSimulacion.tblDespues3.getModel();
        tmSegundoTiroPara4 = (DefaultTableModel) tablaSimulacion.tblDespues4.getModel();
        tmSegundoTiroPara5 = (DefaultTableModel) tablaSimulacion.tblDespues5.getModel();
        tmSegundoTiroPara6 = (DefaultTableModel) tablaSimulacion.tblDespues6.getModel();
        tmSegundoTiroPara7 = (DefaultTableModel) tablaSimulacion.tblDespues7.getModel();
        tmSegundoTiroPara8 = (DefaultTableModel) tablaSimulacion.tblDespues8.getModel();
        tmSegundoTiroPara9 = (DefaultTableModel) tablaSimulacion.tblDespues9.getModel();
    }

    public int getCantidadExperimentosValidos() {
        return acumSiSuperaLimite;
    }

    public void limpiarValoresGlobales() {
        acumSiSuperaLimite = 0;
        cantExperimentos = 1;
        acumPtos3 = 0;
        acumPtos4 = 0;
        acumPtos5 = 0;
        acumPtos6 = 0;
        acumPtos7 = 0;
        acumPtos8 = 0;
        acumPtos9 = 0;
        acumPtos10 = 0;
        contPinos3 = 0;
        contPinos4 = 0;
        contPinos5 = 0;
        contPinos6 = 0;
        contPinos7 = 0;
        contPinos8 = 0;
        contPinos9 = 0;
        contPinos10 = 0;
    }
}
