package biblioteca.virtual.hn.sistema_biblioteca.model;
import java.io.Serializable;
import java.util.List;
public class PrestamoResponse implements Serializable
{
    private List<Prestamo> items;
    private boolean hasMore;
    private int limit;
    private int count;

    public List<Prestamo> getItems() {
        return items;
    }

    public void setItems(List<Prestamo> items) {
        this.items = items;
    }

    public boolean isHasMore() {
        return hasMore;
    }

    public void setHasMore(boolean hasMore) {
        this.hasMore = hasMore;
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
