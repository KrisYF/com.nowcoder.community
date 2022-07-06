package com.nowcoder.community.entity;
/*
封装分页的相关信息
 */
public class Page {
    //当前的页码
    private int current = 1;
    //显示的上限
    private int limit = 10;
    //数据的总数(用于计算总的页数)
    private int rows;
    //查询路径（用于复用分页链接）
    private String path;

    public int getCurrent() {
        return current;
    }

    public void setCurrent(int current) {
        if(current >= 1) {
            this.current = current;
        }
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        if(limit >= 1 && limit <= 100) {
            this.limit = limit;
        }
    }

    public int getRows() {
        return rows;
    }

    public void setRows(int rows) {
        if(rows >= 0) {
            this.rows = rows;
        }
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
    /*
    获取当前页的起始行
     */
    public int getOffset() {
        // 通过当前页current计算
        // current * limit - limit
        return (current - 1) * limit;
    }
    /*
    用于获取总的页数
     */
    public int getTotal() {
        return rows / limit == 0 ? rows / limit : rows / limit + 1;
    }
    /*
    获取起始页码
     */
    public int getFrom() {
        int from = current - 2;
        return Math.max(from, 1);
    }
    /*
    获取终止页码
     */
    public int getTo() {
        int to = current + 2;
        int total = getTotal();
        return Math.min(to, total);
    }
}
