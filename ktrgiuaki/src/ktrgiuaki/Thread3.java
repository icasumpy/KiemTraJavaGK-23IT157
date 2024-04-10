/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ktrgiuaki;

import java.time.LocalDate;
import java.util.concurrent.Callable;

class Thread3 implements Callable<String> {
    private Student student;

    public Thread3(Student student) {
        this.student = student;
    }

    @Override
    public String call() {
        try {
            String dateOfBirthStr = student.getDateOfBirth();
            LocalDate dateOfBirth = LocalDate.parse(dateOfBirthStr);
            int sum = getDigitSum(dateOfBirth.getDayOfMonth()) +
                    getDigitSum(dateOfBirth.getMonthValue()) +
                    getDigitSum(dateOfBirth.getYear());
            boolean isPrime = isPrime(sum);

            StringBuilder resultBuilder = new StringBuilder();
            resultBuilder.append("<sum>").append(sum).append("</sum>\n");
            resultBuilder.append("<isDigit>").append(isPrime).append("</isDigit>\n");
            return resultBuilder.toString();
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    private int getDigitSum(int number) {
        int sum = 0;
        while (number > 0) {
            sum += number % 10;
            number /= 10;
        }
        return sum;
    }

    private boolean isPrime(int number) {
        if (number <= 1) {
            return false;
        }
        for (int i = 2; i * i <= number; i++) {
            if (number % i == 0) {
                return false;
            }
        }
        return true;
    }
}
