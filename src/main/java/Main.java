import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
    private static final String[] FILES = {"src/main/resources/ee08-2021-styczen-egzamin-zawodowy-praktyczny.pdf", "src/main/resources/pdf.txt"};

    public static void main(String[] args) throws Exception {
//        PDDocument document = PDDocument.load(new File(FILES[0]));
//        PDFTextStripper stripper = new PDFTextStripper();
//        ArrayList<String> pdfLineByLine = new ArrayList<>();
//        StringBuilder pdf = new StringBuilder();
//
//        if (document.isEncrypted()) {
//            document.setAllSecurityToBeRemoved(true);
//        }
//
//        document.removePage(0);
//        document.save(FILES[1]);
//        stripper.setSortByPosition(true);
//
//        for (int p = 1; p <= 1; ++p) {
//            stripper.setStartPage(p);
//            stripper.setEndPage(p);
//
//            String text = stripper.getText(document);
//            text = text.replaceAll("Strona " + (p + 1) + " z " + (document.getNumberOfPages() + 1), "");
//            text = text.replaceAll("Więcej arkuszy znajdziesz na stronie: arkusze.pl", "");
//            text = text.replaceAll("[\uF0B7\uF02D]", "•");
//
//            pdf.append(text);
//            pdfLineByLine.addAll(Arrays.asList(text.split("\r\n|\r|\n")));
//        }
//
//        BufferedWriter writer = new BufferedWriter(new FileWriter(FILES[1]));
//        writer.write(pdf.toString());
//        writer.close();
//
//        if (true) {
//            return;
//        }

        String test = "Zadanie egzaminacyjne \n" +
                " \n" +
                "Korzystając z dostępnych narzędzi, oprogramowania oraz elementów znajdujących się na stanowisku \n" +
                "egzaminacyjnym, wykonaj montaż okablowania i podłączenie urządzeń sieciowych. Przeprowadź \n" +
                "konfigurację urządzeń sieciowych i systemów zainstalowanych na dysku twardym stacji roboczej i serwera, \n" +
                "oraz diagnostykę podzespołów komputera. \n" +
                "Do konfiguracji serwera Windows wykorzystaj konto Administrator z hasłem ZAQ!2wsx  \n" +
                "Do diagnostyki i konfiguracji stacji roboczej wykorzystaj następujące konta:  \n" +
                "• w systemie Windows konto Administrator z hasłem ZAQ!2wsx \n" +
                "• w systemie Linux konto administrator z hasłem  ZAQ!2wsx (konto z uprawnieniami użytkownika \n" +
                "root) \n" +
                " \n" +
                "1. Wykonaj montaż okablowania sieciowego: \n" +
                "• wykonaj podłączenie kabla UTP do panelu krosowego według sekwencji T568A \n" +
                "• drugi koniec kabla UTP podłącz do gniazda naściennego z zamontowanym modułem Keystone \n" +
                "według sekwencji T568A \n" +
                "UWAGA: Po wykonaniu montażu okablowania oraz gniazda z modułem Keystone zgłoś przewodniczącemu \n" +
                "ZN – przez podniesienie ręki – gotowość do przeprowadzenia testu wykonanego okablowania. W obecności \n" +
                "egzaminatora sprawdź, za pomocą testera okablowania, poprawność wykonanego połączenia panel krosowy \n" +
                "– gniazdo naścienne. \n" +
                " \n" +
                "2. Zamontuj w serwerze dysk twardy opisany jako SERWER_WINDOWS \n" +
                "UWAGA: Po wykonaniu montażu zgłoś przewodniczącemu ZN – przez podniesienie ręki – gotowość do \n" +
                "zakończenia prac montażowych. Po uzyskaniu zgody przystąp do końcowych czynności montażowych \n" +
                "i uruchomienia systemu Windows w wersji serwerowej \n";

        ArrayList<String> done = new ArrayList<>();

        Pattern pattern = Pattern.compile("([•]?[!-~ĘÓĄŚŁŻŹĆŃęóąśłżźćń ]+[\n]?[!-@\\[-~ęóąśłżźćń]+)+");
        Matcher matcher = pattern.matcher(test);

        while (matcher.find()) {
            try {
                String group = matcher.group();
                if (group.contains("(") || group.contains(")")) {
                    group = group.replaceFirst("[\\(]", "\\\\(");
                    group = group.replaceFirst("[\\)]", "\\\\)");
                }

                test = test.replaceFirst("[ ]?[\r\n]+", "");
                test = test.replaceFirst(group, "");

                if (group.contains("(") || group.contains(")")) {
                    group = group.replaceFirst("[\\\\(]", "");
                    group = group.replaceFirst("[\\\\)]", "");
                }

                done.add(group.replaceAll("(\r\n|[\r\n]+)", ""));
                matcher = pattern.matcher(matcher.replaceFirst(""));
            } catch (Exception ignored) {}
        }

        AtomicInteger match = new AtomicInteger();
        done.forEach(line -> System.out.println(match.getAndIncrement() + ": " + line));
    }
}