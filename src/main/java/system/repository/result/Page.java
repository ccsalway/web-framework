package system.repository.result;

public class Page<T> extends Result<T> {

    private long currentPage;
    private long pageSize;
    private long totalPages;

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

    public void setTotalPages() {
        this.totalPages = (long) Math.ceil((double) totalRows / (double) pageSize);
    }

    public long getTotalPages() {
        return totalPages;
    }

    public boolean getHasNextPage() {
        return currentPage < getTotalPages();
    }

    public boolean getHasPreviousPage() {
        return currentPage > 1;
    }

}
