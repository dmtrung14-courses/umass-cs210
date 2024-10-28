import java.util.ArrayList;
import java.util.List;

public class demo {
    public static void main(String[] args){
        List<String> demo = new ArrayList<String>();
        demo.add("hello");
        demo.add("world");
        for (String i: demo){
            System.out.println(i);
        }
        System.out.println(demo.get(demo.size()-1));
        
    }
}
