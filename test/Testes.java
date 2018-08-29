// Importa as bibliotecas necessárias
// import static org.junit.Assert.*;
// import static org.hamcrest.CoreMatchers.*;
import static org.assertj.core.api.Assertions.*;

import java.util.List;
import java.util.stream.Collectors;

import org.junit.Test;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

// Classe com os métodos de teste
public class Testes {

    // Declara um objeto do tipo WebDriver
    private static WebDriver driver;

    // Método que inicia tudo que for necessário para os testes
    @BeforeClass
    public static void setUpTest(){
        // Cria uma instância do navegador
        driver = new FirefoxDriver();
    }

    // Método que finaliza o teste, fechando a instância do WebDriver
    @AfterClass
    public static void tearDownTest(){
        // Fecha a instância do navegador
        driver.quit();
    }

    // Testa o título
    @Test
    public void pageTitle(){
        driver.get("http://localhost:9990/test.html");

        assertThat(driver.getTitle()).isEqualTo("Documento HTML"); // garante que o título é igual a "Documento HTML"
    }

    // Testa o h1
    @Test
    public void texto_h1(){
        driver.get("http://localhost:9990/test.html");

        List<WebElement> headers = driver.findElements(By.tagName("h1"));
        assertThat(headers).as("nenhum título").isNotEmpty(); // garante que existe algum "h1"

        WebElement h1 = headers.get(0);
        assertThat(h1.getText()).as("texto incorreto").isEqualTo("Hello World"); // garante o texto do elemento
    }

    // Testa o conteúdo do parágrafo
    @Test
    public void p_contem_lorem_ipsum(){
        driver.get("http://localhost:9990/test.html");

        List<WebElement> ps = driver.findElements(By.tagName("p"));
        assertThat(ps).as("nenhum parágrafo").isNotEmpty(); // garante que existe algum elemento "p"

        List<String> texts = ps.stream().map(e -> e.getText()).collect(Collectors.toList());
        assertThat(texts).as("nenhum parágrafo com o texto").contains("Lorem ipsum");

        // WebElement p = ps.get(0);
        // assertThat(p.getText()).as("primeiro").contains("Lorem ipsum"); // garente o texto do primeiro "p" encontrado
    }

}