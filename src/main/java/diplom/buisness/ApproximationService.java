package diplom.buisness;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Сервис для аппроксимация значений методом наименьших квадратов
 */
public class ApproximationService {

    public List<List<Double>> approximate(List<Double> masCoord, List<Double> masLine) {
        List<List<Double>> approximatedValues = new ArrayList<>();
        List<List<Double>> originalMas = fillOriginalMas(masCoord, masLine);
        List<List<Double>> mnkMas = getMnkMas(originalMas, 14);
        List<Double> gauss = findX(mnkMas);
        List<Double> listY = findFunc(gauss, masCoord);
        approximatedValues.add(masCoord);
        approximatedValues.add(listY);
        return approximatedValues;
    }

    private List<List<Double>> fillOriginalMas(List<Double> masX, List<Double> maxY) {
        List<List<Double>> originalMas = new ArrayList<>();
        originalMas.add(masX);
        originalMas.add(maxY);
        return originalMas;
    }

    private List<Double> findX(List<List<Double>> values) {
        int len = values.size();
        Double[] valuesOfX = new Double[len];
        Arrays.fill(valuesOfX, 0);
        for (int i=len-1; i>-1; i--) {
            double sum = 0;
            for (int j=i+1; j<len; j++) {
                sum += values.get(i).get(j) * valuesOfX[j];
            }
            valuesOfX[i] = (values.get(i).get(values.get(0).size())-sum)/values.get(i).get(i);
        }
        return Arrays.asList(valuesOfX);
    }

    private List<Double> findFunc(List<Double> masResh, List<Double> masX) {
        List<Double> funcMas = new ArrayList<>();
        for (int j=0; j<masX.size(); j++) {
            double funcValue = 0;
            for (int i=0; i<masResh.size(); i++) {
                funcValue += masResh.get(i)*Math.pow(masX.get(j), i);
            }
            funcMas.add(funcValue);
        }
        return funcMas;
    }


    private List<List<Double>> getMnkMas(List<List<Double>> originalMas, int n) {
        List<List<Double>> mnk = new ArrayList<>();
        List<List<Double>> mnkX = getMnkXMas(originalMas, n);
        List<List<Double>> mnkY = getMnkYMas(originalMas, n);
        int len = originalMas.get(0).size();
        for (int i=0; i<n+1; i++) {
            List<Double> line = new ArrayList<>();
            for (int j=0; j<n+1; j++) {
                if (i==0 && j==0) {
                    line.add((double) len);
                } else if (i==0 && j!=n+1) {
                    line.add(mnkX.get(j-1).get(len));
                } else if (j!=n+1) {
                    line.add(mnkX.get(j+i-1).get(len));
                }
            }
            line.add(mnkY.get(i).get(len));
            mnk.add(line);
        }
        return mnk;
    }

    private List<List<Double>> getMnkYMas(List<List<Double>> mas, int n) {
        List<List<Double>> mnkY = new ArrayList<>();
        for (int i=0; i<n+1; i++) {
            double sumOfLine = 0;
            List<Double> line = new ArrayList<>();
            for (int j=0; j<mas.get(0).size(); j++) {
                double value = mas.get(1).get(j)*Math.pow(mas.get(0).get(j), i);
                line.add(value);
                sumOfLine += value;
            }
            line.add(sumOfLine);
            mnkY.add(line);
        }
        return mnkY;
    }

    private List<List<Double>> getMnkXMas(List<List<Double>> mas, int n) {
        List<List<Double>> mnkX = new ArrayList<>();
        for (int i=0; i<2*n; i++) {
            double sumOfLine = 0;
            List<Double> line = new ArrayList<>();
            for (int j=0; j<mas.get(0).size(); j++) {
                double value = Math.pow(mas.get(0).get(j), i+1);
                line.add(value);
                sumOfLine += value;
            }
            line.add(sumOfLine);
            mnkX.add(line);
        }
        return mnkX;
    }
}
