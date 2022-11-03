import in.at.BannerGenerator;

public class Main {

    public static void main(String[] args) {
        if(args[0] == null || "".equals(args[0].trim())) {
            System.out.println("Please supply banner input as an argument.");
        } else {
            System.out.println(BannerGenerator.generate(args[0]));
        }
    }
}
