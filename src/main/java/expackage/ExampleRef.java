package expackage;

public class ExampleRef {

    public int exampleMethod3(int var1) {
        try {
            try {
                if (var1 == 1) {
                    System.out.println("x is equal to 1");
                    return 10;
                }

                System.out.println("x NOT eq to 1");
            } catch (IllegalArgumentException var5) {
                System.out.println("an exception happened");
                if (var5 instanceof Exception) {
                    System.out.println("e IS instance of Exception");
                }

                System.out.println(var5);
                throw var5;
            }

            System.out.println("here is outside");
            return 15;
        } finally {
            System.out.println("this is great");
        }
    }
}
