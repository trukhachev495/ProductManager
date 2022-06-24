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



    @Test
    void shouldFindBookByNameIfExists() {
        manager.addProduct(book3);
        String textToFind = "Мастер и Маргарита";
        Product[] expected = new Product[]{book3};
        Product[] actual = manager.searchBy(textToFind);
        assertArrayEquals(expected, actual);
    }

    @Test
    void addingMultipleProducts() {
        manager.addProduct(book1);
        manager.addProduct(book2);
        manager.addProduct(book3);
        manager.addProduct(phone1);
        manager.addProduct(phone2);
        manager.addProduct(phone3);

        String textToFind = "Евгений Онегин";
        Product[] expected = new Product[]{book2};
        Product[] actual = manager.searchBy(textToFind);
        assertArrayEquals(expected, actual);
    }

    @Test
    void addingOneProduct() {
        manager.addProduct(phone1);
        String textToFind = "Iphone";
        Product[] expected = new Product[]{phone1};
        Product[] actual = manager.searchBy(textToFind);
        assertArrayEquals(expected, actual);
    }

    @Test
    void addingTwoProducts() {
        manager.addProduct(phone1);
        manager.addProduct(phone2);

        String textToFind = "Galaxy";
        Product[] expected = new Product[]{phone2};
        Product[] actual = manager.searchBy(textToFind);
        assertArrayEquals(expected, actual);
    }

    @Test
    void deleteByExistingId() {
        manager.addProduct(book1);
        manager.addProduct(book2);
        manager.addProduct(book3);
        manager.addProduct(phone1);
        manager.addProduct(phone2);
        manager.addProduct(phone3);

        Product[] actual = repository.removeById(1);
        Product[] expected = new Product[]{book2, book3, phone1, phone2, phone3};
        assertArrayEquals(expected, actual);
    }

    @Test
    void addingEmpty() {
        String textToFind = null;
        Product[] actual = manager.searchBy(textToFind);
        Product[] expected = new Product[]{};
        assertArrayEquals(expected, actual);

    }
}
