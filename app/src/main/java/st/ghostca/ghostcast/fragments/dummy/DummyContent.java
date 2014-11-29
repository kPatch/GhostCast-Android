package st.ghostca.ghostcast.fragments.dummy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Helper class for providing sample content for user interfaces created by
 * Android template wizards.
 * <p/>
 * TODO: Replace all uses of this class before publishing your app.
 */
public class DummyContent {

    /**
     * An array of sample (dummy) items.
     */
    public static List<DummyItem> ITEMS = new ArrayList<DummyItem>();

    /**
     * A map of sample (dummy) items, by ID.
     */
    public static Map<String, DummyItem> ITEM_MAP = new HashMap<String, DummyItem>();

    static {
        addItem(new DummyItem("1", "Friendly Friend 1"));
        addItem(new DummyItem("2", "Friendly Friend 2"));
        addItem(new DummyItem("3", "Friendly Friend 3"));
        addItem(new DummyItem("4", "Friendly Friend 4"));
        addItem(new DummyItem("5", "Friendly Friend 5"));
        addItem(new DummyItem("6", "Friendly Friend 6"));
        addItem(new DummyItem("7", "Friendly Friend 7"));
        addItem(new DummyItem("8", "Friendly Friend 8"));
        addItem(new DummyItem("9", "Friendly Friend 9"));
        addItem(new DummyItem("10", "Friendly Friend 10"));
        addItem(new DummyItem("11", "Friendly Friend 11"));
        addItem(new DummyItem("12", "Friendly Friend 12"));
        addItem(new DummyItem("13", "Friendly Friend 13"));
        addItem(new DummyItem("14", "Friendly Friend 14"));
    }

    private static void addItem(DummyItem item) {
        ITEMS.add(item);
        ITEM_MAP.put(item.id, item);
    }

    /**
     * A dummy item representing a piece of content.
     */
    public static class DummyItem {
        public String id;
        public String content;

        public DummyItem(String id, String content) {
            this.id = id;
            this.content = content;
        }

        @Override
        public String toString() {
            return content;
        }
    }
}
