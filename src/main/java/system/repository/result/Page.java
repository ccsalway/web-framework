package system.repository.result;

import java.util.List;

public class Page<T> extends Result<T> {

    private long pageNumber;
    private long pageSize;
    private long totalPages;

    //--------------------------------

    public Page() {}

    public Page(List<T> rows, long totalRows, long pageNumber, long pageSize) {
        super(rows, totalRows);
        setPageSize(pageSize);
        setTotalPages();
        setPageNumber(pageNumber); // after setTotalPages
    }

    //--------------------------------

    public long getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(long pageNumber) {
        this.pageNumber = pageNumber;
    }

    public long getPageSize() {
        return pageSize;
    }

    public void setPageSize(long pageSize) {
        this.pageSize = pageSize;
    }

    public long getTotalPages() {
        return totalPages;
    }

    public void setTotalPages() {
        this.totalPages = (long) Math.ceil((double) totalRows / (double) pageSize);
    }

    public boolean getHasNextPage() {
        return pageNumber < getTotalPages();
    }

    public boolean getHasPreviousPage() {
        return totalPages > 0 && pageNumber > 1;
    }

}
