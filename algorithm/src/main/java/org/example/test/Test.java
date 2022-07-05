package org.example.test;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * <pre>
 *
 * </pre>
 * @author 杨帮东
 * @since 1.0
 * @date 2020/05/14 10:51
 **/
public class Test {

	@org.junit.jupiter.api.Test
    public void arrayTest() {
        String[] s = new String[0];
        String[] strings = Arrays.copyOf(s, 2);
        strings[0] = "2";
        strings[1] = "2";

        for (String string : strings) {
            System.out.println(string);
        }
    }

	@org.junit.jupiter.api.Test
    public void CopyOnWriteArrayListTest() {
        CopyOnWriteArrayList<Integer> list = new CopyOnWriteArrayList<>();
        list.add(1);
        list.add(2);
        list.get(1);
    }

	@org.junit.jupiter.api.Test
    public void concurrentHashMap() {
        ConcurrentHashMap<String, String> map = new ConcurrentHashMap<>(16);
        map.put("aa", "aa");
        map.put("bb", "bb");
        map.put("cc", "bb");

        map.size();
        map.isEmpty();
    }

    @org.junit.jupiter.api.Test
    public void listSort() {
        ArrayList<SortTest> list = new ArrayList<>();
	    list.size();
        SortTest sortTest = list.get(0);
        System.out.println(sortTest);

        for (int i = 0; i < 9; i++) {
	        list.add(new SortTest(new Random().nextInt(300), "" + i));
        }

        Collections.sort(list, (o1, o2) -> o1.getId() - o2.getId());
        System.out.println(list);

    }



    class SortTest {

	    private int id;

	    private String name;

        public SortTest(int id, String name) {
            this.id = id;
            this.name = name;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }

}
