package ktrgiuaki;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class Main {
    public static void main(String[] args) {
        ExecutorService executor = Executors.newFixedThreadPool(3);
        List<Future<String>> results = new ArrayList<>();

        try {
            Future<String> thread1Result = executor.submit(new Thread1());
            results.add(thread1Result);

            List<Student> students = new ArrayList<>();
            students.add(new Student("1", "Khanh Ly", "Quang Nam", "2005-12-08"));
            students.add(new Student("2", "Hong Nhung", "Da Nang", "2005-06-13"));
            students.add(new Student("3", "Thao Vy", "Quang Nam", "2005-10-07"));
            students.add(new Student("4", "Le Tham", "Daklak", "2005-10-10"));

            List<Future<String>> threadResults = new ArrayList<>();
            for (Student student : students) {
                Future<String> thread2Result = executor.submit(new Thread2(student));
                threadResults.add(thread2Result);

                Future<String> thread3Result = executor.submit(new Thread3(student));
                threadResults.add(thread3Result);
            }

            // Ghi kết quả vào file kq.xml
            for (Future<String> result : threadResults) {
                results.add(result);
            }

            List<String> finalResults = new ArrayList<>();
            for (Future<String> result : results) {
                finalResults.add(result.get());
            }

            try (BufferedWriter writer = new BufferedWriter(new FileWriter("kq.xml", false))) {
                writer.write("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n");
                writer.write("<result>\n");

                for (String result : finalResults) {
                    writer.write(result);
                }

                writer.write("</result>");
                System.out.println("File kq.xml da duoc tao thanh cong");
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }

        executor.shutdown();
    }
}
