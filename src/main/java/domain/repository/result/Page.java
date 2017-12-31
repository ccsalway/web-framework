package domain.repository.result;

public class Page<T> extends Result<T> {

    private long currentPage;
    private long pageSize;

    public void setPageSize(long pageSize) {
        this.pageSize = pageSize;
    }

    public long getPageSize() {
        return pageSize;
    }

    public void setCurrentPage(long currentPage) {
        this.currentPage = currentPage;
    }

    public long getCurrentPage() {
        return currentPage;
    }

    public long getTotalPages() {
        return (long) Math.ceil((double) totalRows / (double) pageSize);
    }

    public boolean getHasNextPage() {
        return currentPage < getTotalPages();
    }

    public boolean getHasPreviousPage() {
        return currentPage > 1;
    }

}
