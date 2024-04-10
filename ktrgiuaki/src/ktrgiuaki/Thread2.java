package ktrgiuaki;

import java.time.LocalDate;
import java.time.Period;
import java.util.Base64;
import java.util.concurrent.Callable;

class Thread2 implements Callable<String> {
    private Student student;

    public Thread2(Student student) {
        this.student = student;
    }

    @Override
    public String call() {
        try {
            String dateOfBirthStr = student.getDateOfBirth();
            LocalDate dateOfBirth = LocalDate.parse(dateOfBirthStr);
            LocalDate currentDate = LocalDate.now();
            Period period = Period.between(dateOfBirth, currentDate);
            int age = period.getYears();

            String encodedAge = encodeAge(age);

            StringBuilder resultBuilder = new StringBuilder();
            resultBuilder.append("<age>").append(encodedAge).append("</age>\n");
            return resultBuilder.toString();
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    private String encodeAge(int age) {
        byte[] ageBytes = String.valueOf(age).getBytes();
        byte[] encodedBytes = Base64.getEncoder().encode(ageBytes);
        return new String(encodedBytes);
    }
}