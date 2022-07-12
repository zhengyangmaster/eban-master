package com.zzy.eban;

import lombok.val;
import org.junit.Test;
import springfox.documentation.service.ApiListing;

import java.lang.reflect.Array;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author ZhuZhengYang
 * @description TODO
 * @since 2022/2/25
 */

public class StreamStudy {

    @Test
    public void filter() {
        List<String> strings = Arrays.asList("abc", "efg", "", "bc", "abcd", "hij");
        List<String> d = strings.stream().filter(s -> s.contains("d")).collect(Collectors.toList());
        System.out.println(d);
    }

    @Test
    public void distinctl() {
        List<String> strings = Arrays.asList("abc", "efg", "bc", "bc", "abcd", "hij");

        List<String> collect = strings.stream().distinct().collect(Collectors.toList());

        System.out.println(collect);

    }

    @Test
    public void map(){

        //List<String> strings = Arrays.asList("abc", "efg", "bc", "bc", "abcd", "hij");

        Map<Integer,String> map = new HashMap<Integer, String>();
        map.put(1,"Java");
        map.put(2,"C++");
        map.put(3,"PHP");
        for (Map.Entry<Integer,String> entry: map.entrySet()) {


        }
      /*  Iterator<Map.Entry<Integer, String>> iterator = map.entrySet().iterator();
      while (iterator.hasNext()){
          final Map.Entry<Integer, String> next = iterator.next();
          System.out.println(next.getKey()+next.getValue());
      }
*/

        for (Map.Entry<Integer,String> entry:map.entrySet()){
            System.out.println(entry.getKey()+entry.getValue());
        }
    }
}
