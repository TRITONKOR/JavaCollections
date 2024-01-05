public class Main {
    public static void main(String[] args) {
        Tester tester = new Tester();
        System.out.println("Тест на 50тис. елементів");
        tester.testing(50_000);
        System.out.println("_____________________________\n\n");

        System.out.println("Тест на 500тис. елементів");
        tester.testing(500_000);
        System.out.println("_____________________________\n\n");

        System.out.println("Тест на 1млн. елементів");
        tester.testing(1_000_000);
        System.out.println("_____________________________\n\n");

        System.out.println(WordReverser.reverseWord("Чесно вообще хз що тут писати"));
    }
}