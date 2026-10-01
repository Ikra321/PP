import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        String inputFileName = "input.txt";
        String output1 = "excel_students.txt";
        String output2 = "average_scores.txt";

        List<Gradebook> students = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(inputFileName))) {
            String countLine = readNextNonEmptyLine(br);
            if (countLine == null) return;

            int studentsCount = Integer.parseInt(countLine);

            for (int i = 0; i < studentsCount; i++) {
                String fioLine = readNextNonEmptyLine(br);
                if (fioLine == null) break;
                String[] fio = fioLine.split("\\s+");

                int course = Integer.parseInt(readNextNonEmptyLine(br));
                String group = readNextNonEmptyLine(br);

                Gradebook student = new Gradebook(fio[0], fio[1], fio[2], course, group);

                int sessionsCount = Integer.parseInt(readNextNonEmptyLine(br));
                for (int j = 0; j < sessionsCount; j++) {
                    String sessionLine = readNextNonEmptyLine(br);

                    String[] sessionParts = sessionLine.split(":");
                    int sessionNum = Integer.parseInt(sessionParts[0].replace("Сессия", "").trim());

                    Gradebook.Session session = student.addSession(sessionNum);

                    if (sessionParts.length > 1 && !sessionParts[1].trim().isEmpty()) {
                        String[] subjectsData = sessionParts[1].split(";");

                        for (String subData : subjectsData) {
                            if (subData.trim().isEmpty()) continue;

                            String[] parts = subData.split(",");
                            String name = parts[0].trim();
                            String gradeStr = parts[1].trim().toLowerCase();
                            String statusStr = parts[2].trim().toLowerCase();

                            boolean isExam;
                            int grade;

                            if (gradeStr.matches("\\d+")) {
                                isExam = true;
                                grade = Integer.parseInt(gradeStr);
                            } else {
                                isExam = false;
                                grade = (statusStr.equals("сдал") || gradeStr.equals("сдал")) ? 1 : 0;
                            }

                            session.addSubject(name, grade, isExam);
                        }
                    }
                }
                students.add(student);
            }
            System.out.println("Данные успешно считаны из " + inputFileName);
        } catch (Exception e) {
            System.err.println("Ошибка при чтении файла: " + e.getMessage());
            return;
        }

        try (PrintWriter pw1 = new PrintWriter(new FileWriter(output1))) {
            for (Gradebook student : students) {
                if (student.isExcellentStudent()) {
                    for (Gradebook.Session session : student.getSessions()) {
                        for (Subject subject : session.getSubjects()) {
                            if (subject.isExam) {
                                pw1.printf("%s, %s, %s, %d, %s, %d, %s, %d%n",
                                        student.getLastName(), student.getFirstName(), student.getMiddleName(),
                                        student.getCourse(), student.getGroup(),
                                        session.getSessionNumber(), subject.name, subject.grade);
                            } else {
                                pw1.printf("%s, %s, %s, %d, %s, %d, %s%n",
                                        student.getLastName(), student.getFirstName(), student.getMiddleName(),
                                        student.getCourse(), student.getGroup(),
                                        session.getSessionNumber(), subject.name);
                            }
                        }
                    }
                }
            }
            System.out.println("Файл отличников записан: " + output1);
        } catch (IOException e) {
            System.err.println("Ошибка записи в файл №1: " + e.getMessage());
        }

        try (PrintWriter pw2 = new PrintWriter(new FileWriter(output2))) {
            for (Gradebook student : students) {
                if (student.isPassingStudent()) {
                    pw2.printf("Студент: %s %s %s | Курс: %d | Группа: %s%n",
                            student.getLastName(), student.getFirstName(), student.getMiddleName(),
                            student.getCourse(), student.getGroup());
                    for (Gradebook.Session session : student.getSessions()) {
                        pw2.printf("  Сессия №%d — Средний балл по экзаменам: %.2f%n",
                                session.getSessionNumber(), session.getAverageScore());
                    }
                    pw2.println("-------------------------------------------------------");
                }
            }
            System.out.println("Ведомость успеваемости записана: " + output2);
        } catch (IOException e) {
            System.err.println("Ошибка записи в файл №2: " + e.getMessage());
        }
    }

    private static String readNextNonEmptyLine(BufferedReader br) throws IOException {
        String line;
        while ((line = br.readLine()) != null) {
            line = line.trim();
            if (!line.isEmpty()) {
                return line;
            }
        }
        return null;
    }
}