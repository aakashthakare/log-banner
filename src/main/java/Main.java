import in.at.BannerGenerator;

public class Main {

    public static void main(String[] args) {
        if(args[0] == null || "".equals(args[0].trim())) {
            System.out.println("Please supply banner input as an argument.");
        }
        System.out.println(BannerGenerator.generate(args[0]));
    }
}
