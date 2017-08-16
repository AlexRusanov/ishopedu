package edu.bionic;

import edu.bionic.config.AppConfig;
import edu.bionic.presentation.console.CommentConsoleController;
import edu.bionic.presentation.console.OrderConsoleController;
import edu.bionic.presentation.console.ProductConsoleController;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ConsoleApplication {

    public static BufferedReader consoleReader = new BufferedReader(new InputStreamReader(System.in));

    private static ProductConsoleController productController;
    private static OrderConsoleController orderConsoleController;
    private static CommentConsoleController commentConsoleController;

    public static void main(String[] args) throws IOException {
        ApplicationContext context = loadJavaConfigContext();

        productController = context.getBean("productConsoleController", ProductConsoleController.class);
        orderConsoleController = context.getBean("orderConsoleController", OrderConsoleController.class);
        commentConsoleController = context.getBean("commentConsoleController", CommentConsoleController.class);

        startPage();
    }

    private static ApplicationContext loadXmlContext() {
        return new ClassPathXmlApplicationContext(new String[]{"spring/spring-app.xml"});
    }

    private static ApplicationContext loadJavaConfigContext() {
        return new AnnotationConfigApplicationContext(AppConfig.class);
    }

    private static void startPage() throws IOException {
        System.out.println("Добро пожаловать в iStore");
        System.out.println();
        System.out.println("Выберите действие:");
        System.out.println("1. Посмотреть и купить товары");
        System.out.println("2. Просмотреть предыдущие заказы");
        System.out.println("3. Оставить отзыв о товарах");
        System.out.println("0. Выйти из приложения");
        switch (consoleReader.readLine()) {
            case "1":
                System.out.println("---------------------------------------");
                byuProducts();
                break;
            case "2":
                System.out.println("---------------------------------------");
                printOrders();
                break;
            case "3":
                System.out.println("---------------------------------------");
                leaveCommentPage();
            case "0":
                System.exit(0);
            default:
                startPage();
        }

    }

    private static void byuProducts() throws IOException {
        orderConsoleController.printProductsInBasket();
        System.out.println("Выберите товар из списка");
        productController.printAllProducts();
        System.out.println();
        System.out.println("0. Завершить заказ");

        int enteredNumber = Integer.parseInt(consoleReader.readLine());
        if (enteredNumber == 0) {
            orderConsoleController.saveOrder();
            startPage();
        } else {
            productPage(enteredNumber);
        }
        System.out.println("--------------------------------------");
        byuProducts();
    }

    private static void productPage(int productId) throws IOException {
        productController.printProductInfo(productId);
        System.out.println();
        commentConsoleController.printCommentForProduct(productId);
        System.out.println();
        System.out.println("Выберите действие:");
        System.out.println("1. Купить товар");
        System.out.println("0. Вернуться");

        int enteredNumber = Integer.parseInt(consoleReader.readLine());
        if (enteredNumber == 1) {
            orderConsoleController.addProductToBasket(enteredNumber);
            byuProducts();
        } else if (enteredNumber == 0) {
            byuProducts();
        }
        System.out.println("--------------------------------------");
        productPage(productId);
    }


    private static void printOrders() throws IOException {
        orderConsoleController.printPreviousOrders();
        System.out.println("--------------------------------------");
        startPage();
    }

    private static void leaveCommentPage() throws IOException {
        System.out.println("Выберите товар, который хотите оценить:");
        productController.printAllProducts();
        System.out.println();
        System.out.println("0. Вернуться на главную");

        int enteredNumber = Integer.parseInt(consoleReader.readLine());
        if (enteredNumber == 0) {
            startPage();
        } else {
            commentConsoleController.createNewComment(enteredNumber);
            System.out.println("Спасибо, ваш отзыв сохранен. Возвращаемся на главную.");
            System.out.println("--------------------------------------");
            startPage();
        }
    }
}
