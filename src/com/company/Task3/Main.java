package com.company.Task3;

/**
 * Created by ALEKSANDR NECHEUKHIN on 12.12.2016.
 */
public class Main {

    public static void main(String[] argc) throws Exception {
        String[] array = {"адрес", "почтовый", "отделение", "адресат", "отправитель", "индекс", "накладная", "абонент",
                "возврат", "знак", "марка", "оплата", "входящий", "исходящий", "служба", "сторона", "вес", "пакет", "письмо", "посылка",
                "штемпель", "класс", "вручение", "секограмма"};

        /*InsertionSort insertionSort = new InsertionSort(array, SortType.ASCENDING);
        insertionSort.sort();
        ShellSort shellSort = new ShellSort(array, SortType.ASCENDING);
        shellSort.sort();*/

        QuickSort quickSort = new QuickSort(array, SortType.DESCENDING);
        Object[] sorted = quickSort.sort();

        for (Object current : sorted) {
            System.out.println(current);
        }
    }
}
