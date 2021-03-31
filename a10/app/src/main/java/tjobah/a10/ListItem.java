package tjobah.a10;

import androidx.annotation.NonNull;

import java.io.Serializable;

public class ListItem implements Serializable {
    private long dttm;
    private String item;

    /**
     * constructor for items in list
     * @param item
     */
    public ListItem(String item) {
        this.item = item;
        dttm = System.nanoTime();
    }

    /**
     * constructor for class that takes in time and item
     * @param dttm
     * @param item
     */
    public ListItem(long dttm, String item) {
        this.dttm = dttm;
        this.item = item;
    }

    @NonNull
    @Override
    public String toString(){
        return item;
    }

    public long getDttm() {
        return dttm;
    }

    public String getItem() {
        return item;
    }
}
