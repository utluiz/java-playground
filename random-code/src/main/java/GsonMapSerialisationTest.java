import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * http://pt.stackoverflow.com/questions/171506/convers%C3%A3o-do-resultado-do-hibernate-para-json
 */
public class GsonMapSerialisationTest {

    public static void main(String[] args) {

        List<Map<String,Object>> listObj = new ArrayList<>();
        Map<String,Object> map1 = new HashMap<>();
        map1.put("f1", "v1");
        map1.put("f2", 1L);
        listObj.add(map1);
        Map<String,Object> map2 = new HashMap<>();
        map2.put("f1", 1.2D);
        listObj.add(map2);

        Gson gs = new Gson();
        String json = gs.toJson(listObj);
        System.out.println(json);

    }

}
