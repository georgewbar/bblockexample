package expackage;

public class Ex3 {

    public int exampleMethod3(int x) {
        try {
            if (x == 1) {
                System.out.println("x is equal to 1");
                return 10;
            }

            System.out.println("x NOT eq to 1");

        } catch (IllegalArgumentException e) {
            System.out.println("an exception happened");
            if (e instanceof Exception) {
                System.out.println("e IS instance of Exception");
            }
            System.out.println(e);
            throw e;
        }

        System.out.println("here is outside");
        return 15;
    }

}
