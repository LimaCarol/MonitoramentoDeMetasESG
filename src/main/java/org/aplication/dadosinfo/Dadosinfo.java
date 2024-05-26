package org.aplication.dadosinfo;

import java.io.FileWriter;
import java.io.IOException;
import org.json.JSONArray;
import org.json.JSONObject;

public class Dadosinfo {

    public static double calcularEmissaoCO2Mensal(double consumoDieselLitros) {
        double intensidadeCO2Diesel = 2.68; // kg CO2 / litro de diesel
        return consumoDieselLitros * intensidadeCO2Diesel;
    }

    public static double calcularEmissaoCO2Total(double[] consumoDieselMensal) {
        double emissaoCO2Total = 0;
        for (double consumoMensal : consumoDieselMensal) {
            emissaoCO2Total += calcularEmissaoCO2Mensal(consumoMensal);
        }
        return emissaoCO2Total;
    }

    public static void main(String[] args) {
        // Dados mensais aproximados de consumo de diesel em litros
        double[] consumoDieselMensal = {10000, 11000, 10500, 12000, 11500, 11800, 12500, 13000, 12200, 11500, 11000, 10500};

        // Nomes dos meses em português
        String[] meses = {"Janeiro", "Fevereiro", "Março", "Abril", "Maio", "Junho", "Julho", "Agosto", "Setembro", "Outubro", "Novembro", "Dezembro"};

        // Calculando as emissões de CO2 aproximadas mensais e totais
        double[] emissaoCO2Mensal = new double[12];
        double emissaoCO2Total;

        for (int i = 0; i < 12; i++) {
            emissaoCO2Mensal[i] = calcularEmissaoCO2Mensal(consumoDieselMensal[i]);
        }

        emissaoCO2Total = calcularEmissaoCO2Total(consumoDieselMensal);

        JSONObject jsonObj = new JSONObject();
        JSONArray jsonMensal = new JSONArray();
        for (int i = 0; i < 12; i++) {
            JSONObject mesObj = new JSONObject();
            mesObj.put("mes", meses[i]);
            mesObj.put("emissaoCO2", emissaoCO2Mensal[i]);
            jsonMensal.put(mesObj);
        }
        jsonObj.put("emissoesMensais", jsonMensal);
        jsonObj.put("emissaoCO2Total", emissaoCO2Total);

        try (FileWriter file = new FileWriter("emissoes.json")) {
            file.write(jsonObj.toString());
            file.flush();
        } catch (IOException e) {
        }
    }
}
