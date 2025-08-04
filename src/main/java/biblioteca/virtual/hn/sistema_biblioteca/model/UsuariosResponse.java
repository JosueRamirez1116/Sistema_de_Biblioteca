package biblioteca.virtual.hn.sistema_biblioteca.model;



import java.io.Serializable;
import java.util.List;

public class UsuariosResponse implements Serializable {
    private List<Usuario> items;
    private boolean hasMore;
    private int limit;
    private int offset;
    private int count;

    // Getters y Setters
    public List<Usuario> getItems() { return items; }
    public void setItems(List<Usuario> items) { this.items = items; }
    public boolean isHasMore() { return hasMore; }
    public void setHasMore(boolean hasMore) { this.hasMore = hasMore; }
    public int getLimit() { return limit; }
    public void setLimit(int limit) { this.limit = limit; }
    public int getOffset() { return offset; }
    public void setOffset(int offset) { this.offset = offset; }
    public int getCount() { return count; }
    public void setCount(int count) { this.count = count; }
}