import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.domain.Book;
import ru.netology.domain.Product;
import ru.netology.domain.Smartphone;
import ru.netology.manager.ProductManager;
import ru.netology.repository.ProductRepository;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

public class ProductManagerTest {
    ProductRepository repository = new ProductRepository();
    ProductManager manager = new ProductManager(repository);
    Book book1 = new Book(1, "Война и мир", 550, "Толстой");
    Book book2 = new Book(2, "Евгений Онегин", 600, "Пушкин");
    Book book3 = new Book(3, "Мастер и Маргарита", 700, "Булгаков");
    Smartphone phone1 = new Smartphone(4, "Iphone", 50000, "Apple");
    Smartphone phone2 = new Smartphone(5, "Galaxy", 30000, "Samsung");
    Smartphone phone3 = new Smartphone(6, "Classic", 5000, "Nokia");

    @BeforeEach
    void setUp() {
        manager = new ProductManager(repository);
        manager.addProduct(book1);
        manager.addProduct(book2);
        manager.addProduct(book3);
        manager.addProduct(phone1);
        manager.addProduct(phone2);
        manager.addProduct(phone3);
    }

    @Test
    void shouldFindBookByNameIfExists() {
        String textToFind = "Мастер и Маргарита";
        Product[] expected = new Product[]{book3};
        Product[] actual = manager.searchBy(textToFind);
        assertArrayEquals(expected, actual);
    }

    @Test
    void shouldNotFindBookByNameIfNoExists() {
        String textToFind = "Шантарам";
        Product[] expected = new Product[]{};
        Product[] actual = manager.searchBy(textToFind);
        assertArrayEquals(expected, actual);
    }

    @Test
    void shouldFindBookByAuthorIfExists() {
        String textToFind = "Толстой";
        Product[] expected = new Product[]{book1};
        Product[] actual = manager.searchBy(textToFind);
        assertArrayEquals(expected, actual);
    }

    @Test
    void shouldNotFindBookByAuthorIfNoExists() {
        String textToFind = "Маяковский";
        Product[] expected = new Product[]{};
        Product[] actual = manager.searchBy(textToFind);
        assertArrayEquals(expected, actual);
    }

    @Test
    void shouldFindSmartphoneByNameIfExists() {
        String textToFind = "Galaxy";
        Product[] expected = new Product[]{phone2};
        Product[] actual = manager.searchBy(textToFind);
        assertArrayEquals(expected, actual);
    }

    @Test
    void shouldNotFindSmartphoneByNameIfNoExists() {
        String textToFind = "Redmi";
        Product[] expected = new Product[]{};
        Product[] actual = manager.searchBy(textToFind);
        assertArrayEquals(expected, actual);
    }

    @Test
    void shouldFindSmartphoneByProducerIfExists() {
        String textToFind = "Apple";
        Product[] expected = new Product[]{phone1};
        Product[] actual = manager.searchBy(textToFind);
        assertArrayEquals(expected, actual);
    }

    @Test
    void shouldNotFindSmartphoneByProducerIfNoExists() {
        String textToFind = "Xiaomi";
        Product[] expected = new Product[]{};
        Product[] actual = manager.searchBy(textToFind);
        assertArrayEquals(expected, actual);
    }

    @Test
    void deleteByExistingId (){
        Product[] actual = repository.removeById(1);
        Product[] expected = new Product[]{book2,book3, phone1,phone2,phone3};
        assertArrayEquals(expected, actual);
    }

}
