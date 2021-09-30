import Base.View.View;
import View.Welcome;

public class TestApplication {


    public static void main(String[] args) throws Exception {
    View view = new View();
    int opt = view.selectOpt(200);
    view.selectOpt(300,200);

    }

}
