/**
 * http://pt.stackoverflow.com/questions/17610/profundidade-array
 */
public class ProfundidadeArray {

    public static void main(String[] args) {
        //a1=[a2[a3[a4[]],a5]]
        Object[] a5 = null;
        Object[] a4 = { };
        Object[] a3 = {a4};
        Object[] a2 = {a3};
        Object[] a1 = {a2, a5};

        int depth = depth(a1, 0);
        System.out.println(depth);
    }

    public static int depth(Object[] array, int currentDepth) {
        if (array != null && array.length > 0) {
            int result = 0;
            for (Object item : array) {
                //is this item an array?
                if (item != null && item.getClass().isArray()) {
                    int d = depth((Object[]) item, currentDepth + 1);
                    if (d > result) {
                        result = d;
                    }
                }
            }
            return result;
        }
        return currentDepth;
    }

}
