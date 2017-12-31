package domain.repository.result;

import java.util.LinkedList;
import java.util.List;

public class Result<T> {

    protected List<T> rows = new LinkedList<>();
    protected long totalRows = 0;

    public void setRows(List<T> rows) {
        this.rows = rows;
    }

    public List<T> getRows() {
        return this.rows;
    }

    public void setTotalRows(long totalRows) {
        this.totalRows = totalRows;
    }

    public long getTotalRows() {
        return totalRows;
    }

}
