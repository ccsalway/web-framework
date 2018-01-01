package system.repository.paging;

public class PageRequest {

    private long pageNumber;
    private long pageSize;
    private String sortBy = "";

    //----------------------------

    public PageRequest() {}

    public PageRequest(String pageNo, String pageSize, String sortBy) {
        setPageNumber(pageNo);
        setPageSize(pageSize);
        setSortBy(sortBy);
    }

    //----------------------------

    public void setPageNumber(long pageNumber) {
        this.pageNumber = Math.max(1, pageNumber);
    }

    public void setPageNumber(String pageNumber) {
        try {
            setPageNumber(Long.parseLong(pageNumber));
        } catch (NumberFormatException e) {
            setPageNumber(1);
        }
    }

    public long getPageNumber() {
        return pageNumber;
    }

    public void setPageSize(long pageSize) {
        this.pageSize = Math.max(10, pageSize);
    }

    public void setPageSize(String pageSize) {
        try {
            setPageSize(Long.parseLong(pageSize));
        } catch (NumberFormatException e) {
            setPageSize(10);
        }
    }

    public long getPageSize() {
        return pageSize;
    }

    public void setSortBy(String sortBy) {
        this.sortBy = sortBy;
    }

    public String getSortBy() {
        return sortBy;
    }

    //----------------------------

    public long getStartRow() {
        long startRow = (pageNumber - 1) * pageSize;
        if (startRow < 0) {  // 999999999999999999L * 10L = -8446744073709551626
            pageNumber = 1;
        }
        return Math.max(0, startRow);
    }

}
