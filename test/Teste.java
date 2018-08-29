import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

public class Teste {
    public static void main(String[] args) {
        String sep = new String(new char[30]).replace("\0", "-");

        System.out.println(sep + " Running tests " + sep);
        final Result result = JUnitCore.runClasses(Testes.class);
        System.out.println(sep);

        System.out.println(sep + " Fails " + sep);
        for (final Failure failure : result.getFailures()) {
            System.out.println(failure.toString());
        }
        System.out.println(sep);

        System.out.println(sep + " Results " + sep);
        int count = result.getRunCount();
        int fails = result.getFailureCount();
        int correctPercent = (100 * (count - fails)) / count;

        System.out.println("Testes: " + count + " / Falha(s): " + fails);
        System.out.println("--");
        System.out.println("Nota (0 ~ 100): " + correctPercent);
    }
}