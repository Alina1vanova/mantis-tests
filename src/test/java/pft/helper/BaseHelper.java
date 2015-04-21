package pft.helper;

/**
 * Created by bohdantrofymchuk on 4/7/15.
 */
public class BaseHelper {
    protected ApplicationManager manager;

    public BaseHelper(ApplicationManager manager) {
        this.manager = manager;
    }


    private void pause(int pause) {
        try {
            Thread.sleep(pause);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
