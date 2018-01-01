package system.repository.paging;

public class PageRequest {

    private long pageNo;
    private long pageSize;

    public PageRequest(long pageNo, long pageSize) {
        this.pageNo = pageNo;
        this.pageSize = pageSize;
    }

    public long currentPage() {
        return pageNo;
    }

    public long startRow() {
        return (pageNo - 1) * pageSize;
    }

    public long pageSize() {
        return pageSize;
    }

}
