package androidbook.ch08;

import java.util.ArrayList;

public class NaverRankingManager {
    
    private static ArrayList<String> list = new ArrayList<String>();  
    public static NaverRankingManager manager = new NaverRankingManager();
    private NaverRankingManager()
    {
    }
    
    public static void setList(ArrayList<String> list) {
        NaverRankingManager.list = list;
    }
    public static ArrayList<String> getList() {
        return list;
    }
    
    public static void add(String keyword) {
        list.add(keyword);
    }
    
    public static void removeAll() {
        list.clear();
    }
}